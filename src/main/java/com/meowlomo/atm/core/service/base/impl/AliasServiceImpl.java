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
import com.meowlomo.atm.core.mapper.AliasMapper;
import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.AliasExample;
import com.meowlomo.atm.core.service.base.AliasService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AliasServiceImpl implements AliasService {

    @Autowired
    private AliasMapper aliasMapper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Alias> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(AliasExample example) {
        return aliasMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AliasExample example) {
        int deleteResult = aliasMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = aliasMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Alias.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Alias record) {
        int insertResult = aliasMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Alias.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Alias> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Alias record = records.get(i);
            if (aliasMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Alias.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Alias> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Alias record = records.get(i);
            if (aliasMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Alias.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Alias record) {
        int insertResult = aliasMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Alias.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(AliasExample example) {
        int deleteResult = aliasMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = aliasMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Alias.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return deleteResult;
    }

    @Override
    public List<Alias> selectByExample(AliasExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Alias.class, example);
        List<Alias> selectResult = new ArrayList<Alias>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Alias>>() {
            });
        }
        else {
            selectResult = aliasMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Alias> selectByExampleWithRowbounds(AliasExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Alias.class, example, rowBounds);
        List<Alias> selectResult = new ArrayList<Alias>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Alias>>() {
            });
        }
        else {
            selectResult = aliasMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Alias selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Alias.class, id);
        Alias selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Alias>() {
            });
        }
        else {
            selectResult = aliasMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Alias selectOneByExample(AliasExample example) {
        Alias selectResult = aliasMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Alias.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Alias record, AliasExample example) {
        int updateResult = aliasMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Alias record, AliasExample example) {
        int updateResult = aliasMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Alias record) {
        int updateResult = aliasMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Alias.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Alias record) {
        int updateResult = aliasMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Alias.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Alias.class));
        }
        return updateResult;
    }
}
