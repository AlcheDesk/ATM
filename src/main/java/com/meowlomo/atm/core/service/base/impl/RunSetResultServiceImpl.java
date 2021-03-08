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
import com.meowlomo.atm.core.mapper.RunSetResultMapper;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;
import com.meowlomo.atm.core.service.base.RunSetResultService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultServiceImpl implements RunSetResultService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetResult> redisService;
    @Autowired
    private RunSetResultMapper runSetResultMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetResultExample example) {
        return runSetResultMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetResultExample example) {
        int deleteResult = runSetResultMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetResultMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetResult.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetResult record) {
        int insertResult = runSetResultMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResult.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetResult> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetResult record = records.get(i);
            if (runSetResultMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetResult.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetResult> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetResult record = records.get(i);
            if (runSetResultMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetResult.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetResult record) {
        int insertResult = runSetResultMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResult.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSetResult> selectByExample(RunSetResultExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetResult.class, example);
        List<RunSetResult> selectResult = new ArrayList<RunSetResult>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetResult>>() {
                    });
        }
        else {
            selectResult = runSetResultMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetResult.class, example, rowBounds);
        List<RunSetResult> selectResult = new ArrayList<RunSetResult>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetResult>>() {
                    });
        }
        else {
            selectResult = runSetResultMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetResult selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetResult.class, id);
        RunSetResult selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunSetResult>() {
                    });
        }
        else {
            selectResult = runSetResultMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetResult selectOneByExample(RunSetResultExample example) {
        RunSetResult selectResult = runSetResultMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResult.class, selectResult.getId()),
                    selectResult);
            redisService.setExpire(cacheKeyGenerator.generateKey(RunSetResult.class, selectResult.getId()),
                    expireTimeInSeconds, TimeUnit.SECONDS);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetResult record, RunSetResultExample example) {
        int updateResult = runSetResultMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetResult record, RunSetResultExample example) {
        int updateResult = runSetResultMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetResult record) {
        int updateResult = runSetResultMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetResult.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetResult record) {
        int updateResult = runSetResultMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetResult.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return updateResult;
    }
}
