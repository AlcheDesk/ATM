package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOption;
import com.meowlomo.atm.core.model.InstructionOptionExample;

public interface InstructionOptionService {
    long countByExample(InstructionOptionExample example);

    int deleteByExample(InstructionOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionOption record);

    List<Long> insertRecords(List<InstructionOption> records);

    List<Long> insertRecordsSelective(List<InstructionOption> records);

    int insertSelective(InstructionOption record);

    List<InstructionOption> selectByExample(InstructionOptionExample example);

    List<InstructionOption> selectByExampleWithRowbounds(InstructionOptionExample example, RowBounds rowBounds);

    InstructionOption selectByPrimaryKey(Long id);

    InstructionOption selectOneByExample(InstructionOptionExample example);

    int updateByExample(InstructionOption record, InstructionOptionExample example);

    int updateByExampleSelective(InstructionOption record, InstructionOptionExample example);

    int updateByPrimaryKey(InstructionOption record);

    int updateByPrimaryKeySelective(InstructionOption record);
}