package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.RunMapper;
import com.meowlomo.atm.core.mapper.RunReferenceMapper;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.service.referrence.RunReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunReferenceServiceImpl implements RunReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Run> redisService;
    @Autowired
    private RunMapper runMapper;
    @Autowired
    private RunReferenceMapper runReferenceMapper;

    @Override
    public long countByExample(RunExample example) {
        return runMapper.countByExample(example);
    }

    @Override
    public List<Run> selectByExample(RunExample example) {
        List<Run> selectResult = runReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Run.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Run> selectByExampleWithRowbounds(RunExample example, RowBounds rowBounds) {
        List<Run> selectResult = runReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Run.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Run selectByPrimaryKey(Long id) {
        Run selectResult = runReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Run.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
