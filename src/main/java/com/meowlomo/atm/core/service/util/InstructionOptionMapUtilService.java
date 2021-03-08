package com.meowlomo.atm.core.service.util;

import java.util.Set;

public interface InstructionOptionMapUtilService {
    Set<Long> selectInstructionOptionIdsByElementType(String elementType);
    Set<Long> selectInstructionOptionIdsByInstructionAction(String instructionAction);
    Set<Long> selectInstructionOptionIdsByInstructionActionAndElementType(String instructionAction, String elementType);
}
