package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ParameterScript;
import com.meowlomo.atm.core.model.ParameterScriptExample;

public interface ParameterScriptMapper {
    long countByExample(ParameterScriptExample example);

    int deleteByExample(ParameterScriptExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ParameterScript record);

    int insertSelective(@Param("record") ParameterScript record, @Param("selective") ParameterScript.Column... selective);

    List<ParameterScript> selectByExample(ParameterScriptExample example);

    List<ParameterScript> selectByExampleSelective(@Param("example") ParameterScriptExample example, @Param("selective") ParameterScript.Column... selective);

    List<ParameterScript> selectByExampleWithRowbounds(ParameterScriptExample example, RowBounds rowBounds);

    ParameterScript selectByPrimaryKey(Long id);

    ParameterScript selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ParameterScript.Column... selective);

    ParameterScript selectOneByExample(ParameterScriptExample example);

    ParameterScript selectOneByExampleSelective(@Param("example") ParameterScriptExample example, @Param("selective") ParameterScript.Column... selective);

    int updateByExample(@Param("record") ParameterScript record, @Param("example") ParameterScriptExample example);

    int updateByExampleSelective(@Param("record") ParameterScript record, @Param("example") ParameterScriptExample example,
            @Param("selective") ParameterScript.Column... selective);

    int updateByPrimaryKey(ParameterScript record);

    int updateByPrimaryKeySelective(@Param("record") ParameterScript record, @Param("selective") ParameterScript.Column... selective);
}