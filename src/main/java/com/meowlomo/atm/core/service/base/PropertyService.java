package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Property;
import com.meowlomo.atm.core.model.PropertyExample;

public interface PropertyService {
    long countByExample(PropertyExample example);

    int deleteByExample(PropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Property record);

    List<Long> insertRecords(List<Property> records);

    List<Long> insertRecordsSelective(List<Property> records);

    int insertSelective(Property record);

    List<Property> selectByExample(PropertyExample example);

    List<Property> selectByExampleWithRowbounds(PropertyExample example, RowBounds rowBounds);

    Property selectByPrimaryKey(Long id);

    Property selectOneByExample(PropertyExample example);

    int updateByExample(Property record, PropertyExample example);

    int updateByExampleSelective(Property record, PropertyExample example);

    int updateByPrimaryKey(Property record);

    int updateByPrimaryKeySelective(Property record);
}