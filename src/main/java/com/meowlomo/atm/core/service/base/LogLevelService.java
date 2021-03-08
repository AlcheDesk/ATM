package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.LogLevel;
import com.meowlomo.atm.core.model.LogLevelExample;

public interface LogLevelService {
    long countByExample(LogLevelExample example);

    int deleteByExample(LogLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogLevel record);

    List<Long> insertRecords(List<LogLevel> records);

    List<Long> insertRecordsSelective(List<LogLevel> records);

    int insertSelective(LogLevel record);

    List<LogLevel> selectByExample(LogLevelExample example);

    List<LogLevel> selectByExampleWithRowbounds(LogLevelExample example, RowBounds rowBounds);

    LogLevel selectByPrimaryKey(Long id);

    LogLevel selectOneByExample(LogLevelExample example);

    int updateByExample(LogLevel record, LogLevelExample example);

    int updateByExampleSelective(LogLevel record, LogLevelExample example);

    int updateByPrimaryKey(LogLevel record);

    int updateByPrimaryKeySelective(LogLevel record);
}