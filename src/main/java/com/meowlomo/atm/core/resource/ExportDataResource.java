package com.meowlomo.atm.core.resource;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.annotation.LogUserActivity;
import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.TestCase;
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
import com.meowlomo.atm.core.service.filter.RunSetContentFilterService;
import com.meowlomo.atm.core.service.filter.TestCaseContentFilterService;
import com.meowlomo.atm.core.service.referrence.ProjectFullReferenceService;
import com.meowlomo.atm.core.service.referrence.RunSetFullReferenceService;
import com.meowlomo.atm.core.service.referrence.RunSetResultFullReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/export")
@Api(value = "export resources", produces = "application/json")
public class ExportDataResource {

    private final Logger logger = LoggerFactory.getLogger(ExportDataResource.class);

    @Value("${meowlomo.atm.export.send-run-set-size:10}")
    private long MAX_RUN_SET_SIZE;
    @Value("${meowlomo.atm.export.send-test-case-size:200}")
    private long MAX_TEST_CASE_SIZE;

    private static final String ERROR_TYPE = "EXPT";

    @Autowired
    private TestCaseReferenceService testCaseReferenceService;

    @Autowired
    private TestCaseContentFilterService testCaseContentFilterService;

    @Autowired
    private RunSetFullReferenceService runSetFullReferenceService;

    @Autowired
    private ProjectFullReferenceService projectFullReferenceService;

    @Autowired
    private RunSetContentFilterService runSetContentFilterService;

    @Autowired
    private RunSetResultFullReferenceService runSetResultFullReferenceService;

    @GET
    @LogUserActivity
    @Path("testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行单个TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "testCase ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse exportTestCase(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received export testCase call,testCaseId=" + testCaseId);
        try {
            TestCase testCaseRef = testCaseReferenceService.selectByPrimaryKey(testCaseId);
            if (testCaseRef == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + testCaseId + "的TestCase不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // check if the test case is empty
            if (testCaseRef.getInstructions().isEmpty()) { return new MeowlomoResponse(null, null, null); }

            TestCase expandedRecord = testCaseContentFilterService
                    .expandTestCaseWithReferencedInstructions(testCaseRef);
            if (expandedRecord != null) {
                logger.debug(" test case was created for test case export for test case id:{}, name:{}",
                        testCaseRef.getId(), testCaseRef.getName());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, expandedRecord, null);
            }
            else {
                // throw new ResourceNotFoundException(ERROR_TYPE);
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + testCaseId + " does not exists.";
                String message = "无法建立执行命令，testCaseId为" + testCaseId + "的对象，无法冻结。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03F";
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
            String message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @LogUserActivity
    @Path("runSets/{runSetId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行单个RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "RunSet ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse exportRunSet(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received export runSet call,runSetId=" + runSetId);
        try {
            RunSet runSetRef = runSetFullReferenceService.selectByPrimaryKey(runSetId);
            if (runSetRef == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // check if the test case is empty
            if (runSetRef.getTestCases().isEmpty()) { return new MeowlomoResponse(null, null, null); }

            RunSet expandedRecord = runSetContentFilterService.expandReferenecingRunSetTestCaseLinkForRunSet(runSetRef);
            if (expandedRecord != null) {
                logger.debug(" test case was created for test case export for test case id:{}, name:{}",
                        runSetRef.getId(), runSetRef.getName());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, expandedRecord, null);
            }
            else {
                // throw new ResourceNotFoundException(ERROR_TYPE);
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + runSetId + " does not exists.";
                String message = "无法建立执行命令，runSetId为" + runSetId + "的对象，无法冻结。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03F";
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

    @GET
    @LogUserActivity
    @Path("projects/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行单个Project", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "project ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse exportProject(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("projectId") Long projectId) throws Exception {
        logger.info("received export project call,projectId=" + projectId);
        try {
            Project projectRef = projectFullReferenceService.selectByPrimaryKey(projectId);
            if (projectRef == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + projectId + "的Project不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // check if the test case is empty
            if (projectRef.getTestCases().isEmpty()) { return new MeowlomoResponse(null, null, null); }

            // Project expandedRecord =
            // projectContentFilterService.expandProjectWithReferencedTestCases(projectRef);
            logger.debug(" test case was created for test case export for test case id:{}, name:{}", projectRef.getId(),
                    projectRef.getName());
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", 1);
            return new MeowlomoResponse(metadata, projectRef, null);
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @LogUserActivity
    @Path("runSetResults/{runSetResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行单个RunSetResult", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "RunSetResult ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse exportRunSetResult(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("runSetResultId") Long runSetResultId) throws Exception {
        logger.info("received export runSetResult call,runSetResultId=" + runSetResultId);
        try {
            RunSetResult runSetResult = runSetResultFullReferenceService.selectByPrimaryKey(runSetResultId);
            if (runSetResult == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetResultId + "的RunSetResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            else {
                logger.debug(" run set result was created for test case export for test case id:{}, name:{}",
                        runSetResult.getId(), runSetResult.getName());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, runSetResult, null);
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
}
