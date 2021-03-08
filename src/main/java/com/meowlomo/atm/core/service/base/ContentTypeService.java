package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ContentType;
import com.meowlomo.atm.core.model.ContentTypeExample;

public interface ContentTypeService {
    long countByExample(ContentTypeExample example);

    int deleteByExample(ContentTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ContentType record);

    List<Long> insertRecords(List<ContentType> records);

    List<Long> insertRecordsSelective(List<ContentType> records);

    int insertSelective(ContentType record);

    List<ContentType> selectByExample(ContentTypeExample example);

    List<ContentType> selectByExampleWithRowbounds(ContentTypeExample example, RowBounds rowBounds);

    ContentType selectByPrimaryKey(Long id);

    ContentType selectOneByExample(ContentTypeExample example);

    int updateByExample(ContentType record, ContentTypeExample example);

    int updateByExampleSelective(ContentType record, ContentTypeExample example);

    int updateByPrimaryKey(ContentType record);

    int updateByPrimaryKeySelective(ContentType record);
}