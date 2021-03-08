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
import com.meowlomo.atm.core.mapper.EmailNotificationTargetMapper;
import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.EmailNotificationTargetExample;
import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLink;
import com.meowlomo.atm.core.service.base.EmailNotificationTargetService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EmailNotificationTargetServiceImpl implements EmailNotificationTargetService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private EmailNotificationTargetMapper emailNotificationTargetMapper;
    @Autowired
    private RedisService<EmailNotificationTarget> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(EmailNotificationTargetExample example) {
        return emailNotificationTargetMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(EmailNotificationTargetExample example) {
        int deleteResult = emailNotificationTargetMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = emailNotificationTargetMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
            redisService
                    .deleteAllMatch(cacheKeyGenerator.generateClassKey(NotificationEmailNotificationTargetLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(EmailNotificationTarget record) {
        int insertResult = emailNotificationTargetMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<EmailNotificationTarget> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            EmailNotificationTarget record = records.get(i);
            if (emailNotificationTargetMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<EmailNotificationTarget> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            EmailNotificationTarget record = records.get(i);
            if (emailNotificationTargetMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, record.getId()),
                        record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(EmailNotificationTarget record) {
        int insertResult = emailNotificationTargetMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return insertResult;
    }

    @Override
    public List<EmailNotificationTarget> selectByExample(EmailNotificationTargetExample example) {
        String redisKey = cacheKeyGenerator.generateKey(EmailNotificationTarget.class, example);
        List<EmailNotificationTarget> selectResult = new ArrayList<EmailNotificationTarget>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<EmailNotificationTarget>>() {
                    });
        }
        else {
            selectResult = emailNotificationTargetMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<EmailNotificationTarget> selectByExampleWithRowbounds(EmailNotificationTargetExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(EmailNotificationTarget.class, example, rowBounds);
        List<EmailNotificationTarget> selectResult = new ArrayList<EmailNotificationTarget>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<EmailNotificationTarget>>() {
                    });
        }
        else {
            selectResult = emailNotificationTargetMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public EmailNotificationTarget selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(EmailNotificationTarget.class, id);
        EmailNotificationTarget selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<EmailNotificationTarget>() {
                    });
        }
        else {
            selectResult = emailNotificationTargetMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public EmailNotificationTarget selectOneByExample(EmailNotificationTargetExample example) {
        EmailNotificationTarget selectResult = emailNotificationTargetMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(EmailNotificationTarget record, EmailNotificationTargetExample example) {
        int updateResult = emailNotificationTargetMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(EmailNotificationTarget record, EmailNotificationTargetExample example) {
        int updateResult = emailNotificationTargetMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(EmailNotificationTarget record) {
        int updateResult = emailNotificationTargetMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(EmailNotificationTarget record) {
        int updateResult = emailNotificationTargetMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(EmailNotificationTarget.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(EmailNotificationTarget.class));
        }
        return updateResult;
    }
}
