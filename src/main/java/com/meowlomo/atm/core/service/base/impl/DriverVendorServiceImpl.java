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
import com.meowlomo.atm.core.mapper.DriverVendorMapper;
import com.meowlomo.atm.core.model.DriverVendor;
import com.meowlomo.atm.core.model.DriverVendorExample;
import com.meowlomo.atm.core.service.base.DriverVendorService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverVendorServiceImpl implements DriverVendorService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverVendorMapper driverVendorMapper;
    @Autowired
    private RedisService<DriverVendor> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverVendorExample example) {
        return driverVendorMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverVendorExample example) {
        int deleteResult = driverVendorMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverVendorMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverVendor.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverVendor record) {
        int insertResult = driverVendorMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverVendor.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverVendor> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverVendor record = records.get(i);
            if (driverVendorMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverVendor.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverVendor> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverVendor record = records.get(i);
            if (driverVendorMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverVendor.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverVendor record) {
        int insertResult = driverVendorMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverVendor.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return insertResult;
    }

    @Override
    public List<DriverVendor> selectByExample(DriverVendorExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverVendor.class, example);
        List<DriverVendor> selectResult = new ArrayList<DriverVendor>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverVendor>>() {
                    });
        }
        else {
            selectResult = driverVendorMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverVendor> selectByExampleWithRowbounds(DriverVendorExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverVendor.class, example, rowBounds);
        List<DriverVendor> selectResult = new ArrayList<DriverVendor>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverVendor>>() {
                    });
        }
        else {
            selectResult = driverVendorMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverVendor selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverVendor.class, id);
        DriverVendor selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<DriverVendor>() {
                    });
        }
        else {
            selectResult = driverVendorMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverVendor selectOneByExample(DriverVendorExample example) {
        DriverVendor selectResult = driverVendorMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverVendor.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverVendor record, DriverVendorExample example) {
        int updateResult = driverVendorMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverVendor record, DriverVendorExample example) {
        int updateResult = driverVendorMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverVendor record) {
        int updateResult = driverVendorMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverVendor.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverVendor record) {
        int updateResult = driverVendorMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverVendor.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverVendor.class));
        }
        return updateResult;
    }
}
