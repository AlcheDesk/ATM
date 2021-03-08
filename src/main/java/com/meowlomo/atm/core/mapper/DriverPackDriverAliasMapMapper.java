package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPackDriverAliasMap;
import com.meowlomo.atm.core.model.DriverPackDriverAliasMapExample;

public interface DriverPackDriverAliasMapMapper {
    long countByExample(DriverPackDriverAliasMapExample example);

    List<DriverPackDriverAliasMap> selectByExample(DriverPackDriverAliasMapExample example);

    List<DriverPackDriverAliasMap> selectByExampleSelective(@Param("example") DriverPackDriverAliasMapExample example,
            @Param("selective") DriverPackDriverAliasMap.Column... selective);

    List<DriverPackDriverAliasMap> selectByExampleWithRowbounds(DriverPackDriverAliasMapExample example, RowBounds rowBounds);

    DriverPackDriverAliasMap selectByPrimaryKey(Long id);

    DriverPackDriverAliasMap selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverPackDriverAliasMap.Column... selective);

    DriverPackDriverAliasMap selectOneByExample(DriverPackDriverAliasMapExample example);

    DriverPackDriverAliasMap selectOneByExampleSelective(@Param("example") DriverPackDriverAliasMapExample example,
            @Param("selective") DriverPackDriverAliasMap.Column... selective);

}