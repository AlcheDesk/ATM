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
import com.meowlomo.atm.core.mapper.UserActivityLogMapper;
import com.meowlomo.atm.core.model.UserActivityLog;
import com.meowlomo.atm.core.model.UserActivityLogExample;
import com.meowlomo.atm.core.service.base.UserActivityLogService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserActivityLogServiceImpl implements UserActivityLogService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<UserActivityLog> redisService;
    @Autowired
    private UserActivityLogMapper userActivityLogMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(UserActivityLogExample example) {
        return userActivityLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserActivityLogExample example) {
        int deleteResult = userActivityLogMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = userActivityLogMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(UserActivityLog.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(UserActivityLog record) {
        int insertResult = userActivityLogMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(UserActivityLog.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<UserActivityLog> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            UserActivityLog record = records.get(i);
            if (userActivityLogMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(UserActivityLog.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<UserActivityLog> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            UserActivityLog record = records.get(i);
            if (userActivityLogMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(UserActivityLog.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(UserActivityLog record) {
        int insertResult = userActivityLogMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(UserActivityLog.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return insertResult;
    }

    @Override
    public List<UserActivityLog> selectByExample(UserActivityLogExample example) {
        String redisKey = cacheKeyGenerator.generateKey(UserActivityLog.class, example);
        List<UserActivityLog> selectResult = new ArrayList<UserActivityLog>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<UserActivityLog>>() {
                    });
        }
        else {
            selectResult = userActivityLogMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<UserActivityLog> selectByExampleWithRowbounds(UserActivityLogExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(UserActivityLog.class, example, rowBounds);
        List<UserActivityLog> selectResult = new ArrayList<UserActivityLog>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<UserActivityLog>>() {
                    });
        }
        else {
            selectResult = userActivityLogMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public UserActivityLog selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(UserActivityLog.class, id);
        UserActivityLog selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<UserActivityLog>() {
                    });
        }
        else {
            selectResult = userActivityLogMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public UserActivityLog selectOneByExample(UserActivityLogExample example) {
        UserActivityLog selectResult = userActivityLogMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(UserActivityLog.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(UserActivityLog record, UserActivityLogExample example) {
        int updateResult = userActivityLogMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(UserActivityLog record, UserActivityLogExample example) {
        int updateResult = userActivityLogMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(UserActivityLog record) {
        int updateResult = userActivityLogMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(UserActivityLog.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(UserActivityLog record) {
        int updateResult = userActivityLogMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(UserActivityLog.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(UserActivityLog.class));
        }
        return updateResult;
    }
}
