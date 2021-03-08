package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.RunSetMapper;
import com.meowlomo.atm.core.mapper.RunSetReferenceMapper;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;
import com.meowlomo.atm.core.service.referrence.RunSetReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetReferenceServiceImpl implements RunSetReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSet> redisService;
    @Autowired
    private RunSetMapper runSetMapper;
    @Autowired
    private RunSetReferenceMapper runSetReferenceMapper;

    @Override
    public long countByExample(RunSetExample example) {
        return runSetMapper.countByExample(example);
    }

    @Override
    public List<RunSet> selectByExample(RunSetExample example) {
        List<RunSet> selectResult = runSetReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunSet.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<RunSet> selectByExampleWithRowbounds(RunSetExample example, RowBounds rowBounds) {
        List<RunSet> selectResult = runSetReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunSet.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public RunSet selectByPrimaryKey(Long id) {
        RunSet selectResult = runSetReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSet.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
