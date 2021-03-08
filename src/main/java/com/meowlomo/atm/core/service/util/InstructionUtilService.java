package com.meowlomo.atm.core.service.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.meowlomo.atm.core.model.Instruction;

public interface InstructionUtilService {

    Instruction checkOptionForPatchInstruction(Instruction record);

    Instruction checkOptionForPostInstruction(Instruction record);

    Instruction copy(Map<String, Map<Long, Long>> refElementIdPackageMap, Map<Long, Long> reftestCaseIdMap,
            Long testCaseId, Instruction record) throws Exception;

    Instruction copyByPrimaryId(Long id, boolean nameIncrement);

    List<Map<String, Long>> getTestCaseReferenceInfo();

    long travelReferenceTree(Long rootTestCaseId, long currentCount);

    long travelReferenceTree(Long rootTestCaseId, long currentCount, Map<Long, Set<Long>> map);
}
