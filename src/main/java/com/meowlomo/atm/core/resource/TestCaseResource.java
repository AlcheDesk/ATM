package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackExample;
import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;
import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.model.RunExecutionInfo;
import com.meowlomo.atm.core.model.RunExecutionInfoExample;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;
import com.meowlomo.atm.core.model.Tag;
import com.meowlomo.atm.core.model.TagExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.model.TestCaseOptionEntry;
import com.meowlomo.atm.core.model.TestCaseOptionEntryExample;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;
import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;
import com.meowlomo.atm.core.model.TestCaseTagLink;
import com.meowlomo.atm.core.model.TestCaseTagLinkExample;
import com.meowlomo.atm.core.model.TestCaseTaskLink;
import com.meowlomo.atm.core.model.TestCaseTaskLinkExample;
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
import com.meowlomo.atm.core.service.base.DriverPackService;
import com.meowlomo.atm.core.service.base.InstructionService;
import com.meowlomo.atm.core.service.base.ModuleService;
import com.meowlomo.atm.core.service.base.ProjectService;
import com.meowlomo.atm.core.service.base.RunExecutionInfoService;
import com.meowlomo.atm.core.service.base.RunService;
import com.meowlomo.atm.core.service.base.RunSetService;
import com.meowlomo.atm.core.service.base.RunSetTestCaseLinkService;
import com.meowlomo.atm.core.service.base.TagService;
import com.meowlomo.atm.core.service.base.TestCaseModuleLinkService;
import com.meowlomo.atm.core.service.base.TestCaseOptionEntryService;
import com.meowlomo.atm.core.service.base.TestCaseOverwriteService;
import com.meowlomo.atm.core.service.base.TestCaseService;
import com.meowlomo.atm.core.service.base.TestCaseShareFolderService;
import com.meowlomo.atm.core.service.base.TestCaseShareFolderTestCaseLinkService;
import com.meowlomo.atm.core.service.base.TestCaseTagLinkService;
import com.meowlomo.atm.core.service.base.TestCaseTaskLinkService;
import com.meowlomo.atm.core.service.referrence.DriverPackReferenceService;
import com.meowlomo.atm.core.service.referrence.InstructionReferenceService;
import com.meowlomo.atm.core.service.referrence.ProjectReferenceService;
import com.meowlomo.atm.core.service.referrence.RunExecutionInfoReferenceService;
import com.meowlomo.atm.core.service.referrence.RunReferenceService;
import com.meowlomo.atm.core.service.referrence.TagReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseOverwriteReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;
import com.meowlomo.atm.core.service.util.DriverPackUtilService;
import com.meowlomo.atm.core.service.util.DriverTypeUtilService;
import com.meowlomo.atm.core.service.util.InstructionOverwriteUtilService;
import com.meowlomo.atm.core.service.util.InstructionUtilService;
import com.meowlomo.atm.core.service.util.TestCaseUtilService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/testCases")
@Api(value = "testCase resources", produces = "application/json")
public class TestCaseResource {

    private final Logger logger = LoggerFactory.getLogger(TestCaseResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "TSC";

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestCaseReferenceService testCaseReferenceService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectReferenceService projectReferenceService;

    @Autowired
    private RunSetService runSetService;

    @Autowired
    private RunReferenceService runReferenceService;

    @Autowired
    private TagReferenceService tagReferenceService;

    @Autowired
    private RunSetTestCaseLinkService testCaseRunSetLinkService;

    @Autowired
    private TestCaseShareFolderService testCaseShareFolderService;

    @Autowired
    private TestCaseShareFolderTestCaseLinkService testCaseShareFolderTestCaseLinkService;

    @Autowired
    private InstructionService instructionService;

    @Autowired
    private InstructionReferenceService instructionReferenceService;

    @Autowired
    private RunService runService;

    @Autowired
    private TestCaseTaskLinkService testCaseTaskLinkService;

    @Autowired
    private TestCaseOptionEntryService testCaseOptionEntryService;

    @Autowired
    private DriverPackUtilService driverPackUtilService;

    @Autowired
    private DriverPackService driverPackService;

    @Autowired
    private DriverPackReferenceService driverPackReferenceService;

    @Autowired
    private DriverTypeUtilService driverTypeUtilService;

    @Autowired
    private InstructionOverwriteUtilService instructionOverwriteUtilService;

    @Autowired
    private TestCaseOverwriteService testCaseOverwriteService;

    @Autowired
    private TestCaseOverwriteReferenceService testCaseOverwriteReferenceService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TestCaseModuleLinkService testCaseModuleLinkService;

    @Autowired
    private TestCaseTagLinkService testCaseTagLinkService;

    @Autowired
    private InstructionUtilService instructionUtilService;
    
    @Autowired
    private RunExecutionInfoService runExecutionInfoService;
    
    @Autowired
    private RunExecutionInfoReferenceService runExecutionInfoReferenceService;
    
    @Autowired
    private TestCaseUtilService testCaseUtilService;

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
    @ApiOperation(value = "读取TestCase", response = MeowlomoResponse.class, httpMethod = "GET")
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
        logger.info("received testCase select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TestCaseExample example = new TestCaseExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseService.countByExample(example));
                List<TestCase> records = testCaseService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                TestCaseExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new TestCaseExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                TestCaseExample.Criteria criteria = null;
                criteria = new TestCaseExample().createCriteria();
                if (queryParams.containsKey("tags")) {
                    TagExample tagExample = new TagExample();
                    String tags = queryParams.getFirst("tags");
                    String[] tagsStringArray = tags.trim().split(",");
                    List<String> tagsList = Arrays.asList(tagsStringArray);
                    tagExample.createCriteria().andNameIn(tagsList);
                    List<Long> tagIds = new ArrayList<Long>();
                    List<Tag> tagRecords = tagService.selectByExample(tagExample);
                    for (Tag tagRecord : tagRecords) {
                        tagIds.add(tagRecord.getId());
                    }
                    TestCaseTagLinkExample testCaseTagLinkExample = new TestCaseTagLinkExample();
                    List<TestCaseTagLink> testCaseTagLinkRecords = new ArrayList<TestCaseTagLink>();
                    List<Long> testCaseFinalIds = new ArrayList<Long>();
                    if (tagIds.size() > 0) {
                        List<Long> testCaseIds;
                        if (queryParams.containsKey("logic")) {
                            if (Boolean.parseBoolean(queryParams.getFirst("logic"))) {
                                testCaseTagLinkExample.createCriteria().andTagIdIn(tagIds);
                                testCaseTagLinkRecords = testCaseTagLinkService.selectByExample(testCaseTagLinkExample);
                                testCaseIds = new ArrayList<Long>();
                                for (TestCaseTagLink testCaseTagLinkRecord : testCaseTagLinkRecords) {
                                    testCaseIds.add(testCaseTagLinkRecord.getTestCaseId());
                                }
                                for (Long testCaseId : testCaseIds) {
                                    ;
                                    int count = 0;
                                    TestCaseTagLinkExample tagSizeLinkExample = new TestCaseTagLinkExample();
                                    tagSizeLinkExample.createCriteria().andTestCaseIdEqualTo(testCaseId);
                                    List<TestCaseTagLink> tagSizelinks = testCaseTagLinkService
                                            .selectByExample(tagSizeLinkExample);
                                    List<Long> tagSizeTagids = new ArrayList<Long>();
                                    for (TestCaseTagLink tagSizelink : tagSizelinks) {
                                        tagSizeTagids.add(tagSizelink.getTagId());
                                    }
                                    TagExample tagSizeTagExample = new TagExample();
                                    tagSizeTagExample.createCriteria().andIdIn(tagSizeTagids);
                                    List<Tag> finalTagSize = tagService.selectByExample(tagSizeTagExample);
                                    for (Long tagId : tagIds) {
                                        TestCaseTagLinkExample newExample = new TestCaseTagLinkExample();
                                        newExample.createCriteria().andTagIdEqualTo(tagId)
                                                .andTestCaseIdEqualTo(testCaseId);
                                        if (testCaseTagLinkService.selectByExample(newExample).size() > 0) {
                                            count++;
                                        }
                                    }
                                    if (count == finalTagSize.size()) {
                                        testCaseFinalIds.add(testCaseId);
                                    }
                                }
                            }
                            else {
                                testCaseTagLinkExample.createCriteria().andTagIdIn(tagIds);
                                testCaseTagLinkRecords = testCaseTagLinkService.selectByExample(testCaseTagLinkExample);
                                for (TestCaseTagLink testCaseTagLinkRecord : testCaseTagLinkRecords) {
                                    testCaseFinalIds.add(testCaseTagLinkRecord.getTestCaseId());
                                }
                            }
                        }
                    }
                    if (testCaseFinalIds.size() > 0) {
                        criteria.andIdIn(testCaseFinalIds);
                    }
                    else {
                        criteria.andIdIsNull();
                    }
                }
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseService.countByExample(example));
                List<TestCase> records = null;
                if (queryParams.containsKey("ref")) {
                    records = testCaseReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = testCaseService.selectByExampleWithRowbounds(example, rowBounds);
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
    @ApiOperation(value = "读取单个TestCase", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "testCase id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long id) {
        logger.info("received testCase select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            TestCase selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = testCaseReferenceService.selectByPrimaryKey(id);
            }
            else {
                selectRecord = testCaseService.selectByPrimaryKey(id);
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
    @ApiOperation(value = "删除单个TestCase", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "testCase id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") long id) {
        logger.info("received testCase delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            TestCase record = testCaseService.selectByPrimaryKey(id);
            int deleteResult = testCaseService.logicalDeleteByPrimaryKey(id);
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
    @ApiOperation(value = "批量删除TestCase", response = MeowlomoResponse.class, httpMethod = "DELETE")
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
        logger.info("received testCase delete call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TestCaseExample example = new TestCaseExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<TestCase> records = testCaseService.selectByExample(example);
                metadata.put("count", testCaseService.countByExample(example));
                int deleteResult = testCaseService.logicalDeleteByExample(example);
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
                com.meowlomo.atm.core.model.TestCaseExample.Criteria criteria = new TestCaseExample().createCriteria();
                criteria.andIdIsNotNull().andDeletedEqualTo(false);
                TestCaseExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseExample.class);
                // count
                List<TestCase> countRecords = testCaseService.selectByExample(example);
                // mark deleted
                int deleteResult = testCaseService.logicalDeleteByExample(example);
                // get back records
                List<Long> deletedIds = new ArrayList<Long>();
                for (TestCase target : countRecords) {
                    deletedIds.add(target.getId());
                }
                TestCaseExample deletedExample = new TestCaseExample();
                deletedExample.or().andIdIsNotNull().andDeletedEqualTo(true).andIdIn(deletedIds);
                List<TestCase> records = testCaseService.selectByExample(deletedExample);
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
    @ApiOperation(value = "更新TestCase", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCase[] records) throws Exception {
        logger.info("received patch testCase by id call " + uriInfo.getPath());
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
                TestCase record = records[i];
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
                    TestCase record = records[i];
                    int updateResult = testCaseService.updateByPrimaryKeySelective(records[i]);
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
                TestCaseExample example = new TestCaseExample();
                example.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<TestCase> finalRecords = testCaseService.selectByExample(example);
                // sort return result
                List<TestCase> finalReturnRecords = new ArrayList<TestCase>();
                for (Long id : targetIds) {
                    for (TestCase oRecord : finalRecords) {
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
    @ApiOperation(value = "替换或添加TestCase", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCase[] records) {
        logger.info("received put testCase by primary id call");
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
                        finalRecords.add(record);
                    }
                }
                else {
                    // do update
                    int updateResult = testCaseService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(testCaseService.selectByPrimaryKey(record.getId()));
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
    @ApiOperation(value = "添加TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TestCase[] records) {
        logger.info("received post testCase call ");
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
            List<TestCase> finalRecords = new ArrayList<TestCase>();
            for (int i = 0; i < records.length; i++) {
                TestCase record = records[i];
                record.setId(null);
                long insertResult = testCaseService.insertSelective(record);
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

    // ===== testCase instruction link start =====

    @GET
    @Path("/{testCaseId}/instructions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCase的Instruction", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "Instruction 名字", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "Instruction 类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "Instruction 创建起始时间 [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "Instruction 创建终结时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getInstructionByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get instructions by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                InstructionExample recordExample = new InstructionExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId).andDeletedEqualTo(false);
                List<Instruction> records = instructionService.selectByExample(recordExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                InstructionExample.Criteria criteria = null;
                criteria = new InstructionExample().createCriteria();
                criteria.andTestCaseIdEqualTo(testCaseId);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                InstructionExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        InstructionExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<Instruction> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = instructionReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = instructionService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionService.countByExample(example));
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
    @Path("/{testCaseId}/instructions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个Instruction到TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加Instruction到TestCase操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path") })
    public MeowlomoResponse insertInstructionAndLinkToTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            Instruction[] records) throws Exception {
        logger.info("received post instruction by testCase id=" + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add the id to the record
            List<Long> insertedResults = new ArrayList<Long>();
            for (Instruction entry : records) {
                entry.setTestCaseId(testCaseId);
                if (entry.getType().equals("Reference")) {
                    instructionService.insertSelective(entry);
                    insertedResults.add(entry.getId());
                }
                else {
                    instructionService.insertSelective(entry);
                    insertedResults.add(entry.getId());
                    // check if there options
                    instructionUtilService.checkOptionForPostInstruction(entry);
                }
            }
            if (insertedResults.size() == records.length) {
                InstructionExample example = new InstructionExample();
                example.or().andIdIn(insertedResults);
                List<Instruction> finalRecords = instructionService.selectByExample(example);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                // Reorder order index
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert.";
                String code = ERROR_TYPE + "02POS";
                String message = "添加Instruction到TestCase操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
    @Path("/{testCaseId}/instructions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到TestCase的Instruction", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "删除TestCase关联的Instruction操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "Instruction IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse deleteInstructionsFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received delete instructions by testCase id=" + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
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
            InstructionExample recordExample = new InstructionExample();
            recordExample.or().andDeletedEqualTo(false).andIdIn(idsList);
            List<Instruction> finalRecords = instructionService.selectByExample(recordExample);
            int deleteResult = instructionService.logicalDeleteByExample(recordExample);
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
                String message = "删除TestCase关联的Instruction操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    @Path("/{testCaseId}/instructions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新关联到TestCase的Instruction", response = MeowlomoResponse.class, httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部更新失败，失败序列=>\" + errorIndex.toString() + \" 无关联序列 =>\" + noLinkedIndex.toString()+ \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更新内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分更新请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "Instruction IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse updateInstructionsFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            Instruction[] records) throws Exception {
        logger.info("received patch instructions by testCase id = " + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
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
                Instruction record = records[i];
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
                    Instruction record = records[i];
                    InstructionExample example = new InstructionExample();
                    example.or().andDeletedEqualTo(false).andTestCaseIdEqualTo(testCaseId).andIdEqualTo(record.getId());
                    if (instructionService.countByExample(example) == 1) {
                        int updateResult = instructionService.updateByPrimaryKeySelective(record);

                        if (updateResult != 1) {
                            errorIndex.add(record.getId());
                        }
                        else {
                            // check if there options
                            instructionUtilService.checkOptionForPostInstruction(record);
                        }
                    }
                    else {
                        noLinkedIndex.add(record.getId());
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty() && noLinkedIndex.isEmpty()) {
                InstructionExample example = new InstructionExample();
                example.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<Instruction> finalRecords = instructionService.selectByExample(example);
                // sort return result
                List<Instruction> finalReturnRecords = new ArrayList<Instruction>();
                for (Long id : targetIds) {
                    for (Instruction oRecord : finalRecords) {
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
    // =====testCase instruction link end =====

    // =====testCase testCaseOption link start =====

    @GET
    @Path("/{testCaseId}/testCaseOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于Instrcution的TestCaseOption", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+testCaseId+\"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "关联的TestCase ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "TestCaseOption IDs, 逗号分隔", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "names", value = "TestCaseOption Names, 逗号分隔", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "values", value = "TestCaseOption Values, 逗号分隔", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "排序输入", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse getTestCaseOptionByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get testCaseOption by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                TestCaseOptionEntryExample recordExample = new TestCaseOptionEntryExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId);
                List<TestCaseOptionEntry> records = testCaseOptionEntryService.selectByExample(recordExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {// with query parameters
                TestCaseOptionEntryExample recordExample = new TestCaseOptionEntryExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId);
                List<TestCaseOptionEntry> records = testCaseOptionEntryService.selectByExample(recordExample);
                // empty just return
                if (records.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }

                // not empty
                TestCaseOptionEntryExample.Criteria criteria = null;
                // get the ids with the
                List<Long> targetIds = new ArrayList<Long>();
                for (TestCaseOptionEntry record : records) {
                    targetIds.add(record.getId());
                }
                criteria = new TestCaseOptionEntryExample().createCriteria();
                criteria.andIdIn(targetIds);
                TestCaseOptionEntryExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseOptionEntryExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<TestCaseOptionEntry> finalRecords = testCaseOptionEntryService
                        .selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseOptionEntryService.countByExample(example));
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @POST
    @LogUserActivity
    @Path("/{testCaseId}/testCaseOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个TestCaseOption到TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加TestCase关联组件TestCaseOption操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path") })
    public MeowlomoResponse batchInsertTestCaseOptionAndLinkToTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            TestCaseOptionEntry[] records) throws Exception {
        logger.info("received post testCaseOption by testCase id=" + testCaseId);
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

            // add the id to the record
            List<TestCaseOptionEntry> entries = new ArrayList<TestCaseOptionEntry>();
            for (TestCaseOptionEntry entry : records) {
                entry.setTestCaseId(testCaseId);
                entries.add(entry);
            }
            List<Long> insertedResult = testCaseOptionEntryService.insertRecordsSelective(entries);
            if (insertedResult.size() == records.length) {
                TestCaseOptionEntryExample example = new TestCaseOptionEntryExample();
                example.or().andIdIn(insertedResult);
                example.setOrderByClause(" id ASC ");
                List<TestCaseOptionEntry> finalRecords = testCaseOptionEntryService.selectByExample(example);
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert.";
                String code = ERROR_TYPE + "L02";
                String message = "添加TestCase关联组件TestCaseOption操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @DELETE
    @LogUserActivity
    @Path("/{testCaseId}/testCaseOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到TestCase的TestCaseOption", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除TestCase关联的TestCaseOption操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+testCaseId+\"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "所输入TestCaseOption中存在未关联到此TestCase。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "TestCaseOption IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse deleteTestCaseOptionFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received post testCase by testCase id=" + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
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
            TestCaseOptionEntryExample entryExample = new TestCaseOptionEntryExample();
            entryExample.or().andTestCaseIdEqualTo(testCaseId).andIdIn(idsList);
            long entryCount = testCaseOptionEntryService.countByExample(entryExample);
            if (entryCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "所输入TestCaseOption中存在未关联到此TestCase。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomForbiddenException(null, message, trace, code, exuuid);
            }
            // start the delete
            List<TestCaseOptionEntry> finalRecords = testCaseOptionEntryService.selectByExample(entryExample);
            int deleteResult = testCaseOptionEntryService.deleteByExample(entryExample);
            if (deleteResult == entryCount) {
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
                String message = "删除TestCase关联的TestCaseOption操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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

    @PATCH
    @LogUserActivity
    @Path("/{testCaseId}/testCaseOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新多个关联TestCase的TestCaseOption", response = MeowlomoResponse.class, httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更新内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分更新请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "所输入TestCaseOption中存在未关联到此TestCase。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部更新失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "TestCaseOption IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse updateTestCaseOptionsFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            TestCaseOptionEntry[] records) throws Exception {
        logger.info("received patch testCaseOptions by testCase id=" + testCaseId);
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

            List<Long> idsList = new ArrayList<Long>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                TestCaseOptionEntry record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    idsList.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            if (records.length != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "部分更新请求不含ID。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            TestCaseOptionEntryExample entryExample = new TestCaseOptionEntryExample();
            entryExample.or().andIdIn(idsList);
            long entryCount = testCaseOptionEntryService.countByExample(entryExample);
            if (entryCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "所输入TestCaseOption中存在未关联到此TestCase。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomForbiddenException(null, message, trace, code, exuuid);
            }
            // start the update
            // update one by one
            for (int i = 0; i < records.length; i++) {
                TestCaseOptionEntry record = records[i];
                int updateResult = testCaseOptionEntryService.updateByPrimaryKeySelective(record);
                if (updateResult != 1) {
                    errorIndex.add(record.getId());
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                List<TestCaseOptionEntry> finalRecords = testCaseOptionEntryService.selectByExample(entryExample);
                // sort the return
                List<TestCaseOptionEntry> finalReturnRecords = new ArrayList<TestCaseOptionEntry>();
                for (Long oRecordId : idsList) {
                    for (TestCaseOptionEntry oRecord : finalRecords) {
                        if (oRecord.getId() == oRecordId) {
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Replace or insert testCase and testCase option link.
     *
     * @param testCaseId
     *            the testCase id
     * @param testCaseOptionId
     *            the testCase option id
     * @param record
     *            the record
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @PUT
    @LogUserActivity
    @Path("/{testCaseId}/testCaseOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新或替换关联TestCase的TestCaseOption", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "替换内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "所输入TestCaseOption中存在未关联到此TestCase。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path") })
    public MeowlomoResponse replaceOrInsertTestCaseOptionByTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            @PathParam("testCaseOptionId") Long testCaseOptionId, TestCaseOptionEntry[] records) throws Exception {
        logger.info("received put testCaseOption by testCase id = " + testCaseId);
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
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
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

            List<Long> idsList = new ArrayList<Long>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                TestCaseOptionEntry record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    idsList.add(record.getId());
                }
            }
            // check all link exists
            TestCaseOptionEntryExample entryExample = new TestCaseOptionEntryExample();
            entryExample.or().andIdIn(idsList);
            long entryCount = testCaseOptionEntryService.countByExample(entryExample);
            if (entryCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "所输入TestCaseOption中存在未关联到此TestCase。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PUT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomForbiddenException(null, message, trace, code, exuuid);
            }
            // start the replace or insert
            Map<Long, Long> resultMap = new HashMap<Long, Long>();
            List<Long> errorUpdateIndex = new ArrayList<Long>();
            List<Long> insertIds = new ArrayList<Long>();
            int insertErrorNum = 0;
            for (int i = 0; i < records.length; i++) {
                TestCaseOptionEntry record = records[i];
                int updateResult = 0;
                long insertResult = 0;
                if (record.getId() != null) {
                    updateResult = testCaseOptionEntryService.updateByPrimaryKeySelective(record);
                    if (updateResult != 1) {
                        errorUpdateIndex.add(record.getId());
                    }
                    resultMap.put(Long.valueOf(i), record.getId());
                }
                else {
                    insertResult = testCaseOptionEntryService.insertSelective(record);
                    if (insertResult == 1) {
                        insertIds.add(record.getId());
                    }
                    else {
                        insertErrorNum = insertErrorNum + 1;
                    }
                    resultMap.put(Long.valueOf(i), record.getId());
                }
            }
            // check all update success
            if (errorUpdateIndex.isEmpty() && insertErrorNum == 0) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                TestCaseOptionEntryExample updateExample = new TestCaseOptionEntryExample();
                updateExample.or().andIdIn(idsList);
                List<TestCaseOptionEntry> finalUpdateRecords = testCaseOptionEntryService
                        .selectByExample(updateExample);
                TestCaseOptionEntryExample insertExample = new TestCaseOptionEntryExample();
                insertExample.or().andIdIn(insertIds);
                List<TestCaseOptionEntry> finalInsertRecords = testCaseOptionEntryService
                        .selectByExample(insertExample);
                // sort the return
                List<TestCaseOptionEntry> finalReturnRecords = new ArrayList<TestCaseOptionEntry>();
                for (int i = 0; i < records.length; i++) {
                    TestCaseOptionEntry oRecord = records[i];
                    if (oRecord.getId() != null) {
                        for (TestCaseOptionEntry uRecord : finalUpdateRecords) {
                            if (oRecord.getId() == uRecord.getId()) {
                                finalReturnRecords.add(uRecord);
                            }
                        }
                    }
                    else {
                        for (TestCaseOptionEntry iRecord : finalInsertRecords) {
                            if (iRecord.getId() == resultMap.get(Long.valueOf(i))) {
                                finalReturnRecords.add(iRecord);
                            }
                        }
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalUpdateRecords.size() + finalInsertRecords.size());
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
                String message = "部分或全部替换失败，更新失败序列。" + errorUpdateIndex.toString() + "插入错误个数  " + insertErrorNum
                        + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PUT";
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
    // =====testCase testCaseOption link end =====

    // =====testCase run link start =====

    /**
     * Gets the testCase option by testCase primary key.
     *
     * @param testCaseId
     *            the testCase id
     * @return the testCase option by testCase primary key
     * @throws Exception
     *             the exception
     */
    @GET
    @Path("/{testCaseId}/runs")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于Instrcution的Run", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
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
    public MeowlomoResponse getRunByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get runs by testCase id=" + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                RunExample recordExample = new RunExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId);
                List<Run> records = runService.selectByExample(recordExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {// with query parameters
                RunExample recordExample = new RunExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId);
                List<Run> records = runService.selectByExample(recordExample);
                // empty just return
                if (records.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }

                // not empty
                RunExample.Criteria criteria = null;
                // get the ids with the
                List<Long> targetIds = new ArrayList<Long>();
                for (Run record : records) {
                    targetIds.add(record.getId());
                }
                criteria = new RunExample().createCriteria();
                criteria.andIdIn(targetIds);
                RunExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria, RunExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<Run> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = runReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = runService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", runService.countByExample(example));
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Batch insert testCase option and link to testCase.
     *
     * @param testCaseId
     *            the testCase id
     * @param records
     *            the records
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @POST
    @LogUserActivity
    @Path("/{testCaseId}/runs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个Run到TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "testCase ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse batchInsertRunAndLinkToTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId, Run[] records)
            throws Exception {
        logger.info("received post run by testCase id=" + testCaseId);
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " body is empty ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add the id to the record
            List<Run> entries = new ArrayList<Run>();
            for (Run entry : records) {
                entry.setTestCaseId(testCaseId);
                entry.setId(null);
                entries.add(entry);
            }
            List<Long> insertedResult = runService.insertRecordsSelective(entries);
            if (insertedResult.size() == entries.size()) {
                RunExample example = new RunExample();
                example.or().andIdIn(insertedResult);
                List<Run> finalRecords = runService.selectByExample(example);
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert.";
                String code = ERROR_TYPE + "02POS";
                String message = "添加TestCase关联组件Run操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Delete testCase and testCase option link.
     *
     * @param testCaseId
     *            the testCase id
     * @param runId
     *            the testCase option id
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @DELETE
    @LogUserActivity
    @Path("/{testCaseId}/runs/{runId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联TestCase的单个Run", response = MeowlomoResponse.class, httpMethod = "DELETE")
    public MeowlomoResponse deleteTestCaseAndRunLink(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            @PathParam("runId") Long runId) throws Exception {
        logger.info("received delete testCase and run link testCase id=" + testCaseId + " run id=" + runId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            Run record = runService.selectByPrimaryKey(runId);
            if (record == null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(null, null, null);
            }
            RunExample linkExample = new RunExample();
            linkExample.createCriteria().andTestCaseIdEqualTo(testCaseId).andIdEqualTo(runId);
            int deleteResult = runService.deleteByExample(linkExample);
            if (deleteResult == 1) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(null, record, null);
            }
            else {
                // rollback
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String message = "不能完整删除，请检查删除条件。错误唯一码[" + exuuid + "]";
                String trace = "exception UUID=" + exuuid
                        + " the deletion is not completed, please check the query parameters.";
                String code = ERROR_TYPE + "02F";
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Delete testCase options from testCase.
     *
     * @param testCaseId
     *            the testCase id
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @DELETE
    @LogUserActivity
    @Path("/{testCaseId}/runs")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除多个关联TestCase的Run", response = MeowlomoResponse.class, httpMethod = "DELETE")
    public MeowlomoResponse deleteRunsFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received delete runs by testCase id=" + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                RunExample runExample = new RunExample();
                runExample.or().andTestCaseIdEqualTo(testCaseId);
                List<Run> targetRecords = runService.selectByExample(runExample);
                int deleteTargetsResult = runService.deleteByExample(runExample);
                if (deleteTargetsResult == targetRecords.size()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", deleteTargetsResult);
                    return new MeowlomoResponse(null, targetRecords, null);
                }
                else {
                    // rollback
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String message = "不能完整删除，请检查删除条件。错误唯一码[" + exuuid + "]";
                    String trace = "exception UUID=" + exuuid
                            + " the deletion is not completed, please check the query parameters.";
                    String code = ERROR_TYPE + "03F";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotFoundException(null, message, trace, code, exuuid);
                }
            }
            else {
                RunExample runExample = new RunExample();
                runExample.or().andTestCaseIdEqualTo(testCaseId);
                List<Run> targetRecords = runService.selectByExample(runExample);
                // empty just return
                if (targetRecords.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                // not empty
                RunExample.Criteria criteria = null;
                // get the ids with the
                List<Long> targetIds = new ArrayList<Long>();
                for (Run record : targetRecords) {
                    targetIds.add(record.getId());
                }
                criteria = new RunExample().createCriteria();
                criteria.andIdIn(targetIds);
                RunExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria, RunExample.class);
                List<Run> finalRecords = runService.selectByExample(example);
                int deleteTargetsResult = runService.deleteByExample(example);
                if (deleteTargetsResult == finalRecords.size()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", deleteTargetsResult);
                    return new MeowlomoResponse(null, finalRecords, null);
                }
                else {
                    // rollback
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String message = "不能完整删除，请检查删除条件。错误唯一码[" + exuuid + "]";
                    String trace = "exception UUID=" + exuuid
                            + " the deletion is not completed, please check the query parameters.";
                    String code = ERROR_TYPE + "03F";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotFoundException(null, message, trace, code, exuuid);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Update testCase options from testCase.
     *
     * @param testCaseId
     *            the testCase id
     * @param record
     *            the record
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @PATCH
    @LogUserActivity
    @Path("/{testCaseId}/runs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新多个关联TestCase的Run", response = MeowlomoResponse.class, httpMethod = "PATCH")
    public MeowlomoResponse updateRunsFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId, Run[] records)
            throws Exception {
        logger.info("received delete runs by testCase id=" + testCaseId);
        try {
            // no body
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String message = "更新内容为空，错误唯一码[" + exuuid + "]。";
                String trace = "exception UUID=" + exuuid + " the body is empty, please check.";
                String code = ERROR_TYPE + "05F";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }

            List<Long> ids = new ArrayList<Long>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                Run record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    ids.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            if (records.length != ids.size()) {
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
                    Run record = records[i];
                    record.setTestCaseId(testCaseId);
                    int updateResult = runService.updateByPrimaryKeySelective(record);
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
                RunExample example = new RunExample();
                example.or().andIdIn(ids);
                List<Run> finalRecords = runService.selectByExample(example);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Replace or insert testCase and testCase option link.
     *
     * @param testCaseId
     *            the testCase id
     * @param runId
     *            the testCase option id
     * @param record
     *            the record
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @PUT
    @LogUserActivity
    @Path("/{testCaseId}/runs/{runId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新或替换关联TestCase的单个Run", response = MeowlomoResponse.class, httpMethod = "PUT")
    public MeowlomoResponse replaceOrInsertTestCaseAndRunLink(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            @PathParam("runId") Long runId, Run record) throws Exception {
        logger.info("received patch testCase and run link testCase id=" + testCaseId + " run id=" + runId);
        try {
            // no body
            if (record == null) {
                UUID exuuid = UUID.randomUUID();
                String message = "更新内容为空，错误唯一码[" + exuuid + "]。";
                String trace = "exception UUID=" + exuuid + " the body is empty, please check.";
                String code = ERROR_TYPE + "04F";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            RunExample linkEntryExample = new RunExample();
            linkEntryExample.createCriteria().andTestCaseIdEqualTo(testCaseId).andIdEqualTo(runId);
            List<Run> targetRecords = runService.selectByExample(linkEntryExample);
            if (targetRecords.size() == 1) {
                // start the update
                record.setId(runId);
                int updateResult = runService.updateByPrimaryKey(record);
                if (updateResult == 1) {
                    Run finalRecord = runService.selectByPrimaryKey(runId);
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 1);
                    return new MeowlomoResponse(null, finalRecord, null);
                }
                else {
                    // rollback
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String message = "无法完成替换。错误唯一码[" + exuuid + "]";
                    String trace = "exception UUID=" + exuuid + " patching is not completed.";
                    String code = ERROR_TYPE + "08F";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotSupportedException(null, message, trace, code, exuuid);
                }
            }
            else {
                // need to do insert
                record.setId(null);
                record.setTestCaseId(testCaseId);
                long insertResult = runService.insert(record);
                if (insertResult == 1) {
                    Run finalRecord = runService.selectByPrimaryKey(runId);
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 1);
                    return new MeowlomoResponse(null, finalRecord, null);
                }
                else {
                    UUID exuuid = UUID.randomUUID();
                    String message = "无法完成有替换转成的添加。错误唯一码[" + exuuid + "]";
                    String trace = "exception UUID=" + exuuid + " patching is not completed.";
                    String code = ERROR_TYPE + "09F";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotSupportedException(null, message, trace, code, exuuid);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====testCase run link end =====

    // =======================================================================================================
    // ===== testCase testCaseShareFolder link start =====

    @GET
    @Path("/{testCaseId}/testCaseShareFolders")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取TestCase关联的TestCaseShareFolder", response = MeowlomoResponse.class, httpMethod = "GET")
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
    public MeowlomoResponse getTestCaseShareFolderByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get testCaseShareFolders by testCase id = " + testCaseId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                List<TestCaseShareFolder> records = testCaseShareFolderTestCaseLinkService
                        .listTestCaseShareFolderByTestCasePrimaryKey(testCaseId);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                List<TestCaseShareFolder> targetRecords = testCaseShareFolderTestCaseLinkService.listTestCaseShareFolderByTestCasePrimaryKey(testCaseId);
                // empty just return
                if (targetRecords.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                List<Long> targetIds = new ArrayList<Long>();
                TestCaseShareFolderExample.Criteria criteria = null;
                for (TestCaseShareFolder record : targetRecords) {
                    targetIds.add(record.getId());
                }
                criteria = new TestCaseShareFolderExample().createCriteria();
                criteria.andIdIn(targetIds);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseShareFolderExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria, TestCaseShareFolderExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<TestCaseShareFolder> finalRecords = testCaseShareFolderService
                        .selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseShareFolderService.countByExample(example));
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ===== testCase testCaseShareFolder link end =====

    // =====testCase project link start =====

    @GET
    @Path("/{testCaseId}/projects")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取TestCase关联的Project", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name (case-insensitive)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "log", value = "log (case-insensitive)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "name (case-insensitive)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "project type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "order by string", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getProjectByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get projects by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的Driver不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                Project records = projectService.selectByPrimaryKey(checkRecord.getProjectId());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records == null ? 0 : 1);
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                ProjectExample.Criteria criteria = new ProjectExample().createCriteria();
                criteria.andIdEqualTo(checkRecord.getProjectId());
                ProjectExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        ProjectExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<Project> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = projectReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = projectService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", projectService.countByExample(example));
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====testCase project link end =====

    // ===== testCase runSet link start =====

    @GET
    @Path("/{testCaseId}/runSets")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取TestCase关联的RunSet", response = MeowlomoResponse.class, httpMethod = "GET")
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
    public MeowlomoResponse getRunSetByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get runSets by testCase id = " + testCaseId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                List<RunSet> records = testCaseRunSetLinkService.listRunSetByTestCasePrimaryKey(testCaseId);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                List<RunSet> targetRecords = testCaseRunSetLinkService.listRunSetByTestCasePrimaryKey(testCaseId);
                // empty just return
                if (targetRecords.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                List<Long> targetIds = new ArrayList<Long>();
                RunSetExample.Criteria criteria = null;
                for (RunSet record : targetRecords) {
                    targetIds.add(record.getId());
                }
                criteria = new RunSetExample().createCriteria();
                criteria.andIdIn(targetIds);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                RunSetExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        RunSetExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<RunSet> finalRecords = runSetService.selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", runSetService.countByExample(example));
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ===== testCase runSet link end =====

    // =====testCase task link start =====

    /**
     * Gets the task by test case primary key.
     *
     * @param testCaseId
     *            the test case id
     * @return the task by test case primary key
     */
    @GET
    @Path("/{testCaseId}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取关联TestCase的Task", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse getTaskByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) {
        logger.info("received get tasks by applciation id=" + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            TestCaseTaskLinkExample linkExample = new TestCaseTaskLinkExample();
            linkExample.or().andTestCaseIdEqualTo(testCaseId);
            List<TestCaseTaskLink> links = testCaseTaskLinkService.selectByExample(linkExample);
            if (links.isEmpty()) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            // coverte to uuids
            List<UUID> uuids = new ArrayList<UUID>();
            for (TestCaseTaskLink link : links) {
                uuids.add(link.getTaskUuid());
            }
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", links.size());
            return new MeowlomoResponse(metadata, uuids, null);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @POST
    @LogUserActivity
    @Path("/{testCaseId}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加Task到TestCase", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
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
    public MeowlomoResponse batchInsertTaskByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId, UUID[] records) {
        logger.info("received get tasks by applciation id=" + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }

            List<UUID> errorIndex = new ArrayList<UUID>();
            List<Long> ids = new ArrayList<Long>();
            for (UUID uuid : records) {
                TestCaseTaskLink link = new TestCaseTaskLink();
                link.setTaskUuid(uuid);
                link.setTestCaseId(testCaseId);
                link.setTaskUuid(checkRecord.getUuid());
                long insertResult = testCaseTaskLinkService.insert(link);
                if (insertResult != 1) {
                    errorIndex.add(uuid);
                }
                else {
                    ids.add(link.getId());
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
                TestCaseTaskLinkExample example = new TestCaseTaskLinkExample();
                example.or().andIdIn(ids);
                List<TestCaseTaskLink> finalRecords = testCaseTaskLinkService.selectByExample(example);
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
            logger.error("Class:{},", this.getClass().getName(), ex);
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
    // =====testCase task link end =====

    // =====testCase driverPack link start =====

    @GET
    @Path("/{testCaseId}/driverPacks")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCase的DriverPack", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "ATM Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "ATM Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "ATM Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "ATM Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "DriverPack创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "DriverPack创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getDriverPackByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get driverPacks by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkTestCaseRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkTestCaseRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                List<DriverPack> finalReturnRecords = driverPackUtilService.getDriverPackByTestCaseId(testCaseId);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                  // List<DriverPack> links =
                List<DriverPack> links = driverPackUtilService.getAllDriverPackByTestCaseId(testCaseId);
                List<Long> driverPackTargetIds = new ArrayList<Long>();
                for (DriverPack link : links) {
                    driverPackTargetIds.add(link.getId());
                }
                if (driverPackTargetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                DriverPackExample.Criteria criteria = null;
                criteria = new DriverPackExample().createCriteria();
                criteria.andIdIn(driverPackTargetIds);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                DriverPackExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        DriverPackExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<DriverPack> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = driverPackReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = driverPackService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", driverPackService.countByExample(example));
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
    // =====testCase driverPack link end =====

    // =====testCase executionDriverMap link start =====

    @GET
    @Path("/{testCaseId}/driverTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCase的ExecutionDriverMap", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse getDriverTypeByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get executionDriverMaps by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkTestCaseRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkTestCaseRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<DriverType> finalReturnRecords = driverTypeUtilService.getDriverTypeByTestCaseId(testCaseId);
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", finalReturnRecords.size());
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
    // =====testCase executionDriverMap link end =====

    // =====testCase instructionOverwrite link start =====

    @GET
    @Path("/{testCaseId}/instructionOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCase的InstructionOverwrite", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse getInstructionOverwriteByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get instructionOverwrites by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkTestCaseRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkTestCaseRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<InstructionOverwrite> finalReturnRecords = instructionOverwriteUtilService
                    .generateInstructionOverwritesForTestCase(testCaseId);
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", finalReturnRecords.size());
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
    // =====testCase instructionOverwrite link end =====

    // ===== testCase testCaseOverwrite link start =====

    @GET
    @Path("/{testCaseId}/testCaseOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCase的TestCaseOverwrite", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "TestCaseOverwrite 名字", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "TestCaseOverwrite 类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "TestCaseOverwrite 创建起始时间 [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "TestCaseOverwrite 创建终结时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getTestCaseOverwriteByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get testCaseOverwrites by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                TestCaseOverwriteExample recordExample = new TestCaseOverwriteExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId).andDeletedEqualTo(false);
                List<TestCaseOverwrite> records = testCaseOverwriteService.selectByExample(recordExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                TestCaseOverwriteExample.Criteria criteria = null;
                criteria = new TestCaseOverwriteExample().createCriteria();
                criteria.andTestCaseIdEqualTo(testCaseId);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseOverwriteExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseOverwriteExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<TestCaseOverwrite> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = testCaseOverwriteReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = testCaseOverwriteService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseOverwriteService.countByExample(example));
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
    @Path("/{testCaseId}/testCaseOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加多个TestCaseOverwrite到TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加TestCaseOverwrite到TestCase操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path") })
    public MeowlomoResponse insertTestCaseOverwriteAndLinkToTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            TestCaseOverwrite[] records) throws Exception {
        logger.info("received post testCaseOverwrite by testCase id=" + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add the id to the record
            List<TestCaseOverwrite> entries = new ArrayList<TestCaseOverwrite>();
            for (TestCaseOverwrite entry : records) {
                entry.setTestCaseId(testCaseId);
                entries.add(entry);
            }
            List<Long> insertedResult = testCaseOverwriteService.insertRecordsSelective(entries);
            if (insertedResult.size() == records.length) {
                TestCaseOverwriteExample example = new TestCaseOverwriteExample();
                example.or().andIdIn(insertedResult);
                List<TestCaseOverwrite> finalRecords = testCaseOverwriteService.selectByExample(example);
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
                String trace = "exception UUID=" + exuuid + " couldn't execute insert.";
                String code = ERROR_TYPE + "02POS";
                String message = "添加TestCaseOverwrite到TestCase操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
    @Path("/{testCaseId}/testCaseOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到TestCase的TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "删除TestCase关联的TestCaseOverwrite操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "TestCaseOverwrite IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse deleteTestCaseOverwritesFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received delete testCaseOverwrites by testCase id=" + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
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
            TestCaseOverwriteExample recordExample = new TestCaseOverwriteExample();
            recordExample.or().andDeletedEqualTo(false).andIdIn(idsList);
            List<TestCaseOverwrite> finalRecords = testCaseOverwriteService.selectByExample(recordExample);
            int deleteResult = testCaseOverwriteService.logicalDeleteByExample(recordExample);
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
                String message = "删除TestCase关联的TestCaseOverwrite操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    @Path("/{testCaseId}/testCaseOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新关联到TestCase的TestCaseOverwrite", response = MeowlomoResponse.class, httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部更新失败，失败序列=>\" + errorIndex.toString() + \" 无关联序列 =>\" + noLinkedIndex.toString()+ \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更新内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分更新请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "TestCaseOverwrite IDs", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse updateTestCaseOverwritesFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId,
            TestCaseOverwrite[] records) throws Exception {
        logger.info("received patch testCaseOverwrites by testCase id = " + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
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
                TestCaseOverwrite record = records[i];
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
                    TestCaseOverwrite record = records[i];
                    TestCaseOverwriteExample example = new TestCaseOverwriteExample();
                    example.or().andDeletedEqualTo(false).andTestCaseIdEqualTo(testCaseId).andIdEqualTo(record.getId());
                    if (testCaseOverwriteService.countByExample(example) == 1) {
                        int updateResult = testCaseOverwriteService.updateByPrimaryKeySelective(record);
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
    // =====testCase testCaseOverwrite link end =====

    // ===== testCase module link start =====

    @GET
    @Path("/{testCaseId}/modules")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取TestCase关联的Module", response = MeowlomoResponse.class, httpMethod = "GET")
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
    public MeowlomoResponse getModuleByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get modules by testCase id = " + testCaseId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                List<Module> records = testCaseModuleLinkService.listModuleByTestCasePrimaryKey(testCaseId);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                List<Module> targetRecords = testCaseModuleLinkService.listModuleByTestCasePrimaryKey(testCaseId);
                // empty just return
                if (targetRecords.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                List<Long> targetIds = new ArrayList<Long>();
                ModuleExample.Criteria criteria = null;
                for (Module record : targetRecords) {
                    targetIds.add(record.getId());
                }
                criteria = new ModuleExample().createCriteria();
                criteria.andIdIn(targetIds);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                ModuleExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        ModuleExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<Module> finalRecords = moduleService.selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", moduleService.countByExample(example));
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ===== testCase module link end =====

    // ===== testCase tag link start =====

    @POST
    @LogUserActivity
    @Path("/{testCaseId}/tags")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个Tag实体并链接到TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加Tag并连接到TestCase操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertTagAndLinkToTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId, Tag[] records)
            throws Exception {
        logger.info("received post tag by testCase id=" + testCaseId);
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
            TestCase checkTestCaseRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkTestCaseRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<Long> insertedResult = tagService.insertRecordsSelective(Arrays.asList(records));
            if (insertedResult.size() == records.length) {
                // add link
                for (Long id : insertedResult) {
                    TestCaseTagLink link = new TestCaseTagLink();
                    link.setTagId(id);
                    link.setTestCaseId(testCaseId);
                    testCaseTagLinkService.insert(link);
                }
                TagExample example = new TagExample();
                example.or().andIdIn(insertedResult);
                List<Tag> finalRecords = tagService.selectByExample(example);
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
                String message = "添加Tag并连接到TestCase操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
    @Path("/{testCaseId}/tags")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于TestCase的Tag", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Tag Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "Tag Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "Tag Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "Tag Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "Tag创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "Tag创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getTagByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get tags by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkTestCaseRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkTestCaseRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                TestCaseTagLinkExample linkExample = new TestCaseTagLinkExample();
                linkExample.or().andTestCaseIdEqualTo(testCaseId);
                List<TestCaseTagLink> links = testCaseTagLinkService.selectByExample(linkExample);
                List<Long> targetIds = new ArrayList<Long>();
                for (TestCaseTagLink link : links) {
                    targetIds.add(link.getTagId());
                }
                if (targetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                TagExample tagExample = new TagExample();
                tagExample.or().andIdIn(targetIds);
                List<Tag> finalReturnRecords = tagService.selectByExample(tagExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                TestCaseTagLinkExample linkExample = new TestCaseTagLinkExample();
                linkExample.or().andTestCaseIdEqualTo(testCaseId);
                List<TestCaseTagLink> links = testCaseTagLinkService.selectByExample(linkExample);
                List<Long> tagTargetIds = new ArrayList<Long>();
                for (TestCaseTagLink link : links) {
                    tagTargetIds.add(link.getTagId());
                }
                if (tagTargetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                TagExample.Criteria criteria = null;
                criteria = new TagExample().createCriteria();
                criteria.andIdIn(tagTargetIds);
                TagExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria, TagExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<Tag> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = tagReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = tagService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", tagService.countByExample(example));
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
    @Path("/{testCaseId}/tags")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个Tag实体并链接到TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + testCaseId + \"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加Tag并连接到TestCase操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutTagToTestCase(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseId") Long testCaseId, Tag[] records) throws Exception {
        logger.info("received post tag by testCase id=" + testCaseId);
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
            TestCase checkTestCaseRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkTestCaseRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<Tag> finalRecords = new ArrayList<Tag>();
            for (int i = 0; i < records.length; i++) {
                Tag record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = tagService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // insert link
                        TestCaseTagLink link = new TestCaseTagLink();
                        link.setTagId(record.getId());
                        link.setTestCaseId(testCaseId);
                        if (testCaseTagLinkService.insertSelective(link) == 1) {
                            finalRecords.add(record);
                        }
                        else {
                            errorIndex.add((long) i);
                        }
                    }
                }
                else {
                    if (record.getName() == null) {
                        record.setName(tagService.selectByPrimaryKey(record.getId()).getName());
                    }
                    int updateResult = tagService.updateByPrimaryKeySelective(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // check link exist
                        TestCaseTagLinkExample linkExample = new TestCaseTagLinkExample();
                        linkExample.or().andTagIdEqualTo(record.getId()).andTestCaseIdEqualTo(testCaseId);
                        long countResult = testCaseTagLinkService.countByExample(linkExample);
                        if (countResult == 1) {
                            finalRecords.add(tagService.selectByPrimaryKey(record.getId()));
                        }
                        else {
                            // add the link
                            TestCaseTagLink link = new TestCaseTagLink();
                            link.setTagId(record.getId());
                            link.setTestCaseId(testCaseId);
                            if (testCaseTagLinkService.insertSelective(link) == 1) {
                                finalRecords.add(tagService.selectByPrimaryKey(record.getId()));
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
    @Path("/{testCaseId}/tags")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到TestCase的Tag的链接", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除TestCase关联的Tag操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的Tag关联到此TestCase，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+testCaseId+\"的TestCase不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此TestCase的Tag。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testCaseId", value = "TestCase ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "Tag IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkTagFromTestCase(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received post tag by testCase id=" + testCaseId);
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
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
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
            TestCaseTagLinkExample linkExample = new TestCaseTagLinkExample();
            linkExample.or().andTestCaseIdEqualTo(testCaseId).andTagIdIn(idsList);
            long linkCount = testCaseTagLinkService.countByExample(linkExample);
            if (linkCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "输入中存在未关联到此TestCase的Tag。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            // start the delete
            TagExample recordExample = new TagExample();
            recordExample.or().andIdIn(idsList);
            List<Tag> finalRecords = tagService.selectByExample(recordExample);
            int deleteResult = testCaseTagLinkService.deleteByExample(linkExample);
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
                String message = "存在已删除的Tag关联到此TestCase，数据不一致。请与管理员联系。并提供唯一码[" + exuuid + "]";
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
                String message = "删除TestCase关联的Tag操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    // =====testCase tag link end =====
    
    
    // =====testCase runExecutionInfo link end =====
    @GET
    @Path("/{testCaseId}/runExecutionInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于Instrcution的Run", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
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
    public MeowlomoResponse getRunExecutionInfoByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get run execution info by testCase id=" + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// not example
                RunExecutionInfoExample recordExample = new RunExecutionInfoExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId);
                List<RunExecutionInfo> records = runExecutionInfoService.selectByExample(recordExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {// with query parameters
                RunExecutionInfoExample recordExample = new RunExecutionInfoExample();
                recordExample.or().andTestCaseIdEqualTo(testCaseId);
                List<RunExecutionInfo> records = runExecutionInfoService.selectByExample(recordExample);
                // empty just return
                if (records.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }

                // not empty
                RunExecutionInfoExample.Criteria criteria = null;
                // get the ids with the
                List<Long> targetIds = new ArrayList<Long>();
                for (RunExecutionInfo record : records) {
                    targetIds.add(record.getRunId());
                }
                criteria = new RunExecutionInfoExample().createCriteria();
                criteria.andRunIdIn(targetIds);
                RunExecutionInfoExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria, RunExecutionInfoExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<RunExecutionInfo> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = runExecutionInfoReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = runExecutionInfoService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", runExecutionInfoService.countByExample(example));
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====testCase runExecutionInfo link end =====
    
    @GET
    @Path("/{testCaseId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取TestCase关联的TestCase", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name (case-insensitive)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "log", value = "log (case-insensitive)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "name (case-insensitive)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "testCase type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "order by string", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getTestCaseByTestCasePrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received get testCases by testCase id = " + testCaseId);
        try {
            // get the record first
            TestCase checkRecord = testCaseService.selectByPrimaryKey(testCaseId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的Driver不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<TestCase> records = testCaseUtilService.getTestCaseReferencedBy(testCaseId);
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", records.size());
            return new MeowlomoResponse(metadata, records, null);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
}
