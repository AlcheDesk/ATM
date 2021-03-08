package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverExample;

public interface DriverService {
    long countByExample(DriverExample example);

    int deleteByExample(DriverExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Driver record);

    List<Long> insertRecords(List<Driver> records);

    List<Long> insertRecordsSelective(List<Driver> records);

    int insertSelective(Driver record);

    List<Driver> selectByExample(DriverExample example);

    List<Driver> selectByExampleWithRowbounds(DriverExample example, RowBounds rowBounds);

    Driver selectByPrimaryKey(Long id);

    Driver selectOneByExample(DriverExample example);

    int updateByExample(Driver record, DriverExample example);

    int updateByExampleSelective(Driver record, DriverExample example);

    int updateByPrimaryKey(Driver record);

    int updateByPrimaryKeySelective(Driver record);
}