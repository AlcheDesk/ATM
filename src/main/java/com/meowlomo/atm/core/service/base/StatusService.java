package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Status;
import com.meowlomo.atm.core.model.StatusExample;

public interface StatusService {
    long countByExample(StatusExample example);

    int deleteByExample(StatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Status record);

    List<Long> insertRecords(List<Status> records);

    List<Long> insertRecordsSelective(List<Status> records);

    int insertSelective(Status record);

    List<Status> selectByExample(StatusExample example);

    List<Status> selectByExampleWithRowbounds(StatusExample example, RowBounds rowBounds);

    Status selectByPrimaryKey(Long id);

    Status selectOneByExample(StatusExample example);

    int updateByExample(Status record, StatusExample example);

    int updateByExampleSelective(Status record, StatusExample example);

    int updateByPrimaryKey(Status record);

    int updateByPrimaryKeySelective(Status record);
}