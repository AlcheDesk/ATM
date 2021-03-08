package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.NotificationExample;

public interface NotificationReferenceMapper {

    long countByExample(NotificationExample example);

    List<Notification> selectByExample(NotificationExample example);

    List<Notification> selectByExampleSelective(@Param("example") NotificationExample example, @Param("selective") Notification.Column... selective);

    List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds);

    Notification selectByPrimaryKey(Long id);

    Notification selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Notification.Column... selective);

    Notification selectOneByExample(NotificationExample example);

    Notification selectOneByExampleSelective(@Param("example") NotificationExample example, @Param("selective") Notification.Column... selective);
}