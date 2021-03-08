package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementTypeElementLocatorTypeLink;
import com.meowlomo.atm.core.model.ElementTypeElementLocatorTypeLinkExample;

public interface ElementTypeElementLocatorTypeLinkMapper {
    long countByExample(ElementTypeElementLocatorTypeLinkExample example);

    int deleteByExample(ElementTypeElementLocatorTypeLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementTypeElementLocatorTypeLink record);

    int insertSelective(@Param("record") ElementTypeElementLocatorTypeLink record, @Param("selective") ElementTypeElementLocatorTypeLink.Column... selective);

    List<ElementTypeElementLocatorTypeLink> selectByExample(ElementTypeElementLocatorTypeLinkExample example);

    List<ElementTypeElementLocatorTypeLink> selectByExampleSelective(@Param("example") ElementTypeElementLocatorTypeLinkExample example,
            @Param("selective") ElementTypeElementLocatorTypeLink.Column... selective);

    List<ElementTypeElementLocatorTypeLink> selectByExampleWithRowbounds(ElementTypeElementLocatorTypeLinkExample example, RowBounds rowBounds);

    ElementTypeElementLocatorTypeLink selectByPrimaryKey(Long id);

    ElementTypeElementLocatorTypeLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ElementTypeElementLocatorTypeLink.Column... selective);

    ElementTypeElementLocatorTypeLink selectOneByExample(ElementTypeElementLocatorTypeLinkExample example);

    ElementTypeElementLocatorTypeLink selectOneByExampleSelective(@Param("example") ElementTypeElementLocatorTypeLinkExample example,
            @Param("selective") ElementTypeElementLocatorTypeLink.Column... selective);

    int updateByExample(@Param("record") ElementTypeElementLocatorTypeLink record, @Param("example") ElementTypeElementLocatorTypeLinkExample example);

    int updateByExampleSelective(@Param("record") ElementTypeElementLocatorTypeLink record, @Param("example") ElementTypeElementLocatorTypeLinkExample example,
            @Param("selective") ElementTypeElementLocatorTypeLink.Column... selective);

    int updateByPrimaryKey(ElementTypeElementLocatorTypeLink record);

    int updateByPrimaryKeySelective(@Param("record") ElementTypeElementLocatorTypeLink record, @Param("selective") ElementTypeElementLocatorTypeLink.Column... selective);
}