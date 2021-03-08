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
import com.meowlomo.atm.core.mapper.RunSetResultJobLinkMapper;
import com.meowlomo.atm.core.model.RunSetResultJobLink;
import com.meowlomo.atm.core.model.RunSetResultJobLinkExample;
import com.meowlomo.atm.core.service.base.RunSetResultJobLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultJobLinkServiceImpl implements RunSetResultJobLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetResultJobLink> redisService;
    @Autowired
    private RunSetResultJobLinkMapper runSetResultJobLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetResultJobLinkExample example) {
        return runSetResultJobLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetResultJobLinkExample example) {
        int deleteResult = runSetResultJobLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetResultJobLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetResultJobLink record) {
        int insertResult = runSetResultJobLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetResultJobLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetResultJobLink record = records.get(i);
            if (runSetResultJobLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetResultJobLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetResultJobLink record = records.get(i);
            if (runSetResultJobLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetResultJobLink record) {
        int insertResult = runSetResultJobLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSetResultJobLink> selectByExample(RunSetResultJobLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetResultJobLink.class, example);
        List<RunSetResultJobLink> selectResult = new ArrayList<RunSetResultJobLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetResultJobLink>>() {
                    });
        }
        else {
            selectResult = runSetResultJobLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetResultJobLink> selectByExampleWithRowbounds(RunSetResultJobLinkExample example,
            RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetResultJobLink.class, example, rowBounds);
        List<RunSetResultJobLink> selectResult = new ArrayList<RunSetResultJobLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetResultJobLink>>() {
                    });
        }
        else {
            selectResult = runSetResultJobLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetResultJobLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetResultJobLink.class, id);
        RunSetResultJobLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunSetResultJobLink>() {
                    });
        }
        else {
            selectResult = runSetResultJobLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetResultJobLink selectOneByExample(RunSetResultJobLinkExample example) {
        RunSetResultJobLink selectResult = runSetResultJobLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetResultJobLink record, RunSetResultJobLinkExample example) {
        int updateResult = runSetResultJobLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetResultJobLink record, RunSetResultJobLinkExample example) {
        int updateResult = runSetResultJobLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetResultJobLink record) {
        int updateResult = runSetResultJobLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetResultJobLink record) {
        int updateResult = runSetResultJobLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetResultJobLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetResultJobLink.class));
        }
        return updateResult;
    }
}
