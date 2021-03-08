package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.RunExecutionInfoMapper;
import com.meowlomo.atm.core.mapper.RunExecutionInfoReferenceMapper;
import com.meowlomo.atm.core.model.RunExecutionInfo;
import com.meowlomo.atm.core.model.RunExecutionInfoExample;
import com.meowlomo.atm.core.service.referrence.RunExecutionInfoReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunExecutionInfoReferenceServiceImpl implements RunExecutionInfoReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunExecutionInfo> redisService;
    @Autowired
    private RunExecutionInfoMapper runExecutionInfoMapper;
    @Autowired
    private RunExecutionInfoReferenceMapper runExecutionInfoReferenceMapper;

    @Override
    public long countByExample(RunExecutionInfoExample example) {
        return runExecutionInfoMapper.countByExample(example);
    }

    @Override
    public List<RunExecutionInfo> selectByExample(RunExecutionInfoExample example) {
        example = this.processOrderBy(example);
        List<RunExecutionInfo> selectResult = runExecutionInfoMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunExecutionInfo.class, example), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<RunExecutionInfo> selectByExampleWithRowbounds(RunExecutionInfoExample example, RowBounds rowBounds) {
        example = this.processOrderBy(example);
        List<RunExecutionInfo> selectResult = runExecutionInfoReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunExecutionInfo.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public RunExecutionInfo selectByPrimaryKey(Long id) {
        RunExecutionInfo selectResult = runExecutionInfoReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunExecutionInfo.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public RunExecutionInfo selectOneByExample(RunExecutionInfoExample example) {
        example = this.processOrderBy(example);
        RunExecutionInfo selectResult = runExecutionInfoReferenceMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunExecutionInfo.class, selectResult.getRunId(), "reference"),
                    selectResult);
        }
        return selectResult;
    }
    
    private RunExecutionInfoExample processOrderBy(RunExecutionInfoExample example) {
        String orderByString = example.getOrderByClause();
        if (orderByString != null) {
            //replace , in the 
            String[] orderByClauses = orderByString.split(",");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < orderByClauses.length; i++) {
                sb.append("rei."+orderByClauses[i].trim());
                if (i < orderByClauses.length - 1) {
                    sb.append(" , ");
                }
            }
            example.setOrderByClause(sb.toString());
        }
        return example;
    }
}
