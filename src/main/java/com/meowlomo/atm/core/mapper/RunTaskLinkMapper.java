package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunTaskLink;
import com.meowlomo.atm.core.model.RunTaskLinkExample;

public interface RunTaskLinkMapper {
    long countByExample(RunTaskLinkExample example);

    int deleteByExample(RunTaskLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunTaskLink record);

    int insertSelective(@Param("record") RunTaskLink record, @Param("selective") RunTaskLink.Column... selective);

    List<RunTaskLink> selectByExample(RunTaskLinkExample example);

    List<RunTaskLink> selectByExampleSelective(@Param("example") RunTaskLinkExample example, @Param("selective") RunTaskLink.Column... selective);

    List<RunTaskLink> selectByExampleWithRowbounds(RunTaskLinkExample example, RowBounds rowBounds);

    RunTaskLink selectByPrimaryKey(Long id);

    RunTaskLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunTaskLink.Column... selective);

    RunTaskLink selectOneByExample(RunTaskLinkExample example);

    RunTaskLink selectOneByExampleSelective(@Param("example") RunTaskLinkExample example, @Param("selective") RunTaskLink.Column... selective);

    int updateByExample(@Param("record") RunTaskLink record, @Param("example") RunTaskLinkExample example);

    int updateByExampleSelective(@Param("record") RunTaskLink record, @Param("example") RunTaskLinkExample example, @Param("selective") RunTaskLink.Column... selective);

    int updateByPrimaryKey(RunTaskLink record);

    int updateByPrimaryKeySelective(@Param("record") RunTaskLink record, @Param("selective") RunTaskLink.Column... selective);
}