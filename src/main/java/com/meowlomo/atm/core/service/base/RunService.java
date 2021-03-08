package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;

public interface RunService {
    long countByExample(RunExample example);

    int deleteByExample(RunExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Run record);

    List<Long> insertRecords(List<Run> records);

    List<Long> insertRecordsSelective(List<Run> records);

    int insertSelective(Run record);

    List<Run> selectByExample(RunExample example);

    List<Run> selectByExampleWithRowbounds(RunExample example, RowBounds rowBounds);

    Run selectByPrimaryKey(Long id);

    Run selectOneByExample(RunExample example);

    int updateByExample(Run record, RunExample example);

    int updateByExampleSelective(Run record, RunExample example);

    int updateByPrimaryKey(Run record);

    int updateByPrimaryKeySelective(Run record);
}