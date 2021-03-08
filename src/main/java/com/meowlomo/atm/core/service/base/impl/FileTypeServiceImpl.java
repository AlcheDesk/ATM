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
import com.meowlomo.atm.core.mapper.FileTypeMapper;
import com.meowlomo.atm.core.model.FileType;
import com.meowlomo.atm.core.model.FileTypeExample;
import com.meowlomo.atm.core.service.base.FileTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FileTypeServiceImpl implements FileTypeService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private FileTypeMapper fileTypeMapper;
    @Autowired
    private RedisService<FileType> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(FileTypeExample example) {
        return fileTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(FileTypeExample example) {
        int deleteResult = fileTypeMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = fileTypeMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(FileType.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(FileType record) {
        int insertResult = fileTypeMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(FileType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<FileType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            FileType record = records.get(i);
            if (fileTypeMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(FileType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<FileType> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            FileType record = records.get(i);
            if (fileTypeMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(FileType.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(FileType record) {
        int insertResult = fileTypeMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(FileType.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return insertResult;
    }

    @Override
    public List<FileType> selectByExample(FileTypeExample example) {
        String redisKey = cacheKeyGenerator.generateKey(FileType.class, example);
        List<FileType> selectResult = new ArrayList<FileType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<FileType>>() {
                    });
        }
        else {
            selectResult = fileTypeMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<FileType> selectByExampleWithRowbounds(FileTypeExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(FileType.class, example, rowBounds);
        List<FileType> selectResult = new ArrayList<FileType>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<FileType>>() {
                    });
        }
        else {
            selectResult = fileTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public FileType selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(FileType.class, id);
        FileType selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<FileType>() {
            });
        }
        else {
            selectResult = fileTypeMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public FileType selectOneByExample(FileTypeExample example) {
        FileType selectResult = fileTypeMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(FileType.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(FileType record, FileTypeExample example) {
        int updateResult = fileTypeMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(FileType record, FileTypeExample example) {
        int updateResult = fileTypeMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(FileType record) {
        int updateResult = fileTypeMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(FileType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(FileType record) {
        int updateResult = fileTypeMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(FileType.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(FileType.class));
        }
        return updateResult;
    }
}
