package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverProperty;
import com.meowlomo.atm.core.model.DriverPropertyExample;

public interface DriverPropertyMapper {
    long countByExample(DriverPropertyExample example);

    int deleteByExample(DriverPropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverProperty record);

    int insertSelective(@Param("record") DriverProperty record, @Param("selective") DriverProperty.Column... selective);

    List<DriverProperty> selectByExample(DriverPropertyExample example);

    List<DriverProperty> selectByExampleSelective(@Param("example") DriverPropertyExample example, @Param("selective") DriverProperty.Column... selective);

    List<DriverProperty> selectByExampleWithRowbounds(DriverPropertyExample example, RowBounds rowBounds);

    DriverProperty selectByPrimaryKey(Long id);

    DriverProperty selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverProperty.Column... selective);

    DriverProperty selectOneByExample(DriverPropertyExample example);

    DriverProperty selectOneByExampleSelective(@Param("example") DriverPropertyExample example, @Param("selective") DriverProperty.Column... selective);

    int updateByExample(@Param("record") DriverProperty record, @Param("example") DriverPropertyExample example);

    int updateByExampleSelective(@Param("record") DriverProperty record, @Param("example") DriverPropertyExample example, @Param("selective") DriverProperty.Column... selective);

    int updateByPrimaryKey(DriverProperty record);

    int updateByPrimaryKeySelective(@Param("record") DriverProperty record, @Param("selective") DriverProperty.Column... selective);
}