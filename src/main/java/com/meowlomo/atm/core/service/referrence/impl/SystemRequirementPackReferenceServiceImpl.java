package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.SystemRequirementPackMapper;
import com.meowlomo.atm.core.mapper.SystemRequirementPackReferenceMapper;
import com.meowlomo.atm.core.model.SystemRequirementPack;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;
import com.meowlomo.atm.core.service.referrence.SystemRequirementPackReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemRequirementPackReferenceServiceImpl implements SystemRequirementPackReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<SystemRequirementPack> redisService;
    @Autowired
    private SystemRequirementPackMapper systemRequirementPackMapper;
    @Autowired
    private SystemRequirementPackReferenceMapper systemRequirementPackReferenceMapper;

    @Override
    public long countByExample(SystemRequirementPackExample example) {
        return systemRequirementPackMapper.countByExample(example);
    }

    @Override
    public List<SystemRequirementPack> selectByExample(SystemRequirementPackExample example) {
        List<SystemRequirementPack> selectResult = systemRequirementPackReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(SystemRequirementPack.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<SystemRequirementPack> selectByExampleWithRowbounds(SystemRequirementPackExample example,
            RowBounds rowBounds) {
        List<SystemRequirementPack> selectResult = systemRequirementPackReferenceMapper
                .selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(SystemRequirementPack.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public SystemRequirementPack selectByPrimaryKey(Long id) {
        SystemRequirementPack selectResult = systemRequirementPackReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementPack.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }

}
