package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.NotificationExample;

public interface NotificationMapper {
    long countByExample(NotificationExample example);

    int deleteByExample(NotificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    int insertSelective(@Param("record") Notification record, @Param("selective") Notification.Column... selective);

    int logicalDeleteByExample(@Param("example") NotificationExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Notification> selectByExample(NotificationExample example);

    List<Notification> selectByExampleSelective(@Param("example") NotificationExample example, @Param("selective") Notification.Column... selective);

    List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds);

    Notification selectByPrimaryKey(Long id);

    Notification selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Notification.Column... selective);

    Notification selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Notification selectOneByExample(NotificationExample example);

    Notification selectOneByExampleSelective(@Param("example") NotificationExample example, @Param("selective") Notification.Column... selective);

    int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example, @Param("selective") Notification.Column... selective);

    int updateByPrimaryKey(Notification record);

    int updateByPrimaryKeySelective(@Param("record") Notification record, @Param("selective") Notification.Column... selective);
}