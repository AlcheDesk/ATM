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
import com.meowlomo.atm.core.mapper.RunExecutionInfoMapper;
import com.meowlomo.atm.core.model.RunExecutionInfo;
import com.meowlomo.atm.core.model.RunExecutionInfoExample;
import com.meowlomo.atm.core.service.base.RunExecutionInfoService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunExecutionInfoServiceImpl implements RunExecutionInfoService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunExecutionInfo> redisService;
    @Autowired
    private RunExecutionInfoMapper runExecutionInfoMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunExecutionInfoExample example) {
        return runExecutionInfoMapper.countByExample(example);
    }

    @Override
    public List<RunExecutionInfo> selectByExample(RunExecutionInfoExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunExecutionInfo.class, example);
        List<RunExecutionInfo> selectResult = new ArrayList<RunExecutionInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunExecutionInfo>>() {
                    });
        }
        else {
            selectResult = runExecutionInfoMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunExecutionInfo> selectByExampleWithRowbounds(RunExecutionInfoExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunExecutionInfo.class, example, rowBounds);
        List<RunExecutionInfo> selectResult = new ArrayList<RunExecutionInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunExecutionInfo>>() {
                    });
        }
        else {
            selectResult = runExecutionInfoMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunExecutionInfo selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunExecutionInfo.class, id);
        RunExecutionInfo selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunExecutionInfo>() {
                    });
        }
        else {
            selectResult = runExecutionInfoMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunExecutionInfo selectOneByExample(RunExecutionInfoExample example) {
        RunExecutionInfo selectResult = runExecutionInfoMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunExecutionInfo.class, selectResult.getRunId()),
                    selectResult);
            redisService.setExpire(cacheKeyGenerator.generateKey(RunExecutionInfo.class, selectResult.getRunId()),
                    expireTimeInSeconds, TimeUnit.SECONDS);
        }
        return selectResult;
    }
}
