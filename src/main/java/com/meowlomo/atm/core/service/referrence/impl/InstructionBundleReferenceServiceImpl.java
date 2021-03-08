package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.InstructionBundleMapper;
import com.meowlomo.atm.core.mapper.InstructionBundleReferenceMapper;
import com.meowlomo.atm.core.model.InstructionBundle;
import com.meowlomo.atm.core.model.InstructionBundleExample;
import com.meowlomo.atm.core.service.referrence.InstructionBundleReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionBundleReferenceServiceImpl implements InstructionBundleReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionBundleMapper instructionBundleMapper;
    @Autowired
    private InstructionBundleReferenceMapper instructionBundleReferenceMapper;
    @Autowired
    private RedisService<InstructionBundle> redisService;

    @Override
    public long countByExample(InstructionBundleExample example) {
        return instructionBundleMapper.countByExample(example);
    }

    @Override
    public List<InstructionBundle> selectByExample(InstructionBundleExample example) {
        List<InstructionBundle> selectResult = instructionBundleReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(InstructionBundle.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<InstructionBundle> selectByExampleWithRowbounds(InstructionBundleExample example, RowBounds rowBounds) {
        List<InstructionBundle> selectResult = instructionBundleReferenceMapper.selectByExampleWithRowbounds(example,
                rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(InstructionBundle.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public InstructionBundle selectByPrimaryKey(Long id) {
        InstructionBundle selectResult = instructionBundleReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundle.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }

}
