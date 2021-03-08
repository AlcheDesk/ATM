package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.ApplicationExample;

public interface ApplicationService {
    long countByExample(ApplicationExample example);

    int deleteByExample(ApplicationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Application record);

    List<Long> insertRecords(List<Application> records);

    List<Long> insertRecordsSelective(List<Application> records);

    int insertSelective(Application record);

    int logicalDeleteByExample(ApplicationExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Application> selectByExample(ApplicationExample example);

    List<Application> selectByExampleWithRowbounds(ApplicationExample example, RowBounds rowBounds);

    Application selectByPrimaryKey(Long id);

    Application selectOneByExample(ApplicationExample example);

    int updateByExample(Application record, ApplicationExample example);

    int updateByExampleSelective(Application record, ApplicationExample example);

    int updateByPrimaryKey(Application record);

    int updateByPrimaryKeySelective(Application record);
}