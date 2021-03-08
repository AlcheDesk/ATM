package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementType;
import com.meowlomo.atm.core.model.SystemRequirementTypeExample;

public interface SystemRequirementTypeMapper {
    long countByExample(SystemRequirementTypeExample example);

    int deleteByExample(SystemRequirementTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirementType record);

    int insertSelective(@Param("record") SystemRequirementType record, @Param("selective") SystemRequirementType.Column... selective);

    List<SystemRequirementType> selectByExample(SystemRequirementTypeExample example);

    List<SystemRequirementType> selectByExampleSelective(@Param("example") SystemRequirementTypeExample example, @Param("selective") SystemRequirementType.Column... selective);

    List<SystemRequirementType> selectByExampleWithRowbounds(SystemRequirementTypeExample example, RowBounds rowBounds);

    SystemRequirementType selectByPrimaryKey(Long id);

    SystemRequirementType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SystemRequirementType.Column... selective);

    SystemRequirementType selectOneByExample(SystemRequirementTypeExample example);

    SystemRequirementType selectOneByExampleSelective(@Param("example") SystemRequirementTypeExample example, @Param("selective") SystemRequirementType.Column... selective);

    int updateByExample(@Param("record") SystemRequirementType record, @Param("example") SystemRequirementTypeExample example);

    int updateByExampleSelective(@Param("record") SystemRequirementType record, @Param("example") SystemRequirementTypeExample example,
            @Param("selective") SystemRequirementType.Column... selective);

    int updateByPrimaryKey(SystemRequirementType record);

    int updateByPrimaryKeySelective(@Param("record") SystemRequirementType record, @Param("selective") SystemRequirementType.Column... selective);
}