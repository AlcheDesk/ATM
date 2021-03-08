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
import com.meowlomo.atm.core.mapper.GroupMapper;
import com.meowlomo.atm.core.model.Group;
import com.meowlomo.atm.core.model.GroupExample;
import com.meowlomo.atm.core.service.base.GroupService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GroupServiceImpl implements GroupService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private RedisService<Group> redisService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(GroupExample example) {
        return groupMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(GroupExample example) {
        int deleteResult = groupMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = groupMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Group.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Group record) {
        int insertResult = groupMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Group.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Group> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Group record = records.get(i);
            if (groupMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Group.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Group> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Group record = records.get(i);
            if (groupMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Group.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Group record) {
        int insertResult = groupMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Group.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return insertResult;
    }

    @Override
    public List<Group> selectByExample(GroupExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Group.class, example);
        List<Group> selectResult = new ArrayList<Group>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Group>>() {
            });
        }
        else {
            selectResult = groupMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Group> selectByExampleWithRowbounds(GroupExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Group.class, example, rowBounds);
        List<Group> selectResult = new ArrayList<Group>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey), new TypeReference<List<Group>>() {
            });
        }
        else {
            selectResult = groupMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Group selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Group.class, id);
        Group selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Group>() {
            });
        }
        else {
            selectResult = groupMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Group selectOneByExample(GroupExample example) {
        Group selectResult = groupMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Group.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Group record, GroupExample example) {
        int updateResult = groupMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Group record, GroupExample example) {
        int updateResult = groupMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Group record) {
        int updateResult = groupMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Group.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Group record) {
        int updateResult = groupMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Group.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Group.class));
        }
        return updateResult;
    }
}
