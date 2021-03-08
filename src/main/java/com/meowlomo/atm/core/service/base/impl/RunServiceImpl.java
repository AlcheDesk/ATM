package com.meowlomo.atm.core.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.RunMapper;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.model.RunExecutionInfo;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.service.base.RunService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunServiceImpl implements RunService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Run> redisService;
    @Autowired
    private RunMapper runMapper;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunExample example) {
        return runMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunExample example) {
        int deleteResult = runMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Run.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Run record) {
        int insertResult = runMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Run.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService
                    .deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetResult.class, record.getRunSetResultId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Run> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Run record = records.get(i);
            if (runMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Run.class, record.getId()), record);
                redisService.deleteByKeyPattern(
                        cacheKeyGenerator.generateKey(RunSetResult.class, record.getRunSetResultId()));
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Run> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Run record = records.get(i);
            if (runMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Run.class, record.getId()), record);
                redisService.deleteByKeyPattern(
                        cacheKeyGenerator.generateKey(RunSetResult.class, record.getRunSetResultId()));
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Run record) {
        int insertResult = runMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Run.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService
                    .deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetResult.class, record.getRunSetResultId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return insertResult;
    }

    @Override
    public List<Run> selectByExample(RunExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Run.class, example);
        List<Run> selectResult = new ArrayList<Run>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Run>>() {
            });
        }
        else {
            selectResult = runMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, 1, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Run> selectByExampleWithRowbounds(RunExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Run.class, example, rowBounds);
        List<Run> selectResult = new ArrayList<Run>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Run>>() {
            });
        }
        else {
            selectResult = runMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, 1, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Run selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Run.class, id);
        Run selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Run>() {
            });
        }
        else {
            selectResult = runMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, 1, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Run selectOneByExample(RunExample example) {
        Run selectResult = runMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Run.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Run record, RunExample example) {
        int updateResult = runMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Run.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Run record, RunExample example) {
        int updateResult = runMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Run.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Run record) {
        int updateResult = runMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Run.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Run record) {
        int updateResult = runMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Run.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Run.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResult.class));
        }
        return updateResult;
    }
}
