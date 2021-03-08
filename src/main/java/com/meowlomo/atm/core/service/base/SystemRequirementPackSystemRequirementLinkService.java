package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLink;
import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLinkExample;

public interface SystemRequirementPackSystemRequirementLinkService {
    long countByExample(SystemRequirementPackSystemRequirementLinkExample example);

    int deleteByExample(SystemRequirementPackSystemRequirementLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirementPackSystemRequirementLink record);

    List<Long> insertRecords(List<SystemRequirementPackSystemRequirementLink> records);

    List<Long> insertRecordsSelective(List<SystemRequirementPackSystemRequirementLink> records);

    int insertSelective(SystemRequirementPackSystemRequirementLink record);

    List<SystemRequirementPackSystemRequirementLink> selectByExample(
            SystemRequirementPackSystemRequirementLinkExample example);

    List<SystemRequirementPackSystemRequirementLink> selectByExampleWithRowbounds(
            SystemRequirementPackSystemRequirementLinkExample example, RowBounds rowBounds);

    SystemRequirementPackSystemRequirementLink selectByPrimaryKey(Long id);

    SystemRequirementPackSystemRequirementLink selectOneByExample(
            SystemRequirementPackSystemRequirementLinkExample example);

    int updateByExample(SystemRequirementPackSystemRequirementLink record,
            SystemRequirementPackSystemRequirementLinkExample example);

    int updateByExampleSelective(SystemRequirementPackSystemRequirementLink record,
            SystemRequirementPackSystemRequirementLinkExample example);

    int updateByPrimaryKey(SystemRequirementPackSystemRequirementLink record);

    int updateByPrimaryKeySelective(SystemRequirementPackSystemRequirementLink record);
}