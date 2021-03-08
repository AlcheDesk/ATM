package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.EmailNotificationTargetMapper;
import com.meowlomo.atm.core.mapper.EmailNotificationTargetReferenceMapper;
import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.EmailNotificationTargetExample;
import com.meowlomo.atm.core.service.referrence.EmailNotificationTargetReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EmailNotificationTargetReferenceServiceImpl implements EmailNotificationTargetReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private EmailNotificationTargetMapper emailNotificationTargetMapper;
    @Autowired
    private EmailNotificationTargetReferenceMapper emailNotificationTargetReferenceMapper;
    @Autowired
    private RedisService<EmailNotificationTarget> redisService;

    @Override
    public long countByExample(EmailNotificationTargetExample example) {
        return emailNotificationTargetMapper.countByExample(example);
    }

    @Override
    public List<EmailNotificationTarget> selectByExample(EmailNotificationTargetExample example) {
        List<EmailNotificationTarget> selectResult = emailNotificationTargetReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, example, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public List<EmailNotificationTarget> selectByExampleWithRowbounds(EmailNotificationTargetExample example,
            RowBounds rowBounds) {
        List<EmailNotificationTarget> selectResult = emailNotificationTargetReferenceMapper
                .selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(
                    cacheKeyGenerator.generateKey(EmailNotificationTarget.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public EmailNotificationTarget selectByPrimaryKey(Long id) {
        EmailNotificationTarget selectResult = emailNotificationTargetReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, id, "reference"),
                    selectResult);
        }
        return selectResult;
    }

}
