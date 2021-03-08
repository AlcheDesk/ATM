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
import com.meowlomo.atm.core.mapper.ApiSchemaMapper;
import com.meowlomo.atm.core.model.ApiSchema;
import com.meowlomo.atm.core.model.ApiSchemaExample;
import com.meowlomo.atm.core.service.base.ApiSchemaService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApiSchemaServiceImpl implements ApiSchemaService {

    @Autowired
    private ApiSchemaMapper apiSchemaMapper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<ApiSchema> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ApiSchemaExample example) {
        return apiSchemaMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ApiSchemaExample example) {
        int deleteResult = apiSchemaMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = apiSchemaMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ApiSchema.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ApiSchema record) {
        int insertResult = apiSchemaMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ApiSchema.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ApiSchema> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ApiSchema record = records.get(i);
            if (apiSchemaMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ApiSchema.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ApiSchema> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ApiSchema record = records.get(i);
            if (apiSchemaMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ApiSchema.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ApiSchema record) {
        int insertResult = apiSchemaMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ApiSchema.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return insertResult;
    }

    @Override
    public List<ApiSchema> selectByExample(ApiSchemaExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ApiSchema.class, example);
        List<ApiSchema> selectResult = new ArrayList<ApiSchema>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ApiSchema>>() {
                    });

        }
        else {
            selectResult = apiSchemaMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ApiSchema> selectByExampleWithRowbounds(ApiSchemaExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ApiSchema.class, example, rowBounds);
        List<ApiSchema> selectResult = new ArrayList<ApiSchema>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ApiSchema>>() {
                    });

        }
        else {
            selectResult = apiSchemaMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ApiSchema selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ApiSchema.class, id);
        ApiSchema selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<ApiSchema>() {
            });
        }
        else {
            selectResult = apiSchemaMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ApiSchema selectOneByExample(ApiSchemaExample example) {
        ApiSchema selectResult = apiSchemaMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ApiSchema.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ApiSchema record, ApiSchemaExample example) {
        int updateResult = apiSchemaMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ApiSchema record, ApiSchemaExample example) {
        int updateResult = apiSchemaMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ApiSchema record) {
        int updateResult = apiSchemaMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ApiSchema.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ApiSchema record) {
        int updateResult = apiSchemaMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ApiSchema.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ApiSchema.class));
        }
        return updateResult;
    }
}
