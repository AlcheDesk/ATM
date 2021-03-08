package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;

public interface ProjectReferenceMapper {

    long countByExample(ProjectExample example);

    List<Project> selectByExample(ProjectExample example);

    List<Project> selectByExampleSelective(@Param("example") ProjectExample example, @Param("selective") Project.Column... selective);

    List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds);

    Project selectByPrimaryKey(Long id);

    Project selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Project.Column... selective);

    Project selectOneByExample(ProjectExample example);

    Project selectOneByExampleSelective(@Param("example") ProjectExample example, @Param("selective") Project.Column... selective);

}