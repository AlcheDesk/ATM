package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;

public interface ProjectService {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    List<Long> insertRecords(List<Project> records);

    List<Long> insertRecordsSelective(List<Project> records);

    int insertSelective(Project record);

    int logicalDeleteByExample(ProjectExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Project> selectByExample(ProjectExample example);

    List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds);

    Project selectByPrimaryKey(Long id);

    Project selectOneByExample(ProjectExample example);

    int updateByExample(Project record, ProjectExample example);

    int updateByExampleSelective(Project record, ProjectExample example);

    int updateByPrimaryKey(Project record);

    int updateByPrimaryKeySelective(Project record);
}