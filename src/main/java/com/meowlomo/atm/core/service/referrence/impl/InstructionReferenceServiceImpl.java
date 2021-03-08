package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.InstructionMapper;
import com.meowlomo.atm.core.mapper.InstructionReferenceMapper;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.service.referrence.InstructionReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionReferenceServiceImpl implements InstructionReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionMapper instructionMapper;
    @Autowired
    private InstructionReferenceMapper instructionReferenceMapper;
    @Autowired
    private RedisService<Instruction> redisService;

    @Override
    public long countByExample(InstructionExample example) {
        return instructionMapper.countByExample(example);
    }

    @Override
    public List<Instruction> selectByExample(InstructionExample example) {
        List<Instruction> selectResult = instructionReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Instruction.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Instruction> selectByExampleWithRowbounds(InstructionExample example, RowBounds rowBounds) {
        List<Instruction> selectResult = instructionReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Instruction.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Instruction selectByPrimaryKey(Long id) {
        Instruction selectResult = instructionReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Instruction.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
