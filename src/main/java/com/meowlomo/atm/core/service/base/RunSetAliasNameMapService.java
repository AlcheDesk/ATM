package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetAliasNameMap;
import com.meowlomo.atm.core.model.RunSetAliasNameMapExample;

public interface RunSetAliasNameMapService {
    long countByExample(RunSetAliasNameMapExample example);

    int deleteByExample(RunSetAliasNameMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetAliasNameMap record);

    List<Long> insertRecords(List<RunSetAliasNameMap> records);

    List<Long> insertRecordsSelective(List<RunSetAliasNameMap> records);

    int insertSelective(RunSetAliasNameMap record);

    List<RunSetAliasNameMap> selectByExample(RunSetAliasNameMapExample example);

    List<RunSetAliasNameMap> selectByExampleWithRowbounds(RunSetAliasNameMapExample example, RowBounds rowBounds);

    RunSetAliasNameMap selectByPrimaryKey(Long id);

    RunSetAliasNameMap selectOneByExample(RunSetAliasNameMapExample example);

    int updateByExample(RunSetAliasNameMap record, RunSetAliasNameMapExample example);

    int updateByExampleSelective(RunSetAliasNameMap record, RunSetAliasNameMapExample example);

    int updateByPrimaryKey(RunSetAliasNameMap record);

    int updateByPrimaryKeySelective(RunSetAliasNameMap record);
}