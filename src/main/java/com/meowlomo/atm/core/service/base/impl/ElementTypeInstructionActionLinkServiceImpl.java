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
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.mapper.ElementTypeInstructionActionLinkMapper;
import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeExample;
import com.meowlomo.atm.core.model.ElementTypeInstructionActionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionActionLinkExample;
import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionActionExample;
import com.meowlomo.atm.core.service.base.ElementTypeInstructionActionLinkService;
import com.meowlomo.atm.core.service.base.ElementTypeService;
import com.meowlomo.atm.core.service.base.InstructionActionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementTypeInstructionActionLinkServiceImpl implements ElementTypeInstructionActionLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ElementTypeInstructionActionLinkMapper elementTypeInstructionActionLinkMapper;
    @Autowired
    private ElementTypeService elementTypeService;
    @Autowired
    private InstructionActionService instructionActionService;
    @Autowired
    private RedisService<ElementTypeInstructionActionLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ElementTypeInstructionActionLinkExample example) {
        return elementTypeInstructionActionLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ElementTypeInstructionActionLinkExample example) {
        int deleteResult = elementTypeInstructionActionLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = elementTypeInstructionActionLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ElementTypeInstructionActionLink record) {
        int insertResult = elementTypeInstructionActionLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ElementTypeInstructionActionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementTypeInstructionActionLink record = records.get(i);
            if (elementTypeInstructionActionLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ElementTypeInstructionActionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementTypeInstructionActionLink record = records.get(i);
            if (elementTypeInstructionActionLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ElementTypeInstructionActionLink record) {
        int insertResult = elementTypeInstructionActionLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<ElementType> listElementTypeByInstructionActionPrimaryKey(Long instructionActionId) {
        ElementTypeInstructionActionLinkExample linkExample = new ElementTypeInstructionActionLinkExample();
        linkExample.createCriteria()
                .andInstructionActionEqualTo(RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId));
        List<ElementTypeInstructionActionLink> targetLinks = elementTypeInstructionActionLinkMapper
                .selectByExample(linkExample);
        List<String> targetIds = new ArrayList<String>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getElementType());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<ElementType>();
        }
        else {
            ElementTypeExample targetExample = new ElementTypeExample();
            targetExample.createCriteria().andNameIn(targetIds);
            return elementTypeService.selectByExample(targetExample);
        }
    }

    @Override
    public List<InstructionAction> listInstructionActionByElementTypePrimaryKey(Long elementTypeId) {
        ElementTypeInstructionActionLinkExample linkExample = new ElementTypeInstructionActionLinkExample();
        linkExample.createCriteria().andElementTypeEqualTo(RuntimeVariables.getIdToElementTypeMap().get(elementTypeId));
        List<ElementTypeInstructionActionLink> targetLinks = elementTypeInstructionActionLinkMapper
                .selectByExample(linkExample);
        List<String> targetIds = new ArrayList<String>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getInstructionAction());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<InstructionAction>();
        }
        else {
            InstructionActionExample targetExample = new InstructionActionExample();
            targetExample.createCriteria().andNameIn(targetIds);
            return instructionActionService.selectByExample(targetExample);
        }
    }

    @Override
    public List<ElementTypeInstructionActionLink> selectByExample(ElementTypeInstructionActionLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, example);
        List<ElementTypeInstructionActionLink> selectResult = new ArrayList<ElementTypeInstructionActionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementTypeInstructionActionLink>>() {
                    });
        }
        else {
            selectResult = elementTypeInstructionActionLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ElementTypeInstructionActionLink> selectByExampleWithRowbounds(
            ElementTypeInstructionActionLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, example, rowBounds);
        List<ElementTypeInstructionActionLink> selectResult = new ArrayList<ElementTypeInstructionActionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementTypeInstructionActionLink>>() {
                    });
        }
        else {
            selectResult = elementTypeInstructionActionLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementTypeInstructionActionLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, id);
        ElementTypeInstructionActionLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ElementTypeInstructionActionLink>() {
                    });
        }
        else {
            selectResult = elementTypeInstructionActionLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementTypeInstructionActionLink selectOneByExample(ElementTypeInstructionActionLinkExample example) {
        ElementTypeInstructionActionLink selectResult = elementTypeInstructionActionLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ElementTypeInstructionActionLink record,
            ElementTypeInstructionActionLinkExample example) {
        int updateResult = elementTypeInstructionActionLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ElementTypeInstructionActionLink record,
            ElementTypeInstructionActionLinkExample example) {
        int updateResult = elementTypeInstructionActionLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ElementTypeInstructionActionLink record) {
        int updateResult = elementTypeInstructionActionLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ElementTypeInstructionActionLink record) {
        int updateResult = elementTypeInstructionActionLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementTypeInstructionActionLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionActionLink.class));
        }
        return updateResult;
    }

}
