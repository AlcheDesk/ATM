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
import com.meowlomo.atm.core.mapper.DevInstructionResultMapper;
import com.meowlomo.atm.core.mapper.ProdInstructionResultMapper;
import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExecutionInfo;
import com.meowlomo.atm.core.service.base.InstructionResultService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionResultServiceImpl implements InstructionResultService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DevInstructionResultMapper devInstructionResultMapper;
    @Autowired
    private ProdInstructionResultMapper prodInstructionResultMapper;
    @Autowired
    private RedisService<InstructionResult> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionResultExample example, String mode) {
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            return devInstructionResultMapper.countByExample(example);
        }
        else {
            return prodInstructionResultMapper.countByExample(example);
        }
    }

    @Override
    public int deleteByExample(InstructionResultExample example, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devInstructionResultMapper.deleteByExample(example);
        }
        else {
            deleteResult = prodInstructionResultMapper.deleteByExample(example);
        }
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devInstructionResultMapper.deleteByPrimaryKey(id);
        }
        else {
            deleteResult = prodInstructionResultMapper.deleteByPrimaryKey(id);
        }
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionResult.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionResult record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devInstructionResultMapper.insert(record);
        }
        else {
            insertResult = prodInstructionResultMapper.insert(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionResult.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));

        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionResult> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionResult record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devInstructionResultMapper.insert(record);
            }
            else {
                insertResult = prodInstructionResultMapper.insert(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionResult.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionResult> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionResult record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devInstructionResultMapper.insertSelective(record);
            }
            else {
                insertResult = prodInstructionResultMapper.insertSelective(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionResult.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionResult record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devInstructionResultMapper.insertSelective(record);
        }
        else {
            insertResult = prodInstructionResultMapper.insertSelective(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionResult.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionResult> selectByExample(InstructionResultExample example, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionResult.class, example, mode);
        List<InstructionResult> selectResult = new ArrayList<InstructionResult>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionResult>>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devInstructionResultMapper.selectByExample(example);
            }
            else {
                selectResult = prodInstructionResultMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionResult> selectByExampleWithRowbounds(InstructionResultExample example, RowBounds rowBounds,
            String mode) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionResult.class, example, rowBounds, mode);
        List<InstructionResult> selectResult = new ArrayList<InstructionResult>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionResult>>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devInstructionResultMapper.selectByExample(example);
            }
            else {
                selectResult = prodInstructionResultMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionResult selectByPrimaryKey(Long id, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionResult.class, id, mode);
        InstructionResult selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionResult>() {
                    });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devInstructionResultMapper.selectByPrimaryKey(id);
            }
            else {
                selectResult = prodInstructionResultMapper.selectByPrimaryKey(id);
            }
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionResult selectOneByExample(InstructionResultExample example, String mode) {
        InstructionResult selectResult = null;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            selectResult = devInstructionResultMapper.selectOneByExample(example);
        }
        else {
            selectResult = prodInstructionResultMapper.selectOneByExample(example);
        }
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionResult.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionResult record, InstructionResultExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devInstructionResultMapper.updateByExample(record, example);
        }
        else {
            updateResult = prodInstructionResultMapper.updateByExample(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Run.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionResult record, InstructionResultExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devInstructionResultMapper.updateByExampleSelective(record, example);
        }
        else {
            updateResult = prodInstructionResultMapper.updateByExampleSelective(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Run.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionResult record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devInstructionResultMapper.updateByPrimaryKey(record);
        }
        else {
            updateResult = prodInstructionResultMapper.updateByPrimaryKey(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionResult.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Run.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionResult record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devInstructionResultMapper.updateByPrimaryKeySelective(record);
        }
        else {
            updateResult = prodInstructionResultMapper.updateByPrimaryKeySelective(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionResult.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionResult.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunExecutionInfo.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Run.class));
        }
        return updateResult;
    }
}
