package com.meowlomo.atm.core.service.background;

public interface RunSetResultCleaner {    
    void cleanDanglingRunSetResult();
    void cleanNewTimeoutRunSetResult();
    void cleanWipTimeoutRunSetResult();
//    void cleanEndButNotFinishedRunSetResult();
}
