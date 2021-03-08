package com.meowlomo.atm.core.service.util.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.ElementMapper;
import com.meowlomo.atm.core.mapper.InstructionOverwriteMapper;
import com.meowlomo.atm.core.mapper.InstructionOverwriteReferenceMapper;
import com.meowlomo.atm.core.mapper.InstructionReferenceMapper;
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.custom.InstructionOverwriteField;
import com.meowlomo.atm.core.service.util.InstructionOverwriteUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionOverwriteUtilServiceImpl implements InstructionOverwriteUtilService {

    private static final Configuration configuration = Configuration.builder()
            .jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider()).build();

    @Autowired
    private ElementMapper elementMapper;

    @Autowired
    private InstructionOverwriteMapper instructionOverwriteMapper;
    @Autowired
    private InstructionOverwriteReferenceMapper instructionOverwriteReferenceMapper;
    @Autowired
    private InstructionReferenceMapper instructionReferenceMapper;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    private final Logger logger = LoggerFactory.getLogger(InstructionOverwriteUtilServiceImpl.class);
    @Autowired
    private TestCaseMapper testCaseMapper;

    @Override
    public InstructionOverwrite copyByPrimaryIdForTestCaseOverwrite(Long id, boolean nameIncrement, Long testCaseOverwriteId) {
        InstructionOverwrite record = instructionOverwriteReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            if (testCaseOverwriteId != null) {
                record.setTestCaseOverwriteId(testCaseOverwriteId);
            }
            record.setCopyFromId(id);
            long insertResult = instructionOverwriteMapper.insert(record);
            if (insertResult == 1) {
                boolean finalResult = true;
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
    public List<Instruction> executeInstructionOverwritesOnInstructions(
            List<InstructionOverwrite> instructionOverwrites, List<Instruction> referenceInstructionsFromTestCase) {
        if (instructionOverwrites == null || instructionOverwrites.isEmpty()) {
            logger.debug(String.format("**********empty instruction overwrites, will be skipped  "));
            return referenceInstructionsFromTestCase;
        }
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        // we use the instuction for looping, becasue of the ontruction overwrite number
        // is usally less than the intructions number from the test case.
        Set<Integer> overwritenInstructionIndexes = new HashSet<Integer>();
        for (int insOverCount = 0; insOverCount < instructionOverwrites.size(); insOverCount++) {
            InstructionOverwrite instructionOverwrite = instructionOverwrites.get(insOverCount);
            Long insOverElementId = instructionOverwrite.getElementId();
            Long insOverInstructionId = instructionOverwrite.getInstructionId();
            Long insOverTestCaseId = instructionOverwrite.getTestCaseId();
            String overwriteFieldsString = instructionOverwrite.getOverwriteFields();
            if (overwriteFieldsString == null) {
                // nothing to overwrite
            }
            else {
                logger.debug("**********processing instruction overwrite " + instructionOverwrite);
                // find the instruction to match the instruction overwrite
                for (int instructionCount = 0; instructionCount < referenceInstructionsFromTestCase
                        .size(); instructionCount++) {
                    if (overwritenInstructionIndexes.contains(instructionCount)) {
                        continue;
                    }
                    Instruction referenceInstruction = referenceInstructionsFromTestCase.get(instructionCount);
                    logger.debug("**********matching instruction " + referenceInstruction);
                    logger.debug("instuction mathing " + (referenceInstruction.getId().equals(insOverInstructionId)
                            ? true
                            : "ins over id " + insOverInstructionId + " ins id " + referenceInstruction.getId()));
                    if (referenceInstruction.getId().equals(insOverInstructionId)
                            && referenceInstruction.getTestCaseId().equals(insOverTestCaseId)) {
                        // check if both has from ref instruction id
                        Long refInsIdFromInsOver = instructionOverwrite.getFromReferenceInstructionId();
                        Long refInsIdFromIns = referenceInstruction.getFromReferenceInstructionId();
                        if ((refInsIdFromIns == null && refInsIdFromInsOver != null)
                                || (refInsIdFromIns != null && refInsIdFromInsOver == null)
                                || ((refInsIdFromIns != null && refInsIdFromInsOver != null)
                                        && !refInsIdFromIns.equals(refInsIdFromInsOver))) {
                            continue;
                        }

                        // matched, let us do the overwrite
                        /*
                         * 1: get the fields and data from the instruction overwrites first 2: do the
                         * overwrite by jsonpath or something else
                         */
                        logger.debug("**********found match for instruction overwrite id "
                                + instructionOverwrite.getId() + " instrcution id " + insOverInstructionId
                                + " element id " + insOverElementId);
                        logger.debug("**********Start processing overwrite for instruction : %s ",
                                referenceInstruction.toString());
                        try {
                            JsonNode overwriteData = instructionOverwrite.getData();
                            if (overwriteData.isArray()) {
                                List<InstructionOverwriteField> data = objectMapper
                                        .readerFor(new TypeReference<List<InstructionOverwriteField>>() {
                                        }).readValue(overwriteData);
                                Instruction overwritedReferenceInstruction = this
                                        .overwriteInstrucitonFields(overwriteFieldsString, data, referenceInstruction);
                                referenceInstructionsFromTestCase.set(instructionCount, overwritedReferenceInstruction);
                            }
                        }
                        catch (IOException e) {
                            logger.error("IOException on coverting data to instruction overwrite field", e);
                        }
                        // because we overwriten this instruction, we need to mark it as overwriten.
                        overwritenInstructionIndexes.add(instructionCount);
                        break;
                    }
                }
            }
        }
        return referenceInstructionsFromTestCase;
    }

    @Override
    public InstructionOverwrite generateInstructionOverwriteFromInstruction(Instruction instruction) {
        InstructionOverwrite insOverwrite = new InstructionOverwrite();
        insOverwrite.setTestCaseId(instruction.getTestCaseId());
        insOverwrite.setInstructionId(instruction.getId());
        insOverwrite.setInstruction(instruction);
        insOverwrite.setElementId(instruction.getElementId());
        Element element = instruction.getElement() == null
                ? elementMapper.selectByPrimaryKey(instruction.getElementId())
                : instruction.getElement();

        // add element type id for instructionOverwrite start
        if (element != null) {
            element.setTypeId(RuntimeVariables.getElementTypeToIdMap().get(element.getType()));
        }
        // add element type id for instructionOverwrite end
        insOverwrite.setElement(element);
        insOverwrite.setType(instruction.getType());
        String insType = instruction.getType();
        // check for reference type
        if (insType.equalsIgnoreCase("Reference")) {
            InstructionOverwriteField instructionOverwriteField = new InstructionOverwriteField();
            instructionOverwriteField
                    .setName(testCaseMapper.selectByPrimaryKey(instruction.getTestCaseId()).getName());
            // select all test case override for this referenced test case
            Long refTestCaseId = instruction.getRefTestCaseId();
            instructionOverwriteField.setRefTestCaseId(refTestCaseId);
        }
        else {
            List<InstructionOverwriteField> instructionFieldList = new ArrayList<InstructionOverwriteField>();
            String overridableFieldString = RuntimeVariables.getInstructionTypeObjectMap().get(insType)
                    .getOverridableFields();
            insOverwrite.setOverwriteFields(overridableFieldString);
            if (overridableFieldString != null) {
                String[] overridableFields = overridableFieldString.split(";");
                for (String fieldString : overridableFields) {
                    if (!fieldString.isEmpty()) {
                        InstructionOverwriteField instructionOverwriteField = new InstructionOverwriteField();
                        String lastWord = fieldString.replaceAll("^.*?(\\w+)\\W*$", "$1");
                        instructionOverwriteField.setName(lastWord);
                        instructionOverwriteField.setJsonPath(fieldString);
                        instructionFieldList.add(instructionOverwriteField);
                    }
                }
            }
            insOverwrite.setData(jacksonConverter.getObjectMapper().valueToTree(instructionFieldList));
        }
        return insOverwrite;
    }

    @Override
    public List<InstructionOverwrite> generateInstructionOverwritesForTestCase(Long testCaseId) {
        // get the instruction type which are overridable
        List<InstructionType> instrcutionTypes = new ArrayList<InstructionType>(
                RuntimeVariables.getInstructionTypeObjectMap().values());
        List<String> overridableInsTypes = new ArrayList<String>();
        for (InstructionType insType : instrcutionTypes) {
            if (insType.getOverridableFields() != null) {
                overridableInsTypes.add(insType.getName());
            }
        }
        if (overridableInsTypes.isEmpty()) { return new ArrayList<InstructionOverwrite>(); }
        // get the undelete instructions for the test case without reference, manual,
        InstructionExample instructionExample = new InstructionExample();
        instructionExample.createCriteria().andDeletedEqualTo(false).andTestCaseIdEqualTo(testCaseId);
        instructionExample.setOrderByClause("order_index ASC ");
        // get the overridable ins for the test case
        List<Instruction> instructions = instructionReferenceMapper.selectByExample(instructionExample);
        // Generate the map for instruction one by one
        List<InstructionOverwrite> returnList = new ArrayList<InstructionOverwrite>();
        for (Instruction ins : instructions) {
            InstructionOverwrite insOverwrite = this.generateInstructionOverwriteFromInstruction(ins);
            returnList.add(insOverwrite);
        }
        return returnList;
    }

    @Override
    public List<InstructionOverwrite> generateInstructionOverwritesForTestCaseOverwrite(
            TestCaseOverwrite testCaseOverwrite) {
        if (testCaseOverwrite.getId() == null || testCaseOverwrite.getTestCaseId() == null) {
            return new ArrayList<InstructionOverwrite>();
        }
        List<InstructionOverwrite> instructionOverwrites = this
                .generateInstructionOverwritesForTestCase(testCaseOverwrite.getTestCaseId());
        List<InstructionOverwrite> newInstructionOverwrites = new ArrayList<InstructionOverwrite>();
        for (InstructionOverwrite insOver : instructionOverwrites) {
            insOver.setTestCaseOverwriteId(testCaseOverwrite.getId());
            newInstructionOverwrites.add(insOver);
        }
        return newInstructionOverwrites;
    }

    private Instruction overwriteInstrucitonFields(String overwriteFieldString,
            List<InstructionOverwriteField> instructionOverwriteFieldList, Instruction referenceInstruction) {
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        referenceInstruction.setIsOverwrite(true);
        String fields[] = overwriteFieldString.split(";");
        Element element = referenceInstruction.getElement();
        boolean elementModified = false;
        for (String fieldString : fields) {
            if (fieldString.isEmpty()) {
                continue;
            }
            // convert the instruction to JsonNode first
            // find the value from the data
            JsonNode value = null;
            logger.debug("**********processing instruction overwrite field " + fieldString);
            for (InstructionOverwriteField instructionOverwriteField : instructionOverwriteFieldList) {
                logger.debug(String.format(
                        "**********processing value for instruction over write field jsonpath:[%s] value:[%s]",
                        instructionOverwriteField.getJsonPath(), instructionOverwriteField.getValue()));
                if (fieldString.equalsIgnoreCase(instructionOverwriteField.getJsonPath())) {
                    value = instructionOverwriteField.getValue();
                    logger.debug("**********got value " + value);
                    break;
                }
            }
            if (value != null && !(value instanceof NullNode)) { // if the value is null or NullNode, we keep the
                                                                 // original value
                logger.info(
                        "Set value [" + value + "] class:[" + value.getClass().getName() + "]to [" + fieldString + "]");
                String jsonInstruction;
                String jsonElement;
                // check if we need to over write the element
                if (fieldString.toLowerCase().contains("locator") && element != null) {
                    try {
                        jsonElement = objectMapper.writeValueAsString(element);
                        JsonNode overwritedJsonElement = JsonPath.using(configuration).parse(jsonElement)
                                .set(fieldString, value).json();
                        logger.debug("Overwrited Instruction " + System.lineSeparator()
                                + objectMapper.writeValueAsString(overwritedJsonElement));
                        // modified the element
                        element = objectMapper.treeToValue(overwritedJsonElement, Element.class);
                        elementModified = true;
                    }
                    catch (JsonProcessingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    jsonInstruction = objectMapper.writeValueAsString(referenceInstruction);
                    JsonNode newJsonInstruction = JsonPath.using(configuration).parse(jsonInstruction)
                            .set(fieldString, value).json();
                    logger.debug("**********instruction after over write " + newJsonInstruction);
                    referenceInstruction = objectMapper.treeToValue(newJsonInstruction, Instruction.class);
                }
                catch (JsonProcessingException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (elementModified) {
            referenceInstruction.setElement(element);
        }
        return referenceInstruction;
    }

}
