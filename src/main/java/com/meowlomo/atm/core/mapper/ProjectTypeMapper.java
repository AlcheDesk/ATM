package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ProjectType;
import com.meowlomo.atm.core.model.ProjectTypeExample;

public interface ProjectTypeMapper {
    long countByExample(ProjectTypeExample example);

    int deleteByExample(ProjectTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectType record);

    int insertSelective(@Param("record") ProjectType record, @Param("selective") ProjectType.Column... selective);

    List<ProjectType> selectByExample(ProjectTypeExample example);

    List<ProjectType> selectByExampleSelective(@Param("example") ProjectTypeExample example, @Param("selective") ProjectType.Column... selective);

    List<ProjectType> selectByExampleWithRowbounds(ProjectTypeExample example, RowBounds rowBounds);

    ProjectType selectByPrimaryKey(Long id);

    ProjectType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ProjectType.Column... selective);

    ProjectType selectOneByExample(ProjectTypeExample example);

    ProjectType selectOneByExampleSelective(@Param("example") ProjectTypeExample example, @Param("selective") ProjectType.Column... selective);

    int updateByExample(@Param("record") ProjectType record, @Param("example") ProjectTypeExample example);

    int updateByExampleSelective(@Param("record") ProjectType record, @Param("example") ProjectTypeExample example, @Param("selective") ProjectType.Column... selective);

    int updateByPrimaryKey(ProjectType record);

    int updateByPrimaryKeySelective(@Param("record") ProjectType record, @Param("selective") ProjectType.Column... selective);
}