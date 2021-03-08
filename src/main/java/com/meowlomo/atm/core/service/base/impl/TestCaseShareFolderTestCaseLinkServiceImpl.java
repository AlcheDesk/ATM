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
import com.meowlomo.atm.core.mapper.TestCaseShareFolderTestCaseLinkMapper;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLinkExample;
import com.meowlomo.atm.core.service.base.TestCaseService;
import com.meowlomo.atm.core.service.base.TestCaseShareFolderService;
import com.meowlomo.atm.core.service.base.TestCaseShareFolderTestCaseLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseShareFolderTestCaseLinkServiceImpl implements TestCaseShareFolderTestCaseLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseShareFolderTestCaseLink> redisService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private TestCaseShareFolderService testCaseShareFolderService;
    @Autowired
    private TestCaseShareFolderTestCaseLinkMapper testCaseShareFolderTestCaseLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseShareFolderTestCaseLinkExample example) {
        return testCaseShareFolderTestCaseLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseShareFolderTestCaseLinkExample example) {
        int deleteResult = testCaseShareFolderTestCaseLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseShareFolderTestCaseLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseShareFolderTestCaseLink record) {
        int insertResult = testCaseShareFolderTestCaseLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseShareFolderTestCaseLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseShareFolderTestCaseLink record = records.get(i);
            if (testCaseShareFolderTestCaseLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseShareFolderTestCaseLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseShareFolderTestCaseLink record = records.get(i);
            if (testCaseShareFolderTestCaseLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseShareFolderTestCaseLink record) {
        int insertResult = testCaseShareFolderTestCaseLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return insertResult;
    }

    @Override
    public List<TestCase> listTestCaseByTestCaseShareFolderPrimaryKey(Long testCaseShareFolderId) {
        TestCaseShareFolderTestCaseLinkExample linkExample = new TestCaseShareFolderTestCaseLinkExample();
        linkExample.createCriteria().andTestCaseShareFolderIdEqualTo(testCaseShareFolderId);
        List<TestCaseShareFolderTestCaseLink> targetLinks = testCaseShareFolderTestCaseLinkMapper
                .selectByExample(linkExample);
        List<Long> targetIds = new ArrayList<Long>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getTestCaseId());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<TestCase>();
        }
        else {
            TestCaseExample targetExample = new TestCaseExample();
            targetExample.createCriteria().andIdIn(targetIds);
            return testCaseService.selectByExample(targetExample);
        }
    }

    @Override
    public List<TestCaseShareFolder> listTestCaseShareFolderByTestCasePrimaryKey(Long testCaseId) {
        TestCaseShareFolderTestCaseLinkExample linkExample = new TestCaseShareFolderTestCaseLinkExample();
        linkExample.createCriteria().andTestCaseIdEqualTo(testCaseId);
        List<TestCaseShareFolderTestCaseLink> targetLinks = testCaseShareFolderTestCaseLinkMapper
                .selectByExample(linkExample);
        List<Long> targetIds = new ArrayList<Long>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getTestCaseShareFolderId());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<TestCaseShareFolder>();
        }
        else {
            TestCaseShareFolderExample targetExample = new TestCaseShareFolderExample();
            targetExample.createCriteria().andIdIn(targetIds);
            return testCaseShareFolderService.selectByExample(targetExample);
        }
    }

    @Override
    public List<TestCaseShareFolderTestCaseLink> selectByExample(TestCaseShareFolderTestCaseLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, example);
        List<TestCaseShareFolderTestCaseLink> selectResult = new ArrayList<TestCaseShareFolderTestCaseLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseShareFolderTestCaseLink>>() {
                    });
        }
        else {
            selectResult = testCaseShareFolderTestCaseLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseShareFolderTestCaseLink> selectByExampleWithRowbounds(
            TestCaseShareFolderTestCaseLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, example, rowBounds);
        List<TestCaseShareFolderTestCaseLink> selectResult = new ArrayList<TestCaseShareFolderTestCaseLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseShareFolderTestCaseLink>>() {
                    });
        }
        else {
            selectResult = testCaseShareFolderTestCaseLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseShareFolderTestCaseLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, id);
        TestCaseShareFolderTestCaseLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseShareFolderTestCaseLink>() {
                    });
        }
        else {
            selectResult = testCaseShareFolderTestCaseLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseShareFolderTestCaseLink selectOneByExample(TestCaseShareFolderTestCaseLinkExample example) {
        TestCaseShareFolderTestCaseLink selectResult = testCaseShareFolderTestCaseLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseShareFolderTestCaseLink record, TestCaseShareFolderTestCaseLinkExample example) {
        int updateResult = testCaseShareFolderTestCaseLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseShareFolderTestCaseLink record,
            TestCaseShareFolderTestCaseLinkExample example) {
        int updateResult = testCaseShareFolderTestCaseLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseShareFolderTestCaseLink record) {
        int updateResult = testCaseShareFolderTestCaseLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseShareFolderTestCaseLink record) {
        int updateResult = testCaseShareFolderTestCaseLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolderTestCaseLink.class));
        }
        return updateResult;
    }
}
