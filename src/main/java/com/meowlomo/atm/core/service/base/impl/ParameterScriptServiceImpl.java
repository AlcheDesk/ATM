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
import com.meowlomo.atm.core.mapper.ParameterScriptMapper;
import com.meowlomo.atm.core.model.ParameterScript;
import com.meowlomo.atm.core.model.ParameterScriptExample;
import com.meowlomo.atm.core.service.base.ParameterScriptService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ParameterScriptServiceImpl implements ParameterScriptService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ParameterScriptMapper parameterScriptMapper;
    @Autowired
    private RedisService<ParameterScript> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ParameterScriptExample example) {
        return parameterScriptMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ParameterScriptExample example) {
        int deleteResult = parameterScriptMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = parameterScriptMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(ParameterScript.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(ParameterScript record) {
        int insertResult = parameterScriptMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ParameterScript.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<ParameterScript> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ParameterScript record = records.get(i);
            if (parameterScriptMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ParameterScript.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<ParameterScript> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            ParameterScript record = records.get(i);
            if (parameterScriptMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(ParameterScript.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(ParameterScript record) {
        int insertResult = parameterScriptMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(ParameterScript.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return insertResult;
    }

    @Override
    public List<ParameterScript> selectByExample(ParameterScriptExample example) {
        String redisKey = cacheKeyGenerator.generateKey(ParameterScript.class, example);
        List<ParameterScript> selectResult = new ArrayList<ParameterScript>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ParameterScript>>() {
                    });
        }
        else {
            selectResult = parameterScriptMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<ParameterScript> selectByExampleWithRowbounds(ParameterScriptExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(ParameterScript.class, example, rowBounds);
        List<ParameterScript> selectResult = new ArrayList<ParameterScript>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<ParameterScript>>() {
                    });
        }
        else {
            selectResult = parameterScriptMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ParameterScript selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(ParameterScript.class, id);
        ParameterScript selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<ParameterScript>() {
                    });
        }
        else {
            selectResult = parameterScriptMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public ParameterScript selectOneByExample(ParameterScriptExample example) {
        ParameterScript selectResult = parameterScriptMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(ParameterScript.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(ParameterScript record, ParameterScriptExample example) {
        int updateResult = parameterScriptMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(ParameterScript record, ParameterScriptExample example) {
        int updateResult = parameterScriptMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(ParameterScript record) {
        int updateResult = parameterScriptMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ParameterScript.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(ParameterScript record) {
        int updateResult = parameterScriptMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(ParameterScript.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(ParameterScript.class));
        }
        return updateResult;
    }
}
