package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.EmailNotificationTargetExample;

public interface EmailNotificationTargetReferenceService {

    long countByExample(EmailNotificationTargetExample example);

    List<EmailNotificationTarget> selectByExample(EmailNotificationTargetExample example);

    List<EmailNotificationTarget> selectByExampleWithRowbounds(EmailNotificationTargetExample example, RowBounds rowBounds);

    EmailNotificationTarget selectByPrimaryKey(Long id);
}
