package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetNotificationLink;
import com.meowlomo.atm.core.model.RunSetNotificationLinkExample;

public interface RunSetNotificationLinkService {
    long countByExample(RunSetNotificationLinkExample example);

    int deleteByExample(RunSetNotificationLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetNotificationLink record);

    List<Long> insertRecords(List<RunSetNotificationLink> records);

    List<Long> insertRecordsSelective(List<RunSetNotificationLink> records);

    int insertSelective(RunSetNotificationLink record);

    List<RunSetNotificationLink> selectByExample(RunSetNotificationLinkExample example);

    List<RunSetNotificationLink> selectByExampleWithRowbounds(RunSetNotificationLinkExample example,
            RowBounds rowBounds);

    RunSetNotificationLink selectByPrimaryKey(Long id);

    RunSetNotificationLink selectOneByExample(RunSetNotificationLinkExample example);

    int updateByExample(RunSetNotificationLink record, RunSetNotificationLinkExample example);

    int updateByExampleSelective(RunSetNotificationLink record, RunSetNotificationLinkExample example);

    int updateByPrimaryKey(RunSetNotificationLink record);

    int updateByPrimaryKeySelective(RunSetNotificationLink record);
}