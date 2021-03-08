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
import com.meowlomo.atm.core.mapper.LogLevelMapper;
import com.meowlomo.atm.core.model.LogLevel;
import com.meowlomo.atm.core.model.LogLevelExample;
import com.meowlomo.atm.core.service.base.LogLevelService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class LogLevelServiceImpl implements LogLevelService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private LogLevelMapper logLevelMapper;
    @Autowired
    private RedisService<LogLevel> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(LogLevelExample example) {
        return logLevelMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(LogLevelExample example) {
        int deleteResult = logLevelMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = logLevelMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(LogLevel.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(LogLevel record) {
        int insertResult = logLevelMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(LogLevel.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<LogLevel> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            LogLevel record = records.get(i);
            if (logLevelMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(LogLevel.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<LogLevel> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            LogLevel record = records.get(i);
            if (logLevelMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(LogLevel.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(LogLevel record) {
        int insertResult = logLevelMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(LogLevel.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return insertResult;
    }

    @Override
    public List<LogLevel> selectByExample(LogLevelExample example) {
        String redisKey = cacheKeyGenerator.generateKey(LogLevel.class, example);
        List<LogLevel> selectResult = new ArrayList<LogLevel>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<LogLevel>>() {
                    });
        }
        else {
            selectResult = logLevelMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<LogLevel> selectByExampleWithRowbounds(LogLevelExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(LogLevel.class, example, rowBounds);
        List<LogLevel> selectResult = new ArrayList<LogLevel>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<LogLevel>>() {
                    });
        }
        else {
            selectResult = logLevelMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public LogLevel selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(LogLevel.class, id);
        LogLevel selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<LogLevel>() {
            });
        }
        else {
            selectResult = logLevelMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public LogLevel selectOneByExample(LogLevelExample example) {
        LogLevel selectResult = logLevelMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(LogLevel.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(LogLevel record, LogLevelExample example) {
        int updateResult = logLevelMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(LogLevel record, LogLevelExample example) {
        int updateResult = logLevelMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(LogLevel record) {
        int updateResult = logLevelMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(LogLevel.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(LogLevel record) {
        int updateResult = logLevelMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(LogLevel.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(LogLevel.class));
        }
        return updateResult;
    }
}
