package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.meowlomo.atm.core.model.ExecutionLog;
import com.meowlomo.atm.core.model.ExecutionLogExample;
import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
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
import com.meowlomo.atm.core.resource.query.SearchExampleGenerator;
import com.meowlomo.atm.core.service.base.ExecutionLogService;
import com.meowlomo.atm.core.service.base.InstructionResultService;
import com.meowlomo.atm.core.service.base.RunService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/executionLogs")
@Api(value = "executionLog resources", produces = "application/json")
public class ExecutionLogResource {

    private final Logger logger = LoggerFactory.getLogger(ExecutionLogResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "EXEL";

    @Autowired
    private ExecutionLogService executionLogService;

    @Autowired
    private InstructionResultService instructionResultService;

    @Autowired
    private RunService runService;

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
    @ApiOperation(value = "读取ExecutionLog", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "executionLog status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse selectByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest) throws Exception {
        logger.info("received executionLog select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                ExecutionLogExample example = new ExecutionLogExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", executionLogService.countByExample(example, queryParams.getFirst("mode")));
                List<ExecutionLog> records = executionLogService.selectByExampleWithRowbounds(example, rowBounds,
                        queryParams.getFirst("mode"));
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                ExecutionLogExample example = this.searchExampleGenerator.generateExample(uriInfo, null,
                        ExecutionLogExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", executionLogService.countByExample(example, queryParams.getFirst("mode")));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                ExecutionLogExample example = this.searchExampleGenerator.generateExample(uriInfo, null,
                        ExecutionLogExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", executionLogService.countByExample(example, queryParams.getFirst("mode")));
                List<ExecutionLog> records = executionLogService.selectByExampleWithRowbounds(example, rowBounds,
                        queryParams.getFirst("mode"));
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
    @ApiOperation(value = "读取单个ExecutionLog", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instruction id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("id") Long id) {
        logger.info("received executionLog select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            ExecutionLog selectRecord = executionLogService.selectByPrimaryKey(id, queryParams.getFirst("mode"));
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

//    // =====Patch Method Start=====
//
//    /**
//     * Update selective by example.
//     *
//     * @param record
//     *            the record
//     * @return the meowlomo response
//     * @throws Exception
//     *             the exception
//     */
//    @PATCH
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "更新ExecutionLog", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "status", value = "executionLog status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
//    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, ExecutionLog[] records) throws Exception {
//        logger.info("received patch executionLog by id call " + uriInfo.getPath());
//        try {
//            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "01PAT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else if (records.length == 0) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, null, null);
//            }
//
//            List<Long> targetIds = new ArrayList<Long>();
//            // loop and check each record
//            for (int i = 0; i < records.length; i++) {
//                ExecutionLog record = records[i];
//                if (record.getId() != null && record.getId() > 0) {
//                    targetIds.add(record.getId());
//                }
//            }
//            // check all have id
//            List<Long> errorIndex = new ArrayList<Long>();
//            if (records.length != targetIds.size()) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "部分更新请求不含ID。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "02PAT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else {
//                // start the update
//                // update one by one
//                for (int i = 0; i < records.length; i++) {
//                    ExecutionLog record = records[i];
//                    int updateResult = executionLogService.updateByPrimaryKeySelective(records[i],
//                            queryParams.getFirst("mode"));
//                    if (updateResult != 1) {
//                        errorIndex.add(record.getId());
//                    }
//                }
//            }
//            // check all update sucess
//            if (errorIndex.isEmpty()) {
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
//                }
//                catch (Exception e) {
//
//                }
//                ExecutionLogExample example = new ExecutionLogExample();
//                example.or().andIdIn(targetIds);
//                List<ExecutionLog> finalRecords = executionLogService.selectByExample(example,
//                        queryParams.getFirst("mode"));
//                // sort return result
//                List<ExecutionLog> finalReturnRecords = new ArrayList<ExecutionLog>();
//                for (Long id : targetIds) {
//                    for (ExecutionLog oRecord : finalRecords) {
//                        if (oRecord.getId().equals(id)) {
//                            finalReturnRecords.add(oRecord);
//                        }
//                    }
//                }
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalReturnRecords, null);
//            }
//            else {// not all success
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " could not path all record ";
//                String message = "部分或全部更新失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "03PAT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//            }
//
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},",this.getClass().getName(),ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "05SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//
//    // =====Patch Method End=====
//
//    // =====Put Method Start=====
//
//    @PUT
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "替换或添加ExecutionLog", response = MeowlomoResponse.class, httpMethod = "PUT")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, ExecutionLog[] records) {
//        logger.info("received put executionLog by primary id call");
//        try {
//            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " put body is empty";
//                String message = "替换内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "01PUT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else if (records.length == 0) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, null, null);
//            }
//
//            // validation
//            BeanValidator.BeanValidation(records, ERROR_TYPE);
//
//            List<Long> targetIds = new ArrayList<Long>();
//            // loop and check each record
//            for (int i = 0; i < records.length; i++) {
//                ExecutionLog record = records[i];
//                if (record.getId() != null && record.getId() > 0) {
//                    targetIds.add(record.getId());
//                }
//            }
//            // check all have id
//            List<Long> errorIndex = new ArrayList<Long>();
//            if (records.length != targetIds.size()) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "部分替换请求不含ID。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "02PUT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else {
//                // start the update
//                // update one by one
//                for (int i = 0; i < records.length; i++) {
//                    ExecutionLog record = records[i];
//                    int updateResult = executionLogService.updateByPrimaryKey(record, queryParams.getFirst("mode"));
//                    if (updateResult != 1) {
//                        errorIndex.add(record.getId());
//                    }
//                }
//            }
//            // check all update sucess
//            if (errorIndex.isEmpty()) {
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
//                }
//                catch (Exception e) {
//
//                }
//                ExecutionLogExample example = new ExecutionLogExample();
//                example.or().andIdIn(targetIds);
//                List<ExecutionLog> finalRecords = executionLogService.selectByExample(example,
//                        queryParams.getFirst("mode"));
//                // sort return result
//                List<ExecutionLog> finalReturnRecords = new ArrayList<ExecutionLog>();
//                for (Long id : targetIds) {
//                    for (ExecutionLog oRecord : finalRecords) {
//                        if (oRecord.getId().equals(id)) {
//                            finalReturnRecords.add(oRecord);
//                        }
//                    }
//                }
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalReturnRecords, null);
//            }
//            else {// not all success
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " could not path all record ";
//                String message = "部分或全部替换失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "03PUT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomForbiddenException(null, message, trace, code, exuuid);
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},",this.getClass().getName(),ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "06SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//    // =====Put Method End=====

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
    @ApiOperation(value = "添加ExecutionLog", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "executionLog UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, ExecutionLog[] records) {
        logger.info("received post executionLog call ");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
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
                ExecutionLog record = records[i];
                record.setId(null);
                long insertResult = executionLogService.insertSelective(record, queryParams.getFirst("mode"));
                if (insertResult != 1) {
                    errorIndex.add(record.getId());
                }
                else {
                    ids.add(record.getId());
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
                ExecutionLogExample example = new ExecutionLogExample();
                example.or().andIdIn(ids);
                List<ExecutionLog> finalRecords = executionLogService.selectByExample(example,
                        queryParams.getFirst("mode"));
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

    // ===== executionLog instructionResult link start =====

    /**
     * Gets the test case by executionLog primary key.
     *
     * @param executionLogId
     *            the executionLog id
     * @return the test case by executionLog primary key
     * @throws Exception
     *             the exception
     */
    @GET
    @Path("/{executionLogId}/instructionResults")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取ExecutionLog关联的InstructionResult", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse getInstructionResultByExecutionLogPrimaryKey(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("executionLogId") Long executionLogId) throws Exception {
        logger.info("received get instructionResults by executionLog id = " + executionLogId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // get the record first
            ExecutionLog checkRecord = executionLogService.selectByPrimaryKey(executionLogId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + executionLogId + "的ExecutionLog不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            InstructionResultExample instructionResultExample = new InstructionResultExample();
            instructionResultExample.or().andIdEqualTo(checkRecord.getInstructionResultId());
            instructionResultExample.setOrderByClause(" id ");
            List<InstructionResult> finalRecords = instructionResultService.selectByExample(instructionResultExample,
                    queryParams.getFirst("mode"));
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", finalRecords.size());
            return new MeowlomoResponse(null, finalRecords, null);
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
    // ===== executionLog instructionResult link end =====

    // ===== executionLog run link start =====

    /**
     * Gets the test case by executionLog primary key.
     *
     * @param executionLogId
     *            the executionLog id
     * @return the test case by executionLog primary key
     * @throws Exception
     *             the exception
     */
    @GET
    @Path("/{executionLogId}/runs")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取ExecutionLog关联的Run", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse getRunByExecutionLogPrimaryKey(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("executionLogId") Long executionLogId)
            throws Exception {
        logger.info("received get runs by executionLog id = " + executionLogId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // get the record first
            ExecutionLog checkRecord = executionLogService.selectByPrimaryKey(executionLogId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + executionLogId + "的ExecutionLog不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            RunExample example = new RunExample();
            example.or().andIdEqualTo(checkRecord.getRunId());
            List<Run> finalRecords = runService.selectByExample(example);
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", finalRecords.size());
            return new MeowlomoResponse(null, finalRecords, null);
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
    // ===== executionLog run link end =====
}
