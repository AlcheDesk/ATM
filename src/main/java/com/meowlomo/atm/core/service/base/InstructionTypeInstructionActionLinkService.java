package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLink;
import com.meowlomo.atm.core.model.InstructionTypeInstructionActionLinkExample;

public interface InstructionTypeInstructionActionLinkService {
    long countByExample(InstructionTypeInstructionActionLinkExample example);

    int deleteByExample(InstructionTypeInstructionActionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionTypeInstructionActionLink record);

    List<Long> insertRecords(List<InstructionTypeInstructionActionLink> records);

    List<Long> insertRecordsSelective(List<InstructionTypeInstructionActionLink> records);

    int insertSelective(InstructionTypeInstructionActionLink record);

    List<InstructionAction> listInstructionActionByInstructionTypePrimaryKey(Long long1);

    List<InstructionType> listInstructionTypeByInstructionActionPrimaryKey(Long instructionActionId);

    List<InstructionTypeInstructionActionLink> selectByExample(InstructionTypeInstructionActionLinkExample example);

    List<InstructionTypeInstructionActionLink> selectByExampleWithRowbounds(
            InstructionTypeInstructionActionLinkExample example, RowBounds rowBounds);

    InstructionTypeInstructionActionLink selectByPrimaryKey(Long id);

    InstructionTypeInstructionActionLink selectOneByExample(InstructionTypeInstructionActionLinkExample example);

    int updateByExample(InstructionTypeInstructionActionLink record,
            InstructionTypeInstructionActionLinkExample example);

    int updateByExampleSelective(InstructionTypeInstructionActionLink record,
            InstructionTypeInstructionActionLinkExample example);

    int updateByPrimaryKey(InstructionTypeInstructionActionLink record);

    int updateByPrimaryKeySelective(InstructionTypeInstructionActionLink record);
}