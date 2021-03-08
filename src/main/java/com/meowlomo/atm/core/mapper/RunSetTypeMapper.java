package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetType;
import com.meowlomo.atm.core.model.RunSetTypeExample;

public interface RunSetTypeMapper {
    long countByExample(RunSetTypeExample example);

    int deleteByExample(RunSetTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetType record);

    int insertSelective(@Param("record") RunSetType record, @Param("selective") RunSetType.Column... selective);

    List<RunSetType> selectByExample(RunSetTypeExample example);

    List<RunSetType> selectByExampleSelective(@Param("example") RunSetTypeExample example, @Param("selective") RunSetType.Column... selective);

    List<RunSetType> selectByExampleWithRowbounds(RunSetTypeExample example, RowBounds rowBounds);

    RunSetType selectByPrimaryKey(Long id);

    RunSetType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetType.Column... selective);

    RunSetType selectOneByExample(RunSetTypeExample example);

    RunSetType selectOneByExampleSelective(@Param("example") RunSetTypeExample example, @Param("selective") RunSetType.Column... selective);

    int updateByExample(@Param("record") RunSetType record, @Param("example") RunSetTypeExample example);

    int updateByExampleSelective(@Param("record") RunSetType record, @Param("example") RunSetTypeExample example, @Param("selective") RunSetType.Column... selective);

    int updateByPrimaryKey(RunSetType record);

    int updateByPrimaryKeySelective(@Param("record") RunSetType record, @Param("selective") RunSetType.Column... selective);
}