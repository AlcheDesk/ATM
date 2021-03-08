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
import com.meowlomo.atm.core.mapper.DriverPropertyPredefinedValueMapper;
import com.meowlomo.atm.core.model.DriverPropertyPredefinedValue;
import com.meowlomo.atm.core.model.DriverPropertyPredefinedValueExample;
import com.meowlomo.atm.core.service.base.DriverPropertyPredefinedValueService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPropertyPredefinedValueServiceImpl implements DriverPropertyPredefinedValueService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverPropertyPredefinedValueMapper driverPropertyPredefinedValueMapper;
    @Autowired
    private RedisService<DriverPropertyPredefinedValue> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverPropertyPredefinedValueExample example) {
        return driverPropertyPredefinedValueMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverPropertyPredefinedValueExample example) {
        int deleteResult = driverPropertyPredefinedValueMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverPropertyPredefinedValueMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverPropertyPredefinedValue record) {
        int insertResult = driverPropertyPredefinedValueMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverPropertyPredefinedValue> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPropertyPredefinedValue record = records.get(i);
            if (driverPropertyPredefinedValueMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverPropertyPredefinedValue> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPropertyPredefinedValue record = records.get(i);
            if (driverPropertyPredefinedValueMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverPropertyPredefinedValue record) {
        int insertResult = driverPropertyPredefinedValueMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, record.getId()),
                    record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return insertResult;
    }

    @Override
    public List<DriverPropertyPredefinedValue> selectByExample(DriverPropertyPredefinedValueExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, example);
        List<DriverPropertyPredefinedValue> selectResult = new ArrayList<DriverPropertyPredefinedValue>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPropertyPredefinedValue>>() {
                    });
        }
        else {
            selectResult = driverPropertyPredefinedValueMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverPropertyPredefinedValue> selectByExampleWithRowbounds(
            DriverPropertyPredefinedValueExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, example, rowBounds);
        List<DriverPropertyPredefinedValue> selectResult = new ArrayList<DriverPropertyPredefinedValue>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPropertyPredefinedValue>>() {
                    });
        }
        else {
            selectResult = driverPropertyPredefinedValueMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPropertyPredefinedValue selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, id);
        DriverPropertyPredefinedValue selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<DriverPropertyPredefinedValue>() {
                    });
        }
        else {
            selectResult = driverPropertyPredefinedValueMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPropertyPredefinedValue selectOneByExample(DriverPropertyPredefinedValueExample example) {
        DriverPropertyPredefinedValue selectResult = driverPropertyPredefinedValueMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverPropertyPredefinedValue record, DriverPropertyPredefinedValueExample example) {
        int updateResult = driverPropertyPredefinedValueMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverPropertyPredefinedValue record,
            DriverPropertyPredefinedValueExample example) {
        int updateResult = driverPropertyPredefinedValueMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverPropertyPredefinedValue record) {
        int updateResult = driverPropertyPredefinedValueMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverPropertyPredefinedValue record) {
        int updateResult = driverPropertyPredefinedValueMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPropertyPredefinedValue.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPropertyPredefinedValue.class));
        }
        return updateResult;
    }
}
