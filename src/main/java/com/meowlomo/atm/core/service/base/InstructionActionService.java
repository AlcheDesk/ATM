package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionActionExample;

public interface InstructionActionService {
    long countByExample(InstructionActionExample example);

    int deleteByExample(InstructionActionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionAction record);

    List<Long> insertRecords(List<InstructionAction> records);

    List<Long> insertRecordsSelective(List<InstructionAction> records);

    int insertSelective(InstructionAction record);

    List<InstructionAction> selectByExample(InstructionActionExample example);

    List<InstructionAction> selectByExampleWithRowbounds(InstructionActionExample example, RowBounds rowBounds);

    InstructionAction selectByPrimaryKey(Long id);

    InstructionAction selectOneByExample(InstructionActionExample example);

    int updateByExample(InstructionAction record, InstructionActionExample example);

    int updateByExampleSelective(InstructionAction record, InstructionActionExample example);

    int updateByPrimaryKey(InstructionAction record);

    int updateByPrimaryKeySelective(InstructionAction record);
}