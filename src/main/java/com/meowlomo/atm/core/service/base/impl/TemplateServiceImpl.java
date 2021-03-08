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
import com.meowlomo.atm.core.mapper.TemplateMapper;
import com.meowlomo.atm.core.model.Template;
import com.meowlomo.atm.core.model.TemplateExample;
import com.meowlomo.atm.core.service.base.TemplateService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Template> redisService;
    @Autowired
    private TemplateMapper templateMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(TemplateExample example) {
        return templateMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TemplateExample example) {
        int deleteResult = templateMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = templateMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Template.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Template record) {
        int insertResult = templateMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Template.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Template> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Template record = records.get(i);
            if (templateMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Template.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Template> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Template record = records.get(i);
            if (templateMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Template.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Template record) {
        int insertResult = templateMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Template.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(TemplateExample example) {
        int deleteResult = templateMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = templateMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Template.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return deleteResult;
    }

    @Override
    public List<Template> selectByExample(TemplateExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Template.class, example);
        List<Template> selectResult = new ArrayList<Template>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Template>>() {
                    });
        }
        else {
            selectResult = templateMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Template> selectByExampleWithRowbounds(TemplateExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Template.class, example, rowBounds);
        List<Template> selectResult = new ArrayList<Template>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Template>>() {
                    });
        }
        else {
            selectResult = templateMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Template selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Template.class, id);
        Template selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Template>() {
            });
        }
        else {
            selectResult = templateMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Template selectOneByExample(TemplateExample example) {
        Template selectResult = templateMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Template.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Template record, TemplateExample example) {
        int updateResult = templateMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Template record, TemplateExample example) {
        int updateResult = templateMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Template record) {
        int updateResult = templateMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Template.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Template record) {
        int updateResult = templateMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Template.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Template.class));
        }
        return updateResult;
    }
}
