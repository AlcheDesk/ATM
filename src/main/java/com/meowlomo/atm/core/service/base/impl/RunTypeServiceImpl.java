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
import com.meowlomo.atm.core.mapper.RunTypeMapper;
import com.meowlomo.atm.core.model.RunType;
import com.meowlomo.atm.core.model.RunTypeExample;
import com.meowlomo.atm.core.service.base.RunTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunTypeServiceImpl implements RunTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunType> redisService;
    @Autowired
    private RunTypeMapper runTypeMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunTypeExample example) {
        return runTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunTypeExample example) {
        int deleteResult = runTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunType record) {
        int insertResult = runTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunType record = records.get(i);
            if (runTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunType record = records.get(i);
            if (runTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunType record) {
        int insertResult = runTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return insertResult;
    }

    @Override
    public List<RunType> selectByExample(RunTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunType.class, example);
        List<RunType> selectResult = new ArrayList<RunType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunType>>() {
                    });
        }
        else {
            selectResult = runTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunType> selectByExampleWithRowbounds(RunTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunType.class, example, rowBounds);
        List<RunType> selectResult = new ArrayList<RunType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunType>>() {
                    });
        }
        else {
            selectResult = runTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunType.class, id);
        RunType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<RunType>() {
            });
        }
        else {
            selectResult = runTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunType selectOneByExample(RunTypeExample example) {
        RunType selectResult = runTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunType record, RunTypeExample example) {
        int updateResult = runTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunType record, RunTypeExample example) {
        int updateResult = runTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunType record) {
        int updateResult = runTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunType record) {
        int updateResult = runTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunType.class));
        }
        return updateResult;
    }
}
