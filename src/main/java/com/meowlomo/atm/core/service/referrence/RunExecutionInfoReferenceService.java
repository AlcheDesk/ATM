package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunExecutionInfo;
import com.meowlomo.atm.core.model.RunExecutionInfoExample;

public interface RunExecutionInfoReferenceService {
    long countByExample(RunExecutionInfoExample example);

    List<RunExecutionInfo> selectByExample(RunExecutionInfoExample example);

    List<RunExecutionInfo> selectByExampleWithRowbounds(RunExecutionInfoExample example, RowBounds rowBounds);

    RunExecutionInfo selectByPrimaryKey(Long runId);

    RunExecutionInfo selectOneByExample(RunExecutionInfoExample example);

}