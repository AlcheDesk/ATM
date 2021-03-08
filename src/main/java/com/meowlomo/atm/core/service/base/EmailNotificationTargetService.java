package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.EmailNotificationTargetExample;

public interface EmailNotificationTargetService {
    long countByExample(EmailNotificationTargetExample example);

    int deleteByExample(EmailNotificationTargetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmailNotificationTarget record);

    List<Long> insertRecords(List<EmailNotificationTarget> records);

    List<Long> insertRecordsSelective(List<EmailNotificationTarget> records);

    int insertSelective(EmailNotificationTarget record);

    List<EmailNotificationTarget> selectByExample(EmailNotificationTargetExample example);

    List<EmailNotificationTarget> selectByExampleWithRowbounds(EmailNotificationTargetExample example,
            RowBounds rowBounds);

    EmailNotificationTarget selectByPrimaryKey(Long id);

    EmailNotificationTarget selectOneByExample(EmailNotificationTargetExample example);

    int updateByExample(EmailNotificationTarget record, EmailNotificationTargetExample example);

    int updateByExampleSelective(EmailNotificationTarget record, EmailNotificationTargetExample example);

    int updateByPrimaryKey(EmailNotificationTarget record);

    int updateByPrimaryKeySelective(EmailNotificationTarget record);
}