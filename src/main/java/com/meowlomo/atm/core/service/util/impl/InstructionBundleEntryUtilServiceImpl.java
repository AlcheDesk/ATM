package com.meowlomo.atm.core.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.util.InstructionBundleEntryUtilMapper;
import com.meowlomo.atm.core.service.util.InstructionBundleEntryUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionBundleEntryUtilServiceImpl implements InstructionBundleEntryUtilService {

    @Autowired
    private InstructionBundleEntryUtilMapper instructionBundleEntryUtilMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void regenerateOrderIndexByInstructionBundleId(Long instructionBundleId) {
        if (instructionBundleId != null) {
            instructionBundleEntryUtilMapper.updateOrderIndexByInstructionBundleId(instructionBundleId);
        }
    }

}
