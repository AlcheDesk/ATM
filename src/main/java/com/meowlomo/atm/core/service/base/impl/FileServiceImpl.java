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
import com.meowlomo.atm.core.mapper.DevFileMapper;
import com.meowlomo.atm.core.mapper.ProdFileMapper;
import com.meowlomo.atm.core.model.File;
import com.meowlomo.atm.core.model.FileExample;
import com.meowlomo.atm.core.service.base.FileService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private DevFileMapper devFileMapper;
    @Autowired
    private ProdFileMapper prodFileMapper;
    @Autowired
    private RedisService<File> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(FileExample example, String mode) {
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            return devFileMapper.countByExample(example);
        }
        else {
            return prodFileMapper.countByExample(example);
        }
    }

    @Override
    public int deleteByExample(FileExample example, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devFileMapper.deleteByExample(example);
        }
        else {
            deleteResult = prodFileMapper.deleteByExample(example);
        }
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id, String mode) {
        int deleteResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            deleteResult = devFileMapper.deleteByPrimaryKey(id);
        }
        else {
            deleteResult = prodFileMapper.deleteByPrimaryKey(id);
        }
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(File.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(File record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devFileMapper.insert(record);
        }
        else {
            insertResult = prodFileMapper.insert(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(File.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<File> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            File record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devFileMapper.insert(record);
            }
            else {
                insertResult = prodFileMapper.insert(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(File.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<File> records, String mode) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            File record = records.get(i);
            int insertResult = 0;
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                insertResult = devFileMapper.insertSelective(record);
            }
            else {
                insertResult = prodFileMapper.insertSelective(record);
            }
            if (insertResult == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(File.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(File record, String mode) {
        int insertResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            insertResult = devFileMapper.insertSelective(record);
        }
        else {
            insertResult = prodFileMapper.insertSelective(record);
        }
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(File.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return insertResult;
    }

    @Override
    public List<File> selectByExample(FileExample example, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(File.class, example, mode);
        List<File> selectResult = new ArrayList<File>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<File>>() {
            });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devFileMapper.selectByExample(example);
            }
            else {
                selectResult = prodFileMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<File> selectByExampleWithRowbounds(FileExample example, RowBounds rowBounds, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(File.class, example, rowBounds, mode);
        List<File> selectResult = new ArrayList<File>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<File>>() {
            });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devFileMapper.selectByExample(example);
            }
            else {
                selectResult = prodFileMapper.selectByExample(example);
            }
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public File selectByPrimaryKey(Long id, String mode) {
        String redisKey = cacheKeyGenerator.generateKey(File.class, id, mode);
        File selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<File>() {
            });
        }
        else {
            if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
                selectResult = devFileMapper.selectByPrimaryKey(id);
            }
            else {
                selectResult = prodFileMapper.selectByPrimaryKey(id);
            }
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public File selectOneByExample(FileExample example, String mode) {
        File selectResult = null;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            selectResult = devFileMapper.selectOneByExample(example);
        }
        else {
            selectResult = prodFileMapper.selectOneByExample(example);
        }
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(File.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(File record, FileExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devFileMapper.updateByExample(record, example);
        }
        else {
            updateResult = prodFileMapper.updateByExample(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(File.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(File record, FileExample example, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devFileMapper.updateByExampleSelective(record, example);
        }
        else {
            updateResult = prodFileMapper.updateByExampleSelective(record, example);
        }
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(File.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(File record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devFileMapper.updateByPrimaryKey(record);
        }
        else {
            updateResult = prodFileMapper.updateByPrimaryKey(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(File.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(File record, String mode) {
        int updateResult = 0;
        if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
            updateResult = devFileMapper.updateByPrimaryKeySelective(record);
        }
        else {
            updateResult = prodFileMapper.updateByPrimaryKeySelective(record);
        }
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(File.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(File.class));
        }
        return updateResult;
    }
}
