package com.meowlomo.atm.core.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.DriverPackDriverAliasMapMapper;
import com.meowlomo.atm.core.model.DriverPackDriverAliasMap;
import com.meowlomo.atm.core.model.DriverPackDriverAliasMapExample;
import com.meowlomo.atm.core.service.base.DriverPackDriverAliasMapService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPackDriverAliasMapServiceImpl implements DriverPackDriverAliasMapService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverPackDriverAliasMapMapper driverPackDriverAliasMapMapper;
    @Autowired
    private RedisService<DriverPackDriverAliasMap> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverPackDriverAliasMapExample example) {
        return driverPackDriverAliasMapMapper.countByExample(example);
    }

    @Override
    public List<DriverPackDriverAliasMap> selectByExample(DriverPackDriverAliasMapExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverAliasMap.class, example);
        List<DriverPackDriverAliasMap> selectResult = new ArrayList<DriverPackDriverAliasMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPackDriverAliasMap>>() {
                    });
        }
        else {
            selectResult = driverPackDriverAliasMapMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverPackDriverAliasMap> selectByExampleWithRowbounds(DriverPackDriverAliasMapExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverAliasMap.class, example, rowBounds);
        List<DriverPackDriverAliasMap> selectResult = new ArrayList<DriverPackDriverAliasMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPackDriverAliasMap>>() {
                    });
        }
        else {
            selectResult = driverPackDriverAliasMapMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPackDriverAliasMap selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverAliasMap.class, id);
        DriverPackDriverAliasMap selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<DriverPackDriverAliasMap>() {
                    });
        }
        else {
            selectResult = driverPackDriverAliasMapMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPackDriverAliasMap selectOneByExample(DriverPackDriverAliasMapExample example) {
        DriverPackDriverAliasMap selectResult = driverPackDriverAliasMapMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverAliasMap.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }
}
