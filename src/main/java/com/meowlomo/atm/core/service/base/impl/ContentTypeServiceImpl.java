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
import com.meowlomo.atm.core.mapper.ContentTypeMapper;
import com.meowlomo.atm.core.model.ContentType;
import com.meowlomo.atm.core.model.ContentTypeExample;
import com.meowlomo.atm.core.service.base.ContentTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ContentTypeServiceImpl implements ContentTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ContentTypeMapper contentTypeMapper;
    @Autowired
    private RedisService<ContentType> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ContentTypeExample example) {
        return contentTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ContentTypeExample example) {
        int deleteResult = contentTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = contentTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ContentType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ContentType record) {
        int insertResult = contentTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ContentType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ContentType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ContentType record = records.get(i);
            if (contentTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ContentType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ContentType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ContentType record = records.get(i);
            if (contentTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ContentType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ContentType record) {
        int insertResult = contentTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ContentType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return insertResult;
    }

    @Override
    public List<ContentType> selectByExample(ContentTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ContentType.class, example);
        List<ContentType> selectResult = new ArrayList<ContentType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ContentType>>() {
                    });
        }
        else {
            selectResult = contentTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ContentType> selectByExampleWithRowbounds(ContentTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ContentType.class, example, rowBounds);
        List<ContentType> selectResult = new ArrayList<ContentType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ContentType>>() {
                    });
        }
        else {
            selectResult = contentTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ContentType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ContentType.class, id);
        ContentType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<ContentType>() {
            });
        }
        else {
            selectResult = contentTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ContentType selectOneByExample(ContentTypeExample example) {
        ContentType selectResult = contentTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ContentType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ContentType record, ContentTypeExample example) {
        int updateResult = contentTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ContentType record, ContentTypeExample example) {
        int updateResult = contentTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ContentType record) {
        int updateResult = contentTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ContentType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ContentType record) {
        int updateResult = contentTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ContentType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ContentType.class));
        }
        return updateResult;
    }
}
