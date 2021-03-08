package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;

public interface RunSetResultMapper {
    long countByExample(RunSetResultExample example);

    int deleteByExample(RunSetResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetResult record);

    int insertSelective(@Param("record") RunSetResult record, @Param("selective") RunSetResult.Column... selective);

    List<RunSetResult> selectByExample(RunSetResultExample example);

    List<RunSetResult> selectByExampleSelective(@Param("example") RunSetResultExample example, @Param("selective") RunSetResult.Column... selective);

    List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample example, RowBounds rowBounds);

    RunSetResult selectByPrimaryKey(Long id);

    RunSetResult selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetResult.Column... selective);

    RunSetResult selectOneByExample(RunSetResultExample example);

    RunSetResult selectOneByExampleSelective(@Param("example") RunSetResultExample example, @Param("selective") RunSetResult.Column... selective);

    int updateByExample(@Param("record") RunSetResult record, @Param("example") RunSetResultExample example);

    int updateByExampleSelective(@Param("record") RunSetResult record, @Param("example") RunSetResultExample example, @Param("selective") RunSetResult.Column... selective);

    int updateByPrimaryKey(RunSetResult record);

    int updateByPrimaryKeySelective(@Param("record") RunSetResult record, @Param("selective") RunSetResult.Column... selective);
}