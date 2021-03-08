package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPackDriverLink;
import com.meowlomo.atm.core.model.DriverPackDriverLinkExample;

public interface DriverPackDriverLinkService {
    long countByExample(DriverPackDriverLinkExample example);

    int deleteByExample(DriverPackDriverLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPackDriverLink record);

    List<Long> insertRecords(List<DriverPackDriverLink> records);

    List<Long> insertRecordsSelective(List<DriverPackDriverLink> records);

    int insertSelective(DriverPackDriverLink record);

    List<DriverPackDriverLink> selectByExample(DriverPackDriverLinkExample example);

    List<DriverPackDriverLink> selectByExampleWithRowbounds(DriverPackDriverLinkExample example, RowBounds rowBounds);

    DriverPackDriverLink selectByPrimaryKey(Long id);

    DriverPackDriverLink selectOneByExample(DriverPackDriverLinkExample example);

    int updateByExample(DriverPackDriverLink record, DriverPackDriverLinkExample example);

    int updateByExampleSelective(DriverPackDriverLink record, DriverPackDriverLinkExample example);

    int updateByPrimaryKey(DriverPackDriverLink record);

    int updateByPrimaryKeySelective(DriverPackDriverLink record);
}