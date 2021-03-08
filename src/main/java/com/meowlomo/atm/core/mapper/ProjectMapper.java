package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.ProjectExample;

public interface ProjectMapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(@Param("record") Project record, @Param("selective") Project.Column... selective);

    int logicalDeleteByExample(@Param("example") ProjectExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Project> selectByExample(ProjectExample example);

    List<Project> selectByExampleSelective(@Param("example") ProjectExample example, @Param("selective") Project.Column... selective);

    List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds);

    Project selectByPrimaryKey(Long id);

    Project selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Project.Column... selective);

    Project selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Project selectOneByExample(ProjectExample example);

    Project selectOneByExampleSelective(@Param("example") ProjectExample example, @Param("selective") Project.Column... selective);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example, @Param("selective") Project.Column... selective);

    int updateByPrimaryKey(Project record);

    int updateByPrimaryKeySelective(@Param("record") Project record, @Param("selective") Project.Column... selective);
}