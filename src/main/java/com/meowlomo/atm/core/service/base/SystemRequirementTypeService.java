package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementType;
import com.meowlomo.atm.core.model.SystemRequirementTypeExample;

public interface SystemRequirementTypeService {
    long countByExample(SystemRequirementTypeExample example);

    int deleteByExample(SystemRequirementTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirementType record);

    List<Long> insertRecords(List<SystemRequirementType> records);

    List<Long> insertRecordsSelective(List<SystemRequirementType> records);

    int insertSelective(SystemRequirementType record);

    List<SystemRequirementType> selectByExample(SystemRequirementTypeExample example);

    List<SystemRequirementType> selectByExampleWithRowbounds(SystemRequirementTypeExample example, RowBounds rowBounds);

    SystemRequirementType selectByPrimaryKey(Long id);

    SystemRequirementType selectOneByExample(SystemRequirementTypeExample example);

    int updateByExample(SystemRequirementType record, SystemRequirementTypeExample example);

    int updateByExampleSelective(SystemRequirementType record, SystemRequirementTypeExample example);

    int updateByPrimaryKey(SystemRequirementType record);

    int updateByPrimaryKeySelective(SystemRequirementType record);
}