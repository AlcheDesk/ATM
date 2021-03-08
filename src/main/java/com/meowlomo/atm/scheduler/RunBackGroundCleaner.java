package com.meowlomo.atm.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.service.background.RunCleaner;
import com.meowlomo.atm.core.service.background.RunSetResultCleaner;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class RunBackGroundCleaner {

    static final Logger logger = LoggerFactory.getLogger(RunBackGroundCleaner.class);

    @Autowired
    private RunCleaner runCleaner;
    @Autowired
    private RunSetResultCleaner runSetResultCleaner;

    @Scheduled(initialDelay = 30000, fixedDelay = 60000)
    public void cleanTimeoutedRun() {
        try {
            logger.debug("==> //////////start cleanNewTimeoutedRun");
            runCleaner.cleanNewTimeoutedRun();
            logger.debug("<== //////////end cleanNewTimeoutedRun");
        } catch (Exception e) {
            logger.error("scheculer cleanTimeoutedRun error", e);
        }
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 60000)
    public void cleanStartedTimeoutedRun() {
        try {
            logger.debug("==> //////////start cleanStartedTimeoutedRun");
            runCleaner.cleanStartedTimeoutedRun();
            ;
            logger.debug("<== //////////end cleanStartedTimeoutedRun");
        } catch (Exception e) {
            logger.error("scheculer cleanStartedTimeoutedRun error", e);
        }
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 60000 * 3)
    public void cleanNotNEWNoStartAtRun() {
        try {
            logger.debug("==> //////////start cleanNotNEWNoStartAtRun");
            runCleaner.cleanNotNewNoStartAtRun();
            logger.debug("<== //////////end cleanNotNEWNoStartAtRun");
        } catch (Exception e) {
            logger.error("scheculer cleanNotNEWNoStartAtRun error", e);
        }
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 60000 * 5)
    public void cleanFinishedStartAtEndAtLogicErrorRuns() {
        try {
            logger.debug("==> //////////start run finished start at and end at logic error");
            runCleaner.cleanFinishedStartAtEndAtLogicErrorRuns();
            logger.debug("<== //////////end run finished start at and end at logic error");
        } catch (Exception e) {
            logger.error("scheculer cleanFinishedStartAtEndAtLogicErrorRuns error", e);
        }
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 60000 * 7)
    public void cleanFinishedNoEndAtRun() {
        try {
            logger.debug("==> //////////start cleanFinishedNoEndAtRun");
            runCleaner.cleanFinishedNoEndAtRun();
            logger.debug("<== //////////end cleanFinishedNoEndAtRun");
        } catch (Exception e) {
            logger.error("scheculer cleanFinishedNoEndAtRun error", e);
        }
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 60000 * 2)
    public void cleanDanglingRunSetResult() {
        try {
            logger.debug("==> //////////start cleanDanglingRunSetResult");
            runSetResultCleaner.cleanDanglingRunSetResult();
            logger.debug("<== //////////end cleanDanglingRunSetResult");
        } catch (Exception e) {
            logger.error("scheculer sendOutEmils error", e);
        }
    }

    @Scheduled(initialDelay = 30000, fixedDelay = (long) (60000 * 2.5))
    public void cleanNewTimeoutRunSetResult() {
        try {
            logger.debug("==> //////////start cleanNewTimeoutRunSetResult");
            runSetResultCleaner.cleanNewTimeoutRunSetResult();
            logger.debug("<== //////////end cleanNewTimeoutRunSetResult");
        } catch (Exception e) {
            logger.error("scheculer sendOutEmils error", e);
        }
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 60000 * 3)
    public void cleanWipTimeoutRunSetResult() {
        try {
            logger.debug("==> //////////start cleanWIPTimeoutRunSetResult");
            runSetResultCleaner.cleanWipTimeoutRunSetResult();
            ;
            logger.debug("<== //////////end cleanWIPTimeoutRunSetResult");
        } catch (Exception e) {
            logger.error("scheculer sendOutEmils error", e);
        }
    }

//    @Scheduled(initialDelay=30000, fixedDelay=(long) (60000*3.5))
//    public void cleanEndButNotFInishedRunSetResult() {
//        logger.debug("==> //////////start cleanEndButNotFInishedRunSetResult");
//        runSetResultCleaner.cleanEndButNotFinishedRunSetResult();;
//        logger.debug("<== //////////end cleanEndButNotFInishedRunSetResult");
//    }
}
