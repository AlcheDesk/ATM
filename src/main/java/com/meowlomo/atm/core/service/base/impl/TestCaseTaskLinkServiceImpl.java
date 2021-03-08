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
import com.meowlomo.atm.core.mapper.TestCaseTaskLinkMapper;
import com.meowlomo.atm.core.model.TestCaseTaskLink;
import com.meowlomo.atm.core.model.TestCaseTaskLinkExample;
import com.meowlomo.atm.core.service.base.TestCaseTaskLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseTaskLinkServiceImpl implements TestCaseTaskLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseTaskLink> redisService;
    @Autowired
    private TestCaseTaskLinkMapper testCaseTaskLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseTaskLinkExample example) {
        return testCaseTaskLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseTaskLinkExample example) {
        int deleteResult = testCaseTaskLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseTaskLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseTaskLink record) {
        int insertResult = testCaseTaskLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseTaskLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseTaskLink record = records.get(i);
            if (testCaseTaskLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseTaskLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseTaskLink record = records.get(i);
            if (testCaseTaskLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseTaskLink record) {
        int insertResult = testCaseTaskLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return insertResult;
    }

    @Override
    public List<TestCaseTaskLink> selectByExample(TestCaseTaskLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseTaskLink.class, example);
        List<TestCaseTaskLink> selectResult = new ArrayList<TestCaseTaskLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseTaskLink>>() {
                    });
        }
        else {
            selectResult = testCaseTaskLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseTaskLink> selectByExampleWithRowbounds(TestCaseTaskLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseTaskLink.class, example, rowBounds);
        List<TestCaseTaskLink> selectResult = new ArrayList<TestCaseTaskLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseTaskLink>>() {
                    });
        }
        else {
            selectResult = testCaseTaskLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseTaskLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseTaskLink.class, id);
        TestCaseTaskLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseTaskLink>() {
                    });
        }
        else {
            selectResult = testCaseTaskLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseTaskLink selectOneByExample(TestCaseTaskLinkExample example) {
        TestCaseTaskLink selectResult = testCaseTaskLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseTaskLink record, TestCaseTaskLinkExample example) {
        int updateResult = testCaseTaskLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseTaskLink record, TestCaseTaskLinkExample example) {
        int updateResult = testCaseTaskLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseTaskLink record) {
        int updateResult = testCaseTaskLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseTaskLink record) {
        int updateResult = testCaseTaskLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseTaskLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTaskLink.class));
        }
        return updateResult;
    }
}
