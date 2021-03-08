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
import com.meowlomo.atm.core.mapper.RunSetAliasLinkMapper;
import com.meowlomo.atm.core.model.RunSetAliasLink;
import com.meowlomo.atm.core.model.RunSetAliasLinkExample;
import com.meowlomo.atm.core.service.base.RunSetAliasLinkService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetAliasLinkServiceImpl implements RunSetAliasLinkService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetAliasLink> redisService;
    @Autowired
    private RunSetAliasLinkMapper runSetAliasLinkMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(RunSetAliasLinkExample example) {
        return runSetAliasLinkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RunSetAliasLinkExample example) {
        int deleteResult = runSetAliasLinkMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = runSetAliasLinkMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(RunSetAliasLink.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(RunSetAliasLink record) {
        int insertResult = runSetAliasLinkMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<RunSetAliasLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetAliasLink record = records.get(i);
            if (runSetAliasLinkMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<RunSetAliasLink> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            RunSetAliasLink record = records.get(i);
            if (runSetAliasLinkMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasLink.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(RunSetAliasLink record) {
        int insertResult = runSetAliasLinkMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasLink.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return insertResult;
    }

    @Override
    public List<RunSetAliasLink> selectByExample(RunSetAliasLinkExample example) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetAliasLink.class, example);
        List<RunSetAliasLink> selectResult = new ArrayList<RunSetAliasLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetAliasLink>>() {
                    });
        }
        else {
            selectResult = runSetAliasLinkMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<RunSetAliasLink> selectByExampleWithRowbounds(RunSetAliasLinkExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetAliasLink.class, example, rowBounds);
        List<RunSetAliasLink> selectResult = new ArrayList<RunSetAliasLink>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<RunSetAliasLink>>() {
                    });
        }
        else {
            selectResult = runSetAliasLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetAliasLink selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(RunSetAliasLink.class, id);
        RunSetAliasLink selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey),
                    new TypeReference<RunSetAliasLink>() {
                    });
        }
        else {
            selectResult = runSetAliasLinkMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public RunSetAliasLink selectOneByExample(RunSetAliasLinkExample example) {
        RunSetAliasLink selectResult = runSetAliasLinkMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetAliasLink.class, selectResult.getId()),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(RunSetAliasLink record, RunSetAliasLinkExample example) {
        int updateResult = runSetAliasLinkMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(RunSetAliasLink record, RunSetAliasLinkExample example) {
        int updateResult = runSetAliasLinkMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(RunSetAliasLink record) {
        int updateResult = runSetAliasLinkMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetAliasLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(RunSetAliasLink record) {
        int updateResult = runSetAliasLinkMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(RunSetAliasLink.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(RunSetAliasLink.class));
        }
        return updateResult;
    }
}
