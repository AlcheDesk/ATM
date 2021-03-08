package com.meowlomo.atm.core.service.util.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.RunMapper;
import com.meowlomo.atm.core.mapper.RunSetResultJobLinkMapper;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;
import com.meowlomo.atm.core.model.RunSetResultJobLink;
import com.meowlomo.atm.core.model.RunSetResultJobLinkExample;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.service.base.RunSetResultService;
import com.meowlomo.atm.core.service.notification.RunSetResultNotificationService;
import com.meowlomo.atm.core.service.util.RunSetResultUtilService;
import com.meowlomo.atm.external.ems.service.EMSJobService;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultUtilServiceImpl implements RunSetResultUtilService {

    @Autowired
    private EMSJobService emsJobService;

    private final Logger logger = LoggerFactory.getLogger(RunSetResultUtilServiceImpl.class);
    @Autowired
    private RunMapper runMapper;
    @Autowired
    private RunSetResultJobLinkMapper runSetResultJobLinkMapper;
    @Autowired
    private RunSetResultNotificationService runSetResultNotificationService;
    @Autowired
    private RunSetResultService runSetResultService;

    @Override
    public void finishDanglingRunSetResult() {
        // find all unfinished run set result first
        RunSetResultExample unfinishedExample = new RunSetResultExample();
        unfinishedExample.createCriteria().andFinishedEqualTo(false);
        List<RunSetResult> unfinishedRecords = runSetResultService.selectByExample(unfinishedExample);
        // check them one by one
        List<RunSetResult> finishedDanglingRunSetResultList = new ArrayList<RunSetResult>();
        for (RunSetResult unfinishedRecord : unfinishedRecords) {
            // find all runs linked to this record
            Long resultId = unfinishedRecord.getId();
            // check all run linked to this run set result are all finished
            RunExample totalExample = new RunExample();
            totalExample.createCriteria().andRunSetResultIdEqualTo(resultId);
            long toltalNumber = runMapper.countByExample(totalExample);
            RunExample finishedExmaple = new RunExample();
            finishedExmaple.createCriteria().andRunSetResultIdEqualTo(resultId).andFinishedEqualTo(true);
            long finishedNumber = runMapper.countByExample(finishedExmaple);
            if (toltalNumber == finishedNumber) {
                RunSetResult updateRunSetResult = new RunSetResult();
                if (unfinishedRecord.getStatus().equalsIgnoreCase("WIP")) {
                    updateRunSetResult.setStatus("PASS");
                }
                updateRunSetResult.setId(resultId);
                updateRunSetResult.setFinished(true);
                updateRunSetResult
                        .setLog("Set to finished due to all linked run has been finished [Cleaner triggered].");
                int updateResult = runSetResultService.updateByPrimaryKeySelective(updateRunSetResult);
                if (updateResult == 1) {

                    logger.info("Runset result [" + resultId + "] has been set to finished.");
                    finishedDanglingRunSetResultList.add(runSetResultService.selectByPrimaryKey(resultId));
                }
                logger.debug("***********Set run set result " + resultId
                        + " finished due to all linked run has been finished.");
            }
        }

        try {
            TransactionAspectSupport.currentTransactionStatus().isCompleted();
        }
        catch (Exception e) {

        }
        // send out emails
        if (!finishedDanglingRunSetResultList.isEmpty()) {
            logger.info("Starting sending emails for finished Dangling run set result.");
            for (RunSetResult runSetResultToSentEmail : finishedDanglingRunSetResultList) {
                runSetResultNotificationService.sendRunSetResultEmail(runSetResultToSentEmail.getId());
            }

        }

    }

    @Override
    public Object terminateRunSetResult(Long runSetResultId) throws ObjectNotFoundException {
        if (runSetResultId == null) { throw new NullPointerException("Null value for run set id in termination call"); }
        // get the uuid of the run set
        RunSetResultJobLinkExample runSetResultJobLinkExample = new RunSetResultJobLinkExample();
        runSetResultJobLinkExample.createCriteria().andRunSetResultIdEqualTo(runSetResultId);
        List<RunSetResultJobLink> runSetResultJobLinks = runSetResultJobLinkMapper
                .selectByExample(runSetResultJobLinkExample);
        if (runSetResultJobLinks.isEmpty()) {
            // check the run set result is still in new status
            RunSetResult runSetResult = runSetResultService.selectByPrimaryKey(runSetResultId);
            Set<String> finalStatusSet = new HashSet<String>(RuntimeVariables.FINAL_STATUSES);
            if (finalStatusSet.contains(runSetResult.getStatus())) {
                throw new ObjectNotFoundException("Could not find the linked job for run set result [" + runSetResultId
                        + "], and the runset result is in status: " + runSetResult.getStatus());
            }
            else {
                this.updateRunSetResultForTermination(runSetResultId);
                logger.debug("Run set result id:{} and its linked runs has been terminated internaly", runSetResultId);
                return runSetResult;
            }
        }
        else {
            this.updateRunSetResultForTermination(runSetResultId);
            UUID jobUuid = runSetResultJobLinks.get(0).getJobUuid();
            MeowlomoResponse emsResponse = emsJobService.terminationJob(jobUuid);
            if (emsResponse == null) {
                return null;
            }
            else {
                return emsResponse.getData();
            }
        }
    }

    private void updateRunSetResultForTermination(Long runSetResultId) {
        // lock and update the run set result status first
        RunSetResult updateRunSetResult = new RunSetResult();
        updateRunSetResult.setId(runSetResultId);
        updateRunSetResult.setLog("Status has been set to TERMINATED due to TERMINATION signal.");
        updateRunSetResult.setStatus("TERMINATED");
        int updateResult = runSetResultService.updateByPrimaryKeySelective(updateRunSetResult);
        if (updateResult == 1) {
            // update the runs which are linked to this run set result and not in final
            // status
            RunExample runCountExample = new RunExample();
            runCountExample.createCriteria().andRunSetResultIdEqualTo(runSetResultId);
            long runCountResult = runMapper.countByExample(runCountExample);
            if (runCountResult > 0) { // we need to do the update
                RunExample runExample = new RunExample();
                runExample.createCriteria().andRunSetResultIdEqualTo(runSetResultId)
                        .andStatusNotIn(RuntimeVariables.FINAL_STATUSES);
                Run updateRun = new Run();
                updateRun.setLog("Status has been set to TERMINATED due to TERMINATION signal.");
                updateRun.setStatus("TERMINATED");
                int runUpdateResult = runMapper.updateByExampleSelective(updateRun, runExample);
                if (runUpdateResult <= runCountResult) {
                    // we dont need to do any thing
                }
                else {
                    throw new RuntimeException(
                            "Could not update the run status to TERMINATED which linked to run set result["
                                    + runSetResultId
                                    + "]. The update result number is larder than the linked count. Please check");
                }
            }
        }
        else {
            throw new RuntimeException("Could not update the run set result status to TERMINATED.");
        }
    }
}
