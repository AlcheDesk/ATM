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
import com.meowlomo.atm.core.mapper.DevStepLogMapper;
import com.meowlomo.atm.core.mapper.ProdStepLogMapper;
import com.meowlomo.atm.core.model.StepLog;
import com.meowlomo.atm.core.model.StepLogExample;
import com.meowlomo.atm.core.service.base.StepLogService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StepLogServiceImpl implements StepLogService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DevStepLogMapper devStepLogMapper;
    @Autowired
    private ProdStepLogMapper prodStepLogMapper;
    @Autowired
    private RedisService<StepLog> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(StepLogExample example, String mode) {
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            return devStepLogMapper.countByExample(example);
        }
        else {
            return prodStepLogMapper.countByExample(example);
        }
    }

    @Override
    public int deleteByExample(StepLogExample example, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devStepLogMapper.deleteByExample(example);
        }
        else {
            deleteResult = prodStepLogMapper.deleteByExample(example);
        }
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devStepLogMapper.deleteByPrimaryKey(id);
        }
        else {
            deleteResult = prodStepLogMapper.deleteByPrimaryKey(id);
        }
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(StepLog.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(StepLog record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devStepLogMapper.insert(record);
        }
        else {
            insertResult = prodStepLogMapper.insert(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(StepLog.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<StepLog> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            StepLog record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devStepLogMapper.insert(record);
            }
            else {
                insertResult = prodStepLogMapper.insert(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(StepLog.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<StepLog> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            StepLog record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devStepLogMapper.insertSelective(record);
            }
            else {
                insertResult = prodStepLogMapper.insertSelective(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(StepLog.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(StepLog record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devStepLogMapper.insertSelective(record);
        }
        else {
            insertResult = prodStepLogMapper.insertSelective(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(StepLog.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return insertResult;
    }

    @Override
    public List<StepLog> selectByExample(StepLogExample example, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(StepLog.class, example, mode);
        List<StepLog> selectResult = new ArrayList<StepLog>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<StepLog>>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devStepLogMapper.selectByExample(example);
            }
            else {
                selectResult = prodStepLogMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(cacheKeyGenerator.generateKey(StepLog.class, example), selectResult);
            }
        }
        return selectResult;
    }

    @Override
    public List<StepLog> selectByExampleWithRowbounds(StepLogExample example, RowBounds rowBounds, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(StepLog.class, example, rowBounds, mode);
        List<StepLog> selectResult = new ArrayList<StepLog>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<StepLog>>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devStepLogMapper.selectByExample(example);
            }
            else {
                selectResult = prodStepLogMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public StepLog selectByPrimaryKey(Long id, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(StepLog.class, id, mode);
        StepLog selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<StepLog>() {
            });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devStepLogMapper.selectByPrimaryKey(id);
            }
            else {
                selectResult = prodStepLogMapper.selectByPrimaryKey(id);
            }
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public StepLog selectOneByExample(StepLogExample example, String mode) {
        StepLog selectResult = null;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            selectResult = devStepLogMapper.selectOneByExample(example);
        }
        else {
            selectResult = prodStepLogMapper.selectOneByExample(example);
        }
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(StepLog.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(StepLog record, StepLogExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devStepLogMapper.updateByExample(record, example);
        }
        else {
            updateResult = prodStepLogMapper.updateByExample(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(StepLog record, StepLogExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devStepLogMapper.updateByExampleSelective(record, example);
        }
        else {
            updateResult = prodStepLogMapper.updateByExampleSelective(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(StepLog record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devStepLogMapper.updateByPrimaryKey(record);
        }
        else {
            updateResult = prodStepLogMapper.updateByPrimaryKey(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(StepLog.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(StepLog record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devStepLogMapper.updateByPrimaryKeySelective(record);
        }
        else {
            updateResult = prodStepLogMapper.updateByPrimaryKeySelective(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(StepLog.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(StepLog.class));
        }
        return updateResult;
    }
}
