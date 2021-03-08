package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLink;
import com.meowlomo.atm.core.model.NotificationEmailNotificationTargetLinkExample;

public interface NotificationEmailNotificationTargetLinkService {
    long countByExample(NotificationEmailNotificationTargetLinkExample example);

    int deleteByExample(NotificationEmailNotificationTargetLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NotificationEmailNotificationTargetLink record);

    List<Long> insertRecords(List<NotificationEmailNotificationTargetLink> records);

    List<Long> insertRecordsSelective(List<NotificationEmailNotificationTargetLink> records);

    int insertSelective(NotificationEmailNotificationTargetLink record);

    List<NotificationEmailNotificationTargetLink> selectByExample(
            NotificationEmailNotificationTargetLinkExample example);

    List<NotificationEmailNotificationTargetLink> selectByExampleWithRowbounds(
            NotificationEmailNotificationTargetLinkExample example, RowBounds rowBounds);

    NotificationEmailNotificationTargetLink selectByPrimaryKey(Long id);

    NotificationEmailNotificationTargetLink selectOneByExample(NotificationEmailNotificationTargetLinkExample example);

    int updateByExample(NotificationEmailNotificationTargetLink record,
            NotificationEmailNotificationTargetLinkExample example);

    int updateByExampleSelective(NotificationEmailNotificationTargetLink record,
            NotificationEmailNotificationTargetLinkExample example);

    int updateByPrimaryKey(NotificationEmailNotificationTargetLink record);

    int updateByPrimaryKeySelective(NotificationEmailNotificationTargetLink record);
}