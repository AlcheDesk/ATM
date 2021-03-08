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
import com.meowlomo.atm.core.mapper.TestCaseShareFolderMapper;
import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;
import com.meowlomo.atm.core.service.base.TestCaseShareFolderService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseShareFolderServiceImpl implements TestCaseShareFolderService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseShareFolder> redisService;
    @Autowired
    private TestCaseShareFolderMapper testCaseShareFolderMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TestCaseShareFolderExample example) {
        return testCaseShareFolderMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TestCaseShareFolderExample example) {
        int deleteResult = testCaseShareFolderMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = testCaseShareFolderMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(TestCaseShareFolder record) {
        int insertResult = testCaseShareFolderMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<TestCaseShareFolder> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseShareFolder record = records.get(i);
            if (testCaseShareFolderMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<TestCaseShareFolder> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            TestCaseShareFolder record = records.get(i);
            if (testCaseShareFolderMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(TestCaseShareFolder record) {
        int insertResult = testCaseShareFolderMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(TestCaseShareFolderExample example) {
        int deleteResult = testCaseShareFolderMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = testCaseShareFolderMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return deleteResult;
    }

    @Override
    public List<TestCaseShareFolder> selectByExample(TestCaseShareFolderExample example) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseShareFolder.class, example);
        List<TestCaseShareFolder> selectResult = new ArrayList<TestCaseShareFolder>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseShareFolder>>() {
                    });
        }
        else {
            selectResult = testCaseShareFolderMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<TestCaseShareFolder> selectByExampleWithRowbounds(TestCaseShareFolderExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseShareFolder.class, example, rowBounds);
        List<TestCaseShareFolder> selectResult = new ArrayList<TestCaseShareFolder>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<TestCaseShareFolder>>() {
                    });
        }
        else {
            selectResult = testCaseShareFolderMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseShareFolder selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(TestCaseShareFolder.class, id);
        TestCaseShareFolder selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<TestCaseShareFolder>() {
                    });
        }
        else {
            selectResult = testCaseShareFolderMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public TestCaseShareFolder selectOneByExample(TestCaseShareFolderExample example) {
        TestCaseShareFolder selectResult = testCaseShareFolderMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(TestCaseShareFolder record, TestCaseShareFolderExample example) {
        int updateResult = testCaseShareFolderMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(TestCaseShareFolder record, TestCaseShareFolderExample example) {
        int updateResult = testCaseShareFolderMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(TestCaseShareFolder record) {
        int updateResult = testCaseShareFolderMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(TestCaseShareFolder record) {
        int updateResult = testCaseShareFolderMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(TestCaseShareFolder.class));
        }
        return updateResult;
    }
}
