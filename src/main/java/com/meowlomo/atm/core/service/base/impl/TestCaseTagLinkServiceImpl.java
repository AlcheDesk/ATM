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
import com.meowlomo.atm.core.mapper.TestCaseTagLinkMapper;
import com.meowlomo.atm.core.model.TestCaseTagLink;
import com.meowlomo.atm.core.model.TestCaseTagLinkExample;
import com.meowlomo.atm.core.service.base.TestCaseTagLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseTagLinkServiceImpl implements TestCaseTagLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseTagLink> redisService;
    @Autowired
    private TestCaseTagLinkMapper testCaseTagLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseTagLinkExample example) {
        return testCaseTagLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseTagLinkExample example) {
        int deleteResult = testCaseTagLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseTagLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseTagLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseTagLink record) {
        int insertResult = testCaseTagLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTagLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseTagLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseTagLink record = records.get(i);
            if (testCaseTagLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTagLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseTagLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseTagLink record = records.get(i);
            if (testCaseTagLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTagLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseTagLink record) {
        int insertResult = testCaseTagLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTagLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return insertResult;
    }

    @Override
    public List<TestCaseTagLink> selectByExample(TestCaseTagLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseTagLink.class, example);
        List<TestCaseTagLink> selectResult = new ArrayList<TestCaseTagLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseTagLink>>() {
                    });
        }
        else {
            selectResult = testCaseTagLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseTagLink> selectByExampleWithRowbounds(TestCaseTagLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseTagLink.class, example, rowBounds);
        List<TestCaseTagLink> selectResult = new ArrayList<TestCaseTagLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseTagLink>>() {
                    });
        }
        else {
            selectResult = testCaseTagLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseTagLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseTagLink.class, id);
        TestCaseTagLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseTagLink>() {
                    });
        }
        else {
            selectResult = testCaseTagLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseTagLink selectOneByExample(TestCaseTagLinkExample example) {
        TestCaseTagLink selectResult = testCaseTagLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseTagLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseTagLink record, TestCaseTagLinkExample example) {
        int updateResult = testCaseTagLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseTagLink record, TestCaseTagLinkExample example) {
        int updateResult = testCaseTagLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseTagLink record) {
        int updateResult = testCaseTagLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseTagLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseTagLink record) {
        int updateResult = testCaseTagLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseTagLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseTagLink.class));
        }
        return updateResult;
    }
}
