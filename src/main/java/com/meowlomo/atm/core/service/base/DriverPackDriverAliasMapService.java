package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPackDriverAliasMap;
import com.meowlomo.atm.core.model.DriverPackDriverAliasMapExample;

public interface DriverPackDriverAliasMapService {
    long countByExample(DriverPackDriverAliasMapExample example);

    List<DriverPackDriverAliasMap> selectByExample(DriverPackDriverAliasMapExample example);

    List<DriverPackDriverAliasMap> selectByExampleWithRowbounds(DriverPackDriverAliasMapExample example,
            RowBounds rowBounds);

    DriverPackDriverAliasMap selectByPrimaryKey(Long id);

    DriverPackDriverAliasMap selectOneByExample(DriverPackDriverAliasMapExample example);

}