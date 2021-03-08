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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.RunMapper;
import com.meowlomo.atm.core.mapper.RunTaskLinkMapper;
import com.meowlomo.atm.core.mapper.TestCaseFullReferenceMapper;
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.mapper.TestCaseTaskLinkMapper;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunTaskLink;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseTaskLink;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.service.filter.InstructionContentFilterService;
import com.meowlomo.atm.core.service.filter.TestCaseContentFilterService;
import com.meowlomo.atm.core.service.filter.TestCaseOverwriteContentFilterService;
import com.meowlomo.atm.core.service.util.RunUtilService;
import com.meowlomo.atm.core.service.util.TestCaseOverwriteUtilService;
import com.meowlomo.atm.external.ems.api.EMSApiService;
import com.meowlomo.atm.external.ems.model.Task;
import com.meowlomo.atm.external.ems.service.EMSTaskService;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EMSTaskServiceImpl implements EMSTaskService{

    @Value("${meowlomo.atm.execute.termination.connection-timeout-seconds:10}")
    private int CONNECTION_TIMEOUT;

    @Autowired
    private EMSApiService emsApiService;

    @Autowired
    private InstructionContentFilterService instructionContentFilterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    private final Logger logger = LoggerFactory.getLogger(EMSTaskServiceImpl.class);

    @Autowired
    private RunMapper runMapper;

    @Autowired
    private RunTaskLinkMapper runTaskLinkMapper;

    @Autowired
    private RunUtilService runUtilService;

    @Value("${meowlomo.atm.execute.send-task-size:5}")
    private int SEND_TASK_SIZE;

    @Autowired
    private TestCaseContentFilterService testCaseContentFilterService;

    @Autowired
    private TestCaseFullReferenceMapper testCaseFullReferenceMapper;

    @Autowired
    private TestCaseOverwriteContentFilterService testCaseOverwriteContentFilterService;

    @Autowired
    private TestCaseOverwriteUtilService testCaseOverwriteUtilService;

    @Autowired
    private TestCaseMapper testCaseMapper;
    @Autowired
    private TestCaseTaskLinkMapper testCaseTaskLinkMapper;

    private Run buildRun(TestCase testCaseFullRef, Run executionInputRun, Long testCaseId, Long driverPackId,
            Long testCaseOverwriteId) {
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        if (executionInputRun == null || testCaseFullRef == null) { return null; }
        TestCaseOverwrite testCaseOverwriteRef;
        TestCase testCaseRefWithExpandedInstructions;
        TestCaseOverwrite testCaseOverwrtieWithExpandInsOvr = null;
        TestCase fullyExpanedAndOverwroteTestCase = null;
        // build the run from the test case
        Run processedRun = runUtilService.buildRunWithDriverPackAndTestCaseOverwriteForExecution(executionInputRun,
                testCaseId, driverPackId, testCaseOverwriteId);
        testCaseRefWithExpandedInstructions = testCaseContentFilterService
                .expandTestCaseWithReferencedInstructions(testCaseFullRef);
        if (processedRun.getTestCaseOverwrite() != null && !processedRun.getTestCaseOverwrite().isNull()) {
            try {
                testCaseOverwriteRef = objectMapper.treeToValue(processedRun.getTestCaseOverwrite(),
                        TestCaseOverwrite.class);
                testCaseOverwrtieWithExpandInsOvr = testCaseOverwriteContentFilterService
                        .expandTestCaseOverwriteWithRefereneceTestCaseOverwrite(testCaseOverwriteRef);
                processedRun.setTestCaseOverwrite(
                        objectMapper.convertValue(testCaseOverwrtieWithExpandInsOvr, JsonNode.class));
                if (testCaseOverwriteRef != null) {
                    fullyExpanedAndOverwroteTestCase = testCaseOverwriteUtilService.executeTestCaseOverwriteOnTestCase(
                            testCaseOverwrtieWithExpandInsOvr, testCaseRefWithExpandedInstructions);
                }
            }
            catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            fullyExpanedAndOverwroteTestCase = testCaseRefWithExpandedInstructions;
        }

        // repalce the instruction with instructions that comments are filtered out.
        List<Instruction> fullyProcessedInstructions = instructionContentFilterService
                .filteroutReferenceAndCommentInstructions(fullyExpanedAndOverwroteTestCase.getInstructions());
        fullyExpanedAndOverwroteTestCase.setInstructions(fullyProcessedInstructions);
        processedRun.setExecutableInstructionNumber((long) fullyProcessedInstructions.size());
        // remove the following field for saving network traffic
        // driverPack need to insert to the run
        testCaseRefWithExpandedInstructions.setTags(null);
        processedRun.setTestCase(objectMapper.convertValue(fullyExpanedAndOverwroteTestCase, JsonNode.class));
        processedRun.setName(testCaseFullRef.getName());
        processedRun.setTestCaseOverwrite(objectMapper.convertValue(testCaseOverwrtieWithExpandInsOvr, JsonNode.class));

        // check if we need to do over write
        logger.info("created full test case from run");
        return this.buildRunFromTestCase(fullyExpanedAndOverwroteTestCase, processedRun);
    }

    private Run buildRunFromTestCase(TestCase testCase, Run run) {
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        // set the run information
        run.setName(testCase.getName());
        run.setTestCaseId(testCase.getId());
        run.setTestCase(objectMapper.convertValue(testCase, JsonNode.class));
        run.setLog("test case " + testCase.getName() + " begin execution");
        run.setTestCaseUuid(testCase.getUuid());
        // default run type
        if (run.getType() != null && run.getType().equalsIgnoreCase("DEVELOPMENT")) {
            run.setType("DEVELOPMENT");
        }
        else {
            run.setType("PRODUCTION");
        }
        return run;
    }

    @Override
    public Task buildTaskFromRun(Run run) {
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        Task task = new Task();
        task.setName(run.getName());
        task.setPriority(Integer.valueOf(RuntimeVariables.property.get("meowlomo.ems.task.priority")));
        if (run.getGroup() == null) {
            task.setGroup(RuntimeVariables.property.get("meowlomo.ems.task.group"));
        }
        else {
            task.setGroup(run.getGroup());
        }
        task.setCpuCoreRequired(Integer.valueOf(RuntimeVariables.property.get("meowlomo.ems.task.cpuCore")));
        task.setType(RuntimeVariables.property.get("meowlomo.ems.task.type"));
        task.setInteractive(Boolean.valueOf(RuntimeVariables.property.get("meowlomo.ems.task.interactive")));
        task.setRamRequired(Integer.valueOf(RuntimeVariables.property.get("meowlomo.ems.task.ram")));
        task.setOperatingSystem(RuntimeVariables.property.get("meowlomo.ems.task.operatingSystem"));
        task.setStatus(RuntimeVariables.property.get("meowlomo.ems.task.status"));
        task.setTimeout(Long.valueOf(RuntimeVariables.property.get("meowlomo.ems.task.timeout")));
        task.setMaxRetry(Integer.valueOf(RuntimeVariables.property.get("meowlomo.ems.task.maxRetry")));
        task.setRetryNumber(Integer.valueOf(RuntimeVariables.property.get("meowlomo.ems.task.retryNumber")));
        // add run to the
        JsonNode jsonRunDataObject = objectMapper.convertValue(run, JsonNode.class);
        ((ObjectNode) jsonRunDataObject).put("runId", run.getId());
        task.setData(jsonRunDataObject);
        task.setParameter(null);
        return task;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.meowlomo.atm.external.ems.service.EmsTaskMapper#buildFromTestCaseAndRun(
     * com.meowlomo.atm.core.model.TestCase, com.meowlomo.atm.core.model.Run) the
     * full run has test case overwrite and drivers the test case is the full set of
     * test case information
     */
    @Override
    public List<Task> buildTasksForLinkedTestCase(Run executionInputRun, RunSetTestCaseLink runSetTestCaseLink) {
        Long testCaseId = runSetTestCaseLink.getTestCaseId();
        Long driverPackId = runSetTestCaseLink.getDriverPackId();
        Long testCaseOverwriteId = runSetTestCaseLink.getTestCaseOverwriteId();
        Long runSetTestCaseLinkId = runSetTestCaseLink.getId();
        Boolean synchronize = runSetTestCaseLink.getSynchronize();
        if (testCaseId == null) {
            testCaseId = executionInputRun.getTestCaseId();
        }
        if (driverPackId == null) {
            driverPackId = executionInputRun.getDriverPackId();
        }
        if (testCaseOverwriteId == null) {
            testCaseOverwriteId = executionInputRun.getTestCaseOverwriteId();
        }
        if (runSetTestCaseLinkId == null) {
            runSetTestCaseLinkId = executionInputRun.getRunSetTestCaseLinkId();
        }
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        TestCase testCaseFullRefTemp = testCaseFullReferenceMapper.selectByPrimaryKey(testCaseId);
        TestCase testCaseFullRef = null;
        try {
            testCaseFullRef = objectMapper.treeToValue(objectMapper.convertValue(testCaseFullRefTemp, JsonNode.class),
                    TestCase.class);
        }
        catch (JsonProcessingException | IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List<Task> returnTasks = new ArrayList<Task>();
        Run finalProcessedRun = this.buildRun(testCaseFullRef, executionInputRun, testCaseId, driverPackId,
                testCaseOverwriteId);
        finalProcessedRun.setRunSetTestCaseLinkId(runSetTestCaseLinkId);
        Task task = this.buildTaskFromRun(finalProcessedRun);
        task = this.processingTestCaseInfoToTask(task, testCaseFullRef);
        task.setSynchronizedExecution(synchronize);
        returnTasks.add(task);
        logger.info(returnTasks.size() + " tasks created from run");
        return returnTasks;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.meowlomo.atm.external.ems.service.EmsTaskMapper#buildFromTestCaseAndRun(
     * com.meowlomo.atm.core.model.TestCase, com.meowlomo.atm.core.model.Run) the
     * full run has test case overwrite and drivers the test case is the full set of
     * test case information
     */
    @Override
    public List<Task> buildTasksForTestCase(Run executionInputRun, Long testCaseId, Long driverPackId,
            Long testCaseOverwriteId) {
        if (testCaseId == null) {
            testCaseId = executionInputRun.getTestCaseId();
        }
        if (driverPackId == null) {
            driverPackId = executionInputRun.getDriverPackId();
        }
        if (testCaseOverwriteId == null) {
            testCaseOverwriteId = executionInputRun.getTestCaseOverwriteId();
        }
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        TestCase testCaseFullRefTemp = testCaseFullReferenceMapper.selectByPrimaryKey(testCaseId);
        TestCase testCaseFullRef = null;
        try {
            testCaseFullRef = objectMapper.treeToValue(objectMapper.convertValue(testCaseFullRefTemp, JsonNode.class),
                    TestCase.class);
        }
        catch (JsonProcessingException | IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List<Task> returnTasks = new ArrayList<Task>();
        Run finalProcessedRun = this.buildRun(testCaseFullRef, executionInputRun, testCaseId, driverPackId,
                testCaseOverwriteId);
        Task task = this.buildTaskFromRun(finalProcessedRun);
        task = this.processingTestCaseInfoToTask(task, testCaseFullRef);
        returnTasks.add(task);
        logger.info(returnTasks.size() + " tasks created from run");
        return returnTasks;
    }

    @Override
    public void generateLinksForReponsedTask(Task task) {
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        Run run = mapper.convertValue(task.getData(), Run.class);
        TestCase testCase = testCaseMapper.selectByPrimaryKey(run.getTestCaseId());
        UUID taskUUID = task.getUuid();
        // set test case to task link
        TestCaseTaskLink testCaseTaskLink = new TestCaseTaskLink();
        testCaseTaskLink.setTestCaseId(testCase.getId());
        testCaseTaskLink.setTestCaseUuid(testCase.getUuid());
        testCaseTaskLink.setTaskUuid(taskUUID);
        testCaseTaskLinkMapper.insertSelective(testCaseTaskLink);
        // set run to task link
        RunTaskLink runTaskLink = new RunTaskLink();
        Run runFromDB = runMapper.selectByPrimaryKey(run.getId());
        runTaskLink.setTaskUuid(taskUUID);
        runTaskLink.setRunId(run.getId());
        runTaskLink.setRunUuid(runFromDB.getUuid());
        runTaskLinkMapper.insertSelective(runTaskLink);
    }

    @Override
    public List<Task> insertRecords(List<Task> tasks, Long runSetResultId) throws JsonProcessingException {
        List<Task> returnTasks = new ArrayList<Task>();
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        // get the run data from the tasks
        for (Task task : tasks) {
            // get the data
            JsonNode data = task.getData();
            Run run = objectMapper.treeToValue(data, Run.class);
            // insert the run
            if (runSetResultId != null) {
                run.setRunSetResultId(runSetResultId);
            }
            // not include tenant_id
            long runInsertResult = runMapper.insertSelective(run);
            if (runInsertResult == 1) {
                task.setExternalIdentifier(run.getUuid());
            }
            // insert the inserted run to the task
            task.setData(objectMapper.convertValue(run, JsonNode.class));
            // add the inserted task to the return list
            returnTasks.add(task);
        }
        return returnTasks;
    }
    
    private Task processingTestCaseInfoToTask(Task task, TestCase testCase) {
        if (testCase.getSingleton() != null && testCase.getSingleton()) {
            /*
             * we need to set two things singleton to true the test case uuid as singleton
             * uuid
             */
            task.setSingleton(true);
            task.setSingletonUuid(testCase.getUuid());
        }
        else {
            task.setSingleton(false);
        }
        return task;
    }

    @Override
    public long sendTasksToEMS(List<Task> tasks) {
        /*
         * fetch the job from the job queue we fetch 5 each time
         */

        // check if we need to send the jobs
        if (tasks.isEmpty()) {
            return 0;
        }
        for(Task task : tasks){
            if (task == null) { 
                UUID exuuid = UUID.randomUUID();
                String message = String.format("SSystem is trying to send null task to EMS, please contact system admin.");
                String trace = Thread.currentThread().getStackTrace().toString();
                String code = "ETS";
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
        }

        MeowlomoResponse response = emsApiService.sendTaskToEMS(tasks);
        if (response != null) {
            ObjectMapper mapper = jacksonConverter.getObjectMapper();
            List<Task> responsedTasks = mapper.convertValue(response.getData(), new TypeReference<List<Task>>() {
            });
            if (responsedTasks.size() != tasks.size()) {
                UUID exuuid = UUID.randomUUID();
                String message = String.format("Response from EMS server for task insert has different number count, please contact system admin.");
                String trace = Thread.currentThread().getStackTrace().toString();
                String code = "ETS";
                throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
            }
            // add the test case task link
            for (Task responsedTask : responsedTasks) {
                this.generateLinksForReponsedTask(responsedTask);
            }
            return responsedTasks.size();
        }
        else {
            UUID exuuid = UUID.randomUUID();
            String message = String.format("Null response from EMS server for task call please check log.");
            String trace = Thread.currentThread().getStackTrace().toString();
            String code = "ETS";
            throw new CustomInternalServerErrorException(null, message, trace, code, exuuid);
        }
    }

    @Override
    public MeowlomoResponse terminationTask(UUID taskUuid) {
        if (taskUuid == null) { throw new NullPointerException("Null for Task UUID"); }
        MeowlomoResponse response = emsApiService.terminateTaskByUuid(taskUuid.toString());
        // check the result
        if (response != null) {
            return response;
        }
        else {
            logger.error("Termination for task [" + taskUuid.toString() + "] has null response");
            return null;
        }
    }

}
