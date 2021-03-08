package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverTypeInstructionTypeLink;
import com.meowlomo.atm.core.model.DriverTypeInstructionTypeLinkExample;

public interface DriverTypeInstructionTypeLinkService {
    long countByExample(DriverTypeInstructionTypeLinkExample example);

    int deleteByExample(DriverTypeInstructionTypeLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverTypeInstructionTypeLink record);

    List<Long> insertRecords(List<DriverTypeInstructionTypeLink> records);

    List<Long> insertRecordsSelective(List<DriverTypeInstructionTypeLink> records);

    int insertSelective(DriverTypeInstructionTypeLink record);

    List<DriverTypeInstructionTypeLink> selectByExample(DriverTypeInstructionTypeLinkExample example);

    List<DriverTypeInstructionTypeLink> selectByExampleWithRowbounds(DriverTypeInstructionTypeLinkExample example,
            RowBounds rowBounds);

    DriverTypeInstructionTypeLink selectByPrimaryKey(Long id);

    DriverTypeInstructionTypeLink selectOneByExample(DriverTypeInstructionTypeLinkExample example);

    int updateByExample(DriverTypeInstructionTypeLink record, DriverTypeInstructionTypeLinkExample example);

    int updateByExampleSelective(DriverTypeInstructionTypeLink record, DriverTypeInstructionTypeLinkExample example);

    int updateByPrimaryKey(DriverTypeInstructionTypeLink record);

    int updateByPrimaryKeySelective(DriverTypeInstructionTypeLink record);
}