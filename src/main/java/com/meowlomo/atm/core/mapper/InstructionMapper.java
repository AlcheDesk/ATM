package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;

public interface InstructionMapper {
    long countByExample(InstructionExample example);

    int deleteByExample(InstructionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Instruction record);

    int insertSelective(@Param("record") Instruction record, @Param("selective") Instruction.Column... selective);

    int logicalDeleteByExample(@Param("example") InstructionExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Instruction> selectByExample(InstructionExample example);

    List<Instruction> selectByExampleSelective(@Param("example") InstructionExample example, @Param("selective") Instruction.Column... selective);

    List<Instruction> selectByExampleWithRowbounds(InstructionExample example, RowBounds rowBounds);

    Instruction selectByPrimaryKey(Long id);

    Instruction selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Instruction.Column... selective);

    Instruction selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Instruction selectOneByExample(InstructionExample example);

    Instruction selectOneByExampleSelective(@Param("example") InstructionExample example, @Param("selective") Instruction.Column... selective);

    int updateByExample(@Param("record") Instruction record, @Param("example") InstructionExample example);

    int updateByExampleSelective(@Param("record") Instruction record, @Param("example") InstructionExample example, @Param("selective") Instruction.Column... selective);

    int updateByPrimaryKey(Instruction record);

    int updateByPrimaryKeySelective(@Param("record") Instruction record, @Param("selective") Instruction.Column... selective);
}