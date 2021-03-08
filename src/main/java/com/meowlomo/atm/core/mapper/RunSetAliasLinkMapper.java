package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetAliasLink;
import com.meowlomo.atm.core.model.RunSetAliasLinkExample;

public interface RunSetAliasLinkMapper {
    long countByExample(RunSetAliasLinkExample example);

    int deleteByExample(RunSetAliasLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetAliasLink record);

    int insertSelective(@Param("record") RunSetAliasLink record, @Param("selective") RunSetAliasLink.Column... selective);

    List<RunSetAliasLink> selectByExample(RunSetAliasLinkExample example);

    List<RunSetAliasLink> selectByExampleSelective(@Param("example") RunSetAliasLinkExample example, @Param("selective") RunSetAliasLink.Column... selective);

    List<RunSetAliasLink> selectByExampleWithRowbounds(RunSetAliasLinkExample example, RowBounds rowBounds);

    RunSetAliasLink selectByPrimaryKey(Long id);

    RunSetAliasLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetAliasLink.Column... selective);

    RunSetAliasLink selectOneByExample(RunSetAliasLinkExample example);

    RunSetAliasLink selectOneByExampleSelective(@Param("example") RunSetAliasLinkExample example, @Param("selective") RunSetAliasLink.Column... selective);

    int updateByExample(@Param("record") RunSetAliasLink record, @Param("example") RunSetAliasLinkExample example);

    int updateByExampleSelective(@Param("record") RunSetAliasLink record, @Param("example") RunSetAliasLinkExample example,
            @Param("selective") RunSetAliasLink.Column... selective);

    int updateByPrimaryKey(RunSetAliasLink record);

    int updateByPrimaryKeySelective(@Param("record") RunSetAliasLink record, @Param("selective") RunSetAliasLink.Column... selective);
}