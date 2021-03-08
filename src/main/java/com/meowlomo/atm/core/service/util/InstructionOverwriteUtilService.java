package com.meowlomo.atm.core.service.util;

import java.util.List;

import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwrite;

public interface InstructionOverwriteUtilService {
    InstructionOverwrite copyByPrimaryIdForTestCaseOverwrite(Long id, boolean nameIncrement, Long testCaseOverwriteId);

    List<Instruction> executeInstructionOverwritesOnInstructions(List<InstructionOverwrite> instructionOverwrites,
            List<Instruction> referenceInstructionsFromTestCase);

    InstructionOverwrite generateInstructionOverwriteFromInstruction(Instruction instruction);

    List<InstructionOverwrite> generateInstructionOverwritesForTestCase(Long testCaseId);

    List<InstructionOverwrite> generateInstructionOverwritesForTestCaseOverwrite(TestCaseOverwrite testCaseOverwrite);

}
