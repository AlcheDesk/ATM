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
import com.meowlomo.atm.core.mapper.InstructionOptionMapper;
import com.meowlomo.atm.core.model.InstructionOption;
import com.meowlomo.atm.core.model.InstructionOptionExample;
import com.meowlomo.atm.core.model.InstructionOptionMap;
import com.meowlomo.atm.core.service.base.InstructionOptionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionOptionServiceImpl implements InstructionOptionService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionOptionMapper instructionOptionMapper;
    @Autowired
    private RedisService<InstructionOption> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionOptionExample example) {
        return instructionOptionMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionOptionExample example) {
        int deleteResult = instructionOptionMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionOptionMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionOption.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionOption record) {
        int insertResult = instructionOptionMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOption.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionOption> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionOption record = records.get(i);
            if (instructionOptionMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionOption.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionOption> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionOption record = records.get(i);
            if (instructionOptionMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionOption.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionOption record) {
        int insertResult = instructionOptionMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOption.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionOption> selectByExample(InstructionOptionExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOption.class, example);
        List<InstructionOption> selectResult = new ArrayList<InstructionOption>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionOption>>() {
                    });
        }
        else {
            selectResult = instructionOptionMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionOption> selectByExampleWithRowbounds(InstructionOptionExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOption.class, example, rowBounds);
        List<InstructionOption> selectResult = new ArrayList<InstructionOption>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionOption>>() {
                    });
        }
        else {
            selectResult = instructionOptionMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionOption selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOption.class, id);
        InstructionOption selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionOption>() {
                    });
        }
        else {
            selectResult = instructionOptionMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionOption selectOneByExample(InstructionOptionExample example) {
        InstructionOption selectResult = instructionOptionMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOption.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionOption record, InstructionOptionExample example) {
        int updateResult = instructionOptionMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOption.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionOption record, InstructionOptionExample example) {
        int updateResult = instructionOptionMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOption.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionOption record) {
        int updateResult = instructionOptionMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionOption.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionOption record) {
        int updateResult = instructionOptionMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionOption.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOption.class));
        }
        return updateResult;
    }
}
