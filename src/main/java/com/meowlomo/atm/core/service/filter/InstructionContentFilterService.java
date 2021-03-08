package com.meowlomo.atm.core.service.filter;

import java.util.List;

import com.meowlomo.atm.core.model.Instruction;

public interface InstructionContentFilterService {

    Instruction convertInstructionDataJsonFieldNaming(Instruction record);

    List<Instruction> expandReferenceInstruction(List<Instruction> instructions, Long upperOrderIndex);

    List<Instruction> filteroutReferenceAndCommentInstructions(List<Instruction> instructions);

    Instruction generateInstructionElementIdForInsertAndUpdate(Instruction record);

    Instruction generateInstructionVirtualElementInfo(Instruction record);
}
