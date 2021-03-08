package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.TestCaseShareFolderTestCaseLinkMapper;
import com.meowlomo.atm.core.mapper.TestCaseShareFolderTestCaseLinkReferenceMapper;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLinkExample;
import com.meowlomo.atm.core.service.referrence.TestCaseShareFolderTestCaseLinkReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseShareFolderTestCaseLinkReferenceServiceImpl
        implements TestCaseShareFolderTestCaseLinkReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseShareFolderTestCaseLink> redisService;
    @Autowired
    private TestCaseShareFolderTestCaseLinkMapper testCaseShareFolderTestCaseLinkMapper;
    @Autowired
    private TestCaseShareFolderTestCaseLinkReferenceMapper testCaseShareFolderTestCaseLinkReferenceMapper;

    @Override
    public long countByExample(TestCaseShareFolderTestCaseLinkExample example) {
        return testCaseShareFolderTestCaseLinkMapper.countByExample(example);
    }

    @Override
    public List<TestCaseShareFolderTestCaseLink> selectByExample(TestCaseShareFolderTestCaseLinkExample example) {
        List<TestCaseShareFolderTestCaseLink> selectResult = testCaseShareFolderTestCaseLinkReferenceMapper
                .selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<TestCaseShareFolderTestCaseLink> selectByExampleWithRowbounds(
            TestCaseShareFolderTestCaseLinkExample example, RowBounds rowBounds) {
        List<TestCaseShareFolderTestCaseLink> selectResult = testCaseShareFolderTestCaseLinkReferenceMapper
                .selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, example,
                    rowBounds, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public TestCaseShareFolderTestCaseLink selectByPrimaryKey(Long id) {
        TestCaseShareFolderTestCaseLink selectResult = testCaseShareFolderTestCaseLinkReferenceMapper
                .selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolderTestCaseLink.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }

}