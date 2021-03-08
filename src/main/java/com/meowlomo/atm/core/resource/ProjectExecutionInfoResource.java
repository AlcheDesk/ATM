package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.model.ProjectExecutionInfo;
import com.meowlomo.atm.core.model.ProjectExecutionInfoExample;
import com.meowlomo.atm.core.model.TestCaseExecutionInfo;
import com.meowlomo.atm.core.model.TestCaseExecutionInfoExample;
import com.meowlomo.atm.core.model.TestCaseExecutionInfoExample.Criteria;
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
import com.meowlomo.atm.core.service.base.ProjectExecutionInfoService;
import com.meowlomo.atm.core.service.base.TestCaseExecutionInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/projectExecutionInfo")
@Api(value = "ProjectExecutionInfo resources", produces = "application/json")
public class ProjectExecutionInfoResource {

    private final Logger logger = LoggerFactory.getLogger(ProjectExecutionInfoResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "PEI";

    @Autowired
    private ProjectExecutionInfoService projectExecutionInfoService;

    @Autowired
    private TestCaseExecutionInfoService testCaseExecutionInfoService;

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
    @ApiOperation(value = "读取ProjectExecutionInfo", response = MeowlomoResponse.class, httpMethod = "GET")
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
        logger.info("received ProjectExecutionInfo select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                ProjectExecutionInfoExample example = new ProjectExecutionInfoExample();
                example.or().andProjectIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", projectExecutionInfoService.countByExample(example));
                List<ProjectExecutionInfo> records = projectExecutionInfoService.selectByExampleWithRowbounds(example,
                        rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                ProjectExecutionInfoExample.Criteria criteria = null;
                ProjectExecutionInfoExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        ProjectExecutionInfoExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", projectExecutionInfoService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                ProjectExecutionInfoExample.Criteria criteria = null;
                ProjectExecutionInfoExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        ProjectExecutionInfoExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", projectExecutionInfoService.countByExample(example));
                List<ProjectExecutionInfo> records = null;
                records = projectExecutionInfoService.selectByExampleWithRowbounds(example, rowBounds);
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
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个ProjectExecutionInfo", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "ProjectExecutionInfo id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("projectId") Long projectId) {
        logger.info("received ProjectExecutionInfo select by id call");
        try {
            ProjectExecutionInfo selectRecord = null;
            selectRecord = projectExecutionInfoService.selectByPrimaryKey(projectId);
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

    @GET
    @Path("/{projectId}/testCaseExecutionInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于Project的TestCase运行结果", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "project status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse getTestCaseExecutionInfoByProjectId(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("projectId") Long projectId)
            throws ServerErrorException {
        logger.info("received get testCase result report call");
        try {
            // get the record first
            ProjectExecutionInfo checkRecord = projectExecutionInfoService.selectByPrimaryKey(projectId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "project id为" + projectId + "的project execution info不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                List<Long> testCaseIds = new ArrayList<Long>(checkRecord.getTestCaseIds());
                TestCaseExecutionInfoExample testCaseExecutionInfoExample = new TestCaseExecutionInfoExample();
                Criteria criteria = testCaseExecutionInfoExample.createCriteria();
                if (!testCaseIds.isEmpty()) {
                    criteria.andTestCaseIdIn(testCaseIds);
                    RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                    List<TestCaseExecutionInfo> testCaseExecutionInfos = testCaseExecutionInfoService
                            .selectByExampleWithRowbounds(testCaseExecutionInfoExample, rowBounds);
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", testCaseIds.size());
                    return new MeowlomoResponse(metadata, testCaseExecutionInfos, null);
                }
                else {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", testCaseIds.size());
                    return new MeowlomoResponse(metadata, null, null);
                }
            }
            else {
                List<Long> testCaseIds = new ArrayList<>(checkRecord.getTestCaseIds());
                TestCaseExecutionInfoExample testCaseExecutionInfoExample = new TestCaseExecutionInfoExample();
                Criteria criteria = testCaseExecutionInfoExample.createCriteria();
                if (!testCaseIds.isEmpty()) {
                    criteria.andTestCaseIdIn(testCaseIds);
                }
                TestCaseExecutionInfoExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseExecutionInfoExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<TestCaseExecutionInfo> testCaseExecutionInfos = testCaseExecutionInfoService
                        .selectByExampleWithRowbounds(example, rowBounds);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseExecutionInfoService.countByExample(example));
                return new MeowlomoResponse(metadata, testCaseExecutionInfos, null);
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
