package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.DevInstructionResultMapper;
import com.meowlomo.atm.core.mapper.DevInstructionResultReferenceMapper;
import com.meowlomo.atm.core.mapper.ProdInstructionResultMapper;
import com.meowlomo.atm.core.mapper.ProdInstructionResultReferenceMapper;
import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;
import com.meowlomo.atm.core.service.referrence.InstructionResultReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionResultReferenceServiceImpl implements InstructionResultReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DevInstructionResultMapper devInstructionResultMapper;
    @Autowired
    private DevInstructionResultReferenceMapper devInstructionResultReferenceMapper;
    @Autowired
    private ProdInstructionResultMapper prodInstructionResultMapper;
    @Autowired
    private ProdInstructionResultReferenceMapper prodInstructionResultReferenceMapper;
    @Autowired
    private RedisService<InstructionResult> redisService;

    @Override
    public long countByExample(InstructionResultExample example, String mode) {
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            return devInstructionResultMapper.countByExample(example);
        }
        else {
            return prodInstructionResultMapper.countByExample(example);
        }
    }

    @Override
    public List<InstructionResult> selectByExample(InstructionResultExample example, String mode) {
        List<InstructionResult> selectResult = null;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            selectResult = devInstructionResultReferenceMapper.selectByExample(example);
        }
        else {
            selectResult = prodInstructionResultReferenceMapper.selectByExample(example);
        }
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(InstructionResult.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<InstructionResult> selectByExampleWithRowbounds(InstructionResultExample example, RowBounds rowBounds,
            String mode) {
        List<InstructionResult> selectResult = null;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            selectResult = devInstructionResultReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        }
        else {
            selectResult = prodInstructionResultReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        }
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(InstructionResult.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public InstructionResult selectByPrimaryKey(Long id, String mode) {
        InstructionResult selectResult = null;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            selectResult = devInstructionResultReferenceMapper.selectByPrimaryKey(id);
        }
        else {
            selectResult = prodInstructionResultReferenceMapper.selectByPrimaryKey(id);
        }
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionResult.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }

}
