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
import com.meowlomo.atm.core.mapper.ProjectTypeMapper;
import com.meowlomo.atm.core.model.ProjectType;
import com.meowlomo.atm.core.model.ProjectTypeExample;
import com.meowlomo.atm.core.service.base.ProjectTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectTypeServiceImpl implements ProjectTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ProjectTypeMapper projectTypeMapper;
    @Autowired
    private RedisService<ProjectType> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ProjectTypeExample example) {
        return projectTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ProjectTypeExample example) {
        int deleteResult = projectTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = projectTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ProjectType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ProjectType record) {
        int insertResult = projectTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ProjectType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ProjectType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ProjectType record = records.get(i);
            if (projectTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ProjectType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ProjectType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ProjectType record = records.get(i);
            if (projectTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ProjectType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ProjectType record) {
        int insertResult = projectTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ProjectType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return insertResult;
    }

    @Override
    public List<ProjectType> selectByExample(ProjectTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectType.class, example);
        List<ProjectType> selectResult = new ArrayList<ProjectType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ProjectType>>() {
                    });
        }
        else {
            selectResult = projectTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ProjectType> selectByExampleWithRowbounds(ProjectTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectType.class, example, rowBounds);
        List<ProjectType> selectResult = new ArrayList<ProjectType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ProjectType>>() {
                    });
        }
        else {
            selectResult = projectTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ProjectType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ProjectType.class, id);
        ProjectType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<ProjectType>() {
            });
        }
        else {
            selectResult = projectTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ProjectType selectOneByExample(ProjectTypeExample example) {
        ProjectType selectResult = projectTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ProjectType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ProjectType record, ProjectTypeExample example) {
        int updateResult = projectTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ProjectType record, ProjectTypeExample example) {
        int updateResult = projectTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ProjectType record) {
        int updateResult = projectTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ProjectType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ProjectType record) {
        int updateResult = projectTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ProjectType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ProjectType.class));
        }
        return updateResult;
    }
}
