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
import com.meowlomo.atm.core.mapper.ElementTypeInstructionOptionLinkMapper;
import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLinkExample;
import com.meowlomo.atm.core.model.InstructionOption;
import com.meowlomo.atm.core.model.InstructionOptionExample;
import com.meowlomo.atm.core.model.InstructionOptionMap;
import com.meowlomo.atm.core.service.base.ElementTypeInstructionOptionLinkService;
import com.meowlomo.atm.core.service.base.InstructionOptionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementTypeInstructionOptionLinkServiceImpl implements ElementTypeInstructionOptionLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ElementTypeInstructionOptionLinkMapper elementTypeInstructionOptionLinkMapper;
    @Autowired
    private InstructionOptionService instructionOptionService;
    @Autowired
    private RedisService<ElementTypeInstructionOptionLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ElementTypeInstructionOptionLinkExample example) {
        return elementTypeInstructionOptionLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ElementTypeInstructionOptionLinkExample example) {
        int deleteResult = elementTypeInstructionOptionLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = elementTypeInstructionOptionLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionOptionMap.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ElementTypeInstructionOptionLink record) {
        int insertResult = elementTypeInstructionOptionLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ElementTypeInstructionOptionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementTypeInstructionOptionLink record = records.get(i);
            if (elementTypeInstructionOptionLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ElementTypeInstructionOptionLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ElementTypeInstructionOptionLink record = records.get(i);
            if (elementTypeInstructionOptionLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ElementTypeInstructionOptionLink record) {
        int insertResult = elementTypeInstructionOptionLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return insertResult;
    }

    @Override
    public List<InstructionOption> listInstructionOptionByElementTypePrimaryKey(Long elementTypeId) {
        ElementTypeInstructionOptionLinkExample linkExample = new ElementTypeInstructionOptionLinkExample();
        linkExample.createCriteria().andElementTypeEqualTo(RuntimeVariables.getIdToElementTypeMap().get(elementTypeId));
        List<ElementTypeInstructionOptionLink> targetLinks = elementTypeInstructionOptionLinkMapper
                .selectByExample(linkExample);
        List<String> targetIds = new ArrayList<String>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getInstructionOption());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<InstructionOption>();
        }
        else {
            InstructionOptionExample targetExample = new InstructionOptionExample();
            targetExample.createCriteria().andNameIn(targetIds);
            return instructionOptionService.selectByExample(targetExample);
        }
    }

    @Override
    public List<ElementTypeInstructionOptionLink> selectByExample(ElementTypeInstructionOptionLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, example);
        List<ElementTypeInstructionOptionLink> selectResult = new ArrayList<ElementTypeInstructionOptionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementTypeInstructionOptionLink>>() {
                    });
        }
        else {
            selectResult = elementTypeInstructionOptionLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ElementTypeInstructionOptionLink> selectByExampleWithRowbounds(
            ElementTypeInstructionOptionLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, example, rowBounds);
        List<ElementTypeInstructionOptionLink> selectResult = new ArrayList<ElementTypeInstructionOptionLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ElementTypeInstructionOptionLink>>() {
                    });
        }
        else {
            selectResult = elementTypeInstructionOptionLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementTypeInstructionOptionLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, id);
        ElementTypeInstructionOptionLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ElementTypeInstructionOptionLink>() {
                    });
        }
        else {
            selectResult = elementTypeInstructionOptionLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ElementTypeInstructionOptionLink selectOneByExample(ElementTypeInstructionOptionLinkExample example) {
        ElementTypeInstructionOptionLink selectResult = elementTypeInstructionOptionLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ElementTypeInstructionOptionLink record,
            ElementTypeInstructionOptionLinkExample example) {
        int updateResult = elementTypeInstructionOptionLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ElementTypeInstructionOptionLink record,
            ElementTypeInstructionOptionLinkExample example) {
        int updateResult = elementTypeInstructionOptionLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ElementTypeInstructionOptionLink record) {
        int updateResult = elementTypeInstructionOptionLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ElementTypeInstructionOptionLink record) {
        int updateResult = elementTypeInstructionOptionLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ElementTypeInstructionOptionLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ElementTypeInstructionOptionLink.class));
        }
        return updateResult;
    }
}
