package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import com.meowlomo.atm.core.annotation.LogUserActivity;
import com.meowlomo.atm.core.model.SystemRequirement;
import com.meowlomo.atm.core.model.SystemRequirementExample;
import com.meowlomo.atm.core.model.SystemRequirementPack;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;
import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLink;
import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLinkExample;
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
import com.meowlomo.atm.core.service.base.SystemRequirementPackService;
import com.meowlomo.atm.core.service.base.SystemRequirementPackSystemRequirementLinkService;
import com.meowlomo.atm.core.service.base.SystemRequirementService;
import com.meowlomo.atm.core.service.referrence.SystemRequirementPackReferenceService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/systemRequirementPacks")
@Api(value = "systemRequirementPack resources", produces = "application/json")
public class SystemRequirementPackResource {

    private final Logger logger = LoggerFactory.getLogger(SystemRequirementPackResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "DRIP";

    @Autowired
    private SystemRequirementPackService systemRequirementPackService;

    @Autowired
    private SystemRequirementPackReferenceService systemRequirementPackReferenceService;

    @Autowired
    private SystemRequirementPackSystemRequirementLinkService systemRequirementPackSystemRequirementLinkService;

    @Autowired
    private SystemRequirementService systemRequirementService;

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
    @ApiOperation(value = "读取SystemRequirementPack", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "log", value = "log (case-sensitive)", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse selectByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest) throws Exception {
        logger.info("received systemRequirementPack select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                SystemRequirementPackExample example = new SystemRequirementPackExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", systemRequirementPackService.countByExample(example));
                List<SystemRequirementPack> records = systemRequirementPackService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                SystemRequirementPackExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new SystemRequirementPackExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                SystemRequirementPackExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        SystemRequirementPackExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", systemRequirementPackService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                SystemRequirementPackExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new SystemRequirementPackExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                SystemRequirementPackExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        SystemRequirementPackExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", systemRequirementPackService.countByExample(example));
                List<SystemRequirementPack> records = null;
                if (queryParams.containsKey("ref")) {
                    records = systemRequirementPackReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = systemRequirementPackService.selectByExampleWithRowbounds(example, rowBounds);
                }
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
            String code = ERROR_TYPE + "CORE01SYS";
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
    @ApiOperation(value = "读取单个SystemRequirementPack", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "systemRequirementPack id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("id") Long id) {
        logger.info("received systemRequirementPack select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            SystemRequirementPack selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = systemRequirementPackReferenceService.selectByPrimaryKey(id);
            }
            else {
                selectRecord = systemRequirementPackService.selectByPrimaryKey(id);
            }
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
            String code = ERROR_TYPE + "CORE02SYS";
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
    @ApiOperation(value = "删除单个SystemRequirementPack", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "systemRequirementPack id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("id") long id) {
        logger.info("received systemRequirementPack delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            SystemRequirementPack record = systemRequirementPackService.selectByPrimaryKey(id);
            int deleteResult = systemRequirementPackService.logicalDeleteByPrimaryKey(id);
            if (deleteResult == 0 && record == null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, record, null);
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
                String message = "不存在ID为" + id + "的对象，无法删除。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01DEL";
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
            String code = ERROR_TYPE + "CORE03SYS";
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
    @ApiOperation(value = "批量删除SystemRequirementPack", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "log", value = "log (case-sensitive)", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse deleteByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest) {
        logger.info("received systemRequirementPack delete call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                SystemRequirementPackExample example = new SystemRequirementPackExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<SystemRequirementPack> records = systemRequirementPackService.selectByExample(example);
                metadata.put("count", systemRequirementPackService.countByExample(example));
                int deleteResult = systemRequirementPackService.logicalDeleteByExample(example);
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
                    String code = ERROR_TYPE + "CORE02DEL";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
                }
            }
            else {
                // default to set is not deleted
                com.meowlomo.atm.core.model.SystemRequirementPackExample.Criteria criteria = new SystemRequirementPackExample()
                        .createCriteria();
                criteria.andIdIsNotNull().andDeletedEqualTo(false);
                SystemRequirementPackExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        SystemRequirementPackExample.class);
                // count
                List<SystemRequirementPack> countRecords = systemRequirementPackService.selectByExample(example);
                // mark deleted
                int deleteResult = systemRequirementPackService.logicalDeleteByExample(example);
                // get back records
                List<Long> deletedIds = new ArrayList<Long>();
                for (SystemRequirementPack target : countRecords) {
                    deletedIds.add(target.getId());
                }
                SystemRequirementPackExample deletedExample = new SystemRequirementPackExample();
                deletedExample.or().andIdIsNotNull().andDeletedEqualTo(true).andIdIn(deletedIds);
                List<SystemRequirementPack> records = systemRequirementPackService.selectByExample(deletedExample);
                if (deleteResult == records.size()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", countRecords.size());
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
                    String message = "无法完成删除，删除执行数与目标数不一致。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "CORE03DEL";
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
     * Update selective.
     *
     * @param records
     *            the records
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @PATCH
    @LogUserActivity
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新SystemRequirementPack", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, SystemRequirementPack[] records) throws Exception {
        logger.info("received patch systemRequirementPack by id call " + uriInfo.getPath());
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01PAT";
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
                SystemRequirementPack record = records[i];
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
                String code = ERROR_TYPE + "CORE02PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else {
                // start the update
                // update one by one
                for (int i = 0; i < records.length; i++) {
                    SystemRequirementPack record = records[i];
                    int updateResult = systemRequirementPackService.updateByPrimaryKeySelective(records[i]);
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
                SystemRequirementPackExample example = new SystemRequirementPackExample();
                example.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<SystemRequirementPack> finalRecords = systemRequirementPackService.selectByExample(example);
                // sort return result
                List<SystemRequirementPack> finalReturnRecords = new ArrayList<SystemRequirementPack>();
                for (Long id : targetIds) {
                    for (SystemRequirementPack oRecord : finalRecords) {
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
                String trace = "exception UUID=" + exuuid + " could not patch all records ";
                String message = "部分或全部更新失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE03PAT";
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
            String code = ERROR_TYPE + "CORE05SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // =====Patch Method End=====

    // =====Put Method Start=====

    /**
     * Replace.
     *
     * @param records
     *            the records
     * @return the meowlomo response
     */
    @PUT
    @LogUserActivity
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "替换或添加SystemRequirementPack", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, SystemRequirementPack[] records) {
        logger.info("received put systemRequirementPack by primary id call");
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " put body is empty";
                String message = "替换内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01PUT";
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

            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<SystemRequirementPack> finalRecords = new ArrayList<SystemRequirementPack>();
            for (int i = 0; i < records.length; i++) {
                SystemRequirementPack record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = systemRequirementPackService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(record);
                    }
                }
                else {
                    // do update
                    int updateResult = systemRequirementPackService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(systemRequirementPackService.selectByPrimaryKey(record.getId()));
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
                String trace = "exception UUID=" + exuuid + " could not put all record ";
                String message = "部分或全部替换添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CROE02PUT";
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
            String code = ERROR_TYPE + "CORE06SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====Put Method End=====

    // =====Post Method Start=====

    /**
     * Insert.
     *
     * @param records
     *            the records
     * @return the meowlomo response
     */
    @POST
    @LogUserActivity
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加SystemRequirementPack", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, SystemRequirementPack[] records) {
        logger.info("received post systemRequirementPack call ");
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01POS";
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

            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            // start the insert
            // insert one by one
            List<SystemRequirementPack> finalRecords = new ArrayList<SystemRequirementPack>();
            for (int i = 0; i < records.length; i++) {
                SystemRequirementPack record = records[i];
                record.setId(null);
                long insertResult = systemRequirementPackService.insertSelective(record);
                if (insertResult != 1) {
                    errorIndex.add((long) i);
                }
                else {
                    finalRecords.add(record);
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
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
                String trace = "exception UUID=" + exuuid + " could not post all record ";
                String message = "部分或全部添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE02POS";
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
            String code = ERROR_TYPE + "CORE07SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ===== Post Method End=====

    // ===== systemRequirementPack systemRequirement link start =====

    @GET
    @Path("/{systemRequirementPackId}/systemRequirements")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于SystemRequirementPack的SystemRequirement", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + systemRequirementPackId + \"的SystemRequirementPack不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "SystemRequirement Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "SystemRequirement Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "SystemRequirement Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "SystemRequirement Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "SystemRequirement创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "SystemRequirement创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getSystemRequirementBySystemRequirementPackPrimaryKey(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("systemRequirementPackId") Long systemRequirementPackId)
            throws Exception {
        logger.info("received get systemRequirements by systemRequirementPack id = " + systemRequirementPackId);
        try {
            // get the record first
            SystemRequirementPack checkSystemRequirementPackRecord = systemRequirementPackService.selectByPrimaryKey(systemRequirementPackId);
            if (checkSystemRequirementPackRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + systemRequirementPackId + "的SystemRequirementPack不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                SystemRequirementPackSystemRequirementLinkExample linkExample = new SystemRequirementPackSystemRequirementLinkExample();
                linkExample.or().andSystemRequirementPackIdEqualTo(systemRequirementPackId);
                List<SystemRequirementPackSystemRequirementLink> links = systemRequirementPackSystemRequirementLinkService.selectByExample(linkExample);
                List<Long> targetIds = new ArrayList<Long>();
                for (SystemRequirementPackSystemRequirementLink link : links) {
                    targetIds.add(link.getSystemRequirementId());
                }
                if (targetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                SystemRequirementExample systemRequirementExample = new SystemRequirementExample();
                systemRequirementExample.or().andIdIn(targetIds);
                List<SystemRequirement> finalReturnRecords = systemRequirementService.selectByExample(systemRequirementExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                SystemRequirementPackSystemRequirementLinkExample linkExample = new SystemRequirementPackSystemRequirementLinkExample();
                linkExample.or().andSystemRequirementPackIdEqualTo(systemRequirementPackId);
                List<SystemRequirementPackSystemRequirementLink> links = systemRequirementPackSystemRequirementLinkService.selectByExample(linkExample);
                List<Long> systemRequirementTargetIds = new ArrayList<Long>();
                for (SystemRequirementPackSystemRequirementLink link : links) {
                    systemRequirementTargetIds.add(link.getSystemRequirementId());
                }
                if (systemRequirementTargetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                SystemRequirementExample.Criteria criteria = null;
                criteria = new SystemRequirementExample().createCriteria();
                criteria.andIdIn(systemRequirementTargetIds);
                SystemRequirementExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        SystemRequirementExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<SystemRequirement> finalRecords = null;

                    finalRecords = systemRequirementService.selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", systemRequirementService.countByExample(example));
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

    @DELETE
    @LogUserActivity
    @Path("/{systemRequirementPackId}/systemRequirements")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到SystemRequirementPack的SystemRequirement的链接", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除SystemRequirementPack关联的SystemRequirement操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的SystemRequirement关联到此SystemRequirementPack，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+systemRequirementPackId+\"的SystemRequirementPack不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此SystemRequirementPack的SystemRequirement。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemRequirementPackId", value = "SystemRequirementPack ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "SystemRequirement IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkSystemRequirementsFromSystemRequirementPack(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("systemRequirementPackId") Long systemRequirementPackId) throws Exception {
        logger.info("received post systemRequirement by systemRequirementPack id=" + systemRequirementPackId);
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
            SystemRequirementPack checkRecord = systemRequirementPackService.selectByPrimaryKey(systemRequirementPackId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + systemRequirementPackId + "的SystemRequirementPack不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // process the ids
            String idsString = queryParams.getFirst("ids");
            String[] idsStringArray = idsString.trim().split(",");
            List<Long> idsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                    .collect(Collectors.toList());
            // check the links all exists
            SystemRequirementPackSystemRequirementLinkExample linkExample = new SystemRequirementPackSystemRequirementLinkExample();
            linkExample.or().andSystemRequirementPackIdEqualTo(systemRequirementPackId).andSystemRequirementIdIn(idsList);
            long linkCount = systemRequirementPackSystemRequirementLinkService.countByExample(linkExample);
            if (linkCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "输入中存在未关联到此SystemRequirementPack的SystemRequirement。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            // start the delete
            SystemRequirementExample recordExample = new SystemRequirementExample();
            recordExample.or().andIdIn(idsList);
            List<SystemRequirement> finalRecords = systemRequirementService.selectByExample(recordExample);
            int deleteResult = systemRequirementPackSystemRequirementLinkService.deleteByExample(linkExample);
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
                String message = "存在已删除的SystemRequirement关联到此SystemRequirementPack，数据不一致。请与管理员联系。并提供唯一码[" + exuuid + "]";
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
                String message = "删除SystemRequirementPack关联的SystemRequirement操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    
    @PUT
    @LogUserActivity
    @Path("/{systemRequirementPackId}/systemRequirements")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个SystemRequirement实体并链接到SystemRequirementPack", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + systemRequirementPackId + \"的SystemRequirementPack不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加SystemRequirement并连接到SystemRequirementPack操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutSystemRequirementToSystemRequirementPack(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("systemRequirementPackId") Long systemRequirementPackId, SystemRequirement[] records)
            throws Exception {
        logger.info("received post systemRequirement by systemRequirementPack id=" + systemRequirementPackId);
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
            // BeanValidator.BeanValidation(records, ERROR_TYPE);

            // get the record first
            SystemRequirementPack checkSystemRequirementPackRecord = systemRequirementPackService.selectByPrimaryKey(systemRequirementPackId);
            if (checkSystemRequirementPackRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + systemRequirementPackId + "的SystemRequirementPack不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<SystemRequirement> finalRecords = new ArrayList<SystemRequirement>();
            for (int i = 0; i < records.length; i++) {
                SystemRequirement record = records[i];
                if (record.getId() == null) {
                    // insert link
                    SystemRequirementPackSystemRequirementLink link = new SystemRequirementPackSystemRequirementLink();
                    link.setSystemRequirementId(record.getId());
                    link.setSystemRequirementPackId(systemRequirementPackId);
                    if (systemRequirementPackSystemRequirementLinkService.insertSelective(link) == 1) {
                        finalRecords.add(record);
                    }
                    else {
                        errorIndex.add((long) i);
                    }
                }
                else {
                    // add the link
                    SystemRequirementPackSystemRequirementLink link = new SystemRequirementPackSystemRequirementLink();
                    link.setSystemRequirementId(record.getId());
                    link.setSystemRequirementPackId(systemRequirementPackId);
                    if (systemRequirementPackSystemRequirementLinkService.insertSelective(link) == 1) {
                        finalRecords.add(systemRequirementService.selectByPrimaryKey(record.getId()));
                    }
                    else {
                        errorIndex.add((long) i);
                    }
                }
            }
            // check all update success
            if (errorIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
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
                String trace = "exception UUID=" + exuuid + " could not put all record ";
                String message = "部分或全部替换添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CROE02PUT";
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
            String code = ERROR_TYPE + "CORE06SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====systemRequirementPack systemRequirement link end =====
}
