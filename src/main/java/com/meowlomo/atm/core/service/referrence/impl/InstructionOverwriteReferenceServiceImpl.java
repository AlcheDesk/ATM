package com.meowlomo.atm.core.service.referrence.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.InstructionOverwriteMapper;
import com.meowlomo.atm.core.mapper.InstructionOverwriteReferenceMapper;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;
import com.meowlomo.atm.core.service.referrence.InstructionOverwriteReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionOverwriteReferenceServiceImpl implements InstructionOverwriteReferenceService {

    @Autowired
    private InstructionOverwriteMapper instructionOverwriteMapper;
    @Autowired
    private InstructionOverwriteReferenceMapper instructionOverwriteReferenceMapper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<InstructionOverwrite> redisService;

    @Override
    public long countByExample(InstructionOverwriteExample example) {
        return instructionOverwriteMapper.countByExample(example);
    }

    @Override
    public List<InstructionOverwrite> selectByExample(InstructionOverwriteExample example) {
        List<InstructionOverwrite> instructionOverwrites = instructionOverwriteReferenceMapper.selectByExample(example);
        List<InstructionOverwrite> newInstructionOverwrites = new ArrayList<InstructionOverwrite>();
        for (InstructionOverwrite insOver : instructionOverwrites) {
            Instruction instruction = insOver.getInstruction();
            insOver.setInstruction(instruction);
            newInstructionOverwrites.add(insOver);
        }
        if (!newInstructionOverwrites.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(InstructionOverwrite.class, example, "reference"), newInstructionOverwrites);
        }
        return newInstructionOverwrites;
    }

    @Override
    public List<InstructionOverwrite> selectByExampleWithRowbounds(InstructionOverwriteExample example,
            RowBounds rowBounds) {
        List<InstructionOverwrite> instructionOverwrites = instructionOverwriteReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<InstructionOverwrite> newInstructionOverwrites = new ArrayList<InstructionOverwrite>();
        for (InstructionOverwrite insOver : instructionOverwrites) {
            Instruction instruction = insOver.getInstruction();
            insOver.setInstruction(instruction);
            // add element type id for instructionOverwrite start
            if (insOver.getElement() != null) {
                Element element = insOver.getElement();
                Long elementTypeId = RuntimeVariables.getElementTypeToIdMap().get(element.getType());
                element.setTypeId(elementTypeId);
                insOver.setElement(element);
            }
            // add element type id for instructionOverwrite end
            newInstructionOverwrites.add(insOver);
        }
        if (!newInstructionOverwrites.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(InstructionOverwrite.class, example, rowBounds, "reference"), newInstructionOverwrites);
        }
        return newInstructionOverwrites;
    }

    @Override
    public InstructionOverwrite selectByPrimaryKey(Long id) {
        InstructionOverwrite instructionOverwrite = instructionOverwriteReferenceMapper.selectByPrimaryKey(id);
        Instruction instruction = instructionOverwrite.getInstruction();
        if (instruction != null) {
            instructionOverwrite.setInstruction(instruction);
        }
        if (instructionOverwrite != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOverwrite.class, id, "reference"), instructionOverwrite);
        }
        return instructionOverwrite;
    }

}
