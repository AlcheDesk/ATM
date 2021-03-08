package com.meowlomo.atm.external.ems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.RunMapper;
import com.meowlomo.atm.core.mapper.RunSetJobLinkMapper;
import com.meowlomo.atm.core.mapper.RunSetReferenceMapper;
import com.meowlomo.atm.core.mapper.RunSetResultJobLinkMapper;
import com.meowlomo.atm.core.mapper.RunSetResultMapper;
import com.meowlomo.atm.core.mapper.RunSetTestCaseLinkMapper;
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetJobLink;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;
import com.meowlomo.atm.core.model.RunSetResultJobLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.service.filter.RunSetContentFilterService;
import com.meowlomo.atm.external.ems.api.EMSApiService;
import com.meowlomo.atm.external.ems.model.Job;
import com.meowlomo.atm.external.ems.model.Task;
import com.meowlomo.atm.external.ems.service.EMSJobService;
import com.meowlomo.atm.external.ems.service.EMSTaskService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EMSJobServiceImpl implements EMSJobService {

    @Value("${meowlomo.atm.execute.termination.connection-timeout-seconds:10}")
    private int CONNECTION_TIMEOUT;

    @Autowired
    private EMSApiService emsApiService;
    @Autowired
    private EMSTaskService emsTaskService;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    @Value("${meowlomo.atm.execute.job-default-priority:10}")
    private int JOB_DEFAULT_PRIORITY;
    private final Logger logger = LoggerFactory.getLogger(EMSJobServiceImpl.class);
    @Autowired
    private RunMapper runMapper;
    @Autowired
    private RunSetContentFilterService runSetContentFilterService;
    @Autowired
    private RunSetJobLinkMapper runSetJobLinkMapper;
    @Autowired
    private RunSetReferenceMapper runSetReferenceMapper;
    @Autowired
    private RunSetResultJobLinkMapper runSetResultJobLinkMapper;
    @Autowired
    private RunSetResultMapper runSetResultMapper;
    @Autowired
    private RunSetTestCaseLinkMapper runSetTestCaseLinkMapper;
    @Value("${meowlomo.atm.execute.send-job-size:5}")
    private int SEND_JOB_SIZE;
    @Autowired
    private TestCaseMapper testCaseMapper;

    @Override
    public Job buildJobForRunSet(RunSet runSetRef, Run executionInputRun) {
        Job job = this.createJobFromSystemProperties();
        job = this.transferRunInfoToJob(job, executionInputRun);
        job = this.transferRunSetInfoToJob(job, runSetRef);
        List<Task> tasks = this.createTasksForJob(runSetRef, executionInputRun);
        job.setTasks(tasks);
        return job;
    }

    @Override
    public Job buildJobForRunSetResultForErrorAndFailRuns(Long runSetResultId) throws JsonProcessingException {
        // get the error and failed runs from the run set result
        RunSetResult runSetResult = runSetResultMapper.selectByPrimaryKey(runSetResultId);
        Run run = null;
        if (runSetResult.getRun() == null) {
            run = new Run();
            run.setGroup(runSetResult.getGroup());
            run.setType(runSetResult.getRunType());
        }
        else {
            run = jacksonConverter.getObjectMapper().treeToValue(runSetResult.getRun(), Run.class);
        }
        // get the runset first
        RunSet runSet = runSetReferenceMapper.selectByPrimaryKey(runSetResult.getRunSetId());
        // change the name of the run set to rerun
        runSet.setName(runSet.getName() + "[RERUN]");
        // get changed run
        List<Run> targetRuns = getRerunRunsForRunSetResult(runSetResultId);
        logger.debug("total run {} for failed and error run set result id:{} need to be reexecuted", targetRuns.size(),
                runSetResult);
        // refresh runSet
        RunSet newRunSetRef = buildRunSetRefFromRerunRuns(runSet, targetRuns);
        return this.buildJobForRunSet(newRunSetRef, run);
    }

    @Override
    public List<Job> buildJobsForRunSets(List<RunSet> runSetsFull, Run executionInputRun) {
        List<Job> returnJobs = new ArrayList<Job>();
        for (RunSet runSet : runSetsFull) {
            returnJobs.add(this.buildJobForRunSet(runSet, executionInputRun));
        }
        return returnJobs;
    }

    private RunSet buildRunSetRefFromRerunRuns(RunSet runSetRef, List<Run> targetRuns) {
        List<Long> runSetTestCaseLinkIds = new ArrayList<Long>();
        for (Run targetRun : targetRuns) {
            Long runSetTestCaseLinkId = targetRun.getRunSetTestCaseLinkId();
            if (runSetTestCaseLinkId != null) {
                runSetTestCaseLinkIds.add(runSetTestCaseLinkId);
            }
        }
        // refresh runSettestCaseLink
        List<RunSetTestCaseLink> runSetTestCaseLinks = new ArrayList<RunSetTestCaseLink>();
        if (runSetTestCaseLinkIds.size() > 0) {
            RunSetTestCaseLinkExample runSetTestCaseLinkExample = new RunSetTestCaseLinkExample();
            runSetTestCaseLinkExample.createCriteria().andIdIn(runSetTestCaseLinkIds);
            runSetTestCaseLinks = runSetTestCaseLinkMapper.selectByExample(runSetTestCaseLinkExample);
        }
        runSetRef.setRunSetTestCaseLinks(runSetTestCaseLinks);
        // refresh testCase
        List<Long> newTestCaseIds = new ArrayList<Long>();
        for (RunSetTestCaseLink runSetTestCaseLink : runSetTestCaseLinks) {
            newTestCaseIds.add(runSetTestCaseLink.getTestCaseId());
        }
        TestCaseExample testCaseExample = new TestCaseExample();
        testCaseExample.createCriteria().andIdIn(newTestCaseIds);
        List<TestCase> newTestCases = testCaseMapper.selectByExample(testCaseExample);
        runSetRef.setTestCases(newTestCases);
        return runSetRef;
    }

    private List<Task> buildTasksForRunSet(RunSet runSetWithExpandedLinks, Run executionInputRun) {
        List<Task> returnTasks = new ArrayList<Task>();
        // remove test case overwrite from execution run input
        executionInputRun = this.filterExecutionInputRun(executionInputRun);
        // set priority
        if (runSetWithExpandedLinks.getPriority() == null) {
            executionInputRun.setPriority(JOB_DEFAULT_PRIORITY);
        }
        // get the expanded run set test case link from the run set
        List<RunSetTestCaseLink> runSetTestCaseLinks = runSetWithExpandedLinks.getRunSetTestCaseLinks();
        if (runSetTestCaseLinks != null) {
            for (int i = 0; i < runSetTestCaseLinks.size(); i++) {
                RunSetTestCaseLink runSetTestCaseLink = runSetTestCaseLinks.get(i);
                // get the test case first
                // filter deleted testCase
                Long testCaseId = runSetTestCaseLink.getTestCaseId();
                TestCaseExample testCaseExample = new TestCaseExample();
                testCaseExample.createCriteria().andIdEqualTo(testCaseId).andDeletedEqualTo(true);
                if (testCaseId != null && testCaseMapper.countByExample(testCaseExample) == 0) {
                    // final transform
                    List<Task> executableTasks = emsTaskService.buildTasksForLinkedTestCase(executionInputRun, runSetTestCaseLink);
                    if (executableTasks.isEmpty()) {

                    }
                    else {
                        returnTasks.addAll(executableTasks);
                    }
                }
            }
        }
        return returnTasks;
    }

    private Job createJobFromSystemProperties() {
        Job job = new Job();
        job.setType(RuntimeVariables.property.get("meowlomo.ems.job.type"));
        job.setPriority(Integer.valueOf(RuntimeVariables.property.get("meowlomo.ems.job.priority")));
        job.setStatus(RuntimeVariables.property.get("meowlomo.ems.job.status"));
        return job;
    }

    private List<Task> createTasksForJob(RunSet runSetRef, Run executionInputRun) {
        RunSet expandedRunSet = runSetContentFilterService.expandReferenecingRunSetTestCaseLinkForRunSet(runSetRef);
        return this.buildTasksForRunSet(expandedRunSet, executionInputRun);
    }

    private Run filterExecutionInputRun(Run executionInputRun) {
        executionInputRun.setTestCaseOverwriteId(null);
        executionInputRun.setTestCaseOverwrite(null);
        executionInputRun.setDriverPackId(null);
        executionInputRun.setDriverPack(null);
        return executionInputRun;
    }

    private void generateLinksForResponsedJob(Job job) {
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        RunSet runSet = mapper.convertValue(job.getParameter(), RunSet.class);
        UUID jobUUID = job.getUuid();
        // set test case to task link
        RunSetJobLink runSetJobLink = new RunSetJobLink();
        runSetJobLink.setJobUuid(jobUUID);
        runSetJobLink.setRunSetId(runSet.getId());
        runSetJobLink.setRunSetUuid(runSet.getUuid());
        runSetJobLinkMapper.insert(runSetJobLink);
        // set run to task link
        UUID runSetResultUuid = job.getExternalIdentifier();
        RunSetResultExample runSetResultExample = new RunSetResultExample();
        runSetResultExample.createCriteria().andUuidEqualTo(runSetResultUuid);
        List<RunSetResult> runSetResults = runSetResultMapper.selectByExample(runSetResultExample);
        if (runSetResults.isEmpty()) {
            logger.debug("Job {} for run set result uuid:{} is missing, it will not be processed", job.getName(),
                    runSetResultUuid.toString());
        }
        RunSetResult runSetResult = runSetResults.get(0);
        Long runSetResultId = runSetResult.getId();
        RunSetResultJobLink runSetResultJobLink = new RunSetResultJobLink();
        runSetResultJobLink.setJobUuid(jobUUID);
        runSetResultJobLink.setRunSetResultId(runSetResultId);
        runSetResultJobLink.setRunSetResultUuid(job.getExternalIdentifier());
        runSetResultJobLinkMapper.insertSelective(runSetResultJobLink);
    }

    private List<Run> getRerunRunsForRunSetResult(Long runSetResultId) {
        RunExample runExample = new RunExample();
        runExample.createCriteria().andRunSetResultIdEqualTo(runSetResultId)
                .andStatusIn(RuntimeVariables.RERUN_STATUSES);
        List<Run> runs = runMapper.selectByExample(runExample);
        // do reset
        List<Run> newRuns = new ArrayList<Run>();
        for (Run targetRun : runs) {
            newRuns.add(this.resetRunFieldForRerun(targetRun));
        }
        return newRuns;
    }

    @Override
    public List<Job> insertRecords(List<Job> jobs, Run run) throws JsonProcessingException {
        List<Job> returnRecrods = new ArrayList<Job>();
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        // get the tasks from each job
        for (Job job : jobs) {
            RunSet runSet = objectMapper.treeToValue(job.getParameter(), RunSet.class);
            logger.debug("getting runset from job " + runSet.toString());
            // insert the run set result first
            RunSetResult runSetResult = new RunSetResult();
            runSetResult.setName(runSet.getName());
            runSetResult.setPriority(runSet.getPriority());
            runSetResult.setStatus("NEW");
            runSetResult.setGroup(runSet.getGroup());
            runSetResult.setType(runSet.getType());
            runSetResult.setRunSetId(runSet.getId());
            runSetResult.setLog("Created for runset " + runSet.getId());
            runSetResult.setRunType(run.getType());
            runSetResult.setSourceType(runSet.getSourceType());
            runSetResult.setComment(runSet.getComment());
            runSetResult.setRun(objectMapper.convertValue(run, JsonNode.class));
            long insertResult = runSetResultMapper.insertSelective(runSetResult);
            // // create email from runSetResult
            if (insertResult == 1) {
                runSetResult = runSetResultMapper.selectByPrimaryKey(runSetResult.getId());
                // get the tasks
                List<Task> tasks = jacksonConverter.getObjectMapper().convertValue(job.getTasks(),
                        new TypeReference<List<Task>>() {
                        });
                List<Task> taskInserResult = emsTaskService.insertRecords(tasks, runSetResult.getId());
                if (taskInserResult.size() == tasks.size()) {
                    // set the task to the job
                    job.setTasks(taskInserResult);
                    job.setExternalIdentifier(runSetResult.getUuid());
                    returnRecrods.add(job);
                }
            }
            else {

            }
        }
        return returnRecrods;
    }

    private boolean processEmptyJob(Job job) {
        // check the job is empty or not
        List<Task> tasks = job.getTasks();
        UUID runSetResultUuid = null;
        if (job.getExternalIdentifier() != null && tasks.size() == 0) {
            runSetResultUuid = job.getExternalIdentifier();
            // get the run set result
            RunSetResultExample runSetResultExample = new RunSetResultExample();
            runSetResultExample.createCriteria().andUuidEqualTo(runSetResultUuid);
            List<RunSetResult> runSetResults = runSetResultMapper.selectByExample(runSetResultExample);
            if (runSetResults.isEmpty()) {
                logger.debug("Job {} for run set result uuid:{} is missing, it will not be processed", job.getName(),
                        runSetResultUuid.toString());
                return false;
            }
            RunSetResult runSetResult = runSetResults.get(0);
            Long runSetResultId = runSetResult.getId();
            // check is the run set result is empty
            RunExample runExample = new RunExample();
            runExample.createCriteria().andRunSetResultIdEqualTo(runSetResultId);
            List<Run> runs = runMapper.selectByExample(runExample);
            if (runs.size() == 0) {
                // update the run set result
                RunSetResult updateRunSetResult = new RunSetResult();
                updateRunSetResult.setId(runSetResultId);
                updateRunSetResult.setLog("The Run Set Result id empty. Set to PASS");
                updateRunSetResult.setStatus("PASS");
                int updateResult = runSetResultMapper.updateByPrimaryKeySelective(updateRunSetResult);
                logger.debug("Job {} for run set result id:{} is empty, it will marked to PASS. update return {}",
                        job.getName(), runSetResultId, updateResult);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            logger.debug(" job name : {}, indentifier: {}, tasks size:{}", job.getName(), job.getExternalIdentifier(),
                    job.getTasks().size());
            return false;
        }

    }

    private Run resetRunFieldForRerun(Run run) {
        run.setId(null);
        run.setStatus(null);
        run.setLog("Created for rerun for run set result " + run.getRunSetResultId());
        run.setRunSetResultId(null);
        run.setTriggerSource("INTERNAL");
        return run;
    }

    @Override
    public long sendJobsToEMS(List<Job> jobs) {
        // check if we need to send the jobs
        if (jobs.isEmpty()) {
            return 0;
        }
        
        for (Job job : jobs) {
            if (job == null) {
                UUID exuuid = UUID.randomUUID();
                String message = String.format("The job seending to ems is null.");
                String trace = Thread.currentThread().getStackTrace().toString();
                String code = "EJS";
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
            else if (this.processEmptyJob(job)) {
                UUID exuuid = UUID.randomUUID();
                String message = String.format("The job is missing some required fields, please contact system admin. this job will be ignore");
                String trace = Thread.currentThread().getStackTrace().toString();
                String code = "EJS";
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
        }

        MeowlomoResponse response = emsApiService.sendJobsToEMS(jobs);
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        if (response != null) {

            List<Job> responsedJobs = mapper.convertValue(response.getData(), new TypeReference<List<Job>>() {
            });
            
            if (responsedJobs.size() != jobs.size()) {
                UUID exuuid = UUID.randomUUID();
                String message = String.format("Response from EMS server for job insert has different number count, please contact system admin.");
                String trace = Thread.currentThread().getStackTrace().toString();
                String code = "ETS";
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
            for (Job jobFromResponse : responsedJobs) {
                this.generateLinksForResponsedJob(jobFromResponse);
                // set the links for tasks
                List<Task> tasks = jobFromResponse.getTasks();
                for (Task task : tasks) {
                    emsTaskService.generateLinksForReponsedTask(task);
                }
            }
            return responsedJobs.size();
        } else {
            UUID exuuid = UUID.randomUUID();
            String message = String.format("Null response from EMS server job call please check log.");
            String trace = Thread.currentThread().getStackTrace().toString();
            String code = "EJS";
            throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
        }
    }
    
    @Override
    public MeowlomoResponse terminationJob(UUID jobUuid) {
        if (jobUuid == null) {
            throw new NullPointerException("Null for Job UUID");
        }
        // set the base url base on the property settings
        MeowlomoResponse response = emsApiService.terminateJobByUuid(jobUuid.toString());
        // check the result
        if (response != null) {
            // get the body
            return response;
        } else {
            logger.error("Termination for job [" + jobUuid.toString() + "] hash null response");
            return null;
        }
    }


    private Job transferRunInfoToJob(Job job, Run run) {
        if (run != null) {
            if (run.getGroup() == null) {
                job.setGroup(RuntimeVariables.property.get("meowlomo.ems.job.group"));
            }
            else {
                job.setGroup(run.getGroup());
            }
            return job;
        }
        else {
            return null;
        }
    }

    private Job transferRunSetInfoToJob(Job job, RunSet runSet) {
        if (runSet != null) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            job.setName(runSet.getName());
            job.setParameter(objectMapper.convertValue(runSet, JsonNode.class));
            return job;
        }
        else {
            return job;
        }
    }


}
