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
import com.meowlomo.atm.core.mapper.ProjectExecutionInfoMapper;
import com.meowlomo.atm.core.model.ProjectExecutionInfo;
import com.meowlomo.atm.core.model.ProjectExecutionInfoExample;
import com.meowlomo.atm.core.service.base.ProjectExecutionInfoService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectExecutionInfoServiceImpl implements ProjectExecutionInfoService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ProjectExecutionInfoMapper projectExecutionInfoMapper;
    @Autowired
    private RedisService<ProjectExecutionInfo> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ProjectExecutionInfoExample example) {
        return projectExecutionInfoMapper.countByExample(example);
    }

    @Override
    public List<ProjectExecutionInfo> selectByExample(ProjectExecutionInfoExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectExecutionInfo.class, example);
        List<ProjectExecutionInfo> selectResult = new ArrayList<ProjectExecutionInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ProjectExecutionInfo>>() {
                    });
        }
        else {
            selectResult = projectExecutionInfoMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ProjectExecutionInfo> selectByExampleWithRowbounds(ProjectExecutionInfoExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectExecutionInfo.class, example, rowBounds);
        List<ProjectExecutionInfo> selectResult = new ArrayList<ProjectExecutionInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ProjectExecutionInfo>>() {
                    });
        }
        else {
            selectResult = projectExecutionInfoMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ProjectExecutionInfo selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectExecutionInfo.class, id);
        ProjectExecutionInfo selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ProjectExecutionInfo>() {
                    });
        }
        else {
            selectResult = projectExecutionInfoMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ProjectExecutionInfo selectOneByExample(ProjectExecutionInfoExample example) {
        ProjectExecutionInfo selectResult = projectExecutionInfoMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(ProjectExecutionInfo.class, selectResult.getProjectId()),
                    selectResult);
            redisService.setExpire(
                    cacheKeyGenerator.generateKey(ProjectExecutionInfo.class, selectResult.getProjectId()),
                    expireTimeInSeconds, TimeUnit.SECONDS);
        }
        return selectResult;
    }
}
