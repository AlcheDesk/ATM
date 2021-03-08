package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLink;
import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLinkExample;

public interface NotificationEmailNotificationTargetLinkMapper {
    long countByExample(NotificationEmailNotificationTargetLinkExample example);

    int deleteByExample(NotificationEmailNotificationTargetLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NotificationEmailNotificationTargetLink record);

    int insertSelective(@Param("record") NotificationEmailNotificationTargetLink record, @Param("selective") NotificationEmailNotificationTargetLink.Column... selective);

    List<NotificationEmailNotificationTargetLink> selectByExample(NotificationEmailNotificationTargetLinkExample example);

    List<NotificationEmailNotificationTargetLink> selectByExampleSelective(@Param("example") NotificationEmailNotificationTargetLinkExample example,
            @Param("selective") NotificationEmailNotificationTargetLink.Column... selective);

    List<NotificationEmailNotificationTargetLink> selectByExampleWithRowbounds(NotificationEmailNotificationTargetLinkExample example, RowBounds rowBounds);

    NotificationEmailNotificationTargetLink selectByPrimaryKey(Long id);

    NotificationEmailNotificationTargetLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") NotificationEmailNotificationTargetLink.Column... selective);

    NotificationEmailNotificationTargetLink selectOneByExample(NotificationEmailNotificationTargetLinkExample example);

    NotificationEmailNotificationTargetLink selectOneByExampleSelective(@Param("example") NotificationEmailNotificationTargetLinkExample example,
            @Param("selective") NotificationEmailNotificationTargetLink.Column... selective);

    int updateByExample(@Param("record") NotificationEmailNotificationTargetLink record, @Param("example") NotificationEmailNotificationTargetLinkExample example);

    int updateByExampleSelective(@Param("record") NotificationEmailNotificationTargetLink record, @Param("example") NotificationEmailNotificationTargetLinkExample example,
            @Param("selective") NotificationEmailNotificationTargetLink.Column... selective);

    int updateByPrimaryKey(NotificationEmailNotificationTargetLink record);

    int updateByPrimaryKeySelective(@Param("record") NotificationEmailNotificationTargetLink record,
            @Param("selective") NotificationEmailNotificationTargetLink.Column... selective);
}