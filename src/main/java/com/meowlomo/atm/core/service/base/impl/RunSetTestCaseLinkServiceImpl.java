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
import com.meowlomo.atm.core.mapper.RunSetTestCaseLinkMapper;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.service.base.RunSetService;
import com.meowlomo.atm.core.service.base.RunSetTestCaseLinkService;
import com.meowlomo.atm.core.service.base.TestCaseService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetTestCaseLinkServiceImpl implements RunSetTestCaseLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetTestCaseLink> redisService;
    @Autowired
    private RunSetService runSetService;
    @Autowired
    private RunSetTestCaseLinkMapper runSetTestCaseLinkMapper;
    @Autowired
    private TestCaseService testCaseService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetTestCaseLinkExample example) {
        return runSetTestCaseLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetTestCaseLinkExample example) {
        int deleteResult = runSetTestCaseLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetTestCaseLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetTestCaseLink record) {
        int insertResult = runSetTestCaseLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetTestCaseLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetTestCaseLink record = records.get(i);
            if (runSetTestCaseLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetTestCaseLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetTestCaseLink record = records.get(i);
            if (runSetTestCaseLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetTestCaseLink record) {
        int insertResult = runSetTestCaseLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSet> listRunSetByTestCasePrimaryKey(Long testCaseId) {
        RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
        linkExample.createCriteria().andTestCaseIdEqualTo(testCaseId);
        List<RunSetTestCaseLink> targetLinks = runSetTestCaseLinkMapper.selectByExample(linkExample);
        List<Long> targetIds = new ArrayList<Long>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getRunSetId());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<RunSet>();
        }
        else {
            RunSetExample targetExample = new RunSetExample();
            targetExample.createCriteria().andIdIn(targetIds);
            return runSetService.selectByExample(targetExample);
        }
    }

    @Override
    public List<TestCase> listTestCaseByRunSetPrimaryKey(Long runSetId) {
        RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
        linkExample.createCriteria().andRunSetIdEqualTo(runSetId);
        List<RunSetTestCaseLink> targetLinks = runSetTestCaseLinkMapper.selectByExample(linkExample);
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
    public List<RunSetTestCaseLink> selectByExample(RunSetTestCaseLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, example);
        List<RunSetTestCaseLink> selectResult = new ArrayList<RunSetTestCaseLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetTestCaseLink>>() {
                    });
        }
        else {
            selectResult = runSetTestCaseLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetTestCaseLink> selectByExampleWithRowbounds(RunSetTestCaseLinkExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, example, rowBounds);
        List<RunSetTestCaseLink> selectResult = new ArrayList<RunSetTestCaseLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetTestCaseLink>>() {
                    });
        }
        else {
            selectResult = runSetTestCaseLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetTestCaseLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, id);
        RunSetTestCaseLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunSetTestCaseLink>() {
                    });
        }
        else {
            selectResult = runSetTestCaseLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetTestCaseLink selectOneByExample(RunSetTestCaseLinkExample example) {
        RunSetTestCaseLink selectResult = runSetTestCaseLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetTestCaseLink record, RunSetTestCaseLinkExample example) {
        int updateResult = runSetTestCaseLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetTestCaseLink record, RunSetTestCaseLinkExample example) {
        int updateResult = runSetTestCaseLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetTestCaseLink record) {
        int updateResult = runSetTestCaseLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetTestCaseLink record) {
        int updateResult = runSetTestCaseLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetTestCaseLink.class));
        }
        return updateResult;
    }
}
