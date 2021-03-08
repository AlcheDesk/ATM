package com.meowlomo.atm.core.service.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.meowlomo.atm.core.model.TestCase;

public interface TestCaseUtilService {

    TestCase copyByPrimaryId(Long id, boolean nameIncrement);

    TestCase copyByPrimaryIdForProject(Map<String, Map<Long, Long>> refElementIdPackageMap,
            Map<Long, Long> refTestCaseIdMap, Long id, boolean nameIncrement, Long projectId);

    long countCurrentTestReferenceLayer(Long currentTestCaseId);

    long countTestCaseReferenceLayer(Long currentTestCaseId);

    Map<Long, Set<Long>> getTestCaseReferenceMap();

    boolean isReferenceLookOccur(Long testCaseId, Long referencetestCaseId);
    
    List<TestCase> getTestCaseReferencedBy(Long testCaseId);

    long countTestCaseReferencedBy(Long testCaseId);
}
