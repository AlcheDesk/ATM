package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.DriverTypeExample;

public interface DriverTypeMapper {
    long countByExample(DriverTypeExample example);

    int deleteByExample(DriverTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverType record);

    int insertSelective(@Param("record") DriverType record, @Param("selective") DriverType.Column... selective);

    List<DriverType> selectByExample(DriverTypeExample example);

    List<DriverType> selectByExampleSelective(@Param("example") DriverTypeExample example, @Param("selective") DriverType.Column... selective);

    List<DriverType> selectByExampleWithRowbounds(DriverTypeExample example, RowBounds rowBounds);

    DriverType selectByPrimaryKey(Long id);

    DriverType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverType.Column... selective);

    DriverType selectOneByExample(DriverTypeExample example);

    DriverType selectOneByExampleSelective(@Param("example") DriverTypeExample example, @Param("selective") DriverType.Column... selective);

    int updateByExample(@Param("record") DriverType record, @Param("example") DriverTypeExample example);

    int updateByExampleSelective(@Param("record") DriverType record, @Param("example") DriverTypeExample example, @Param("selective") DriverType.Column... selective);

    int updateByPrimaryKey(DriverType record);

    int updateByPrimaryKeySelective(@Param("record") DriverType record, @Param("selective") DriverType.Column... selective);
}