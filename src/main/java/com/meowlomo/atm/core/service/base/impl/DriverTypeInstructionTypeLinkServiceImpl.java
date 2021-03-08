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
import com.meowlomo.atm.core.mapper.DriverTypeInstructionTypeLinkMapper;
import com.meowlomo.atm.core.model.DriverTypeInstructionTypeLink;
import com.meowlomo.atm.core.model.DriverTypeInstructionTypeLinkExample;
import com.meowlomo.atm.core.service.base.DriverTypeInstructionTypeLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverTypeInstructionTypeLinkServiceImpl implements DriverTypeInstructionTypeLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverTypeInstructionTypeLinkMapper driverTypeInstructionTypeLinkMapper;
    @Autowired
    private RedisService<DriverTypeInstructionTypeLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverTypeInstructionTypeLinkExample example) {
        return driverTypeInstructionTypeLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverTypeInstructionTypeLinkExample example) {
        int deleteResult = driverTypeInstructionTypeLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverTypeInstructionTypeLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverTypeInstructionTypeLink record) {
        int insertResult = driverTypeInstructionTypeLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverTypeInstructionTypeLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverTypeInstructionTypeLink record = records.get(i);
            if (driverTypeInstructionTypeLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverTypeInstructionTypeLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverTypeInstructionTypeLink record = records.get(i);
            if (driverTypeInstructionTypeLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverTypeInstructionTypeLink record) {
        int insertResult = driverTypeInstructionTypeLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return insertResult;
    }

    @Override
    public List<DriverTypeInstructionTypeLink> selectByExample(DriverTypeInstructionTypeLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, example);
        List<DriverTypeInstructionTypeLink> selectResult = new ArrayList<DriverTypeInstructionTypeLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverTypeInstructionTypeLink>>() {
                    });
        }
        else {
            selectResult = driverTypeInstructionTypeLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverTypeInstructionTypeLink> selectByExampleWithRowbounds(
            DriverTypeInstructionTypeLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, example, rowBounds);
        List<DriverTypeInstructionTypeLink> selectResult = new ArrayList<DriverTypeInstructionTypeLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverTypeInstructionTypeLink>>() {
                    });
        }
        else {
            selectResult = driverTypeInstructionTypeLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverTypeInstructionTypeLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, id);
        DriverTypeInstructionTypeLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<DriverTypeInstructionTypeLink>() {
                    });
        }
        else {
            selectResult = driverTypeInstructionTypeLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverTypeInstructionTypeLink selectOneByExample(DriverTypeInstructionTypeLinkExample example) {
        DriverTypeInstructionTypeLink selectResult = driverTypeInstructionTypeLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverTypeInstructionTypeLink record, DriverTypeInstructionTypeLinkExample example) {
        int updateResult = driverTypeInstructionTypeLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverTypeInstructionTypeLink record,
            DriverTypeInstructionTypeLinkExample example) {
        int updateResult = driverTypeInstructionTypeLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverTypeInstructionTypeLink record) {
        int updateResult = driverTypeInstructionTypeLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverTypeInstructionTypeLink record) {
        int updateResult = driverTypeInstructionTypeLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverTypeInstructionTypeLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverTypeInstructionTypeLink.class));
        }
        return updateResult;
    }
}
