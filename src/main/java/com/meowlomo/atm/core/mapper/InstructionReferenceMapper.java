package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;

public interface InstructionReferenceMapper {

    long countByExample(InstructionExample example);

    List<Instruction> selectByExample(InstructionExample example);

    List<Instruction> selectByExampleSelective(@Param("example") InstructionExample example, @Param("selective") Instruction.Column... selective);

    List<Instruction> selectByExampleWithRowbounds(InstructionExample example, RowBounds rowBounds);

    Instruction selectByPrimaryKey(Long id);

    Instruction selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Instruction.Column... selective);

    Instruction selectOneByExample(InstructionExample example);

    Instruction selectOneByExampleSelective(@Param("example") InstructionExample example, @Param("selective") Instruction.Column... selective);

}
