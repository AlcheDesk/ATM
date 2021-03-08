package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirement;
import com.meowlomo.atm.core.model.SystemRequirementExample;

public interface SystemRequirementMapper {
    long countByExample(SystemRequirementExample example);

    int deleteByExample(SystemRequirementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirement record);

    int insertSelective(@Param("record") SystemRequirement record, @Param("selective") SystemRequirement.Column... selective);

    List<SystemRequirement> selectByExample(SystemRequirementExample example);

    List<SystemRequirement> selectByExampleSelective(@Param("example") SystemRequirementExample example, @Param("selective") SystemRequirement.Column... selective);

    List<SystemRequirement> selectByExampleWithRowbounds(SystemRequirementExample example, RowBounds rowBounds);

    SystemRequirement selectByPrimaryKey(Long id);

    SystemRequirement selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SystemRequirement.Column... selective);

    SystemRequirement selectOneByExample(SystemRequirementExample example);

    SystemRequirement selectOneByExampleSelective(@Param("example") SystemRequirementExample example, @Param("selective") SystemRequirement.Column... selective);

    int updateByExample(@Param("record") SystemRequirement record, @Param("example") SystemRequirementExample example);

    int updateByExampleSelective(@Param("record") SystemRequirement record, @Param("example") SystemRequirementExample example,
            @Param("selective") SystemRequirement.Column... selective);

    int updateByPrimaryKey(SystemRequirement record);

    int updateByPrimaryKeySelective(@Param("record") SystemRequirement record, @Param("selective") SystemRequirement.Column... selective);
}