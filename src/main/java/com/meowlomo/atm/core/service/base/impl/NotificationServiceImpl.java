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
import com.meowlomo.atm.core.mapper.NotificationMapper;
import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLink;
import com.meowlomo.atm.core.model.NotificationExample;
import com.meowlomo.atm.core.service.base.NotificationService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Notification> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(NotificationExample example) {
        return notificationMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(NotificationExample example) {
        int deleteResult = notificationMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = notificationMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Notification.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Notification record) {
        int insertResult = notificationMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Notification.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Notification> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Notification record = records.get(i);
            if (notificationMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Notification.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Notification> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Notification record = records.get(i);
            if (notificationMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Notification.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Notification record) {
        int insertResult = notificationMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Notification.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(NotificationExample example) {
        int deleteResult = notificationMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = notificationMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Notification.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
        }
        return deleteResult;
    }

    @Override
    public List<Notification> selectByExample(NotificationExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Notification.class, example);
        List<Notification> selectResult = new ArrayList<Notification>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Notification>>() {
                    });
        }
        else {
            selectResult = notificationMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Notification.class, example, rowBounds);
        List<Notification> selectResult = new ArrayList<Notification>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Notification>>() {
                    });
        }
        else {
            selectResult = notificationMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Notification selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Notification.class, id);
        Notification selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<Notification>() {
                    });
        }
        else {
            selectResult = notificationMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Notification selectOneByExample(NotificationExample example) {
        Notification selectResult = notificationMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Notification.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Notification record, NotificationExample example) {
        int updateResult = notificationMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Notification.class));
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Notification record, NotificationExample example) {
        int updateResult = notificationMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Notification.class));
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Notification record) {
        int updateResult = notificationMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Notification.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Notification record) {
        int updateResult = notificationMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Notification.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Notification.class));
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return updateResult;
    }
}
