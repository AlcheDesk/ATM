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
import com.meowlomo.atm.core.mapper.RunSetTypeMapper;
import com.meowlomo.atm.core.model.RunSetType;
import com.meowlomo.atm.core.model.RunSetTypeExample;
import com.meowlomo.atm.core.service.base.RunSetTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetTypeServiceImpl implements RunSetTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetType> redisService;
    @Autowired
    private RunSetTypeMapper runSetTypeMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetTypeExample example) {
        return runSetTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetTypeExample example) {
        int deleteResult = runSetTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetType record) {
        int insertResult = runSetTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetType record = records.get(i);
            if (runSetTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetType record = records.get(i);
            if (runSetTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetType record) {
        int insertResult = runSetTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSetType> selectByExample(RunSetTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetType.class, example);
        List<RunSetType> selectResult = new ArrayList<RunSetType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetType>>() {
                    });
        }
        else {
            selectResult = runSetTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetType> selectByExampleWithRowbounds(RunSetTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetType.class, example, rowBounds);
        List<RunSetType> selectResult = new ArrayList<RunSetType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetType>>() {
                    });
        }
        else {
            selectResult = runSetTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetType.class, id);
        RunSetType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<RunSetType>() {
            });
        }
        else {
            selectResult = runSetTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetType selectOneByExample(RunSetTypeExample example) {
        RunSetType selectResult = runSetTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetType record, RunSetTypeExample example) {
        int updateResult = runSetTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetType record, RunSetTypeExample example) {
        int updateResult = runSetTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetType record) {
        int updateResult = runSetTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetType record) {
        int updateResult = runSetTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetType.class));
        }
        return updateResult;
    }
}
