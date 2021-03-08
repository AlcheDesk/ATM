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
import com.meowlomo.atm.core.mapper.TestCaseModuleLinkMapper;
import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.model.TestCaseModuleLink;
import com.meowlomo.atm.core.model.TestCaseModuleLinkExample;
import com.meowlomo.atm.core.service.base.ModuleService;
import com.meowlomo.atm.core.service.base.TestCaseModuleLinkService;
import com.meowlomo.atm.core.service.base.TestCaseService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseModuleLinkServiceImpl implements TestCaseModuleLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RedisService<TestCaseModuleLink> redisService;
    @Autowired
    private TestCaseModuleLinkMapper testCaseModuleLinkMapper;
    @Autowired
    private TestCaseService testCaseService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseModuleLinkExample example) {
        return testCaseModuleLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseModuleLinkExample example) {
        int deleteResult = testCaseModuleLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseModuleLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseModuleLink record) {
        int insertResult = testCaseModuleLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseModuleLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseModuleLink record = records.get(i);
            if (testCaseModuleLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseModuleLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseModuleLink record = records.get(i);
            if (testCaseModuleLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseModuleLink record) {
        int insertResult = testCaseModuleLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Module> listModuleByTestCasePrimaryKey(Long testCaseId) {
        TestCaseModuleLinkExample linkExample = new TestCaseModuleLinkExample();
        linkExample.createCriteria().andTestCaseIdEqualTo(testCaseId);
        List<TestCaseModuleLink> targetLinks = testCaseModuleLinkMapper.selectByExample(linkExample);
        List<Long> targetIds = new ArrayList<Long>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getModuleId());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<Module>();
        }
        else {
            ModuleExample targetExample = new ModuleExample();
            targetExample.createCriteria().andIdIn(targetIds);
            return moduleService.selectByExample(targetExample);
        }
    }

    @Override
    public List<TestCase> listTestCaseByModulePrimaryKey(Long moduleId) {
        TestCaseModuleLinkExample linkExample = new TestCaseModuleLinkExample();
        linkExample.createCriteria().andModuleIdEqualTo(moduleId);
        List<TestCaseModuleLink> targetLinks = testCaseModuleLinkMapper.selectByExample(linkExample);
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
    public List<TestCaseModuleLink> selectByExample(TestCaseModuleLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseModuleLink.class, example);
        List<TestCaseModuleLink> selectResult = new ArrayList<TestCaseModuleLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseModuleLink>>() {
                    });
        }
        else {
            selectResult = testCaseModuleLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseModuleLink> selectByExampleWithRowbounds(TestCaseModuleLinkExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseModuleLink.class, example, rowBounds);
        List<TestCaseModuleLink> selectResult = new ArrayList<TestCaseModuleLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseModuleLink>>() {
                    });
        }
        else {
            selectResult = testCaseModuleLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseModuleLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseModuleLink.class, id);
        TestCaseModuleLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseModuleLink>() {
                    });
        }
        else {
            selectResult = testCaseModuleLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseModuleLink selectOneByExample(TestCaseModuleLinkExample example) {
        TestCaseModuleLink selectResult = testCaseModuleLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseModuleLink record, TestCaseModuleLinkExample example) {
        int updateResult = testCaseModuleLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseModuleLink record, TestCaseModuleLinkExample example) {
        int updateResult = testCaseModuleLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseModuleLink record) {
        int updateResult = testCaseModuleLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseModuleLink record) {
        int updateResult = testCaseModuleLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseModuleLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }
}
