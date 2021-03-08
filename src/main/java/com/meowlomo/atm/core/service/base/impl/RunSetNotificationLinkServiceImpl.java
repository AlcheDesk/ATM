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
import com.meowlomo.atm.core.mapper.RunSetNotificationLinkMapper;
import com.meowlomo.atm.core.model.RunSetNotificationLink;
import com.meowlomo.atm.core.model.RunSetNotificationLinkExample;
import com.meowlomo.atm.core.service.base.RunSetNotificationLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetNotificationLinkServiceImpl implements RunSetNotificationLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetNotificationLink> redisService;
    @Autowired
    private RunSetNotificationLinkMapper runSetNotificationLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetNotificationLinkExample example) {
        return runSetNotificationLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetNotificationLinkExample example) {
        int deleteResult = runSetNotificationLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetNotificationLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetNotificationLink record) {
        int insertResult = runSetNotificationLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetNotificationLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetNotificationLink record = records.get(i);
            if (runSetNotificationLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetNotificationLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetNotificationLink record = records.get(i);
            if (runSetNotificationLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetNotificationLink record) {
        int insertResult = runSetNotificationLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSetNotificationLink> selectByExample(RunSetNotificationLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetNotificationLink.class, example);
        List<RunSetNotificationLink> selectResult = new ArrayList<RunSetNotificationLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetNotificationLink>>() {
                    });
        }
        else {
            selectResult = runSetNotificationLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetNotificationLink> selectByExampleWithRowbounds(RunSetNotificationLinkExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetNotificationLink.class, example, rowBounds);
        List<RunSetNotificationLink> selectResult = new ArrayList<RunSetNotificationLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetNotificationLink>>() {
                    });
        }
        else {
            selectResult = runSetNotificationLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetNotificationLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetNotificationLink.class, id);
        RunSetNotificationLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunSetNotificationLink>() {
                    });
        }
        else {
            selectResult = runSetNotificationLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetNotificationLink selectOneByExample(RunSetNotificationLinkExample example) {
        RunSetNotificationLink selectResult = runSetNotificationLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetNotificationLink record, RunSetNotificationLinkExample example) {
        int updateResult = runSetNotificationLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetNotificationLink record, RunSetNotificationLinkExample example) {
        int updateResult = runSetNotificationLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetNotificationLink record) {
        int updateResult = runSetNotificationLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetNotificationLink record) {
        int updateResult = runSetNotificationLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetNotificationLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetNotificationLink.class));
        }
        return updateResult;
    }
}
