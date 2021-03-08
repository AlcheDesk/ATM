package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.RunSetTestCaseLinkMapper;
import com.meowlomo.atm.core.mapper.RunSetTestCaseLinkReferenceMapper;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.service.referrence.RunSetTestCaseLinkReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetTestCaseLinkReferenceServiceImpl implements RunSetTestCaseLinkReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetTestCaseLink> redisService;
    @Autowired
    private RunSetTestCaseLinkMapper runSetTestCaseLinkMapper;
    @Autowired
    private RunSetTestCaseLinkReferenceMapper runSetTestCaseLinkReferenceMapper;

    @Override
    public long countByExample(RunSetTestCaseLinkExample example) {
        return runSetTestCaseLinkMapper.countByExample(example);
    }

    @Override
    public List<RunSetTestCaseLink> selectByExample(RunSetTestCaseLinkExample example) {
        List<RunSetTestCaseLink> selectResult = runSetTestCaseLinkReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<RunSetTestCaseLink> selectByExampleWithRowbounds(RunSetTestCaseLinkExample example,
            RowBounds rowBounds) {
        List<RunSetTestCaseLink> selectResult = runSetTestCaseLinkReferenceMapper.selectByExampleWithRowbounds(example,
                rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public RunSetTestCaseLink selectByPrimaryKey(Long id) {
        RunSetTestCaseLink selectResult = runSetTestCaseLinkReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetTestCaseLink.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }

}
