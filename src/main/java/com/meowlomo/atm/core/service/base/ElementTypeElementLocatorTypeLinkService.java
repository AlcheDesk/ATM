package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeElementLocatorTypeLink;
import com.meowlomo.atm.core.model.ElementTypeElementLocatorTypeLinkExample;

public interface ElementTypeElementLocatorTypeLinkService {
    long countByExample(ElementTypeElementLocatorTypeLinkExample example);

    int deleteByExample(ElementTypeElementLocatorTypeLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementTypeElementLocatorTypeLink record);

    List<Long> insertRecords(List<ElementTypeElementLocatorTypeLink> records);

    List<Long> insertRecordsSelective(List<ElementTypeElementLocatorTypeLink> records);

    int insertSelective(ElementTypeElementLocatorTypeLink record);

    List<ElementType> listElementTypeByElementLocatorTypePrimaryKey(Long elementLocatorTypeId);

    List<ElementTypeElementLocatorTypeLink> selectByExample(ElementTypeElementLocatorTypeLinkExample example);

    List<ElementTypeElementLocatorTypeLink> selectByExampleWithRowbounds(
            ElementTypeElementLocatorTypeLinkExample example, RowBounds rowBounds);

    ElementTypeElementLocatorTypeLink selectByPrimaryKey(Long id);

    ElementTypeElementLocatorTypeLink selectOneByExample(ElementTypeElementLocatorTypeLinkExample example);

    int updateByExample(ElementTypeElementLocatorTypeLink record, ElementTypeElementLocatorTypeLinkExample example);

    int updateByExampleSelective(ElementTypeElementLocatorTypeLink record,
            ElementTypeElementLocatorTypeLinkExample example);

    int updateByPrimaryKey(ElementTypeElementLocatorTypeLink record);

    int updateByPrimaryKeySelective(ElementTypeElementLocatorTypeLink record);
}