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
import com.meowlomo.atm.core.mapper.TestCaseOptionEntryMapper;
import com.meowlomo.atm.core.model.TestCaseOptionEntry;
import com.meowlomo.atm.core.model.TestCaseOptionEntryExample;
import com.meowlomo.atm.core.service.base.TestCaseOptionEntryService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseOptionEntryServiceImpl implements TestCaseOptionEntryService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseOptionEntry> redisService;
    @Autowired
    private TestCaseOptionEntryMapper testCaseOptionEntryMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseOptionEntryExample example) {
        return testCaseOptionEntryMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseOptionEntryExample example) {
        int deleteResult = testCaseOptionEntryMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseOptionEntryMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseOptionEntry record) {
        int insertResult = testCaseOptionEntryMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseOptionEntry> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseOptionEntry record = records.get(i);
            if (testCaseOptionEntryMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseOptionEntry> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseOptionEntry record = records.get(i);
            if (testCaseOptionEntryMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseOptionEntry record) {
        int insertResult = testCaseOptionEntryMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return insertResult;
    }

    @Override
    public List<TestCaseOptionEntry> selectByExample(TestCaseOptionEntryExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, example);
        List<TestCaseOptionEntry> selectResult = new ArrayList<TestCaseOptionEntry>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseOptionEntry>>() {
                    });
        }
        else {
            selectResult = testCaseOptionEntryMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseOptionEntry> selectByExampleWithRowbounds(TestCaseOptionEntryExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, example, rowBounds);
        List<TestCaseOptionEntry> selectResult = new ArrayList<TestCaseOptionEntry>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseOptionEntry>>() {
                    });
        }
        else {
            selectResult = testCaseOptionEntryMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseOptionEntry selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, id);
        TestCaseOptionEntry selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseOptionEntry>() {
                    });
        }
        else {
            selectResult = testCaseOptionEntryMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseOptionEntry selectOneByExample(TestCaseOptionEntryExample example) {
        TestCaseOptionEntry selectResult = testCaseOptionEntryMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseOptionEntry record, TestCaseOptionEntryExample example) {
        int updateResult = testCaseOptionEntryMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseOptionEntry record, TestCaseOptionEntryExample example) {
        int updateResult = testCaseOptionEntryMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseOptionEntry record) {
        int updateResult = testCaseOptionEntryMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseOptionEntry record) {
        int updateResult = testCaseOptionEntryMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseOptionEntry.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseOptionEntry.class));
        }
        return updateResult;
    }
}
