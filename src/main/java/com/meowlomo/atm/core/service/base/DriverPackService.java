package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackExample;

public interface DriverPackService {
    long countByExample(DriverPackExample example);

    int deleteByExample(DriverPackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPack record);

    List<Long> insertRecords(List<DriverPack> records);

    List<Long> insertRecordsSelective(List<DriverPack> records);

    int insertSelective(DriverPack record);

    int logicalDeleteByExample(DriverPackExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<DriverPack> selectByExample(DriverPackExample example);

    List<DriverPack> selectByExampleWithRowbounds(DriverPackExample example, RowBounds rowBounds);

    DriverPack selectByPrimaryKey(Long id);

    DriverPack selectOneByExample(DriverPackExample example);

    int updateByExample(DriverPack record, DriverPackExample example);

    int updateByExampleSelective(DriverPack record, DriverPackExample example);

    int updateByPrimaryKey(DriverPack record);

    int updateByPrimaryKeySelective(DriverPack record);
}