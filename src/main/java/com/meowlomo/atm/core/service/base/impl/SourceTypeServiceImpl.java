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
import com.meowlomo.atm.core.mapper.SourceTypeMapper;
import com.meowlomo.atm.core.model.SourceType;
import com.meowlomo.atm.core.model.SourceTypeExample;
import com.meowlomo.atm.core.service.base.SourceTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SourceTypeServiceImpl implements SourceTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<SourceType> redisService;
    @Autowired
    private SourceTypeMapper sourceTypeMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(SourceTypeExample example) {
        return sourceTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SourceTypeExample example) {
        int deleteResult = sourceTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = sourceTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(SourceType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(SourceType record) {
        int insertResult = sourceTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SourceType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<SourceType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SourceType record = records.get(i);
            if (sourceTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SourceType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<SourceType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SourceType record = records.get(i);
            if (sourceTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(SourceType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(SourceType record) {
        int insertResult = sourceTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(SourceType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return insertResult;
    }

    @Override
    public List<SourceType> selectByExample(SourceTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(SourceType.class, example);
        List<SourceType> selectResult = new ArrayList<SourceType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SourceType>>() {
                    });
        }
        else {
            selectResult = sourceTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<SourceType> selectByExampleWithRowbounds(SourceTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(SourceType.class, example, rowBounds);
        List<SourceType> selectResult = new ArrayList<SourceType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SourceType>>() {
                    });
        }
        else {
            selectResult = sourceTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SourceType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(SourceType.class, id);
        SourceType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<SourceType>() {
            });
        }
        else {
            selectResult = sourceTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SourceType selectOneByExample(SourceTypeExample example) {
        SourceType selectResult = sourceTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(SourceType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(SourceType record, SourceTypeExample example) {
        int updateResult = sourceTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(SourceType record, SourceTypeExample example) {
        int updateResult = sourceTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(SourceType record) {
        int updateResult = sourceTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SourceType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(SourceType record) {
        int updateResult = sourceTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(SourceType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(SourceType.class));
        }
        return updateResult;
    }
}
