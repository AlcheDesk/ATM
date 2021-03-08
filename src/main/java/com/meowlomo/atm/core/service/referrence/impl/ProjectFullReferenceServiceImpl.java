package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.ProjectFullReferenceMapper;
import com.meowlomo.atm.core.mapper.ProjectMapper;
import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;
import com.meowlomo.atm.core.service.referrence.ProjectFullReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectFullReferenceServiceImpl implements ProjectFullReferenceService {
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectFullReferenceMapper projectFullReferenceMapper;
    @Autowired
    private RedisService<Project> redisService;

    @Override
    public long countByExample(ProjectExample example) {
        return projectMapper.countByExample(example);
    }

    @Override
    public List<Project> selectByExample(ProjectExample example) {
        List<Project> selectResult = projectFullReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Project.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds) {
        List<Project> selectResult = projectFullReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Project.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Project selectByPrimaryKey(Long id) {
        Project selectResult = projectFullReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Project.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

    // @Override
    // public Project selectFullRefByPrimaryKey(Long id) {
    // Project selectResult = projectFullReferenceMapper.selectByPrimaryKey(id);
    // List<TestCase> testCases = selectResult.getTestCases();
    // List<TestCase> testCasesRef = new ArrayList<TestCase>();
    // for (TestCase testCase : testCases) {
    // TestCase testCaseRef =
    // testCaseReferenceMapper.selectByPrimaryKey(testCase.getId());
    // testCasesRef.add(testCaseRef);
    // }
    // selectResult.setTestCases(testCasesRef);
    // if (selectResult != null) {
    // redisService.putValue(cacheKeyGenerator.generateKey(Project.class, id,
    // "reference"), selectResult);
    // }
    // return selectResult;
    // }

}
