package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPackDriverTypeMap;
import com.meowlomo.atm.core.model.DriverPackDriverTypeMapExample;

public interface DriverPackDriverTypeMapService {
    long countByExample(DriverPackDriverTypeMapExample example);

    int deleteByExample(DriverPackDriverTypeMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPackDriverTypeMap record);

    List<Long> insertRecords(List<DriverPackDriverTypeMap> records);

    List<Long> insertRecordsSelective(List<DriverPackDriverTypeMap> records);

    int insertSelective(DriverPackDriverTypeMap record);

    List<DriverPackDriverTypeMap> selectByExample(DriverPackDriverTypeMapExample example);

    List<DriverPackDriverTypeMap> selectByExampleWithRowbounds(DriverPackDriverTypeMapExample example,
            RowBounds rowBounds);

    DriverPackDriverTypeMap selectByPrimaryKey(Long id);

    DriverPackDriverTypeMap selectOneByExample(DriverPackDriverTypeMapExample example);

    int updateByExample(DriverPackDriverTypeMap record, DriverPackDriverTypeMapExample example);

    int updateByExampleSelective(DriverPackDriverTypeMap record, DriverPackDriverTypeMapExample example);

    int updateByPrimaryKey(DriverPackDriverTypeMap record);

    int updateByPrimaryKeySelective(DriverPackDriverTypeMap record);
}