package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseInstructionTypeMap;
import com.meowlomo.atm.core.model.TestCaseInstructionTypeMapExample;

public interface TestCaseInstructionTypeMapService {
    long countByExample(TestCaseInstructionTypeMapExample example);

    int deleteByExample(TestCaseInstructionTypeMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseInstructionTypeMap record);

    List<Long> insertRecords(List<TestCaseInstructionTypeMap> records);

    List<Long> insertRecordsSelective(List<TestCaseInstructionTypeMap> records);

    int insertSelective(TestCaseInstructionTypeMap record);

    List<TestCaseInstructionTypeMap> selectByExample(TestCaseInstructionTypeMapExample example);

    List<TestCaseInstructionTypeMap> selectByExampleWithRowbounds(TestCaseInstructionTypeMapExample example,
            RowBounds rowBounds);

    TestCaseInstructionTypeMap selectByPrimaryKey(Long id);

    TestCaseInstructionTypeMap selectOneByExample(TestCaseInstructionTypeMapExample example);

    int updateByExample(TestCaseInstructionTypeMap record, TestCaseInstructionTypeMapExample example);

    int updateByExampleSelective(TestCaseInstructionTypeMap record, TestCaseInstructionTypeMapExample example);

    int updateByPrimaryKey(TestCaseInstructionTypeMap record);

    int updateByPrimaryKeySelective(TestCaseInstructionTypeMap record);
}