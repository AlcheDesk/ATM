package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPropertyPredefinedValue;
import com.meowlomo.atm.core.model.DriverPropertyPredefinedValueExample;

public interface DriverPropertyPredefinedValueService {
    long countByExample(DriverPropertyPredefinedValueExample example);

    int deleteByExample(DriverPropertyPredefinedValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPropertyPredefinedValue record);

    List<Long> insertRecords(List<DriverPropertyPredefinedValue> records);

    List<Long> insertRecordsSelective(List<DriverPropertyPredefinedValue> records);

    int insertSelective(DriverPropertyPredefinedValue record);

    List<DriverPropertyPredefinedValue> selectByExample(DriverPropertyPredefinedValueExample example);

    List<DriverPropertyPredefinedValue> selectByExampleWithRowbounds(DriverPropertyPredefinedValueExample example,
            RowBounds rowBounds);

    DriverPropertyPredefinedValue selectByPrimaryKey(Long id);

    DriverPropertyPredefinedValue selectOneByExample(DriverPropertyPredefinedValueExample example);

    int updateByExample(DriverPropertyPredefinedValue record, DriverPropertyPredefinedValueExample example);

    int updateByExampleSelective(DriverPropertyPredefinedValue record, DriverPropertyPredefinedValueExample example);

    int updateByPrimaryKey(DriverPropertyPredefinedValue record);

    int updateByPrimaryKeySelective(DriverPropertyPredefinedValue record);
}