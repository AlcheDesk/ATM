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
import com.meowlomo.atm.core.model.InstructionBundle;
import com.meowlomo.atm.core.model.InstructionBundleEntry;
import com.meowlomo.atm.core.model.InstructionBundleEntryExample;
import com.meowlomo.atm.core.model.InstructionBundleExample;
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
import com.meowlomo.atm.core.service.base.InstructionBundleEntryService;
import com.meowlomo.atm.core.service.base.InstructionBundleService;
import com.meowlomo.atm.core.service.referrence.InstructionBundleReferenceService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/instructionBundles")
@Api(value = "instructionBundle resources", produces = "application/json")
public class InstructionBundleResource {

    private final Logger logger = LoggerFactory.getLogger(InstructionBundleResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "INSB";

    @Autowired
    private InstructionBundleService instructionBundleService;

    @Autowired
    private InstructionBundleReferenceService instructionBundleReferenceService;

    @Autowired
    private InstructionBundleEntryService instructionBundleEntryService;

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
    @ApiOperation(value = "读取InstructionBundle", response = MeowlomoResponse.class, httpMethod = "GET")
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
    public MeowlomoResponse selectByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest)
            throws Exception {
        logger.info("received instructionBundle select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                InstructionBundleExample example = new InstructionBundleExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionBundleService.countByExample(example));
                List<InstructionBundle> records = instructionBundleService.selectByExampleWithRowbounds(example,
                        rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                InstructionBundleExample.Criteria criteria = null;
                InstructionBundleExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionBundleExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionBundleService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                InstructionBundleExample.Criteria criteria = null;
                InstructionBundleExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionBundleExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionBundleService.countByExample(example));
                List<InstructionBundle> records = null;
                if (queryParams.containsKey("ref")) {
                    records = instructionBundleReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = instructionBundleService.selectByExampleWithRowbounds(example, rowBounds);
                }
                return new MeowlomoResponse(metadata, records, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @ApiOperation(value = "读取单个InstructionBundle", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instructionBundle id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long id) {
        logger.info("received instructionBundle select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            InstructionBundle selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = instructionBundleReferenceService.selectByPrimaryKey(id);
            }
            else {
                selectRecord = instructionBundleService.selectByPrimaryKey(id);
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @ApiOperation(value = "删除单个InstructionBundle", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instructionBundle id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") long id) {
        logger.info("received instructionBundle delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            InstructionBundle record = instructionBundleService.selectByPrimaryKey(id);
            int deleteResult = instructionBundleService.logicalDeleteByPrimaryKey(id);
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @ApiOperation(value = "批量删除InstructionBundle", response = MeowlomoResponse.class, httpMethod = "DELETE")
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
    public MeowlomoResponse logicalDeleteByExample(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest) {
        logger.info("received instructionBundle delete call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                InstructionBundleExample example = new InstructionBundleExample();
                example.or().andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<InstructionBundle> records = instructionBundleService.selectByExample(example);
                metadata.put("count", instructionBundleService.countByExample(example));
                int deleteResult = instructionBundleService.logicalDeleteByExample(example);
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
                com.meowlomo.atm.core.model.InstructionBundleExample.Criteria criteria = new InstructionBundleExample()
                        .createCriteria();
                criteria.andIdIsNotNull();
                InstructionBundleExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionBundleExample.class);
                // count
                List<InstructionBundle> countRecords = instructionBundleService.selectByExample(example);
                // mark deleted
                int deleteResult = instructionBundleService.logicalDeleteByExample(example);
                // get back records
                List<Long> deletedIds = new ArrayList<Long>();
                for (InstructionBundle target : countRecords) {
                    deletedIds.add(target.getId());
                }
                InstructionBundleExample deletedExample = new InstructionBundleExample();
                deletedExample.or().andIdIsNotNull().andIdIn(deletedIds);
                List<InstructionBundle> records = instructionBundleService.selectByExample(deletedExample);
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @ApiOperation(value = "更新InstructionBundle", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            InstructionBundle[] records) throws Exception {
        logger.info("received patch instructionBundle by id call " + uriInfo.getPath());
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
                InstructionBundle record = records[i];
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
                    InstructionBundle record = records[i];
                    int updateResult = instructionBundleService.updateByPrimaryKeySelective(records[i]);
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
                InstructionBundleExample example = new InstructionBundleExample();
                example.or().andIdIn(targetIds);
                List<InstructionBundle> finalRecords = instructionBundleService.selectByExample(example);
                // sort return result
                List<InstructionBundle> finalReturnRecords = new ArrayList<InstructionBundle>();
                for (Long id : targetIds) {
                    for (InstructionBundle oRecord : finalRecords) {
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @ApiOperation(value = "替换或添加InstructionBundle", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            InstructionBundle[] records) {
        logger.info("received put instructionBundle by primary id call");
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
            List<InstructionBundle> finalRecords = new ArrayList<InstructionBundle>();
            for (int i = 0; i < records.length; i++) {
                InstructionBundle record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = instructionBundleService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(record);
                    }
                }
                else {
                    // do update
                    int updateResult = instructionBundleService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(instructionBundleService.selectByPrimaryKey(record.getId()));
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @ApiOperation(value = "添加InstructionBundle", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            InstructionBundle[] records) {
        logger.info("received post instructionBundle call ");
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
            List<InstructionBundle> finalRecords = new ArrayList<InstructionBundle>();
            for (int i = 0; i < records.length; i++) {
                InstructionBundle record = records[i];
                record.setId(null);
                long insertResult = instructionBundleService.insertSelective(record);
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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

    // ===== instructionBundle instructionBundleEntry link start =====
    @POST
    @LogUserActivity
    @Path("/{instructionBundleId}/instructionBundleEntries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个InstructionBundleEntry实体并链接到InstructionBundle", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionBundleId + \"的InstructionBundle不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加InstructionBundleEntry并连接到InstructionBundle操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertInstructionBundleEntryAndLinkToInstructionBundle(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionBundleId") Long instructionBundleId,
            InstructionBundleEntry[] records) throws Exception {
        logger.info("received post instructionBundleEntry by instructionBundle id=" + instructionBundleId);
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
            InstructionBundle checkInstructionBundleRecord = instructionBundleService
                    .selectByPrimaryKey(instructionBundleId);
            if (checkInstructionBundleRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionBundleId + "的InstructionBundle不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // loop all instructionBundleEntries and set instructionBundle id to the record
            List<InstructionBundleEntry> processedRecords = new ArrayList<InstructionBundleEntry>();
            for (InstructionBundleEntry instructionBundleEntry : records) {
                instructionBundleEntry.setInstructionBundleId(instructionBundleId);
                processedRecords.add(instructionBundleEntry);
            }
            List<Long> insertedResult = instructionBundleEntryService.insertRecordsSelective(processedRecords);
            if (insertedResult.size() == records.length) {
                InstructionBundleEntryExample example = new InstructionBundleEntryExample();
                example.or().andIdIn(insertedResult);
                List<InstructionBundleEntry> finalRecords = instructionBundleEntryService.selectByExample(example);
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
                String message = "添加InstructionBundleEntry并连接到InstructionBundle操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomForbiddenException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @Path("/{instructionBundleId}/instructionBundleEntries")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于InstructionBundle的InstructionBundleEntry", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionBundleId + \"的InstructionBundle不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "InstructionBundleEntry Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "InstructionBundleEntry Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "InstructionBundleEntry Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "InstructionBundleEntry Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "InstructionBundleEntry创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "InstructionBundleEntry创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getInstructionBundleEntriesByInstructionBundlePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionBundleId") Long instructionBundleId)
            throws Exception {
        logger.info("received get instructionBundleEntries by instructionBundle id = " + instructionBundleId);
        try {
            // get the record first
            InstructionBundle checkInstructionBundleRecord = instructionBundleService
                    .selectByPrimaryKey(instructionBundleId);
            if (checkInstructionBundleRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionBundleId + "的InstructionBundle不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                InstructionBundleEntryExample instructionBundleEntryExample = new InstructionBundleEntryExample();
                instructionBundleEntryExample.createCriteria().andInstructionBundleIdEqualTo(instructionBundleId);
                List<InstructionBundleEntry> finalReturnRecords = instructionBundleEntryService
                        .selectByExample(instructionBundleEntryExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                InstructionBundleEntryExample.Criteria criteria = null;
                criteria = new InstructionBundleEntryExample().createCriteria();
                criteria.andInstructionBundleIdEqualTo(instructionBundleId);
                InstructionBundleEntryExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionBundleEntryExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<InstructionBundleEntry> finalRecords = null;
                finalRecords = instructionBundleEntryService.selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionBundleEntryService.countByExample(example));
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    @Path("/{instructionBundleId}/instructionBundleEntries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个InstructionBundleEntry实体并链接到InstructionBundle", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionBundleId + \"的InstructionBundle不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加InstructionBundleEntry并连接到InstructionBundle操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutInstructionBundleEntryToInstructionBundle(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionBundleId") Long instructionBundleId,
            InstructionBundleEntry[] records) throws Exception {
        logger.info("received post instructionBundleEntry by instructionBundle id=" + instructionBundleId);
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
            InstructionBundle checkInstructionBundleRecord = instructionBundleService
                    .selectByPrimaryKey(instructionBundleId);
            if (checkInstructionBundleRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionBundleId + "的InstructionBundle不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<InstructionBundleEntry> finalRecords = new ArrayList<InstructionBundleEntry>();
            for (int i = 0; i < records.length; i++) {
                InstructionBundleEntry record = records[i];
                if (record.getId() == null) {
                    // do insert
                    record.setInstructionBundleId(instructionBundleId);
                    long insertResult = instructionBundleEntryService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(instructionBundleEntryService.selectByPrimaryKey(record.getId()));
                    }
                }
                else {
                    record.setInstructionBundleId(instructionBundleId);
                    int updateResult = instructionBundleEntryService.updateByPrimaryKeySelective(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(instructionBundleEntryService.selectByPrimaryKey(record.getId()));
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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

    @DELETE
    @LogUserActivity
    @Path("/{instructionBundleId}/instructionBundleEntries")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到InstructionBundle的InstructionBundleEntry的链接", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除InstructionBundle关联的InstructionBundleEntry操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的InstructionBundleEntry关联到此InstructionBundle，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+instructionBundleId+\"的InstructionBundle不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此InstructionBundle的InstructionBundleEntry。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionBundleId", value = "InstructionBundle ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "InstructionBundleEntry IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkInstructionBundleEntryFromInstructionBundle(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionBundleId") Long instructionBundleId)
            throws Exception {
        logger.info("received post instructionBundleEntry by instructionBundle id=" + instructionBundleId);
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
            InstructionBundle checkRecord = instructionBundleService.selectByPrimaryKey(instructionBundleId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionBundleId + "的InstructionBundle不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // process the ids
            String idsString = queryParams.getFirst("ids");
            String[] idsStringArray = idsString.trim().split(",");
            List<Long> idsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                    .collect(Collectors.toList());
            // start the delete
            InstructionBundleEntryExample recordExample = new InstructionBundleEntryExample();
            recordExample.or().andIdIn(idsList);
            List<InstructionBundleEntry> finalRecords = instructionBundleEntryService.selectByExample(recordExample);
            int deleteResult = instructionBundleEntryService.logicalDeleteByExample(recordExample);
            if (deleteResult == finalRecords.size()) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", deleteResult);
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
            else if (deleteResult != finalRecords.size()) {
                // rollback
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " couldn't execute delete.";
                String code = ERROR_TYPE + "03DEL";
                String message = "存在已删除的InstructionBundleEntry关联到此InstructionBundle，数据不一致。请与管理员联系。并提供唯一码[" + exuuid
                        + "]";
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
                String message = "删除InstructionBundle关联的InstructionBundleEntry操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    // =====instructionBundle instructionBundleEntry link end =====
}
