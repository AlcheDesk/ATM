package com.meowlomo.atm.core.service.util;

import java.util.List;
import java.util.Set;

import com.meowlomo.atm.core.model.DriverType;

public interface DriverTypeUtilService {

    List<DriverType> getDriverTypeByRunSetId(Long runSetId);

    List<DriverType> getDriverTypeByTestCaseId(Long testCaseId);
    
    Set<String> getDriverTypeSetByRunSetId(Long runSetId);

    Set<String> getDriverTypeSetByTestCaseId(Long testCaseId);
    
}
