package com.meowlomo.atm.core.service.util.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.util.InstructionOptionMapUtilMapper;
import com.meowlomo.atm.core.service.util.InstructionOptionMapUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionOptionMapUtilServiceImpl implements InstructionOptionMapUtilService{

    @Autowired
    private InstructionOptionMapUtilMapper instructionOptionMapUtilMapper;
    
    @Override
    public Set<Long> selectInstructionOptionIdsByElementType(String elementType) {
        return instructionOptionMapUtilMapper.selectInstructionOptionIdsByElementType(elementType);
    }

    @Override
    public Set<Long> selectInstructionOptionIdsByInstructionAction(String instructionAction) {
        return instructionOptionMapUtilMapper.selectInstructionOptionIdsByInstructionAction(instructionAction);
    }

    @Override
    public Set<Long> selectInstructionOptionIdsByInstructionActionAndElementType(String instructionAction, String elementType) {
        return instructionOptionMapUtilMapper.selectInstructionOptionIdsByInstructionActionAndElementType(instructionAction, elementType);
    }

}
