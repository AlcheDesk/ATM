package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetResultJobLink;
import com.meowlomo.atm.core.model.RunSetResultJobLinkExample;

public interface RunSetResultJobLinkService {
    long countByExample(RunSetResultJobLinkExample example);

    int deleteByExample(RunSetResultJobLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetResultJobLink record);

    List<Long> insertRecords(List<RunSetResultJobLink> records);

    List<Long> insertRecordsSelective(List<RunSetResultJobLink> records);

    int insertSelective(RunSetResultJobLink record);

    List<RunSetResultJobLink> selectByExample(RunSetResultJobLinkExample example);

    List<RunSetResultJobLink> selectByExampleWithRowbounds(RunSetResultJobLinkExample example, RowBounds rowBounds);

    RunSetResultJobLink selectByPrimaryKey(Long id);

    RunSetResultJobLink selectOneByExample(RunSetResultJobLinkExample example);

    int updateByExample(RunSetResultJobLink record, RunSetResultJobLinkExample example);

    int updateByExampleSelective(RunSetResultJobLink record, RunSetResultJobLinkExample example);

    int updateByPrimaryKey(RunSetResultJobLink record);

    int updateByPrimaryKeySelective(RunSetResultJobLink record);
}