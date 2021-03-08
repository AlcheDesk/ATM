package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementLocatorType;
import com.meowlomo.atm.core.model.ElementLocatorTypeExample;

public interface ElementLocatorTypeService {
    long countByExample(ElementLocatorTypeExample example);

    int deleteByExample(ElementLocatorTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementLocatorType record);

    List<Long> insertRecords(List<ElementLocatorType> records);

    List<Long> insertRecordsSelective(List<ElementLocatorType> records);

    int insertSelective(ElementLocatorType record);

    List<ElementLocatorType> selectByExample(ElementLocatorTypeExample example);

    List<ElementLocatorType> selectByExampleWithRowbounds(ElementLocatorTypeExample example, RowBounds rowBounds);

    ElementLocatorType selectByPrimaryKey(Long id);

    ElementLocatorType selectOneByExample(ElementLocatorTypeExample example);

    int updateByExample(ElementLocatorType record, ElementLocatorTypeExample example);

    int updateByExampleSelective(ElementLocatorType record, ElementLocatorTypeExample example);

    int updateByPrimaryKey(ElementLocatorType record);

    int updateByPrimaryKeySelective(ElementLocatorType record);
}