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
import com.meowlomo.atm.core.mapper.DriverPropertyMapper;
import com.meowlomo.atm.core.model.DriverProperty;
import com.meowlomo.atm.core.model.DriverPropertyExample;
import com.meowlomo.atm.core.service.base.DriverPropertyService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPropertyServiceImpl implements DriverPropertyService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverPropertyMapper driverPropertyMapper;
    @Autowired
    private RedisService<DriverProperty> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverPropertyExample example) {
        return driverPropertyMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverPropertyExample example) {
        int deleteResult = driverPropertyMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverPropertyMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverProperty.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverProperty record) {
        int insertResult = driverPropertyMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverProperty.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverProperty> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverProperty record = records.get(i);
            if (driverPropertyMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverProperty.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverProperty> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverProperty record = records.get(i);
            if (driverPropertyMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverProperty.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverProperty record) {
        int insertResult = driverPropertyMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverProperty.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return insertResult;
    }

    @Override
    public List<DriverProperty> selectByExample(DriverPropertyExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverProperty.class, example);
        List<DriverProperty> selectResult = new ArrayList<DriverProperty>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverProperty>>() {
                    });
        }
        else {
            selectResult = driverPropertyMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverProperty> selectByExampleWithRowbounds(DriverPropertyExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverProperty.class, example, rowBounds);
        List<DriverProperty> selectResult = new ArrayList<DriverProperty>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverProperty>>() {
                    });
        }
        else {
            selectResult = driverPropertyMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
            }
        }
        return selectResult;
    }

    @Override
    public DriverProperty selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverProperty.class, id);
        DriverProperty selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<DriverProperty>() {
                    });
        }
        else {
            selectResult = driverPropertyMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverProperty selectOneByExample(DriverPropertyExample example) {
        DriverProperty selectResult = driverPropertyMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverProperty.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverProperty record, DriverPropertyExample example) {
        int updateResult = driverPropertyMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverProperty record, DriverPropertyExample example) {
        int updateResult = driverPropertyMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverProperty record) {
        int updateResult = driverPropertyMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverProperty.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverProperty record) {
        int updateResult = driverPropertyMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverProperty.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverProperty.class));
        }
        return updateResult;
    }
}
