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
import com.meowlomo.atm.core.mapper.ProjectReportInfoMapper;
import com.meowlomo.atm.core.model.ProjectReportInfo;
import com.meowlomo.atm.core.model.ProjectReportInfoExample;
import com.meowlomo.atm.core.service.base.ProjectReportInfoService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectReportInfoServiceImpl implements ProjectReportInfoService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ProjectReportInfoMapper projectReportInfoMapper;
    @Autowired
    private RedisService<ProjectReportInfo> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ProjectReportInfoExample example) {
        return projectReportInfoMapper.countByExample(example);
    }

    @Override
    public List<ProjectReportInfo> selectByExample(ProjectReportInfoExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectReportInfo.class, example);
        List<ProjectReportInfo> selectResult = new ArrayList<ProjectReportInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ProjectReportInfo>>() {
                    });
        }
        else {
            selectResult = projectReportInfoMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ProjectReportInfo> selectByExampleWithRowbounds(ProjectReportInfoExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectReportInfo.class, example, rowBounds);
        List<ProjectReportInfo> selectResult = new ArrayList<ProjectReportInfo>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ProjectReportInfo>>() {
                    });
        }
        else {
            selectResult = projectReportInfoMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ProjectReportInfo selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectReportInfo.class, id);
        ProjectReportInfo selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ProjectReportInfo>() {
                    });
        }
        else {
            selectResult = projectReportInfoMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ProjectReportInfo selectOneByExample(ProjectReportInfoExample example) {
        ProjectReportInfo selectResult = projectReportInfoMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ProjectReportInfo.class, selectResult.getProjectId()),
                    selectResult);
            redisService.setExpire(cacheKeyGenerator.generateKey(ProjectReportInfo.class, selectResult.getProjectId()),
                    expireTimeInSeconds, TimeUnit.SECONDS);
        }
        return selectResult;
    }

}
