package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.AliasExample;

public interface AliasMapper {
    long countByExample(AliasExample example);

    int deleteByExample(AliasExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Alias record);

    int insertSelective(@Param("record") Alias record, @Param("selective") Alias.Column... selective);

    int logicalDeleteByExample(@Param("example") AliasExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Alias> selectByExample(AliasExample example);

    List<Alias> selectByExampleSelective(@Param("example") AliasExample example, @Param("selective") Alias.Column... selective);

    List<Alias> selectByExampleWithRowbounds(AliasExample example, RowBounds rowBounds);

    Alias selectByPrimaryKey(Long id);

    Alias selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Alias.Column... selective);

    Alias selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Alias selectOneByExample(AliasExample example);

    Alias selectOneByExampleSelective(@Param("example") AliasExample example, @Param("selective") Alias.Column... selective);

    int updateByExample(@Param("record") Alias record, @Param("example") AliasExample example);

    int updateByExampleSelective(@Param("record") Alias record, @Param("example") AliasExample example, @Param("selective") Alias.Column... selective);

    int updateByPrimaryKey(Alias record);

    int updateByPrimaryKeySelective(@Param("record") Alias record, @Param("selective") Alias.Column... selective);
}