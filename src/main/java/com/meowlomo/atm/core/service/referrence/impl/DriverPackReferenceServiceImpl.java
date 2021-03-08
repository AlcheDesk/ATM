package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.DriverPackMapper;
import com.meowlomo.atm.core.mapper.DriverPackReferenceMapper;
import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackExample;
import com.meowlomo.atm.core.service.referrence.DriverPackReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPackReferenceServiceImpl implements DriverPackReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverPackMapper driverPackMapper;
    @Autowired
    private DriverPackReferenceMapper driverPackReferenceMapper;
    @Autowired
    private RedisService<DriverPack> redisService;

    @Override
    public long countByExample(DriverPackExample example) {
        return driverPackMapper.countByExample(example);
    }

    @Override
    public List<DriverPack> selectByExample(DriverPackExample example) {
        List<DriverPack> selectResult = driverPackReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(DriverPack.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<DriverPack> selectByExampleWithRowbounds(DriverPackExample example, RowBounds rowBounds) {
        List<DriverPack> selectResult = driverPackReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(DriverPack.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public DriverPack selectByPrimaryKey(Long id) {
        DriverPack selectResult = driverPackReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPack.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
