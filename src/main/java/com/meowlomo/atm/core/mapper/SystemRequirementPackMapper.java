package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementPack;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;

public interface SystemRequirementPackMapper {
    long countByExample(SystemRequirementPackExample example);

    int deleteByExample(SystemRequirementPackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirementPack record);

    int insertSelective(@Param("record") SystemRequirementPack record, @Param("selective") SystemRequirementPack.Column... selective);

    int logicalDeleteByExample(@Param("example") SystemRequirementPackExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<SystemRequirementPack> selectByExample(SystemRequirementPackExample example);

    List<SystemRequirementPack> selectByExampleSelective(@Param("example") SystemRequirementPackExample example, @Param("selective") SystemRequirementPack.Column... selective);

    List<SystemRequirementPack> selectByExampleWithRowbounds(SystemRequirementPackExample example, RowBounds rowBounds);

    SystemRequirementPack selectByPrimaryKey(Long id);

    SystemRequirementPack selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SystemRequirementPack.Column... selective);

    SystemRequirementPack selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    SystemRequirementPack selectOneByExample(SystemRequirementPackExample example);

    SystemRequirementPack selectOneByExampleSelective(@Param("example") SystemRequirementPackExample example, @Param("selective") SystemRequirementPack.Column... selective);

    int updateByExample(@Param("record") SystemRequirementPack record, @Param("example") SystemRequirementPackExample example);

    int updateByExampleSelective(@Param("record") SystemRequirementPack record, @Param("example") SystemRequirementPackExample example,
            @Param("selective") SystemRequirementPack.Column... selective);

    int updateByPrimaryKey(SystemRequirementPack record);

    int updateByPrimaryKeySelective(@Param("record") SystemRequirementPack record, @Param("selective") SystemRequirementPack.Column... selective);
}