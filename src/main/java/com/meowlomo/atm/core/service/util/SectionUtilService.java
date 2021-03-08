package com.meowlomo.atm.core.service.util;

import java.util.Map;

import com.meowlomo.atm.core.model.Section;

public interface SectionUtilService {

    Section copyByPrimaryId(Long id, boolean nameIncrement);

    Section copyByPrimaryId(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id, boolean nameIncrement);

    Section copyByPrimaryIdForApplication(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id,
            boolean nameIncrement, Long applicationId);

}
