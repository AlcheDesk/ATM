package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionBundle;
import com.meowlomo.atm.core.model.InstructionBundleExample;

public interface InstructionBundleService {
    long countByExample(InstructionBundleExample example);

    int deleteByExample(InstructionBundleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionBundle record);

    List<Long> insertRecords(List<InstructionBundle> records);

    List<Long> insertRecordsSelective(List<InstructionBundle> records);

    int insertSelective(InstructionBundle record);

    int logicalDeleteByExample(InstructionBundleExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<InstructionBundle> selectByExample(InstructionBundleExample example);

    List<InstructionBundle> selectByExampleWithRowbounds(InstructionBundleExample example, RowBounds rowBounds);

    InstructionBundle selectByPrimaryKey(Long id);

    InstructionBundle selectOneByExample(InstructionBundleExample example);

    int updateByExample(InstructionBundle record, InstructionBundleExample example);

    int updateByExampleSelective(InstructionBundle record, InstructionBundleExample example);

    int updateByPrimaryKey(InstructionBundle record);

    int updateByPrimaryKeySelective(InstructionBundle record);
}