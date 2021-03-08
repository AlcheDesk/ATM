package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetAliasNameMap;
import com.meowlomo.atm.core.model.RunSetAliasNameMapExample;

public interface RunSetAliasNameMapMapper {
    long countByExample(RunSetAliasNameMapExample example);

    int deleteByExample(RunSetAliasNameMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetAliasNameMap record);

    int insertSelective(@Param("record") RunSetAliasNameMap record, @Param("selective") RunSetAliasNameMap.Column... selective);

    List<RunSetAliasNameMap> selectByExample(RunSetAliasNameMapExample example);

    List<RunSetAliasNameMap> selectByExampleSelective(@Param("example") RunSetAliasNameMapExample example, @Param("selective") RunSetAliasNameMap.Column... selective);

    List<RunSetAliasNameMap> selectByExampleWithRowbounds(RunSetAliasNameMapExample example, RowBounds rowBounds);

    RunSetAliasNameMap selectByPrimaryKey(Long id);

    RunSetAliasNameMap selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetAliasNameMap.Column... selective);

    RunSetAliasNameMap selectOneByExample(RunSetAliasNameMapExample example);

    RunSetAliasNameMap selectOneByExampleSelective(@Param("example") RunSetAliasNameMapExample example, @Param("selective") RunSetAliasNameMap.Column... selective);

    int updateByExample(@Param("record") RunSetAliasNameMap record, @Param("example") RunSetAliasNameMapExample example);

    int updateByExampleSelective(@Param("record") RunSetAliasNameMap record, @Param("example") RunSetAliasNameMapExample example,
            @Param("selective") RunSetAliasNameMap.Column... selective);

    int updateByPrimaryKey(RunSetAliasNameMap record);

    int updateByPrimaryKeySelective(@Param("record") RunSetAliasNameMap record, @Param("selective") RunSetAliasNameMap.Column... selective);
}