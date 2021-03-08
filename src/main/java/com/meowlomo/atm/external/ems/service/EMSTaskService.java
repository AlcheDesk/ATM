package com.meowlomo.atm.external.ems.service;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.external.ems.model.Task;

public interface EMSTaskService {

    List<Task> buildTasksForTestCase(Run executionInputRun, Long testCaseId, Long driverPackId,
            Long testCaseOverwriteId);

    List<Task> buildTasksForLinkedTestCase(Run executionInputRun, RunSetTestCaseLink runSetTestCaseLink);

    List<Task> insertRecords(List<Task> tasks, Long runSetResultId) throws JsonProcessingException;

    MeowlomoResponse terminationTask(UUID taskUuid);

    Task buildTaskFromRun(Run run);

    void generateLinksForReponsedTask(Task task);

//    boolean sendTaskToEMS(Task task);

    long sendTasksToEMS(List<Task> tasks);
}
