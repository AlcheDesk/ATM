package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ProjectReportInfo;
import com.meowlomo.atm.core.model.ProjectReportInfoExample;

public interface ProjectReportInfoService {
    long countByExample(ProjectReportInfoExample example);

    List<ProjectReportInfo> selectByExample(ProjectReportInfoExample example);

    List<ProjectReportInfo> selectByExampleWithRowbounds(ProjectReportInfoExample example, RowBounds rowBounds);

    ProjectReportInfo selectByPrimaryKey(Long projectId);

    ProjectReportInfo selectOneByExample(ProjectReportInfoExample example);

}