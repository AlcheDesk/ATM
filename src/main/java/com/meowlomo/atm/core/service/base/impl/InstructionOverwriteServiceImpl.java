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
import com.meowlomo.atm.core.mapper.InstructionOverwriteMapper;
import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;
import com.meowlomo.atm.core.service.base.InstructionOverwriteService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionOverwriteServiceImpl implements InstructionOverwriteService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionOverwriteMapper instructionOverwriteMapper;
    @Autowired
    private RedisService<InstructionOverwrite> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionOverwriteExample example) {
        return instructionOverwriteMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionOverwriteExample example) {
        int deleteResult = instructionOverwriteMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionOverwriteMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionOverwrite.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionOverwrite record) {
        int insertResult = instructionOverwriteMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOverwrite.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionOverwrite> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionOverwrite record = records.get(i);
            if (instructionOverwriteMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionOverwrite.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionOverwrite> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionOverwrite record = records.get(i);
            if (instructionOverwriteMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionOverwrite.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionOverwrite record) {
        int insertResult = instructionOverwriteMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOverwrite.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionOverwrite> selectByExample(InstructionOverwriteExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOverwrite.class, example);
        List<InstructionOverwrite> selectResult = new ArrayList<InstructionOverwrite>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionOverwrite>>() {
                    });
        }
        else {
            selectResult = instructionOverwriteMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionOverwrite> selectByExampleWithRowbounds(InstructionOverwriteExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOverwrite.class, example, rowBounds);
        List<InstructionOverwrite> selectResult = new ArrayList<InstructionOverwrite>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionOverwrite>>() {
                    });
        }
        else {
            selectResult = instructionOverwriteMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionOverwrite selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOverwrite.class, id);
        InstructionOverwrite selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionOverwrite>() {
                    });
        }
        else {
            selectResult = instructionOverwriteMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionOverwrite selectOneByExample(InstructionOverwriteExample example) {
        InstructionOverwrite selectResult = instructionOverwriteMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOverwrite.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionOverwrite record, InstructionOverwriteExample example) {
        int updateResult = instructionOverwriteMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionOverwrite record, InstructionOverwriteExample example) {
        int updateResult = instructionOverwriteMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionOverwrite record) {
        int updateResult = instructionOverwriteMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionOverwrite.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionOverwrite record) {
        int updateResult = instructionOverwriteMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionOverwrite.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOverwrite.class));
        }
        return updateResult;
    }
}
