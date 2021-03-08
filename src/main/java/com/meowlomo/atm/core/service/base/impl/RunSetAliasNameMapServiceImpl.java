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
import com.meowlomo.atm.core.mapper.RunSetAliasNameMapMapper;
import com.meowlomo.atm.core.model.RunSetAliasNameMap;
import com.meowlomo.atm.core.model.RunSetAliasNameMapExample;
import com.meowlomo.atm.core.service.base.RunSetAliasNameMapService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetAliasNameMapServiceImpl implements RunSetAliasNameMapService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetAliasNameMap> redisService;
    @Autowired
    private RunSetAliasNameMapMapper runSetAliasNameMapMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetAliasNameMapExample example) {
        return runSetAliasNameMapMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetAliasNameMapExample example) {
        int deleteResult = runSetAliasNameMapMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetAliasNameMapMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetAliasNameMap record) {
        int insertResult = runSetAliasNameMapMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetAliasNameMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetAliasNameMap record = records.get(i);
            if (runSetAliasNameMapMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetAliasNameMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetAliasNameMap record = records.get(i);
            if (runSetAliasNameMapMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetAliasNameMap record) {
        int insertResult = runSetAliasNameMapMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSetAliasNameMap> selectByExample(RunSetAliasNameMapExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, example);
        List<RunSetAliasNameMap> selectResult = new ArrayList<RunSetAliasNameMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetAliasNameMap>>() {
                    });
        }
        else {
            selectResult = runSetAliasNameMapMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetAliasNameMap> selectByExampleWithRowbounds(RunSetAliasNameMapExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, example, rowBounds);
        List<RunSetAliasNameMap> selectResult = new ArrayList<RunSetAliasNameMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetAliasNameMap>>() {
                    });
        }
        else {
            selectResult = runSetAliasNameMapMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetAliasNameMap selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, id);
        RunSetAliasNameMap selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunSetAliasNameMap>() {
                    });
        }
        else {
            selectResult = runSetAliasNameMapMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetAliasNameMap selectOneByExample(RunSetAliasNameMapExample example) {
        RunSetAliasNameMap selectResult = runSetAliasNameMapMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetAliasNameMap record, RunSetAliasNameMapExample example) {
        int updateResult = runSetAliasNameMapMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetAliasNameMap record, RunSetAliasNameMapExample example) {
        int updateResult = runSetAliasNameMapMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetAliasNameMap record) {
        int updateResult = runSetAliasNameMapMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetAliasNameMap record) {
        int updateResult = runSetAliasNameMapMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetAliasNameMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasNameMap.class));
        }
        return updateResult;
    }
}
