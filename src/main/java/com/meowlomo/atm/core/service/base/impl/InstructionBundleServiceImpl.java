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
import com.meowlomo.atm.core.mapper.InstructionBundleMapper;
import com.meowlomo.atm.core.model.InstructionBundle;
import com.meowlomo.atm.core.model.InstructionBundleExample;
import com.meowlomo.atm.core.service.base.InstructionBundleService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionBundleServiceImpl implements InstructionBundleService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionBundleMapper instructionBundleMapper;
    @Autowired
    private RedisService<InstructionBundle> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionBundleExample example) {
        return instructionBundleMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionBundleExample example) {
        int deleteResult = instructionBundleMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionBundleMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionBundle.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionBundle record) {
        int insertResult = instructionBundleMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundle.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionBundle> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionBundle record = records.get(i);
            if (instructionBundleMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundle.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionBundle> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionBundle record = records.get(i);
            if (instructionBundleMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundle.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionBundle record) {
        int insertResult = instructionBundleMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundle.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(InstructionBundleExample example) {
        int deleteResult = instructionBundleMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = instructionBundleMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionBundle.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return deleteResult;
    }

    @Override
    public List<InstructionBundle> selectByExample(InstructionBundleExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionBundle.class, example);
        List<InstructionBundle> selectResult = new ArrayList<InstructionBundle>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionBundle>>() {
                    });
        }
        else {
            selectResult = instructionBundleMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionBundle> selectByExampleWithRowbounds(InstructionBundleExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionBundle.class, example, rowBounds);
        List<InstructionBundle> selectResult = new ArrayList<InstructionBundle>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionBundle>>() {
                    });
        }
        else {
            selectResult = instructionBundleMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionBundle selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionBundle.class, id);
        InstructionBundle selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionBundle>() {
                    });
        }
        else {
            selectResult = instructionBundleMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionBundle selectOneByExample(InstructionBundleExample example) {
        InstructionBundle selectResult = instructionBundleMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionBundle.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionBundle record, InstructionBundleExample example) {
        int updateResult = instructionBundleMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionBundle record, InstructionBundleExample example) {
        int updateResult = instructionBundleMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionBundle record) {
        int updateResult = instructionBundleMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionBundle.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionBundle record) {
        int updateResult = instructionBundleMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionBundle.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionBundle.class));
        }
        return updateResult;
    }
}
