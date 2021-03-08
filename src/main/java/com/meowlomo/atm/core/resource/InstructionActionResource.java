package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.annotation.LogUserActivity;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeExample;
import com.meowlomo.atm.core.model.ElementTypeInstructionActionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionActionLinkExample;
import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLinkExample;
import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionActionExample;
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLink;
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLinkExample;
import com.meowlomo.atm.core.model.InstructionOption;
import com.meowlomo.atm.core.model.InstructionOptionExample;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.InstructionTypeExample;
import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLink;
import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLinkExample;
import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;
import com.meowlomo.atm.core.resource.exception.CustomForbiddenException;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.atm.core.resource.exception.CustomNotAllowedException;
import com.meowlomo.atm.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.atm.core.resource.exception.CustomNotFoundException;
import com.meowlomo.atm.core.resource.exception.CustomNotSupportedException;
import com.meowlomo.atm.core.resource.exception.CustomServiceUnavailableException;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.resource.query.QueryParameterValidator;
import com.meowlomo.atm.core.resource.query.SearchExampleGenerator;
import com.meowlomo.atm.core.service.base.ElementService;
import com.meowlomo.atm.core.service.base.ElementTypeInstructionActionLinkService;
import com.meowlomo.atm.core.service.base.ElementTypeInstructionOptionLinkService;
import com.meowlomo.atm.core.service.base.ElementTypeService;
import com.meowlomo.atm.core.service.base.InstructionActionInstructionOptionLinkService;
import com.meowlomo.atm.core.service.base.InstructionActionService;
import com.meowlomo.atm.core.service.base.InstructionOptionService;
import com.meowlomo.atm.core.service.base.InstructionTypeInstructionActionLinkService;
import com.meowlomo.atm.core.service.base.InstructionTypeService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/instructionActions")
@Api(value = "instructionAction resources", produces = "application/json")
public class InstructionActionResource {

    private final Logger logger = LoggerFactory.getLogger(InstructionActionResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "ELEA";

    @Autowired
    private InstructionActionService instructionActionService;

    @Autowired
    private ElementTypeInstructionActionLinkService elementTypeInstructionActionLinkService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private InstructionOptionService instructionOptionService;

    @Autowired
    private InstructionActionInstructionOptionLinkService instructionActionInstructionOptionLinkService;

    @Autowired
    private ElementTypeInstructionOptionLinkService elementTypeInstructionOptionLinkService;

    @Autowired
    private InstructionTypeService instructionTypeService;

    @Autowired
    private ElementService elementService;

    @Autowired
    private InstructionTypeInstructionActionLinkService instructionTypeInstructionActionLinkService;

    // =====Get Method Start=====

    /**
     * Select by example.
     *
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取InstructionAction", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "instructionAction status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse selectByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest)
            throws Exception {
        logger.info("received instructionAction select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                InstructionActionExample example = new InstructionActionExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionActionService.countByExample(example));
                List<InstructionAction> records = instructionActionService.selectByExampleWithRowbounds(example,
                        rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                InstructionActionExample example = this.searchExampleGenerator.generateExample(uriInfo, null,
                        InstructionActionExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionActionService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                String instructionType = queryParams.getFirst("instructionType");
                InstructionTypeInstructionActionLinkExample instructionTypeInstructionActionLinkExample = new InstructionTypeInstructionActionLinkExample();
                instructionTypeInstructionActionLinkExample.or().andInstructionTypeEqualTo(instructionType);
                List<InstructionTypeInstructionActionLink> instructionTypeInstructionActionLinks = instructionTypeInstructionActionLinkService
                        .selectByExample(instructionTypeInstructionActionLinkExample);
                List<String> instructionActionTargetNames = new ArrayList<String>();
                for (InstructionTypeInstructionActionLink instructionTypeInstructionActionLink : instructionTypeInstructionActionLinks) {
                    instructionActionTargetNames.add(instructionTypeInstructionActionLink.getInstructionAction());
                }
                if (instructionActionTargetNames.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }

                String elementType = queryParams.getFirst("elementType");
                // filter with elementType
                if (elementType != null) {
                    ElementTypeInstructionActionLinkExample elementTypeInstructionActionLinkExample = new ElementTypeInstructionActionLinkExample();
                    elementTypeInstructionActionLinkExample.or().andElementTypeEqualTo(elementType);
                    List<ElementTypeInstructionActionLink> elementTypeInstructionActionLinks = elementTypeInstructionActionLinkService
                            .selectByExample(elementTypeInstructionActionLinkExample);
                    List<String> elementTypeInstructionActionTargetNames = new ArrayList<String>();
                    for (ElementTypeInstructionActionLink elementTypeInstructionActionLink : elementTypeInstructionActionLinks) {
                        elementTypeInstructionActionTargetNames
                                .add(elementTypeInstructionActionLink.getInstructionAction());
                    }
                    if (elementTypeInstructionActionTargetNames.isEmpty()) {
                        ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                        metadata.put("count", 0);
                        return new MeowlomoResponse(metadata, null, null);
                    }
                    instructionActionTargetNames.retainAll(elementTypeInstructionActionTargetNames);
                }
                InstructionActionExample.Criteria criteria = null;
                criteria = new InstructionActionExample().createCriteria();
                criteria.andNameIn(instructionActionTargetNames);

                InstructionActionExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionActionExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionActionService.countByExample(example));
                List<InstructionAction> records = instructionActionService.selectByExampleWithRowbounds(example,
                        rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Select by primary id.
     *
     * @param id
     *            the id
     * @return the meowlomo response
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个InstructionAction", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instruction id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long id) {
        logger.info("received instructionAction select by id call");
        try {
            InstructionAction selectRecord = instructionActionService.selectByPrimaryKey(id);
            if (selectRecord != null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, selectRecord, null);
            }
            else {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "02SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====Get Method End=====

    // =====Delete Method Start=====

    /**
     * Delete by ID.
     *
     * @param id
     *            the id
     * @return the meowlomo response
     */
    @DELETE
    @LogUserActivity
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除单个InstructionAction", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "task id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") long id) {
        logger.info("received instructionAction delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            InstructionAction record = instructionActionService.selectByPrimaryKey(id);
            int deleteResult = instructionActionService.deleteByPrimaryKey(id);
            if (deleteResult == 0 && record == null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            else if (deleteResult == 1 && record != null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, record, null);
            }
            else {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + id + " does not exists.";
                String message = "不存在ID为" + id + "的对象，无法更新。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "03SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Delete by example.
     *
     * @return the meowlomo response
     */
    @DELETE
    @LogUserActivity
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除InstructionAction", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "instructionAction status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse deleteByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest) {
        logger.info("received instructionAction delete call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                InstructionActionExample example = new InstructionActionExample();
                example.or().andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<InstructionAction> records = instructionActionService.selectByExample(example);
                metadata.put("count", instructionActionService.countByExample(example));
                int deleteResult = instructionActionService.deleteByExample(example);
                if (deleteResult == records.size()) {
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String trace = "exception UUID=" + exuuid + " target count <> delete count ";
                    String message = "无法完成删除，删除数与目标数。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "02DEL";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
                }
            }
            else {
                com.meowlomo.atm.core.model.InstructionActionExample.Criteria criteria = new InstructionActionExample()
                        .createCriteria();
                criteria.andIdIsNotNull();
                InstructionActionExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionActionExample.class);
                List<InstructionAction> records = instructionActionService.selectByExample(example);
                int deleteResult = instructionActionService.deleteByExample(example);
                if (deleteResult == records.size()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", instructionActionService.countByExample(example));
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String trace = "exception UUID=" + exuuid + " target count <> delete count ";
                    String message = "无法完成删除，删除数与目标数。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "03DEL";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
                }
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "04SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // =====Delete Method End=====

    // =====Patch Method Start=====

    /**
     * Update selective by example.
     *
     * @param record
     *            the record
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @PATCH
    @LogUserActivity
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新InstructionAction", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "instructionAction status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            InstructionAction[] records) throws Exception {
        logger.info("received patch instructionAction by id call " + uriInfo.getPath());
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }

            List<Long> targetIds = new ArrayList<Long>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                InstructionAction record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            if (records.length != targetIds.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "部分更新请求不含ID。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else {
                // start the update
                // update one by one
                for (int i = 0; i < records.length; i++) {
                    InstructionAction record = records[i];
                    int updateResult = instructionActionService.updateByPrimaryKeySelective(records[i]);
                    if (updateResult != 1) {
                        errorIndex.add(record.getId());
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                InstructionActionExample example = new InstructionActionExample();
                example.or().andIdIn(targetIds);
                List<InstructionAction> finalRecords = instructionActionService.selectByExample(example);
                // sort return result
                List<InstructionAction> finalReturnRecords = new ArrayList<InstructionAction>();
                for (Long id : targetIds) {
                    for (InstructionAction oRecord : finalRecords) {
                        if (oRecord.getId().equals(id)) {
                            finalReturnRecords.add(oRecord);
                        }
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// not all success
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或全部更新失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
            }

        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "05SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // =====Patch Method End=====

    // =====Put Method Start=====

    @PUT
    @LogUserActivity
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "替换或添加InstructionAction", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            InstructionAction[] records) {
        logger.info("received put instructionAction by primary id call");
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " put body is empty";
                String message = "替换内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PUT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            List<Long> targetIds = new ArrayList<Long>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                InstructionAction record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            if (records.length != targetIds.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "部分替换请求不含ID。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02PUT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else {
                // start the update
                // update one by one
                for (int i = 0; i < records.length; i++) {
                    InstructionAction record = records[i];
                    int updateResult = instructionActionService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add(record.getId());
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                InstructionActionExample example = new InstructionActionExample();
                example.or().andIdIn(targetIds);
                List<InstructionAction> finalRecords = instructionActionService.selectByExample(example);
                // sort return result
                List<InstructionAction> finalReturnRecords = new ArrayList<InstructionAction>();
                for (Long id : targetIds) {
                    for (InstructionAction oRecord : finalRecords) {
                        if (oRecord.getId().equals(id)) {
                            finalReturnRecords.add(oRecord);
                        }
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// not all success
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或全部替换失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PUT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomForbiddenException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "06SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====Put Method End=====

    // =====Post Method Start=====
    /**
     * Insert.
     *
     * @param record
     *            the record
     * @return the meowlomo response
     */
    @POST
    @LogUserActivity
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加InstructionAction", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "instructionAction UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            InstructionAction[] records) {
        logger.info("received post instructionAction call ");
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            List<Long> ids = new ArrayList<Long>();
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            // start the insert
            // insert one by one
            for (int i = 0; i < records.length; i++) {
                InstructionAction record = records[i];
                record.setId(null);
                long insertResult = instructionActionService.insertSelective(record);
                if (insertResult != 1) {
                    errorIndex.add(record.getId());
                }
                else {
                    ids.add(record.getId());
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
                InstructionActionExample example = new InstructionActionExample();
                example.or().andIdIn(ids);
                List<InstructionAction> finalRecords = instructionActionService.selectByExample(example);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
            else {// not all success
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或全部添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "08SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ===== Post Method End=====

    // ===== instructionAction elementType link start =====

    @GET
    @Path("/{instructionActionId}/elementTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取InstructionAction关联的ElementType", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "task priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "task type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "task UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "task status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getElementTypeByInstructionActionPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionActionId") Long instructionActionId)
            throws Exception {
        logger.info("received get elementTypes by instructionAction id = " + instructionActionId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                List<ElementType> records = elementTypeInstructionActionLinkService
                        .listElementTypeByInstructionActionPrimaryKey(instructionActionId);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                List<ElementType> targetRecords = elementTypeInstructionActionLinkService
                        .listElementTypeByInstructionActionPrimaryKey(instructionActionId);
                // empty just return
                if (targetRecords.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                List<Long> targetIds = new ArrayList<Long>();
                ElementTypeExample.Criteria criteria = null;
                for (ElementType record : targetRecords) {
                    targetIds.add(record.getId());
                }
                criteria = new ElementTypeExample().createCriteria();
                criteria.andIdIn(targetIds);
                ElementTypeExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        ElementTypeExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<ElementType> finalRecords = elementTypeService.selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", elementTypeService.countByExample(example));
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ===== instructionAction elementType link end =====

    // ===== instructionAction instructionOption link start =====

    @POST
    @LogUserActivity
    @Path("/{instructionActionId}/instructionOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个InstructionOption实体并链接到InstructionAction", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionActionId + \"的InstructionAction不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加InstructionOption并连接到InstructionAction操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertInstructionOptionAndLinkToInstructionAction(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionActionId") Long instructionActionId,
            InstructionOption[] records) throws Exception {
        logger.info("received post instructionOption by instructionAction id=" + instructionActionId);
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            // get the record first
            InstructionAction checkInstructionActionRecord = instructionActionService
                    .selectByPrimaryKey(instructionActionId);
            if (checkInstructionActionRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionActionId + "的InstructionAction不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<InstructionOption> entries = new ArrayList<InstructionOption>();
            for (InstructionOption entry : records) {
                entry.setId(instructionActionId);
                entries.add(entry);
            }
            List<Long> insertedResult = instructionOptionService.insertRecordsSelective(entries);
            if (insertedResult.size() == records.length) {
                // add link
                for (Long id : insertedResult) {
                    InstructionActionInstructionOptionLink link = new InstructionActionInstructionOptionLink();
                    link.setInstructionOption(RuntimeVariables.getIdToInstructionOptionMap().get(id));
                    link.setInstructionAction(RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId));
                    instructionActionInstructionOptionLinkService.insert(link);
                }
                InstructionOptionExample example = new InstructionOptionExample();
                example.or().andIdIn(insertedResult);
                List<InstructionOption> finalRecords = instructionOptionService.selectByExample(example);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
            else {
                // rollback
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " couldn't complete insert.";
                String code = ERROR_TYPE + "02POS";
                String message = "添加InstructionOption并连接到InstructionAction操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomForbiddenException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @Path("/{instructionActionId}/instructionOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于InstructionAction的InstructionOption", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionActionId + \"的InstructionAction不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "InstructionOption Name，支持模糊搜索", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse getInstructionOptionByInstructionActionPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionActionId") Long instructionActionId)
            throws Exception {
        logger.info("received get instructionOptions by instructionAction id = " + instructionActionId);
        try {
            // get the record first
            InstructionAction checkInstructionActionRecord = instructionActionService
                    .selectByPrimaryKey(instructionActionId);
            if (checkInstructionActionRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionActionId + "的InstructionAction不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                InstructionActionInstructionOptionLinkExample linkExample = new InstructionActionInstructionOptionLinkExample();
                linkExample.or().andInstructionActionEqualTo(
                        RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId));
                List<InstructionActionInstructionOptionLink> links = instructionActionInstructionOptionLinkService
                        .selectByExample(linkExample);
                List<String> targetNames = new ArrayList<String>();
                for (InstructionActionInstructionOptionLink link : links) {
                    targetNames.add(link.getInstructionOption());
                }
                if (targetNames.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                InstructionOptionExample instructionOptionExample = new InstructionOptionExample();
                instructionOptionExample.or().andNameIn(targetNames);
                List<InstructionOption> finalReturnRecords = instructionOptionService
                        .selectByExample(instructionOptionExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                String elementType = queryParams.getFirst("elementType");
                String instructionType = queryParams.getFirst("instructionType");
                InstructionActionInstructionOptionLinkExample linkExample = new InstructionActionInstructionOptionLinkExample();
                linkExample.or().andInstructionActionEqualTo(
                        RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId));
                List<InstructionActionInstructionOptionLink> links = instructionActionInstructionOptionLinkService
                        .selectByExample(linkExample);
                List<String> instructionOptionTargetNames = new ArrayList<String>();
                for (InstructionActionInstructionOptionLink link : links) {
                    instructionOptionTargetNames.add(link.getInstructionOption());
                }
                if (instructionOptionTargetNames.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                // get virtual_element by instructionType if exist
                if (instructionType != null) {
                    InstructionTypeExample instructionTypeExample = new InstructionTypeExample();
                    instructionTypeExample.createCriteria().andNameEqualTo(instructionType);
                    List<InstructionType> instructionTypes = instructionTypeService
                            .selectByExample(instructionTypeExample);
                    if (instructionTypes.get(0).getVirtualElementId() != null) {
                        Element element = elementService
                                .selectByPrimaryKey(instructionTypes.get(0).getVirtualElementId());
                        ElementTypeExample elementTypeExample = new ElementTypeExample();
                        elementTypeExample.createCriteria().andNameEqualTo(element.getType());
                        elementType = elementTypeService
                                .selectByPrimaryKey(RuntimeVariables.getElementTypeToIdMap().get(element.getType()))
                                .getName();
                    }
                }
                // filter with elementType
                if (elementType != null) {
                    ElementTypeInstructionOptionLinkExample elementTypeInstructionOptionLinkExample = new ElementTypeInstructionOptionLinkExample();
                    elementTypeInstructionOptionLinkExample.or().andElementTypeEqualTo(elementType);
                    List<ElementTypeInstructionOptionLink> elementTypeInstructionOptionLinks = elementTypeInstructionOptionLinkService
                            .selectByExample(elementTypeInstructionOptionLinkExample);
                    List<String> elementTypeInstructionOptionTargetNames = new ArrayList<String>();
                    for (ElementTypeInstructionOptionLink elementTypeInstructionOptionLink : elementTypeInstructionOptionLinks) {
                        elementTypeInstructionOptionTargetNames
                                .add(elementTypeInstructionOptionLink.getInstructionOption());
                    }
                    if (elementTypeInstructionOptionTargetNames.isEmpty()) {
                        ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                        metadata.put("count", 0);
                        return new MeowlomoResponse(metadata, null, null);
                    }
                    instructionOptionTargetNames.retainAll(elementTypeInstructionOptionTargetNames);
                }
                InstructionOptionExample.Criteria criteria = null;
                criteria = new InstructionOptionExample().createCriteria();
                criteria.andNameIn(instructionOptionTargetNames);
                InstructionOptionExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionOptionExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<InstructionOption> finalRecords = instructionOptionService.selectByExampleWithRowbounds(example,
                        rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionOptionService.countByExample(example));
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @PUT
    @LogUserActivity
    @Path("/{instructionActionId}/instructionOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "建立单个或多个InstructionOption到InstructionAction的链接", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+instructionActionId+\"的InstructionAction不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "关联InstructionOption到InstructionAction的操作无法全部完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionActionId", value = "InstructionAction ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "InstructionOption IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse linkInstructionOptionToInstructionAction(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionActionId") Long instructionActionId,
            InstructionOption[] records) throws Exception {
        logger.info("received put instructionOption by instructionAction id = " + instructionActionId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // empty just return
            if (!queryParams.containsKey("ids")) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else if (queryParams.getFirst("ids") == null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            else if (queryParams.getFirst("ids").isEmpty()) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            if (!QueryParameterValidator.idsValidator(queryParams.getFirst("ids"))) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // get the record first
            InstructionAction checkRecord = instructionActionService.selectByPrimaryKey(instructionActionId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionActionId + "的InstructionAction不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02PUT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // process the ids
            String idsString = queryParams.getFirst("ids");
            String[] idsStringArray = idsString.trim().split(",");
            // get InstructionOption IDs
            List<Long> targetIdList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                    .collect(Collectors.toList());
            // create the link one by one
            List<Long> targetInstructionOptionRecordIds = new ArrayList<Long>();
            List<Long> errorInstructionOptionIds = new ArrayList<Long>();
            for (Long id : targetIdList) {
                InstructionActionInstructionOptionLinkExample linkExample = new InstructionActionInstructionOptionLinkExample();
                linkExample.or().andInstructionOptionEqualTo(RuntimeVariables.getIdToInstructionOptionMap().get(id))
                        .andInstructionActionEqualTo(
                                RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId));
                long countResult = instructionActionInstructionOptionLinkService.countByExample(linkExample);
                if (countResult == 1) {
                    targetInstructionOptionRecordIds.add(id);
                }
                else {
                    // add the link
                    InstructionActionInstructionOptionLink link = new InstructionActionInstructionOptionLink();
                    link.setInstructionOption(RuntimeVariables.getIdToInstructionOptionMap().get(id));
                    link.setInstructionAction(RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId));
                    if (instructionActionInstructionOptionLinkService.insertSelective(link) == 1) {
                        targetInstructionOptionRecordIds.add(id);
                    }
                    else {
                        errorInstructionOptionIds.add(id);
                    }
                }
            }
            // check total added
            if (targetInstructionOptionRecordIds.size() == targetIdList.size() && errorInstructionOptionIds.isEmpty()) {
                // get the instructionOption
                InstructionOptionExample recordExample = new InstructionOptionExample();
                recordExample.or().andIdIn(targetInstructionOptionRecordIds);
                List<InstructionOption> finalRecords = instructionOptionService.selectByExample(recordExample);
                // sort the result
                List<InstructionOption> finalReturnRecords = new ArrayList<InstructionOption>();
                for (Long oId : targetInstructionOptionRecordIds) {
                    for (InstructionOption oRecord : finalRecords) {
                        if (oRecord.getId() == oId) {
                            finalReturnRecords.add(oRecord);
                        }
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {
                // rollback
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " couldn't execute delete.";
                String code = ERROR_TYPE + "03PUT";
                String message = "关联InstructionOption到InstructionAction的操作无法全部完成，错误IDs"
                        + errorInstructionOptionIds.toString() + "请与管理员联系。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomForbiddenException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @DELETE
    @LogUserActivity
    @Path("/{instructionActionId}/instructionOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到InstructionAction的InstructionOption", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除InstructionAction关联的InstructionOption操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的InstructionOption关联到此InstructionAction，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+instructionActionId+\"的InstructionAction不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分或全部ID不存在对应的InstructionOption", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此InstructionAction的InstructionOption。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionActionId", value = "InstructionAction ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "InstructionOption IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkInstructionOptionFromInstructionAction(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionActionId") Long instructionActionId)
            throws Exception {
        logger.info("received post instructionOption by instructionAction id=" + instructionActionId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // empty just return
            if (!queryParams.containsKey("ids")) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else if (queryParams.getFirst("ids") == null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            else if (queryParams.getFirst("ids").isEmpty()) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            if (!QueryParameterValidator.idsValidator(queryParams.getFirst("ids"))) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // get the record first
            InstructionAction checkRecord = instructionActionService.selectByPrimaryKey(instructionActionId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionActionId + "的InstructionAction不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // process the ids
            String idsString = queryParams.getFirst("ids");
            String[] idsStringArray = idsString.trim().split(",");
            List<Long> idsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                    .collect(Collectors.toList());
            Set<Long> idSet = RuntimeVariables.getIdToInstructionOptionMap().keySet();
            if (!idSet.containsAll(idsList)) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "部分或全部ID不存在对应的InstructionOption";
                String code = ERROR_TYPE + "02DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<String> nameList = new ArrayList<String>();
            for (Long oId : idsList) {
                nameList.add(RuntimeVariables.getIdToInstructionOptionMap().get(oId));
            }
            // check the links all exists
            InstructionActionInstructionOptionLinkExample linkExample = new InstructionActionInstructionOptionLinkExample();
            linkExample.or()
                    .andInstructionActionEqualTo(
                            RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId))
                    .andInstructionOptionIn(nameList);
            long linkCount = instructionActionInstructionOptionLinkService.countByExample(linkExample);
            if (linkCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "输入中存在未关联到此InstructionAction的InstructionOption。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            // start the delete
            InstructionOptionExample recordExample = new InstructionOptionExample();
            recordExample.or().andIdIn(idsList);
            List<InstructionOption> finalRecords = instructionOptionService.selectByExample(recordExample);
            int deleteResult = instructionActionInstructionOptionLinkService.deleteByExample(linkExample);
            if (deleteResult == linkCount && finalRecords.size() == linkCount) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", deleteResult);
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
            else if (deleteResult == linkCount && finalRecords.size() != linkCount) {
                // rollback
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " couldn't execute delete.";
                String code = ERROR_TYPE + "03DEL";
                String message = "存在已删除的InstructionOption关联到此InstructionAction，数据不一致。请与管理员联系。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
            else {
                // rollback
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " couldn't execute delete.";
                String code = ERROR_TYPE + "03DEL";
                String message = "删除InstructionAction关联的InstructionOption操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====instructionAction instructionOption link end =====
}
