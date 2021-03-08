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
import com.meowlomo.atm.core.mapper.InstructionActionMapper;
import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionActionExample;
import com.meowlomo.atm.core.service.base.InstructionActionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionActionServiceImpl implements InstructionActionService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionActionMapper instructionActionMapper;
    @Autowired
    private RedisService<InstructionAction> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionActionExample example) {
        return instructionActionMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionActionExample example) {
        int deleteResult = instructionActionMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionActionMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionAction.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionAction record) {
        int insertResult = instructionActionMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionAction.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionAction> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionAction record = records.get(i);
            if (instructionActionMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionAction.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionAction> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionAction record = records.get(i);
            if (instructionActionMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionAction.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionAction record) {
        int insertResult = instructionActionMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionAction.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionAction> selectByExample(InstructionActionExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionAction.class, example);
        List<InstructionAction> selectResult = new ArrayList<InstructionAction>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionAction>>() {
                    });
        }
        else {
            selectResult = instructionActionMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionAction> selectByExampleWithRowbounds(InstructionActionExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionAction.class, example, rowBounds);
        List<InstructionAction> selectResult = new ArrayList<InstructionAction>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionAction>>() {
                    });
        }
        else {
            selectResult = instructionActionMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionAction selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionAction.class, id);
        InstructionAction selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionAction>() {
                    });
        }
        else {
            selectResult = instructionActionMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionAction selectOneByExample(InstructionActionExample example) {
        InstructionAction selectResult = instructionActionMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionAction.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionAction record, InstructionActionExample example) {
        int updateResult = instructionActionMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionAction record, InstructionActionExample example) {
        int updateResult = instructionActionMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionAction record) {
        int updateResult = instructionActionMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionAction.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionAction record) {
        int updateResult = instructionActionMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionAction.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionAction.class));
        }
        return updateResult;
    }
}
