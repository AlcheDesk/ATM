package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;

public interface ProjectReferenceService {

    long countByExample(ProjectExample example);

    List<Project> selectByExample(ProjectExample example);

    List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds);

    Project selectByPrimaryKey(Long id);
}
