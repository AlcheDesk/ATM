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
import com.meowlomo.atm.core.mapper.ColorMapper;
import com.meowlomo.atm.core.model.Color;
import com.meowlomo.atm.core.model.ColorExample;
import com.meowlomo.atm.core.service.base.ColorService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ColorServiceImpl implements ColorService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private RedisService<Color> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(ColorExample example) {
        return colorMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ColorExample example) {
        int deleteResult = colorMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = colorMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Color.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Color record) {
        int insertResult = colorMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Color.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Color> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Color record = records.get(i);
            if (colorMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Color.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Color> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Color record = records.get(i);
            if (colorMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Color.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Color record) {
        int insertResult = colorMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Color.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return insertResult;
    }

    @Override
    public List<Color> selectByExample(ColorExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Color.class, example);
        List<Color> selectResult = new ArrayList<Color>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Color>>() {
            });
        }
        else {
            selectResult = colorMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Color> selectByExampleWithRowbounds(ColorExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Color.class, example, rowBounds);
        List<Color> selectResult = new ArrayList<Color>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Color>>() {
            });
        }
        else {
            selectResult = colorMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Color selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Color.class, id);
        Color selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Color>() {
            });
        }
        else {
            selectResult = colorMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Color selectOneByExample(ColorExample example) {
        Color selectResult = colorMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Color.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Color record, ColorExample example) {
        int updateResult = colorMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Color record, ColorExample example) {
        int updateResult = colorMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Color record) {
        int updateResult = colorMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Color.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Color record) {
        int updateResult = colorMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Color.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Color.class));
        }
        return updateResult;
    }
}
