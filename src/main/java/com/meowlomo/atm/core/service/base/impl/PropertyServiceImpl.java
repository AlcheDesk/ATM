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
import com.meowlomo.atm.core.mapper.PropertyMapper;
import com.meowlomo.atm.core.model.Property;
import com.meowlomo.atm.core.model.PropertyExample;
import com.meowlomo.atm.core.service.base.PropertyService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private RedisService<Property> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(PropertyExample example) {
        return propertyMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(PropertyExample example) {
        int deleteResult = propertyMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = propertyMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Property.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Property record) {
        int insertResult = propertyMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Property.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Property> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Property record = records.get(i);
            if (propertyMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Property.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Property> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Property record = records.get(i);
            if (propertyMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Property.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Property record) {
        int insertResult = propertyMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Property.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return insertResult;
    }

    @Override
    public List<Property> selectByExample(PropertyExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Property.class, example);
        List<Property> selectResult = new ArrayList<Property>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Property>>() {
                    });
        }
        else {
            selectResult = propertyMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Property> selectByExampleWithRowbounds(PropertyExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Property.class, example, rowBounds);
        List<Property> selectResult = new ArrayList<Property>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Property>>() {
                    });
        }
        else {
            selectResult = propertyMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Property selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Property.class, id);
        Property selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Property>() {
            });
        }
        else {
            selectResult = propertyMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Property selectOneByExample(PropertyExample example) {
        Property selectResult = propertyMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Property.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Property record, PropertyExample example) {
        int updateResult = propertyMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Property record, PropertyExample example) {
        int updateResult = propertyMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Property record) {
        int updateResult = propertyMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Property.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Property record) {
        int updateResult = propertyMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Property.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Property.class));
        }
        return updateResult;
    }
}
