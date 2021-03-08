package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverExample;

public interface DriverReferenceMapper {

    long countByExample(DriverExample example);

    List<Driver> selectByExample(DriverExample example);

    List<Driver> selectByExampleSelective(@Param("example") DriverExample example, @Param("selective") Driver.Column... selective);

    List<Driver> selectByExampleWithRowbounds(DriverExample example, RowBounds rowBounds);

    Driver selectByPrimaryKey(Long id);

    Driver selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Driver.Column... selective);

    Driver selectOneByExample(DriverExample example);

    Driver selectOneByExampleSelective(@Param("example") DriverExample example, @Param("selective") Driver.Column... selective);
}