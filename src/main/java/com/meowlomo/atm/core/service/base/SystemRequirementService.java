package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirement;
import com.meowlomo.atm.core.model.SystemRequirementExample;

public interface SystemRequirementService {
    long countByExample(SystemRequirementExample example);

    int deleteByExample(SystemRequirementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirement record);

    List<Long> insertRecords(List<SystemRequirement> records);

    List<Long> insertRecordsSelective(List<SystemRequirement> records);

    int insertSelective(SystemRequirement record);

    List<SystemRequirement> selectByExample(SystemRequirementExample example);

    List<SystemRequirement> selectByExampleWithRowbounds(SystemRequirementExample example, RowBounds rowBounds);

    SystemRequirement selectByPrimaryKey(Long id);

    SystemRequirement selectOneByExample(SystemRequirementExample example);

    int updateByExample(SystemRequirement record, SystemRequirementExample example);

    int updateByExampleSelective(SystemRequirement record, SystemRequirementExample example);

    int updateByPrimaryKey(SystemRequirement record);

    int updateByPrimaryKeySelective(SystemRequirement record);
}