package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.StepLogType;
import com.meowlomo.atm.core.model.StepLogTypeExample;

public interface StepLogTypeService {
    long countByExample(StepLogTypeExample example);

    int deleteByExample(StepLogTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StepLogType record);

    List<Long> insertRecords(List<StepLogType> records);

    List<Long> insertRecordsSelective(List<StepLogType> records);

    int insertSelective(StepLogType record);

    List<StepLogType> selectByExample(StepLogTypeExample example);

    List<StepLogType> selectByExampleWithRowbounds(StepLogTypeExample example, RowBounds rowBounds);

    StepLogType selectByPrimaryKey(Long id);

    StepLogType selectOneByExample(StepLogTypeExample example);

    int updateByExample(StepLogType record, StepLogTypeExample example);

    int updateByExampleSelective(StepLogType record, StepLogTypeExample example);

    int updateByPrimaryKey(StepLogType record);

    int updateByPrimaryKeySelective(StepLogType record);
}