package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverExample;

public interface DriverMapper {
    long countByExample(DriverExample example);

    int deleteByExample(DriverExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Driver record);

    int insertSelective(@Param("record") Driver record, @Param("selective") Driver.Column... selective);

    List<Driver> selectByExample(DriverExample example);

    List<Driver> selectByExampleSelective(@Param("example") DriverExample example, @Param("selective") Driver.Column... selective);

    List<Driver> selectByExampleWithRowbounds(DriverExample example, RowBounds rowBounds);

    Driver selectByPrimaryKey(Long id);

    Driver selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Driver.Column... selective);

    Driver selectOneByExample(DriverExample example);

    Driver selectOneByExampleSelective(@Param("example") DriverExample example, @Param("selective") Driver.Column... selective);

    int updateByExample(@Param("record") Driver record, @Param("example") DriverExample example);

    int updateByExampleSelective(@Param("record") Driver record, @Param("example") DriverExample example, @Param("selective") Driver.Column... selective);

    int updateByPrimaryKey(Driver record);

    int updateByPrimaryKeySelective(@Param("record") Driver record, @Param("selective") Driver.Column... selective);
}