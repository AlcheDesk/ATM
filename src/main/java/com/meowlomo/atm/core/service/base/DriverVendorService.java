package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverVendor;
import com.meowlomo.atm.core.model.DriverVendorExample;

public interface DriverVendorService {
    long countByExample(DriverVendorExample example);

    int deleteByExample(DriverVendorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverVendor record);

    List<Long> insertRecords(List<DriverVendor> records);

    List<Long> insertRecordsSelective(List<DriverVendor> records);

    int insertSelective(DriverVendor record);

    List<DriverVendor> selectByExample(DriverVendorExample example);

    List<DriverVendor> selectByExampleWithRowbounds(DriverVendorExample example, RowBounds rowBounds);

    DriverVendor selectByPrimaryKey(Long id);

    DriverVendor selectOneByExample(DriverVendorExample example);

    int updateByExample(DriverVendor record, DriverVendorExample example);

    int updateByExampleSelective(DriverVendor record, DriverVendorExample example);

    int updateByPrimaryKey(DriverVendor record);

    int updateByPrimaryKeySelective(DriverVendor record);
}