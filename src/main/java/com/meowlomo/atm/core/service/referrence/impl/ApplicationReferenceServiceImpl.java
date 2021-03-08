package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.ApplicationMapper;
import com.meowlomo.atm.core.mapper.ApplicationReferenceMapper;
import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.ApplicationExample;
import com.meowlomo.atm.core.service.referrence.ApplicationReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApplicationReferenceServiceImpl implements ApplicationReferenceService {

    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private ApplicationReferenceMapper applicationReferenceMapper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Application> redisService;

    @Override
    public long countByExample(ApplicationExample example) {
        return applicationMapper.countByExample(example);
    }

    @Override
    public List<Application> selectByExample(ApplicationExample example) {
        List<Application> selectResult = applicationReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Application.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Application> selectByExampleWithRowbounds(ApplicationExample example, RowBounds rowBounds) {
        List<Application> selectResult = applicationReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Application.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Application selectByPrimaryKey(Long id) {
        Application selectResult = applicationReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Application.class, id, "reference"), selectResult);
        }
        return selectResult;
    }
}
