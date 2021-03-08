package com.meowlomo.atm.core.mapper.util;

import java.util.List;
import java.util.Map;

import com.meowlomo.atm.core.model.Instruction;

public interface InstructionUtilMapper {

    List<Map<String, Long>> selectTestCaseReferneceInfo();

    List<Instruction> selectInstructionRefInfoByTestCaseId(Long testCaseId);

}