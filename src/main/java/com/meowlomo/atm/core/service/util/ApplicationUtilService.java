package com.meowlomo.atm.core.service.util;

import java.util.Map;

import com.meowlomo.atm.core.model.Application;

public interface ApplicationUtilService {

    Application copyByPrimaryId(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id, boolean nameIncrement);

    Application copyByPrimaryIdForPorject(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id, boolean nameIncrement,
            Long projectId);

}
