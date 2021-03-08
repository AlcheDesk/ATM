package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.annotation.LogUserActivity;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;
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
import com.meowlomo.atm.core.service.referrence.RunSetReferenceService;
import com.meowlomo.atm.core.service.referrence.RunSetResultReferenceService;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;
import com.meowlomo.atm.core.service.util.RunSetUtilService;
import com.meowlomo.atm.external.ems.model.Job;
import com.meowlomo.atm.external.ems.model.Task;
import com.meowlomo.atm.external.ems.service.EMSJobService;
import com.meowlomo.atm.external.ems.service.EMSTaskService;
import com.meowlomo.atm.security.model.AuthenticatedUserDetails;
import com.meowlomo.atm.security.service.AuthenticatedUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/execute")
@Api(value = "execute resources", produces = "application/json")
public class ExecutionResource {

    private final Logger logger = LoggerFactory.getLogger(ExecutionResource.class);

    @Value("${meowlomo.atm.execute.send-run-set-size:10}")
    private long MAX_RUN_SET_SIZE;
    @Value("${meowlomo.atm.execute.send-test-case-size:200}")
    private long MAX_TEST_CASE_SIZE;

    private static final String ERROR_TYPE = "EXE";

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Autowired
    private EMSTaskService emsTaskService;

    @Autowired
    private EMSJobService emsJobService;

    @Autowired
    private TestCaseReferenceService testCaseReferenceService;

    @Autowired
    private RunSetReferenceService runSetReferenceService;

    @Autowired
    private RunSetResultReferenceService runSetResultReferenceService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private RunSetUtilService runSetUtilService;

    @POST
    @LogUserActivity
    @Path("testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行单个TestCase", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "testCase ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse executeTestCase(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("testCaseId") Long testCaseId, Run[] runs) throws Exception {
        logger.info("received execute testCase call,testCaseId=" + testCaseId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (runs == null || runs.length == 0 || runs[0].getGroup() == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "发送内容为空，或不包含Group。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PST";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
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
            // set the drives and test case over write for the run
            // add trigger source
            runs[0].setTriggerSource(null);
            Run executionInputRun = runs[0];
            AuthenticatedUserDetails authenticatedUserDetails = authenticatedUserService.getAuthenticateUserDetails();
            if (authenticatedUserDetails != null) {
                String ipAddressRequestCameFrom = authenticatedUserDetails.getIpAddress();
                executionInputRun.setTriggerSource(ipAddressRequestCameFrom);
            }
            executionInputRun.setTestCaseId(testCaseId);
            List<Task> executableTasks = emsTaskService.buildTasksForTestCase(executionInputRun,
                    executionInputRun.getTestCaseId(), executionInputRun.getDriverPackId(),
                    executionInputRun.getTestCaseOverwriteId());
            if (executableTasks != null && !executableTasks.isEmpty()) {
                logger.debug(
                        executableTasks.size() + " was created for test case execution for test case id:{}, name:{}",
                        testCaseRef.getId(), testCaseRef.getName());
                if (queryParams.getFirst("dryRun") != null) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    return new MeowlomoResponse(null, executableTasks, null);
                }
                // insert the tasks
                List<Task> insertedTasks = emsTaskService.insertRecords(executableTasks, null);
                // send to ems
                long responsedTaskCount = emsTaskService.sendTasksToEMS(insertedTasks);
                if (insertedTasks.size() != executableTasks.size() || insertedTasks.size() != responsedTaskCount) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String trace = "exception UUID=" + exuuid + " target count <> insert count ";
                    String message = "无法完成添加，添加数与目标数不一致，将会回滚。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "02POS";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
                }

                // put the task to the queue for later process
//                RuntimeVariables.TASK_QUEUE.addAll(insertedTasks);
                logger.debug(executableTasks.size() + " we queue for test case execution for test case id:{}, name:{}", testCaseRef.getId(), testCaseRef.getName());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", insertedTasks.size());
                return new MeowlomoResponse(metadata, insertedTasks, null);
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

    @POST
    @LogUserActivity
    @Path("runSets/{runSetId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行单个RunSet", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "RunSet ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse executeRunSet(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("runSetId") Long runSetId, Run[] runs) throws Exception {
        logger.info("received execute runSet call,runSetId=" + runSetId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (runs == null || runs.length == 0 || runs[0].getGroup() == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "发送内容为空，或不包含Group。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PST";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            RunSet runSetRef = runSetReferenceService.selectByPrimaryKey(runSetId);
            if (runSetRef == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetId + "的RunSet不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // check if the test case is empty
            if (runSetRef.getRunSetTestCaseLinks().isEmpty()) { return new MeowlomoResponse(null, null, null); }
            // get all test case from the run set
            // add trigger source
            Run executionInputRun = runs[0];
            executionInputRun.setTriggerSource(null);
            AuthenticatedUserDetails authenticatedUserDetails = authenticatedUserService.getAuthenticateUserDetails();
            if (authenticatedUserDetails != null) {
                String ipAddressRequestCameFrom = authenticatedUserDetails.getIpAddress();
                executionInputRun.setTriggerSource(ipAddressRequestCameFrom);
            }
            Job createdJobForPost = emsJobService.buildJobForRunSet(runSetRef, executionInputRun);
            if (createdJobForPost != null) {
                logger.debug("runset execution job created {} to for run set id: {}, name: {}",
                        createdJobForPost.getName(), runSetRef.getId(), runSetRef.getName());
                if (queryParams.getFirst("dryRun") != null) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    return new MeowlomoResponse(null, createdJobForPost, null);
                }
                // insert the jobs
                List<Job> insertedJobs = emsJobService.insertRecords(Arrays.asList(createdJobForPost), executionInputRun);
                // send to ems
                long responsedJobCount = emsJobService.sendJobsToEMS(insertedJobs);
                if (insertedJobs.size() != 1 || responsedJobCount != 1) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String trace = "exception UUID=" + exuuid + " target count <> insert count ";
                    String message = "无法完成添加，添加数与目标数不一致，将会回滚。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "02POS";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
                }

                // put the jobs to the queue for later process
//                RuntimeVariables.JOB_QUEUE.addAll(insertedJob);
                logger.debug("runset execution job added queue {} for run set id: {}, name: {}",
                        createdJobForPost.getName(), runSetRef.getId(), runSetRef.getName());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", insertedJobs.size());
                return new MeowlomoResponse(metadata, insertedJobs, null);
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + runSetId + " does not exists.";
                String message = "无法创建runSetId为" + runSetId + "的执行。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03F";
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @POST
    @LogUserActivity
    @Path("runSetResults/{runSetResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行单个RunSetResult", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "RunSetResult ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse executeRunSetResult(@Context UriInfo uriInfo,
            @Context HttpServletRequest httpServletRequest, @PathParam("runSetResultId") Long runSetResultId)
            throws Exception {
        logger.info("received execute runSetResult call,runSetResultId=" + runSetResultId);
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            RunSetResult runSetResult = runSetResultReferenceService.selectByPrimaryKey(runSetResultId);
            if (runSetResult == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " record with id not exist ";
                String message = "ID为" + runSetResultId + "的RunSetResult不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01GET";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // get all test case from the run set
            Job createdJobForPost = emsJobService.buildJobForRunSetResultForErrorAndFailRuns(runSetResultId);
            if (createdJobForPost != null) {
                logger.debug("fail or error rerun job created {} to for run set result id: {}, name: {}",
                        createdJobForPost.getName(), runSetResult.getId(), runSetResult.getName());
                if (queryParams.getFirst("dryRun") != null) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    return new MeowlomoResponse(null, createdJobForPost, null);
                }
                // insert the jobs
                Run run = null;
                if (runSetResult.getRun() == null) {
                    run = new Run();
                    run.setGroup(runSetResult.getGroup());
                    run.setType(runSetResult.getRunType());
                }
                else {
                    run = jacksonConverter.getObjectMapper().treeToValue(runSetResult.getRun(), Run.class);
                }
                List<Job> insertedJobs = emsJobService.insertRecords(Arrays.asList(createdJobForPost), run);
                // send to ems
                long responsedJobCount = emsJobService.sendJobsToEMS(insertedJobs);
                if (insertedJobs.size() != 1 || responsedJobCount != 1) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String trace = "exception UUID=" + exuuid + " target count <> insert count ";
                    String message = "无法完成添加，添加数与目标数不一致，将会回滚。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "02POS";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
                }
                // put the jobs to the queue for later process
//                RuntimeVariables.JOB_QUEUE.addAll(insertedJobs);
                logger.debug("Added fail or error rerun job {} to the execution queue", insertedJobs.get(0).getName());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", insertedJobs.size());
                return new MeowlomoResponse(metadata, insertedJobs, null);
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + runSetResultId
                        + " does not exists.";
                String message = "无法根据runSet" + runSetResultId + "创建列表复执行。问题唯一码[" + exuuid + "]";
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

    @POST
    @LogUserActivity
    @Path("aliases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "执行多个Aliases", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "Aliases ID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse executeAliases(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @Context HttpServletRequest requestContext, Run[] runs) throws Exception {
        logger.info("received execute aliases call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (runs == null || runs.length == 0 || runs[0].getGroup() == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "发送内容为空，或不包含Group。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PST";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            if (runs[0].getAliases() == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "aliasNames内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PST";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // add trigger source
            runs[0].setTriggerSource(null);
            AuthenticatedUserDetails authenticatedUserDetails = authenticatedUserService.getAuthenticateUserDetails();
            if (authenticatedUserDetails != null) {
                String ipAddressRequestCameFrom = authenticatedUserDetails.getIpAddress();
                runs[0].setTriggerSource(ipAddressRequestCameFrom);
            }
            // check all the aliases is existed
            Set<Set<String>> aliasNamesArray = runs[0].getAliases();
            Set<Long> runSetIds = runSetUtilService.getRunSetIdsByAliasNameSets(aliasNamesArray);
            if (runSetIds.size() == 0) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " runSet is not exist ";
                String message = "别名对应的runSet均不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PST";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            RunSetExample runSetExample = new RunSetExample();
            runSetExample.createCriteria().andIdIn(new ArrayList<>(runSetIds)).andDeletedEqualTo(false);
            List<RunSet> runSets = runSetReferenceService.selectByExample(runSetExample);
            List<Job> createdJobsForPost = emsJobService.buildJobsForRunSets(runSets, runs[0]);
            if (createdJobsForPost.size() > 0) {
                logger.debug("alias execution jobs created size: {} for run: {}", createdJobsForPost.size(),
                        runs[0].toString());
                if (queryParams.getFirst("dryRun") != null) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    return new MeowlomoResponse(null, createdJobsForPost, null);
                }
                // insert the jobs
                List<Job> insertedJobs = emsJobService.insertRecords(createdJobsForPost, runs[0]);
                // send to ems
                long responsedJobCount = emsJobService.sendJobsToEMS(insertedJobs);
                if (insertedJobs.size() != createdJobsForPost.size() || insertedJobs.size() != responsedJobCount) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String trace = "exception UUID=" + exuuid + " target count <> insert count ";
                    String message = "无法完成别名触发，将会回滚。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "02POS";
                    logger.error(trace, httpServletRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
                }

                // put the jobs to the queue for later process
//                RuntimeVariables.JOB_QUEUE.addAll(insertedJobs);
                logger.debug("alias execution jobs added queue size: {} for run: {}", insertedJobs.size(),
                        runs[0].toString());
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", insertedJobs.size());
                return new MeowlomoResponse(metadata, insertedJobs, null);
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + runs[0].getAliases().toString()
                        + " does not exists.";
                String message = "无法创建内容为" + runs[0].getAliases().toString() + "的别名执行。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03F";
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
        // ObjectNode metadata = JsonNodeFactory.instance.objectNode();
        // metadata.put("count", result.size());
        // return new MeowlomoResponse(null, null, null);
    }
}
