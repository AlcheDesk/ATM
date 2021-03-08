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
import com.meowlomo.atm.core.mapper.ResourceMapper;
import com.meowlomo.atm.core.model.Resource;
import com.meowlomo.atm.core.model.ResourceExample;
import com.meowlomo.atm.core.service.base.ResourceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Resource> redisService;
    @Autowired
    private ResourceMapper resourceMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ResourceExample example) {
        return resourceMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ResourceExample example) {
        int deleteResult = resourceMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = resourceMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Resource.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Resource record) {
        int insertResult = resourceMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Resource.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Resource> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Resource record = records.get(i);
            if (resourceMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Resource.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Resource> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Resource record = records.get(i);
            if (resourceMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Resource.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Resource record) {
        int insertResult = resourceMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Resource.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return insertResult;
    }

    @Override
    public List<Resource> selectByExample(ResourceExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Resource.class, example);
        List<Resource> selectResult = new ArrayList<Resource>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Resource>>() {
                    });
        }
        else {
            selectResult = resourceMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Resource> selectByExampleWithRowbounds(ResourceExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Resource.class, example, rowBounds);
        List<Resource> selectResult = new ArrayList<Resource>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Resource>>() {
                    });
        }
        else {
            selectResult = resourceMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Resource selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Resource.class, id);
        Resource selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Resource>() {
            });
        }
        else {
            selectResult = resourceMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Resource selectOneByExample(ResourceExample example) {
        Resource selectResult = resourceMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Resource.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Resource record, ResourceExample example) {
        int updateResult = resourceMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Resource record, ResourceExample example) {
        int updateResult = resourceMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Resource record) {
        int updateResult = resourceMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Resource.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Resource record) {
        int updateResult = resourceMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Resource.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Resource.class));
        }
        return updateResult;
    }
}
