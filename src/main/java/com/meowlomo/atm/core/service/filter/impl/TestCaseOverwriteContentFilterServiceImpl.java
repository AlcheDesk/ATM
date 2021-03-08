package com.meowlomo.atm.core.service.filter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.service.filter.InstructionOverwriteContentFilterService;
import com.meowlomo.atm.core.service.filter.TestCaseOverwriteContentFilterService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseOverwriteContentFilterServiceImpl implements TestCaseOverwriteContentFilterService{
    
    @Autowired
    private InstructionOverwriteContentFilterService instructionOverwriteContentFilterService;
    
    @Override
    public TestCaseOverwrite expandTestCaseOverwriteWithRefereneceTestCaseOverwrite(TestCaseOverwrite testCaseOverwriteRef) {
        // get all instructions from the test case
        List<InstructionOverwrite> instructionOverwrites = testCaseOverwriteRef.getInstructionOverwrites();
        if (instructionOverwrites == null || instructionOverwrites.isEmpty()) {
            return testCaseOverwriteRef;
        }
        instructionOverwrites = instructionOverwriteContentFilterService.expandInstructionOverwrite(instructionOverwrites);
        // loop the instruction
        testCaseOverwriteRef.setInstructionOverwrites(instructionOverwrites);
        return testCaseOverwriteRef;
    }
}
