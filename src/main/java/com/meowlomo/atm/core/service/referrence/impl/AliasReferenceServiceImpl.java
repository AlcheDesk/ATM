package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.AliasMapper;
import com.meowlomo.atm.core.mapper.AliasReferenceMapper;
import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.AliasExample;
import com.meowlomo.atm.core.service.referrence.AliasReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AliasReferenceServiceImpl implements AliasReferenceService {

    @Autowired
    private AliasMapper aliasMapper;
    @Autowired
    private AliasReferenceMapper aliasReferenceMapper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Alias> redisService;

    @Override
    public long countByExample(AliasExample example) {
        return aliasMapper.countByExample(example);
    }

    @Override
    public List<Alias> selectByExample(AliasExample example) {
        List<Alias> selectResult = aliasReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Alias.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Alias> selectByExampleWithRowbounds(AliasExample example, RowBounds rowBounds) {
        List<Alias> selectResult = aliasReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Alias.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Alias selectByPrimaryKey(Long id) {
        Alias selectResult = aliasReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Alias.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
