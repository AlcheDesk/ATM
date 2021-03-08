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
import com.meowlomo.atm.core.mapper.SystemRequirementMapper;
import com.meowlomo.atm.core.model.SystemRequirement;
import com.meowlomo.atm.core.model.SystemRequirementExample;
import com.meowlomo.atm.core.service.base.SystemRequirementService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemRequirementServiceImpl implements SystemRequirementService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<SystemRequirement> redisService;
    @Autowired
    private SystemRequirementMapper systemRequirementMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(SystemRequirementExample example) {
        return systemRequirementMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SystemRequirementExample example) {
        int deleteResult = systemRequirementMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = systemRequirementMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(SystemRequirement.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(SystemRequirement record) {
        int insertResult = systemRequirementMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirement.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<SystemRequirement> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirement record = records.get(i);
            if (systemRequirementMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirement.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<SystemRequirement> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirement record = records.get(i);
            if (systemRequirementMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirement.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(SystemRequirement record) {
        int insertResult = systemRequirementMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirement.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return insertResult;
    }

    @Override
    public List<SystemRequirement> selectByExample(SystemRequirementExample example) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirement.class, example);
        List<SystemRequirement> selectResult = new ArrayList<SystemRequirement>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirement>>() {
                    });
        }
        else {
            selectResult = systemRequirementMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<SystemRequirement> selectByExampleWithRowbounds(SystemRequirementExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirement.class, example, rowBounds);
        List<SystemRequirement> selectResult = new ArrayList<SystemRequirement>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirement>>() {
                    });
        }
        else {
            selectResult = systemRequirementMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirement selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirement.class, id);
        SystemRequirement selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<SystemRequirement>() {
                    });
        }
        else {
            selectResult = systemRequirementMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirement selectOneByExample(SystemRequirementExample example) {
        SystemRequirement selectResult = systemRequirementMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirement.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(SystemRequirement record, SystemRequirementExample example) {
        int updateResult = systemRequirementMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(SystemRequirement record, SystemRequirementExample example) {
        int updateResult = systemRequirementMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(SystemRequirement record) {
        int updateResult = systemRequirementMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SystemRequirement.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(SystemRequirement record) {
        int updateResult = systemRequirementMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SystemRequirement.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirement.class));
        }
        return updateResult;
    }
}
