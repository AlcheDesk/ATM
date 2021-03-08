package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeExample;

public interface ElementTypeService {
    long countByExample(ElementTypeExample example);

    int deleteByExample(ElementTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementType record);

    List<Long> insertRecords(List<ElementType> records);

    List<Long> insertRecordsSelective(List<ElementType> records);

    int insertSelective(ElementType record);

    List<ElementType> selectByExample(ElementTypeExample example);

    List<ElementType> selectByExampleWithRowbounds(ElementTypeExample example, RowBounds rowBounds);

    ElementType selectByPrimaryKey(Long id);

    ElementType selectOneByExample(ElementTypeExample example);

    int updateByExample(ElementType record, ElementTypeExample example);

    int updateByExampleSelective(ElementType record, ElementTypeExample example);

    int updateByPrimaryKey(ElementType record);

    int updateByPrimaryKeySelective(ElementType record);
}