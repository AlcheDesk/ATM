package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;

public interface DevRunReferenceMapper {

    long countByExample(RunExample example);

    List<Run> selectByExample(RunExample example);

    List<Run> selectByExampleSelective(@Param("example") RunExample example, @Param("selective") Run.Column... selective);

    List<Run> selectByExampleWithRowbounds(RunExample example, RowBounds rowBounds);

    Run selectByPrimaryKey(Long id);

    Run selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Run.Column... selective);

    Run selectOneByExample(RunExample example);

    Run selectOneByExampleSelective(@Param("example") RunExample example, @Param("selective") Run.Column... selective);

}