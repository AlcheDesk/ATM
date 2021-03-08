package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Group;
import com.meowlomo.atm.core.model.GroupExample;

public interface GroupService {
    long countByExample(GroupExample example);

    int deleteByExample(GroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    List<Long> insertRecords(List<Group> records);

    List<Long> insertRecordsSelective(List<Group> records);

    int insertSelective(Group record);

    List<Group> selectByExample(GroupExample example);

    List<Group> selectByExampleWithRowbounds(GroupExample example, RowBounds rowBounds);

    Group selectByPrimaryKey(Long id);

    Group selectOneByExample(GroupExample example);

    int updateByExample(Group record, GroupExample example);

    int updateByExampleSelective(Group record, GroupExample example);

    int updateByPrimaryKey(Group record);

    int updateByPrimaryKeySelective(Group record);
}