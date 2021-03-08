package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.NotificationExample;

public interface NotificationReferenceService {

    long countByExample(NotificationExample example);

    List<Notification> selectByExample(NotificationExample example);

    List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds);

    Notification selectByPrimaryKey(Long id);
}
