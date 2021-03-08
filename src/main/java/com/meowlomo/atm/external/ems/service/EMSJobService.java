package com.meowlomo.atm.external.ems.service;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.external.ems.model.Job;

public interface EMSJobService {

    Job buildJobForRunSetResultForErrorAndFailRuns(Long runSetResultId) throws JsonProcessingException;

    Job buildJobForRunSet(RunSet runSetFull, Run run);

    List<Job> buildJobsForRunSets(List<RunSet> runSetsFull, Run run);

    MeowlomoResponse terminationJob(UUID jobUuid);

    List<Job> insertRecords(List<Job> jobs, Run run) throws JsonProcessingException;

//    boolean sendJobToEMS(Job job);

    long sendJobsToEMS(List<Job> jobs);
}
