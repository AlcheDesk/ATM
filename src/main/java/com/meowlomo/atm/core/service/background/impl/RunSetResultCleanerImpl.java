package com.meowlomo.atm.core.service.background.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;
import com.meowlomo.atm.core.service.background.RunSetResultCleaner;
import com.meowlomo.atm.core.service.base.RunSetResultService;
import com.meowlomo.atm.core.service.util.RunSetResultUtilService;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class RunSetResultCleanerImpl implements RunSetResultCleaner{
    
    @Value("${meowlomo.atm.execute.timeout-in-minutes}")
    private int EXECUTION_TIME_OUT_MINUTES;
    
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(RunSetResultCleanerImpl.class);
    @Autowired
    private RunSetResultService runSetResultService;
    
    @Autowired
    private RunSetResultUtilService runSetResultUtilService;

    @Override
    public void cleanDanglingRunSetResult(){
        runSetResultUtilService.finishDanglingRunSetResult();
    }

    @Override
    public void cleanNewTimeoutRunSetResult() {
        //search the the time out run first
        List<String> targetStatusList = Arrays.asList("NEW");
        RunSetResultExample runSetResultExample = new RunSetResultExample();
        runSetResultExample.createCriteria()
        .andStatusIn(targetStatusList)
        .andCreatedAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000));
        List<RunSetResult> targets = runSetResultService.selectByExample(runSetResultExample);
        //we got all the timeouted runs
        if (!targets.isEmpty()) {
            //let us create the condition to update them
            List<Long> targetIds = new ArrayList<Long>();
            for (RunSetResult target : targets) {
                targetIds.add(target.getId());
            }
            RunSetResultExample updateExample = new RunSetResultExample();
            //when doing the update they must still in the condition
            updateExample.createCriteria().andStatusIn(targetStatusList).andCreatedAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000)).andIdIn(targetIds);
            RunSetResult updateRunSetResult = new RunSetResult();
            updateRunSetResult.setStatus("TIMEOUT");
            updateRunSetResult.setFinished(true);
            runSetResultService.updateByExampleSelective(updateRunSetResult, updateExample);
        }        
    }

    @Override
    public void cleanWipTimeoutRunSetResult() {
        //search the the time out run first
        List<String> targetStatusList = Arrays.asList("WIP");
        RunSetResultExample runSetResultExample = new RunSetResultExample();
        runSetResultExample.createCriteria().andStatusIn(targetStatusList).andStartAtIsNull().andStartAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000));
        List<RunSetResult> targets = runSetResultService.selectByExample(runSetResultExample);
        //we got all the timeouted runs
        if (!targets.isEmpty()) {
            //let us create the condition to update them
            List<Long> targetIds = new ArrayList<Long>();
            for (RunSetResult target : targets) {
                targetIds.add(target.getId());
            }
            RunSetResultExample updateExample = new RunSetResultExample();
            //when doing the update they must still in the condition
            updateExample.createCriteria().andStatusIn(targetStatusList).andStartAtIsNull().andStartAtLessThan(new Date(System.currentTimeMillis() - EXECUTION_TIME_OUT_MINUTES * 60 * 1000)).andIdIn(targetIds);
            RunSetResult updateRunSetResult = new RunSetResult();
            updateRunSetResult.setStatus("TIMEOUT");
            updateRunSetResult.setFinished(true);
            runSetResultService.updateByExampleSelective(updateRunSetResult, updateExample);
        } 
    }
}
