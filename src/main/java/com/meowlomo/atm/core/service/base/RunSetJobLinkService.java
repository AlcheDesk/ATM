package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetJobLink;
import com.meowlomo.atm.core.model.RunSetJobLinkExample;

public interface RunSetJobLinkService {
    long countByExample(RunSetJobLinkExample example);

    int deleteByExample(RunSetJobLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetJobLink record);

    List<Long> insertRecords(List<RunSetJobLink> records);

    List<Long> insertRecordsSelective(List<RunSetJobLink> records);

    int insertSelective(RunSetJobLink record);

    List<RunSetJobLink> selectByExample(RunSetJobLinkExample example);

    List<RunSetJobLink> selectByExampleWithRowbounds(RunSetJobLinkExample example, RowBounds rowBounds);

    RunSetJobLink selectByPrimaryKey(Long id);

    RunSetJobLink selectOneByExample(RunSetJobLinkExample example);

    int updateByExample(RunSetJobLink record, RunSetJobLinkExample example);

    int updateByExampleSelective(RunSetJobLink record, RunSetJobLinkExample example);

    int updateByPrimaryKey(RunSetJobLink record);

    int updateByPrimaryKeySelective(RunSetJobLink record);
}