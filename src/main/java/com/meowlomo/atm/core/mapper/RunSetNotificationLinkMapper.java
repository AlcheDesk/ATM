package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetNotificationLink;
import com.meowlomo.atm.core.model.RunSetNotificationLinkExample;

public interface RunSetNotificationLinkMapper {
    long countByExample(RunSetNotificationLinkExample example);

    int deleteByExample(RunSetNotificationLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetNotificationLink record);

    int insertSelective(@Param("record") RunSetNotificationLink record, @Param("selective") RunSetNotificationLink.Column... selective);

    List<RunSetNotificationLink> selectByExample(RunSetNotificationLinkExample example);

    List<RunSetNotificationLink> selectByExampleSelective(@Param("example") RunSetNotificationLinkExample example, @Param("selective") RunSetNotificationLink.Column... selective);

    List<RunSetNotificationLink> selectByExampleWithRowbounds(RunSetNotificationLinkExample example, RowBounds rowBounds);

    RunSetNotificationLink selectByPrimaryKey(Long id);

    RunSetNotificationLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetNotificationLink.Column... selective);

    RunSetNotificationLink selectOneByExample(RunSetNotificationLinkExample example);

    RunSetNotificationLink selectOneByExampleSelective(@Param("example") RunSetNotificationLinkExample example, @Param("selective") RunSetNotificationLink.Column... selective);

    int updateByExample(@Param("record") RunSetNotificationLink record, @Param("example") RunSetNotificationLinkExample example);

    int updateByExampleSelective(@Param("record") RunSetNotificationLink record, @Param("example") RunSetNotificationLinkExample example,
            @Param("selective") RunSetNotificationLink.Column... selective);

    int updateByPrimaryKey(RunSetNotificationLink record);

    int updateByPrimaryKeySelective(@Param("record") RunSetNotificationLink record, @Param("selective") RunSetNotificationLink.Column... selective);
}