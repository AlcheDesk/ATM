package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLinkExample;
import com.meowlomo.atm.core.model.InstructionOption;

public interface ElementTypeInstructionOptionLinkService {
    long countByExample(ElementTypeInstructionOptionLinkExample example);

    int deleteByExample(ElementTypeInstructionOptionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementTypeInstructionOptionLink record);

    List<Long> insertRecords(List<ElementTypeInstructionOptionLink> records);

    List<Long> insertRecordsSelective(List<ElementTypeInstructionOptionLink> records);

    int insertSelective(ElementTypeInstructionOptionLink record);

    List<InstructionOption> listInstructionOptionByElementTypePrimaryKey(Long elementTypeId);

    List<ElementTypeInstructionOptionLink> selectByExample(ElementTypeInstructionOptionLinkExample example);

    List<ElementTypeInstructionOptionLink> selectByExampleWithRowbounds(ElementTypeInstructionOptionLinkExample example,
            RowBounds rowBounds);

    ElementTypeInstructionOptionLink selectByPrimaryKey(Long id);

    ElementTypeInstructionOptionLink selectOneByExample(ElementTypeInstructionOptionLinkExample example);

    int updateByExample(ElementTypeInstructionOptionLink record, ElementTypeInstructionOptionLinkExample example);

    int updateByExampleSelective(ElementTypeInstructionOptionLink record,
            ElementTypeInstructionOptionLinkExample example);

    int updateByPrimaryKey(ElementTypeInstructionOptionLink record);

    int updateByPrimaryKeySelective(ElementTypeInstructionOptionLink record);
}