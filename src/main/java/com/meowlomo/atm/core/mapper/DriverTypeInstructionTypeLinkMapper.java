package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverTypeInstructionTypeLink;
import com.meowlomo.atm.core.model.DriverTypeInstructionTypeLinkExample;

public interface DriverTypeInstructionTypeLinkMapper {
    long countByExample(DriverTypeInstructionTypeLinkExample example);

    int deleteByExample(DriverTypeInstructionTypeLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverTypeInstructionTypeLink record);

    int insertSelective(@Param("record") DriverTypeInstructionTypeLink record, @Param("selective") DriverTypeInstructionTypeLink.Column... selective);

    List<DriverTypeInstructionTypeLink> selectByExample(DriverTypeInstructionTypeLinkExample example);

    List<DriverTypeInstructionTypeLink> selectByExampleSelective(@Param("example") DriverTypeInstructionTypeLinkExample example,
            @Param("selective") DriverTypeInstructionTypeLink.Column... selective);

    List<DriverTypeInstructionTypeLink> selectByExampleWithRowbounds(DriverTypeInstructionTypeLinkExample example, RowBounds rowBounds);

    DriverTypeInstructionTypeLink selectByPrimaryKey(Long id);

    DriverTypeInstructionTypeLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverTypeInstructionTypeLink.Column... selective);

    DriverTypeInstructionTypeLink selectOneByExample(DriverTypeInstructionTypeLinkExample example);

    DriverTypeInstructionTypeLink selectOneByExampleSelective(@Param("example") DriverTypeInstructionTypeLinkExample example,
            @Param("selective") DriverTypeInstructionTypeLink.Column... selective);

    int updateByExample(@Param("record") DriverTypeInstructionTypeLink record, @Param("example") DriverTypeInstructionTypeLinkExample example);

    int updateByExampleSelective(@Param("record") DriverTypeInstructionTypeLink record, @Param("example") DriverTypeInstructionTypeLinkExample example,
            @Param("selective") DriverTypeInstructionTypeLink.Column... selective);

    int updateByPrimaryKey(DriverTypeInstructionTypeLink record);

    int updateByPrimaryKeySelective(@Param("record") DriverTypeInstructionTypeLink record, @Param("selective") DriverTypeInstructionTypeLink.Column... selective);
}