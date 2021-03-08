package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementLocatorType;
import com.meowlomo.atm.core.model.ElementLocatorTypeExample;

public interface ElementLocatorTypeMapper {
    long countByExample(ElementLocatorTypeExample example);

    int deleteByExample(ElementLocatorTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementLocatorType record);

    int insertSelective(@Param("record") ElementLocatorType record, @Param("selective") ElementLocatorType.Column... selective);

    List<ElementLocatorType> selectByExample(ElementLocatorTypeExample example);

    List<ElementLocatorType> selectByExampleSelective(@Param("example") ElementLocatorTypeExample example, @Param("selective") ElementLocatorType.Column... selective);

    List<ElementLocatorType> selectByExampleWithRowbounds(ElementLocatorTypeExample example, RowBounds rowBounds);

    ElementLocatorType selectByPrimaryKey(Long id);

    ElementLocatorType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ElementLocatorType.Column... selective);

    ElementLocatorType selectOneByExample(ElementLocatorTypeExample example);

    ElementLocatorType selectOneByExampleSelective(@Param("example") ElementLocatorTypeExample example, @Param("selective") ElementLocatorType.Column... selective);

    int updateByExample(@Param("record") ElementLocatorType record, @Param("example") ElementLocatorTypeExample example);

    int updateByExampleSelective(@Param("record") ElementLocatorType record, @Param("example") ElementLocatorTypeExample example,
            @Param("selective") ElementLocatorType.Column... selective);

    int updateByPrimaryKey(ElementLocatorType record);

    int updateByPrimaryKeySelective(@Param("record") ElementLocatorType record, @Param("selective") ElementLocatorType.Column... selective);
}