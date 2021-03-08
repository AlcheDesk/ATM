package com.meowlomo.atm.core.service.background;

public interface RunCleaner {

    void cleanFinishedNoEndAtRun();

    void cleanFinishedStartAtEndAtLogicErrorRuns();

//    void cleanWipOrNewWithFinishedRun();

    void cleanNewTimeoutedRun();

    void cleanNotNewNoStartAtRun();
    
    void cleanStartedTimeoutedRun();
    
}
