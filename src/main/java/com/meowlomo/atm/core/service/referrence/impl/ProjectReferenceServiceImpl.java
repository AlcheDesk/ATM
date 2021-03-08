package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.ProjectMapper;
import com.meowlomo.atm.core.mapper.ProjectReferenceMapper;
import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;
import com.meowlomo.atm.core.service.referrence.ProjectReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectReferenceServiceImpl implements ProjectReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectReferenceMapper projectReferenceMapper;
    @Autowired
    private RedisService<Project> redisService;

    @Override
    public long countByExample(ProjectExample example) {
        return projectMapper.countByExample(example);
    }

    @Override
    public List<Project> selectByExample(ProjectExample example) {
        List<Project> selectResult = projectReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Project.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds) {
        List<Project> selectResult = projectReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Project.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Project selectByPrimaryKey(Long id) {
        Project selectResult = projectReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Project.class, id, "reference"), selectResult);
        }
        return selectResult;
    }
}
