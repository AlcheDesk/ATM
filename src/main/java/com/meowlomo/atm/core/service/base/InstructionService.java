package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;

public interface InstructionService {
    long countByExample(InstructionExample example);

    int deleteByExample(InstructionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Instruction record);

    List<Long> insertRecords(List<Instruction> records);

    List<Long> insertRecordsSelective(List<Instruction> records);

    int insertSelective(Instruction record);

    int logicalDeleteByExample(InstructionExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Instruction> selectByExample(InstructionExample example);

    List<Instruction> selectByExampleWithRowbounds(InstructionExample example, RowBounds rowBounds);

    Instruction selectByPrimaryKey(Long id);

    Instruction selectOneByExample(InstructionExample example);

    int updateByExample(Instruction record, InstructionExample example);

    int updateByExampleSelective(Instruction record, InstructionExample example);

    int updateByPrimaryKey(Instruction record);

    int updateByPrimaryKeySelective(Instruction record);
}