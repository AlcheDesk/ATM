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
import com.meowlomo.atm.core.mapper.InstructionTypeElementTypeLinkMapper;
import com.meowlomo.atm.core.model.InstructionTypeElementTypeLink;
import com.meowlomo.atm.core.model.InstructionTypeElementTypeLinkExample;
import com.meowlomo.atm.core.service.base.InstructionTypeElementTypeLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionTypeElementTypeLinkServiceImpl implements InstructionTypeElementTypeLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionTypeElementTypeLinkMapper instructionTypeElementTypeLinkMapper;
    @Autowired
    private RedisService<InstructionTypeElementTypeLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionTypeElementTypeLinkExample example) {
        return instructionTypeElementTypeLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionTypeElementTypeLinkExample example) {
        int deleteResult = instructionTypeElementTypeLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionTypeElementTypeLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionTypeElementTypeLink record) {
        int insertResult = instructionTypeElementTypeLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionTypeElementTypeLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionTypeElementTypeLink record = records.get(i);
            if (instructionTypeElementTypeLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionTypeElementTypeLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionTypeElementTypeLink record = records.get(i);
            if (instructionTypeElementTypeLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionTypeElementTypeLink record) {
        int insertResult = instructionTypeElementTypeLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionTypeElementTypeLink> selectByExample(InstructionTypeElementTypeLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, example);
        List<InstructionTypeElementTypeLink> selectResult = new ArrayList<InstructionTypeElementTypeLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionTypeElementTypeLink>>() {
                    });
        }
        else {
            selectResult = instructionTypeElementTypeLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionTypeElementTypeLink> selectByExampleWithRowbounds(
            InstructionTypeElementTypeLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, example, rowBounds);
        List<InstructionTypeElementTypeLink> selectResult = new ArrayList<InstructionTypeElementTypeLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionTypeElementTypeLink>>() {
                    });
        }
        else {
            selectResult = instructionTypeElementTypeLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionTypeElementTypeLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, id);
        InstructionTypeElementTypeLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionTypeElementTypeLink>() {
                    });
        }
        else {
            selectResult = instructionTypeElementTypeLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionTypeElementTypeLink selectOneByExample(InstructionTypeElementTypeLinkExample example) {
        InstructionTypeElementTypeLink selectResult = instructionTypeElementTypeLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionTypeElementTypeLink record, InstructionTypeElementTypeLinkExample example) {
        int updateResult = instructionTypeElementTypeLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionTypeElementTypeLink record,
            InstructionTypeElementTypeLinkExample example) {
        int updateResult = instructionTypeElementTypeLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionTypeElementTypeLink record) {
        int updateResult = instructionTypeElementTypeLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionTypeElementTypeLink record) {
        int updateResult = instructionTypeElementTypeLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(InstructionTypeElementTypeLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeElementTypeLink.class));
        }
        return updateResult;
    }
}
