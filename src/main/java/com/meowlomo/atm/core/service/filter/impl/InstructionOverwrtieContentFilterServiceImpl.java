package com.meowlomo.atm.core.service.filter.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.core.mapper.InstructionOverwriteMapper;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;
import com.meowlomo.atm.core.service.filter.InstructionOverwriteContentFilterService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionOverwrtieContentFilterServiceImpl implements InstructionOverwriteContentFilterService{
    
    @Autowired
    private InstructionOverwriteMapper instructionOverwriteMapper;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    
    @Override
    public List<InstructionOverwrite> expandInstructionOverwrite(List<InstructionOverwrite> instructionOverwrites) {
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        List<InstructionOverwrite> expanedInstructionOverwrites = new ArrayList<InstructionOverwrite>();
        for (int insOverCount = 0 ; insOverCount < instructionOverwrites.size(); insOverCount++) {
            InstructionOverwrite instructionOverwrite = instructionOverwrites.get(insOverCount);
            if(instructionOverwrite.getType().equalsIgnoreCase("Reference")) {
                /* this has tw oconditions we need to consider
                 1: it has test case overwrite ref id, we use the one from the test case overwrite
                 2: the test case overwrtite ref id is null, and the test case reference has the test case overwrite id, then we use it from the test case
                */
                //get the instruction overwrite of that test case overwrite 
                Long refTestCaseOverwriteId = instructionOverwrite.getRefTestCaseOverwriteId();
                Long fromRefInstructionId = instructionOverwrite.getInstructionId();
                // we will do the work only when the test case overwrite is not null.
                if (refTestCaseOverwriteId != null) {               
                    InstructionOverwriteExample lowerInstructionOverwriteExample = new InstructionOverwriteExample();
                    lowerInstructionOverwriteExample.createCriteria().andTestCaseOverwriteIdEqualTo(refTestCaseOverwriteId);
                    List<InstructionOverwrite> tempInstructionOverwrites = instructionOverwriteMapper.selectByExample(lowerInstructionOverwriteExample);
                    // object to string
                    String objectString = null;                
                    List<InstructionOverwrite> instructionOverwritesInLowerLevel = null;
                    try {
                        objectString = mapper.writeValueAsString(tempInstructionOverwrites);
                        instructionOverwritesInLowerLevel = mapper.readValue(objectString, new TypeReference<List<InstructionOverwrite>>(){});
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
                    //expand the lower level
                    List<InstructionOverwrite> exapndedLowerInstructionOverwrites = this.expandInstructionOverwrite(instructionOverwritesInLowerLevel);
                    exapndedLowerInstructionOverwrites = this.generateFromRefInstructionId(exapndedLowerInstructionOverwrites, fromRefInstructionId);
                    expanedInstructionOverwrites.addAll(exapndedLowerInstructionOverwrites);                    
                }
            }
            else {
                expanedInstructionOverwrites.add(instructionOverwrite);
            }
        }
        return expanedInstructionOverwrites;
    }
    
    private List<InstructionOverwrite> generateFromRefInstructionId(List<InstructionOverwrite> instructionOverwrites, Long fromRefInstructionId){
        List<InstructionOverwrite> returnInstructionOvers = new ArrayList<InstructionOverwrite>();
        for (InstructionOverwrite instructionOver : instructionOverwrites) {
            instructionOver.setFromReferenceInstructionId(fromRefInstructionId);
            returnInstructionOvers.add(instructionOver);
        }
        return returnInstructionOvers;
    }
}
