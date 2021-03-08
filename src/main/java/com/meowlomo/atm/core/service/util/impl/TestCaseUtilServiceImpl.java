package com.meowlomo.atm.core.service.util.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.InstructionMapper;
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.mapper.TestCaseOptionEntryMapper;
import com.meowlomo.atm.core.mapper.TestCaseReferenceMapper;
import com.meowlomo.atm.core.mapper.util.TestCaseUtilMapper;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseOptionEntry;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.InstructionUtilService;
import com.meowlomo.atm.core.service.util.TestCaseUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseUtilServiceImpl implements TestCaseUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private InstructionMapper instructionMapper;
    @Autowired
    private InstructionUtilService instructionUtilService;
    @Autowired
    private TestCaseMapper testCaseMapper;
    @Autowired
    private TestCaseOptionEntryMapper testCaseOptionEntryMapper;
    @Autowired
    private TestCaseReferenceMapper testCaseReferenceMapper;
    @Autowired
    private TestCaseUtilMapper testCaseUtilMapper;

    @Override
    public TestCase copyByPrimaryId(Long id, boolean nameIncrement) {
        return this.copyByPrimaryIdForProject(null, null, id, nameIncrement, null);
    }

    @Override
    public TestCase copyByPrimaryIdForProject(Map<String, Map<Long, Long>> refElementIdPackageMap,
            Map<Long, Long> refTestCaseIdMap, Long id, boolean nameIncrement, Long projectId) {
        TestCase record = testCaseReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewTestCaseForProjectNameForCopy(record.getProjectId(), finalName);
                record.setName(finalName);
            }
            if (projectId != null) {
                record.setProjectId(projectId);
            }
            record.setCopyFromId(id);
            long insertResult = testCaseMapper.insert(record);
            // add reftestCaseIdMap
            if (refTestCaseIdMap != null) {
                refTestCaseIdMap.put(id, record.getId());
            }
            if (insertResult == 1) {
                boolean finalResult = true;
                List<Instruction> instructions = record.getInstructions();
                for (Instruction nextLevelRecord : instructions) {
                    nextLevelRecord.setTestCaseId(record.getId());
                    Instruction insertInstructionResult = null;
                    try {
                        insertInstructionResult = instructionUtilService.copy(refElementIdPackageMap, refTestCaseIdMap,
                                record.getId(), nextLevelRecord);
                    }
                    catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (insertInstructionResult.equals(null)) {
                        finalResult = false;
                        break;
                    }
                }
                if (!finalResult) { return null; }
                // testCaseOptions
                List<TestCaseOptionEntry> testCaseOptions = record.getTestCaseOptions();
                for (TestCaseOptionEntry nextLevelRecord : testCaseOptions) {
                    nextLevelRecord.setTestCaseId(record.getId());
                    if (testCaseOptionEntryMapper.insert(nextLevelRecord) == 0) {
                        finalResult = false;
                        break;
                    }
                }
                if (finalResult) {
                    return record;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
    }

    @Override
    public long countCurrentTestReferenceLayer(Long currentTestCaseId) {
        // get the list of map first
        Map<Long, Set<Long>> processedReferenceData = this.getTestCaseReferenceMap();
        return this.countTestCaseReferenceLayer(processedReferenceData, 0, currentTestCaseId);
    }

    @Override
    public long countTestCaseReferencedBy(Long testCaseId) {
        return testCaseUtilMapper.countReferencedTestCasesFromTestCaseId(testCaseId);
    }

    @Override
    public long countTestCaseReferenceLayer(Long currentTestCaseId) {
        InstructionExample totalInstructionExample = new InstructionExample();
        totalInstructionExample.createCriteria().andRefTestCaseIdIsNotNull().andDeletedEqualTo(false);
        List<Instruction> totalInstructionsForRefTestCase = instructionMapper.selectByExample(totalInstructionExample);
        Map<Long, Set<Long>> refTestCaseMap = new HashMap<Long, Set<Long>>();
        for (Instruction totalInstructionForRefTestCase : totalInstructionsForRefTestCase) {
            InstructionExample instructionExample = new InstructionExample();
            instructionExample.createCriteria().andTestCaseIdEqualTo(totalInstructionForRefTestCase.getTestCaseId())
                    .andRefTestCaseIdIsNotNull().andDeletedEqualTo(false);
            List<Instruction> instructionsForRefTestCase = instructionMapper.selectByExample(instructionExample);
            Set<Long> refTestCaseIds = new HashSet<Long>();

            for (Instruction instructionForRefTestCase : instructionsForRefTestCase) {
                refTestCaseIds.add(instructionForRefTestCase.getRefTestCaseId());
            }
            refTestCaseMap.put(totalInstructionForRefTestCase.getTestCaseId(), refTestCaseIds);
        }
        return instructionUtilService.travelReferenceTree(currentTestCaseId, 0, refTestCaseMap);
    }

    private long countTestCaseReferenceLayer(Map<Long, Set<Long>> referenceData, long currentCount,
            Long currentTestCaseId) {
        // get the test case refernece set
        if (referenceData.containsKey(currentTestCaseId)) {
            Set<Long> referenceSet = referenceData.get(currentTestCaseId);
            // check if the set has is not empty
            if (referenceSet.isEmpty()) {
                // return the current count;
                return currentCount;
            }
            else {
                // it has reference, we need to call the function to loop through the set
                long finalCountResult = 0;
                for (long nextLayerTestCaseId : referenceSet) {
                    long countResult = countTestCaseReferenceLayer(referenceData, currentCount + 1,
                            nextLayerTestCaseId);
                    if (countResult > finalCountResult) {
                        finalCountResult = countResult;
                    }
                }
                return finalCountResult;
            }
        }
        else {
            // cloudn't find, juect return the current count;
            return currentCount;
        }

    }

    private Map<Long, Set<Long>> generateTestCaseReferenceMap(List<Map<String, Long>> referenceTestCaseData) {
        if (referenceTestCaseData == null) { return null; }
        Map<Long, Set<Long>> returnMap = new HashMap<Long, Set<Long>>();
        // loop the test case reference data
        for (Map<String, Long> dataPair : referenceTestCaseData) {
            // get the test case id and the reference id
            Long testCaseId = dataPair.get("test_case_id");
            Long referenceId = dataPair.get("ref_test_case_id");
            // check the return map has the test case id or not.
            if (returnMap.containsKey(testCaseId)) {
                // get the set first
                Set<Long> testCaseReferenceIdSet = returnMap.get(testCaseId);
                testCaseReferenceIdSet.add(referenceId);
                returnMap.put(testCaseId, testCaseReferenceIdSet);
            }
            // doesn't contain the key
            else {
                Set<Long> testCaseReferenceIdSet = new HashSet<Long>();
                testCaseReferenceIdSet.add(referenceId);
                returnMap.put(testCaseId, testCaseReferenceIdSet);
            }
        }
        return returnMap;
    }

    private void getReferenceLockDataSet(long referencetestCaseId, Set<Long> refLockDataSet,
            Map<Long, Set<Long>> referenceData) {
        // get the current test case set
        if (referenceData.containsKey(referencetestCaseId)) {
            refLockDataSet.addAll(referenceData.get(referencetestCaseId));
            // loop through the sub reference ids
            for (long newRefernceId : referenceData.get(referencetestCaseId)) {
                this.getReferenceLockDataSet(newRefernceId, refLockDataSet, referenceData);
            }
        }
        else {
            return;
        }
    }

    @Override
    public List<TestCase> getTestCaseReferencedBy(Long testCaseId) {
        return testCaseUtilMapper.getReferencedTestCasesFromTestCaseId(testCaseId);
    }

    @Override
    public Map<Long, Set<Long>> getTestCaseReferenceMap() {
        return this.generateTestCaseReferenceMap(instructionUtilService.getTestCaseReferenceInfo());
    }
    
    @Override
    public boolean isReferenceLookOccur(Long testCaseId, Long referenceTestCaseId) {
        if (testCaseId == null || referenceTestCaseId == null) { return true; }
        // convert the data map
        Map<Long, Set<Long>> referenceDataMap = this.getTestCaseReferenceMap();
        // reference lock data set
        Set<Long> refLockDataSet = new HashSet<Long>();
        this.getReferenceLockDataSet(referenceTestCaseId, refLockDataSet, referenceDataMap);
        // check if the ref lock contain current test case id
        if (refLockDataSet.contains(testCaseId)) {
            return true;
        }
        else {
            return false;
        }
    }
}
