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
import com.meowlomo.atm.core.mapper.DriverPackDriverLinkMapper;
import com.meowlomo.atm.core.model.DriverPackDriverAliasMap;
import com.meowlomo.atm.core.model.DriverPackDriverLink;
import com.meowlomo.atm.core.model.DriverPackDriverLinkExample;
import com.meowlomo.atm.core.service.base.DriverPackDriverLinkService;
import com.meowlomo.atm.core.service.util.DriverPackDriverLinkUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPackDriverLinkServiceImpl implements DriverPackDriverLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DriverPackDriverLinkMapper driverPackDriverLinkMapper;
    @Autowired
    private RedisService<DriverPackDriverLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    @Autowired
    private DriverPackDriverLinkUtilService driverPackDriverLinkUtilService;

    @Override
    public long countByExample(DriverPackDriverLinkExample example) {
        return driverPackDriverLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DriverPackDriverLinkExample example) {
        int deleteResult = driverPackDriverLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = driverPackDriverLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(DriverPackDriverLink record) {
        int insertResult = 0;
        if (driverPackDriverLinkUtilService.isIgnoredWebBrowserLink(record)) {
            insertResult = 1;
        }
        else {
            insertResult = driverPackDriverLinkMapper.insert(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<DriverPackDriverLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPackDriverLink record = records.get(i);
            int insertResult = 0;
            if (driverPackDriverLinkUtilService.isIgnoredWebBrowserLink(record)) {
                insertResult = 1;
            }
            else {
                insertResult = driverPackDriverLinkMapper.insert(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<DriverPackDriverLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            DriverPackDriverLink record = records.get(i);
            int insertResult = 0;
            if (driverPackDriverLinkUtilService.isIgnoredWebBrowserLink(record)) {
                insertResult = 1;
            }
            else {
                insertResult = driverPackDriverLinkMapper.insertSelective(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, record.getId()),record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(DriverPackDriverLink record) {
        int insertResult = 0;
        if (driverPackDriverLinkUtilService.isIgnoredWebBrowserLink(record)) {
            insertResult = 1;
        }
        else {
            insertResult = driverPackDriverLinkMapper.insertSelective(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
        }
        return insertResult;
    }

    @Override
    public List<DriverPackDriverLink> selectByExample(DriverPackDriverLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverLink.class, example);
        List<DriverPackDriverLink> selectResult = new ArrayList<DriverPackDriverLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPackDriverLink>>() {
                    });
        }
        else {
            selectResult = driverPackDriverLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<DriverPackDriverLink> selectByExampleWithRowbounds(DriverPackDriverLinkExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverLink.class, example, rowBounds);
        List<DriverPackDriverLink> selectResult = new ArrayList<DriverPackDriverLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<DriverPackDriverLink>>() {
                    });
        }
        else {
            selectResult = driverPackDriverLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPackDriverLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(DriverPackDriverLink.class, id);
        DriverPackDriverLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<DriverPackDriverLink>() {
                    });
        }
        else {
            selectResult = driverPackDriverLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public DriverPackDriverLink selectOneByExample(DriverPackDriverLinkExample example) {
        DriverPackDriverLink selectResult = driverPackDriverLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(DriverPackDriverLink record, DriverPackDriverLinkExample example) {
        int updateResult = driverPackDriverLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(DriverPackDriverLink record, DriverPackDriverLinkExample example) {
        int updateResult = driverPackDriverLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(DriverPackDriverLink record) {
        int updateResult = driverPackDriverLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(DriverPackDriverLink record) {
        int updateResult = driverPackDriverLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(DriverPackDriverLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverLink.class));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(DriverPackDriverAliasMap.class));
        }
        return updateResult;
    }
}
