package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Tag;
import com.meowlomo.atm.core.model.TagExample;

public interface TagMapper {
    long countByExample(TagExample example);

    int deleteByExample(TagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(@Param("record") Tag record, @Param("selective") Tag.Column... selective);

    List<Tag> selectByExample(TagExample example);

    List<Tag> selectByExampleSelective(@Param("example") TagExample example, @Param("selective") Tag.Column... selective);

    List<Tag> selectByExampleWithRowbounds(TagExample example, RowBounds rowBounds);

    Tag selectByPrimaryKey(Long id);

    Tag selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Tag.Column... selective);

    Tag selectOneByExample(TagExample example);

    Tag selectOneByExampleSelective(@Param("example") TagExample example, @Param("selective") Tag.Column... selective);

    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example, @Param("selective") Tag.Column... selective);

    int updateByPrimaryKey(Tag record);

    int updateByPrimaryKeySelective(@Param("record") Tag record, @Param("selective") Tag.Column... selective);
}