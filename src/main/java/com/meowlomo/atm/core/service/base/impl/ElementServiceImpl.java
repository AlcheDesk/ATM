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
import com.meowlomo.atm.core.mapper.ElementMapper;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.ElementExample;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionTargetMap;
import com.meowlomo.atm.core.service.base.ElementService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementServiceImpl implements ElementService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private RedisService<Element> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ElementExample example) {
        return elementMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ElementExample example) {
        int deleteResult = elementMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = elementMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Element.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Element record) {
        int insertResult = elementMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Element.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Element> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Element record = records.get(i);
            if (elementMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Element.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Element> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Element record = records.get(i);
            if (elementMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Element.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Element record) {
        int insertResult = elementMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Element.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(ElementExample example) {
        int deleteResult = elementMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = elementMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Element.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
        }
        return deleteResult;
    }

    @Override
    public List<Element> selectByExample(ElementExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Element.class, example);
        List<Element> selectResult = new ArrayList<Element>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Element>>() {
                    });
        }
        else {
            selectResult = elementMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Element> selectByExampleWithRowbounds(ElementExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Element.class, example, rowBounds);
        List<Element> selectResult = new ArrayList<Element>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Element>>() {
                    });
        }
        else {
            selectResult = elementMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Element selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Element.class, id);
        Element selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Element>() {
            });
        }
        else {
            selectResult = elementMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Element selectOneByExample(ElementExample example) {
        Element selectResult = elementMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Element.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Element record, ElementExample example) {
        int updateResult = elementMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Element.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Instruction.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Element record, ElementExample example) {
        int updateResult = elementMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Element.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Instruction.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Element record) {
        int updateResult = elementMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Element.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Instruction.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Element record) {
        int updateResult = elementMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Element.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Element.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Instruction.class));
        }
        return updateResult;
    }
}
