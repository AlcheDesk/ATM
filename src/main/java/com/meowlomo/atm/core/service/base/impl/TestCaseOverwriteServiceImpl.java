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
import com.meowlomo.atm.core.mapper.TestCaseOverwriteMapper;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;
import com.meowlomo.atm.core.service.base.TestCaseOverwriteService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseOverwriteServiceImpl implements TestCaseOverwriteService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseOverwrite> redisService;
    @Autowired
    private TestCaseOverwriteMapper testCaseOverwriteMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseOverwriteExample example) {
        return testCaseOverwriteMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseOverwriteExample example) {
        int deleteResult = testCaseOverwriteMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseOverwriteMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseOverwrite record) {
        int insertResult = testCaseOverwriteMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseOverwrite> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseOverwrite record = records.get(i);
            if (testCaseOverwriteMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseOverwrite> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseOverwrite record = records.get(i);
            if (testCaseOverwriteMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseOverwrite record) {
        int insertResult = testCaseOverwriteMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(TestCaseOverwriteExample example) {
        int deleteResult = testCaseOverwriteMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = testCaseOverwriteMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return deleteResult;
    }

    @Override
    public List<TestCaseOverwrite> selectByExample(TestCaseOverwriteExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseOverwrite.class, example);
        List<TestCaseOverwrite> selectResult = new ArrayList<TestCaseOverwrite>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseOverwrite>>() {
                    });
        }
        else {
            selectResult = testCaseOverwriteMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseOverwrite> selectByExampleWithRowbounds(TestCaseOverwriteExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseOverwrite.class, example, rowBounds);
        List<TestCaseOverwrite> selectResult = new ArrayList<TestCaseOverwrite>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseOverwrite>>() {
                    });
        }
        else {
            selectResult = testCaseOverwriteMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseOverwrite selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseOverwrite.class, id);
        TestCaseOverwrite selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseOverwrite>() {
                    });
        }
        else {
            selectResult = testCaseOverwriteMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseOverwrite selectOneByExample(TestCaseOverwriteExample example) {
        TestCaseOverwrite selectResult = testCaseOverwriteMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseOverwrite record, TestCaseOverwriteExample example) {
        int updateResult = testCaseOverwriteMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseOverwrite record, TestCaseOverwriteExample example) {
        int updateResult = testCaseOverwriteMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseOverwrite record) {
        int updateResult = testCaseOverwriteMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseOverwrite record) {
        int updateResult = testCaseOverwriteMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOverwrite.class));
        }
        return updateResult;
    }
}
