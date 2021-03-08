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
import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.AliasExample;
import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLink;
import com.meowlomo.atm.core.model.NotificationExample;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetAliasLink;
import com.meowlomo.atm.core.model.RunSetAliasLinkExample;
import com.meowlomo.atm.core.model.RunSetExample;
import com.meowlomo.atm.core.model.RunSetJobLink;
import com.meowlomo.atm.core.model.RunSetJobLinkExample;
import com.meowlomo.atm.core.model.RunSetNotificationLink;
import com.meowlomo.atm.core.model.RunSetNotificationLinkExample;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
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
import com.meowlomo.atm.core.service.base.AliasService;
import com.meowlomo.atm.core.service.base.NotificationEmailNotificationTargetLinkService;
import com.meowlomo.atm.core.service.base.NotificationService;
import com.meowlomo.atm.core.service.base.RunSetAliasLinkService;
import com.meowlomo.atm.core.service.base.RunSetJobLinkService;
import com.meowlomo.atm.core.service.base.RunSetNotificationLinkService;
import com.meowlomo.atm.core.service.base.RunSetService;
import com.meowlomo.atm.core.service.base.RunSetTestCaseLinkService;
import com.meowlomo.atm.core.service.base.TestCaseOverwriteService;
import com.meowlomo.atm.core.service.base.TestCaseService;
import com.meowlomo.atm.core.service.referrence.AliasReferenceService;
import com.meowlomo.atm.core.service.referrence.NotificationReferenceService;
import com.meowlomo.atm.core.service.referrence.RunSetReferenceService;
import com.meowlomo.atm.core.service.referrence.RunSetTestCaseLinkReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;
import com.meowlomo.atm.core.service.util.DriverTypeUtilService;
import com.meowlomo.atm.core.validator.BeanValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/runSets")
@Api(value = "runSet resources", produces = "application/json")
public class RunSetResource {

    private final Logger logger = LoggerFactory.getLogger(RunSetResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "RUNS";

    @Autowired
    private RunSetService runSetService;

    @Autowired
    private RunSetReferenceService runSetReferenceService;

    @Autowired
    private RunSetJobLinkService runSetJobLinkService;

    @Autowired
    private RunSetTestCaseLinkService runSetTestCaseLinkService;

    @Autowired
    private RunSetTestCaseLinkReferenceService runSetTestCaseLinkReferenceService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestCaseReferenceService testCaseReferenceService;

    @Autowired
    private DriverTypeUtilService driverTypeUtilService;

    @Autowired
    private TestCaseOverwriteService testCaseOverwriteService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationReferenceService notificationReferenceService;

    @Autowired
    private RunSetNotificationLinkService runSetNotificationLinkService;

    @Autowired
    private AliasService aliasService;

    @Autowired
    private RunSetAliasLinkService runSetAliasLinkService;

    @Autowired
    private AliasReferenceService aliasReferenceService;

    @Autowired
    private NotificationEmailNotificationTargetLinkService notificationEmailNotificationTargetLinkService;

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
    @ApiOperation(value = "读取RunSet", response = MeowlomoResponse.class, httpMethod = "GET")
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
        logger.info("received runSet select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                RunSetExample example = new RunSetExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count",
                        runSetService.countByExample(example));
                List<RunSet> records = runSetService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                RunSetExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new RunSetExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                RunSetExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        RunSetExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count",
                        runSetService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else if (queryParams.containsKey("refRunSet")) {
                Long runSetId = Long.valueOf(queryParams.getFirst("runSetId"));
                RunSetTestCaseLinkExample runSetTestCaseLinkExample = new RunSetTestCaseLinkExample();
                runSetTestCaseLinkExample.createCriteria().andRefRunSetIdEqualTo(runSetId);
                List<RunSetTestCaseLink> runSetTestCaseLinks = runSetTestCaseLinkService
                        .selectByExample(runSetTestCaseLinkExample);
                List<Long> runSetIds = new ArrayList<Long>();
                runSetIds.add(runSetId);
                for (RunSetTestCaseLink runSetTestCaseLink : runSetTestCaseLinks) {
                    runSetIds.add(runSetTestCaseLink.getRunSetId());
                }
                RunSetExample runSetExample = new RunSetExample();
                runSetExample.createCriteria().andIdNotIn(runSetIds).andDeletedEqualTo(false);
                List<RunSet> records = runSetService.selectByExample(runSetExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                RunSetExample.Criteria criteria = null;
                criteria = new RunSetExample().createCriteria();
                if (queryParams.containsKey("aliases")) {
                    String aliasesNames = queryParams.getFirst("aliases");
                    String[] aliasesNamesStringArray = aliasesNames.trim().split(",");
                    List<String> aliasesNamesList = Arrays.asList(aliasesNamesStringArray);
                    AliasExample aliasExample = new AliasExample();
                    aliasExample.createCriteria().andNameIn(aliasesNamesList);
                    List<Alias> aliases = aliasService.selectByExample(aliasExample);
                    List<Long> aliasesIds = new ArrayList<Long>();
                    for (Alias alias : aliases) {
                        aliasesIds.add(alias.getId());
                    }
                    RunSetAliasLinkExample runSetAliasLinkExample = new RunSetAliasLinkExample();
                    if (aliasesIds.size() > 0) {
                        runSetAliasLinkExample.createCriteria().andAliasIdIn(aliasesIds);
                    }
                    else {
                        runSetAliasLinkExample.createCriteria().andIdIsNull();
                    }
                    List<RunSetAliasLink> runSetAliasLinks = runSetAliasLinkService
                            .selectByExample(runSetAliasLinkExample);
                    List<Long> runSetIds = new ArrayList<Long>();
                    for (RunSetAliasLink runSetAliasLink : runSetAliasLinks) {
                        runSetIds.add(runSetAliasLink.getRunSetId());
                    }
                    if (runSetIds.size() > 0) {
                        criteria.andIdIn(runSetIds);
                    }
                    else {
                        criteria.andIdIsNull();
                    }
                }
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                RunSetExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        RunSetExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count",
                        runSetService.countByExample(example));
                List<RunSet> records = null;
                if (queryParams.containsKey("ref")) {
                    records = runSetReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = runSetService.selectByExampleWithRowbounds(example, rowBounds);
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
    @ApiOperation(value = "读取单个RunSet", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "runSet id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long id) {
        logger.info("received runSet select by id call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            RunSet selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = runSetReferenceService.selectByPrimaryKey(id);
            }
            else {
                selectRecord = runSetService.selectByPrimaryKey(id);
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
    @ApiOperation(value = "删除单个RunSet", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "runSet id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") long id) {
        logger.info("received runSet delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            RunSet record = runSetService.selectByPrimaryKey(id);
            int deleteResult = runSetService.logicalDeleteByPrimaryKey(id);
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
    @ApiOperation(value = "批量删除RunSet", response = MeowlomoResponse.class, httpMethod = "DELETE")
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
        logger.info("received runSet delete call " + uriInfo.getPath());
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                RunSetExample example = new RunSetExample();
                example.or().andDeletedEqualTo(false).andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<RunSet> records = runSetService.selectByExample(example);
                metadata.put("count", runSetService.countByExample(example));
                int deleteResult = runSetService.logicalDeleteByExample(example);
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
                com.meowlomo.atm.core.model.RunSetExample.Criteria criteria = new RunSetExample().createCriteria();
                criteria.andIdIsNotNull().andDeletedEqualTo(false);
                RunSetExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        RunSetExample.class);
                // count
                List<RunSet> countRecords = runSetService.selectByExample(example);
                // mark deleted
                int deleteResult = runSetService.logicalDeleteByExample(example);
                // get back records
                List<Long> deletedIds = new ArrayList<Long>();
                for (RunSet target : countRecords) {
                    deletedIds.add(target.getId());
                }
                RunSetExample deletedExample = new RunSetExample();
                deletedExample.or().andIdIsNotNull().andDeletedEqualTo(true).andIdIn(deletedIds);
                List<RunSet> records = runSetService.selectByExample(deletedExample);
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
    @ApiOperation(value = "更新RunSet", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            RunSet[] records) throws Exception {
        logger.info("received patch runSet by id call " + uriInfo.getPath());
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
                RunSet record = records[i];
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
                    RunSet record = records[i];
                    int updateResult = runSetService.updateByPrimaryKeySelective(records[i]);
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
                RunSetExample example = new RunSetExample();
                example.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<RunSet> finalRecords = runSetService.selectByExample(example);
                // sort return result
                List<RunSet> finalReturnRecords = new ArrayList<RunSet>();
                for (Long id : targetIds) {
                    for (RunSet oRecord : finalRecords) {
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
    @ApiOperation(value = "替换或添加RunSet", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            RunSet[] records) {
        logger.info("received put runSet by primary id call");
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
            List<RunSet> finalRecords = new ArrayList<RunSet>();
            for (int i = 0; i < records.length; i++) {
                RunSet record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = runSetService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(record);
                    }
                }
                else {
                    // do update
                    int updateResult = runSetService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(runSetService.selectByPrimaryKey(record.getId()));
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
    @ApiOperation(value = "添加RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            RunSet[] records) {
        logger.info("received post runSet call ");
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
            List<RunSet> finalRecords = new ArrayList<RunSet>();
            for (int i = 0; i < records.length; i++) {
                RunSet record = records[i];
                record.setId(null);
                long insertResult = runSetService.insertSelective(record);
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

    // ===== runSet testCase link start =====

    @POST
    @LogUserActivity
    @Path("/{runSetId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个TestCase实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加TestCase并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertTestCaseAndLinkToRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId, TestCase[] records)
            throws Exception {
        logger.info("received post testCase by runSet id=" + runSetId);
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
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            for (TestCase record : records) {
                if (record.getType() == "Reference Test") {
                    // 只能引用一层，带有引用的runSet不能被引用
                    long refRunSetId = record.getRefRunSetId();
                    RunSetTestCaseLinkExample runSetTestCaseLinkExample = new RunSetTestCaseLinkExample();
                    runSetTestCaseLinkExample.createCriteria().andRunSetIdEqualTo(refRunSetId);
                    List<RunSetTestCaseLink> runSetTestCaseLinks = runSetTestCaseLinkService
                            .selectByExample(runSetTestCaseLinkExample);
                    for (RunSetTestCaseLink runSetTestCaseLink : runSetTestCaseLinks) {
                        if (testCaseService.selectByPrimaryKey(runSetTestCaseLink.getTestCaseId())
                                .getRefRunSetId() != null) {
                            UUID exuuid = UUID.randomUUID();
                            String trace = "exception UUID=" + exuuid + " record with id not exist ";
                            String message = "ID为" + record.getId() + "的TestCase引用了带有引用的runSet。问题唯一码[" + exuuid + "]";
                            String code = ERROR_TYPE + "01POS";
                            logger.error(trace, httpServletRequest.getContextPath());
                            throw new CustomBadRequestException(null, message, trace, code, exuuid);
                        }
                    }
                    // 已包含在被引用runSet里的用例不能添加到当前runSet
                    RunSetTestCaseLinkExample newRunSetTestCaseLinkExample = new RunSetTestCaseLinkExample();
                    newRunSetTestCaseLinkExample.createCriteria().andTestCaseIdEqualTo(record.getId());
                    List<RunSetTestCaseLink> newRunSetTestCaseLinks = runSetTestCaseLinkService
                            .selectByExample(newRunSetTestCaseLinkExample);
                    for (RunSetTestCaseLink newRunSetTestCaseLink : newRunSetTestCaseLinks) {
                        TestCaseExample newTestCaseExample = new TestCaseExample();
                        newTestCaseExample.createCriteria().andRefRunSetIdEqualTo(newRunSetTestCaseLink.getRunSetId());
                        if (testCaseService.selectByExample(newTestCaseExample).size() > 0) {
                            UUID exuuid = UUID.randomUUID();
                            String trace = "exception UUID=" + exuuid + " record with id not exist ";
                            String message = "ID为" + record.getId() + "的TestCase包含于带有引用的runSet。问题唯一码[" + exuuid + "]";
                            String code = ERROR_TYPE + "01POS";
                            logger.error(trace, httpServletRequest.getContextPath());
                            throw new CustomBadRequestException(null, message, trace, code, exuuid);
                        }
                    }
                }
            }
            List<Long> insertedResult = testCaseService.insertRecordsSelective(Arrays.asList(records));
            if (insertedResult.size() == records.length) {
                // add link
                for (Long id : insertedResult) {
                    RunSetTestCaseLink link = new RunSetTestCaseLink();
                    link.setTestCaseId(id);
                    link.setRunSetId(runSetId);
                    runSetTestCaseLinkService.insert(link);
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
                String message = "添加TestCase并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
    @Path("/{runSetId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于RunSet的TestCase", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "TestCase Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "TestCase Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "TestCase Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "TestCase Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "TestCase创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "TestCase创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getTestCaseByRunSetPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received get testCases by runSet id = " + runSetId);
        try {
            // get the record first
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
                linkExample.or().andRunSetIdEqualTo(runSetId);
                List<RunSetTestCaseLink> links = runSetTestCaseLinkService.selectByExample(linkExample);
                List<Long> targetIds = new ArrayList<Long>();
                for (RunSetTestCaseLink link : links) {
                    targetIds.add(link.getTestCaseId());
                }
                if (targetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                TestCaseExample testCaseExample = new TestCaseExample();
                testCaseExample.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<TestCase> finalReturnRecords = testCaseService.selectByExample(testCaseExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
                linkExample.or().andRunSetIdEqualTo(runSetId);
                List<RunSetTestCaseLink> links = runSetTestCaseLinkService.selectByExample(linkExample);
                List<Long> testCaseTargetIds = new ArrayList<Long>();
                List<Long> runSetTestCaseLinkTargetIds = new ArrayList<Long>();
                for (RunSetTestCaseLink link : links) {
                    testCaseTargetIds.add(link.getTestCaseId());
                }
                for (RunSetTestCaseLink link : links) {
                    runSetTestCaseLinkTargetIds.add(link.getId());
                }
                if (testCaseTargetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                TestCaseExample.Criteria criteria = null;
                criteria = new TestCaseExample().createCriteria();
                criteria.andIdIn(testCaseTargetIds);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<TestCase> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = testCaseReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = testCaseService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", testCaseService.countByExample(example));
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

    @GET
    @Path("/{runSetId}/runSetTestCaseLinks")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于RunSet的TestCase", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "TestCase Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "TestCase Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "TestCase Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "TestCase Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "TestCase创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "TestCase创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getRunSetTestCaseLinkByRunSetPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received get runSetTestCaseLinks by runSet id = " + runSetId);
        try {
            // get the record first
            RunSet checkRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                RunSetTestCaseLinkExample recordExample = new RunSetTestCaseLinkExample();
                recordExample.or().andRunSetIdEqualTo(runSetId);
                List<RunSetTestCaseLink> records = runSetTestCaseLinkService.selectByExample(recordExample);
                // filter deleted testCase
                List<RunSetTestCaseLink> finalRecords = new ArrayList<RunSetTestCaseLink>();
                for (RunSetTestCaseLink record : records) {
                    if (!testCaseService.selectByPrimaryKey(record.getTestCaseId()).getDeleted()) {
                        finalRecords.add(record);
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                RunSetTestCaseLinkExample.Criteria criteria = null;
                criteria = new RunSetTestCaseLinkExample().createCriteria();
                criteria.andRunSetIdEqualTo(runSetId);
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
                        criteria.andRunSetIdEqualTo(runSetId).andTestCaseIdIn(testCaseIds);
                    }
                    else {
                        criteria.andIdIsNull();
                    }
                }
                RunSetTestCaseLinkExample example = new RunSetTestCaseLinkExample();
                example.or(criteria);
                final String orderBy = queryParams.getFirst("orderBy");
                if (orderBy != null && orderBy.startsWith("id asc")) {
                    String newOrderBy = orderBy.replace("id asc", "test_case_id asc,id asc");
                    example.setOrderByClause(newOrderBy);
                }
                if (orderBy != null && orderBy.startsWith("id desc")) {
                    String newOrderBy = orderBy.replace("id desc", "test_case_id desc,id desc");
                    example.setOrderByClause(newOrderBy);
                }
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<RunSetTestCaseLink> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = runSetTestCaseLinkReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = runSetTestCaseLinkService.selectByExampleWithRowbounds(example, rowBounds);
                }

                // if order by testCase name or createdAt
                if (orderBy != null && !orderBy.startsWith("id")) {
                    Collections.sort(finalRecords, new Comparator<RunSetTestCaseLink>() {

                        @Override
                        public int compare(RunSetTestCaseLink o1, RunSetTestCaseLink o2) {
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
                metadata.put("count", runSetTestCaseLinkService.countByExample(example));
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
    @Path("/{runSetId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个TestCase实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加TestCase并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutTestCaseToRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId, TestCase[] records)
            throws Exception {
        logger.info("received post testCase by runSet id=" + runSetId);
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

            // get the record first
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // if no records,add links with url
            if (records.length == 0) {
                MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
                TestCaseExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new TestCaseExample().createCriteria();
                    criteria.andDeletedEqualTo(false);
                }
                TestCaseExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        TestCaseExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<TestCase> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = testCaseReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = testCaseService.selectByExampleWithRowbounds(example, rowBounds);
                }
                for (TestCase finalRecord : finalRecords) {
                    // insert link
                    RunSetTestCaseLink link = new RunSetTestCaseLink();
                    link.setTestCaseId(finalRecord.getId());
                    link.setRunSetId(runSetId);
                    runSetTestCaseLinkService.insert(link);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalRecords, null);
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
                        RunSetTestCaseLink link = new RunSetTestCaseLink();
                        link.setTestCaseId(record.getId());
                        link.setRunSetId(runSetId);
                        if (runSetTestCaseLinkService.insertSelective(link) == 1) {
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
                        RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
                        linkExample.or().andTestCaseIdEqualTo(record.getId()).andRunSetIdEqualTo(runSetId);
                        long countResult = runSetTestCaseLinkService.countByExample(linkExample);
                        if (countResult > 0) {
                            finalRecords.add(testCaseService.selectByPrimaryKey(record.getId()));
                        }
                        else {
                            // add the link
                            RunSetTestCaseLink link = new RunSetTestCaseLink();
                            link.setTestCaseId(record.getId());
                            link.setRunSetId(runSetId);
                            if (runSetTestCaseLinkService.insertSelective(link) == 1) {
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
    @Path("/{runSetId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到RunSet的TestCase的链接", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除RunSet关联的TestCase操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的TestCase关联到此RunSet，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+runSetId+\"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此RunSet的TestCase。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "runSetId", value = "RunSet ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "TestCase IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkTestCaseFromRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received post testCase by runSet id=" + runSetId);
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
            RunSet checkRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
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
            RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
            linkExample.or().andRunSetIdEqualTo(runSetId).andTestCaseIdIn(idsList);
            long linkCount = runSetTestCaseLinkService.countByExample(linkExample);
            if (linkCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "输入中存在未关联到此RunSet的TestCase。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            // start the delete
            TestCaseExample recordExample = new TestCaseExample();
            recordExample.or().andDeletedEqualTo(false).andIdIn(idsList);
            List<TestCase> finalRecords = testCaseService.selectByExample(recordExample);
            int deleteResult = runSetTestCaseLinkService.deleteByExample(linkExample);
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
                String message = "存在已删除的TestCase关联到此RunSet，数据不一致。请与管理员联系。并提供唯一码[" + exuuid + "]";
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
                String message = "删除RunSet关联的TestCase操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    // =====runSet testCase link end =====

    // =====runSet testCase testCaseOverwrite link start =====

    @PUT
    @LogUserActivity
    @Path("/{runSetId}/testCases/{testCaseId}/testCaseOverwrites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个TestCaseOverwrite实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加TestCaseOverwrite并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutTestCaseOverwriteToTestCaseToRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId,
            @PathParam("testCaseId") Long testCaseId, TestCaseOverwrite[] records) throws Exception {
        logger.info("received post testCaseOverwrite by runSet id=" + runSetId);
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
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
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
                        // insert link
                        RunSetTestCaseLink link = new RunSetTestCaseLink();
                        link.setTestCaseOverwriteId(record.getId());
                        link.setTestCaseId(testCaseId);
                        link.setRunSetId(runSetId);
                        if (runSetTestCaseLinkService.insertSelective(link) == 1) {
                            finalRecords.add(record);
                        }
                        else {
                            errorIndex.add((long) i);
                        }
                    }
                }
                else {
                    if (record.getName() == null) {
                        record.setName(testCaseOverwriteService.selectByPrimaryKey(record.getId()).getName());
                    }
                    int updateResult = testCaseOverwriteService.updateByPrimaryKeySelective(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // check link exist
                        RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
                        linkExample.or().andTestCaseIdEqualTo(testCaseId).andRunSetIdEqualTo(runSetId);
                        List<RunSetTestCaseLink> results = runSetTestCaseLinkService.selectByExample(linkExample);
                        if (results.size() == 1) {
                            // update the link
                            RunSetTestCaseLink linkRecord = new RunSetTestCaseLink();
                            linkRecord.setTestCaseOverwriteId(record.getId());
                            linkRecord.setTestCaseId(testCaseId);
                            linkRecord.setRunSetId(runSetId);
                            linkRecord.setId(results.get(0).getId());
                            runSetTestCaseLinkService.updateByPrimaryKeySelective(linkRecord);
                            finalRecords.add(testCaseOverwriteService.selectByPrimaryKey(record.getId()));
                        }
                        else {
                            // add the link
                            RunSetTestCaseLink link = new RunSetTestCaseLink();
                            link.setTestCaseOverwriteId(record.getId());
                            link.setTestCaseId(testCaseId);
                            link.setRunSetId(runSetId);
                            if (runSetTestCaseLinkService.insertSelective(link) == 1) {
                                finalRecords.add(testCaseOverwriteService.selectByPrimaryKey(record.getId()));
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

    // =====runSet testCase testCaseOverwrite link end =====

    // =====runSet testCase runSetTestCaseLink link start =====

    @PATCH
    @LogUserActivity
    @Path("/{runSetId}/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个runSetTestCaseLink实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加driverPack并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateRunSetTestCaseLink(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId,
            @PathParam("testCaseId") Long testCaseId, RunSetTestCaseLink[] records) throws Exception {
        logger.info("received patch runSet testCase link by runSet id=" + runSetId + " testCase Id=" + testCaseId);
        try {
            // empty just return
            if (records == null || records.length == 0) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }

            // get the record first
            RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
            linkExample.createCriteria().andTestCaseIdEqualTo(testCaseId).andRunSetIdEqualTo(runSetId);
            runSetTestCaseLinkService.selectByExample(linkExample);
            List<RunSetTestCaseLink> checkRunSetRecords = runSetTestCaseLinkService.selectByExample(linkExample);
            if (checkRunSetRecords.isEmpty()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "RunSet ID为" + runSetId + " TestCase ID 为 " + testCaseId + "的Link不存在。问题唯一码[" + exuuid
                        + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // get the link
            RunSetTestCaseLink linkRecord = records[0];
            linkRecord.setId(checkRunSetRecords.get(0).getId());
            // do the update
            int updateResult = runSetTestCaseLinkService.updateByPrimaryKeySelective(linkRecord);
            // check all update success
            if (updateResult > 0) {
                RunSetTestCaseLink finalRecords = runSetTestCaseLinkService
                        .selectByPrimaryKey(checkRunSetRecords.get(0).getId());
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", updateResult);
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
                String message = "部分或全部更新失败，失败序列。 问题唯一码[" + exuuid + "]";
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

    // =====runSet testCase runSetTestCaseLink link end =====

    // =====runSet job link start =====

    /**
     * Gets the job by test case primary key.
     *
     * @param runSetId
     *            the test case id
     * @return the job by test case primary key
     */
    @GET
    @Path("/{runSetId}/jobs")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取关联RunSet的Job", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse getJobByRunSetPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) {
        logger.info("received get jobs by applciation id=" + runSetId);
        try {
            // get the record first
            RunSet checkRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            RunSetJobLinkExample linkExample = new RunSetJobLinkExample();
            linkExample.or().andRunSetIdEqualTo(runSetId);
            List<RunSetJobLink> links = runSetJobLinkService.selectByExample(linkExample);
            if (links.isEmpty()) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            // coverte to uuids
            List<UUID> uuids = new ArrayList<UUID>();
            for (RunSetJobLink link : links) {
                uuids.add(link.getJobUuid());
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
    @Path("/{runSetId}/jobs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加Job到RunSet", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "job priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "job type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "job UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "job status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse batchInsertJobByRunSetPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId, UUID[] records) {
        logger.info("received get jobs by applciation id=" + runSetId);
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
            RunSet checkRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
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
                RunSetJobLink link = new RunSetJobLink();
                link.setJobUuid(uuid);
                link.setRunSetId(runSetId);
                link.setRunSetUuid(checkRecord.getUuid());
                long insertResult = runSetJobLinkService.insert(link);
                if (insertResult != 1) {
                    errorIndex.add(uuid);
                }
                else {
                    ids.add(link.getId());
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
                RunSetJobLinkExample example = new RunSetJobLinkExample();
                example.or().andIdIn(ids);
                List<RunSetJobLink> finalRecords = runSetJobLinkService.selectByExample(example);
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
    // =====runSet job link end =====

    // ===== runSet notification link start =====

    @POST
    @LogUserActivity
    @Path("/{runSetId}/notifications")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个Notification实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加Notification并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertNotificationAndLinkToRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId,
            Notification[] records) throws Exception {
        logger.info("received post notification by runSet id=" + runSetId);
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
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // List<Long> insertedResult =
            // notificationService.insertRecordsSelective(Arrays.asList(records));
            List<Long> errorIndex = new ArrayList<Long>();
            // start the insert
            // insert one by one
            List<Long> insertedResult = new ArrayList<Long>();
            for (int i = 0; i < records.length; i++) {
                Notification record = records[i];
                if (record.getEmailNotificationTargets() != null) {
                    // insert email notification link
                    NotificationEmailNotificationTargetLink notificationEmailNotificationTargetLink = new NotificationEmailNotificationTargetLink();
                    notificationEmailNotificationTargetLink.setId(record.getId());
                    List<EmailNotificationTarget> emailNotificationTargets = record.getEmailNotificationTargets();
                    for (EmailNotificationTarget emailNotificationTarget : emailNotificationTargets) {
                        notificationEmailNotificationTargetLink
                                .setEmailNotificationTargetId(emailNotificationTarget.getId());
                        notificationEmailNotificationTargetLinkService.insert(notificationEmailNotificationTargetLink);
                    }
                }
                long insertResult = 0;
                insertResult = notificationService.insertSelective(record);
                if (insertResult != 1) {
                    errorIndex.add((long) i);
                }
                else {
                    insertedResult.add(record.getId());
                }
            }
            if (insertedResult.size() == records.length) {
                // add link
                for (Long id : insertedResult) {
                    RunSetNotificationLink link = new RunSetNotificationLink();
                    link.setNotificationId(id);
                    link.setRunSetId(runSetId);
                    runSetNotificationLinkService.insert(link);
                }
                NotificationExample example = new NotificationExample();
                example.or().andIdIn(insertedResult);
                List<Notification> finalRecords = notificationService.selectByExample(example);
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
                String message = "添加Notification并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
    @Path("/{runSetId}/notifications")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于RunSet的Notification", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Notification Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "Notification Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "Notification Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "Notification Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "Notification创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "Notification创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getNotificationByRunSetPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received get notifications by runSet id = " + runSetId);
        try {
            // get the record first
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                RunSetNotificationLinkExample linkExample = new RunSetNotificationLinkExample();
                linkExample.or().andRunSetIdEqualTo(runSetId);
                List<RunSetNotificationLink> links = runSetNotificationLinkService.selectByExample(linkExample);
                List<Long> targetIds = new ArrayList<Long>();
                for (RunSetNotificationLink link : links) {
                    targetIds.add(link.getNotificationId());
                }
                if (targetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                NotificationExample notificationExample = new NotificationExample();
                notificationExample.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<Notification> finalReturnRecords = notificationService.selectByExample(notificationExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                RunSetNotificationLinkExample linkExample = new RunSetNotificationLinkExample();
                linkExample.or().andRunSetIdEqualTo(runSetId);
                List<RunSetNotificationLink> links = runSetNotificationLinkService.selectByExample(linkExample);
                List<Long> notificationTargetIds = new ArrayList<Long>();
                for (RunSetNotificationLink link : links) {
                    notificationTargetIds.add(link.getNotificationId());
                }
                if (notificationTargetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                NotificationExample.Criteria criteria = null;
                criteria = new NotificationExample().createCriteria();
                criteria.andIdIn(notificationTargetIds);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                NotificationExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        NotificationExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<Notification> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = notificationReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                    for (Notification finalRecord : finalRecords) {
                        finalRecord.setRunSets(null);
                    }
                }
                else {
                    finalRecords = notificationService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", notificationService.countByExample(example));
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
    @Path("/{runSetId}/notifications")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个Notification实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加Notification并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutNotificationToRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId,
            Notification[] records) throws Exception {
        logger.info("received post notification by runSet id=" + runSetId);
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
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<Notification> finalRecords = new ArrayList<Notification>();
            for (int i = 0; i < records.length; i++) {
                Notification record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = 0;
                    insertResult = notificationService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // insert link
                        RunSetNotificationLink link = new RunSetNotificationLink();
                        link.setNotificationId(record.getId());
                        link.setRunSetId(runSetId);
                        if (runSetNotificationLinkService.insertSelective(link) == 1) {
                            finalRecords.add(record);
                        }
                        else {
                            errorIndex.add((long) i);
                        }
                    }
                }
                else {
                    // do update
                    if (record.getSubject() == null) {
                        record.setSubject(notificationService.selectByPrimaryKey(record.getId()).getSubject());
                    }
                    int updateResult = notificationService.updateByPrimaryKeySelective(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // check link exist
                        RunSetNotificationLinkExample linkExample = new RunSetNotificationLinkExample();
                        linkExample.or().andNotificationIdEqualTo(record.getId()).andRunSetIdEqualTo(runSetId);
                        long countResult = runSetNotificationLinkService.countByExample(linkExample);
                        if (countResult == 1) {
                            finalRecords.add(notificationService.selectByPrimaryKey(record.getId()));
                        }
                        else {
                            // add the link
                            RunSetNotificationLink link = new RunSetNotificationLink();
                            link.setNotificationId(record.getId());
                            link.setRunSetId(runSetId);
                            if (runSetNotificationLinkService.insertSelective(link) == 1) {
                                finalRecords.add(notificationService.selectByPrimaryKey(record.getId()));
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
    @Path("/{runSetId}/notifications")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到RunSet的Notification的链接", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除RunSet关联的Notification操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的Notification关联到此RunSet，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+runSetId+\"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此RunSet的Notification。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "runSetId", value = "RunSet ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "Notification IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkNotificationFromRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received post notification by runSet id=" + runSetId);
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
            RunSet checkRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
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
            RunSetNotificationLinkExample linkExample = new RunSetNotificationLinkExample();
            linkExample.or().andRunSetIdEqualTo(runSetId).andNotificationIdIn(idsList);
            long linkCount = runSetNotificationLinkService.countByExample(linkExample);
            if (linkCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "输入中存在未关联到此RunSet的Notification。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            // start the delete
            NotificationExample recordExample = new NotificationExample();
            recordExample.or().andDeletedEqualTo(false).andIdIn(idsList);
            List<Notification> finalRecords = notificationService.selectByExample(recordExample);
            int deleteResult = runSetNotificationLinkService.deleteByExample(linkExample);
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
                String message = "存在已删除的Notification关联到此RunSet，数据不一致。请与管理员联系。并提供唯一码[" + exuuid + "]";
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
                String message = "删除RunSet关联的Notification操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    // =====runSet notification link end =====

    // =====runSet executionDriverMap link start =====

    @GET
    @Path("/{runSetId}/driverTypes")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于RunSet的ExecutionDriverMap", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse getDriverTypeByRunSetPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received get executionDriverMaps by runSet id = " + runSetId);
        try {
            // get the record first
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<DriverType> finalReturnRecords = driverTypeUtilService.getDriverTypeByRunSetId(runSetId);
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
    // =====runSet executionDriverMap link end =====

    // ===== runSet alias link start =====

    @POST
    @LogUserActivity
    @Path("/{runSetId}/aliases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个Alias实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加Alias并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertAliasAndLinkToRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId, Alias[] records)
            throws Exception {
        logger.info("received post alias by runSet id=" + runSetId);
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
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            List<Long> insertedResult = aliasService.insertRecordsSelective(Arrays.asList(records));
            if (insertedResult.size() == records.length) {
                // add link
                for (Long id : insertedResult) {
                    RunSetAliasLink link = new RunSetAliasLink();
                    link.setAliasId(id);
                    link.setRunSetId(runSetId);
                    runSetAliasLinkService.insert(link);
                }
                AliasExample example = new AliasExample();
                example.or().andIdIn(insertedResult);
                List<Alias> finalRecords = aliasService.selectByExample(example);
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
                String message = "添加Alias并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[" + exuuid + "]";
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
    @Path("/{runSetId}/aliases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取关联于RunSet的Alias", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Alias Name，支持模糊搜索", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "Alias Priority，优先级", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "Alias Type，类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "Alias Status，状态", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "Alias创建启示时间[unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "Alias创建结束时间 [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse getAliasByRunSetPrimaryKey(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received get aliases by runSet id = " + runSetId);
        try {
            // get the record first
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {// no example
                RunSetAliasLinkExample linkExample = new RunSetAliasLinkExample();
                linkExample.or().andRunSetIdEqualTo(runSetId);
                List<RunSetAliasLink> links = runSetAliasLinkService.selectByExample(linkExample);
                List<Long> targetIds = new ArrayList<Long>();
                for (RunSetAliasLink link : links) {
                    targetIds.add(link.getAliasId());
                }
                if (targetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                AliasExample aliasExample = new AliasExample();
                aliasExample.or().andDeletedEqualTo(false).andIdIn(targetIds);
                List<Alias> finalReturnRecords = aliasService.selectByExample(aliasExample);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalReturnRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// with query parameters
                RunSetAliasLinkExample linkExample = new RunSetAliasLinkExample();
                linkExample.or().andRunSetIdEqualTo(runSetId);
                List<RunSetAliasLink> links = runSetAliasLinkService.selectByExample(linkExample);
                List<Long> aliasTargetIds = new ArrayList<Long>();
                for (RunSetAliasLink link : links) {
                    aliasTargetIds.add(link.getAliasId());
                }
                if (aliasTargetIds.isEmpty()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
                AliasExample.Criteria criteria = null;
                criteria = new AliasExample().createCriteria();
                criteria.andIdIn(aliasTargetIds);
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria.andDeletedEqualTo(false);
                }
                AliasExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        AliasExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                List<Alias> finalRecords = null;
                if (queryParams.containsKey("ref")) {
                    finalRecords = aliasReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    finalRecords = aliasService.selectByExampleWithRowbounds(example, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", aliasService.countByExample(example));
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
    @Path("/{runSetId}/aliases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加单个或多个Alias实体并链接到RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加内容为空。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\" + runSetId + \"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "添加Alias并连接到RunSet操作无法整体完成，请检查数据。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse PutAliasToRunSet(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("runSetId") Long runSetId, Alias[] records) throws Exception {
        logger.info("received post alias by runSet id=" + runSetId);
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
            RunSet checkRunSetRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRunSetRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<Alias> finalRecords = new ArrayList<Alias>();
            for (int i = 0; i < records.length; i++) {
                Alias record = records[i];
                if (record.getId() == null) {
                    // do insert
                    long insertResult = aliasService.insertSelective(record);
                    if (insertResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // insert link
                        RunSetAliasLink link = new RunSetAliasLink();
                        link.setAliasId(record.getId());
                        link.setRunSetId(runSetId);
                        if (runSetAliasLinkService.insertSelective(link) == 1) {
                            finalRecords.add(record);
                        }
                        else {
                            errorIndex.add((long) i);
                        }
                    }
                }
                else {
                    if (record.getName() == null) {
                        record.setName(aliasService.selectByPrimaryKey(record.getId()).getName());
                    }
                    int updateResult = aliasService.updateByPrimaryKeySelective(record);
                    if (updateResult != 1) {
                        errorIndex.add((long) i);
                    }
                    else {
                        // check link exist
                        RunSetAliasLinkExample linkExample = new RunSetAliasLinkExample();
                        linkExample.or().andAliasIdEqualTo(record.getId()).andRunSetIdEqualTo(runSetId);
                        long countResult = runSetAliasLinkService.countByExample(linkExample);
                        if (countResult == 1) {
                            finalRecords.add(aliasService.selectByPrimaryKey(record.getId()));
                        }
                        else {
                            // add the link
                            RunSetAliasLink link = new RunSetAliasLink();
                            link.setAliasId(record.getId());
                            link.setRunSetId(runSetId);
                            if (runSetAliasLinkService.insertSelective(link) == 1) {
                                finalRecords.add(aliasService.selectByPrimaryKey(record.getId()));
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
    @Path("/{runSetId}/aliases")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除关联到RunSet的Alias的链接", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "删除RunSet关联的Alias操作无法完成，请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "存在已删除的Alias关联到此RunSet，数据不一致。请与管理员联系。并提供唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ID为\"+runSetId+\"的RunSet不存在。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "未包含ids在URL中，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ids格式不正确。第一个ids为有效输入，且只能为逗号分隔整数形式，第一个ids为有效输入。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "输入中存在未关联到此RunSet的Alias。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "runSetId", value = "RunSet ID", required = true, allowEmptyValue = false, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "ids", value = "Alias IDs, 逗号分隔", required = true, allowEmptyValue = false, dataType = "String", paramType = "query") })
    public MeowlomoResponse unlinkAliasFromRunSet(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetId") Long runSetId) throws Exception {
        logger.info("received post alias by runSet id=" + runSetId);
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
            RunSet checkRecord = runSetService.selectByPrimaryKey(runSetId);
            if (checkRecord == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
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
            RunSetAliasLinkExample linkExample = new RunSetAliasLinkExample();
            linkExample.or().andRunSetIdEqualTo(runSetId).andAliasIdIn(idsList);
            long linkCount = runSetAliasLinkService.countByExample(linkExample);
            if (linkCount != idsList.size()) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "输入中存在未关联到此RunSet的Alias。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            // start the delete
            AliasExample recordExample = new AliasExample();
            recordExample.or().andDeletedEqualTo(false).andIdIn(idsList);
            List<Alias> finalRecords = aliasService.selectByExample(recordExample);
            int deleteResult = runSetAliasLinkService.deleteByExample(linkExample);
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
                String message = "存在已删除的Alias关联到此RunSet，数据不一致。请与管理员联系。并提供唯一码[" + exuuid + "]";
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
                String message = "删除RunSet关联的Alias操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
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
    // =====runSet alias link end =====
}
