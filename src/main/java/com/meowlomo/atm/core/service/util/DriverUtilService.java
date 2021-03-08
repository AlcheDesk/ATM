package com.meowlomo.atm.core.service.util;

import com.meowlomo.atm.core.model.Driver;

public interface DriverUtilService {

    Driver copyByPrimaryId(Long id, boolean nameIncrement);

}
