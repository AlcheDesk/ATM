package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeExample;

public interface ElementTypeMapper {
    long countByExample(ElementTypeExample example);

    int deleteByExample(ElementTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementType record);

    int insertSelective(@Param("record") ElementType record, @Param("selective") ElementType.Column... selective);

    List<ElementType> selectByExample(ElementTypeExample example);

    List<ElementType> selectByExampleSelective(@Param("example") ElementTypeExample example, @Param("selective") ElementType.Column... selective);

    List<ElementType> selectByExampleWithRowbounds(ElementTypeExample example, RowBounds rowBounds);

    ElementType selectByPrimaryKey(Long id);

    ElementType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ElementType.Column... selective);

    ElementType selectOneByExample(ElementTypeExample example);

    ElementType selectOneByExampleSelective(@Param("example") ElementTypeExample example, @Param("selective") ElementType.Column... selective);

    int updateByExample(@Param("record") ElementType record, @Param("example") ElementTypeExample example);

    int updateByExampleSelective(@Param("record") ElementType record, @Param("example") ElementTypeExample example, @Param("selective") ElementType.Column... selective);

    int updateByPrimaryKey(ElementType record);

    int updateByPrimaryKeySelective(@Param("record") ElementType record, @Param("selective") ElementType.Column... selective);
}