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
import com.meowlomo.atm.core.mapper.InstructionBundleEntryMapper;
import com.meowlomo.atm.core.model.InstructionBundleEntry;
import com.meowlomo.atm.core.model.InstructionBundleEntryExample;
import com.meowlomo.atm.core.service.base.InstructionBundleEntryService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionBundleEntryServiceImpl implements InstructionBundleEntryService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionBundleEntryMapper instructionBundleEntryMapper;
    @Autowired
    private RedisService<InstructionBundleEntry> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionBundleEntryExample example) {
        return instructionBundleEntryMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionBundleEntryExample example) {
        int deleteResult = instructionBundleEntryMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionBundleEntryMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionBundleEntry record) {
        int insertResult = instructionBundleEntryMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionBundleEntry> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionBundleEntry record = records.get(i);
            if (instructionBundleEntryMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionBundleEntry> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionBundleEntry record = records.get(i);
            if (instructionBundleEntryMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionBundleEntry record) {
        int insertResult = instructionBundleEntryMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(InstructionBundleEntryExample example) {
        int deleteResult = instructionBundleEntryMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = instructionBundleEntryMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return deleteResult;
    }

    @Override
    public List<InstructionBundleEntry> selectByExample(InstructionBundleEntryExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionBundleEntry.class, example);
        List<InstructionBundleEntry> selectResult = new ArrayList<InstructionBundleEntry>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionBundleEntry>>() {
                    });
        }
        else {
            selectResult = instructionBundleEntryMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionBundleEntry> selectByExampleWithRowbounds(InstructionBundleEntryExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionBundleEntry.class, example, rowBounds);
        List<InstructionBundleEntry> selectResult = new ArrayList<InstructionBundleEntry>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionBundleEntry>>() {
                    });
        }
        else {
            selectResult = instructionBundleEntryMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionBundleEntry selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionBundleEntry.class, id);
        InstructionBundleEntry selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionBundleEntry>() {
                    });
        }
        else {
            selectResult = instructionBundleEntryMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionBundleEntry selectOneByExample(InstructionBundleEntryExample example) {
        InstructionBundleEntry selectResult = instructionBundleEntryMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionBundleEntry record, InstructionBundleEntryExample example) {
        int updateResult = instructionBundleEntryMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionBundleEntry record, InstructionBundleEntryExample example) {
        int updateResult = instructionBundleEntryMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionBundleEntry record) {
        int updateResult = instructionBundleEntryMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionBundleEntry record) {
        int updateResult = instructionBundleEntryMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionBundleEntry.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundleEntry.class));
        }
        return updateResult;
    }
}
