package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;

public interface RunSetResultService {
    long countByExample(RunSetResultExample example);

    int deleteByExample(RunSetResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetResult record);

    List<Long> insertRecords(List<RunSetResult> records);

    List<Long> insertRecordsSelective(List<RunSetResult> records);

    int insertSelective(RunSetResult record);

    List<RunSetResult> selectByExample(RunSetResultExample example);

    List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample example, RowBounds rowBounds);

    RunSetResult selectByPrimaryKey(Long id);

    RunSetResult selectOneByExample(RunSetResultExample example);

    int updateByExample(RunSetResult record, RunSetResultExample example);

    int updateByExampleSelective(RunSetResult record, RunSetResultExample example);

    int updateByPrimaryKey(RunSetResult record);

    int updateByPrimaryKeySelective(RunSetResult record);
}