package com.meowlomo.atm.core.service.autogen.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.service.autogen.InstructionAutoGenService;

@Service
public class InstructionAutoGenServiceImpl implements InstructionAutoGenService{
    
    private final Set<String> UNIQUE_ACTION_TYPE_SET = new HashSet<>(Arrays.asList(
            "SQL", 
            "JavaScript", 
            "StringDataProcessor", 
            "MathExpressionProcessor", 
            "SOAP_API",
            "RPC_Dubbo",
            "VirtualJavaScript",
            "CheckEmail"));
    
    @Override
    public Instruction autoGenFields(Instruction record) {
        //set default action for some instruction type
        if (record != null && record.getType() != null && UNIQUE_ACTION_TYPE_SET.contains(record.getType())) {
            record.setAction("Execute");
        }
        //set virtual element for some instruction
        if (record != null && record.getType() != null) {
            //get the instruction type from the run time variable
            if (RuntimeVariables.getInstructionTypes().contains(record.getType())) {
                InstructionType instructionType = RuntimeVariables.getInstructionTypeObjectMap().get(record.getType());
                //check id there is a virtual element id 
                if (instructionType != null && instructionType.getVirtualElementId() != null) {
                    record.setElementId(instructionType.getVirtualElementId());
                }
            }
        }
        return record;
    }

}
