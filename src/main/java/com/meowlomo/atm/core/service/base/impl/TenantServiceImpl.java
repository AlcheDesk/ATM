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
import com.meowlomo.atm.core.mapper.TenantMapper;
import com.meowlomo.atm.core.model.Tenant;
import com.meowlomo.atm.core.model.TenantExample;
import com.meowlomo.atm.core.service.base.TenantService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TenantServiceImpl implements TenantService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Tenant> redisService;
    @Autowired
    private TenantMapper tenantMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TenantExample example) {
        return tenantMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TenantExample example) {
        int deleteResult = tenantMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = tenantMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Tenant.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Tenant record) {
        int insertResult = tenantMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Tenant.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Tenant> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Tenant record = records.get(i);
            if (tenantMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Tenant.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Tenant> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Tenant record = records.get(i);
            if (tenantMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Tenant.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Tenant record) {
        int insertResult = tenantMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Tenant.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return insertResult;
    }

    @Override
    public List<Tenant> selectByExample(TenantExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Tenant.class, example);
        List<Tenant> selectResult = new ArrayList<Tenant>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Tenant>>() {
            });
        }
        else {
            selectResult = tenantMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Tenant> selectByExampleWithRowbounds(TenantExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Tenant.class, example, rowBounds);
        List<Tenant> selectResult = new ArrayList<Tenant>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Tenant>>() {
            });
        }
        else {
            selectResult = tenantMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Tenant selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Tenant.class, id);
        Tenant selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Tenant>() {
            });
        }
        else {
            selectResult = tenantMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Tenant selectOneByExample(TenantExample example) {
        Tenant selectResult = tenantMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Tenant.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Tenant record, TenantExample example) {
        int updateResult = tenantMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Tenant record, TenantExample example) {
        int updateResult = tenantMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Tenant record) {
        int updateResult = tenantMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Tenant.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Tenant record) {
        int updateResult = tenantMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Tenant.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Tenant.class));
        }
        return updateResult;
    }
}
