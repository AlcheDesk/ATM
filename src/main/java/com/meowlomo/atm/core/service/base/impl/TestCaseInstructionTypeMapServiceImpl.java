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
import com.meowlomo.atm.core.mapper.TestCaseInstructionTypeMapMapper;
import com.meowlomo.atm.core.model.TestCaseInstructionTypeMap;
import com.meowlomo.atm.core.model.TestCaseInstructionTypeMapExample;
import com.meowlomo.atm.core.service.base.TestCaseInstructionTypeMapService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseInstructionTypeMapServiceImpl implements TestCaseInstructionTypeMapService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseInstructionTypeMap> redisService;
    @Autowired
    private TestCaseInstructionTypeMapMapper testCaseInstructionTypeMapMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseInstructionTypeMapExample example) {
        return testCaseInstructionTypeMapMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseInstructionTypeMapExample example) {
        int deleteResult = testCaseInstructionTypeMapMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseInstructionTypeMapMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseInstructionTypeMap record) {
        int insertResult = testCaseInstructionTypeMapMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseInstructionTypeMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseInstructionTypeMap record = records.get(i);
            if (testCaseInstructionTypeMapMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseInstructionTypeMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseInstructionTypeMap record = records.get(i);
            if (testCaseInstructionTypeMapMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseInstructionTypeMap record) {
        int insertResult = testCaseInstructionTypeMapMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<TestCaseInstructionTypeMap> selectByExample(TestCaseInstructionTypeMapExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, example);
        List<TestCaseInstructionTypeMap> selectResult = new ArrayList<TestCaseInstructionTypeMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseInstructionTypeMap>>() {
                    });
        }
        else {
            selectResult = testCaseInstructionTypeMapMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseInstructionTypeMap> selectByExampleWithRowbounds(TestCaseInstructionTypeMapExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, example, rowBounds);
        List<TestCaseInstructionTypeMap> selectResult = new ArrayList<TestCaseInstructionTypeMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseInstructionTypeMap>>() {
                    });
        }
        else {
            selectResult = testCaseInstructionTypeMapMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseInstructionTypeMap selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, id);
        TestCaseInstructionTypeMap selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseInstructionTypeMap>() {
                    });
        }
        else {
            selectResult = testCaseInstructionTypeMapMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseInstructionTypeMap selectOneByExample(TestCaseInstructionTypeMapExample example) {
        TestCaseInstructionTypeMap selectResult = testCaseInstructionTypeMapMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseInstructionTypeMap record, TestCaseInstructionTypeMapExample example) {
        int updateResult = testCaseInstructionTypeMapMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseInstructionTypeMap record, TestCaseInstructionTypeMapExample example) {
        int updateResult = testCaseInstructionTypeMapMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseInstructionTypeMap record) {
        int updateResult = testCaseInstructionTypeMapMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseInstructionTypeMap record) {
        int updateResult = testCaseInstructionTypeMapMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseInstructionTypeMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return updateResult;
    }
}
