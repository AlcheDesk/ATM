package com.meowlomo.atm.core.service.util;

import com.meowlomo.atm.core.model.Element;

public interface ElementUtilService {

    Element copyByPrimaryId(Long id, boolean nameIncrement);

}
