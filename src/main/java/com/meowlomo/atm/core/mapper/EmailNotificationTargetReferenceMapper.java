package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.EmailNotificationTargetExample;

public interface EmailNotificationTargetReferenceMapper {

    long countByExample(EmailNotificationTargetExample example);

    List<EmailNotificationTarget> selectByExample(EmailNotificationTargetExample example);

    List<EmailNotificationTarget> selectByExampleSelective(@Param("example") EmailNotificationTargetExample example,
            @Param("selective") EmailNotificationTarget.Column... selective);

    List<EmailNotificationTarget> selectByExampleWithRowbounds(EmailNotificationTargetExample example, RowBounds rowBounds);

    EmailNotificationTarget selectByPrimaryKey(Long id);

    EmailNotificationTarget selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") EmailNotificationTarget.Column... selective);

    EmailNotificationTarget selectOneByExample(EmailNotificationTargetExample example);

    EmailNotificationTarget selectOneByExampleSelective(@Param("example") EmailNotificationTargetExample example, @Param("selective") EmailNotificationTarget.Column... selective);

}