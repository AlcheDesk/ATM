package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;

public interface InstructionOverwriteReferenceService {

    long countByExample(InstructionOverwriteExample example);

    List<InstructionOverwrite> selectByExample(InstructionOverwriteExample example);

    List<InstructionOverwrite> selectByExampleWithRowbounds(InstructionOverwriteExample example, RowBounds rowBounds);

    InstructionOverwrite selectByPrimaryKey(Long id);
}
