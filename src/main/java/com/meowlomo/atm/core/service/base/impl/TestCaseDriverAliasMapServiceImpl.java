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
import com.meowlomo.atm.core.mapper.TestCaseDriverAliasMapMapper;
import com.meowlomo.atm.core.model.TestCaseDriverAliasMap;
import com.meowlomo.atm.core.model.TestCaseDriverAliasMapExample;
import com.meowlomo.atm.core.service.base.TestCaseDriverAliasMapService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseDriverAliasMapServiceImpl implements TestCaseDriverAliasMapService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseDriverAliasMap> redisService;
    @Autowired
    private TestCaseDriverAliasMapMapper testCaseDriverAliasMapMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseDriverAliasMapExample example) {
        return testCaseDriverAliasMapMapper.countByExample(example);
    }

    @Override
    public List<TestCaseDriverAliasMap> selectByExample(TestCaseDriverAliasMapExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseDriverAliasMap.class, example);
        List<TestCaseDriverAliasMap> selectResult = new ArrayList<TestCaseDriverAliasMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseDriverAliasMap>>() {
                    });
        }
        else {
            selectResult = testCaseDriverAliasMapMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseDriverAliasMap> selectByExampleWithRowbounds(TestCaseDriverAliasMapExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseDriverAliasMap.class, example, rowBounds);
        List<TestCaseDriverAliasMap> selectResult = new ArrayList<TestCaseDriverAliasMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseDriverAliasMap>>() {
                    });
        }
        else {
            selectResult = testCaseDriverAliasMapMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseDriverAliasMap selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseDriverAliasMap.class, id);
        TestCaseDriverAliasMap selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseDriverAliasMap>() {
                    });
        }
        else {
            selectResult = testCaseDriverAliasMapMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseDriverAliasMap selectOneByExample(TestCaseDriverAliasMapExample example) {
        TestCaseDriverAliasMap selectResult = testCaseDriverAliasMapMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseDriverAliasMap.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }
}
