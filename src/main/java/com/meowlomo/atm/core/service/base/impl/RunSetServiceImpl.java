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
import com.meowlomo.atm.core.mapper.RunSetMapper;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;
import com.meowlomo.atm.core.service.base.RunSetService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetServiceImpl implements RunSetService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSet> redisService;
    @Autowired
    private RunSetMapper runSetMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetExample example) {
        return runSetMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetExample example) {
        int deleteResult = runSetMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSet.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSet record) {
        int insertResult = runSetMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSet.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSet> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSet record = records.get(i);
            if (runSetMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSet.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSet> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSet record = records.get(i);
            if (runSetMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSet.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSet record) {
        int insertResult = runSetMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSet.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(RunSetExample example) {
        int deleteResult = runSetMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = runSetMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSet.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return deleteResult;
    }

    @Override
    public List<RunSet> selectByExample(RunSetExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSet.class, example);
        List<RunSet> selectResult = new ArrayList<RunSet>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<RunSet>>() {
            });
        }
        else {
            selectResult = runSetMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSet> selectByExampleWithRowbounds(RunSetExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSet.class, example, rowBounds);
        List<RunSet> selectResult = new ArrayList<RunSet>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<RunSet>>() {
            });
        }
        else {
            selectResult = runSetMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSet selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSet.class, id);
        RunSet selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<RunSet>() {
            });
        }
        else {
            selectResult = runSetMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSet selectOneByExample(RunSetExample example) {
        RunSet selectResult = runSetMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSet.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSet record, RunSetExample example) {
        int updateResult = runSetMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSet record, RunSetExample example) {
        int updateResult = runSetMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSet record) {
        int updateResult = runSetMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSet.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSet record) {
        int updateResult = runSetMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSet.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSet.class));
        }
        return updateResult;
    }
}
