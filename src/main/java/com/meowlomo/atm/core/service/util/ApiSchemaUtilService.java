package com.meowlomo.atm.core.service.util;

import com.meowlomo.atm.core.model.ApiSchema;

public interface ApiSchemaUtilService {

    ApiSchema copyByPrimaryId(Long id, boolean nameIncrement);

}
