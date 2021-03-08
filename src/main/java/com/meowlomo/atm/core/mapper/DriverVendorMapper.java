package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverVendor;
import com.meowlomo.atm.core.model.DriverVendorExample;

public interface DriverVendorMapper {
    long countByExample(DriverVendorExample example);

    int deleteByExample(DriverVendorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverVendor record);

    int insertSelective(@Param("record") DriverVendor record, @Param("selective") DriverVendor.Column... selective);

    List<DriverVendor> selectByExample(DriverVendorExample example);

    List<DriverVendor> selectByExampleSelective(@Param("example") DriverVendorExample example, @Param("selective") DriverVendor.Column... selective);

    List<DriverVendor> selectByExampleWithRowbounds(DriverVendorExample example, RowBounds rowBounds);

    DriverVendor selectByPrimaryKey(Long id);

    DriverVendor selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverVendor.Column... selective);

    DriverVendor selectOneByExample(DriverVendorExample example);

    DriverVendor selectOneByExampleSelective(@Param("example") DriverVendorExample example, @Param("selective") DriverVendor.Column... selective);

    int updateByExample(@Param("record") DriverVendor record, @Param("example") DriverVendorExample example);

    int updateByExampleSelective(@Param("record") DriverVendor record, @Param("example") DriverVendorExample example, @Param("selective") DriverVendor.Column... selective);

    int updateByPrimaryKey(DriverVendor record);

    int updateByPrimaryKeySelective(@Param("record") DriverVendor record, @Param("selective") DriverVendor.Column... selective);
}