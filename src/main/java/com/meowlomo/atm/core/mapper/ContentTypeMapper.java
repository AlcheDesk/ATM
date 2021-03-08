package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ContentType;
import com.meowlomo.atm.core.model.ContentTypeExample;

public interface ContentTypeMapper {
    long countByExample(ContentTypeExample example);

    int deleteByExample(ContentTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ContentType record);

    int insertSelective(@Param("record") ContentType record, @Param("selective") ContentType.Column... selective);

    List<ContentType> selectByExample(ContentTypeExample example);

    List<ContentType> selectByExampleSelective(@Param("example") ContentTypeExample example, @Param("selective") ContentType.Column... selective);

    List<ContentType> selectByExampleWithRowbounds(ContentTypeExample example, RowBounds rowBounds);

    ContentType selectByPrimaryKey(Long id);

    ContentType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ContentType.Column... selective);

    ContentType selectOneByExample(ContentTypeExample example);

    ContentType selectOneByExampleSelective(@Param("example") ContentTypeExample example, @Param("selective") ContentType.Column... selective);

    int updateByExample(@Param("record") ContentType record, @Param("example") ContentTypeExample example);

    int updateByExampleSelective(@Param("record") ContentType record, @Param("example") ContentTypeExample example, @Param("selective") ContentType.Column... selective);

    int updateByPrimaryKey(ContentType record);

    int updateByPrimaryKeySelective(@Param("record") ContentType record, @Param("selective") ContentType.Column... selective);
}