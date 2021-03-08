package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeInstructionActionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionActionLinkExample;
import com.meowlomo.atm.core.model.InstructionAction;

public interface ElementTypeInstructionActionLinkService {
    long countByExample(ElementTypeInstructionActionLinkExample example);

    int deleteByExample(ElementTypeInstructionActionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementTypeInstructionActionLink record);

    List<Long> insertRecords(List<ElementTypeInstructionActionLink> records);

    List<Long> insertRecordsSelective(List<ElementTypeInstructionActionLink> records);

    int insertSelective(ElementTypeInstructionActionLink record);

    List<ElementType> listElementTypeByInstructionActionPrimaryKey(Long instructionActionId);

    List<InstructionAction> listInstructionActionByElementTypePrimaryKey(Long elementTypeId);

    List<ElementTypeInstructionActionLink> selectByExample(ElementTypeInstructionActionLinkExample example);

    List<ElementTypeInstructionActionLink> selectByExampleWithRowbounds(ElementTypeInstructionActionLinkExample example,
            RowBounds rowBounds);

    ElementTypeInstructionActionLink selectByPrimaryKey(Long id);

    ElementTypeInstructionActionLink selectOneByExample(ElementTypeInstructionActionLinkExample example);

    int updateByExample(ElementTypeInstructionActionLink record, ElementTypeInstructionActionLinkExample example);

    int updateByExampleSelective(ElementTypeInstructionActionLink record,
            ElementTypeInstructionActionLinkExample example);

    int updateByPrimaryKey(ElementTypeInstructionActionLink record);

    int updateByPrimaryKeySelective(ElementTypeInstructionActionLink record);
}