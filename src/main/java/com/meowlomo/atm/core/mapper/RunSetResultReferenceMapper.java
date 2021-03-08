package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;

public interface RunSetResultReferenceMapper {

    long countByExample(RunSetResultExample example);

    List<RunSetResult> selectByExample(RunSetResultExample example);

    List<RunSetResult> selectByExampleSelective(@Param("example") RunSetResultExample example, @Param("selective") RunSetResult.Column... selective);

    List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample example, RowBounds rowBounds);

    RunSetResult selectByPrimaryKey(Long id);

    RunSetResult selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetResult.Column... selective);

    RunSetResult selectOneByExample(RunSetResultExample example);

    RunSetResult selectOneByExampleSelective(@Param("example") RunSetResultExample example, @Param("selective") RunSetResult.Column... selective);

}