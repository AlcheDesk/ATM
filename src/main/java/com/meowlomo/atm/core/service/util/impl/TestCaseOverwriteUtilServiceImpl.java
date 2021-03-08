package com.meowlomo.atm.core.service.util.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.InstructionOverwriteReferenceMapper;
import com.meowlomo.atm.core.mapper.TestCaseOverwriteMapper;
import com.meowlomo.atm.core.mapper.TestCaseOverwriteReferenceMapper;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.InstructionOverwriteUtilService;
import com.meowlomo.atm.core.service.util.TestCaseOverwriteUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseOverwriteUtilServiceImpl implements TestCaseOverwriteUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;

    @Autowired
    private InstructionOverwriteReferenceMapper instructionOverwriteReferenceMapper;
    @Autowired
    private InstructionOverwriteUtilService instructionOverwriteUtilService;
    private final Logger logger = LoggerFactory.getLogger(TestCaseOverwriteUtilServiceImpl.class);
    @Autowired
    private TestCaseOverwriteMapper testCaseOverwriteMapper;
    @Autowired
    private TestCaseOverwriteReferenceMapper testCaseOverwriteReferenceMapper;

    @Override
    public TestCaseOverwrite copyByPrimaryId(Long id, boolean nameIncrement, String testCaseOverwriteName) {
        TestCaseOverwrite record = testCaseOverwriteReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (testCaseOverwriteName != null) {
                finalName = testCaseOverwriteName;
            }
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewTestCaseOverwriteForTestCaseNameForCopy(record.getTestCaseId(),
                        finalName);
            }
            record.setName(finalName);
            record.setCopyFromId(id);
            long insertResult = testCaseOverwriteMapper.insert(record);
            if (insertResult == 1) {
                boolean finalResult = true;
                // check next level
                // instructionOverwrites
                List<InstructionOverwrite> instructionOverwrites = record.getInstructionOverwrites();
                for (InstructionOverwrite nextLevelRecord : instructionOverwrites) {
                    InstructionOverwrite refNextLevelRecord = instructionOverwriteReferenceMapper
                            .selectByPrimaryKey(nextLevelRecord.getId());
                    if (refNextLevelRecord.getInstruction() != null) {
                        if (instructionOverwriteUtilService.copyByPrimaryIdForTestCaseOverwrite(nextLevelRecord.getId(), false, record.getId()) == null) {
                            finalResult = false;
                            break;
                        }
                    }
                }
                if (finalResult) {
                    return record;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
    }

    @Override
    public TestCase executeTestCaseOverwriteOnTestCase(TestCaseOverwrite fullExpandedTestCaseOverwrite,
            TestCase fullExpandedReferenceTestCase) {
        if (fullExpandedReferenceTestCase == null) {
            logger.debug("**********Test Case Overwrite is null ");
            return fullExpandedReferenceTestCase;
        }

        // start the overwrite
        /*
         * we do the following steps 1: loop the overwrite to get the instruction
         * overwrites 2: match the instruction overwrite one by one to the test case's
         * instructions 3: if match, then overwrite the instruction 4: replace the
         * instruction in the list 5: put back the to the test case 6: return
         */

        // expand the instruction overwrite and test case instructions for later
        // processing
        List<Instruction> referenceInstructionsFromTestCase = fullExpandedReferenceTestCase.getInstructions();
        List<InstructionOverwrite> instructionOverwrites = fullExpandedTestCaseOverwrite.getInstructionOverwrites();
        referenceInstructionsFromTestCase = instructionOverwriteUtilService
                .executeInstructionOverwritesOnInstructions(instructionOverwrites, referenceInstructionsFromTestCase);
        fullExpandedReferenceTestCase.setInstructions(referenceInstructionsFromTestCase);
        return fullExpandedReferenceTestCase;
    }

}
