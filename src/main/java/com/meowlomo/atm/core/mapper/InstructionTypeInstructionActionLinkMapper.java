package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLink;
import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLinkExample;

public interface InstructionTypeInstructionActionLinkMapper {
    long countByExample(InstructionTypeInstructionActionLinkExample example);

    int deleteByExample(InstructionTypeInstructionActionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionTypeInstructionActionLink record);

    int insertSelective(@Param("record") InstructionTypeInstructionActionLink record, @Param("selective") InstructionTypeInstructionActionLink.Column... selective);

    List<InstructionTypeInstructionActionLink> selectByExample(InstructionTypeInstructionActionLinkExample example);

    List<InstructionTypeInstructionActionLink> selectByExampleSelective(@Param("example") InstructionTypeInstructionActionLinkExample example,
            @Param("selective") InstructionTypeInstructionActionLink.Column... selective);

    List<InstructionTypeInstructionActionLink> selectByExampleWithRowbounds(InstructionTypeInstructionActionLinkExample example, RowBounds rowBounds);

    InstructionTypeInstructionActionLink selectByPrimaryKey(Long id);

    InstructionTypeInstructionActionLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionTypeInstructionActionLink.Column... selective);

    InstructionTypeInstructionActionLink selectOneByExample(InstructionTypeInstructionActionLinkExample example);

    InstructionTypeInstructionActionLink selectOneByExampleSelective(@Param("example") InstructionTypeInstructionActionLinkExample example,
            @Param("selective") InstructionTypeInstructionActionLink.Column... selective);

    int updateByExample(@Param("record") InstructionTypeInstructionActionLink record, @Param("example") InstructionTypeInstructionActionLinkExample example);

    int updateByExampleSelective(@Param("record") InstructionTypeInstructionActionLink record, @Param("example") InstructionTypeInstructionActionLinkExample example,
            @Param("selective") InstructionTypeInstructionActionLink.Column... selective);

    int updateByPrimaryKey(InstructionTypeInstructionActionLink record);

    int updateByPrimaryKeySelective(@Param("record") InstructionTypeInstructionActionLink record, @Param("selective") InstructionTypeInstructionActionLink.Column... selective);
}