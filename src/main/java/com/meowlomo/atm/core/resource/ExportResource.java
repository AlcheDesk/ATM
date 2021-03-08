package com.meowlomo.atm.core.resource;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
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
import com.meowlomo.atm.core.service.exportor.ProjectExportor;
import com.meowlomo.atm.core.service.exportor.TestCaseExportor;
import com.meowlomo.atm.core.service.referrence.ProjectReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseFullReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/download")
@Api(value = "export resources", produces = "application/json")
public class ExportResource {

    private final Logger logger = LoggerFactory.getLogger(ExportResource.class);

    private static final String ERROR_TYPE = "EXP";

    @Autowired
    private TestCaseExportor testCaseExportor;

    @Autowired
    private ProjectExportor projectExportor;

    @Autowired
    private TestCaseFullReferenceService testCaseFullReferenceService;

    @Autowired
    private ProjectReferenceService projectReferenceService;

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    @Autowired
    private TestCaseReferenceService testCaseReferenceService;

    @GET
    @Path("/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "导出TestCase", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
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
    public Response downloadExcelTestCase(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseId") Long testCaseId) throws Exception {
        logger.info("received download testCase call,testCaseId=" + testCaseId);
        try {
            TestCase testCase = testCaseFullReferenceService.selectByPrimaryKey(testCaseId);
            if (testCase != null) {
                // convert the test case the excel file
                String fileName = "测试用例_" + testCase.getName() + "_"
                        + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx";
                Response response = null;
                ByteArrayOutputStream fileStream = testCaseExportor.exportTestCaseToExcel(testCase);
                // Retrieve the file
                ResponseBuilder builder = Response.ok(fileStream.toByteArray(), MediaType.APPLICATION_OCTET_STREAM);
                String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
                builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
                response = builder.build();
                return response;
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with testCaseId = " + testCaseId
                        + " does not exists.";
                String message = "不存在testCaseId为" + testCaseId + "的对象，无法读取。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "05F";
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
            ex.printStackTrace();
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @Path("/projects/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "导出Project", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "task priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "task type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "task UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "task status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public Response downloadExcelTestCases(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("projectId") Long projectId) throws Exception {
        logger.info("received download project call,projectId=" + projectId);
        try {
            Project project = projectReferenceService.selectByPrimaryKey(projectId);
            if (project != null) {
                // convert the test case the excel file
                String fileName = "项目_" + project.getName() + "_"
                        + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx";
                Response response = null;
                ByteArrayOutputStream fileStream = projectExportor.exportProjectToExcel(project);
                // Retrieve the file
                ResponseBuilder builder = Response.ok(fileStream.toByteArray(), MediaType.APPLICATION_OCTET_STREAM);
                String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
                builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
                response = builder.build();
                return response;
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with testCaseId = " + projectId
                        + " does not exists.";
                String message = "不存在testCaseId为" + projectId + "的对象，无法读取。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "05F";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException |

                CustomServiceUnavailableException ex) {
            throw ex;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // 导出所有测试用例
    @GET
    @Path("/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "导出TestCases", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "task priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "task type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "task UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "task status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public Response downloadAllTestCases(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("projectId") Long projectId) throws Exception {
        logger.info("received download project call,projectId=" + projectId);
        try {
            TestCaseExample example = new TestCaseExample();
            example.or().andDeletedEqualTo(false).andIdIsNotNull();
            RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
            List<TestCase> testCases = testCaseReferenceService.selectByExampleWithRowbounds(example, rowBounds);
            if (testCases != null) {
                // convert the test case the excel file
                String fileName = "测试用例_" + uriInfo.toString() + "_"
                        + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx";
                Response response = null;
                ByteArrayOutputStream fileStream = testCaseExportor.exportTestCasesToExcel(testCases);

                // Retrieve the file
                ResponseBuilder builder = Response.ok(fileStream.toByteArray(), MediaType.APPLICATION_OCTET_STREAM);
                String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
                builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
                response = builder.build();
                return response;
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with testCaseId = " + projectId
                        + " does not exists.";
                String message = "不存在testCaseId为" + projectId + "的对象，无法读取。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "05F";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException |

                CustomServiceUnavailableException ex) {
            throw ex;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
}
