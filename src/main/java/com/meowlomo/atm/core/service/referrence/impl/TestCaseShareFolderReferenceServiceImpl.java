package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.TestCaseShareFolderMapper;
import com.meowlomo.atm.core.mapper.TestCaseShareFolderReferenceMapper;
import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;
import com.meowlomo.atm.core.service.referrence.TestCaseShareFolderReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseShareFolderReferenceServiceImpl implements TestCaseShareFolderReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<TestCaseShareFolder> redisService;
    @Autowired
    private TestCaseShareFolderMapper testCaseShareFolderMapper;
    @Autowired
    private TestCaseShareFolderReferenceMapper testCaseShareFolderReferenceMapper;

    @Override
    public long countByExample(TestCaseShareFolderExample example) {
        return testCaseShareFolderMapper.countByExample(example);
    }

    @Override
    public List<TestCaseShareFolder> selectByExample(TestCaseShareFolderExample example) {
        List<TestCaseShareFolder> selectResult = testCaseShareFolderReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<TestCaseShareFolder> selectByExampleWithRowbounds(TestCaseShareFolderExample example,
            RowBounds rowBounds) {
        List<TestCaseShareFolder> selectResult = testCaseShareFolderReferenceMapper
                .selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(TestCaseShareFolder.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public TestCaseShareFolder selectByPrimaryKey(Long id) {
        TestCaseShareFolder selectResult = testCaseShareFolderReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(TestCaseShareFolder.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }

}
