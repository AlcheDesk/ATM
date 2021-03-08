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
import com.meowlomo.atm.core.mapper.TagMapper;
import com.meowlomo.atm.core.model.Tag;
import com.meowlomo.atm.core.model.TagExample;
import com.meowlomo.atm.core.service.base.TagService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TagServiceImpl implements TagService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Tag> redisService;
    @Autowired
    private TagMapper tagMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TagExample example) {
        return tagMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TagExample example) {
        int deleteResult = tagMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = tagMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Tag.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Tag record) {
        int insertResult = tagMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Tag.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Tag> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Tag record = records.get(i);
            if (tagMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Tag.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Tag> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Tag record = records.get(i);
            if (tagMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Tag.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Tag record) {
        int insertResult = tagMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Tag.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return insertResult;
    }

    @Override
    public List<Tag> selectByExample(TagExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Tag.class, example);
        List<Tag> selectResult = new ArrayList<Tag>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Tag>>() {
            });
        }
        else {
            selectResult = tagMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Tag> selectByExampleWithRowbounds(TagExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Tag.class, example, rowBounds);
        List<Tag> selectResult = new ArrayList<Tag>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Tag>>() {
            });
        }
        else {
            selectResult = tagMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Tag selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Tag.class, id);
        Tag selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Tag>() {
            });
        }
        else {
            selectResult = tagMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Tag selectOneByExample(TagExample example) {
        Tag selectResult = tagMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Tag.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Tag record, TagExample example) {
        int updateResult = tagMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Tag record, TagExample example) {
        int updateResult = tagMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Tag record) {
        int updateResult = tagMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Tag.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Tag record) {
        int updateResult = tagMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Tag.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tag.class));
        }
        return updateResult;
    }
}
