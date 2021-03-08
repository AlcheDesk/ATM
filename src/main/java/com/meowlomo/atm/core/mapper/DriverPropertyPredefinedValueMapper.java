package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPropertyPredefinedValue;
import com.meowlomo.atm.core.model.DriverPropertyPredefinedValueExample;

public interface DriverPropertyPredefinedValueMapper {
    long countByExample(DriverPropertyPredefinedValueExample example);

    int deleteByExample(DriverPropertyPredefinedValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPropertyPredefinedValue record);

    int insertSelective(@Param("record") DriverPropertyPredefinedValue record, @Param("selective") DriverPropertyPredefinedValue.Column... selective);

    List<DriverPropertyPredefinedValue> selectByExample(DriverPropertyPredefinedValueExample example);

    List<DriverPropertyPredefinedValue> selectByExampleSelective(@Param("example") DriverPropertyPredefinedValueExample example,
            @Param("selective") DriverPropertyPredefinedValue.Column... selective);

    List<DriverPropertyPredefinedValue> selectByExampleWithRowbounds(DriverPropertyPredefinedValueExample example, RowBounds rowBounds);

    DriverPropertyPredefinedValue selectByPrimaryKey(Long id);

    DriverPropertyPredefinedValue selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverPropertyPredefinedValue.Column... selective);

    DriverPropertyPredefinedValue selectOneByExample(DriverPropertyPredefinedValueExample example);

    DriverPropertyPredefinedValue selectOneByExampleSelective(@Param("example") DriverPropertyPredefinedValueExample example,
            @Param("selective") DriverPropertyPredefinedValue.Column... selective);

    int updateByExample(@Param("record") DriverPropertyPredefinedValue record, @Param("example") DriverPropertyPredefinedValueExample example);

    int updateByExampleSelective(@Param("record") DriverPropertyPredefinedValue record, @Param("example") DriverPropertyPredefinedValueExample example,
            @Param("selective") DriverPropertyPredefinedValue.Column... selective);

    int updateByPrimaryKey(DriverPropertyPredefinedValue record);

    int updateByPrimaryKeySelective(@Param("record") DriverPropertyPredefinedValue record, @Param("selective") DriverPropertyPredefinedValue.Column... selective);
}