package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;

public interface RunSetMapper {
    long countByExample(RunSetExample example);

    int deleteByExample(RunSetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSet record);

    int insertSelective(@Param("record") RunSet record, @Param("selective") RunSet.Column... selective);

    int logicalDeleteByExample(@Param("example") RunSetExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<RunSet> selectByExample(RunSetExample example);

    List<RunSet> selectByExampleSelective(@Param("example") RunSetExample example, @Param("selective") RunSet.Column... selective);

    List<RunSet> selectByExampleWithRowbounds(RunSetExample example, RowBounds rowBounds);

    RunSet selectByPrimaryKey(Long id);

    RunSet selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSet.Column... selective);

    RunSet selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    RunSet selectOneByExample(RunSetExample example);

    RunSet selectOneByExampleSelective(@Param("example") RunSetExample example, @Param("selective") RunSet.Column... selective);

    int updateByExample(@Param("record") RunSet record, @Param("example") RunSetExample example);

    int updateByExampleSelective(@Param("record") RunSet record, @Param("example") RunSetExample example, @Param("selective") RunSet.Column... selective);

    int updateByPrimaryKey(RunSet record);

    int updateByPrimaryKeySelective(@Param("record") RunSet record, @Param("selective") RunSet.Column... selective);
}