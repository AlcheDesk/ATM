package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;

public interface InstructionOverwriteService {
    long countByExample(InstructionOverwriteExample example);

    int deleteByExample(InstructionOverwriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionOverwrite record);

    List<Long> insertRecords(List<InstructionOverwrite> records);

    List<Long> insertRecordsSelective(List<InstructionOverwrite> records);

    int insertSelective(InstructionOverwrite record);

    List<InstructionOverwrite> selectByExample(InstructionOverwriteExample example);

    List<InstructionOverwrite> selectByExampleWithRowbounds(InstructionOverwriteExample example, RowBounds rowBounds);

    InstructionOverwrite selectByPrimaryKey(Long id);

    InstructionOverwrite selectOneByExample(InstructionOverwriteExample example);

    int updateByExample(InstructionOverwrite record, InstructionOverwriteExample example);

    int updateByExampleSelective(InstructionOverwrite record, InstructionOverwriteExample example);

    int updateByPrimaryKey(InstructionOverwrite record);

    int updateByPrimaryKeySelective(InstructionOverwrite record);
}