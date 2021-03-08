package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.NotificationExample;

public interface NotificationService {
    long countByExample(NotificationExample example);

    int deleteByExample(NotificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    List<Long> insertRecords(List<Notification> records);

    List<Long> insertRecordsSelective(List<Notification> records);

    int insertSelective(Notification record);

    int logicalDeleteByExample(NotificationExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Notification> selectByExample(NotificationExample example);

    List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds);

    Notification selectByPrimaryKey(Long id);

    Notification selectOneByExample(NotificationExample example);

    int updateByExample(Notification record, NotificationExample example);

    int updateByExampleSelective(Notification record, NotificationExample example);

    int updateByPrimaryKey(Notification record);

    int updateByPrimaryKeySelective(Notification record);
}