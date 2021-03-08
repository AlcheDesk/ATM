package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.ProjectReportInfoMapper;
import com.meowlomo.atm.core.mapper.ProjectReportInfoReferenceMapper;
import com.meowlomo.atm.core.model.ProjectReportInfo;
import com.meowlomo.atm.core.model.ProjectReportInfoExample;
import com.meowlomo.atm.core.service.referrence.ProjectReportInfoReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectReportInfoReferenceServiceImpl implements ProjectReportInfoReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ProjectReportInfoMapper projectReportInfoMapper;
    @Autowired
    private ProjectReportInfoReferenceMapper projectReportInfoReferenceMapper;
    @Autowired
    private RedisService<ProjectReportInfo> redisService;

    @Override
    public long countByExample(ProjectReportInfoExample example) {
        return projectReportInfoMapper.countByExample(example);
    }

    @Override
    public List<ProjectReportInfo> selectByExample(ProjectReportInfoExample example) {
        List<ProjectReportInfo> selectResult = projectReportInfoReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(ProjectReportInfo.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<ProjectReportInfo> selectByExampleWithRowbounds(ProjectReportInfoExample example, RowBounds rowBounds) {
        List<ProjectReportInfo> selectResult = projectReportInfoReferenceMapper.selectByExampleWithRowbounds(example,
                rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(ProjectReportInfo.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public ProjectReportInfo selectByPrimaryKey(Long id) {
        ProjectReportInfo selectResult = projectReportInfoReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ProjectReportInfo.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }
}
