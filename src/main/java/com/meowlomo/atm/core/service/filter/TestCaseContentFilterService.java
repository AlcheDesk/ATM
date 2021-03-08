package com.meowlomo.atm.core.service.filter;

import com.meowlomo.atm.core.model.TestCase;

public interface TestCaseContentFilterService {

    public TestCase expandTestCaseWithReferencedInstructions(TestCase testCase);

}
