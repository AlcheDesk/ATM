package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;

public interface RunSetFullReferenceMapper {

    long countByExample(RunSetExample example);

    List<RunSet> selectByExample(RunSetExample example);

    List<RunSet> selectByExampleSelective(@Param("example") RunSetExample example, @Param("selective") RunSet.Column... selective);

    List<RunSet> selectByExampleWithRowbounds(RunSetExample example, RowBounds rowBounds);

    RunSet selectByPrimaryKey(Long id);

    RunSet selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSet.Column... selective);

    RunSet selectOneByExample(RunSetExample example);

    RunSet selectOneByExampleSelective(@Param("example") RunSetExample example, @Param("selective") RunSet.Column... selective);

}