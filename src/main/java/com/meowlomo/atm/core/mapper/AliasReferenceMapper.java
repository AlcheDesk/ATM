package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.AliasExample;

public interface AliasReferenceMapper {

    long countByExample(AliasExample example);

    List<Alias> selectByExample(AliasExample example);

    List<Alias> selectByExampleSelective(@Param("example") AliasExample example, @Param("selective") Alias.Column... selective);

    List<Alias> selectByExampleWithRowbounds(AliasExample example, RowBounds rowBounds);

    Alias selectByPrimaryKey(Long id);

    Alias selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Alias.Column... selective);

    Alias selectOneByExample(AliasExample example);

    Alias selectOneByExampleSelective(@Param("example") AliasExample example, @Param("selective") Alias.Column... selective);

}