package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.mapper.TestCaseReferenceMapper;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseReferenceServiceImpl implements TestCaseReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCase> redisService;
    @Autowired
    private TestCaseMapper testCaseMapper;
    @Autowired
    private TestCaseReferenceMapper testCaseReferenceMapper;

    @Override
    public long countByExample(TestCaseExample example) {
        return testCaseMapper.countByExample(example);
    }

    @Override
    public List<TestCase> selectByExample(TestCaseExample example) {
        List<TestCase> selectResult = testCaseReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(TestCase.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<TestCase> selectByExampleWithRowbounds(TestCaseExample example, RowBounds rowBounds) {
        List<TestCase> selectResult = testCaseReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(TestCase.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public TestCase selectByPrimaryKey(Long id) {
        TestCase selectResult = testCaseReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCase.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}