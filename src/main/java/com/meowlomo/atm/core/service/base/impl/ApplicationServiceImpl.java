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
import com.meowlomo.atm.core.mapper.ApplicationMapper;
import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.ApplicationExample;
import com.meowlomo.atm.core.model.InstructionTargetMap;
import com.meowlomo.atm.core.service.base.ApplicationService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Application> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ApplicationExample example) {
        return applicationMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ApplicationExample example) {
        int deleteResult = applicationMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = applicationMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Application.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Application record) {
        int insertResult = applicationMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Application.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Application> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Application record = records.get(i);
            if (applicationMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Application.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Application> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Application record = records.get(i);
            if (applicationMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Application.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Application record) {
        int insertResult = applicationMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Application.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(ApplicationExample example) {
        int deleteResult = applicationMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = applicationMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Application.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
        }
        return deleteResult;
    }

    @Override
    public List<Application> selectByExample(ApplicationExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Application.class, example);
        List<Application> selectResult = new ArrayList<Application>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Application>>() {
                    });
        }
        else {
            selectResult = applicationMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Application> selectByExampleWithRowbounds(ApplicationExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Application.class, example, rowBounds);
        List<Application> selectResult = new ArrayList<Application>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Application>>() {
                    });
        }
        else {
            selectResult = applicationMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Application selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Application.class, id);
        Application selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Application>() {
            });
        }
        else {
            selectResult = applicationMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Application selectOneByExample(ApplicationExample example) {
        Application selectResult = applicationMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Application.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Application record, ApplicationExample example) {
        int updateResult = applicationMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Application.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Application record, ApplicationExample example) {
        int updateResult = applicationMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Application.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Application record) {
        int updateResult = applicationMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Application.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Application record) {
        int updateResult = applicationMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Application.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Application.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(InstructionTargetMap.class));
        }
        return updateResult;
    }
}
