package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetAliasLink;
import com.meowlomo.atm.core.model.RunSetAliasLinkExample;

public interface RunSetAliasLinkService {
    long countByExample(RunSetAliasLinkExample example);

    int deleteByExample(RunSetAliasLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetAliasLink record);

    List<Long> insertRecords(List<RunSetAliasLink> records);

    List<Long> insertRecordsSelective(List<RunSetAliasLink> records);

    int insertSelective(RunSetAliasLink record);

    List<RunSetAliasLink> selectByExample(RunSetAliasLinkExample example);

    List<RunSetAliasLink> selectByExampleWithRowbounds(RunSetAliasLinkExample example, RowBounds rowBounds);

    RunSetAliasLink selectByPrimaryKey(Long id);

    RunSetAliasLink selectOneByExample(RunSetAliasLinkExample example);

    int updateByExample(RunSetAliasLink record, RunSetAliasLinkExample example);

    int updateByExampleSelective(RunSetAliasLink record, RunSetAliasLinkExample example);

    int updateByPrimaryKey(RunSetAliasLink record);

    int updateByPrimaryKeySelective(RunSetAliasLink record);
}