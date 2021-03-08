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
import com.meowlomo.atm.core.mapper.DriverTypeMapper;
import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.DriverTypeExample;
import com.meowlomo.atm.core.service.base.DriverTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverTypeServiceImpl implements DriverTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverTypeMapper driverTypeMapper;
    @Autowired
    private RedisService<DriverType> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverTypeExample example) {
        return driverTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverTypeExample example) {
        int deleteResult = driverTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverType record) {
        int insertResult = driverTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverType record = records.get(i);
            if (driverTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverType record = records.get(i);
            if (driverTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverType record) {
        int insertResult = driverTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return insertResult;
    }

    @Override
    public List<DriverType> selectByExample(DriverTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverType.class, example);
        List<DriverType> selectResult = new ArrayList<DriverType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverType>>() {
                    });
        }
        else {
            selectResult = driverTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverType> selectByExampleWithRowbounds(DriverTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverType.class, example, rowBounds);
        List<DriverType> selectResult = new ArrayList<DriverType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverType>>() {
                    });
        }
        else {
            selectResult = driverTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverType.class, id);
        DriverType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<DriverType>() {
            });
        }
        else {
            selectResult = driverTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverType selectOneByExample(DriverTypeExample example) {
        DriverType selectResult = driverTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverType record, DriverTypeExample example) {
        int updateResult = driverTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverType record, DriverTypeExample example) {
        int updateResult = driverTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverType record) {
        int updateResult = driverTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverType record) {
        int updateResult = driverTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverType.class));
        }
        return updateResult;
    }
}
