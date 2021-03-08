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
import com.meowlomo.atm.core.mapper.DevExecutionLogMapper;
import com.meowlomo.atm.core.mapper.ProdExecutionLogMapper;
import com.meowlomo.atm.core.model.ExecutionLog;
import com.meowlomo.atm.core.model.ExecutionLogExample;
import com.meowlomo.atm.core.service.base.ExecutionLogService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ExecutionLogServiceImpl implements ExecutionLogService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DevExecutionLogMapper devExecutionLogMapper;
    @Autowired
    private ProdExecutionLogMapper prodExecutionLogMapper;
    @Autowired
    private RedisService<ExecutionLog> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ExecutionLogExample example, String mode) {
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            return devExecutionLogMapper.countByExample(example);
        }
        else {
            return prodExecutionLogMapper.countByExample(example);
        }
    }

    @Override
    public int deleteByExample(ExecutionLogExample example, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devExecutionLogMapper.deleteByExample(example);
        }
        else {
            deleteResult = prodExecutionLogMapper.deleteByExample(example);
        }
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devExecutionLogMapper.deleteByPrimaryKey(id);
        }
        else {
            deleteResult = prodExecutionLogMapper.deleteByPrimaryKey(id);
        }
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ExecutionLog.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ExecutionLog record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devExecutionLogMapper.insert(record);
        }
        else {
            insertResult = prodExecutionLogMapper.insert(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ExecutionLog.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ExecutionLog> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ExecutionLog record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devExecutionLogMapper.insert(record);
            }
            else {
                insertResult = prodExecutionLogMapper.insert(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ExecutionLog.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ExecutionLog> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ExecutionLog record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devExecutionLogMapper.insertSelective(record);
            }
            else {
                insertResult = prodExecutionLogMapper.insertSelective(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ExecutionLog.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ExecutionLog record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devExecutionLogMapper.insertSelective(record);
        }
        else {
            insertResult = prodExecutionLogMapper.insertSelective(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ExecutionLog.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return insertResult;
    }

    @Override
    public List<ExecutionLog> selectByExample(ExecutionLogExample example, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(ExecutionLog.class, example, mode);
        List<ExecutionLog> selectResult = new ArrayList<ExecutionLog>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ExecutionLog>>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devExecutionLogMapper.selectByExample(example);
            }
            else {
                selectResult = prodExecutionLogMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ExecutionLog> selectByExampleWithRowbounds(ExecutionLogExample example, RowBounds rowBounds,
            String mode) {
        String redisKey = cacheKeyGenerator.generateKey(ExecutionLog.class, example, rowBounds, mode);
        List<ExecutionLog> selectResult = new ArrayList<ExecutionLog>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ExecutionLog>>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devExecutionLogMapper.selectByExample(example);
            }
            else {
                selectResult = prodExecutionLogMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ExecutionLog selectByPrimaryKey(Long id, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(ExecutionLog.class, id, mode);
        ExecutionLog selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ExecutionLog>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devExecutionLogMapper.selectByPrimaryKey(id);
            }
            else {
                selectResult = prodExecutionLogMapper.selectByPrimaryKey(id);
            }
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ExecutionLog selectOneByExample(ExecutionLogExample example, String mode) {
        ExecutionLog selectResult = null;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            selectResult = devExecutionLogMapper.selectOneByExample(example);
        }
        else {
            selectResult = prodExecutionLogMapper.selectOneByExample(example);
        }
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ExecutionLog.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ExecutionLog record, ExecutionLogExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devExecutionLogMapper.updateByExample(record, example);
        }
        else {
            updateResult = prodExecutionLogMapper.updateByExample(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ExecutionLog record, ExecutionLogExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devExecutionLogMapper.updateByExampleSelective(record, example);
        }
        else {
            updateResult = prodExecutionLogMapper.updateByExampleSelective(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ExecutionLog record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devExecutionLogMapper.updateByPrimaryKey(record);
        }
        else {
            updateResult = prodExecutionLogMapper.updateByPrimaryKey(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ExecutionLog.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ExecutionLog record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devExecutionLogMapper.updateByPrimaryKeySelective(record);
        }
        else {
            updateResult = prodExecutionLogMapper.updateByPrimaryKeySelective(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ExecutionLog.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ExecutionLog.class));
        }
        return updateResult;
    }
}
