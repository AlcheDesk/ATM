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
import com.meowlomo.atm.core.mapper.ElementLocatorTypeMapper;
import com.meowlomo.atm.core.model.ElementLocatorType;
import com.meowlomo.atm.core.model.ElementLocatorTypeExample;
import com.meowlomo.atm.core.service.base.ElementLocatorTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementLocatorTypeServiceImpl implements ElementLocatorTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ElementLocatorTypeMapper elementLocatorTypeMapper;
    @Autowired
    private RedisService<ElementLocatorType> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ElementLocatorTypeExample example) {
        return elementLocatorTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ElementLocatorTypeExample example) {
        int deleteResult = elementLocatorTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = elementLocatorTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ElementLocatorType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ElementLocatorType record) {
        int insertResult = elementLocatorTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementLocatorType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ElementLocatorType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementLocatorType record = records.get(i);
            if (elementLocatorTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ElementLocatorType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ElementLocatorType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementLocatorType record = records.get(i);
            if (elementLocatorTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ElementLocatorType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ElementLocatorType record) {
        int insertResult = elementLocatorTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementLocatorType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return insertResult;
    }

    @Override
    public List<ElementLocatorType> selectByExample(ElementLocatorTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ElementLocatorType.class, example);
        List<ElementLocatorType> selectResult = new ArrayList<ElementLocatorType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementLocatorType>>() {
                    });
        }
        else {
            selectResult = elementLocatorTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ElementLocatorType> selectByExampleWithRowbounds(ElementLocatorTypeExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ElementLocatorType.class, example, rowBounds);
        List<ElementLocatorType> selectResult = new ArrayList<ElementLocatorType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementLocatorType>>() {
                    });
        }
        else {
            selectResult = elementLocatorTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementLocatorType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ElementLocatorType.class, id);
        ElementLocatorType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ElementLocatorType>() {
                    });
        }
        else {
            selectResult = elementLocatorTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementLocatorType selectOneByExample(ElementLocatorTypeExample example) {
        ElementLocatorType selectResult = elementLocatorTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementLocatorType.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ElementLocatorType record, ElementLocatorTypeExample example) {
        int updateResult = elementLocatorTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ElementLocatorType record, ElementLocatorTypeExample example) {
        int updateResult = elementLocatorTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ElementLocatorType record) {
        int updateResult = elementLocatorTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementLocatorType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ElementLocatorType record) {
        int updateResult = elementLocatorTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementLocatorType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementLocatorType.class));
        }
        return updateResult;
    }
}
