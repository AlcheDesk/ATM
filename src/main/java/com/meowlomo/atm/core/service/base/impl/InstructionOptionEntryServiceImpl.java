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
import com.meowlomo.atm.core.mapper.InstructionOptionEntryMapper;
import com.meowlomo.atm.core.model.InstructionOptionEntry;
import com.meowlomo.atm.core.model.InstructionOptionEntryExample;
import com.meowlomo.atm.core.service.base.InstructionOptionEntryService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionOptionEntryServiceImpl implements InstructionOptionEntryService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionOptionEntryMapper instructionOptionEntryMapper;
    @Autowired
    private RedisService<InstructionOptionEntry> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionOptionEntryExample example) {
        return instructionOptionEntryMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionOptionEntryExample example) {
        int deleteResult = instructionOptionEntryMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionOptionEntryMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionOptionEntry record) {
        int insertResult = instructionOptionEntryMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionOptionEntry> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionOptionEntry record = records.get(i);
            if (instructionOptionEntryMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionOptionEntry> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionOptionEntry record = records.get(i);
            if (instructionOptionEntryMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionOptionEntry record) {
        int insertResult = instructionOptionEntryMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionOptionEntry> selectByExample(InstructionOptionEntryExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOptionEntry.class, example);
        List<InstructionOptionEntry> selectResult = new ArrayList<InstructionOptionEntry>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionOptionEntry>>() {
                    });
        }
        else {
            selectResult = instructionOptionEntryMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionOptionEntry> selectByExampleWithRowbounds(InstructionOptionEntryExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOptionEntry.class, example, rowBounds);
        List<InstructionOptionEntry> selectResult = new ArrayList<InstructionOptionEntry>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionOptionEntry>>() {
                    });
        }
        else {
            selectResult = instructionOptionEntryMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionOptionEntry selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionOptionEntry.class, id);
        InstructionOptionEntry selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionOptionEntry>() {
                    });
        }
        else {
            selectResult = instructionOptionEntryMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionOptionEntry selectOneByExample(InstructionOptionEntryExample example) {
        InstructionOptionEntry selectResult = instructionOptionEntryMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionOptionEntry record, InstructionOptionEntryExample example) {
        int updateResult = instructionOptionEntryMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionOptionEntry record, InstructionOptionEntryExample example) {
        int updateResult = instructionOptionEntryMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionOptionEntry record) {
        int updateResult = instructionOptionEntryMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionOptionEntry record) {
        int updateResult = instructionOptionEntryMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionOptionEntry.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionOptionEntry.class));
        }
        return updateResult;
    }
}
