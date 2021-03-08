package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.LogLevel;
import com.meowlomo.atm.core.model.LogLevelExample;

public interface LogLevelMapper {
    long countByExample(LogLevelExample example);

    int deleteByExample(LogLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogLevel record);

    int insertSelective(@Param("record") LogLevel record, @Param("selective") LogLevel.Column... selective);

    List<LogLevel> selectByExample(LogLevelExample example);

    List<LogLevel> selectByExampleSelective(@Param("example") LogLevelExample example, @Param("selective") LogLevel.Column... selective);

    List<LogLevel> selectByExampleWithRowbounds(LogLevelExample example, RowBounds rowBounds);

    LogLevel selectByPrimaryKey(Long id);

    LogLevel selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") LogLevel.Column... selective);

    LogLevel selectOneByExample(LogLevelExample example);

    LogLevel selectOneByExampleSelective(@Param("example") LogLevelExample example, @Param("selective") LogLevel.Column... selective);

    int updateByExample(@Param("record") LogLevel record, @Param("example") LogLevelExample example);

    int updateByExampleSelective(@Param("record") LogLevel record, @Param("example") LogLevelExample example, @Param("selective") LogLevel.Column... selective);

    int updateByPrimaryKey(LogLevel record);

    int updateByPrimaryKeySelective(@Param("record") LogLevel record, @Param("selective") LogLevel.Column... selective);
}