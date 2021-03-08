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
import com.meowlomo.atm.core.mapper.StatusMapper;
import com.meowlomo.atm.core.model.Status;
import com.meowlomo.atm.core.model.StatusExample;
import com.meowlomo.atm.core.service.base.StatusService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StatusServiceImpl implements StatusService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Status> redisService;
    @Autowired
    private StatusMapper statusMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(StatusExample example) {
        return statusMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(StatusExample example) {
        int deleteResult = statusMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = statusMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Status.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Status record) {
        int insertResult = statusMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Status.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Status> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Status record = records.get(i);
            if (statusMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Status.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Status> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Status record = records.get(i);
            if (statusMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Status.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Status record) {
        int insertResult = statusMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Status.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return insertResult;
    }

    @Override
    public List<Status> selectByExample(StatusExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Status.class, example);
        List<Status> selectResult = new ArrayList<Status>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Status>>() {
            });
        }
        else {
            selectResult = statusMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Status> selectByExampleWithRowbounds(StatusExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Status.class, example, rowBounds);
        List<Status> selectResult = new ArrayList<Status>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Status>>() {
            });
        }
        else {
            selectResult = statusMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Status selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Status.class, id);
        Status selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Status>() {
            });
        }
        else {
            selectResult = statusMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Status selectOneByExample(StatusExample example) {
        Status selectResult = statusMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Status.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Status record, StatusExample example) {
        int updateResult = statusMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Status record, StatusExample example) {
        int updateResult = statusMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Status record) {
        int updateResult = statusMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Status.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Status record) {
        int updateResult = statusMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Status.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Status.class));
        }
        return updateResult;
    }
}
