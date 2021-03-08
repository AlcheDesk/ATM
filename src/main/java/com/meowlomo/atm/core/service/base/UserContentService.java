package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.UserContent;
import com.meowlomo.atm.core.model.UserContentExample;

public interface UserContentService {
    long countByExample(UserContentExample example);

    int deleteByExample(UserContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserContent record);

    List<Long> insertRecords(List<UserContent> records);

    List<Long> insertRecordsSelective(List<UserContent> records);

    int insertSelective(UserContent record);

    List<UserContent> selectByExample(UserContentExample example);

    List<UserContent> selectByExampleWithRowbounds(UserContentExample example, RowBounds rowBounds);

    UserContent selectByPrimaryKey(Long id);

    UserContent selectOneByExample(UserContentExample example);

    int updateByExample(UserContent record, UserContentExample example);

    int updateByExampleSelective(UserContent record, UserContentExample example);

    int updateByPrimaryKey(UserContent record);

    int updateByPrimaryKeySelective(UserContent record);
}