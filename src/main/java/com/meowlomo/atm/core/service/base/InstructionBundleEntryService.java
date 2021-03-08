package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionBundleEntry;
import com.meowlomo.atm.core.model.InstructionBundleEntryExample;

public interface InstructionBundleEntryService {
    long countByExample(InstructionBundleEntryExample example);

    int deleteByExample(InstructionBundleEntryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionBundleEntry record);

    List<Long> insertRecords(List<InstructionBundleEntry> records);

    List<Long> insertRecordsSelective(List<InstructionBundleEntry> records);

    int insertSelective(InstructionBundleEntry record);

    int logicalDeleteByExample(InstructionBundleEntryExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<InstructionBundleEntry> selectByExample(InstructionBundleEntryExample example);

    List<InstructionBundleEntry> selectByExampleWithRowbounds(InstructionBundleEntryExample example,
            RowBounds rowBounds);

    InstructionBundleEntry selectByPrimaryKey(Long id);

    InstructionBundleEntry selectOneByExample(InstructionBundleEntryExample example);

    int updateByExample(InstructionBundleEntry record, InstructionBundleEntryExample example);

    int updateByExampleSelective(InstructionBundleEntry record, InstructionBundleEntryExample example);

    int updateByPrimaryKey(InstructionBundleEntry record);

    int updateByPrimaryKeySelective(InstructionBundleEntry record);
}