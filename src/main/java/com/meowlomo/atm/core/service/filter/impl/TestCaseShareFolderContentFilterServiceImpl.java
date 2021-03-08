package com.meowlomo.atm.core.service.filter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.service.filter.TestCaseShareFolderContentFilterService;
import com.meowlomo.atm.core.service.util.TestCaseUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseShareFolderContentFilterServiceImpl implements TestCaseShareFolderContentFilterService {

    @Autowired
    private TestCaseUtilService testCaseUtilService;
    
    @Override
    public List<TestCase> filteroutNonReferencableTestCases(Long currentTestCaseId, List<TestCase> originalTestCases) {
        if (originalTestCases == null || originalTestCases.isEmpty()) {
            return originalTestCases;
        }
        //loop the test cases
        for (int i = 0; i < originalTestCases.size(); i++) {
            TestCase testCase = originalTestCases.get(i);
            //get the id
            Long targetTestCaseId = testCase.getId();
            if (targetTestCaseId.equals(currentTestCaseId)) {
                originalTestCases.remove(i);
                // reset the index
                i--;
            }
            else if (testCaseUtilService.isReferenceLookOccur(currentTestCaseId, targetTestCaseId)) {
                originalTestCases.remove(i);
                // reset the index
                i--;
            }
        }        
        return originalTestCases;
    }

}
