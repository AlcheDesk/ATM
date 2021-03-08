package com.meowlomo.atm.core.service.util.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.InstructionMapper;
import com.meowlomo.atm.core.mapper.InstructionOptionEntryMapper;
import com.meowlomo.atm.core.mapper.InstructionReferenceMapper;
import com.meowlomo.atm.core.mapper.util.InstructionUtilMapper;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.InstructionOptionEntry;
import com.meowlomo.atm.core.model.InstructionOptionEntryExample;
import com.meowlomo.atm.core.service.util.InstructionUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionUtilServiceImpl implements InstructionUtilService {

    @Autowired
    private InstructionMapper instructionMapper;
    @Autowired
    private InstructionOptionEntryMapper instructionOptionEntryMapper;
    @Autowired
    private InstructionReferenceMapper instructionReferenceMapper;
    @Autowired
    private InstructionUtilMapper instructionUtilMapper;

    @Override
    public Instruction checkOptionForPatchInstruction(Instruction record) {
        List<InstructionOptionEntry> instructionOptions = record.getInstructionOptions();
        if (instructionOptions != null && !instructionOptions.isEmpty()) {
            // delete the old instruction option first, because we support only one now
            InstructionOptionEntryExample instructionOptionEntryExample = new InstructionOptionEntryExample();
            instructionOptionEntryExample.createCriteria().andInstructionIdEqualTo(record.getId());
            instructionOptionEntryMapper.deleteByExample(instructionOptionEntryExample);
            // then insert the first one only
            instructionOptions.get(0).setInstructionId(record.getId());
            instructionOptionEntryMapper.insertSelective(instructionOptions.get(0));
        }
        else if (instructionOptions != null && instructionOptions.size() == 0) {
            // delete the old instruction option
            InstructionOptionEntryExample instructionOptionEntryExample = new InstructionOptionEntryExample();
            instructionOptionEntryExample.createCriteria().andInstructionIdEqualTo(record.getId());
            instructionOptionEntryMapper.deleteByExample(instructionOptionEntryExample);
        }
        return record;
    }

    @Override
    public Instruction checkOptionForPostInstruction(Instruction record) {
        List<InstructionOptionEntry> instructionOptions = record.getInstructionOptions();
        if (instructionOptions != null && !instructionOptions.isEmpty()) {
            // we support only one instruction option now
            instructionOptions.get(0).setInstructionId(record.getId());
            instructionOptionEntryMapper.insert(instructionOptions.get(0));
        }
        return record;
    }

    @Override
    public Instruction copy(Map<String, Map<Long, Long>> refElementIdPackageMap, Map<Long, Long> reftestCaseIdMap,
            Long testCaseId, Instruction record) throws Exception {
        // get Instruction name
        long instructionOldId = record.getId();
        long instructionOldOrderIndex = record.getOrderIndex();
        record = instructionMapper.selectByPrimaryKey(instructionOldId);
        record.setId(null);
        record.setTestCaseId(testCaseId);
        record.setOrderIndex(instructionOldOrderIndex);

        // replace old application id and section id and element id for instruction
        if (refElementIdPackageMap != null) {
            if ((record.getApplicationId() != null) && (refElementIdPackageMap.get("refApplicationIdMap") != null)) {
                if (refElementIdPackageMap.get("refApplicationIdMap").get(record.getApplicationId()) != null) {
                    Long value = (Long) refElementIdPackageMap.get("refApplicationIdMap")
                            .get(record.getApplicationId());
                    record.setApplicationId(value);
                }
            }
            if ((record.getSectionId() != null) && (refElementIdPackageMap.get("refSectionIdMap") != null)) {
                if (refElementIdPackageMap.get("refSectionIdMap").get(record.getSectionId()) != null) {
                    Long value = (Long) refElementIdPackageMap.get("refSectionIdMap").get(record.getSectionId());
                    record.setSectionId(value);
                }
            }
            if ((record.getElementId() != null) && (refElementIdPackageMap.get("refElementIdMap") != null)) {
                if (refElementIdPackageMap.get("refElementIdMap").get(record.getElementId()) != null) {
                    Long value = (Long) refElementIdPackageMap.get("refElementIdMap").get(record.getElementId());
                    record.setElementId(value);
                }
            }
        }

        long insertResult = instructionMapper.insertSelective(record);
        // new instruction id
        long newInstructionId = record.getId();
        if (insertResult == 1) {
            // insert InstructionOption link one by one
            InstructionOptionEntryExample instructionOptionEntryExample = new InstructionOptionEntryExample();
            instructionOptionEntryExample.or().andInstructionIdEqualTo(instructionOldId);
            List<InstructionOptionEntry> instructionOptionRecords = instructionOptionEntryMapper
                    .selectByExample(instructionOptionEntryExample);
            // insert instructionOption link one by one
            for (int j = 0; j < instructionOptionRecords.size(); j++) {
                InstructionOptionEntry instructionOptionRecord = instructionOptionRecords.get(j);
                instructionOptionRecord.setInstructionId(newInstructionId);
                instructionOptionEntryMapper.insert(instructionOptionRecord);
            }
            // order the instructions
            // this.reorderOrderIndex(record, true);
            Instruction returnRecord = instructionMapper.selectByPrimaryKey(newInstructionId);
            return returnRecord;
        }
        else {
            throw new Exception("Error on insert coreponding instruction");
        }
    }

    @Override
    public Instruction copyByPrimaryId(Long id, boolean nameIncrement) {
        Instruction record = instructionReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            record.setCopyFromId(id);
            long insertResult = instructionMapper.insert(record);
            if (insertResult == 1) {
                // call lower level copy
                return record;
            }
            else {
                return null;
            }
        }
    }

    @Override
    public List<Map<String, Long>> getTestCaseReferenceInfo() {
        return instructionUtilMapper.selectTestCaseReferneceInfo();
    }

    @Override
    public long travelReferenceTree(Long rootTestCaseId, long currentCount) {
        // the return point
        InstructionExample example = new InstructionExample();
        example.createCriteria().andTestCaseIdEqualTo(rootTestCaseId).andRefTestCaseIdIsNotNull()
                .andDeletedEqualTo(false);
        List<Instruction> instructionsForRefTestCases = instructionMapper.selectByExample(example);
        if (instructionsForRefTestCases.isEmpty()) {
            return currentCount;
        }
        else {
            // we have ref here, call the function recursively
            // loop the reference
            long newCurrentCount = currentCount + 1;
            long maxCurrentCount = 1;
            for (Instruction refInstruction : instructionsForRefTestCases) {
                Long refTestCaseId = refInstruction.getRefTestCaseId();
                long deepCount = travelReferenceTree(refTestCaseId, newCurrentCount);
                if (deepCount + currentCount > newCurrentCount) {
                    // newCurrentCount = deepCount + currentCount;
                    maxCurrentCount = deepCount;
                }
            }
            newCurrentCount = maxCurrentCount;
            return newCurrentCount;
        }
    }

    @Override
    public long travelReferenceTree(Long rootTestCaseId, long currentCount, Map<Long, Set<Long>> map) {
        // the return point
        Set<Long> refTestCaseIds = map.get(rootTestCaseId);
        if (refTestCaseIds == null) {
            return currentCount;
        }
        else {
            // we have ref here, call the function recursively
            // loop the reference
            long newCurrentCount = currentCount + 1;
            long maxCurrentCount = currentCount + 1;
            for (Long refTestCaseId : refTestCaseIds) {
                long deepCount = travelReferenceTree(refTestCaseId, newCurrentCount, map);
                if (deepCount > newCurrentCount) {
                    // if (deepCount > 1) {
                    maxCurrentCount = deepCount;
                }
            }
            newCurrentCount = maxCurrentCount;
            return newCurrentCount;
        }
    }
}
