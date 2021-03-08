package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.InstructionTypeExample;

public interface InstructionTypeService {
    long countByExample(InstructionTypeExample example);

    int deleteByExample(InstructionTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionType record);

    List<Long> insertRecords(List<InstructionType> records);

    List<Long> insertRecordsSelective(List<InstructionType> records);

    int insertSelective(InstructionType record);

    List<InstructionType> selectByExample(InstructionTypeExample example);

    List<InstructionType> selectByExampleWithRowbounds(InstructionTypeExample example, RowBounds rowBounds);

    InstructionType selectByPrimaryKey(Long id);

    InstructionType selectOneByExample(InstructionTypeExample example);

    int updateByExample(InstructionType record, InstructionTypeExample example);

    int updateByExampleSelective(InstructionType record, InstructionTypeExample example);

    int updateByPrimaryKey(InstructionType record);

    int updateByPrimaryKeySelective(InstructionType record);
}