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
import com.meowlomo.atm.core.mapper.ElementTypeElementLocatorTypeLinkMapper;
import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeElementLocatorTypeLink;
import com.meowlomo.atm.core.model.ElementTypeElementLocatorTypeLinkExample;
import com.meowlomo.atm.core.model.ElementTypeExample;
import com.meowlomo.atm.core.service.base.ElementLocatorTypeService;
import com.meowlomo.atm.core.service.base.ElementTypeElementLocatorTypeLinkService;
import com.meowlomo.atm.core.service.base.ElementTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementTypeElementLocatorTypeLinkServiceImpl implements ElementTypeElementLocatorTypeLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ElementLocatorTypeService elementLocatorTypeService;
    @Autowired
    private ElementTypeElementLocatorTypeLinkMapper elementTypeElementLocatorTypeLinkMapper;
    @Autowired
    private ElementTypeService elementTypeService;
    @Autowired
    private RedisService<ElementTypeElementLocatorTypeLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ElementTypeElementLocatorTypeLinkExample example) {
        return elementTypeElementLocatorTypeLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ElementTypeElementLocatorTypeLinkExample example) {
        int deleteResult = elementTypeElementLocatorTypeLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = elementTypeElementLocatorTypeLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ElementTypeElementLocatorTypeLink record) {
        int insertResult = elementTypeElementLocatorTypeLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ElementTypeElementLocatorTypeLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementTypeElementLocatorTypeLink record = records.get(i);
            if (elementTypeElementLocatorTypeLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ElementTypeElementLocatorTypeLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementTypeElementLocatorTypeLink record = records.get(i);
            if (elementTypeElementLocatorTypeLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ElementTypeElementLocatorTypeLink record) {
        int insertResult = elementTypeElementLocatorTypeLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return insertResult;
    }

    @Override
    public List<ElementType> listElementTypeByElementLocatorTypePrimaryKey(Long elementLocatorTypeId) {
        ElementTypeElementLocatorTypeLinkExample linkExample = new ElementTypeElementLocatorTypeLinkExample();
        linkExample.createCriteria().andElementLocatorTypeEqualTo(
                elementLocatorTypeService.selectByPrimaryKey(elementLocatorTypeId).getName());
        List<ElementTypeElementLocatorTypeLink> targetLinks = elementTypeElementLocatorTypeLinkMapper
                .selectByExample(linkExample);
        List<String> targetNames = new ArrayList<String>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetNames.add(targetLinks.get(i).getElementType());
        }
        if (targetNames.size() == 0) {
            return new ArrayList<ElementType>();
        }
        else {
            ElementTypeExample targetExample = new ElementTypeExample();
            targetExample.createCriteria().andNameIn(targetNames);
            return elementTypeService.selectByExample(targetExample);
        }
    }

    @Override
    public List<ElementTypeElementLocatorTypeLink> selectByExample(ElementTypeElementLocatorTypeLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, example);
        List<ElementTypeElementLocatorTypeLink> selectResult = new ArrayList<ElementTypeElementLocatorTypeLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementTypeElementLocatorTypeLink>>() {
                    });
        }
        else {
            selectResult = elementTypeElementLocatorTypeLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ElementTypeElementLocatorTypeLink> selectByExampleWithRowbounds(
            ElementTypeElementLocatorTypeLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, example, rowBounds);
        List<ElementTypeElementLocatorTypeLink> selectResult = new ArrayList<ElementTypeElementLocatorTypeLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementTypeElementLocatorTypeLink>>() {
                    });
        }
        else {
            selectResult = elementTypeElementLocatorTypeLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementTypeElementLocatorTypeLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, id);
        ElementTypeElementLocatorTypeLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ElementTypeElementLocatorTypeLink>() {
                    });
        }
        else {
            selectResult = elementTypeElementLocatorTypeLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementTypeElementLocatorTypeLink selectOneByExample(ElementTypeElementLocatorTypeLinkExample example) {
        ElementTypeElementLocatorTypeLink selectResult = elementTypeElementLocatorTypeLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ElementTypeElementLocatorTypeLink record,
            ElementTypeElementLocatorTypeLinkExample example) {
        int updateResult = elementTypeElementLocatorTypeLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ElementTypeElementLocatorTypeLink record,
            ElementTypeElementLocatorTypeLinkExample example) {
        int updateResult = elementTypeElementLocatorTypeLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ElementTypeElementLocatorTypeLink record) {
        int updateResult = elementTypeElementLocatorTypeLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ElementTypeElementLocatorTypeLink record) {
        int updateResult = elementTypeElementLocatorTypeLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementTypeElementLocatorTypeLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeElementLocatorTypeLink.class));
        }
        return updateResult;
    }
}
