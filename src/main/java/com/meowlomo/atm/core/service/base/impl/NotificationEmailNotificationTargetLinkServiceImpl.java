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
import com.meowlomo.atm.core.mapper.NotificationEmailNotificationTargetLinkMapper;
import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLink;
import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLinkExample;
import com.meowlomo.atm.core.service.base.NotificationEmailNotificationTargetLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class NotificationEmailNotificationTargetLinkServiceImpl
        implements NotificationEmailNotificationTargetLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private NotificationEmailNotificationTargetLinkMapper notificationEmailNotificationTargetLinkMapper;
    @Autowired
    private RedisService<NotificationEmailNotificationTargetLink> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(NotificationEmailNotificationTargetLinkExample example) {
        return notificationEmailNotificationTargetLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(NotificationEmailNotificationTargetLinkExample example) {
        int deleteResult = notificationEmailNotificationTargetLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = notificationEmailNotificationTargetLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(
                    cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, id));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(NotificationEmailNotificationTargetLink record) {
        int insertResult = notificationEmailNotificationTargetLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<NotificationEmailNotificationTargetLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            NotificationEmailNotificationTargetLink record = records.get(i);
            if (notificationEmailNotificationTargetLinkMapper.insert(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<NotificationEmailNotificationTargetLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            NotificationEmailNotificationTargetLink record = records.get(i);
            if (notificationEmailNotificationTargetLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(
                        cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(NotificationEmailNotificationTargetLink record) {
        int insertResult = notificationEmailNotificationTargetLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, record.getId()),
                    record);
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return insertResult;
    }

    @Override
    public List<NotificationEmailNotificationTargetLink> selectByExample(
            NotificationEmailNotificationTargetLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, example);
        List<NotificationEmailNotificationTargetLink> selectResult = new ArrayList<NotificationEmailNotificationTargetLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<NotificationEmailNotificationTargetLink>>() {
                    });
        }
        else {
            selectResult = notificationEmailNotificationTargetLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<NotificationEmailNotificationTargetLink> selectByExampleWithRowbounds(
            NotificationEmailNotificationTargetLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, example,
                rowBounds);
        List<NotificationEmailNotificationTargetLink> selectResult = new ArrayList<NotificationEmailNotificationTargetLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<NotificationEmailNotificationTargetLink>>() {
                    });
        }
        else {
            selectResult = notificationEmailNotificationTargetLinkMapper.selectByExampleWithRowbounds(example,
                    rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public NotificationEmailNotificationTargetLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, id);
        NotificationEmailNotificationTargetLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<NotificationEmailNotificationTargetLink>() {
                    });
        }
        else {
            selectResult = notificationEmailNotificationTargetLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public NotificationEmailNotificationTargetLink selectOneByExample(
            NotificationEmailNotificationTargetLinkExample example) {
        NotificationEmailNotificationTargetLink selectResult = notificationEmailNotificationTargetLinkMapper
                .selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(
                    cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(NotificationEmailNotificationTargetLink record,
            NotificationEmailNotificationTargetLinkExample example) {
        int updateResult = notificationEmailNotificationTargetLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(NotificationEmailNotificationTargetLink record,
            NotificationEmailNotificationTargetLinkExample example) {
        int updateResult = notificationEmailNotificationTargetLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(NotificationEmailNotificationTargetLink record) {
        int updateResult = notificationEmailNotificationTargetLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(
                    cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, record.getId()));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(NotificationEmailNotificationTargetLink record) {
        int updateResult = notificationEmailNotificationTargetLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(
                    cacheKeyGenerator.generateKey(NotificationEmailNotificationTargetLink.class, record.getId()));
            redisService.deleteListPattern(
                    cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }
}
