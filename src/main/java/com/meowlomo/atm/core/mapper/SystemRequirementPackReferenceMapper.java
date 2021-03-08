package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementPack;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;

public interface SystemRequirementPackReferenceMapper {

    long countByExample(SystemRequirementPackExample example);

    List<SystemRequirementPack> selectByExample(SystemRequirementPackExample example);

    List<SystemRequirementPack> selectByExampleSelective(@Param("example") SystemRequirementPackExample example, @Param("selective") SystemRequirementPack.Column... selective);

    List<SystemRequirementPack> selectByExampleWithRowbounds(SystemRequirementPackExample example, RowBounds rowBounds);

    SystemRequirementPack selectByPrimaryKey(Long id);

    SystemRequirementPack selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SystemRequirementPack.Column... selective);

    SystemRequirementPack selectOneByExample(SystemRequirementPackExample example);

    SystemRequirementPack selectOneByExampleSelective(@Param("example") SystemRequirementPackExample example, @Param("selective") SystemRequirementPack.Column... selective);
}