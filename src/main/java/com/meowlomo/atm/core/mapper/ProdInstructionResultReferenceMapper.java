package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;

public interface ProdInstructionResultReferenceMapper {

    long countByExample(InstructionResultExample example);

    List<InstructionResult> selectByExample(InstructionResultExample example);

    List<InstructionResult> selectByExampleSelective(@Param("example") InstructionResultExample example, @Param("selective") InstructionResult.Column... selective);

    List<InstructionResult> selectByExampleWithRowbounds(InstructionResultExample example, RowBounds rowBounds);

    InstructionResult selectByPrimaryKey(Long id);

    InstructionResult selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionResult.Column... selective);

    InstructionResult selectOneByExample(InstructionResultExample example);

    InstructionResult selectOneByExampleSelective(@Param("example") InstructionResultExample example, @Param("selective") InstructionResult.Column... selective);

}
