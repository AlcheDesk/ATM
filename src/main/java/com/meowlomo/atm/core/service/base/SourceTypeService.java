package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SourceType;
import com.meowlomo.atm.core.model.SourceTypeExample;

public interface SourceTypeService {
    long countByExample(SourceTypeExample example);

    int deleteByExample(SourceTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SourceType record);

    List<Long> insertRecords(List<SourceType> records);

    List<Long> insertRecordsSelective(List<SourceType> records);

    int insertSelective(SourceType record);

    List<SourceType> selectByExample(SourceTypeExample example);

    List<SourceType> selectByExampleWithRowbounds(SourceTypeExample example, RowBounds rowBounds);

    SourceType selectByPrimaryKey(Long id);

    SourceType selectOneByExample(SourceTypeExample example);

    int updateByExample(SourceType record, SourceTypeExample example);

    int updateByExampleSelective(SourceType record, SourceTypeExample example);

    int updateByPrimaryKey(SourceType record);

    int updateByPrimaryKeySelective(SourceType record);
}