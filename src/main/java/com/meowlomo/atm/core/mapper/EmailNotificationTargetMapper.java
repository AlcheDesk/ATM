package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.EmailNotificationTargetExample;

public interface EmailNotificationTargetMapper {
    long countByExample(EmailNotificationTargetExample example);

    int deleteByExample(EmailNotificationTargetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmailNotificationTarget record);

    int insertSelective(@Param("record") EmailNotificationTarget record, @Param("selective") EmailNotificationTarget.Column... selective);

    List<EmailNotificationTarget> selectByExample(EmailNotificationTargetExample example);

    List<EmailNotificationTarget> selectByExampleSelective(@Param("example") EmailNotificationTargetExample example,
            @Param("selective") EmailNotificationTarget.Column... selective);

    List<EmailNotificationTarget> selectByExampleWithRowbounds(EmailNotificationTargetExample example, RowBounds rowBounds);

    EmailNotificationTarget selectByPrimaryKey(Long id);

    EmailNotificationTarget selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") EmailNotificationTarget.Column... selective);

    EmailNotificationTarget selectOneByExample(EmailNotificationTargetExample example);

    EmailNotificationTarget selectOneByExampleSelective(@Param("example") EmailNotificationTargetExample example, @Param("selective") EmailNotificationTarget.Column... selective);

    int updateByExample(@Param("record") EmailNotificationTarget record, @Param("example") EmailNotificationTargetExample example);

    int updateByExampleSelective(@Param("record") EmailNotificationTarget record, @Param("example") EmailNotificationTargetExample example,
            @Param("selective") EmailNotificationTarget.Column... selective);

    int updateByPrimaryKey(EmailNotificationTarget record);

    int updateByPrimaryKeySelective(@Param("record") EmailNotificationTarget record, @Param("selective") EmailNotificationTarget.Column... selective);
}