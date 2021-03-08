package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverProperty;
import com.meowlomo.atm.core.model.DriverPropertyExample;

public interface DriverPropertyService {
    long countByExample(DriverPropertyExample example);

    int deleteByExample(DriverPropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverProperty record);

    List<Long> insertRecords(List<DriverProperty> records);

    List<Long> insertRecordsSelective(List<DriverProperty> records);

    int insertSelective(DriverProperty record);

    List<DriverProperty> selectByExample(DriverPropertyExample example);

    List<DriverProperty> selectByExampleWithRowbounds(DriverPropertyExample example, RowBounds rowBounds);

    DriverProperty selectByPrimaryKey(Long id);

    DriverProperty selectOneByExample(DriverPropertyExample example);

    int updateByExample(DriverProperty record, DriverPropertyExample example);

    int updateByExampleSelective(DriverProperty record, DriverPropertyExample example);

    int updateByPrimaryKey(DriverProperty record);

    int updateByPrimaryKeySelective(DriverProperty record);
}