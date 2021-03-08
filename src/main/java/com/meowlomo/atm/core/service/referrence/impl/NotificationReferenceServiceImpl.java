package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.NotificationMapper;
import com.meowlomo.atm.core.mapper.NotificationReferenceMapper;
import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.NotificationExample;
import com.meowlomo.atm.core.service.referrence.NotificationReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class NotificationReferenceServiceImpl implements NotificationReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private NotificationReferenceMapper notificationReferenceMapper;
    @Autowired
    private RedisService<Notification> redisService;

    @Override
    public long countByExample(NotificationExample example) {
        return notificationMapper.countByExample(example);
    }

    @Override
    public List<Notification> selectByExample(NotificationExample example) {
        List<Notification> selectResult = notificationReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Notification.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds) {
        List<Notification> selectResult = notificationReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Notification.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Notification selectByPrimaryKey(Long id) {
        Notification selectResult = notificationReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Notification.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
