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
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseDriverAliasMap;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.model.TestCaseExecutionInfo;
import com.meowlomo.atm.core.model.TestCaseInstructionTypeMap;
import com.meowlomo.atm.core.service.base.TestCaseService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCase> redisService;
    @Autowired
    private TestCaseMapper testCaseMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseExample example) {
        return testCaseMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseExample example) {
        int deleteResult = testCaseMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCase.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCase record) {
        int insertResult = testCaseMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCase.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCase> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCase record = records.get(i);
            if (testCaseMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCase.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCase> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCase record = records.get(i);
            if (testCaseMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCase.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCase record) {
        int insertResult = testCaseMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCase.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(TestCaseExample example) {
        int deleteResult = testCaseMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = testCaseMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCase.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
        }
        return deleteResult;
    }

    @Override
    public List<TestCase> selectByExample(TestCaseExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCase.class, example);
        List<TestCase> selectResult = new ArrayList<TestCase>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCase>>() {
                    });
        }
        else {
            selectResult = testCaseMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCase> selectByExampleWithRowbounds(TestCaseExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCase.class, example, rowBounds);
        List<TestCase> selectResult = new ArrayList<TestCase>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCase>>() {
                    });
        }
        else {
            selectResult = testCaseMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCase selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCase.class, id);
        TestCase selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<TestCase>() {
            });
        }
        else {
            selectResult = testCaseMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCase selectOneByExample(TestCaseExample example) {
        TestCase selectResult = testCaseMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCase.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCase record, TestCaseExample example) {
        int updateResult = testCaseMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteList(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCase record, TestCaseExample example) {
        int updateResult = testCaseMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteList(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCase record) {
        int updateResult = testCaseMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCase.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteList(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCase record) {
        int updateResult = testCaseMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCase.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteList(cacheKeyGenerator.generateClassKey(TestCaseExecutionInfo.class));
        }
        return updateResult;
    }
}
