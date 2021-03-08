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
import com.meowlomo.atm.core.mapper.InstructionTypeMapper;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.InstructionTypeExample;
import com.meowlomo.atm.core.service.base.InstructionTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionTypeServiceImpl implements InstructionTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionTypeMapper instructionTypeMapper;
    @Autowired
    private RedisService<InstructionType> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionTypeExample example) {
        return instructionTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionTypeExample example) {
        int deleteResult = instructionTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionType record) {
        int insertResult = instructionTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionType record = records.get(i);
            if (instructionTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionType record = records.get(i);
            if (instructionTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionType record) {
        int insertResult = instructionTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionType> selectByExample(InstructionTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionType.class, example);
        List<InstructionType> selectResult = new ArrayList<InstructionType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionType>>() {
                    });
        }
        else {
            selectResult = instructionTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionType> selectByExampleWithRowbounds(InstructionTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionType.class, example, rowBounds);
        List<InstructionType> selectResult = new ArrayList<InstructionType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionType>>() {
                    });
        }
        else {
            selectResult = instructionTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionType.class, id);
        InstructionType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionType>() {
                    });
        }
        else {
            selectResult = instructionTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionType selectOneByExample(InstructionTypeExample example) {
        InstructionType selectResult = instructionTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionType.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionType record, InstructionTypeExample example) {
        int updateResult = instructionTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionType record, InstructionTypeExample example) {
        int updateResult = instructionTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionType record) {
        int updateResult = instructionTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionType record) {
        int updateResult = instructionTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionType.class));
        }
        return updateResult;
    }
}
