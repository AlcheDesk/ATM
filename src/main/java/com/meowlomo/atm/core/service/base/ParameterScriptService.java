package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ParameterScript;
import com.meowlomo.atm.core.model.ParameterScriptExample;

public interface ParameterScriptService {
    long countByExample(ParameterScriptExample example);

    int deleteByExample(ParameterScriptExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ParameterScript record);

    List<Long> insertRecords(List<ParameterScript> records);

    List<Long> insertRecordsSelective(List<ParameterScript> records);

    int insertSelective(ParameterScript record);

    List<ParameterScript> selectByExample(ParameterScriptExample example);

    List<ParameterScript> selectByExampleWithRowbounds(ParameterScriptExample example, RowBounds rowBounds);

    ParameterScript selectByPrimaryKey(Long id);

    ParameterScript selectOneByExample(ParameterScriptExample example);

    int updateByExample(ParameterScript record, ParameterScriptExample example);

    int updateByExampleSelective(ParameterScript record, ParameterScriptExample example);

    int updateByPrimaryKey(ParameterScript record);

    int updateByPrimaryKeySelective(ParameterScript record);
}