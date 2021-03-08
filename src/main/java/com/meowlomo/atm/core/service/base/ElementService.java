package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.ElementExample;

public interface ElementService {
    long countByExample(ElementExample example);

    int deleteByExample(ElementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Element record);

    List<Long> insertRecords(List<Element> records);

    List<Long> insertRecordsSelective(List<Element> records);

    int insertSelective(Element record);

    int logicalDeleteByExample(ElementExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Element> selectByExample(ElementExample example);

    List<Element> selectByExampleWithRowbounds(ElementExample example, RowBounds rowBounds);

    Element selectByPrimaryKey(Long id);

    Element selectOneByExample(ElementExample example);

    int updateByExample(Element record, ElementExample example);

    int updateByExampleSelective(Element record, ElementExample example);

    int updateByPrimaryKey(Element record);

    int updateByPrimaryKeySelective(Element record);
}