package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetType;
import com.meowlomo.atm.core.model.RunSetTypeExample;

public interface RunSetTypeService {
    long countByExample(RunSetTypeExample example);

    int deleteByExample(RunSetTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetType record);

    List<Long> insertRecords(List<RunSetType> records);

    List<Long> insertRecordsSelective(List<RunSetType> records);

    int insertSelective(RunSetType record);

    List<RunSetType> selectByExample(RunSetTypeExample example);

    List<RunSetType> selectByExampleWithRowbounds(RunSetTypeExample example, RowBounds rowBounds);

    RunSetType selectByPrimaryKey(Long id);

    RunSetType selectOneByExample(RunSetTypeExample example);

    int updateByExample(RunSetType record, RunSetTypeExample example);

    int updateByExampleSelective(RunSetType record, RunSetTypeExample example);

    int updateByPrimaryKey(RunSetType record);

    int updateByPrimaryKeySelective(RunSetType record);
}