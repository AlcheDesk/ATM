package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.TestCaseFullReferenceMapper;
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.service.referrence.TestCaseFullReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseFullReferenceServiceImpl implements TestCaseFullReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCase> redisService;
    @Autowired
    private TestCaseFullReferenceMapper testCaseFullReferenceMapper;
    @Autowired
    private TestCaseMapper testCaseMapper;

    @Override
    public long countByExample(TestCaseExample example) {
        return testCaseMapper.countByExample(example);
    }

    @Override
    public List<TestCase> selectByExample(TestCaseExample example) {
        List<TestCase> selectResult = testCaseFullReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(TestCase.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<TestCase> selectByExampleWithRowbounds(TestCaseExample example, RowBounds rowBounds) {
        List<TestCase> selectResult = testCaseFullReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(TestCase.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public TestCase selectByPrimaryKey(Long id) {
        TestCase selectResult = testCaseFullReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCase.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}