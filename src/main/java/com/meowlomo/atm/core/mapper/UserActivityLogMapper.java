package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.UserActivityLog;
import com.meowlomo.atm.core.model.UserActivityLogExample;

public interface UserActivityLogMapper {
    long countByExample(UserActivityLogExample example);

    int deleteByExample(UserActivityLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserActivityLog record);

    int insertSelective(@Param("record") UserActivityLog record, @Param("selective") UserActivityLog.Column... selective);

    List<UserActivityLog> selectByExample(UserActivityLogExample example);

    List<UserActivityLog> selectByExampleSelective(@Param("example") UserActivityLogExample example, @Param("selective") UserActivityLog.Column... selective);

    List<UserActivityLog> selectByExampleWithRowbounds(UserActivityLogExample example, RowBounds rowBounds);

    UserActivityLog selectByPrimaryKey(Long id);

    UserActivityLog selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserActivityLog.Column... selective);

    UserActivityLog selectOneByExample(UserActivityLogExample example);

    UserActivityLog selectOneByExampleSelective(@Param("example") UserActivityLogExample example, @Param("selective") UserActivityLog.Column... selective);

    int updateByExample(@Param("record") UserActivityLog record, @Param("example") UserActivityLogExample example);

    int updateByExampleSelective(@Param("record") UserActivityLog record, @Param("example") UserActivityLogExample example,
            @Param("selective") UserActivityLog.Column... selective);

    int updateByPrimaryKey(UserActivityLog record);

    int updateByPrimaryKeySelective(@Param("record") UserActivityLog record, @Param("selective") UserActivityLog.Column... selective);
}