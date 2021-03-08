package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunTaskLink;
import com.meowlomo.atm.core.model.RunTaskLinkExample;

public interface RunTaskLinkService {
    long countByExample(RunTaskLinkExample example);

    int deleteByExample(RunTaskLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunTaskLink record);

    List<Long> insertRecords(List<RunTaskLink> records);

    List<Long> insertRecordsSelective(List<RunTaskLink> records);

    int insertSelective(RunTaskLink record);

    List<RunTaskLink> selectByExample(RunTaskLinkExample example);

    List<RunTaskLink> selectByExampleWithRowbounds(RunTaskLinkExample example, RowBounds rowBounds);

    RunTaskLink selectByPrimaryKey(Long id);

    RunTaskLink selectOneByExample(RunTaskLinkExample example);

    int updateByExample(RunTaskLink record, RunTaskLinkExample example);

    int updateByExampleSelective(RunTaskLink record, RunTaskLinkExample example);

    int updateByPrimaryKey(RunTaskLink record);

    int updateByPrimaryKeySelective(RunTaskLink record);
}