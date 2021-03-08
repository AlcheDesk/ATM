package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOptionEntry;
import com.meowlomo.atm.core.model.InstructionOptionEntryExample;

public interface InstructionOptionEntryService {
    long countByExample(InstructionOptionEntryExample example);

    int deleteByExample(InstructionOptionEntryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionOptionEntry record);

    List<Long> insertRecords(List<InstructionOptionEntry> records);

    List<Long> insertRecordsSelective(List<InstructionOptionEntry> records);

    int insertSelective(InstructionOptionEntry record);

    List<InstructionOptionEntry> selectByExample(InstructionOptionEntryExample example);

    List<InstructionOptionEntry> selectByExampleWithRowbounds(InstructionOptionEntryExample example,
            RowBounds rowBounds);

    InstructionOptionEntry selectByPrimaryKey(Long id);

    InstructionOptionEntry selectOneByExample(InstructionOptionEntryExample example);

    int updateByExample(InstructionOptionEntry record, InstructionOptionEntryExample example);

    int updateByExampleSelective(InstructionOptionEntry record, InstructionOptionEntryExample example);

    int updateByPrimaryKey(InstructionOptionEntry record);

    int updateByPrimaryKeySelective(InstructionOptionEntry record);
}