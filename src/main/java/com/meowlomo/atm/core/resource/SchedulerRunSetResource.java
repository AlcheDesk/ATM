package com.meowlomo.atm.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.annotation.LogUserActivity;
import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;
import com.meowlomo.atm.core.resource.exception.CustomForbiddenException;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.atm.core.resource.exception.CustomNotAllowedException;
import com.meowlomo.atm.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.atm.core.resource.exception.CustomNotFoundException;
import com.meowlomo.atm.core.resource.exception.CustomNotSupportedException;
import com.meowlomo.atm.core.resource.exception.CustomServiceUnavailableException;
import com.meowlomo.atm.core.resource.model.MeowlomoErrorResponse;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.validator.BeanValidator;
import com.meowlomo.atm.quartz.model.JobDescriptor;
import com.meowlomo.atm.quartz.model.TriggerDescriptor;
import com.meowlomo.atm.quartz.service.JobService;
import com.meowlomo.atm.quartz.service.TriggerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/scheduler/runSets")
@Api(value = "application resources", produces = "application/json")
public class SchedulerRunSetResource {

    private final Logger logger = LoggerFactory.getLogger(SchedulerRunSetResource.class);

    private final static String JOB_GROUP = "RUN_SET_SCHEDULER_GROUP";

    private static final String ERROR_TYPE = "QTZ";

    @Autowired
    @Qualifier("runSetJobService")
    private JobService jobService;

    @Autowired
    private TriggerService triggerService;

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
    @ApiOperation(value = "读取RunSetSchedulerJob", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "group", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse selectJobs(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest)
            throws Exception {
        logger.info("received quartzJob select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                List<JobDescriptor> records = jobService.getAllJobs();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                List<JobDescriptor> records = jobService.getAllJobs();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", records.size());
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                if (queryParams.containsKey("group") && !queryParams.getFirst("group").isEmpty()) {
                    List<JobDescriptor> records = jobService.getGroupJobs(JOB_GROUP);
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", records.size());
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    return new MeowlomoResponse(null, null, null);
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
            String code = ERROR_TYPE + "CORE01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @Path("/{id}/triggers")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取QuartzJob", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "group", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse selectJobTriggers(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long runSetId) throws Exception {
        logger.info("received runSet shceduler trigger select call. runSet id :" + runSetId);
        try {
            JobDescriptor jobDescriptor = jobService.getJob(Long.toString(runSetId), JOB_GROUP);
            if (jobDescriptor != null) {
                List<TriggerDescriptor> triggerDescriptors = jobDescriptor.getTriggerDescriptors();
                for (TriggerDescriptor triggerDescriptor : triggerDescriptors) {
                    // transferCronToCronDetails or transferStartTimeToStartTimeDetails
                    if (triggerDescriptor.getCronExpression() != null) {
                        triggerService.transferCronToCronDetails(triggerDescriptor);
                    }
                    if (triggerDescriptor.getStartTime() != null) {
                        triggerService.transferStartTimeToStartTimeDetails(triggerDescriptor);
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", triggerDescriptors.size());
                return new MeowlomoResponse(metadata, triggerDescriptors, null);
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
            String code = ERROR_TYPE + "CORE01SYS";
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
    @Path("/{id}/triggers/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除单个Trigger", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "quartzJob id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteJobTriggers(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") long runSetId, @PathParam("uuid") String triggerUuid) {
        logger.info("received quartzJob delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            JobDescriptor jobDescriptor = jobService.getJob(Long.toString(runSetId), JOB_GROUP);
            if (jobDescriptor == null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                TriggerDescriptor triggerDescriptor = triggerService.getTrigger(triggerUuid, JOB_GROUP);
                if (triggerDescriptor != null) {
                    // delete the trigger
                    boolean deleteResult = triggerService.removeTrigger(triggerDescriptor);
                    if (deleteResult) {
                        // transferCronToCronDetails or transferStartTimeToStartTimeDetails
                        if (triggerDescriptor.getCronExpression() != null) {
                            triggerService.transferCronToCronDetails(triggerDescriptor);
                        }
                        if (triggerDescriptor.getStartTime() != null) {
                            triggerService.transferStartTimeToStartTimeDetails(triggerDescriptor);
                        }
                        ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                        metadata.put("count", 1);
                        return new MeowlomoResponse(metadata, triggerDescriptor, null);
                    }
                    else {
                        ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                        metadata.put("count", 0);
                        return new MeowlomoResponse(metadata, null, null);
                    }
                }
                else {
                    UUID exuuid = UUID.randomUUID();
                    String trace = "exception UUID=" + exuuid + " trigger with UUID = " + triggerUuid
                            + " does not exists.";
                    String message = "不存在UUID为" + triggerUuid + "的对象，无法删除。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "CORE01DEL";
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
            String code = ERROR_TYPE + "CORE03SYS";
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
    @Path("/{id}/triggers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新RunSetSchedulerJob", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
    public MeowlomoResponse updateTriggers(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            TriggerDescriptor[] records) throws Exception {
        logger.info("received patch quartzJob by id call " + uriInfo.getPath());
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

            // validation
            BeanValidator.BeanValidation(records, ERROR_TYPE);

            List<TriggerKey> targetkeys = new ArrayList<TriggerKey>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                TriggerDescriptor record = records[i];
                if (record.getName() != null) {
                    targetkeys.add(TriggerKey.triggerKey(record.getName(), JOB_GROUP));
                }
            }
            if (targetkeys.size() != records.length) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "部分更新请求不含UUID。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
            // do update one by one
            List<String> errorIndex = new ArrayList<String>();
            List<TriggerDescriptor> finalRecords = new ArrayList<TriggerDescriptor>();
            for (TriggerDescriptor triggerDescriptor : records) {
                // transferCronDetailsToCron or transferStartTimeDetailsToStartTime
                if (triggerDescriptor.getCronDetails() != null) {
                    triggerService.transferCronDetailsToCron(triggerDescriptor);
                }
                else if (triggerDescriptor.getStartTimeTimestamp() != null) {
                    triggerService.transferStartTimeDetailsToStartTime(triggerDescriptor);
                }
                TriggerDescriptor result = null;
                if ((triggerDescriptor.getStatus() != null) && (triggerDescriptor.getName() != null)) {
                    boolean updateResultBoolean = false;
                    if (triggerDescriptor.getStatus().equalsIgnoreCase("PAUSED")) {
                        updateResultBoolean = triggerService.pauseTrigger(triggerDescriptor.getName(), JOB_GROUP);
                    }
                    else if (triggerDescriptor.getStatus().equalsIgnoreCase("SCHEDULED")) {
                        updateResultBoolean = triggerService.resumeTrigger(triggerDescriptor.getName(), JOB_GROUP);
                    }
                    if (updateResultBoolean) {
                        result = triggerDescriptor;
                    }
                }
                else {
                    result = triggerService.updateTrigger(triggerDescriptor.getName(), JOB_GROUP, triggerDescriptor);
                }
                if (result == null) {
                    errorIndex.add(triggerDescriptor.getName());
                }
                else {
                    finalRecords.add(result);
                }
            }

            // check all update sucess
            if (errorIndex.isEmpty()) {
                // transferCronToCronDetails or transferStartTimeToStartTimeDetails
                for (TriggerDescriptor finalRecord : finalRecords) {
                    if (finalRecord.getCronExpression() != null) {
                        triggerService.transferCronToCronDetails(finalRecord);
                    }
                    if (finalRecord.getStartTime() != null) {
                        triggerService.transferStartTimeToStartTimeDetails(finalRecord);
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, triggerService.setTriggersStatus(finalRecords), null);
            }
            else {// not all success
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " could not patch all records ";
                String message = "部分或全部更新失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE03PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                MeowlomoErrorResponse errorReponse = new MeowlomoErrorResponse();
                errorReponse.setCode(code);
                errorReponse.setDeveloperMessage(trace);
                errorReponse.setMessage(message);
                errorReponse.setUuid(exuuid);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, triggerService.setTriggersStatus(finalRecords), errorReponse);
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
    @Path("/{id}/triggers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "替换或添加Triggers", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long runSetId, TriggerDescriptor[] records) {
        logger.info("received put quartzJob by primary id call");
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

            // check job exist
            JobDescriptor jobDescriptor = jobService.getJob(Long.toString(runSetId), JOB_GROUP);
            if (jobDescriptor == null) {
                jobDescriptor = new JobDescriptor();
                jobDescriptor.setName(Long.toString(runSetId));
                jobDescriptor.setGroup(JOB_GROUP);
                jobDescriptor = jobService.scheduleJob(jobDescriptor);
                if (jobDescriptor == null) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
            }
            // with id do replace, no id do insert
            List<Long> errorIndex = new ArrayList<Long>();
            List<TriggerDescriptor> finalRecords = new ArrayList<TriggerDescriptor>();
            for (int i = 0; i < records.length; i++) {
                TriggerDescriptor triggerDescriptor = records[i];
                if (triggerDescriptor.getName() == null) {
                    // do insert
                    // transferCronDetailsToCron or transferStartTimeDetailsToStartTime
                    if (triggerDescriptor.getCronDetails() != null) {
                        triggerService.transferCronDetailsToCron(triggerDescriptor);
                    }
                    else if (triggerDescriptor.getStartTimeTimestamp() != null) {
                        triggerService.transferStartTimeDetailsToStartTime(triggerDescriptor);
                    }
                    // delete old ones before insert new ones
                    // check insert trigger type
                    if (triggerDescriptor.getCronExpression() != null) {
                        List<TriggerDescriptor> oldTriggerDescriptors = jobDescriptor.getTriggerDescriptors();
                        for (TriggerDescriptor oldTriggerDescriptor : oldTriggerDescriptors) {
                            if (oldTriggerDescriptor.getCronExpression() != null) {
                                triggerService.removeTrigger(oldTriggerDescriptor);
                            }
                        }
                    }
                    else {
                        // delete the not cron trigger
                        List<TriggerDescriptor> oldTriggerDescriptors = jobDescriptor.getTriggerDescriptors();
                        for (TriggerDescriptor oldTriggerDescriptor : oldTriggerDescriptors) {
                            if (oldTriggerDescriptor.getCronExpression() == null) {
                                triggerService.removeTrigger(oldTriggerDescriptor);
                            }
                        }
                    }
                    // after delete and do insert
                    // check second step job exist
                    JobDescriptor secondStepJobDescriptor = jobService.getJob(Long.toString(runSetId), JOB_GROUP);
                    if (secondStepJobDescriptor == null) {
                        secondStepJobDescriptor = new JobDescriptor();
                        secondStepJobDescriptor.setName(Long.toString(runSetId));
                        secondStepJobDescriptor.setGroup(JOB_GROUP);
                        secondStepJobDescriptor = jobService.scheduleJob(secondStepJobDescriptor);
                        if (secondStepJobDescriptor == null) {
                            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                            metadata.put("count", 0);
                            return new MeowlomoResponse(metadata, null, null);
                        }
                    }
                    TriggerDescriptor insertResult = triggerService.addTrigger(secondStepJobDescriptor,
                            triggerDescriptor);
                    if (insertResult == null) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(insertResult);
                    }
                }
                else {
                    // do update
                    // transferCronDetailsToCron or transferStartTimeDetailsToStartTime
                    if (triggerDescriptor.getCronDetails() != null) {
                        triggerService.transferCronDetailsToCron(triggerDescriptor);
                    }
                    else if (triggerDescriptor.getStartTimeTimestamp() != null) {
                        triggerService.transferStartTimeDetailsToStartTime(triggerDescriptor);
                    }
                    TriggerDescriptor updateResult = null;
                    if ((triggerDescriptor.getStatus() != null) && (triggerDescriptor.getName() != null)) {
                        boolean updateResultBoolean = false;
                        if (triggerDescriptor.getStatus().equalsIgnoreCase("PAUSED")) {
                            updateResultBoolean = triggerService.pauseTrigger(triggerDescriptor.getName(), JOB_GROUP);
                        }
                        else if (triggerDescriptor.getStatus().equalsIgnoreCase("SCHEDULED")) {
                            updateResultBoolean = triggerService.resumeTrigger(triggerDescriptor.getName(), JOB_GROUP);
                        }
                        if (updateResultBoolean) {
                            updateResult = triggerDescriptor;
                        }
                    }
                    else {
                        updateResult = triggerService.updateTrigger(triggerDescriptor.getName(), JOB_GROUP,
                                triggerDescriptor);
                    }
                    if (updateResult == null) {
                        errorIndex.add((long) i);
                    }
                    else {
                        finalRecords.add(updateResult);
                    }
                }
            }

            // check all update or replace sucess
            if (errorIndex.isEmpty()) {
                for (TriggerDescriptor finalRecord : finalRecords) {
                    // transferCronToCronDetails or transferStartTimeToStartTimeDetails
                    if (finalRecord.getCronExpression() != null) {
                        triggerService.transferCronToCronDetails(finalRecord);
                    }
                    if (finalRecord.getStartTime() != null) {
                        triggerService.transferStartTimeToStartTimeDetails(finalRecord);
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, triggerService.setTriggersStatus(finalRecords), null);
            }
            else {// not all success
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " could not patch all records ";
                String message = "部分或全部更新添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE03PAT";
                logger.error(trace, httpServletRequest.getContextPath());
                MeowlomoErrorResponse errorReponse = new MeowlomoErrorResponse();
                errorReponse.setCode(code);
                errorReponse.setDeveloperMessage(trace);
                errorReponse.setMessage(message);
                errorReponse.setUuid(exuuid);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, triggerService.setTriggersStatus(finalRecords), errorReponse);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException |

                CustomServiceUnavailableException ex) {
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
    @Path("/{id}/triggers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加RunSetSchedulerJob", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse insertTrigger(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("id") Long runSetId, TriggerDescriptor[] records) {
        logger.info("received post quartzJob call ");
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

            // check job exist
            JobDescriptor jobDescriptor = jobService.getJob(Long.toString(runSetId), JOB_GROUP);
            if (jobDescriptor == null) {
                jobDescriptor = new JobDescriptor();
                jobDescriptor.setName(Long.toString(runSetId));
                jobDescriptor.setGroup(JOB_GROUP);
                jobDescriptor = jobService.scheduleJob(jobDescriptor);
                if (jobDescriptor == null) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 0);
                    return new MeowlomoResponse(metadata, null, null);
                }
            }

            // add the trigger one by one to the job
            List<Long> errorIndex = new ArrayList<Long>();
            ArrayList<TriggerDescriptor> finalRecords = new ArrayList<TriggerDescriptor>();
            ArrayList<TriggerDescriptor> processedTriggers = new ArrayList<TriggerDescriptor>();
            for (int i = 0; i < records.length; i++) {
                TriggerDescriptor triggerDescriptor = records[i];
                // transferCronDetailsToCron or transferStartTimeDetailsToStartTime
                if (triggerDescriptor.getCronDetails() != null) {
                    triggerService.transferCronDetailsToCron(triggerDescriptor);
                }
                else if (triggerDescriptor.getStartTimeTimestamp() != null) {
                    triggerService.transferStartTimeDetailsToStartTime(triggerDescriptor);
                }
                TriggerDescriptor result = triggerService.addTrigger(jobDescriptor, triggerDescriptor);
                if (result != null) {
                    finalRecords.add(result);
                }
                else {
                    errorIndex.add((long) i);
                }
                processedTriggers.add(triggerDescriptor);
            }

            if (finalRecords.size() == records.length) {
                for (TriggerDescriptor finalRecord : finalRecords) {
                    // transferCronToCronDetails or transferStartTimeToStartTimeDetails
                    if (finalRecord.getCronExpression() != null) {
                        triggerService.transferCronToCronDetails(finalRecord);
                    }
                    if (finalRecord.getStartTime() != null) {
                        triggerService.transferStartTimeToStartTimeDetails(finalRecord);
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, triggerService.setTriggersStatus(finalRecords), null);
            }
            else {
                // remove all trigger
                triggerService.removeTriggers(processedTriggers);
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
}
