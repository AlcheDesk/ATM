package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.InstructionExample;

public interface InstructionReferenceService {

    long countByExample(InstructionExample example);

    List<Instruction> selectByExample(InstructionExample example);

    List<Instruction> selectByExampleWithRowbounds(InstructionExample example, RowBounds rowBounds);

    Instruction selectByPrimaryKey(Long id);
}
