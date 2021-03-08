package com.meowlomo.atm.core.service.filter.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.InstructionOverwriteMapper;
import com.meowlomo.atm.core.mapper.InstructionReferenceMapper;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.service.filter.InstructionContentFilterService;
import com.meowlomo.atm.core.service.util.CommonUtil;
import com.meowlomo.atm.core.service.util.InstructionOverwriteUtilService;

/**
 * @author scott
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionContentFilterServiceImpl implements InstructionContentFilterService {

    @Autowired
    private CommonUtil commonUtilMapper;

    @Autowired
    private InstructionOverwriteMapper instructionOverwriteMapper;
    @Autowired
    private InstructionOverwriteUtilService instructionOverwriteUtilService;
    @Autowired
    private InstructionReferenceMapper instructionReferenceMapper;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    private final Logger logger = LoggerFactory.getLogger(InstructionContentFilterServiceImpl.class);

    @Override
    public Instruction convertInstructionDataJsonFieldNaming(Instruction record) {
        // check is there is a parameter field
        if (record.getParameter() != null) {
            JsonNode parameter = record.getParameter();
            JsonNode formatedParameter = commonUtilMapper.convertNameToCamelCase(parameter);
            record.setParameter(formatedParameter);
        }

        if (record.getData() != null) {
            JsonNode data = record.getParameter();
            JsonNode formatedData = commonUtilMapper.convertNameToCamelCase(data);
            record.setParameter(formatedData);
        }

        return record;
    }

    @Override
    public List<Instruction> expandReferenceInstruction(List<Instruction> instructions, Long upperOrderIndex) {
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        // loop instructions, to get all reference instruction
        List<Instruction> newInstructions = new ArrayList<Instruction>();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.getType().equalsIgnoreCase("REFERENCE")) {
                Long currentOrderIndex = instruction.getOrderIndex();
                // this is for the current test case's isntruction which are referencing other
                // test cases
                Long refrenceTestCaseId = instruction.getRefTestCaseId();
                InstructionExample example = new InstructionExample();
                example.or().andDeletedEqualTo(false).andTestCaseIdEqualTo(refrenceTestCaseId);
                example.setOrderByClause("order_index");
                List<Instruction> tempInstructions = instructionReferenceMapper.selectByExample(example);
                // object to string
                String objectString = null;
                List<Instruction> referencedInstructions = null;
                try {
                    objectString = mapper.writeValueAsString(tempInstructions);
                    referencedInstructions = mapper.readValue(objectString, new TypeReference<List<Instruction>>() {
                    });
                }
                catch (JsonParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (JsonMappingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Long refTestCaseOverwriteId = instruction.getRefTestCaseOverwriteId();
                Long fromRefInstructionId = instruction.getId();
                if (refTestCaseOverwriteId != null) {
                    // it has test case overwrite with it, wee need to exapnd and execute it
                    InstructionOverwriteExample insOvrExample = new InstructionOverwriteExample();
                    insOvrExample.createCriteria().andTestCaseOverwriteIdEqualTo(refTestCaseOverwriteId);
                    List<InstructionOverwrite> insOvers = instructionOverwriteMapper.selectByExample(insOvrExample);
                    referencedInstructions = instructionOverwriteUtilService
                            .executeInstructionOverwritesOnInstructions(insOvers, referencedInstructions);
                }
                referencedInstructions = this.expandReferenceInstruction(referencedInstructions, currentOrderIndex);
                logger.info("add refrenced instructions from test case " + refrenceTestCaseId
                        + " with instructions number " + referencedInstructions.size());
                logger.debug("Set logical order index mutiple ");
                referencedInstructions = this.generateLogicalOrderIndexToInstructions(referencedInstructions,
                        currentOrderIndex);
                referencedInstructions = this.generateFromRefInstructionId(referencedInstructions,
                        fromRefInstructionId);
                newInstructions.addAll(referencedInstructions);
            }
            else {
                logger.debug("Set logical order index one ");
                instruction = this.generateLogicalOrderIndexToInstruction(instruction, null);
                newInstructions.add(instruction);
            }
        }
        logger.info("Test Case after expantion, total instruction size is " + newInstructions.size());
        return newInstructions;
    }

    @Override
    public List<Instruction> filteroutReferenceAndCommentInstructions(List<Instruction> instructions) {
        List<Instruction> newInstructionsWithoutConmmentAndReference = new ArrayList<Instruction>();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (!instruction.getType().equalsIgnoreCase("comment")
                    && !instruction.getType().equalsIgnoreCase("Reference")) {
                newInstructionsWithoutConmmentAndReference.add(instruction);
            }
        }
        return newInstructionsWithoutConmmentAndReference;
    }

    private List<Instruction> generateFromRefInstructionId(List<Instruction> instructions, Long fromRefInstructionId) {
        List<Instruction> returnInstructions = new ArrayList<Instruction>();
        for (Instruction instruction : instructions) {
            instruction.setFromReferenceInstructionId(fromRefInstructionId);
            returnInstructions.add(instruction);
        }
        return returnInstructions;
    }

    @Override
    public Instruction generateInstructionElementIdForInsertAndUpdate(Instruction record) {
        String type = record.getType();
        if (type != null && RuntimeVariables.getInstructionTypes().contains(type)) {
            InstructionType instructionType = RuntimeVariables.getInstructionTypeObjectMap().get(type);
            Boolean requireElement = instructionType.getIsElementRequired();
            if (requireElement != null && requireElement) {
                if (record.getElementId() == null) {
                    logger.error("trying to insert a instrcution which required a element, but the record has non.");
                    record.setType(null);
                    record.setElementId(null);
                }
            }
            else if (requireElement != null && !requireElement) {
                logger.info(
                        "trying to insert a instrcution which required non element, the element id for this insert will be set to null.");
                record.setElementId(null);
            }
        }
        else {
            logger.error("trying to insert a instrcution with out valid instruction type");
            record.setType(null);
            record.setElementId(null);
        }
        logger.debug("instrcution element id filter " + record.toString());
        return record;
    }

    @Override
    public Instruction generateInstructionVirtualElementInfo(Instruction record) {
        logger.debug("instrcution is_driver generator entry " + record.toString());
        String type = record.getType();
        if (type != null && RuntimeVariables.getInstructionTypes().contains(type)) {
            InstructionType instrcutionType = RuntimeVariables.getInstructionTypeObjectMap().get(type);
            if (instrcutionType != null) {
                Boolean isDriver = instrcutionType.getIsDriver();
                Long virtualElementId = instrcutionType.getVirtualElementId();
                if (isDriver != null && isDriver && virtualElementId != null) {
                    record.setIsDriver(isDriver);
                    record.setElementId(virtualElementId);
                }
            }
        }
        logger.debug("instrcution is_driver generator out " + record.toString());
        return record;
    }

    private Instruction generateLogicalOrderIndexToInstruction(Instruction instruction, Long upperOrderIndex) {
        String logicalOrderIndex = instruction.getLogicalOrderIndex();
        if (logicalOrderIndex == null) {
            logicalOrderIndex = String.valueOf(instruction.getOrderIndex());
        }
        String newLogicalOrderIndex = upperOrderIndex == null ? logicalOrderIndex
                : upperOrderIndex + "-" + logicalOrderIndex;
        logger.debug("Set logical order index [" + newLogicalOrderIndex + "] logicalOrderIndex [" + logicalOrderIndex
                + "] upper order index [" + upperOrderIndex + "] for instruction " + instruction.getId()
                + " test case id :" + instruction.getTestCaseId());
        instruction.setLogicalOrderIndex(newLogicalOrderIndex);
        return instruction;
    }

    private List<Instruction> generateLogicalOrderIndexToInstructions(List<Instruction> instructions,
            Long upperOrderIndex) {
        List<Instruction> returnList = new ArrayList<Instruction>();
        for (int i = 0; i < instructions.size(); i++) {
            // get the current order index and attached to the end of the upper logical
            // order index
            Instruction instruction = instructions.get(i);
            instruction = this.generateLogicalOrderIndexToInstruction(instruction, upperOrderIndex);
            returnList.add(instruction);
        }
        return returnList;
    }
}
