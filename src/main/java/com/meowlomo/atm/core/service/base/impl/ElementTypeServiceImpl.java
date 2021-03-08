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
import com.meowlomo.atm.core.mapper.ElementTypeMapper;
import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeExample;
import com.meowlomo.atm.core.service.base.ElementTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementTypeServiceImpl implements ElementTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ElementTypeMapper elementTypeMapper;
    @Autowired
    private RedisService<ElementType> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ElementTypeExample example) {
        return elementTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ElementTypeExample example) {
        int deleteResult = elementTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = elementTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ElementType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ElementType record) {
        int insertResult = elementTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ElementType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementType record = records.get(i);
            if (elementTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ElementType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ElementType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementType record = records.get(i);
            if (elementTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ElementType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ElementType record) {
        int insertResult = elementTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return insertResult;
    }

    @Override
    public List<ElementType> selectByExample(ElementTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ElementType.class, example);
        List<ElementType> selectResult = new ArrayList<ElementType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementType>>() {
                    });
        }
        else {
            selectResult = elementTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ElementType> selectByExampleWithRowbounds(ElementTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ElementType.class, example, rowBounds);
        List<ElementType> selectResult = new ArrayList<ElementType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementType>>() {
                    });
        }
        else {
            selectResult = elementTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ElementType.class, id);
        ElementType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<ElementType>() {
            });
        }
        else {
            selectResult = elementTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementType selectOneByExample(ElementTypeExample example) {
        ElementType selectResult = elementTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ElementType record, ElementTypeExample example) {
        int updateResult = elementTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ElementType record, ElementTypeExample example) {
        int updateResult = elementTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ElementType record) {
        int updateResult = elementTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ElementType record) {
        int updateResult = elementTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementType.class));
        }
        return updateResult;
    }
}
