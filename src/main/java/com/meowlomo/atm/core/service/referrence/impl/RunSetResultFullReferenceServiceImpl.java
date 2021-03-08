package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.RunSetResultFullReferenceMapper;
import com.meowlomo.atm.core.mapper.RunSetResultMapper;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;
import com.meowlomo.atm.core.service.referrence.RunSetResultFullReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultFullReferenceServiceImpl implements RunSetResultFullReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<RunSetResult> redisService;
    @Autowired
    private RunSetResultMapper runSetResultMapper;
    @Autowired
    private RunSetResultFullReferenceMapper runSetResultFullReferenceMapper;

    // @Override
    // public long countByExample(RunSetResultExample example) {
    // return runSetResultMapper.countByExample(example);
    // }
    //
    // @Override
    // public List<RunSetResult> selectByExample(RunSetResultExample example) {
    // List<RunSetResult> records =
    // runSetResultReferenceMapper.selectByExample(example);
    // if (records.isEmpty()) { return records; }
    // List<RunSetResult> returnRecords = new ArrayList<RunSetResult>();
    // for (RunSetResult runSetResult : records) {
    // // get the run first
    // List<Run> runs = runSetResult.getRuns();
    // List<Run> runRecords = new ArrayList<Run>();
    // for (Run run : runs) {
    // InstructionResultExample instructionResultExample = new
    // InstructionResultExample();
    // instructionResultExample.createCriteria().andRunIdEqualTo(run.getId());
    // instructionResultExample.setOrderByClause(" id ");
    // List<InstructionResult> instrcutionResults = instructionResultService
    // .selectByExample(instructionResultExample, run.getType());
    // run.setInstructionResults(instrcutionResults);
    // runRecords.add(run);
    // }
    // runSetResult.setRuns(runRecords);
    // returnRecords.add(runSetResult);
    // }
    // return returnRecords;
    // }
    //
    // @Override
    // public List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample
    // example, RowBounds rowBounds) {
    // List<RunSetResult> records =
    // runSetResultReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
    // if (records.isEmpty()) { return records; }
    // List<RunSetResult> returnRecords = new ArrayList<RunSetResult>();
    // for (RunSetResult runSetResult : records) {
    // // get the run first
    // List<Run> runs = runSetResult.getRuns();
    // List<Run> runRecords = new ArrayList<Run>();
    // for (Run run : runs) {
    // InstructionResultExample instructionResultExample = new
    // InstructionResultExample();
    // instructionResultExample.createCriteria().andRunIdEqualTo(run.getId());
    // instructionResultExample.setOrderByClause(" id ");
    // List<InstructionResult> instrcutionResults = instructionResultService
    // .selectByExample(instructionResultExample, run.getType());
    // run.setInstructionResults(instrcutionResults);
    // runRecords.add(run);
    // }
    // runSetResult.setRuns(runRecords);
    // returnRecords.add(runSetResult);
    // }
    // return returnRecords;
    // }
    //
    // @Override
    // public RunSetResult selectByPrimaryKey(Long id) {
    // RunSetResult record = runSetResultReferenceMapper.selectByPrimaryKey(id);
    // if (record == null) { return record; }
    // // get the run first
    // List<Run> runs = record.getRuns();
    // List<Run> runRecords = new ArrayList<Run>();
    // for (Run run : runs) {
    // InstructionResultExample instructionResultExample = new
    // InstructionResultExample();
    // instructionResultExample.createCriteria().andRunIdEqualTo(run.getId());
    // instructionResultExample.setOrderByClause(" id ");
    // List<InstructionResult> instrcutionResults = instructionResultService
    // .selectByExample(instructionResultExample, run.getType());
    // run.setInstructionResults(instrcutionResults);
    // runRecords.add(run);
    // }
    // record.setRuns(runRecords);
    // return record;
    // }
    @Override
    public long countByExample(RunSetResultExample example) {
        return runSetResultMapper.countByExample(example);
    }

    @Override
    public List<RunSetResult> selectByExample(RunSetResultExample example) {
        List<RunSetResult> selectResult = runSetResultFullReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunSetResult.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample example, RowBounds rowBounds) {
        List<RunSetResult> selectResult = runSetResultFullReferenceMapper.selectByExampleWithRowbounds(example,
                rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(RunSetResult.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public RunSetResult selectByPrimaryKey(Long id) {
        RunSetResult selectResult = runSetResultFullReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(RunSetResult.class, id, "reference"), selectResult);
        }
        return selectResult;
    }
}
