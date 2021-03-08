package com.meowlomo.atm.core.service.background.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.service.background.RunCleaner;
import com.meowlomo.atm.core.service.base.RunService;
import com.meowlomo.atm.core.service.util.RunUtilService;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class RunCleanerImpl implements RunCleaner {

    @Value("${meowlomo.atm.execute.timeout-in-minutes}")
    private int EXECUTION_TIME_OUT_MINUTES;

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(RunCleanerImpl.class);
    @Autowired
    private RunService runService;

    @Autowired
    private RunUtilService runUtilService;

    @Override
    public void cleanFinishedNoEndAtRun() {
        // search the the time out run first
        RunExample searchExample = new RunExample();
        searchExample.createCriteria().andFinishedEqualTo(true).andEndAtIsNull().andStatusNotEqualTo("ERROR");
        List<Run> targets = runService.selectByExample(searchExample);
        // we got all the timeouted runs
        if (!targets.isEmpty()) {
            // let us create the condition to update them
            List<Long> targetIds = new ArrayList<Long>();
            for (Run target : targets) {
                targetIds.add(target.getId());
            }
            RunExample updateExample = new RunExample();
            // when doing the update they must still in the condition
            updateExample.createCriteria().andFinishedEqualTo(true).andEndAtIsNull().andStatusNotEqualTo("ERROR")
                    .andIdIn(targetIds);
            Run updateRun = new Run();
            updateRun.setStatus("ERROR");
            updateRun.setLog(
                    "Due to ERROR condition, Finished with out end time. Status has ben set to ERROR with end time. Please contact system admin.");
            runService.updateByExampleSelective(updateRun, updateExample);
        }
    }

    @Override
    public void cleanFinishedStartAtEndAtLogicErrorRuns() {
        logger.info("start clean run end at is eariler than start at");
        int cleanEndAtEarlierStartAtRunResult = runUtilService.cleanEndAtEarlierStartAtRun();
        logger.info("end clean run end at is eariler than start at, result {}", cleanEndAtEarlierStartAtRunResult);
        
        logger.info("start clean run is finished but without end at proper status");
        int cleanFinishedNotInEndStatusResult = runUtilService.cleanFinishedNotInEndStatus();
        logger.info("end clean run is finished but without end at proper status, result {}", cleanFinishedNotInEndStatusResult);
        
        logger.info("start clean run not start at time is null and finished is true");
        int cleanStartAtIsNullFinishedIsTrueRunResult = runUtilService.cleanStartAtIsNullFinishedIsTrueRun();
        logger.info("end clean run not start at time is null and finished is true, result {}", cleanStartAtIsNullFinishedIsTrueRunResult);   
        
        logger.info("start clean run start at is null but end at is not null");
        int cleanEndAtNotNullStartAtNullRunResult = runUtilService.cleanEndAtNotNullStartAtNullRun();
        logger.info("end clean run start at is null but end at is not null, result {}", cleanEndAtNotNullStartAtNullRunResult);  
    }

    @Override
    public void cleanNewTimeoutedRun() {
        // search the the time out run first
        RunExample runExample = new RunExample();
        runExample.createCriteria().andStatusEqualTo("NEW").andStartAtIsNull().andFinishedEqualTo(false)
                .andCreatedAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000));
        List<Run> targets = runService.selectByExample(runExample);
        // we got all the timeouted runs
        if (!targets.isEmpty()) {
            // let us create the condition to update them
            List<Long> targetIds = new ArrayList<Long>();
            for (Run target : targets) {
                targetIds.add(target.getId());
            }
            RunExample updateExample = new RunExample();
            // when doing the update they must still in the condition
            updateExample.createCriteria().andStatusEqualTo("NEW").andStartAtIsNull().andFinishedEqualTo(false)
                    .andCreatedAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000))
                    .andIdIn(targetIds);
            Run updateRun = new Run();
            updateRun.setStatus("TIMEOUT");
            updateRun.setFinished(true);
            runService.updateByExampleSelective(updateRun, updateExample);
        }
    }

    @Override
    public void cleanNotNewNoStartAtRun() {
        // search the the time out run first
        RunExample searchExample = new RunExample();
        searchExample.createCriteria().andStatusNotEqualTo("NEW").andStartAtIsNull().andStatusNotEqualTo("ERROR");
        List<Run> targets = runService.selectByExample(searchExample);
        // we got all the timeouted runs
        if (!targets.isEmpty()) {
            // let us create the condition to update them
            List<Long> targetIds = new ArrayList<Long>();
            for (Run target : targets) {
                targetIds.add(target.getId());
            }
            RunExample updateExample = new RunExample();
            // when doing the update they must still in the condition
            updateExample.createCriteria().andStatusNotEqualTo("NEW").andStartAtIsNull().andStatusNotEqualTo("ERROR")
                    .andIdIn(targetIds);
            Run updateRun = new Run();
            updateRun.setStatus("ERROR");
            updateRun.setFinished(true);
            updateRun.setLog(
                    "Due to ERROR condition, In not NEW status but no start time record. Status has ben set to ERROR with finished, satart and end time. Please contact system admin.");
            runService.updateByExampleSelective(updateRun, updateExample);
        }
    }

    @Override
    public void cleanStartedTimeoutedRun() {
        // search the the time out run first
        RunExample runExample = new RunExample();
        runExample.createCriteria().andFinishedEqualTo(false)
                .andStartAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000));
        List<Run> targets = runService.selectByExample(runExample);
        // we got all the timeouted runs
        if (!targets.isEmpty()) {
            // let us create the condition to update them
            List<Long> targetIds = new ArrayList<Long>();
            for (Run target : targets) {
                targetIds.add(target.getId());
            }
            RunExample updateExample = new RunExample();
            // when doing the update they must still in the condition
            updateExample.createCriteria().andFinishedEqualTo(false)
                    .andStartAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000))
                    .andIdIn(targetIds);
            Run updateRun = new Run();
            updateRun.setStatus("TIMEOUT");
            updateRun.setFinished(true);
            runService.updateByExampleSelective(updateRun, updateExample);
        }
    }

}
