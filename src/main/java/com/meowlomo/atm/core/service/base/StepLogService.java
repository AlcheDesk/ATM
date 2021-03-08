package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.StepLog;
import com.meowlomo.atm.core.model.StepLogExample;

public interface StepLogService {
    long countByExample(StepLogExample example, String mode);

    int deleteByExample(StepLogExample example, String mode);

    int deleteByPrimaryKey(Long id, String mode);

    int insert(StepLog record, String mode);

    List<Long> insertRecords(List<StepLog> records, String mode);

    List<Long> insertRecordsSelective(List<StepLog> records, String mode);

    int insertSelective(StepLog record, String mode);

    List<StepLog> selectByExample(StepLogExample example, String mode);

    List<StepLog> selectByExampleWithRowbounds(StepLogExample example, RowBounds rowBounds, String mode);

    StepLog selectByPrimaryKey(Long id, String mode);

    StepLog selectOneByExample(StepLogExample example, String mode);

    int updateByExample(StepLog record, StepLogExample example, String mode);

    int updateByExampleSelective(StepLog record, StepLogExample example, String mode);

    int updateByPrimaryKey(StepLog record, String mode);

    int updateByPrimaryKeySelective(StepLog record, String mode);
}