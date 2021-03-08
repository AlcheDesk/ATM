package com.meowlomo.atm.core.service.util;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.external.ems.model.Task;

import javassist.tools.rmi.ObjectNotFoundException;

public interface RunUtilService {
    Run buildRunWithDriverPackAndTestCaseOverwriteForExecution(Run run, Long testCaseId, Long driverPackId,
            Long testCaseOverwriteId);

//    Run buildRunWithDriverPackAndTestCaseOverwriteForExecution(Run run, Long testCaseId, Long driverPackId,
//            Long testCaseOverwriteId, Long runSetTestCaseLinkId);

    int cleanEndAtEarlierStartAtRun();

    int cleanEndAtNotNullStartAtNullRun();
    
    int cleanFinishedNotInEndStatus();
    int cleanStartAtIsNullFinishedIsTrueRun();
    Task processExecutableTaskByRun(Run run, Task task);
    Object terminateRun(Long runId) throws ObjectNotFoundException;
}
