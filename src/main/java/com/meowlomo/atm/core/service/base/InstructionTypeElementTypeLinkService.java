package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionTypeElementTypeLink;
import com.meowlomo.atm.core.model.InstructionTypeElementTypeLinkExample;

public interface InstructionTypeElementTypeLinkService {
    long countByExample(InstructionTypeElementTypeLinkExample example);

    int deleteByExample(InstructionTypeElementTypeLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionTypeElementTypeLink record);

    List<Long> insertRecords(List<InstructionTypeElementTypeLink> records);

    List<Long> insertRecordsSelective(List<InstructionTypeElementTypeLink> records);

    int insertSelective(InstructionTypeElementTypeLink record);

    List<InstructionTypeElementTypeLink> selectByExample(InstructionTypeElementTypeLinkExample example);

    List<InstructionTypeElementTypeLink> selectByExampleWithRowbounds(InstructionTypeElementTypeLinkExample example,
            RowBounds rowBounds);

    InstructionTypeElementTypeLink selectByPrimaryKey(Long id);

    InstructionTypeElementTypeLink selectOneByExample(InstructionTypeElementTypeLinkExample example);

    int updateByExample(InstructionTypeElementTypeLink record, InstructionTypeElementTypeLinkExample example);

    int updateByExampleSelective(InstructionTypeElementTypeLink record, InstructionTypeElementTypeLinkExample example);

    int updateByPrimaryKey(InstructionTypeElementTypeLink record);

    int updateByPrimaryKeySelective(InstructionTypeElementTypeLink record);
}