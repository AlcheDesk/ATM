package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionActionExample;

public interface InstructionActionMapper {
    long countByExample(InstructionActionExample example);

    int deleteByExample(InstructionActionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionAction record);

    int insertSelective(@Param("record") InstructionAction record, @Param("selective") InstructionAction.Column... selective);

    List<InstructionAction> selectByExample(InstructionActionExample example);

    List<InstructionAction> selectByExampleSelective(@Param("example") InstructionActionExample example, @Param("selective") InstructionAction.Column... selective);

    List<InstructionAction> selectByExampleWithRowbounds(InstructionActionExample example, RowBounds rowBounds);

    InstructionAction selectByPrimaryKey(Long id);

    InstructionAction selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionAction.Column... selective);

    InstructionAction selectOneByExample(InstructionActionExample example);

    InstructionAction selectOneByExampleSelective(@Param("example") InstructionActionExample example, @Param("selective") InstructionAction.Column... selective);

    int updateByExample(@Param("record") InstructionAction record, @Param("example") InstructionActionExample example);

    int updateByExampleSelective(@Param("record") InstructionAction record, @Param("example") InstructionActionExample example,
            @Param("selective") InstructionAction.Column... selective);

    int updateByPrimaryKey(InstructionAction record);

    int updateByPrimaryKeySelective(@Param("record") InstructionAction record, @Param("selective") InstructionAction.Column... selective);
}