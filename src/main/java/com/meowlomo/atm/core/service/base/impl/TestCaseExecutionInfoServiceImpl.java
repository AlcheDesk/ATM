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
import com.meowlomo.atm.core.mapper.TestCaseExecutionInfoMapper;
import com.meowlomo.atm.core.model.TestCaseExecutionInfo;
import com.meowlomo.atm.core.model.TestCaseExecutionInfoExample;
import com.meowlomo.atm.core.service.base.TestCaseExecutionInfoService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseExecutionInfoServiceImpl implements TestCaseExecutionInfoService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseExecutionInfo> redisService;
    @Autowired
    private TestCaseExecutionInfoMapper testCaseExecutionInfoMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseExecutionInfoExample example) {
        return testCaseExecutionInfoMapper.countByExample(example);
    }

    @Override
    public List<TestCaseExecutionInfo> selectByExample(TestCaseExecutionInfoExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseExecutionInfo.class, example);
        List<TestCaseExecutionInfo> selectResult = new ArrayList<TestCaseExecutionInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseExecutionInfo>>() {
                    });
        }
        else {
            selectResult = testCaseExecutionInfoMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseExecutionInfo> selectByExampleWithRowbounds(TestCaseExecutionInfoExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseExecutionInfo.class, example, rowBounds);
        List<TestCaseExecutionInfo> selectResult = new ArrayList<TestCaseExecutionInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseExecutionInfo>>() {
                    });
        }
        else {
            selectResult = testCaseExecutionInfoMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseExecutionInfo selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseExecutionInfo.class, id);
        TestCaseExecutionInfo selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseExecutionInfo>() {
                    });
        }
        else {
            selectResult = testCaseExecutionInfoMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseExecutionInfo selectOneByExample(TestCaseExecutionInfoExample example) {
        TestCaseExecutionInfo selectResult = testCaseExecutionInfoMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(TestCaseExecutionInfo.class, selectResult.getTestCaseId()),
                    selectResult);
            redisService.setExpire(
                    cacheKeyGenerator.generateKey(TestCaseExecutionInfo.class, selectResult.getTestCaseId()),
                    expireTimeInSeconds, TimeUnit.SECONDS);
        }
        return selectResult;
    }
}
