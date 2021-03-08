package com.meowlomo.atm.core.service.filter;

import com.meowlomo.atm.core.model.RunSet;

public interface RunSetContentFilterService {
    RunSet expandReferenecingRunSetTestCaseLinkForRunSet(RunSet runSetRef);
}
