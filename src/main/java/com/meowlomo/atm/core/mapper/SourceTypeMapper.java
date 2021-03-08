package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SourceType;
import com.meowlomo.atm.core.model.SourceTypeExample;

public interface SourceTypeMapper {
    long countByExample(SourceTypeExample example);

    int deleteByExample(SourceTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SourceType record);

    int insertSelective(@Param("record") SourceType record, @Param("selective") SourceType.Column... selective);

    List<SourceType> selectByExample(SourceTypeExample example);

    List<SourceType> selectByExampleSelective(@Param("example") SourceTypeExample example, @Param("selective") SourceType.Column... selective);

    List<SourceType> selectByExampleWithRowbounds(SourceTypeExample example, RowBounds rowBounds);

    SourceType selectByPrimaryKey(Long id);

    SourceType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SourceType.Column... selective);

    SourceType selectOneByExample(SourceTypeExample example);

    SourceType selectOneByExampleSelective(@Param("example") SourceTypeExample example, @Param("selective") SourceType.Column... selective);

    int updateByExample(@Param("record") SourceType record, @Param("example") SourceTypeExample example);

    int updateByExampleSelective(@Param("record") SourceType record, @Param("example") SourceTypeExample example, @Param("selective") SourceType.Column... selective);

    int updateByPrimaryKey(SourceType record);

    int updateByPrimaryKeySelective(@Param("record") SourceType record, @Param("selective") SourceType.Column... selective);
}