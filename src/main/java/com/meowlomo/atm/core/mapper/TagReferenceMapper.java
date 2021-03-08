package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Tag;
import com.meowlomo.atm.core.model.TagExample;

public interface TagReferenceMapper {

    long countByExample(TagExample example);

    List<Tag> selectByExample(TagExample example);

    List<Tag> selectByExampleSelective(@Param("example") TagExample example, @Param("selective") Tag.Column... selective);

    List<Tag> selectByExampleWithRowbounds(TagExample example, RowBounds rowBounds);

    Tag selectByPrimaryKey(Long id);

    Tag selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Tag.Column... selective);

    Tag selectOneByExample(TagExample example);

    Tag selectOneByExampleSelective(@Param("example") TagExample example, @Param("selective") Tag.Column... selective);

}