package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.annotation.LogUserActivity;
import com.meowlomo.atm.core.model.ExecutionLog;
import com.meowlomo.atm.core.model.ExecutionLogExample;
import com.meowlomo.atm.core.model.File;
import com.meowlomo.atm.core.model.FileExample;
import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.model.StepLog;
import com.meowlomo.atm.core.model.StepLogExample;
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
import com.meowlomo.atm.core.service.base.FileService;
import com.meowlomo.atm.core.service.base.InstructionResultService;
import com.meowlomo.atm.core.service.base.RunService;
import com.meowlomo.atm.core.service.base.StepLogService;
import com.meowlomo.atm.core.service.referrence.InstructionResultReferenceService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/instructionResults")
@Api(value = "instructionResult resources", produces = "application/json")
public class InstructionResultResource {

    private final Logger logger = LoggerFactory.getLogger(InstructionResultResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    // jackson object writer
    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private static final String ERROR_TYPE = "INSR";

    @Autowired
    private InstructionResultService instructionResultService;

    @Autowired
    private InstructionResultReferenceService instructionResultReferenceService;

    @Autowired
    private RunService runService;

    @Autowired
    private StepLogService stepLogService;

    @Autowired
    private ExecutionLogService executionLogService;

    @Autowired
    private FileService fileService;

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
    @ApiOperation(value = "读取InstructionResult", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "instructionResult status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse selectByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest)
            throws Exception {
        logger.info("received instructionResult select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                InstructionResultExample example = new InstructionResultExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionResultService.countByExample(example, queryParams.getFirst("mode")));
                List<InstructionResult> records = instructionResultService.selectByExampleWithRowbounds(example, rowBounds, queryParams.getFirst("mode"));
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                InstructionResultExample example = this.searchExampleGenerator.generateExample(uriInfo, null,
                        InstructionResultExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count",
                        instructionResultReferenceService.countByExample(example, queryParams.getFirst("mode")));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                InstructionResultExample example = this.searchExampleGenerator.generateExample(uriInfo, null,
                        InstructionResultExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionResultService.countByExample(example, queryParams.getFirst("mode")));
                List<InstructionResult> records = null;
                if (queryParams.containsKey("ref")) {
                    records = instructionResultReferenceService.selectByExampleWithRowbounds(example, rowBounds,
                            queryParams.getFirst("mode"));
                }
                else {
                    records = instructionResultService.selectByExampleWithRowbounds(example, rowBounds,
                            queryParams.getFirst("mode"));
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
    @ApiOperation(value = "读取单个InstructionResult", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instructionResult id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long id) {
        logger.info("received instructionResult select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            InstructionResult selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = instructionResultReferenceService.selectByPrimaryKey(id, queryParams.getFirst("mode"));
            }
            else {
                selectRecord = instructionResultService.selectByPrimaryKey(id, queryParams.getFirst("mode"));
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
            String code = ERROR_TYPE + "02SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====Get Method End=====

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
    @ApiOperation(value = "更新InstructionResult", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "instructionResult status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            InstructionResult[] records) throws Exception {
        logger.info("received patch instructionResult by id call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
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
                InstructionResult record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
                else {
                    logger.error("record has no id {}", record.toString());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            if (records.length != targetIds.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body has not id entry";
                String message = "部分更新请求不含ID。问题唯一码[" + exuuid + "], ";
                String code = ERROR_TYPE + "02PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else {
                // start the update
                // update one by one
                for (int i = 0; i < records.length; i++) {
                    InstructionResult record = records[i];
                    int updateResult = instructionResultService.updateByPrimaryKeySelective(records[i],
                            queryParams.getFirst("mode"));
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
                InstructionResultExample example = new InstructionResultExample();
                example.or().andIdIn(targetIds);
                example.setOrderByClause(" id ");
                List<InstructionResult> finalRecords = instructionResultService.selectByExample(example,
                        queryParams.getFirst("mode"));
                // sort return result
                List<InstructionResult> finalReturnRecords = new ArrayList<InstructionResult>();
                for (Long id : targetIds) {
                    for (InstructionResult oRecord : finalRecords) {
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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

//    // =====Put Method Start=====
//
//    @PUT
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "替换或添加InstructionResult", response = MeowlomoResponse.class, httpMethod = "PUT")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
//            InstructionResult[] records) {
//        logger.info("received put instructionResult by primary id call");
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
//                InstructionResult record = records[i];
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
//                    InstructionResult record = records[i];
//                    int updateResult = instructionResultService.updateByPrimaryKey(record,
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
//                InstructionResultExample example = new InstructionResultExample();
//                example.or().andIdIn(targetIds);
//                example.setOrderByClause(" id ");
//                List<InstructionResult> finalRecords = instructionResultService.selectByExample(example,
//                        queryParams.getFirst("mode"));
//                // sort return result
//                List<InstructionResult> finalReturnRecords = new ArrayList<InstructionResult>();
//                for (Long id : targetIds) {
//                    for (InstructionResult oRecord : finalRecords) {
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
//            logger.error("Class:{},", this.getClass().getName(), ex);
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
//
//    // =====Post Method Start=====
//    /**
//     * Insert.
//     *
//     * @param record
//     *            the record
//     * @return the meowlomo response
//     */
//    @POST
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "添加InstructionResult", response = MeowlomoResponse.class, httpMethod = "POST")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    @ApiImplicitParam(name = "uuid", value = "instructionResult UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
//    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
//            InstructionResult[] records) {
//        logger.info("received post instructionResult call ");
//        try {
//            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "01POS";
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
//            List<Long> ids = new ArrayList<Long>();
//            // check all have id
//            List<Long> errorIndex = new ArrayList<Long>();
//            // start the insert
//            // insert one by one
//            for (int i = 0; i < records.length; i++) {
//                InstructionResult record = records[i];
//                record.setId(null);
//                long insertResult = instructionResultService.insertSelective(record, queryParams.getFirst("mode"));
//                if (insertResult != 1) {
//                    errorIndex.add(record.getId());
//                }
//                else {
//                    ids.add(record.getId());
//                }
//            }
//            // check all insert success
//            if (errorIndex.isEmpty()) {
//                InstructionResultExample example = new InstructionResultExample();
//                example.or().andIdIn(ids);
//                example.setOrderByClause(" id ");
//                List<InstructionResult> finalRecords = instructionResultService.selectByExample(example,
//                        queryParams.getFirst("mode"));
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalRecords, null);
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
//                String message = "部分或全部添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "02POS";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},", this.getClass().getName(), ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "08SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//    // ===== Post Method End=====

    // ===== instructionResult file link start =====

    @GET
    @Path("/{instructionResultId}/files")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于InstructionResult的File", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "File 名字", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "File 类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "File 创建起始时间 [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "File 创建终结时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getFileByInstructionResultPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId)
            throws Exception {
        logger.info("received get files by instructionResult id = " + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            if (queryParams.isEmpty()) {// no example
                FileExample recordExample = new FileExample();
                recordExample.or().andInstructionResultIdEqualTo(instructionResultId);
                List<File> records = fileService.selectByExample(recordExample, checkRecord.getRunType());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {// with query parameters
                FileExample recordExample = new FileExample();
                recordExample.or().andInstructionResultIdEqualTo(instructionResultId);
                List<File> records = fileService.selectByExample(recordExample, checkRecord.getRunType());
                // empty just return
                if (records.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }

                // not empty
                FileExample.Criteria criteria = null;
                // get the ids with the
                List<Long> targetIds = new ArrayList<Long>();
                for (File record : records) {
                    targetIds.add(record.getId());
                }
                criteria = new FileExample().createCriteria();
                criteria.andIdIn(targetIds);
                FileExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria, FileExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<File> finalRecords = fileService.selectByExampleWithRowbounds(example, rowBounds,
                        checkRecord.getRunType());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", fileService.countByExample(example, queryParams.getFirst("mode")));
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
            String code = ERROR_TYPE + "08SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @POST
    @LogUserActivity
    @Path("/{instructionResultId}/files")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个File到InstructionResult", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加File到InstructionResult操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path") })
    public MeowlomoResponse insertFileAndLinkToInstructionResult(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId,
            File[] records) throws Exception {
        logger.info("received post file by instructionResult id=" + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add the id to the record
            List<File> entries = new ArrayList<File>();
            for (File entry : records) {
                entry.setInstructionResultId(instructionResultId);
                entries.add(entry);
            }
            List<Long> insertedResult = fileService.insertRecordsSelective(entries, checkRecord.getRunType());
            if (insertedResult.size() == records.length) {
                FileExample example = new FileExample();
                example.or().andIdIn(insertedResult);
                List<File> finalRecords = fileService.selectByExample(example, checkRecord.getRunType());
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert. request body="
                        + objectWriter.writeValueAsString(records);
                String code = ERROR_TYPE + "02POS";
                String message = "添加File到InstructionResult操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
            String code = ERROR_TYPE + "09SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @PATCH
    @LogUserActivity
    @Path("/{instructionResultId}/files")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新关联到InstructionResult的File", response = MeowlomoResponse.class, httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部更新失败，失败序列=>\" + errorIndex.toString() + \" 无关联序列 =>\" + noLinkedIndex.toString()+ \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更新内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分更新请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "File IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse updateFilesFromInstructionResult(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId,
            File[] records) throws Exception {
        logger.info("received patch files by instructionResult id = " + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02DEL";
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
                File record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            List<Long> noLinkedIndex = new ArrayList<Long>();
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
                    File record = records[i];
                    FileExample example = new FileExample();
                    example.or().andInstructionResultIdEqualTo(instructionResultId).andIdEqualTo(record.getId());
                    if (fileService.countByExample(example, queryParams.getFirst("mode")) == 1) {
                        int updateResult = fileService.updateByPrimaryKeySelective(record, checkRecord.getRunType());
                        if (updateResult != 1) {
                            errorIndex.add(record.getId());
                        }
                    }
                    else {
                        noLinkedIndex.add(record.getId());
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty() && noLinkedIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                FileExample example = new FileExample();
                example.or().andIdIn(targetIds);
                List<File> finalRecords = fileService.selectByExample(example, checkRecord.getRunType());
                // sort return result
                List<File> finalReturnRecords = new ArrayList<File>();
                for (Long id : targetIds) {
                    for (File oRecord : finalRecords) {
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
                String message = "部分或全部更新失败，失败序列=>" + errorIndex.toString() + " 无关联序列 =>" + noLinkedIndex.toString()
                        + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PAT";
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
            String code = ERROR_TYPE + "12SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====instructionResult file link end =====

    // ===== instructionResult stepLog link start =====

    @GET
    @Path("/{instructionResultId}/stepLogs")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于InstructionResult的StepLog", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "StepLog 名字", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "StepLog 类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "StepLog 创建起始时间 [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "StepLog 创建终结时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getStepLogByInstructionResultPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId)
            throws Exception {
        logger.info("received get stepLogs by instructionResult id = " + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            if (queryParams.isEmpty()) {// no example
                StepLogExample recordExample = new StepLogExample();
                recordExample.or().andInstructionResultIdEqualTo(instructionResultId);
                List<StepLog> records = stepLogService.selectByExample(recordExample, checkRecord.getRunType());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {// with query parameters
                StepLogExample recordExample = new StepLogExample();
                recordExample.or().andInstructionResultIdEqualTo(instructionResultId);
                List<StepLog> records = stepLogService.selectByExample(recordExample, checkRecord.getRunType());
                // empty just return
                if (records.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }

                // not empty
                StepLogExample.Criteria criteria = null;
                // get the ids with the
                List<Long> targetIds = new ArrayList<Long>();
                for (StepLog record : records) {
                    targetIds.add(record.getId());
                }
                criteria = new StepLogExample().createCriteria();
                criteria.andIdIn(targetIds);
                StepLogExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        StepLogExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<StepLog> finalRecords = stepLogService.selectByExampleWithRowbounds(example, rowBounds,
                        checkRecord.getRunType());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", stepLogService.countByExample(example, checkRecord.getRunType()));
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
            String code = ERROR_TYPE + "08SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @POST
    @LogUserActivity
    @Path("/{instructionResultId}/stepLogs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个StepLog到InstructionResult", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加StepLog到InstructionResult操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path") })
    public MeowlomoResponse insertStepLogAndLinkToInstructionResult(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId,
            StepLog[] records) throws Exception {
        logger.info("received post stepLog by instructionResult id=" + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add the id to the record
            List<StepLog> entries = new ArrayList<StepLog>();
            for (StepLog entry : records) {
                entry.setInstructionResultId(instructionResultId);
                entries.add(entry);
            }
            List<Long> insertedResult = stepLogService.insertRecordsSelective(entries, checkRecord.getRunType());
            if (insertedResult.size() == records.length) {
                StepLogExample example = new StepLogExample();
                example.or().andIdIn(insertedResult);
                List<StepLog> finalRecords = stepLogService.selectByExample(example, checkRecord.getRunType());
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert. request body="
                        + objectWriter.writeValueAsString(records);
                String code = ERROR_TYPE + "02POS";
                String message = "添加StepLog到InstructionResult操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
            String code = ERROR_TYPE + "09SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @PATCH
    @LogUserActivity
    @Path("/{instructionResultId}/stepLogs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新关联到InstructionResult的StepLog", response = MeowlomoResponse.class, httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部更新失败，失败序列=>\" + errorIndex.toString() + \" 无关联序列 =>\" + noLinkedIndex.toString()+ \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更新内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分更新请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "StepLog IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse updateStepLogsFromInstructionResult(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId,
            StepLog[] records) throws Exception {
        logger.info("received patch stepLogs by instructionResult id = " + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02DEL";
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
                StepLog record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            List<Long> noLinkedIndex = new ArrayList<Long>();
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
                    StepLog record = records[i];
                    StepLogExample example = new StepLogExample();
                    example.or().andInstructionResultIdEqualTo(instructionResultId).andIdEqualTo(record.getId());
                    if (stepLogService.countByExample(example, queryParams.getFirst("mode")) == 1) {
                        int updateResult = stepLogService.updateByPrimaryKeySelective(record, checkRecord.getRunType());
                        if (updateResult != 1) {
                            errorIndex.add(record.getId());
                        }
                    }
                    else {
                        noLinkedIndex.add(record.getId());
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty() && noLinkedIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                StepLogExample example = new StepLogExample();
                example.or().andIdIn(targetIds);
                List<StepLog> finalRecords = stepLogService.selectByExample(example, checkRecord.getRunType());
                // sort return result
                List<StepLog> finalReturnRecords = new ArrayList<StepLog>();
                for (Long id : targetIds) {
                    for (StepLog oRecord : finalRecords) {
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
                String message = "部分或全部更新失败，失败序列=>" + errorIndex.toString() + " 无关联序列 =>" + noLinkedIndex.toString()
                        + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PAT";
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
            String code = ERROR_TYPE + "12SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====instructionResult stepLog link end =====

    // ===== instructionResult executionLog link start =====

    @GET
    @Path("/{instructionResultId}/executionLogs")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于InstructionResult的ExecutionLog", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "ExecutionLog 名字", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "ExecutionLog 类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "ExecutionLog 创建起始时间 [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "ExecutionLog 创建终结时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getExecutionLogByInstructionResultPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId)
            throws Exception {
        logger.info("received get executionLogs by instructionResult id = " + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            if (queryParams.isEmpty()) {// no example
                ExecutionLogExample recordExample = new ExecutionLogExample();
                recordExample.or().andInstructionResultIdEqualTo(instructionResultId);
                List<ExecutionLog> records = executionLogService.selectByExample(recordExample,
                        checkRecord.getRunType());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {// with query parameters
                ExecutionLogExample recordExample = new ExecutionLogExample();
                recordExample.or().andInstructionResultIdEqualTo(instructionResultId);
                List<ExecutionLog> records = executionLogService.selectByExample(recordExample,
                        checkRecord.getRunType());
                // empty just return
                if (records.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }

                // not empty
                ExecutionLogExample.Criteria criteria = null;
                // get the ids with the
                List<Long> targetIds = new ArrayList<Long>();
                for (ExecutionLog record : records) {
                    targetIds.add(record.getId());
                }
                criteria = new ExecutionLogExample().createCriteria();
                criteria.andIdIn(targetIds);
                ExecutionLogExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        ExecutionLogExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<ExecutionLog> finalRecords = executionLogService.selectByExampleWithRowbounds(example, rowBounds,
                        checkRecord.getRunType());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", executionLogService.countByExample(example, checkRecord.getRunType()));
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
            String code = ERROR_TYPE + "08SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @POST
    @LogUserActivity
    @Path("/{instructionResultId}/executionLogs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个ExecutionLog到InstructionResult", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加ExecutionLog到InstructionResult操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path") })
    public MeowlomoResponse insertExecutionLogAndLinkToInstructionResult(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId,
            ExecutionLog[] records) throws Exception {
        logger.info("received post executionLog by instructionResult id=" + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add the id to the record
            List<ExecutionLog> entries = new ArrayList<ExecutionLog>();
            for (ExecutionLog entry : records) {
                entry.setInstructionResultId(instructionResultId);
                entries.add(entry);
            }
            List<Long> insertedResult = executionLogService.insertRecordsSelective(entries, checkRecord.getRunType());
            if (insertedResult.size() == records.length) {
                ExecutionLogExample example = new ExecutionLogExample();
                example.or().andIdIn(insertedResult);
                List<ExecutionLog> finalRecords = executionLogService.selectByExample(example,
                        checkRecord.getRunType());
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert. request body="
                        + objectWriter.writeValueAsString(records);
                String code = ERROR_TYPE + "02POS";
                String message = "添加ExecutionLog到InstructionResult操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
            String code = ERROR_TYPE + "09SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @PATCH
    @LogUserActivity
    @Path("/{instructionResultId}/executionLogs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新关联到InstructionResult的ExecutionLog", response = MeowlomoResponse.class, httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部更新失败，失败序列=>\" + errorIndex.toString() + \" 无关联序列 =>\" + noLinkedIndex.toString()+ \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更新内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + instructionResultId + \"的InstructionResult不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分更新请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instructionResultId", value = "InstructionResult ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "ExecutionLog IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse updateExecutionLogsFromInstructionResult(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId,
            ExecutionLog[] records) throws Exception {
        logger.info("received patch executionLogs by instructionResult id = " + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02DEL";
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
                ExecutionLog record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            List<Long> noLinkedIndex = new ArrayList<Long>();
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
                    ExecutionLog record = records[i];
                    ExecutionLogExample example = new ExecutionLogExample();
                    example.or().andInstructionResultIdEqualTo(instructionResultId).andIdEqualTo(record.getId());
                    if (executionLogService.countByExample(example, queryParams.getFirst("mode")) == 1) {
                        int updateResult = executionLogService.updateByPrimaryKeySelective(record,
                                checkRecord.getRunType());
                        if (updateResult != 1) {
                            errorIndex.add(record.getId());
                        }
                    }
                    else {
                        noLinkedIndex.add(record.getId());
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty() && noLinkedIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                ExecutionLogExample example = new ExecutionLogExample();
                example.or().andIdIn(targetIds);
                List<ExecutionLog> finalRecords = executionLogService.selectByExample(example,
                        checkRecord.getRunType());
                // sort return result
                List<ExecutionLog> finalReturnRecords = new ArrayList<ExecutionLog>();
                for (Long id : targetIds) {
                    for (ExecutionLog oRecord : finalRecords) {
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
                String message = "部分或全部更新失败，失败序列=>" + errorIndex.toString() + " 无关联序列 =>" + noLinkedIndex.toString()
                        + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PAT";
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
            String code = ERROR_TYPE + "12SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====instructionResult executionLog link end =====

    // ===== instructionResult run link start =====

    /**
     * Gets the test case by instructionResult primary key.
     *
     * @param instructionResultId
     *            the instructionResult id
     * @return the test case by instructionResult primary key
     * @throws Exception
     *             the exception
     */
    @GET
    @Path("/{instructionResultId}/runs")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取InstructionResult关联的Run", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse getRunByInstructionResultPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("instructionResultId") Long instructionResultId)
            throws Exception {
        logger.info("received get runs by instructionResult id = " + instructionResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // get the record first
            InstructionResult checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId,
                    queryParams.getFirst("mode"));
            if (checkRecord == null) {
                checkRecord = instructionResultService.selectByPrimaryKey(instructionResultId, "DEVELOPMENT");
            }
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + instructionResultId + "的InstructionResult不存在。问题唯一码[" + exuuid + "]";
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
    // ===== instructionResult run link end =====
}
