package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackDriverLink;
import com.meowlomo.atm.core.model.DriverPackDriverLinkExample;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;
import com.meowlomo.atm.core.model.ProjectExample;
import com.meowlomo.atm.core.model.RunSetAliasLink;
import com.meowlomo.atm.core.model.RunSetAliasLinkExample;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseTagLink;
import com.meowlomo.atm.core.model.TestCaseTagLinkExample;
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
import com.meowlomo.atm.core.service.base.DriverPackDriverLinkService;
import com.meowlomo.atm.core.service.base.DriverPackService;
import com.meowlomo.atm.core.service.base.InstructionOverwriteService;
import com.meowlomo.atm.core.service.base.InstructionService;
import com.meowlomo.atm.core.service.base.ProjectService;
import com.meowlomo.atm.core.service.base.RunSetAliasLinkService;
import com.meowlomo.atm.core.service.base.RunSetService;
import com.meowlomo.atm.core.service.base.RunSetTestCaseLinkService;
import com.meowlomo.atm.core.service.base.TestCaseService;
import com.meowlomo.atm.core.service.base.TestCaseTagLinkService;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.TestCaseUtilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/count")
@Api(value = "count resources", produces = "application/json")
public class CountResource {

    private final Logger logger = LoggerFactory.getLogger(CountResource.class);

    private static final String ERROR_TYPE = "COUT";

    @Value("${meowlomo.atm.test-case.reference-layer-max:15}")
    private int testCaseReferenceLayerMax;

    @Autowired
    private DataNameUtilService dataNameUtilService;

    @Autowired
    private InstructionService instructionService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DriverPackService driverPackService;

    @Autowired
    private TestCaseTagLinkService testCaseTagLinkService;

    @Autowired
    private InstructionOverwriteService instructionOverwriteService;

    @Autowired
    private RunSetTestCaseLinkService runSetTestCaseLinkService;

    @Autowired
    private RunSetService runSetService;

    @Autowired
    private RunSetAliasLinkService runSetAliasLinkService;

    @Autowired
    private DriverPackDriverLinkService driverPackDriverLinkService;

    @Autowired
    private TestCaseUtilService testCaseUtilService;

    @GET
    @Path("apiSchemas")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取apiSchema数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countApiSchema(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received apiSchema count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long apiSchemaCount = 0;
            String apiSchemaName = queryParams.getFirst("name");
            apiSchemaCount = dataNameUtilService.checkApiSchemaNameBeforeInsert(apiSchemaName);
            result.put("count", apiSchemaCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("applications")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取application名称数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "projectId", value = "projectId", required = true, dataType = "long", paramType = "query") })
    public MeowlomoResponse countApplication(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received application count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long applicationCount = 0;
            String applicationName = queryParams.getFirst("name");
            if (applicationName != null && queryParams.getFirst("projectId") != null) {
                Long projectId = Long.valueOf(queryParams.getFirst("projectId"));
                applicationCount = dataNameUtilService.checkApplicationNameForProjectBeforeInsert(projectId,
                        applicationName);
            }
            result.put("count", applicationCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("drivers")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取driver数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countDriver(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received driver count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            if (queryParams.containsKey("relation") && queryParams.getFirst("driverId") != null) {
                Long driverId = Long.valueOf(queryParams.getFirst("driverId"));
                DriverPackDriverLinkExample driverPackDriverLinkExample = new DriverPackDriverLinkExample();
                driverPackDriverLinkExample.createCriteria().andDriverIdEqualTo(driverId);
                List<DriverPackDriverLink> driverPackDriverLinks = driverPackDriverLinkService
                        .selectByExample(driverPackDriverLinkExample);
                Set<Long> driverPackIds = new HashSet<Long>();
                for (int i = 0; i < driverPackDriverLinks.size(); i++) {
                    Long driverPackId = driverPackDriverLinks.get(i).getDriverPackId();
                    driverPackIds.add(driverPackId);
                }
                List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
                long count = 0;
                for (Long driverPackId : driverPackIds) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    DriverPack driverPack = driverPackService.selectByPrimaryKey(driverPackId);
                    map.put("driverPackName", driverPack.getName());
                    map.put("isDeleted", driverPack.getDeleted());
                    records.add(map);
                    count++;
                }
                result.put("count", count);
                return new MeowlomoResponse(result, records, null);
            }
            long driverCount = 0;
            String driverName = queryParams.getFirst("name");
            if (driverName != null) {
                driverCount = dataNameUtilService.checkDriverNameBeforeInsert(driverName);
            }
            result.put("count", driverCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("driverPacks")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取driverPack数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countDriverPack(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received driverPack count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long driverPackCount = 0;
            String driverPackName = queryParams.getFirst("name");
            driverPackCount = dataNameUtilService.checkDriverPackNameBeforeInsert(driverPackName);
            result.put("count", driverPackCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("driverProperties")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取driverProperty数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "driverVenderId", value = "driverVenderId", required = true, dataType = "long", paramType = "query") })
    public MeowlomoResponse countDriverProperty(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received driverProperty count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long driverPropertyCount = 0;
            String driverPropertyName = queryParams.getFirst("name");
            if (driverPropertyName != null && queryParams.getFirst("driverVenderId") != null) {
                Long driverVenderId = Long.valueOf(queryParams.getFirst("driverVenderId"));
                driverPropertyCount = dataNameUtilService
                        .checkDriverPropertyNameForDriverVendorBeforeInsert(driverVenderId, driverPropertyName);
            }
            result.put("count", driverPropertyCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("elements")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取element名称数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "projectId", value = "projectId", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "sectionId", value = "sectionId", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse countElement(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received element count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            if (queryParams.containsKey("relation") && queryParams.getFirst("elementId") != null) {
                Long elementId = Long.valueOf(queryParams.getFirst("elementId"));
                InstructionExample instructionExample = new InstructionExample();
                instructionExample.createCriteria().andElementIdEqualTo(elementId).andDeletedEqualTo(false);
                List<Instruction> instructions = instructionService.selectByExample(instructionExample);
                Set<Long> testCaseIds = new HashSet<Long>();
                for (int i = 0; i < instructions.size(); i++) {
                    Long testCaseId = instructions.get(i).getTestCaseId();
                    testCaseIds.add(testCaseId);
                }
                List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
                long count = 0;
                for (Long testCaseId : testCaseIds) {
                    TestCase testCase = testCaseService.selectByPrimaryKey(testCaseId);
                    ProjectExample projectExample = new ProjectExample();
                    projectExample.createCriteria().andIdEqualTo(testCase.getProjectId());
                    String projectName = projectService.selectByExample(projectExample).get(0).getName();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("testCaseName", testCase.getName());
                    map.put("projectName", projectName);
                    map.put("reference", false);
                    map.put("isDeleted", testCase.getDeleted());
                    records.add(map);
                    count++;
                    // get the ref testCase
                    InstructionExample instructionRefExample = new InstructionExample();
                    instructionRefExample.createCriteria().andRefTestCaseIdEqualTo(testCaseId).andDeletedEqualTo(false);
                    List<Instruction> refInstructions = instructionService.selectByExample(instructionRefExample);
                    if (refInstructions.size() != 0) {
                        Long RefTestCaseId = refInstructions.get(0).getTestCaseId();
                        TestCase refTestCase = testCaseService.selectByPrimaryKey(RefTestCaseId);
                        ProjectExample refProjectExample = new ProjectExample();
                        refProjectExample.createCriteria().andIdEqualTo(refTestCase.getProjectId());
                        String refProjectName = projectService.selectByExample(refProjectExample).get(0)
                                .getName();
                        Map<String, Object> refMap = new HashMap<String, Object>();
                        refMap.put("instructionId", refInstructions.get(0).getId());
                        refMap.put("testCaseName", refTestCase.getName());
                        refMap.put("projectName", refProjectName);
                        refMap.put("reference", true);
                        records.add(refMap);
                        count++;
                    }
                }
                result.put("count", count);
                return new MeowlomoResponse(result, records, null);
            }
            long elementCount = 0;
            String elementName = queryParams.getFirst("name");
            if (elementName != null && queryParams.getFirst("projectId") != null) {
                Long projectId = Long.valueOf(queryParams.getFirst("projectId"));
                elementCount = dataNameUtilService.checkElementNameForProjectBeforeInsert(projectId, elementName);
            }
            if (elementName != null && queryParams.getFirst("sectionId") != null) {
                Long sectionId = Long.valueOf(queryParams.getFirst("sectionId"));
                elementCount = dataNameUtilService.checkElementNameForSectionBeforeInsert(sectionId, elementName);
            }
            if (queryParams.containsKey("instructionOverwrites") && queryParams.getFirst("elementId") != null) {
                Long elementId = Long.valueOf(queryParams.getFirst("elementId"));
                InstructionExample instructionExample = new InstructionExample();
                instructionExample.createCriteria().andElementIdEqualTo(elementId);
                List<Instruction> instructions = instructionService.selectByExample(instructionExample);
                List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
                long count = 0;
                for (Instruction instruction : instructions) {
                    long instructionId = instruction.getId();
                    InstructionOverwriteExample instructionOverwriteExample = new InstructionOverwriteExample();
                    instructionOverwriteExample.createCriteria().andInstructionIdEqualTo(instructionId);
                    List<InstructionOverwrite> instructionOverwrites = instructionOverwriteService
                            .selectByExample(instructionOverwriteExample);
                    for (InstructionOverwrite instructionOverwrite : instructionOverwrites) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("instructionOverwriteId", instructionOverwrite.getId());
                        records.add(map);
                        count++;
                    }
                }
                result.put("count", count);
                return new MeowlomoResponse(result, records, null);
            }
            result.put("count", elementCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("instructionActions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取instructionAction数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countInstructionAction(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received instructionAction count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long instructionActionCount = 0;
            String instructionActionName = queryParams.getFirst("name");
            instructionActionCount = dataNameUtilService.checkInstructionActionNameBeforeInsert(instructionActionName);
            result.put("count", instructionActionCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("elementLocatorTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取elementLocatorType数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countElementLocatorType(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received elementLocatorType count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long elementLocatorTypeCount = 0;
            String elementLocatorTypeName = queryParams.getFirst("name");
            elementLocatorTypeCount = dataNameUtilService
                    .checkElementLocatorTypeNameBeforeInsert(elementLocatorTypeName);
            result.put("count", elementLocatorTypeCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("elementTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取elementType数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countElementType(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received elementType count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long elementTypeCount = 0;
            String elementTypeName = queryParams.getFirst("name");
            elementTypeCount = dataNameUtilService.checkElementTypeNameBeforeInsert(elementTypeName);
            result.put("count", elementTypeCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("fileTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取fileType数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countFileType(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received fileType count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long fileTypeCount = 0;
            String fileTypeName = queryParams.getFirst("name");
            fileTypeCount = dataNameUtilService.checkFileTypeNameBeforeInsert(fileTypeName);
            result.put("count", fileTypeCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("groups")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取group数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countGroup(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received group count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long groupCount = 0;
            String groupName = queryParams.getFirst("name");
            groupCount = dataNameUtilService.checkGroupNameBeforeInsert(groupName);
            result.put("count", groupCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("instructions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取instruction名称数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "projectId", value = "projectId", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "sectionId", value = "sectionId", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse countInstruction(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received instruction count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long instructionCount = 0;
            if (queryParams.containsKey("relation") && queryParams.getFirst("instructionId") != null) {
                Long instructionId = Long.valueOf(queryParams.getFirst("instructionId"));
                InstructionOverwriteExample instructionOverwriteExample = new InstructionOverwriteExample();
                instructionOverwriteExample.createCriteria().andInstructionIdEqualTo(instructionId);
                List<InstructionOverwrite> instructionOverwrites = instructionOverwriteService
                        .selectByExample(instructionOverwriteExample);
                List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
                long count = 0;
                for (InstructionOverwrite instructionOverwrite : instructionOverwrites) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    long instructionOverwriteId = instructionOverwrite.getId();
                    map.put("instructionOverwriteId", instructionOverwriteId);
                    records.add(map);
                    count++;
                }
                result.put("count", count);
                return new MeowlomoResponse(result, records, null);
            }
            if (queryParams.getFirst("elementId") != null) {
                InstructionExample instructionExample = new InstructionExample();
                instructionExample.createCriteria()
                        .andElementIdEqualTo(Long.valueOf(queryParams.getFirst("elementId")));
                instructionCount = instructionService.countByExample(instructionExample);
                result.put("count", instructionCount);
                return new MeowlomoResponse(result, null, null);
            }
            result.put("count", instructionCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("instructionOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取instructionOption数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countInstructionOption(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received instructionOption count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long instructionOptionCount = 0;
            String instructionOptionName = queryParams.getFirst("name");
            instructionOptionCount = dataNameUtilService.checkInstructionOptionNameBeforeInsert(instructionOptionName);
            result.put("count", instructionOptionCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("instructionTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取instructionType数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countInstructionType(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received instructionType count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long instructionTypeCount = 0;
            String instructionTypeName = queryParams.getFirst("name");
            instructionTypeCount = dataNameUtilService.checkInstructionTypeNameBeforeInsert(instructionTypeName);
            result.put("count", instructionTypeCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("instructionOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取instructionOverwrite数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countInstructionOverwrite(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received instructionOverwrite count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long instructionOverwriteCount = 0;
            if (queryParams.getFirst("instructionId") != null) {
                Long instructionId = Long.valueOf(queryParams.getFirst("instructionId"));
                InstructionOverwriteExample instructionOverwriteExample = new InstructionOverwriteExample();
                instructionOverwriteExample.createCriteria().andInstructionIdEqualTo(instructionId);
                instructionOverwriteCount = instructionOverwriteService.selectByExample(instructionOverwriteExample)
                        .size();
            }
            if (queryParams.getFirst("elementId") != null) {
                Long elementId = Long.valueOf(queryParams.getFirst("elementId"));
                InstructionOverwriteExample instructionOverwriteExample = new InstructionOverwriteExample();
                instructionOverwriteExample.createCriteria().andElementIdEqualTo(elementId);
                instructionOverwriteCount = instructionOverwriteService.selectByExample(instructionOverwriteExample)
                        .size();
            }
            result.put("count", instructionOverwriteCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("projects")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取project数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countProject(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received project count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long projectCount = 0;
            String projectName = queryParams.getFirst("name");
            projectCount = dataNameUtilService.checkProjectNameBeforeInsert(projectName);
            result.put("count", projectCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("emailNotificationTargets")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取email数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countEmail(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received email count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long emailCount = 0;
            String emailAddress = queryParams.getFirst("emailAddress");
            emailCount = dataNameUtilService.checkEmailAddressBeforeInsert(emailAddress);
            result.put("count", emailCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("projectTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取projectType数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countProjectType(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received projectType count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long projectTypeCount = 0;
            String projectTypeName = queryParams.getFirst("name");
            projectTypeCount = dataNameUtilService.checkProjectTypeNameBeforeInsert(projectTypeName);
            result.put("count", projectTypeCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("properties")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取property数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countProperty(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received property count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long propertyCount = 0;
            String propertyName = queryParams.getFirst("name");
            propertyCount = dataNameUtilService.checkPropertyNameBeforeInsert(propertyName);
            result.put("count", propertyCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("runSets")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取runSet数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countRunSet(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received runSet count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long runSetCount = 0;
            if (queryParams.containsKey("haveRefRunSet")) {
                Long runSetId = Long.valueOf(queryParams.getFirst("runSetId"));
                RunSetTestCaseLinkExample runSetTestCaseLinkExample = new RunSetTestCaseLinkExample();
                runSetTestCaseLinkExample.createCriteria().andRefRunSetIdEqualTo(runSetId);
                List<RunSetTestCaseLink> runSetTestCaseLinks = runSetTestCaseLinkService
                        .selectByExample(runSetTestCaseLinkExample);
                List<Long> runSetIds = new ArrayList<Long>();
                for (RunSetTestCaseLink runSetTestCaseLink : runSetTestCaseLinks) {
                    runSetIds.add(runSetTestCaseLink.getRunSetId());
                }
                runSetCount = runSetIds.size();
            }
            else {
                if (queryParams.containsKey("name")) {
                    String runSetName = queryParams.getFirst("name");
                    runSetCount = dataNameUtilService.checkRunSetNameBeforeInsert(runSetName);
                }
            }
            result.put("count", runSetCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("sections")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取section名称数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "applicationId", value = "applicationId", required = true, dataType = "long", paramType = "query") })
    public MeowlomoResponse countSection(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received section count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long sectionCount = 0;
            String sectionName = queryParams.getFirst("name");
            if (sectionName != null && queryParams.getFirst("applicationId") != null) {
                Long applicationId = Long.valueOf(queryParams.getFirst("applicationId"));
                sectionCount = dataNameUtilService.checkSectionNameBeforeInsert(applicationId, sectionName);
            }
            result.put("count", sectionCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("stepLogTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取stepLogType数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countStepLogType(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received stepLogType count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long stepLogTypeCount = 0;
            String stepLogTypeName = queryParams.getFirst("name");
            stepLogTypeCount = dataNameUtilService.checkStepLogTypeNameBeforeInsert(stepLogTypeName);
            result.put("count", stepLogTypeCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("systemRequirements")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取systemRequirement数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countSystemRequirement(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received systemRequirement count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long systemRequirementCount = 0;
            String systemRequirementName = queryParams.getFirst("name");
            systemRequirementCount = dataNameUtilService.checkSystemRequirementNameBeforeInsert(systemRequirementName);
            result.put("count", systemRequirementCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取testCase名称数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "projectId", value = "projectId", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "runSetId", value = "runSetId", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "testCaseShareFolderId", value = "testCaseShareFolderId", required = true, dataType = "long", paramType = "query") })
    public MeowlomoResponse countTestCase(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received testCase count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long testCaseCount = 0;
            String testCaseName = queryParams.getFirst("name");
            if (testCaseName != null && queryParams.getFirst("projectId") != null) {
                Long projectId = Long.valueOf(queryParams.getFirst("projectId"));
                testCaseCount = dataNameUtilService.checkTestCaseNameForProjectBeforeInsert(projectId, testCaseName);
            }
            if (testCaseName != null && queryParams.getFirst("runSetId") != null) {
                Long runSetId = Long.valueOf(queryParams.getFirst("runSetId"));
                testCaseCount = dataNameUtilService.checkTestCaseNameForRunSetBeforeInsert(runSetId, testCaseName);
            }
            if (testCaseName != null && queryParams.getFirst("testCaseShareFolderId") != null) {
                Long testCaseShareFolderId = Long.valueOf(queryParams.getFirst("testCaseShareFolderId"));
                testCaseCount = dataNameUtilService
                        .checkTestCaseNameForTestCaseShareFolderBeforeInsert(testCaseShareFolderId, testCaseName);
            }
            result.put("count", testCaseCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("testCaseReference")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取testCaseReference层级数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "projectId", value = "projectId", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "runSetId", value = "runSetId", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "testCaseShareFolderId", value = "testCaseShareFolderId", required = true, dataType = "long", paramType = "query") })
    public MeowlomoResponse countRefTestCase(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received testCaseReference count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long refCount = -1;
            if (queryParams.containsKey("testCaseId")) {
                Long testCaseId = Long.valueOf(queryParams.getFirst("testCaseId"));
                // refCount = testCaseUtilService.countTestCaseReferenceLayer(testCaseId);
                refCount = testCaseUtilService.countCurrentTestReferenceLayer(testCaseId);
            }
            if (refCount >= this.testCaseReferenceLayerMax) {
                refCount = -1;
            }
            result.put("count", refCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("testCaseShareFolders")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取testCaseShareFolder数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countTestCaseShareFolder(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received testCaseShareFolder count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long testCaseShareFolderCount = 0;
            String testCaseShareFolderName = queryParams.getFirst("name");
            testCaseShareFolderCount = dataNameUtilService
                    .checkTestCaseShareFolderNameBeforeInsert(testCaseShareFolderName);
            result.put("count", testCaseShareFolderCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("testCaseOptions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取testCaseOption数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countTestCaseOption(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received testCaseOption count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long testCaseOptionCount = 0;
            String testCaseOptionName = queryParams.getFirst("name");
            testCaseOptionCount = dataNameUtilService.checkTestCaseOptionNameBeforeInsert(testCaseOptionName);
            result.put("count", testCaseOptionCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("testCaseOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取testCaseOverwrite数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countTestCaseOverwrite(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received testCaseOverwrite count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long testCaseOverwriteCount = 0;
            String testCaseOverwriteName = queryParams.getFirst("name");
            Long testCaseId = Long.valueOf(queryParams.getFirst("testCaseId"));
            testCaseOverwriteCount = dataNameUtilService.checkTestCaseOverwriteNameForTestCaseBeforeInsert(testCaseId,
                    testCaseOverwriteName);
            result.put("count", testCaseOverwriteCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("tags")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取tag数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countTag(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received tag count call");
        try {
            List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
            if (queryParams.containsKey("relation") && queryParams.getFirst("tagId") != null) {
                Long tagId = Long.valueOf(queryParams.getFirst("tagId"));
                TestCaseTagLinkExample testCaseTagLinkExample = new TestCaseTagLinkExample();
                testCaseTagLinkExample.createCriteria().andTagIdEqualTo(tagId);
                List<TestCaseTagLink> testCaseTagLinks = testCaseTagLinkService.selectByExample(testCaseTagLinkExample);
                List<Long> testCaseIds = new ArrayList<Long>();
                for (int i = 0; i < testCaseTagLinks.size(); i++) {
                    Long testCaseId = testCaseTagLinks.get(i).getTestCaseId();
                    if (!testCaseService.selectByPrimaryKey(testCaseId).getDeleted()) {
                        testCaseIds.add(testCaseId);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("testCaseName", testCaseService.selectByPrimaryKey(testCaseId).getName());
                        records.add(map);
                    }
                }
            }
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

    @GET
    @Path("aliases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取alias数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countAlias(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received alias count call");
        try {
            List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
            if (queryParams.containsKey("relation") && queryParams.getFirst("aliasId") != null) {
                Long aliasId = Long.valueOf(queryParams.getFirst("aliasId"));
                RunSetAliasLinkExample runSetAliasLinkExample = new RunSetAliasLinkExample();
                runSetAliasLinkExample.createCriteria().andAliasIdEqualTo(aliasId);
                List<RunSetAliasLink> runSetAliasLinks = runSetAliasLinkService.selectByExample(runSetAliasLinkExample);
                List<Long> runSetIds = new ArrayList<Long>();
                for (int i = 0; i < runSetAliasLinks.size(); i++) {
                    Long runSetId = runSetAliasLinks.get(i).getRunSetId();
                    if (!runSetService.selectByPrimaryKey(runSetId).getDeleted()) {
                        runSetIds.add(runSetId);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("runSetName", runSetService.selectByPrimaryKey(runSetId).getName());
                        records.add(map);
                    }
                }
            }
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

    @GET
    @Path("runSetTestCaseLinks")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取runSetTestCaseLink名称数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "projectId", value = "projectId", required = true, dataType = "long", paramType = "query") })
    public MeowlomoResponse countRunSetTestCaseLink(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received runSetTestCaseLink count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long runSetTestCaseLinkCount = 0;
            Long runSetId = null;
            Long testCaseId = null;
            Long testCaseOverwriteId = null;
            Long driverPackId = null;
            if (queryParams.getFirst("runSetId") != null) {
                runSetId = Long.valueOf(queryParams.getFirst("runSetId"));
            }
            if (queryParams.getFirst("testCaseId") != null) {
                testCaseId = Long.valueOf(queryParams.getFirst("testCaseId"));
            }
            if (queryParams.getFirst("testCaseOverwriteId") != null) {
                testCaseOverwriteId = Long.valueOf(queryParams.getFirst("testCaseOverwriteId"));
            }
            if (queryParams.getFirst("driverPackId") != null) {
                driverPackId = Long.valueOf(queryParams.getFirst("driverPackId"));
            }
            runSetTestCaseLinkCount = dataNameUtilService.checkRunSetTestCaseLinkBeforeInsertOrUpdate(runSetId,
                    testCaseId, testCaseOverwriteId, driverPackId);
            result.put("count", runSetTestCaseLinkCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("instructionBundles")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取instructionBundle数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countInstructionBundle(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received instructionBundle count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long instructionBundleCount = 0;
            String instructionBundleName = queryParams.getFirst("name");
            instructionBundleCount = dataNameUtilService.checkInstructionBundleNameBeforeInsert(instructionBundleName);
            result.put("count", instructionBundleCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("systemRequirementPacks")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取systemRequirementPack名称数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "projectId", value = "projectId", required = true, dataType = "long", paramType = "query") })
    public MeowlomoResponse countSystemRequirementPack(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @RequestParam Map<String, String> requestParams) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        logger.info("received systemRequirementPack count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long systemRequirementPackCount = 0;
            String systemRequirementPackName = queryParams.getFirst("name");
            if (systemRequirementPackName != null) {
                systemRequirementPackCount = dataNameUtilService.checkSystemRequirementPackBeforeInsert(systemRequirementPackName);
            }
            result.put("count", systemRequirementPackCount);
            return new MeowlomoResponse(result, null, null);
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
    @Path("testCases/{testCaseId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取instructionBundle数量", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query") })
    public MeowlomoResponse countRefFerencedTestCaseByTestCaseId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("testCaseId") Long testCaseId) {
        logger.info("received instructionBundle count call");
        try {
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            long testCaseCount = testCaseUtilService.countTestCaseReferencedBy(testCaseId);
            result.put("count", testCaseCount);
            return new MeowlomoResponse(result, null, null);
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
