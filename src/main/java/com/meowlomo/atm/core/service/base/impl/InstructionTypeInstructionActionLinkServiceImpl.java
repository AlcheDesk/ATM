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
import com.meowlomo.atm.core.mapper.InstructionTypeInstructionActionLinkMapper;
import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionActionExample;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.InstructionTypeExample;
import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLink;
import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLinkExample;
import com.meowlomo.atm.core.service.base.InstructionActionService;
import com.meowlomo.atm.core.service.base.InstructionTypeInstructionActionLinkService;
import com.meowlomo.atm.core.service.base.InstructionTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionTypeInstructionActionLinkServiceImpl implements InstructionTypeInstructionActionLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionActionService instructionActionService;
    @Autowired
    private InstructionTypeInstructionActionLinkMapper instructionTypeInstructionActionLinkMapper;
    @Autowired
    private InstructionTypeService instructionTypeService;
    @Autowired
    private RedisService<InstructionTypeInstructionActionLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionTypeInstructionActionLinkExample example) {
        return instructionTypeInstructionActionLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionTypeInstructionActionLinkExample example) {
        int deleteResult = instructionTypeInstructionActionLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionTypeInstructionActionLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService
                    .deleteByKeyPattern(cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, id));
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(InstructionTypeInstructionActionLink record) {
        int insertResult = instructionTypeInstructionActionLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, record.getId()), record);
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<InstructionTypeInstructionActionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionTypeInstructionActionLink record = records.get(i);
            if (instructionTypeInstructionActionLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<InstructionTypeInstructionActionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            InstructionTypeInstructionActionLink record = records.get(i);
            if (instructionTypeInstructionActionLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(InstructionTypeInstructionActionLink record) {
        int insertResult = instructionTypeInstructionActionLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, record.getId()), record);
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionAction> listInstructionActionByInstructionTypePrimaryKey(Long instructionTypeId) {
        InstructionTypeInstructionActionLinkExample linkExample = new InstructionTypeInstructionActionLinkExample();
        linkExample.createCriteria()
                .andInstructionTypeEqualTo(instructionTypeService.selectByPrimaryKey(instructionTypeId).getName());
        List<InstructionTypeInstructionActionLink> targetLinks = instructionTypeInstructionActionLinkMapper
                .selectByExample(linkExample);
        List<String> targetNames = new ArrayList<String>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetNames.add(targetLinks.get(i).getInstructionAction());
        }
        if (targetNames.size() == 0) {
            return new ArrayList<InstructionAction>();
        }
        else {
            InstructionActionExample targetExample = new InstructionActionExample();
            targetExample.createCriteria().andNameIn(targetNames);
            return instructionActionService.selectByExample(targetExample);
        }
    }

    @Override
    public List<InstructionType> listInstructionTypeByInstructionActionPrimaryKey(Long instructionActionId) {
        InstructionTypeInstructionActionLinkExample linkExample = new InstructionTypeInstructionActionLinkExample();
        linkExample.createCriteria().andInstructionActionEqualTo(
                instructionActionService.selectByPrimaryKey(instructionActionId).getName());
        List<InstructionTypeInstructionActionLink> targetLinks = instructionTypeInstructionActionLinkMapper
                .selectByExample(linkExample);
        List<String> targetNames = new ArrayList<String>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetNames.add(targetLinks.get(i).getInstructionType());
        }
        if (targetNames.size() == 0) {
            return new ArrayList<InstructionType>();
        }
        else {
            InstructionTypeExample targetExample = new InstructionTypeExample();
            targetExample.createCriteria().andNameIn(targetNames);
            return instructionTypeService.selectByExample(targetExample);
        }
    }

    @Override
    public List<InstructionTypeInstructionActionLink> selectByExample(
            InstructionTypeInstructionActionLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, example);
        List<InstructionTypeInstructionActionLink> selectResult = new ArrayList<InstructionTypeInstructionActionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionTypeInstructionActionLink>>() {
                    });
        }
        else {
            selectResult = instructionTypeInstructionActionLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<InstructionTypeInstructionActionLink> selectByExampleWithRowbounds(
            InstructionTypeInstructionActionLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, example, rowBounds);
        List<InstructionTypeInstructionActionLink> selectResult = new ArrayList<InstructionTypeInstructionActionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<InstructionTypeInstructionActionLink>>() {
                    });
        }
        else {
            selectResult = instructionTypeInstructionActionLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionTypeInstructionActionLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, id);
        InstructionTypeInstructionActionLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<InstructionTypeInstructionActionLink>() {
                    });
        }
        else {
            selectResult = instructionTypeInstructionActionLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public InstructionTypeInstructionActionLink selectOneByExample(
            InstructionTypeInstructionActionLinkExample example) {
        InstructionTypeInstructionActionLink selectResult = instructionTypeInstructionActionLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(InstructionTypeInstructionActionLink record,
            InstructionTypeInstructionActionLinkExample example) {
        int updateResult = instructionTypeInstructionActionLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(InstructionTypeInstructionActionLink record,
            InstructionTypeInstructionActionLinkExample example) {
        int updateResult = instructionTypeInstructionActionLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(InstructionTypeInstructionActionLink record) {
        int updateResult = instructionTypeInstructionActionLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService
                    .delete(cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, record.getId()));
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(InstructionTypeInstructionActionLink record) {
        int updateResult = instructionTypeInstructionActionLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService
                    .delete(cacheKeyGenerator.generateKey(InstructionTypeInstructionActionLink.class, record.getId()));
            redisService
                    .deleteListPattern(cacheKeyGenerator.generateClassKey(InstructionTypeInstructionActionLink.class));
        }
        return updateResult;
    }
}
