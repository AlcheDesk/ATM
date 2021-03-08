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
import com.meowlomo.atm.core.mapper.SystemRequirementPackSystemRequirementLinkMapper;
import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLink;
import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLinkExample;
import com.meowlomo.atm.core.service.base.SystemRequirementPackSystemRequirementLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemRequirementPackSystemRequirementLinkServiceImpl
        implements SystemRequirementPackSystemRequirementLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<SystemRequirementPackSystemRequirementLink> redisService;
    @Autowired
    private SystemRequirementPackSystemRequirementLinkMapper systemRequirementPackSystemRequirementLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(SystemRequirementPackSystemRequirementLinkExample example) {
        return systemRequirementPackSystemRequirementLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SystemRequirementPackSystemRequirementLinkExample example) {
        int deleteResult = systemRequirementPackSystemRequirementLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = systemRequirementPackSystemRequirementLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(
                    cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, id));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(SystemRequirementPackSystemRequirementLink record) {
        int insertResult = systemRequirementPackSystemRequirementLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<SystemRequirementPackSystemRequirementLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirementPackSystemRequirementLink record = records.get(i);
            if (systemRequirementPackSystemRequirementLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<SystemRequirementPackSystemRequirementLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            SystemRequirementPackSystemRequirementLink record = records.get(i);
            if (systemRequirementPackSystemRequirementLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(SystemRequirementPackSystemRequirementLink record) {
        int insertResult = systemRequirementPackSystemRequirementLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return insertResult;
    }

    @Override
    public List<SystemRequirementPackSystemRequirementLink> selectByExample(
            SystemRequirementPackSystemRequirementLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, example);
        List<SystemRequirementPackSystemRequirementLink> selectResult = new ArrayList<SystemRequirementPackSystemRequirementLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirementPackSystemRequirementLink>>() {
                    });
        }
        else {
            selectResult = systemRequirementPackSystemRequirementLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<SystemRequirementPackSystemRequirementLink> selectByExampleWithRowbounds(
            SystemRequirementPackSystemRequirementLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, example,
                rowBounds);
        List<SystemRequirementPackSystemRequirementLink> selectResult = new ArrayList<SystemRequirementPackSystemRequirementLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<SystemRequirementPackSystemRequirementLink>>() {
                    });
        }
        else {
            selectResult = systemRequirementPackSystemRequirementLinkMapper.selectByExampleWithRowbounds(example,
                    rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirementPackSystemRequirementLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, id);
        SystemRequirementPackSystemRequirementLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<SystemRequirementPackSystemRequirementLink>() {
                    });
        }
        else {
            selectResult = systemRequirementPackSystemRequirementLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public SystemRequirementPackSystemRequirementLink selectOneByExample(
            SystemRequirementPackSystemRequirementLinkExample example) {
        SystemRequirementPackSystemRequirementLink selectResult = systemRequirementPackSystemRequirementLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class,
                    selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(SystemRequirementPackSystemRequirementLink record,
            SystemRequirementPackSystemRequirementLinkExample example) {
        int updateResult = systemRequirementPackSystemRequirementLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(SystemRequirementPackSystemRequirementLink record,
            SystemRequirementPackSystemRequirementLinkExample example) {
        int updateResult = systemRequirementPackSystemRequirementLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(SystemRequirementPackSystemRequirementLink record) {
        int updateResult = systemRequirementPackSystemRequirementLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(
                    cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, record.getId()));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(SystemRequirementPackSystemRequirementLink record) {
        int updateResult = systemRequirementPackSystemRequirementLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(
                    cacheKeyGenerator.generateKey(SystemRequirementPackSystemRequirementLink.class, record.getId()));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(SystemRequirementPackSystemRequirementLink.class));
        }
        return updateResult;
    }
}
