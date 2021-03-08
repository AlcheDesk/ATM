package com.meowlomo.atm.core.service.filter;

import java.util.List;

import com.meowlomo.atm.core.model.TestCase;

public interface TestCaseShareFolderContentFilterService {

    List<TestCase> filteroutNonReferencableTestCases(Long currentTestCaseId, List<TestCase> originalTestCases);
}
