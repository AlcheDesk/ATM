package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementPack;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;

public interface SystemRequirementPackService {
    long countByExample(SystemRequirementPackExample example);

    int deleteByExample(SystemRequirementPackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirementPack record);

    List<Long> insertRecords(List<SystemRequirementPack> records);

    List<Long> insertRecordsSelective(List<SystemRequirementPack> records);

    int insertSelective(SystemRequirementPack record);

    int logicalDeleteByExample(SystemRequirementPackExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<SystemRequirementPack> selectByExample(SystemRequirementPackExample example);

    List<SystemRequirementPack> selectByExampleWithRowbounds(SystemRequirementPackExample example, RowBounds rowBounds);

    SystemRequirementPack selectByPrimaryKey(Long id);

    SystemRequirementPack selectOneByExample(SystemRequirementPackExample example);

    int updateByExample(SystemRequirementPack record, SystemRequirementPackExample example);

    int updateByExampleSelective(SystemRequirementPack record, SystemRequirementPackExample example);

    int updateByPrimaryKey(SystemRequirementPack record);

    int updateByPrimaryKeySelective(SystemRequirementPack record);
}