package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ProjectType;
import com.meowlomo.atm.core.model.ProjectTypeExample;

public interface ProjectTypeService {
    long countByExample(ProjectTypeExample example);

    int deleteByExample(ProjectTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectType record);

    List<Long> insertRecords(List<ProjectType> records);

    List<Long> insertRecordsSelective(List<ProjectType> records);

    int insertSelective(ProjectType record);

    List<ProjectType> selectByExample(ProjectTypeExample example);

    List<ProjectType> selectByExampleWithRowbounds(ProjectTypeExample example, RowBounds rowBounds);

    ProjectType selectByPrimaryKey(Long id);

    ProjectType selectOneByExample(ProjectTypeExample example);

    int updateByExample(ProjectType record, ProjectTypeExample example);

    int updateByExampleSelective(ProjectType record, ProjectTypeExample example);

    int updateByPrimaryKey(ProjectType record);

    int updateByPrimaryKeySelective(ProjectType record);
}