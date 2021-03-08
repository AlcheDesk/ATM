package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.ElementExample;

public interface ElementMapper {
    long countByExample(ElementExample example);

    int deleteByExample(ElementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Element record);

    int insertSelective(@Param("record") Element record, @Param("selective") Element.Column... selective);

    int logicalDeleteByExample(@Param("example") ElementExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Element> selectByExample(ElementExample example);

    List<Element> selectByExampleSelective(@Param("example") ElementExample example, @Param("selective") Element.Column... selective);

    List<Element> selectByExampleWithRowbounds(ElementExample example, RowBounds rowBounds);

    Element selectByPrimaryKey(Long id);

    Element selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Element.Column... selective);

    Element selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Element selectOneByExample(ElementExample example);

    Element selectOneByExampleSelective(@Param("example") ElementExample example, @Param("selective") Element.Column... selective);

    int updateByExample(@Param("record") Element record, @Param("example") ElementExample example);

    int updateByExampleSelective(@Param("record") Element record, @Param("example") ElementExample example, @Param("selective") Element.Column... selective);

    int updateByPrimaryKey(Element record);

    int updateByPrimaryKeySelective(@Param("record") Element record, @Param("selective") Element.Column... selective);
}