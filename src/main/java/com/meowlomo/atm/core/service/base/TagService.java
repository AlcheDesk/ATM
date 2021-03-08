package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Tag;
import com.meowlomo.atm.core.model.TagExample;

public interface TagService {
    long countByExample(TagExample example);

    int deleteByExample(TagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    List<Long> insertRecords(List<Tag> records);

    List<Long> insertRecordsSelective(List<Tag> records);

    int insertSelective(Tag record);

    List<Tag> selectByExample(TagExample example);

    List<Tag> selectByExampleWithRowbounds(TagExample example, RowBounds rowBounds);

    Tag selectByPrimaryKey(Long id);

    Tag selectOneByExample(TagExample example);

    int updateByExample(Tag record, TagExample example);

    int updateByExampleSelective(Tag record, TagExample example);

    int updateByPrimaryKey(Tag record);

    int updateByPrimaryKeySelective(Tag record);
}