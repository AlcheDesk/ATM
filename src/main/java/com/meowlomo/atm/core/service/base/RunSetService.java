package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;

public interface RunSetService {
    long countByExample(RunSetExample example);

    int deleteByExample(RunSetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSet record);

    List<Long> insertRecords(List<RunSet> records);

    List<Long> insertRecordsSelective(List<RunSet> records);

    int insertSelective(RunSet record);

    int logicalDeleteByExample(RunSetExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<RunSet> selectByExample(RunSetExample example);

    List<RunSet> selectByExampleWithRowbounds(RunSetExample example, RowBounds rowBounds);

    RunSet selectByPrimaryKey(Long id);

    RunSet selectOneByExample(RunSetExample example);

    int updateByExample(RunSet record, RunSetExample example);

    int updateByExampleSelective(RunSet record, RunSetExample example);

    int updateByPrimaryKey(RunSet record);

    int updateByPrimaryKeySelective(RunSet record);
}