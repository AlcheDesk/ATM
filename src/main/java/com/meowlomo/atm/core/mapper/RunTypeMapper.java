package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunType;
import com.meowlomo.atm.core.model.RunTypeExample;

public interface RunTypeMapper {
    long countByExample(RunTypeExample example);

    int deleteByExample(RunTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunType record);

    int insertSelective(@Param("record") RunType record, @Param("selective") RunType.Column... selective);

    List<RunType> selectByExample(RunTypeExample example);

    List<RunType> selectByExampleSelective(@Param("example") RunTypeExample example, @Param("selective") RunType.Column... selective);

    List<RunType> selectByExampleWithRowbounds(RunTypeExample example, RowBounds rowBounds);

    RunType selectByPrimaryKey(Long id);

    RunType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunType.Column... selective);

    RunType selectOneByExample(RunTypeExample example);

    RunType selectOneByExampleSelective(@Param("example") RunTypeExample example, @Param("selective") RunType.Column... selective);

    int updateByExample(@Param("record") RunType record, @Param("example") RunTypeExample example);

    int updateByExampleSelective(@Param("record") RunType record, @Param("example") RunTypeExample example, @Param("selective") RunType.Column... selective);

    int updateByPrimaryKey(RunType record);

    int updateByPrimaryKeySelective(@Param("record") RunType record, @Param("selective") RunType.Column... selective);
}