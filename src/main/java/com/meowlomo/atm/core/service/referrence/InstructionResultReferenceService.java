package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;

public interface InstructionResultReferenceService {

    long countByExample(InstructionResultExample example, String mode);

    List<InstructionResult> selectByExample(InstructionResultExample example, String mode);

    List<InstructionResult> selectByExampleWithRowbounds(InstructionResultExample example, RowBounds rowBounds,
            String type);

    InstructionResult selectByPrimaryKey(Long id, String mode);
}
