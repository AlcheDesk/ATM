package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;

public interface InstructionResultService {
    long countByExample(InstructionResultExample example, String mode);

    int deleteByExample(InstructionResultExample example, String mode);

    int deleteByPrimaryKey(Long id, String mode);

    int insert(InstructionResult record, String mode);

    List<Long> insertRecords(List<InstructionResult> records, String mode);

    List<Long> insertRecordsSelective(List<InstructionResult> records, String mode);

    int insertSelective(InstructionResult record, String mode);

    List<InstructionResult> selectByExample(InstructionResultExample example, String mode);

    List<InstructionResult> selectByExampleWithRowbounds(InstructionResultExample example, RowBounds rowBounds,
            String mode);

    InstructionResult selectByPrimaryKey(Long id, String mode);

    InstructionResult selectOneByExample(InstructionResultExample example, String mode);

    int updateByExample(InstructionResult record, InstructionResultExample example, String mode);

    int updateByExampleSelective(InstructionResult record, InstructionResultExample example, String mode);

    int updateByPrimaryKey(InstructionResult record, String mode);

    int updateByPrimaryKeySelective(InstructionResult record, String mode);
}