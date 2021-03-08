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
import com.meowlomo.atm.core.mapper.DriverPackDriverTypeMapMapper;
import com.meowlomo.atm.core.model.DriverPackDriverTypeMap;
import com.meowlomo.atm.core.model.DriverPackDriverTypeMapExample;
import com.meowlomo.atm.core.service.base.DriverPackDriverTypeMapService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPackDriverTypeMapServiceImpl implements DriverPackDriverTypeMapService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverPackDriverTypeMapMapper driverPackDriverTypeMapMapper;
    @Autowired
    private RedisService<DriverPackDriverTypeMap> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverPackDriverTypeMapExample example) {
        return driverPackDriverTypeMapMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverPackDriverTypeMapExample example) {
        int deleteResult = driverPackDriverTypeMapMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverPackDriverTypeMapMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverPackDriverTypeMap record) {
        int insertResult = driverPackDriverTypeMapMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverPackDriverTypeMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPackDriverTypeMap record = records.get(i);
            if (driverPackDriverTypeMapMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverPackDriverTypeMap> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPackDriverTypeMap record = records.get(i);
            if (driverPackDriverTypeMapMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverPackDriverTypeMap record) {
        int insertResult = driverPackDriverTypeMapMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<DriverPackDriverTypeMap> selectByExample(DriverPackDriverTypeMapExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, example);
        List<DriverPackDriverTypeMap> selectResult = new ArrayList<DriverPackDriverTypeMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPackDriverTypeMap>>() {
                    });
        }
        else {
            selectResult = driverPackDriverTypeMapMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverPackDriverTypeMap> selectByExampleWithRowbounds(DriverPackDriverTypeMapExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, example, rowBounds);
        List<DriverPackDriverTypeMap> selectResult = new ArrayList<DriverPackDriverTypeMap>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPackDriverTypeMap>>() {
                    });
        }
        else {
            selectResult = driverPackDriverTypeMapMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPackDriverTypeMap selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, id);
        DriverPackDriverTypeMap selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<DriverPackDriverTypeMap>() {
                    });
        }
        else {
            selectResult = driverPackDriverTypeMapMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPackDriverTypeMap selectOneByExample(DriverPackDriverTypeMapExample example) {
        DriverPackDriverTypeMap selectResult = driverPackDriverTypeMapMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverPackDriverTypeMap record, DriverPackDriverTypeMapExample example) {
        int updateResult = driverPackDriverTypeMapMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverPackDriverTypeMap record, DriverPackDriverTypeMapExample example) {
        int updateResult = driverPackDriverTypeMapMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverPackDriverTypeMap record) {
        int updateResult = driverPackDriverTypeMapMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverPackDriverTypeMap record) {
        int updateResult = driverPackDriverTypeMapMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPackDriverTypeMap.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverTypeMap.class));
        }
        return updateResult;
    }
}
