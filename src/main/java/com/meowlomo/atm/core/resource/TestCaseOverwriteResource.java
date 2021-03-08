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
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;
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
import com.meowlomo.atm.core.service.base.InstructionOverwriteService;
import com.meowlomo.atm.core.service.base.InstructionService;
import com.meowlomo.atm.core.service.base.TestCaseOverwriteService;
import com.meowlomo.atm.core.service.referrence.InstructionOverwriteReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseOverwriteReferenceService;
import com.meowlomo.atm.core.service.util.InstructionOverwriteUtilService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/testCaseOverwrites")
@Api(value = "testCaseOverwrite resources", produces = "application/json")
public class TestCaseOverwriteResource {

    private final Logger logger = LoggerFactory.getLogger(TestCaseOverwriteResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "TCOR";

    @Autowired
    private TestCaseOverwriteService testCaseOverwriteService;

    @Autowired
    private TestCaseOverwriteReferenceService testCaseOverwriteReferenceService;

    @Autowired
    private InstructionService instructionService;

    @Autowired
    private InstructionOverwriteService instructionOverwriteService;

    @Autowired
    private InstructionOverwriteReferenceService instructionOverwriteReferenceService;

    @Autowired
    private InstructionOverwriteUtilService instructionOverwriteUtilService;

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
    @ApiOperation(value = "读取TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "GET")
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
        logger.info("received testCaseOverwrite select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TestCaseOverwriteExample example = new TestCaseOverwriteExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseOverwriteService.countByExample(example));
                List<TestCaseOverwrite> records = testCaseOverwriteService.selectByExampleWithRowbounds(example,
                        rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                TestCaseOverwriteExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new TestCaseOverwriteExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseOverwriteExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseOverwriteExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseOverwriteService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                TestCaseOverwriteExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new TestCaseOverwriteExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseOverwriteExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseOverwriteExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseOverwriteService.countByExample(example));
                List<TestCaseOverwrite> records = null;
                if (queryParams.containsKey("ref")) {
                    records = testCaseOverwriteReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = testCaseOverwriteService.selectByExampleWithRowbounds(example, rowBounds);
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
    @ApiOperation(value = "读取单个TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "testCaseOverwrite id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long id) {
        logger.info("received testCaseOverwrite select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            TestCaseOverwrite selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = testCaseOverwriteReferenceService.selectByPrimaryKey(id);
            }
            else {
                selectRecord = testCaseOverwriteService.selectByPrimaryKey(id);
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
    @ApiOperation(value = "删除单个TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "testCaseOverwrite id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") long id) {
        logger.info("received testCaseOverwrite delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            TestCaseOverwrite record = testCaseOverwriteService.selectByPrimaryKey(id);
            int deleteResult = testCaseOverwriteService.logicalDeleteByPrimaryKey(id);
            // delete relation instructionOverwrites
            InstructionOverwriteExample instructionOverwriteExample = new InstructionOverwriteExample();
            instructionOverwriteExample.createCriteria().andTestCaseOverwriteIdEqualTo(id);
            instructionOverwriteService.deleteByExample(instructionOverwriteExample);
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
    @ApiOperation(value = "批量删除TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "DELETE")
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
        logger.info("received testCaseOverwrite delete call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TestCaseOverwriteExample example = new TestCaseOverwriteExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<TestCaseOverwrite> records = testCaseOverwriteService.selectByExample(example);
                metadata.put("count", testCaseOverwriteService.countByExample(example));
                int deleteResult = testCaseOverwriteService.logicalDeleteByExample(example);
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
                com.meowlomo.atm.core.model.TestCaseOverwriteExample.Criteria criteria = new TestCaseOverwriteExample()
                        .createCriteria();
                criteria.andIdIsNotNull().andDeletedEqualTo(false);
                TestCaseOverwriteExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseOverwriteExample.class);
                // count
                List<TestCaseOverwrite> countRecords = testCaseOverwriteService.selectByExample(example);
                // mark deleted
                int deleteResult = testCaseOverwriteService.logicalDeleteByExample(example);
                // get back records
                List<Long> deletedIds = new ArrayList<Long>();
                for (TestCaseOverwrite target : countRecords) {
                    deletedIds.add(target.getId());
                }
                TestCaseOverwriteExample deletedExample = new TestCaseOverwriteExample();
                deletedExample.or().andIdIsNotNull().andDeletedEqualTo(true).andIdIn(deletedIds);
                List<TestCaseOverwrite> records = testCaseOverwriteService.selectByExample(deletedExample);
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
    @ApiOperation(value = "更新TestCaseOverwrite", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCaseOverwrite[] records) throws Exception {
        logger.info("received patch testCaseOverwrite by id call " + uriInfo.getPath());
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
                TestCaseOverwrite record = records[i];
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
                    TestCaseOverwrite record = records[i];
                    int updateResult = testCaseOverwriteService.updateByPrimaryKeySelective(records[i]);
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
                TestCaseOverwriteExample example = new TestCaseOverwriteExample();
                example.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<TestCaseOverwrite> finalRecords = testCaseOverwriteService.selectByExample(example);
                // sort return result
                List<TestCaseOverwrite> finalReturnRecords = new ArrayList<TestCaseOverwrite>();
                for (Long id : targetIds) {
                    for (TestCaseOverwrite oRecord : finalRecords) {
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
    @ApiOperation(value = "替换或添加TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCaseOverwrite[] records) {
        logger.info("received put testCaseOverwrite by primary id call");
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
            List<TestCaseOverwrite> finalRecords = new ArrayList<TestCaseOverwrite>();
            for (int i = 0; i < records.length; i++) {
                TestCaseOverwrite record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = testCaseOverwriteService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(record);
                    }
                }
                else {
                    // do update
                    int updateResult = testCaseOverwriteService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(testCaseOverwriteService.selectByPrimaryKey(record.getId()));
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
    @ApiOperation(value = "添加TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCaseOverwrite[] records) {
        logger.info("received post testCaseOverwrite call ");
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
            List<TestCaseOverwrite> finalRecords = new ArrayList<TestCaseOverwrite>();
            for (int i = 0; i < records.length; i++) {
                TestCaseOverwrite record = records[i];
                record.setId(null);
                long insertResult = testCaseOverwriteService.insertSelective(record);
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

    // ===== testCaseOverwrite instructionOverwrite link start =====

    @GET
    @Path("/{testCaseOverwriteId}/instructionOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCaseOverwrite的InstructionOverwrite", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseOverwriteId + \"的TestCaseOverwrite不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseOverwriteId", value = "ATM ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "InstructionOverwrite 名字", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "InstructionOverwrite 类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "InstructionOverwrite 创建起始时间 [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "InstructionOverwrite 创建终结时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getInstructionOverwriteByTestCaseOverwritePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseOverwriteId") Long testCaseOverwriteId)
            throws Exception {
        logger.info("received get instructionOverwrites by testCaseOverwrite id = " + testCaseOverwriteId);
        try {
            // get the record first
            TestCaseOverwrite checkRecord = testCaseOverwriteService.selectByPrimaryKey(testCaseOverwriteId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseOverwriteId + "的TestCaseOverwrite不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                InstructionOverwriteExample recordExample = new InstructionOverwriteExample();
                recordExample.or().andTestCaseOverwriteIdEqualTo(testCaseOverwriteId);
                List<InstructionOverwrite> records = instructionOverwriteService.selectByExample(recordExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {// with query parameters
                  // get instruction ids
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                InstructionExample instructionExample = new InstructionExample();
                instructionExample.createCriteria().andDeletedEqualTo(false)
                        .andTestCaseIdEqualTo(checkRecord.getTestCaseId());
                instructionExample.setOrderByClause(" order_index asc, updated_at desc");
                List<Instruction> instructions = instructionService.selectByExampleWithRowbounds(instructionExample,
                        rowBounds);
                List<Long> instructionIds = new ArrayList<Long>();

                for (Instruction instruction : instructions) {
                    instructionIds.add(instruction.getId());
                }
                if (instructionIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                // not empty
                InstructionOverwriteExample.Criteria criteria = null;
                criteria = new InstructionOverwriteExample().createCriteria();
                criteria.andInstructionIdIn(instructionIds).andTestCaseIdEqualTo(checkRecord.getTestCaseId())
                        .andTestCaseOverwriteIdEqualTo(testCaseOverwriteId);
                InstructionOverwriteExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionOverwriteExample.class);
                List<InstructionOverwrite> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = instructionOverwriteReferenceService.selectByExampleWithRowbounds(example,
                            rowBounds);
                }
                else {
                    finalRecords = instructionOverwriteService.selectByExampleWithRowbounds(example, rowBounds);
                }
                // reorder the instruction overwrite
                List<InstructionOverwrite> returnRecords = new ArrayList<InstructionOverwrite>();
                for (Instruction instruction : instructions) {
                    boolean found = false;
                    for (InstructionOverwrite insOver : finalRecords) {
                        if (instruction.getId().equals(insOver.getInstructionId())) {
                            found = true;
                            returnRecords.add(insOver);
                        }
                    }
                    if (!found) {
                        // need to insert a new instruction overwrite
                        InstructionOverwrite insOver = instructionOverwriteUtilService
                                .generateInstructionOverwriteFromInstruction(instruction);
                        returnRecords.add(insOver);
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionOverwriteService.countByExample(example));
                return new MeowlomoResponse(metadata, returnRecords, null);
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
    @Path("/{testCaseOverwriteId}/instructionOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个InstructionOverwrite到TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseOverwriteId + \"的TestCaseOverwrite不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加InstructionOverwrite到TestCaseOverwrite操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseOverwriteId", value = "ATM ID", required = true, dataType = "long", paramType = "path") })
    public MeowlomoResponse insertInstructionOverwriteAndLinkToTestCaseOverwrite(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseOverwriteId") Long testCaseOverwriteId,
            InstructionOverwrite[] records) throws Exception {
        logger.info("received post instructionOverwrite by testCaseOverwrite id=" + testCaseOverwriteId);
        try {
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
            TestCaseOverwrite checkRecord = testCaseOverwriteService.selectByPrimaryKey(testCaseOverwriteId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseOverwriteId + "的TestCaseOverwrite不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add the id to the record
            List<InstructionOverwrite> entries = new ArrayList<InstructionOverwrite>();
            for (InstructionOverwrite entry : records) {
                entry.setTestCaseOverwriteId(testCaseOverwriteId);
                entries.add(entry);
            }
            List<Long> insertedResult = instructionOverwriteService.insertRecordsSelective(entries);
            if (insertedResult.size() == records.length) {
                InstructionOverwriteExample example = new InstructionOverwriteExample();
                example.or().andIdIn(insertedResult);
                List<InstructionOverwrite> finalRecords = instructionOverwriteService.selectByExample(example);
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert";
                String code = ERROR_TYPE + "02POS";
                String message = "添加InstructionOverwrite到TestCaseOverwrite操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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

    @DELETE
    @LogUserActivity
    @Path("/{testCaseOverwriteId}/instructionOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到TestCaseOverwrite的InstructionOverwrite", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "删除TestCaseOverwrite关联的InstructionOverwrite操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseOverwriteId + \"的TestCaseOverwrite不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseOverwriteId", value = "ATM ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "InstructionOverwrite IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse deleteInstructionOverwritesFromTestCaseOverwrite(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseOverwriteId") Long testCaseOverwriteId)
            throws Exception {
        logger.info("received delete instructionOverwrites by testCaseOverwrite id=" + testCaseOverwriteId);
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
            TestCaseOverwrite checkRecord = testCaseOverwriteService.selectByPrimaryKey(testCaseOverwriteId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseOverwriteId + "的TestCaseOverwrite不存在。问题唯一码[" + exuuid + "]";
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
            InstructionOverwriteExample recordExample = new InstructionOverwriteExample();
            recordExample.or().andIdIn(idsList);
            List<InstructionOverwrite> finalRecords = instructionOverwriteService.selectByExample(recordExample);
            int deleteResult = instructionOverwriteService.deleteByExample(recordExample);
            if (deleteResult == finalRecords.size()) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", deleteResult);
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
                String trace = "exception UUID=" + exuuid + " couldn't execute delete.";
                String code = ERROR_TYPE + "03DEL";
                String message = "删除TestCaseOverwrite关联的InstructionOverwrite操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
            String code = ERROR_TYPE + "11SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @PATCH
    @LogUserActivity
    @Path("/{testCaseOverwriteId}/instructionOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新关联到TestCaseOverwrite的InstructionOverwrite", response = MeowlomoResponse.class, httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部更新失败，失败序列=>\" + errorIndex.toString() + \" 无关联序列 =>\" + noLinkedIndex.toString()+ \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更新内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseOverwriteId + \"的TestCaseOverwrite不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分更新请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseOverwriteId", value = "ATM ID", required = true, dataType = "long", paramType = "path") })
    public MeowlomoResponse updateInstructionOverwritesFromTestCaseOverwrite(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseOverwriteId") Long testCaseOverwriteId,
            InstructionOverwrite[] records) throws Exception {
        logger.info("received patch instructionOverwrites by testCaseOverwrite id = " + testCaseOverwriteId);
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

            // get the record first
            TestCaseOverwrite checkRecord = testCaseOverwriteService.selectByPrimaryKey(testCaseOverwriteId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseOverwriteId + "的TestCaseOverwrite不存在。问题唯一码[" + exuuid + "]";
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
                InstructionOverwrite record = records[i];
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
                    InstructionOverwrite record = records[i];
                    InstructionOverwriteExample example = new InstructionOverwriteExample();
                    example.or().andTestCaseOverwriteIdEqualTo(testCaseOverwriteId)
                            .andIdEqualTo(record.getId());
                    if (instructionOverwriteService.countByExample(example) == 1) {
                        int updateResult = instructionOverwriteService.updateByPrimaryKeySelective(record);
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
                InstructionOverwriteExample example = new InstructionOverwriteExample();
                example.or().andIdIn(targetIds);
                List<InstructionOverwrite> finalRecords = instructionOverwriteService.selectByExample(example);
                // sort return result
                List<InstructionOverwrite> finalReturnRecords = new ArrayList<InstructionOverwrite>();
                for (Long id : targetIds) {
                    for (InstructionOverwrite oRecord : finalRecords) {
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
    // =====testCaseOverwrite instructionOverwrite link end =====
}