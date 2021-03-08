package com.meowlomo.atm.core.service.util;

import java.util.List;

import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverPack;

public interface DriverPackUtilService {

    DriverPack copyByPrimaryId(Long id, boolean nameIncrement);

    List<DriverPack> getAllDriverPackByRunSetId(Long runSetId);

    List<DriverPack> getAllDriverPackByTestCaseId(Long testCaseId);

    List<DriverPack> getDriverPackByRunSetId(Long runSetId);

    List<DriverPack> getDriverPackByTestCaseId(Long testCaseId);
    
    List<Driver> removeDuplicatedWebBrowerDriverForExecution(DriverPack driverPack);

}
