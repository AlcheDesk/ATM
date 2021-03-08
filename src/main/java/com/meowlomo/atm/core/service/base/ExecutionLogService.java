package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ExecutionLog;
import com.meowlomo.atm.core.model.ExecutionLogExample;

public interface ExecutionLogService {
    long countByExample(ExecutionLogExample example, String mode);

    int deleteByExample(ExecutionLogExample example, String mode);

    int deleteByPrimaryKey(Long id, String mode);

    int insert(ExecutionLog record, String mode);

    List<Long> insertRecords(List<ExecutionLog> records, String mode);

    List<Long> insertRecordsSelective(List<ExecutionLog> records, String mode);

    int insertSelective(ExecutionLog record, String mode);

    List<ExecutionLog> selectByExample(ExecutionLogExample example, String mode);

    List<ExecutionLog> selectByExampleWithRowbounds(ExecutionLogExample example, RowBounds rowBounds, String mode);

    ExecutionLog selectByPrimaryKey(Long id, String mode);

    ExecutionLog selectOneByExample(ExecutionLogExample example, String mode);

    int updateByExample(ExecutionLog record, ExecutionLogExample example, String mode);

    int updateByExampleSelective(ExecutionLog record, ExecutionLogExample example, String mode);

    int updateByPrimaryKey(ExecutionLog record, String mode);

    int updateByPrimaryKeySelective(ExecutionLog record, String mode);
}