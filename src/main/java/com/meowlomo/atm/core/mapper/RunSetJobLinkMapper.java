package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetJobLink;
import com.meowlomo.atm.core.model.RunSetJobLinkExample;

public interface RunSetJobLinkMapper {
    long countByExample(RunSetJobLinkExample example);

    int deleteByExample(RunSetJobLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetJobLink record);

    int insertSelective(@Param("record") RunSetJobLink record, @Param("selective") RunSetJobLink.Column... selective);

    List<RunSetJobLink> selectByExample(RunSetJobLinkExample example);

    List<RunSetJobLink> selectByExampleSelective(@Param("example") RunSetJobLinkExample example, @Param("selective") RunSetJobLink.Column... selective);

    List<RunSetJobLink> selectByExampleWithRowbounds(RunSetJobLinkExample example, RowBounds rowBounds);

    RunSetJobLink selectByPrimaryKey(Long id);

    RunSetJobLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetJobLink.Column... selective);

    RunSetJobLink selectOneByExample(RunSetJobLinkExample example);

    RunSetJobLink selectOneByExampleSelective(@Param("example") RunSetJobLinkExample example, @Param("selective") RunSetJobLink.Column... selective);

    int updateByExample(@Param("record") RunSetJobLink record, @Param("example") RunSetJobLinkExample example);

    int updateByExampleSelective(@Param("record") RunSetJobLink record, @Param("example") RunSetJobLinkExample example, @Param("selective") RunSetJobLink.Column... selective);

    int updateByPrimaryKey(RunSetJobLink record);

    int updateByPrimaryKeySelective(@Param("record") RunSetJobLink record, @Param("selective") RunSetJobLink.Column... selective);
}