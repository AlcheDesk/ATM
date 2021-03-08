package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLinkExample;
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
import com.meowlomo.atm.core.service.base.TestCaseService;
import com.meowlomo.atm.core.service.base.TestCaseShareFolderService;
import com.meowlomo.atm.core.service.base.TestCaseShareFolderTestCaseLinkService;
import com.meowlomo.atm.core.service.filter.TestCaseShareFolderContentFilterService;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseShareFolderReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseShareFolderTestCaseLinkReferenceService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/testCaseShareFolders")
@Api(value = "testCaseShareFolder resources", produces = "application/json")
public class TestCaseShareFolderResource {

    private final Logger logger = LoggerFactory.getLogger(TestCaseShareFolderResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "TECF";

    @Autowired
    private TestCaseShareFolderService testCaseShareFolderService;

    @Autowired
    private TestCaseShareFolderReferenceService testCaseShareFolderReferenceService;

    @Autowired
    private TestCaseShareFolderTestCaseLinkService testCaseShareFolderTestCaseLinkService;

    @Autowired
    private TestCaseShareFolderTestCaseLinkReferenceService testCaseShareFolderTestCaseLinkReferenceService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestCaseReferenceService testCaseReferenceService;

    @Autowired
    private TestCaseShareFolderContentFilterService testCaseShareFolderContentFilterService;

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
    @ApiOperation(value = "读取TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "GET")
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
        logger.info("received testCaseShareFolder select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TestCaseShareFolderExample example = new TestCaseShareFolderExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseShareFolderService.countByExample(example));
                List<TestCaseShareFolder> records = testCaseShareFolderService.selectByExampleWithRowbounds(example,
                        rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                TestCaseShareFolderExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new TestCaseShareFolderExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseShareFolderExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseShareFolderExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseShareFolderService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                TestCaseShareFolderExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new TestCaseShareFolderExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseShareFolderExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseShareFolderExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseShareFolderService.countByExample(example));
                List<TestCaseShareFolder> records = null;
                if (queryParams.containsKey("ref")) {
                    records = testCaseShareFolderReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = testCaseShareFolderService.selectByExampleWithRowbounds(example, rowBounds);
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
    @ApiOperation(value = "读取单个TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "testCaseShareFolder id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long id) {
        logger.info("received testCaseShareFolder select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            TestCaseShareFolder selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = testCaseShareFolderReferenceService.selectByPrimaryKey(id);
            }
            else {
                selectRecord = testCaseShareFolderService.selectByPrimaryKey(id);
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
    @ApiOperation(value = "删除单个TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "testCaseShareFolder id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") long id) {
        logger.info("received testCaseShareFolder delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            TestCaseShareFolder record = testCaseShareFolderService.selectByPrimaryKey(id);
            int deleteResult = testCaseShareFolderService.logicalDeleteByPrimaryKey(id);
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
    @ApiOperation(value = "批量删除TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "DELETE")
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
        logger.info("received testCaseShareFolder delete call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TestCaseShareFolderExample example = new TestCaseShareFolderExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<TestCaseShareFolder> records = testCaseShareFolderService.selectByExample(example);
                metadata.put("count", testCaseShareFolderService.countByExample(example));
                int deleteResult = testCaseShareFolderService.logicalDeleteByExample(example);
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
                com.meowlomo.atm.core.model.TestCaseShareFolderExample.Criteria criteria = new TestCaseShareFolderExample()
                        .createCriteria();
                criteria.andIdIsNotNull().andDeletedEqualTo(false);
                TestCaseShareFolderExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseShareFolderExample.class);
                // count
                List<TestCaseShareFolder> countRecords = testCaseShareFolderService.selectByExample(example);
                // mark deleted
                int deleteResult = testCaseShareFolderService.logicalDeleteByExample(example);
                // get back records
                List<Long> deletedIds = new ArrayList<Long>();
                for (TestCaseShareFolder target : countRecords) {
                    deletedIds.add(target.getId());
                }
                TestCaseShareFolderExample deletedExample = new TestCaseShareFolderExample();
                deletedExample.or().andIdIsNotNull().andDeletedEqualTo(true).andIdIn(deletedIds);
                List<TestCaseShareFolder> records = testCaseShareFolderService.selectByExample(deletedExample);
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
    @ApiOperation(value = "更新TestCaseShareFolder", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCaseShareFolder[] records) throws Exception {
        logger.info("received patch testCaseShareFolder by id call " + uriInfo.getPath());
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
                TestCaseShareFolder record = records[i];
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
                    TestCaseShareFolder record = records[i];
                    int updateResult = testCaseShareFolderService.updateByPrimaryKeySelective(records[i]);
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
                TestCaseShareFolderExample example = new TestCaseShareFolderExample();
                example.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<TestCaseShareFolder> finalRecords = testCaseShareFolderService.selectByExample(example);
                // sort return result
                List<TestCaseShareFolder> finalReturnRecords = new ArrayList<TestCaseShareFolder>();
                for (Long id : targetIds) {
                    for (TestCaseShareFolder oRecord : finalRecords) {
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
    @ApiOperation(value = "替换或添加TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCaseShareFolder[] records) {
        logger.info("received put testCaseShareFolder by primary id call");
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
            List<TestCaseShareFolder> finalRecords = new ArrayList<TestCaseShareFolder>();
            for (int i = 0; i < records.length; i++) {
                TestCaseShareFolder record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = testCaseShareFolderService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(record);
                    }
                }
                else {
                    // do update
                    int updateResult = testCaseShareFolderService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(testCaseShareFolderService.selectByPrimaryKey(record.getId()));
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
    @ApiOperation(value = "添加TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCaseShareFolder[] records) {
        logger.info("received post testCaseShareFolder call ");
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
            List<TestCaseShareFolder> finalRecords = new ArrayList<TestCaseShareFolder>();
            for (int i = 0; i < records.length; i++) {
                TestCaseShareFolder record = records[i];
                record.setId(null);
                long insertResult = testCaseShareFolderService.insertSelective(record);
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

    // ===== testCaseShareFolder testCase link start =====

    @POST
    @LogUserActivity
    @Path("/{testCaseShareFolderId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个TestCase实体并链接到TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseShareFolderId + \"的TestCaseShareFolder不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加TestCase并连接到TestCaseShareFolder操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertTestCaseAndLinkToTestCaseShareFolder(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseShareFolderId") Long testCaseShareFolderId, TestCase[] records) throws Exception {
        logger.info("received post testCase by testCaseShareFolder id=" + testCaseShareFolderId);
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
            TestCaseShareFolder checkTestCaseShareFolderRecord = testCaseShareFolderService
                    .selectByPrimaryKey(testCaseShareFolderId);
            if (checkTestCaseShareFolderRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseShareFolderId + "的TestCaseShareFolder不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<Long> insertedResult = testCaseService.insertRecordsSelective(Arrays.asList(records));
            if (insertedResult.size() == records.length) {
                // add link
                for (Long id : insertedResult) {
                    TestCaseShareFolderTestCaseLink link = new TestCaseShareFolderTestCaseLink();
                    link.setTestCaseId(id);
                    link.setTestCaseShareFolderId(testCaseShareFolderId);
                    testCaseShareFolderTestCaseLinkService.insert(link);
                }
                TestCaseExample example = new TestCaseExample();
                example.or().andIdIn(insertedResult);
                List<TestCase> finalRecords = testCaseService.selectByExample(example);
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
                String message = "添加TestCase并连接到TestCaseShareFolder操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
    @Path("/{testCaseShareFolderId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCaseShareFolder的TestCase", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseShareFolderId + \"的TestCaseShareFolder不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "TestCase Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "TestCase Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "TestCase Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "TestCase Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "TestCase创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "TestCase创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getTestCaseByTestCaseShareFolderPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseShareFolderId") Long testCaseShareFolderId) throws Exception {
        logger.info("received get testCases by testCaseShareFolder id = " + testCaseShareFolderId);
        try {
            // get the record first
            long count = 0;
            List<TestCase> finalReturnRecords = null;
            TestCaseShareFolder checkTestCaseShareFolderRecord = testCaseShareFolderService
                    .selectByPrimaryKey(testCaseShareFolderId);
            if (checkTestCaseShareFolderRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseShareFolderId + "的TestCaseShareFolder不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                TestCaseShareFolderTestCaseLinkExample linkExample = new TestCaseShareFolderTestCaseLinkExample();
                linkExample.or().andTestCaseShareFolderIdEqualTo(testCaseShareFolderId);
                List<TestCaseShareFolderTestCaseLink> links = testCaseShareFolderTestCaseLinkService
                        .selectByExample(linkExample);
                List<Long> targetIds = new ArrayList<Long>();
                for (TestCaseShareFolderTestCaseLink link : links) {
                    targetIds.add(link.getTestCaseId());
                }
                if (!targetIds.isEmpty()) {
                    TestCaseExample testCaseExample = new TestCaseExample();
                    testCaseExample.or().andDeletedEqualTo(false).andIdIn(targetIds);
                    finalReturnRecords = testCaseService.selectByExample(testCaseExample);
                    count = finalReturnRecords.size();
                }
            }
            else {// with query parameters
                TestCaseShareFolderTestCaseLinkExample linkExample = new TestCaseShareFolderTestCaseLinkExample();
                linkExample.or().andTestCaseShareFolderIdEqualTo(testCaseShareFolderId);
                List<TestCaseShareFolderTestCaseLink> links = testCaseShareFolderTestCaseLinkService
                        .selectByExample(linkExample);
                List<Long> testCaseTargetIds = new ArrayList<Long>();
                for (TestCaseShareFolderTestCaseLink link : links) {
                    testCaseTargetIds.add(link.getTestCaseId());
                }
                if (testCaseTargetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                TestCaseExample.Criteria criteria = null;
                criteria = new TestCaseExample().createCriteria();
                criteria.andIdIn(testCaseTargetIds);
                TestCaseExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                if (queryParams.containsKey("ref")) {
                    finalReturnRecords = testCaseReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalReturnRecords = testCaseService.selectByExampleWithRowbounds(example, rowBounds);
                }
                count = testCaseService.countByExample(example);
            }
            // check ref test case filter
            if (queryParams.containsKey("refTestCaseId")) {
                if (queryParams.getFirst("refTestCaseId") != null) {
                    if (queryParams.getFirst("refTestCaseId").isEmpty()) {
                        UUID exuuid = UUID.randomUUID();
                        String trace = "exception UUID=" + exuuid + " ref test case id is empty";
                        String message = queryParams.getFirst("refTestCaseId") + "refTestCaseId为空。问题唯一码[" + exuuid
                                + "]";
                        String code = ERROR_TYPE + "01GET";
                        logger.error(trace, httpServletRequest.getContextPath());
                        throw new CustomBadRequestException(null, message, trace, code, exuuid);
                    }
                    Long refTestCaseId = Long.valueOf(queryParams.getFirst("refTestCaseId"));
                    if (refTestCaseId != null && refTestCaseId > 0) {
                        finalReturnRecords = testCaseShareFolderContentFilterService
                                .filteroutNonReferencableTestCases(refTestCaseId, finalReturnRecords);
                        count = finalReturnRecords.size();
                    }
                    else {
                        UUID exuuid = UUID.randomUUID();
                        String trace = "exception UUID=" + exuuid + " ref test case id is not a valid number";
                        String message = queryParams.getFirst("refTestCaseId") + "为非法TestCaseId。问题唯一码[" + exuuid + "]";
                        String code = ERROR_TYPE + "01GET";
                        logger.error(trace, httpServletRequest.getContextPath());
                        throw new CustomBadRequestException(null, message, trace, code, exuuid);
                    }
                }
            }
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", count);
            return new MeowlomoResponse(metadata, finalReturnRecords, null);
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
    @Path("/{testCaseShareFolderId}/testCaseShareFolderTestCaseLinks")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCaseShareFolder的TestCase", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseShareFolderId + \"的TestCaseShareFolder不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "TestCase Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "TestCase Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "TestCase Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "TestCase Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "TestCase创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "TestCase创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getTestCaseShareFolderTestCaseLinkByTestCaseShareFolderPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseShareFolderId") Long testCaseShareFolderId) throws Exception {
        logger.info(
                "received get testCaseShareFolderTestCaseLinks by testCaseShareFolder id = " + testCaseShareFolderId);
        try {
            // get the record first
            TestCaseShareFolder checkRecord = testCaseShareFolderService.selectByPrimaryKey(testCaseShareFolderId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseShareFolderId + "的TestCaseShareFolder不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                TestCaseShareFolderTestCaseLinkExample recordExample = new TestCaseShareFolderTestCaseLinkExample();
                recordExample.or().andTestCaseShareFolderIdEqualTo(testCaseShareFolderId);
                List<TestCaseShareFolderTestCaseLink> records = testCaseShareFolderTestCaseLinkService
                        .selectByExample(recordExample);
                // filter deleted testCase
                List<TestCaseShareFolderTestCaseLink> finalRecords = new ArrayList<TestCaseShareFolderTestCaseLink>();
                for (TestCaseShareFolderTestCaseLink record : records) {
                    if (!testCaseService.selectByPrimaryKey(record.getTestCaseId()).getDeleted()) {
                        finalRecords.add(record);
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                TestCaseShareFolderTestCaseLinkExample.Criteria criteria = null;
                criteria = new TestCaseShareFolderTestCaseLinkExample().createCriteria();
                criteria.andTestCaseShareFolderIdEqualTo(testCaseShareFolderId);
                if (queryParams.containsKey("ids") || queryParams.containsKey("name")
                        || queryParams.containsKey("startDate") || queryParams.containsKey("endDate")) {
                    TestCaseExample.Criteria testCaseCriteria = null;
                    testCaseCriteria = new TestCaseExample().createCriteria();
                    testCaseCriteria.andDeletedEqualTo(false);
                    TestCaseExample testCaseExample = this.searchExampleGenerator.generateExample(uriInfo,
                            testCaseCriteria, TestCaseExample.class);
                    List<TestCase> testCases = testCaseService.selectByExample(testCaseExample);
                    List<Long> testCaseIds = new ArrayList<Long>();
                    for (TestCase testCase : testCases) {
                        testCaseIds.add(testCase.getId());
                    }
                    if (testCaseIds.size() > 0) {
                        criteria.andTestCaseShareFolderIdEqualTo(testCaseShareFolderId).andTestCaseIdIn(testCaseIds);
                    }
                    else {
                        criteria.andIdIsNull();
                    }
                }
                TestCaseShareFolderTestCaseLinkExample example = new TestCaseShareFolderTestCaseLinkExample();
                example.or(criteria);
                final String orderBy = queryParams.getFirst("orderBy");
                if (orderBy != null && orderBy.startsWith("id")) {
                    String newOrderBy = orderBy.replace("id", "test_case_id");
                    example.setOrderByClause(newOrderBy);
                }
                else if (orderBy != null && orderBy.startsWith("name")) {
                    String newOrderBy = orderBy.replace("name", "id");
                    example.setOrderByClause(newOrderBy);
                }
                else if (orderBy != null && orderBy.startsWith("createdAt")) {
                    String newOrderBy = orderBy.replace("createdAt", "id");
                    example.setOrderByClause(newOrderBy);
                }
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<TestCaseShareFolderTestCaseLink> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = testCaseShareFolderTestCaseLinkReferenceService.selectByExampleWithRowbounds(example,
                            rowBounds);
                }
                else {
                    finalRecords = testCaseShareFolderTestCaseLinkService.selectByExampleWithRowbounds(example,
                            rowBounds);
                }
                long invalidCount = 0;
                if (queryParams.containsKey("refTestCaseId")) {
                    for (int i = 0; i < finalRecords.size(); i++) {
                        if (finalRecords.get(i).getTestCaseId().toString()
                                .equals(queryParams.getFirst("refTestCaseId"))) {
                            finalRecords.remove(i);
                            i--;
                            invalidCount++;
                        }
                    }
                }

                // if order by testCase name or createdAt
                if (orderBy != null && !orderBy.startsWith("id")) {
                    Collections.sort(finalRecords, new Comparator<TestCaseShareFolderTestCaseLink>() {

                        @Override
                        public int compare(TestCaseShareFolderTestCaseLink o1, TestCaseShareFolderTestCaseLink o2) {
                            int i = 0;
                            if (orderBy.startsWith("name")) {
                                // if no testCase,set defalt name is empty
                                String key1 = "";
                                String key2 = "";
                                if (o1.getTestCase() != null) {
                                    key1 = o1.getTestCase().getName();
                                }
                                if (o2.getTestCase() != null) {
                                    key2 = o2.getTestCase().getName();
                                }
                                if (orderBy.endsWith("asc")) {
                                    i = (key1).compareTo(key2);
                                }
                                else if (orderBy.endsWith("desc")) {
                                    i = -(key1).compareTo(key2);
                                }
                            }
                            else if (orderBy.startsWith("createdAt")) {
                                // if no testCase,set defalt date is 1000 days before
                                Calendar calendar = Calendar.getInstance();
                                calendar.add(Calendar.DATE, -1000);
                                Date key1 = calendar.getTime();
                                Date key2 = calendar.getTime();
                                if (o1.getTestCase() != null) {
                                    key1 = o1.getTestCase().getCreatedAt();
                                }
                                if (o2.getTestCase() != null) {
                                    key2 = o2.getTestCase().getCreatedAt();
                                }
                                if (orderBy.endsWith("asc")) {
                                    i = (key1).compareTo(key2);
                                }
                                else if (orderBy.endsWith("desc")) {
                                    i = -(key1).compareTo(key2);
                                }
                            }
                            return i;
                        }

                    });
                }

                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseShareFolderTestCaseLinkService.countByExample(example) - invalidCount);
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

    @PUT
    @LogUserActivity
    @Path("/{testCaseShareFolderId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个TestCase实体并链接到TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseShareFolderId + \"的TestCaseShareFolder不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加TestCase并连接到TestCaseShareFolder操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutTestCaseToTestCaseShareFolder(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseShareFolderId") Long testCaseShareFolderId, TestCase[] records) throws Exception {
        logger.info("received post testCase by testCaseShareFolder id=" + testCaseShareFolderId);
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
            TestCaseShareFolder checkTestCaseShareFolderRecord = testCaseShareFolderService
                    .selectByPrimaryKey(testCaseShareFolderId);
            if (checkTestCaseShareFolderRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseShareFolderId + "的TestCaseShareFolder不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<TestCase> finalRecords = new ArrayList<TestCase>();
            for (int i = 0; i < records.length; i++) {
                TestCase record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = testCaseService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // insert link
                        TestCaseShareFolderTestCaseLink link = new TestCaseShareFolderTestCaseLink();
                        link.setTestCaseId(record.getId());
                        link.setTestCaseShareFolderId(testCaseShareFolderId);
                        if (testCaseShareFolderTestCaseLinkService.insertSelective(link) == 1) {
                            finalRecords.add(record);
                        }
                        else {
                            errorIndex.add((long) i);
                        }
                    }
                }
                else {
                    if (record.getName() == null) {
                        record.setName(testCaseService.selectByPrimaryKey(record.getId()).getName());
                    }
                    int updateResult = testCaseService.updateByPrimaryKeySelective(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // check link exist
                        TestCaseShareFolderTestCaseLinkExample linkExample = new TestCaseShareFolderTestCaseLinkExample();
                        linkExample.or().andTestCaseIdEqualTo(record.getId())
                                .andTestCaseShareFolderIdEqualTo(testCaseShareFolderId);
                        long countResult = testCaseShareFolderTestCaseLinkService.countByExample(linkExample);
                        if (countResult == 1) {
                            finalRecords.add(testCaseService.selectByPrimaryKey(record.getId()));
                        }
                        else {
                            // add the link
                            TestCaseShareFolderTestCaseLink link = new TestCaseShareFolderTestCaseLink();
                            link.setTestCaseId(record.getId());
                            link.setTestCaseShareFolderId(testCaseShareFolderId);
                            if (testCaseShareFolderTestCaseLinkService.insertSelective(link) == 1) {
                                finalRecords.add(testCaseService.selectByPrimaryKey(record.getId()));
                            }
                            else {
                                errorIndex.add((long) i);
                            }
                        }
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
    @Path("/{testCaseShareFolderId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到TestCaseShareFolder的TestCase的链接", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除TestCaseShareFolder关联的TestCase操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的TestCase关联到此TestCaseShareFolder，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+testCaseShareFolderId+\"的TestCaseShareFolder不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此TestCaseShareFolder的TestCase。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseShareFolderId", value = "TestCaseShareFolder ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "TestCase IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkTestCaseFromTestCaseShareFolder(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseShareFolderId") Long testCaseShareFolderId) throws Exception {
        logger.info("received post testCase by testCaseShareFolder id=" + testCaseShareFolderId);
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
            TestCaseShareFolder checkRecord = testCaseShareFolderService.selectByPrimaryKey(testCaseShareFolderId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseShareFolderId + "的TestCaseShareFolder不存在。问题唯一码[" + exuuid + "]";
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
            TestCaseShareFolderTestCaseLinkExample linkExample = new TestCaseShareFolderTestCaseLinkExample();
            linkExample.or().andTestCaseShareFolderIdEqualTo(testCaseShareFolderId).andTestCaseIdIn(idsList);
            long linkCount = testCaseShareFolderTestCaseLinkService.countByExample(linkExample);
            if (linkCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "输入中存在未关联到此TestCaseShareFolder的TestCase。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            // start the delete
            TestCaseExample recordExample = new TestCaseExample();
            recordExample.or().andDeletedEqualTo(false).andIdIn(idsList);
            List<TestCase> finalRecords = testCaseService.selectByExample(recordExample);
            int deleteResult = testCaseShareFolderTestCaseLinkService.deleteByExample(linkExample);
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
                String message = "存在已删除的TestCase关联到此TestCaseShareFolder，数据不一致。请与管理员联系。并提供唯一码[" + exuuid + "]";
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
                String message = "删除TestCaseShareFolder关联的TestCase操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    // =====testCaseShareFolder testCase link end =====
}
