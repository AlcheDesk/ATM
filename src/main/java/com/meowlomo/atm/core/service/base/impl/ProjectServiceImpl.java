package com.meowlomo.atm.core.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.ProjectMapper;
import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;
import com.meowlomo.atm.core.model.ProjectExecutionInfo;
import com.meowlomo.atm.core.model.ProjectReportInfo;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.service.base.ProjectService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RedisService<Project> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ProjectExample example) {
        return projectMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ProjectExample example) {
        int deleteResult = projectMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = projectMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Project.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Project record) {
        int insertResult = projectMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Project.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Project> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Project record = records.get(i);
            if (projectMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Project.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Project> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Project record = records.get(i);
            if (projectMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Project.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Project record) {
        int insertResult = projectMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Project.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(ProjectExample example) {
        int deleteResult = projectMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = projectMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Project.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
        }
        return deleteResult;
    }

    @Override
    public List<Project> selectByExample(ProjectExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Project.class, example);
        List<Project> selectResult = new ArrayList<Project>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Project>>() {
                    });
        }
        else {
            selectResult = projectMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Project.class, example, rowBounds);
        List<Project> selectResult = new ArrayList<Project>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Project>>() {
                    });
        }
        else {
            selectResult = projectMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Project selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Project.class, id);
        Project selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Project>() {
            });
        }
        else {
            selectResult = projectMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Project selectOneByExample(ProjectExample example) {
        Project selectResult = projectMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Project.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Project record, ProjectExample example) {
        int updateResult = projectMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Project record, ProjectExample example) {
        int updateResult = projectMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        int updateResult = projectMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Project.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        int updateResult = projectMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Project.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Project.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectReportInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCase.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ProjectExecutionInfo.class));
        }
        return updateResult;
    }
}
