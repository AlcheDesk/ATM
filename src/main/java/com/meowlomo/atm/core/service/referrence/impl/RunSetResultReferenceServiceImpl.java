package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.RunSetResultMapper;
import com.meowlomo.atm.core.mapper.RunSetResultReferenceMapper;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;
import com.meowlomo.atm.core.service.referrence.RunSetResultReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultReferenceServiceImpl implements RunSetResultReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetResult> redisService;
    @Autowired
    private RunSetResultMapper runSetResultMapper;
    @Autowired
    private RunSetResultReferenceMapper runSetResultReferenceMapper;

    @Override
    public long countByExample(RunSetResultExample example) {
        return runSetResultMapper.countByExample(example);
    }

    @Override
    public List<RunSetResult> selectByExample(RunSetResultExample example) {
        List<RunSetResult> selectResult = runSetResultReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunSetResult.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample example, RowBounds rowBounds) {
        List<RunSetResult> selectResult = runSetResultReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunSetResult.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public RunSetResult selectByPrimaryKey(Long id) {
        RunSetResult selectResult = runSetResultReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResult.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
