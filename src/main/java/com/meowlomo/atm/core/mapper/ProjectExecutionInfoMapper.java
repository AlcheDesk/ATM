package com.meowlomo.atm.core.mapper;

import com.meowlomo.atm.core.model.ProjectExecutionInfo;
import com.meowlomo.atm.core.model.ProjectExecutionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectExecutionInfoMapper {
    long countByExample(ProjectExecutionInfoExample example);

    List<ProjectExecutionInfo> selectByExample(ProjectExecutionInfoExample example);

    List<ProjectExecutionInfo> selectByExampleSelective(@Param("example") ProjectExecutionInfoExample example, @Param("selective") ProjectExecutionInfo.Column... selective);

    List<ProjectExecutionInfo> selectByExampleWithRowbounds(ProjectExecutionInfoExample example, RowBounds rowBounds);

    ProjectExecutionInfo selectByPrimaryKey(Long projectId);

    ProjectExecutionInfo selectByPrimaryKeySelective(@Param("projectId") Long projectId, @Param("selective") ProjectExecutionInfo.Column... selective);

    ProjectExecutionInfo selectOneByExample(ProjectExecutionInfoExample example);

    ProjectExecutionInfo selectOneByExampleSelective(@Param("example") ProjectExecutionInfoExample example, @Param("selective") ProjectExecutionInfo.Column... selective);
}