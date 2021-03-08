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
import com.meowlomo.atm.core.mapper.RunSetJobLinkMapper;
import com.meowlomo.atm.core.model.RunSetJobLink;
import com.meowlomo.atm.core.model.RunSetJobLinkExample;
import com.meowlomo.atm.core.service.base.RunSetJobLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetJobLinkServiceImpl implements RunSetJobLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetJobLink> redisService;
    @Autowired
    private RunSetJobLinkMapper runSetJobLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetJobLinkExample example) {
        return runSetJobLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetJobLinkExample example) {
        int deleteResult = runSetJobLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetJobLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetJobLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetJobLink record) {
        int insertResult = runSetJobLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetJobLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetJobLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetJobLink record = records.get(i);
            if (runSetJobLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetJobLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetJobLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetJobLink record = records.get(i);
            if (runSetJobLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetJobLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetJobLink record) {
        int insertResult = runSetJobLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetJobLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSetJobLink> selectByExample(RunSetJobLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetJobLink.class, example);
        List<RunSetJobLink> selectResult = new ArrayList<RunSetJobLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetJobLink>>() {
                    });
        }
        else {
            selectResult = runSetJobLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetJobLink> selectByExampleWithRowbounds(RunSetJobLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetJobLink.class, example, rowBounds);
        List<RunSetJobLink> selectResult = new ArrayList<RunSetJobLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetJobLink>>() {
                    });
        }
        else {
            selectResult = runSetJobLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetJobLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetJobLink.class, id);
        RunSetJobLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunSetJobLink>() {
                    });
        }
        else {
            selectResult = runSetJobLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetJobLink selectOneByExample(RunSetJobLinkExample example) {
        RunSetJobLink selectResult = runSetJobLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetJobLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetJobLink record, RunSetJobLinkExample example) {
        int updateResult = runSetJobLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetJobLink record, RunSetJobLinkExample example) {
        int updateResult = runSetJobLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetJobLink record) {
        int updateResult = runSetJobLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetJobLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetJobLink record) {
        int updateResult = runSetJobLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetJobLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetJobLink.class));
        }
        return updateResult;
    }
}
