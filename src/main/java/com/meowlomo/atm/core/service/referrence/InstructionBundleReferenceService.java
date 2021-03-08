package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionBundle;
import com.meowlomo.atm.core.model.InstructionBundleExample;

public interface InstructionBundleReferenceService {

    long countByExample(InstructionBundleExample example);

    List<InstructionBundle> selectByExample(InstructionBundleExample example);

    List<InstructionBundle> selectByExampleWithRowbounds(InstructionBundleExample example, RowBounds rowBounds);

    InstructionBundle selectByPrimaryKey(Long id);
}
