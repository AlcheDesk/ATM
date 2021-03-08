package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPackDriverTypeMap;
import com.meowlomo.atm.core.model.DriverPackDriverTypeMapExample;

public interface DriverPackDriverTypeMapMapper {
    long countByExample(DriverPackDriverTypeMapExample example);

    int deleteByExample(DriverPackDriverTypeMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPackDriverTypeMap record);

    int insertSelective(@Param("record") DriverPackDriverTypeMap record, @Param("selective") DriverPackDriverTypeMap.Column... selective);

    List<DriverPackDriverTypeMap> selectByExample(DriverPackDriverTypeMapExample example);

    List<DriverPackDriverTypeMap> selectByExampleSelective(@Param("example") DriverPackDriverTypeMapExample example,
            @Param("selective") DriverPackDriverTypeMap.Column... selective);

    List<DriverPackDriverTypeMap> selectByExampleWithRowbounds(DriverPackDriverTypeMapExample example, RowBounds rowBounds);

    DriverPackDriverTypeMap selectByPrimaryKey(Long id);

    DriverPackDriverTypeMap selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverPackDriverTypeMap.Column... selective);

    DriverPackDriverTypeMap selectOneByExample(DriverPackDriverTypeMapExample example);

    DriverPackDriverTypeMap selectOneByExampleSelective(@Param("example") DriverPackDriverTypeMapExample example, @Param("selective") DriverPackDriverTypeMap.Column... selective);

    int updateByExample(@Param("record") DriverPackDriverTypeMap record, @Param("example") DriverPackDriverTypeMapExample example);

    int updateByExampleSelective(@Param("record") DriverPackDriverTypeMap record, @Param("example") DriverPackDriverTypeMapExample example,
            @Param("selective") DriverPackDriverTypeMap.Column... selective);

    int updateByPrimaryKey(DriverPackDriverTypeMap record);

    int updateByPrimaryKeySelective(@Param("record") DriverPackDriverTypeMap record, @Param("selective") DriverPackDriverTypeMap.Column... selective);
}