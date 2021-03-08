package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.ModuleMapper;
import com.meowlomo.atm.core.mapper.ModuleReferenceMapper;
import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;
import com.meowlomo.atm.core.service.referrence.ModuleReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ModuleReferenceServiceImpl implements ModuleReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private ModuleReferenceMapper moduleReferenceMapper;
    @Autowired
    private RedisService<Module> redisService;

    @Override
    public long countByExample(ModuleExample example) {
        return moduleMapper.countByExample(example);
    }

    @Override
    public List<Module> selectByExample(ModuleExample example) {
        List<Module> selectResult = moduleReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Module.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Module> selectByExampleWithRowbounds(ModuleExample example, RowBounds rowBounds) {
        List<Module> selectResult = moduleReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Module.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Module selectByPrimaryKey(Long id) {
        Module selectResult = moduleReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Module.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
