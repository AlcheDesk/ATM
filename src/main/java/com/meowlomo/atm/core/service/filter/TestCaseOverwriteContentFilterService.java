package com.meowlomo.atm.core.service.filter;

import com.meowlomo.atm.core.model.TestCaseOverwrite;

public interface TestCaseOverwriteContentFilterService {

    TestCaseOverwrite expandTestCaseOverwriteWithRefereneceTestCaseOverwrite(TestCaseOverwrite testCaseOverwriteRef);
    
}
