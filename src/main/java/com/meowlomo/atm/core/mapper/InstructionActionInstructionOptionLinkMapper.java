package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLink;
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLinkExample;

public interface InstructionActionInstructionOptionLinkMapper {
    long countByExample(InstructionActionInstructionOptionLinkExample example);

    int deleteByExample(InstructionActionInstructionOptionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionActionInstructionOptionLink record);

    int insertSelective(@Param("record") InstructionActionInstructionOptionLink record, @Param("selective") InstructionActionInstructionOptionLink.Column... selective);

    List<InstructionActionInstructionOptionLink> selectByExample(InstructionActionInstructionOptionLinkExample example);

    List<InstructionActionInstructionOptionLink> selectByExampleSelective(@Param("example") InstructionActionInstructionOptionLinkExample example,
            @Param("selective") InstructionActionInstructionOptionLink.Column... selective);

    List<InstructionActionInstructionOptionLink> selectByExampleWithRowbounds(InstructionActionInstructionOptionLinkExample example, RowBounds rowBounds);

    InstructionActionInstructionOptionLink selectByPrimaryKey(Long id);

    InstructionActionInstructionOptionLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionActionInstructionOptionLink.Column... selective);

    InstructionActionInstructionOptionLink selectOneByExample(InstructionActionInstructionOptionLinkExample example);

    InstructionActionInstructionOptionLink selectOneByExampleSelective(@Param("example") InstructionActionInstructionOptionLinkExample example,
            @Param("selective") InstructionActionInstructionOptionLink.Column... selective);

    int updateByExample(@Param("record") InstructionActionInstructionOptionLink record, @Param("example") InstructionActionInstructionOptionLinkExample example);

    int updateByExampleSelective(@Param("record") InstructionActionInstructionOptionLink record, @Param("example") InstructionActionInstructionOptionLinkExample example,
            @Param("selective") InstructionActionInstructionOptionLink.Column... selective);

    int updateByPrimaryKey(InstructionActionInstructionOptionLink record);

    int updateByPrimaryKeySelective(@Param("record") InstructionActionInstructionOptionLink record, @Param("selective") InstructionActionInstructionOptionLink.Column... selective);
}