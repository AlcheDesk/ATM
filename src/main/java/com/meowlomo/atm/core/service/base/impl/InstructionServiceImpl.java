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
import com.meowlomo.atm.core.mapper.InstructionMapper;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;
import com.meowlomo.atm.core.model.TestCaseDriverAliasMap;
import com.meowlomo.atm.core.model.TestCaseInstructionTypeMap;
import com.meowlomo.atm.core.service.autogen.InstructionAutoGenService;
import com.meowlomo.atm.core.service.base.InstructionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InstructionServiceImpl implements InstructionService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private InstructionMapper instructionMapper;
    @Autowired
    private RedisService<Instruction> redisService;
    @Autowired
    private InstructionAutoGenService instructionAutoGenService;
    @Value("${meowlomo.redis.default.time-to-live-in-seconds:60}")
    private long expireTimeInSeconds;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Override
    public long countByExample(InstructionExample example) {
        return instructionMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(InstructionExample example) {
        int deleteResult = instructionMapper.deleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
        }
        return deleteResult;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteResult = instructionMapper.deleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Instruction.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
        }
        return deleteResult;
    }

    @Override
    public int insert(Instruction record) {
        record = instructionAutoGenService.autoGenFields(record);
        int insertResult = instructionMapper.insert(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Instruction.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public List<Long> insertRecords(List<Instruction> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Instruction record = records.get(i);
            if (instructionMapper.insert(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Instruction.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public List<Long> insertRecordsSelective(List<Instruction> records) {
        List<Long> insertIds = new ArrayList<Long>();
        for (int i = 0; i < records.size(); i++) {
            Instruction record = records.get(i);
            record = instructionAutoGenService.autoGenFields(record);
            record = instructionAutoGenService.autoGenFields(record);
            if (instructionMapper.insertSelective(record) == 1) {
                redisService.putValue(cacheKeyGenerator.generateKey(Instruction.class, record.getId()), record);
                insertIds.add(record.getId());
            }
            record = null;
        }
        if (!insertIds.isEmpty()) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertIds;
    }

    @Override
    public int insertSelective(Instruction record) {
        record = instructionAutoGenService.autoGenFields(record);
        int insertResult = instructionMapper.insertSelective(record);
        if (insertResult > 0) {
            redisService.putValue(cacheKeyGenerator.generateKey(Instruction.class, record.getId()), record);
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return insertResult;
    }

    @Override
    public int logicalDeleteByExample(InstructionExample example) {
        int deleteResult = instructionMapper.logicalDeleteByExample(example);
        if (deleteResult > 0) {
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
        }
        return deleteResult;
    }

    @Override
    public int logicalDeleteByPrimaryKey(Long id) {
        int deleteResult = instructionMapper.logicalDeleteByPrimaryKey(id);
        if (deleteResult > 0) {
            redisService.deleteByKeyPattern(cacheKeyGenerator.generateKey(Instruction.class, id));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
        }
        return deleteResult;
    }

    @Override
    public List<Instruction> selectByExample(InstructionExample example) {
        String redisKey = cacheKeyGenerator.generateKey(Instruction.class, example);
        List<Instruction> selectResult = new ArrayList<Instruction>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Instruction>>() {
                    });
        }
        else {
            selectResult = instructionMapper.selectByExample(example);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public List<Instruction> selectByExampleWithRowbounds(InstructionExample example, RowBounds rowBounds) {
        String redisKey = cacheKeyGenerator.generateKey(Instruction.class, example, rowBounds);
        List<Instruction> selectResult = new ArrayList<Instruction>();
        if (redisService.hasList(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getList(redisKey),
                    new TypeReference<List<Instruction>>() {
                    });
        }
        else {
            selectResult = instructionMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (!selectResult.isEmpty()) {
                redisService.putList(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Instruction selectByPrimaryKey(Long id) {
        String redisKey = cacheKeyGenerator.generateKey(Instruction.class, id);
        Instruction selectResult = null;
        if (redisService.hasKey(redisKey)) {
            ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
            selectResult = objectMapper.convertValue(redisService.getValue(redisKey), new TypeReference<Instruction>() {
            });
        }
        else {
            selectResult = instructionMapper.selectByPrimaryKey(id);
            if (selectResult != null) {
                redisService.putValue(redisKey, selectResult);
                redisService.setExpire(redisKey, expireTimeInSeconds, TimeUnit.SECONDS);
            }
        }
        return selectResult;
    }

    @Override
    public Instruction selectOneByExample(InstructionExample example) {
        Instruction selectResult = instructionMapper.selectOneByExample(example);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Instruction.class, selectResult.getId()), selectResult);
        }
        return selectResult;
    }

    @Override
    public int updateByExample(Instruction record, InstructionExample example) {
        int updateResult = instructionMapper.updateByExample(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByExampleSelective(Instruction record, InstructionExample example) {
        int updateResult = instructionMapper.updateByExampleSelective(record, example);
        if (updateResult > 0) {
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKey(Instruction record) {
        int updateResult = instructionMapper.updateByPrimaryKey(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Instruction.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
        }
        return updateResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Instruction record) {
        int updateResult = instructionMapper.updateByPrimaryKeySelective(record);
        if (updateResult > 0) {
            redisService.delete(cacheKeyGenerator.generateKey(Instruction.class, record.getId()));
            redisService.deleteListPattern(cacheKeyGenerator.generateClassKey(Instruction.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseInstructionTypeMap.class));
            redisService.deleteAllMatch(cacheKeyGenerator.generateClassKey(TestCaseDriverAliasMap.class));
        }
        return updateResult;
    }
}
