package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.AliasExample;

public interface AliasService {
    long countByExample(AliasExample example);

    int deleteByExample(AliasExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Alias record);

    List<Long> insertRecords(List<Alias> records);

    List<Long> insertRecordsSelective(List<Alias> records);

    int insertSelective(Alias record);

    int logicalDeleteByExample(AliasExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Alias> selectByExample(AliasExample example);

    List<Alias> selectByExampleWithRowbounds(AliasExample example, RowBounds rowBounds);

    Alias selectByPrimaryKey(Long id);

    Alias selectOneByExample(AliasExample example);

    int updateByExample(Alias record, AliasExample example);

    int updateByExampleSelective(Alias record, AliasExample example);

    int updateByPrimaryKey(Alias record);

    int updateByPrimaryKeySelective(Alias record);
}