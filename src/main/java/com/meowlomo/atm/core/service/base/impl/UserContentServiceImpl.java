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
import com.meowlomo.atm.core.mapper.UserContentMapper;
import com.meowlomo.atm.core.model.UserContent;
import com.meowlomo.atm.core.model.UserContentExample;
import com.meowlomo.atm.core.service.base.UserContentService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserContentServiceImpl implements UserContentService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<UserContent> redisService;
    @Autowired
    private UserContentMapper userContentMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(UserContentExample example) {
        return userContentMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserContentExample example) {
        int deleteResult = userContentMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = userContentMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(UserContent.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(UserContent record) {
        int insertResult = userContentMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(UserContent.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<UserContent> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            UserContent record = records.get(i);
            if (userContentMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(UserContent.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<UserContent> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            UserContent record = records.get(i);
            if (userContentMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(UserContent.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(UserContent record) {
        int insertResult = userContentMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(UserContent.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return insertResult;
    }

    @Override
    public List<UserContent> selectByExample(UserContentExample example) {
        String redisKey = cacheKeyGenerator.generateKey(UserContent.class, example);
        List<UserContent> selectResult = new ArrayList<UserContent>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<UserContent>>() {
                    });
        }
        else {
            selectResult = userContentMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<UserContent> selectByExampleWithRowbounds(UserContentExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(UserContent.class, example, rowBounds);
        List<UserContent> selectResult = new ArrayList<UserContent>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<UserContent>>() {
                    });
        }
        else {
            selectResult = userContentMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public UserContent selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(UserContent.class, id);
        UserContent selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<UserContent>() {
            });
        }
        else {
            selectResult = userContentMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public UserContent selectOneByExample(UserContentExample example) {
        UserContent selectResult = userContentMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(UserContent.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(UserContent record, UserContentExample example) {
        int updateResult = userContentMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(UserContent record, UserContentExample example) {
        int updateResult = userContentMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(UserContent record) {
        int updateResult = userContentMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(UserContent.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(UserContent record) {
        int updateResult = userContentMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(UserContent.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserContent.class));
        }
        return updateResult;
    }
}
