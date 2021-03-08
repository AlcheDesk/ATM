package com.meowlomo.atm.core.mapper;

import com.meowlomo.atm.core.model.ProjectReportInfo;
import com.meowlomo.atm.core.model.ProjectReportInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectReportInfoMapper {
    long countByExample(ProjectReportInfoExample example);

    List<ProjectReportInfo> selectByExample(ProjectReportInfoExample example);

    List<ProjectReportInfo> selectByExampleSelective(@Param("example") ProjectReportInfoExample example, @Param("selective") ProjectReportInfo.Column... selective);

    List<ProjectReportInfo> selectByExampleWithRowbounds(ProjectReportInfoExample example, RowBounds rowBounds);

    ProjectReportInfo selectByPrimaryKey(Long projectId);

    ProjectReportInfo selectByPrimaryKeySelective(@Param("projectId") Long projectId, @Param("selective") ProjectReportInfo.Column... selective);

    ProjectReportInfo selectOneByExample(ProjectReportInfoExample example);

    ProjectReportInfo selectOneByExampleSelective(@Param("example") ProjectReportInfoExample example, @Param("selective") ProjectReportInfo.Column... selective);
}