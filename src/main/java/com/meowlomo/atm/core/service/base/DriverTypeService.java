package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.DriverTypeExample;

public interface DriverTypeService {
    long countByExample(DriverTypeExample example);

    int deleteByExample(DriverTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverType record);

    List<Long> insertRecords(List<DriverType> records);

    List<Long> insertRecordsSelective(List<DriverType> records);

    int insertSelective(DriverType record);

    List<DriverType> selectByExample(DriverTypeExample example);

    List<DriverType> selectByExampleWithRowbounds(DriverTypeExample example, RowBounds rowBounds);

    DriverType selectByPrimaryKey(Long id);

    DriverType selectOneByExample(DriverTypeExample example);

    int updateByExample(DriverType record, DriverTypeExample example);

    int updateByExampleSelective(DriverType record, DriverTypeExample example);

    int updateByPrimaryKey(DriverType record);

    int updateByPrimaryKeySelective(DriverType record);
}