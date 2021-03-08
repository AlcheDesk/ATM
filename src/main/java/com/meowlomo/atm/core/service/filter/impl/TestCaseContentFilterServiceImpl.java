package com.meowlomo.atm.core.service.filter.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.service.filter.InstructionContentFilterService;
import com.meowlomo.atm.core.service.filter.TestCaseContentFilterService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseContentFilterServiceImpl implements TestCaseContentFilterService {

    @Autowired
    private InstructionContentFilterService instructionContentFilterService;

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(TestCaseContentFilterServiceImpl.class);

    @Override
    public TestCase expandTestCaseWithReferencedInstructions(TestCase testCaseFullRef) {
        // get all instructions from the test case
        List<Instruction> instructions = testCaseFullRef.getInstructions();
        instructions = instructionContentFilterService.expandReferenceInstruction(instructions, null);
        // loop the instruction
        testCaseFullRef.setInstructions(instructions);
        return testCaseFullRef;
    }
}
