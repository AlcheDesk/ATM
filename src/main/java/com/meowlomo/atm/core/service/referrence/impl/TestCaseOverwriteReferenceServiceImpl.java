package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.TestCaseOverwriteMapper;
import com.meowlomo.atm.core.mapper.TestCaseOverwriteReferenceMapper;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;
import com.meowlomo.atm.core.service.referrence.TestCaseOverwriteReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseOverwriteReferenceServiceImpl implements TestCaseOverwriteReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseOverwrite> redisService;
    @Autowired
    private TestCaseOverwriteMapper testCaseOverwriteMapper;
    @Autowired
    private TestCaseOverwriteReferenceMapper testCaseOverwriteReferenceMapper;

    @Override
    public long countByExample(TestCaseOverwriteExample example) {
        return testCaseOverwriteMapper.countByExample(example);
    }

    @Override
    public List<TestCaseOverwrite> selectByExample(TestCaseOverwriteExample example) {
        List<TestCaseOverwrite> selectResult = testCaseOverwriteReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<TestCaseOverwrite> selectByExampleWithRowbounds(TestCaseOverwriteExample example, RowBounds rowBounds) {
        List<TestCaseOverwrite> selectResult = testCaseOverwriteReferenceMapper.selectByExampleWithRowbounds(example,
                rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(TestCaseOverwrite.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public TestCaseOverwrite selectByPrimaryKey(Long id) {
        TestCaseOverwrite selectResult = testCaseOverwriteReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseOverwrite.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }
}
