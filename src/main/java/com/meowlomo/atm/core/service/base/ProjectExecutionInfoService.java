package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ProjectExecutionInfo;
import com.meowlomo.atm.core.model.ProjectExecutionInfoExample;

public interface ProjectExecutionInfoService {
    long countByExample(ProjectExecutionInfoExample example);

    List<ProjectExecutionInfo> selectByExample(ProjectExecutionInfoExample example);

    List<ProjectExecutionInfo> selectByExampleWithRowbounds(ProjectExecutionInfoExample example, RowBounds rowBounds);

    ProjectExecutionInfo selectByPrimaryKey(Long projectId);

    ProjectExecutionInfo selectOneByExample(ProjectExecutionInfoExample example);

}