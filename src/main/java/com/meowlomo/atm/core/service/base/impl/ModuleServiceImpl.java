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
import com.meowlomo.atm.core.mapper.ModuleMapper;
import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;
import com.meowlomo.atm.core.model.TestCaseModuleLink;
import com.meowlomo.atm.core.service.base.ModuleService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private RedisService<Module> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ModuleExample example) {
        return moduleMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ModuleExample example) {
        int deleteResult = moduleMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = moduleMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Module.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Module record) {
        int insertResult = moduleMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Module.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Module> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Module record = records.get(i);
            if (moduleMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Module.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Module> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Module record = records.get(i);
            if (moduleMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Module.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Module record) {
        int insertResult = moduleMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Module.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(ModuleExample example) {
        int deleteResult = moduleMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = moduleMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Module.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
        }
        return deleteResult;
    }

    @Override
    public List<Module> selectByExample(ModuleExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Module.class, example);
        List<Module> selectResult = new ArrayList<Module>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Module>>() {
            });
        }
        else {
            selectResult = moduleMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Module> selectByExampleWithRowbounds(ModuleExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Module.class, example, rowBounds);
        List<Module> selectResult = new ArrayList<Module>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Module>>() {
            });
        }
        else {
            selectResult = moduleMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Module selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Module.class, id);
        Module selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Module>() {
            });
        }
        else {
            selectResult = moduleMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Module selectOneByExample(ModuleExample example) {
        Module selectResult = moduleMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Module.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Module record, ModuleExample example) {
        int updateResult = moduleMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Module.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Module record, ModuleExample example) {
        int updateResult = moduleMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Module.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Module record) {
        int updateResult = moduleMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Module.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Module record) {
        int updateResult = moduleMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Module.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Module.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseModuleLink.class));
        }
        return updateResult;
    }
}
