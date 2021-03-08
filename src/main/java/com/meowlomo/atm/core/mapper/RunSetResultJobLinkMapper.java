package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetResultJobLink;
import com.meowlomo.atm.core.model.RunSetResultJobLinkExample;

public interface RunSetResultJobLinkMapper {
    long countByExample(RunSetResultJobLinkExample example);

    int deleteByExample(RunSetResultJobLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetResultJobLink record);

    int insertSelective(@Param("record") RunSetResultJobLink record, @Param("selective") RunSetResultJobLink.Column... selective);

    List<RunSetResultJobLink> selectByExample(RunSetResultJobLinkExample example);

    List<RunSetResultJobLink> selectByExampleSelective(@Param("example") RunSetResultJobLinkExample example, @Param("selective") RunSetResultJobLink.Column... selective);

    List<RunSetResultJobLink> selectByExampleWithRowbounds(RunSetResultJobLinkExample example, RowBounds rowBounds);

    RunSetResultJobLink selectByPrimaryKey(Long id);

    RunSetResultJobLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetResultJobLink.Column... selective);

    RunSetResultJobLink selectOneByExample(RunSetResultJobLinkExample example);

    RunSetResultJobLink selectOneByExampleSelective(@Param("example") RunSetResultJobLinkExample example, @Param("selective") RunSetResultJobLink.Column... selective);

    int updateByExample(@Param("record") RunSetResultJobLink record, @Param("example") RunSetResultJobLinkExample example);

    int updateByExampleSelective(@Param("record") RunSetResultJobLink record, @Param("example") RunSetResultJobLinkExample example,
            @Param("selective") RunSetResultJobLink.Column... selective);

    int updateByPrimaryKey(RunSetResultJobLink record);

    int updateByPrimaryKeySelective(@Param("record") RunSetResultJobLink record, @Param("selective") RunSetResultJobLink.Column... selective);
}