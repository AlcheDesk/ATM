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
import com.meowlomo.atm.core.mapper.RunTaskLinkMapper;
import com.meowlomo.atm.core.model.RunTaskLink;
import com.meowlomo.atm.core.model.RunTaskLinkExample;
import com.meowlomo.atm.core.service.base.RunTaskLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunTaskLinkServiceImpl implements RunTaskLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunTaskLink> redisService;
    @Autowired
    private RunTaskLinkMapper runTaskLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunTaskLinkExample example) {
        return runTaskLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunTaskLinkExample example) {
        int deleteResult = runTaskLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runTaskLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunTaskLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunTaskLink record) {
        int insertResult = runTaskLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunTaskLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunTaskLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunTaskLink record = records.get(i);
            if (runTaskLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunTaskLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunTaskLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunTaskLink record = records.get(i);
            if (runTaskLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunTaskLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunTaskLink record) {
        int insertResult = runTaskLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunTaskLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return insertResult;
    }

    @Override
    public List<RunTaskLink> selectByExample(RunTaskLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunTaskLink.class, example);
        List<RunTaskLink> selectResult = new ArrayList<RunTaskLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunTaskLink>>() {
                    });
        }
        else {
            selectResult = runTaskLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunTaskLink> selectByExampleWithRowbounds(RunTaskLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunTaskLink.class, example, rowBounds);
        List<RunTaskLink> selectResult = new ArrayList<RunTaskLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunTaskLink>>() {
                    });
        }
        else {
            selectResult = runTaskLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunTaskLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunTaskLink.class, id);
        RunTaskLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<RunTaskLink>() {
            });
        }
        else {
            selectResult = runTaskLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunTaskLink selectOneByExample(RunTaskLinkExample example) {
        RunTaskLink selectResult = runTaskLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunTaskLink.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunTaskLink record, RunTaskLinkExample example) {
        int updateResult = runTaskLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunTaskLink record, RunTaskLinkExample example) {
        int updateResult = runTaskLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunTaskLink record) {
        int updateResult = runTaskLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunTaskLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunTaskLink record) {
        int updateResult = runTaskLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunTaskLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunTaskLink.class));
        }
        return updateResult;
    }
}
