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
import com.meowlomo.atm.core.mapper.InstructionActionInstructionOptionLinkMapper;
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLink;
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLinkExample;
import com.meowlomo.atm.core.model.InstructionOptionMap;
import com.meowlomo.atm.core.service.base.InstructionActionInstructionOptionLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionActionInstructionOptionLinkServiceImpl
        implements InstructionActionInstructionOptionLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionActionInstructionOptionLinkMapper instructionActionInstructionOptionLinkMapper;
    @Autowired
    private RedisService<InstructionActionInstructionOptionLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionActionInstructionOptionLinkExample example) {
        return instructionActionInstructionOptionLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionActionInstructionOptionLinkExample example) {
        int deleteResult = instructionActionInstructionOptionLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionActionInstructionOptionLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(
                    cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, id));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionActionInstructionOptionLink record) {
        int insertResult = instructionActionInstructionOptionLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionActionInstructionOptionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionActionInstructionOptionLink record = records.get(i);
            if (instructionActionInstructionOptionLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionActionInstructionOptionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionActionInstructionOptionLink record = records.get(i);
            if (instructionActionInstructionOptionLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionActionInstructionOptionLink record) {
        int insertResult = instructionActionInstructionOptionLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionActionInstructionOptionLink> selectByExample(
            InstructionActionInstructionOptionLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, example);
        List<InstructionActionInstructionOptionLink> selectResult = new ArrayList<InstructionActionInstructionOptionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionActionInstructionOptionLink>>() {
                    });
        }
        else {
            selectResult = instructionActionInstructionOptionLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionActionInstructionOptionLink> selectByExampleWithRowbounds(
            InstructionActionInstructionOptionLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, example,
                rowBounds);
        List<InstructionActionInstructionOptionLink> selectResult = new ArrayList<InstructionActionInstructionOptionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionActionInstructionOptionLink>>() {
                    });
        }
        else {
            selectResult = instructionActionInstructionOptionLinkMapper.selectByExampleWithRowbounds(example,
                    rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionActionInstructionOptionLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, id);
        InstructionActionInstructionOptionLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionActionInstructionOptionLink>() {
                    });
        }
        else {
            selectResult = instructionActionInstructionOptionLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionActionInstructionOptionLink selectOneByExample(
            InstructionActionInstructionOptionLinkExample example) {
        InstructionActionInstructionOptionLink selectResult = instructionActionInstructionOptionLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionActionInstructionOptionLink record,
            InstructionActionInstructionOptionLinkExample example) {
        int updateResult = instructionActionInstructionOptionLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionActionInstructionOptionLink record,
            InstructionActionInstructionOptionLinkExample example) {
        int updateResult = instructionActionInstructionOptionLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionActionInstructionOptionLink record) {
        int updateResult = instructionActionInstructionOptionLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(
                    cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, record.getId()));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionActionInstructionOptionLink record) {
        int updateResult = instructionActionInstructionOptionLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(
                    cacheKeyGenerator.generateKey(InstructionActionInstructionOptionLink.class, record.getId()));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(InstructionActionInstructionOptionLink.class));
        }
        return updateResult;
    }
}
