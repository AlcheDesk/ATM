package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunExecutionInfo;
import com.meowlomo.atm.core.model.RunExecutionInfoExample;

public interface RunExecutionInfoMapper {
    long countByExample(RunExecutionInfoExample example);

    List<RunExecutionInfo> selectByExample(RunExecutionInfoExample example);

    List<RunExecutionInfo> selectByExampleSelective(@Param("example") RunExecutionInfoExample example, @Param("selective") RunExecutionInfo.Column... selective);

    List<RunExecutionInfo> selectByExampleWithRowbounds(RunExecutionInfoExample example, RowBounds rowBounds);

    RunExecutionInfo selectByPrimaryKey(Long runId);

    RunExecutionInfo selectByPrimaryKeySelective(@Param("runId") Long runId, @Param("selective") RunExecutionInfo.Column... selective);

    RunExecutionInfo selectOneByExample(RunExecutionInfoExample example);

    RunExecutionInfo selectOneByExampleSelective(@Param("example") RunExecutionInfoExample example, @Param("selective") RunExecutionInfo.Column... selective);
}