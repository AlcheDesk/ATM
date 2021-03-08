package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;

public interface RunMapper {
    long countByExample(RunExample example);

    int deleteByExample(RunExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Run record);

    int insertSelective(@Param("record") Run record, @Param("selective") Run.Column... selective);

    List<Run> selectByExample(RunExample example);

    List<Run> selectByExampleSelective(@Param("example") RunExample example, @Param("selective") Run.Column... selective);

    List<Run> selectByExampleWithRowbounds(RunExample example, RowBounds rowBounds);

    Run selectByPrimaryKey(Long id);

    Run selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Run.Column... selective);

    Run selectOneByExample(RunExample example);

    Run selectOneByExampleSelective(@Param("example") RunExample example, @Param("selective") Run.Column... selective);

    int updateByExample(@Param("record") Run record, @Param("example") RunExample example);

    int updateByExampleSelective(@Param("record") Run record, @Param("example") RunExample example, @Param("selective") Run.Column... selective);

    int updateByPrimaryKey(Run record);

    int updateByPrimaryKeySelective(@Param("record") Run record, @Param("selective") Run.Column... selective);
}