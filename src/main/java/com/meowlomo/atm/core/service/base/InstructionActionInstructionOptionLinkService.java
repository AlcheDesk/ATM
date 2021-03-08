package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLink;
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLinkExample;

public interface InstructionActionInstructionOptionLinkService {
    long countByExample(InstructionActionInstructionOptionLinkExample example);

    int deleteByExample(InstructionActionInstructionOptionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionActionInstructionOptionLink record);

    List<Long> insertRecords(List<InstructionActionInstructionOptionLink> records);

    List<Long> insertRecordsSelective(List<InstructionActionInstructionOptionLink> records);

    int insertSelective(InstructionActionInstructionOptionLink record);

    List<InstructionActionInstructionOptionLink> selectByExample(InstructionActionInstructionOptionLinkExample example);

    List<InstructionActionInstructionOptionLink> selectByExampleWithRowbounds(
            InstructionActionInstructionOptionLinkExample example, RowBounds rowBounds);

    InstructionActionInstructionOptionLink selectByPrimaryKey(Long id);

    InstructionActionInstructionOptionLink selectOneByExample(InstructionActionInstructionOptionLinkExample example);

    int updateByExample(InstructionActionInstructionOptionLink record,
            InstructionActionInstructionOptionLinkExample example);

    int updateByExampleSelective(InstructionActionInstructionOptionLink record,
            InstructionActionInstructionOptionLinkExample example);

    int updateByPrimaryKey(InstructionActionInstructionOptionLink record);

    int updateByPrimaryKeySelective(InstructionActionInstructionOptionLink record);
}