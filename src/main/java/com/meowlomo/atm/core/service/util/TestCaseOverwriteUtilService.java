package com.meowlomo.atm.core.service.util;

import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseOverwrite;

public interface TestCaseOverwriteUtilService {

    TestCaseOverwrite copyByPrimaryId(Long id, boolean nameIncrement, String testCaseOverwriteName);

    TestCase executeTestCaseOverwriteOnTestCase(TestCaseOverwrite fullTestCaseOverwrite,
            TestCase fullReferenceTestCase);

}
