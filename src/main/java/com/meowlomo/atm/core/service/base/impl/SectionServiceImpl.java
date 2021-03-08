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
import com.meowlomo.atm.core.mapper.SectionMapper;
import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.model.SectionExample;
import com.meowlomo.atm.core.service.base.SectionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SectionServiceImpl implements SectionService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Section> redisService;
    @Autowired
    private SectionMapper sectionMapper;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(SectionExample example) {
        return sectionMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SectionExample example) {
        int deleteResult = sectionMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = sectionMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Section.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Section record) {
        int insertResult = sectionMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Section.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Section> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Section record = records.get(i);
            if (sectionMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Section.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Section> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Section record = records.get(i);
            if (sectionMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Section.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Section record) {
        int insertResult = sectionMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Section.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(SectionExample example) {
        int deleteResult = sectionMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = sectionMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Section.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return deleteResult;
    }

    @Override
    public List<Section> selectByExample(SectionExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Section.class, example);
        List<Section> selectResult = new ArrayList<Section>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Section>>() {
                    });
        }
        else {
            selectResult = sectionMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Section> selectByExampleWithRowbounds(SectionExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Section.class, example, rowBounds);
        List<Section> selectResult = new ArrayList<Section>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Section>>() {
                    });
        }
        else {
            selectResult = sectionMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Section selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Section.class, id);
        Section selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Section>() {
            });
        }
        else {
            selectResult = sectionMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Section selectOneByExample(SectionExample example) {
        Section selectResult = sectionMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Section.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Section record, SectionExample example) {
        int updateResult = sectionMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Section record, SectionExample example) {
        int updateResult = sectionMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Section record) {
        int updateResult = sectionMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Section.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Section record) {
        int updateResult = sectionMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Section.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Section.class));
        }
        return updateResult;
    }
}
