package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.UserActivityLog;
import com.meowlomo.atm.core.model.UserActivityLogExample;

public interface UserActivityLogService {
    long countByExample(UserActivityLogExample example);

    int deleteByExample(UserActivityLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserActivityLog record);

    List<Long> insertRecords(List<UserActivityLog> records);

    List<Long> insertRecordsSelective(List<UserActivityLog> records);

    int insertSelective(UserActivityLog record);

    List<UserActivityLog> selectByExample(UserActivityLogExample example);

    List<UserActivityLog> selectByExampleWithRowbounds(UserActivityLogExample example, RowBounds rowBounds);

    UserActivityLog selectByPrimaryKey(Long id);

    UserActivityLog selectOneByExample(UserActivityLogExample example);

    int updateByExample(UserActivityLog record, UserActivityLogExample example);

    int updateByExampleSelective(UserActivityLog record, UserActivityLogExample example);

    int updateByPrimaryKey(UserActivityLog record);

    int updateByPrimaryKeySelective(UserActivityLog record);
}