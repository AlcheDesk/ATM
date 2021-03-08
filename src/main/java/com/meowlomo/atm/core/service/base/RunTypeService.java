package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunType;
import com.meowlomo.atm.core.model.RunTypeExample;

public interface RunTypeService {
    long countByExample(RunTypeExample example);

    int deleteByExample(RunTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunType record);

    List<Long> insertRecords(List<RunType> records);

    List<Long> insertRecordsSelective(List<RunType> records);

    int insertSelective(RunType record);

    List<RunType> selectByExample(RunTypeExample example);

    List<RunType> selectByExampleWithRowbounds(RunTypeExample example, RowBounds rowBounds);

    RunType selectByPrimaryKey(Long id);

    RunType selectOneByExample(RunTypeExample example);

    int updateByExample(RunType record, RunTypeExample example);

    int updateByExampleSelective(RunType record, RunTypeExample example);

    int updateByPrimaryKey(RunType record);

    int updateByPrimaryKeySelective(RunType record);
}