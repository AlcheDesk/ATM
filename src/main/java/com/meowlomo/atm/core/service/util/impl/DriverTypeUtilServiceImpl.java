package com.meowlomo.atm.core.service.util.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.TestCaseDriverTypeMapMapper;
import com.meowlomo.atm.core.mapper.util.TestCaseDriverTypeMapUtilMapper;
import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.TestCaseDriverTypeMap;
import com.meowlomo.atm.core.model.TestCaseDriverTypeMapExample;
import com.meowlomo.atm.core.service.util.DriverTypeUtilService;
import com.meowlomo.atm.core.service.util.TestCaseUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverTypeUtilServiceImpl implements DriverTypeUtilService {

    @Autowired
    private TestCaseDriverTypeMapMapper testCaseDriverTypeMapMapper;
    @Autowired
    private TestCaseDriverTypeMapUtilMapper testCaseDriverTypeMapUtilMapper;
    @Autowired
    private TestCaseUtilService testCaseUtilService;

    @Override
    public List<DriverType> getDriverTypeByRunSetId(Long runSetId) {
        Set<String> driverTypeSet = this.getDriverTypeSetByRunSetId(runSetId);
        List<DriverType> driverTypes = new ArrayList<DriverType>();
        for (String driverType : driverTypeSet) {
            if (RuntimeVariables.getDriverTypeObjectMap().containsKey(driverType)) {
                driverTypes.add(RuntimeVariables.getDriverTypeObjectMap().get(driverType));
            }
        }
        return driverTypes;
    }

    @Override
    public List<DriverType> getDriverTypeByTestCaseId(Long testCaseId) {
        Set<String> driverTypeSet = this.getDriverTypeSetByTestCaseId(testCaseId);
        List<DriverType> driverTypes = new ArrayList<DriverType>();
        for (String driverType : driverTypeSet) {
            if (RuntimeVariables.getDriverTypeObjectMap().containsKey(driverType)) {
                driverTypes.add(RuntimeVariables.getDriverTypeObjectMap().get(driverType));
            }
        }
        return driverTypes;
    }

    @Override
    public Set<String> getDriverTypeSetByRunSetId(Long runSetId) {
        List<TestCaseDriverTypeMap> testCaseDriverTypeMaps = testCaseDriverTypeMapUtilMapper
                .getTestCaseDriverTypeMapByRunSetId(runSetId);
        return this.processTestCaseDriverTypeMapToGetDriverType(testCaseDriverTypeMaps);
    }

    @Override
    public Set<String> getDriverTypeSetByTestCaseId(Long testCaseId) {
        // get the reference map first
        Map<Long, Set<Long>> testCaseReferenceMap = testCaseUtilService.getTestCaseReferenceMap();
        Set<Long> testCaseIdSet = new HashSet<Long>();
        testCaseIdSet.add(testCaseId);
        testCaseIdSet = this.processTestCaseReferenceMap(testCaseIdSet, testCaseReferenceMap, testCaseId);
        TestCaseDriverTypeMapExample testCaseDriverTypeMapExample = new TestCaseDriverTypeMapExample();
        testCaseDriverTypeMapExample.createCriteria().andTestCaseIdIn(Lists.newArrayList(testCaseIdSet));
        List<TestCaseDriverTypeMap> testCaseDriverTypeMaps = testCaseDriverTypeMapMapper
                .selectByExample(testCaseDriverTypeMapExample);
        return this.processTestCaseDriverTypeMapToGetDriverType(testCaseDriverTypeMaps);
    }

    private Set<String> processTestCaseDriverTypeMapToGetDriverType(
            List<TestCaseDriverTypeMap> testCaseDriverTypeMaps) {
        Set<String> returnSet = new HashSet<String>();
        for (TestCaseDriverTypeMap testCaseDriverTypeMap : testCaseDriverTypeMaps) {
            returnSet.addAll(testCaseDriverTypeMap.getDriverTypes());
        }
        return returnSet;
    }

    private Set<Long> processTestCaseReferenceMap(Set<Long> testCaseIdSet, Map<Long, Set<Long>> testCaseReferenceMap,
            Long currentTestCaseId) {
        // check the test case id first
        if (currentTestCaseId == null) {
            return testCaseIdSet;
        }
        else {
            // get the lower level
            if (testCaseReferenceMap.containsKey(currentTestCaseId)) {
                Set<Long> lowerTestCaseIds = testCaseReferenceMap.get(currentTestCaseId);
                testCaseIdSet.addAll(lowerTestCaseIds);
                // loop through the lower level
                for (Long lowerTestCaseId : lowerTestCaseIds) {
                    testCaseIdSet
                            .addAll(processTestCaseReferenceMap(testCaseIdSet, testCaseReferenceMap, lowerTestCaseId));
                }
            }
            return testCaseIdSet;
        }
    }

}
