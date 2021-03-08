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
import com.meowlomo.atm.core.mapper.SystemRequirementTypeMapper;
import com.meowlomo.atm.core.model.SystemRequirementType;
import com.meowlomo.atm.core.model.SystemRequirementTypeExample;
import com.meowlomo.atm.core.service.base.SystemRequirementTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemRequirementTypeServiceImpl implements SystemRequirementTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<SystemRequirementType> redisService;
    @Autowired
    private SystemRequirementTypeMapper systemRequirementTypeMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(SystemRequirementTypeExample example) {
        return systemRequirementTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SystemRequirementTypeExample example) {
        int deleteResult = systemRequirementTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = systemRequirementTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(SystemRequirementType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(SystemRequirementType record) {
        int insertResult = systemRequirementTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<SystemRequirementType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirementType record = records.get(i);
            if (systemRequirementTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementType.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<SystemRequirementType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirementType record = records.get(i);
            if (systemRequirementTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementType.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(SystemRequirementType record) {
        int insertResult = systemRequirementTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return insertResult;
    }

    @Override
    public List<SystemRequirementType> selectByExample(SystemRequirementTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementType.class, example);
        List<SystemRequirementType> selectResult = new ArrayList<SystemRequirementType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirementType>>() {
                    });
        }
        else {
            selectResult = systemRequirementTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<SystemRequirementType> selectByExampleWithRowbounds(SystemRequirementTypeExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementType.class, example, rowBounds);
        List<SystemRequirementType> selectResult = new ArrayList<SystemRequirementType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirementType>>() {
                    });
        }
        else {
            selectResult = systemRequirementTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirementType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementType.class, id);
        SystemRequirementType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<SystemRequirementType>() {
                    });
        }
        else {
            selectResult = systemRequirementTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirementType selectOneByExample(SystemRequirementTypeExample example) {
        SystemRequirementType selectResult = systemRequirementTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementType.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(SystemRequirementType record, SystemRequirementTypeExample example) {
        int updateResult = systemRequirementTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(SystemRequirementType record, SystemRequirementTypeExample example) {
        int updateResult = systemRequirementTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(SystemRequirementType record) {
        int updateResult = systemRequirementTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SystemRequirementType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(SystemRequirementType record) {
        int updateResult = systemRequirementTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SystemRequirementType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementType.class));
        }
        return updateResult;
    }
}
