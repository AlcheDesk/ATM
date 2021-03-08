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
import com.meowlomo.atm.core.mapper.DriverPackMapper;
import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackDriverAliasMap;
import com.meowlomo.atm.core.model.DriverPackExample;
import com.meowlomo.atm.core.service.base.DriverPackService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPackServiceImpl implements DriverPackService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverPackMapper driverPackMapper;
    @Autowired
    private RedisService<DriverPack> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(DriverPackExample example) {
        return driverPackMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverPackExample example) {
        int deleteResult = driverPackMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverPackMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverPack.class, id));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverPack record) {
        int insertResult = driverPackMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPack.class, record.getId()), record);
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverPack> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPack record = records.get(i);
            if (driverPackMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverPack.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverPack> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPack record = records.get(i);
            if (driverPackMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverPack.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverPack record) {
        int insertResult = driverPackMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPack.class, record.getId()), record);
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(DriverPackExample example) {
        int deleteResult = driverPackMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = driverPackMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverPack.class, id));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
        }
        return deleteResult;
    }

    @Override
    public List<DriverPack> selectByExample(DriverPackExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPack.class, example);
        List<DriverPack> selectResult = new ArrayList<DriverPack>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPack>>() {
                    });
        }
        else {
            selectResult = driverPackMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverPack> selectByExampleWithRowbounds(DriverPackExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPack.class, example, rowBounds);
        List<DriverPack> selectResult = new ArrayList<DriverPack>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPack>>() {
                    });
        }
        else {
            selectResult = driverPackMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPack selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPack.class, id);
        DriverPack selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<DriverPack>() {
            });
        }
        else {
            selectResult = driverPackMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPack selectOneByExample(DriverPackExample example) {
        DriverPack selectResult = driverPackMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPack.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverPack record, DriverPackExample example) {
        int updateResult = driverPackMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverPack record, DriverPackExample example) {
        int updateResult = driverPackMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverPack record) {
        int updateResult = driverPackMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPack.class, record.getId()));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverPack record) {
        int updateResult = driverPackMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPack.class, record.getId()));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPack.class));
        }
        return updateResult;
    }
}
