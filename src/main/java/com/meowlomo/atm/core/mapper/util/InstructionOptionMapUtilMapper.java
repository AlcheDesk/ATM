package com.meowlomo.atm.core.mapper.util;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface InstructionOptionMapUtilMapper {
    Set<Long> selectInstructionOptionIdsByInstructionActionAndElementType(@Param("instructionAction") String instructionAction, @Param("elementType") String elementType);
    Set<Long> selectInstructionOptionIdsByInstructionAction(@Param("instructionAction") String instructionAction);
    Set<Long> selectInstructionOptionIdsByElementType(@Param("elementType") String elementType);
}