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
import com.meowlomo.atm.core.mapper.TestCaseDriverTypeMapMapper;
import com.meowlomo.atm.core.model.TestCaseDriverTypeMap;
import com.meowlomo.atm.core.model.TestCaseDriverTypeMapExample;
import com.meowlomo.atm.core.service.base.TestCaseDriverTypeMapService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseDriverTypeMapServiceImpl implements TestCaseDriverTypeMapService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseDriverTypeMap> redisService;
    @Autowired
    private TestCaseDriverTypeMapMapper testCaseDriverTypeMapMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseDriverTypeMapExample example) {
        return testCaseDriverTypeMapMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseDriverTypeMapExample example) {
        int deleteResult = testCaseDriverTypeMapMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseDriverTypeMapMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseDriverTypeMap record) {
        int insertResult = testCaseDriverTypeMapMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseDriverTypeMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseDriverTypeMap record = records.get(i);
            if (testCaseDriverTypeMapMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseDriverTypeMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseDriverTypeMap record = records.get(i);
            if (testCaseDriverTypeMapMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseDriverTypeMap record) {
        int insertResult = testCaseDriverTypeMapMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<TestCaseDriverTypeMap> selectByExample(TestCaseDriverTypeMapExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, example);
        List<TestCaseDriverTypeMap> selectResult = new ArrayList<TestCaseDriverTypeMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseDriverTypeMap>>() {
                    });
        }
        else {
            selectResult = testCaseDriverTypeMapMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseDriverTypeMap> selectByExampleWithRowbounds(TestCaseDriverTypeMapExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, example, rowBounds);
        List<TestCaseDriverTypeMap> selectResult = new ArrayList<TestCaseDriverTypeMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseDriverTypeMap>>() {
                    });
        }
        else {
            selectResult = testCaseDriverTypeMapMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseDriverTypeMap selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, id);
        TestCaseDriverTypeMap selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseDriverTypeMap>() {
                    });
        }
        else {
            selectResult = testCaseDriverTypeMapMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseDriverTypeMap selectOneByExample(TestCaseDriverTypeMapExample example) {
        TestCaseDriverTypeMap selectResult = testCaseDriverTypeMapMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseDriverTypeMap record, TestCaseDriverTypeMapExample example) {
        int updateResult = testCaseDriverTypeMapMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseDriverTypeMap record, TestCaseDriverTypeMapExample example) {
        int updateResult = testCaseDriverTypeMapMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseDriverTypeMap record) {
        int updateResult = testCaseDriverTypeMapMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseDriverTypeMap record) {
        int updateResult = testCaseDriverTypeMapMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseDriverTypeMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverTypeMap.class));
        }
        return updateResult;
    }
}
