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
import com.meowlomo.atm.core.mapper.DriverMapper;
import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverExample;
import com.meowlomo.atm.core.model.DriverPackDriverLink;
import com.meowlomo.atm.core.service.base.DriverService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverServiceImpl implements DriverService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private RedisService<Driver> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverExample example) {
        return driverMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverExample example) {
        int deleteResult = driverMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Driver.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Driver record) {
        int insertResult = driverMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Driver.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Driver> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Driver record = records.get(i);
            if (driverMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Driver.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Driver> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Driver record = records.get(i);
            if (driverMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Driver.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Driver record) {
        int insertResult = driverMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Driver.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return insertResult;
    }

    @Override
    public List<Driver> selectByExample(DriverExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Driver.class, example);
        List<Driver> selectResult = new ArrayList<Driver>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Driver>>() {
            });
        }
        else {
            selectResult = driverMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Driver> selectByExampleWithRowbounds(DriverExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Driver.class, example, rowBounds);
        List<Driver> selectResult = new ArrayList<Driver>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Driver>>() {
            });
        }
        else {
            selectResult = driverMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Driver selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Driver.class, id);
        Driver selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Driver>() {
            });
        }
        else {
            selectResult = driverMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Driver selectOneByExample(DriverExample example) {
        Driver selectResult = driverMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Driver.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Driver record, DriverExample example) {
        int updateResult = driverMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Driver record, DriverExample example) {
        int updateResult = driverMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Driver record) {
        int updateResult = driverMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Driver.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Driver record) {
        int updateResult = driverMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Driver.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Driver.class));
        }
        return updateResult;
    }
}
