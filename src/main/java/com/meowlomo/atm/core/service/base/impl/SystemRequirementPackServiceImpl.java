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
import com.meowlomo.atm.core.mapper.SystemRequirementPackMapper;
import com.meowlomo.atm.core.model.SystemRequirementPack;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;
import com.meowlomo.atm.core.service.base.SystemRequirementPackService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemRequirementPackServiceImpl implements SystemRequirementPackService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<SystemRequirementPack> redisService;
    @Autowired
    private SystemRequirementPackMapper systemRequirementPackMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(SystemRequirementPackExample example) {
        return systemRequirementPackMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SystemRequirementPackExample example) {
        int deleteResult = systemRequirementPackMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = systemRequirementPackMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(SystemRequirementPack.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(SystemRequirementPack record) {
        int insertResult = systemRequirementPackMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementPack.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<SystemRequirementPack> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirementPack record = records.get(i);
            if (systemRequirementPackMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementPack.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<SystemRequirementPack> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirementPack record = records.get(i);
            if (systemRequirementPackMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementPack.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(SystemRequirementPack record) {
        int insertResult = systemRequirementPackMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementPack.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(SystemRequirementPackExample example) {
        int deleteResult = systemRequirementPackMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = systemRequirementPackMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(SystemRequirementPack.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return deleteResult;
    }

    @Override
    public List<SystemRequirementPack> selectByExample(SystemRequirementPackExample example) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementPack.class, example);
        List<SystemRequirementPack> selectResult = new ArrayList<SystemRequirementPack>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirementPack>>() {
                    });
        }
        else {
            selectResult = systemRequirementPackMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<SystemRequirementPack> selectByExampleWithRowbounds(SystemRequirementPackExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementPack.class, example, rowBounds);
        List<SystemRequirementPack> selectResult = new ArrayList<SystemRequirementPack>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirementPack>>() {
                    });
        }
        else {
            selectResult = systemRequirementPackMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirementPack selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementPack.class, id);
        SystemRequirementPack selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<SystemRequirementPack>() {
                    });
        }
        else {
            selectResult = systemRequirementPackMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirementPack selectOneByExample(SystemRequirementPackExample example) {
        SystemRequirementPack selectResult = systemRequirementPackMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementPack.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(SystemRequirementPack record, SystemRequirementPackExample example) {
        int updateResult = systemRequirementPackMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(SystemRequirementPack record, SystemRequirementPackExample example) {
        int updateResult = systemRequirementPackMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(SystemRequirementPack record) {
        int updateResult = systemRequirementPackMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SystemRequirementPack.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(SystemRequirementPack record) {
        int updateResult = systemRequirementPackMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SystemRequirementPack.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SystemRequirementPack.class));
        }
        return updateResult;
    }
}
