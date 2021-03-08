package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseDriverTypeMap;
import com.meowlomo.atm.core.model.TestCaseDriverTypeMapExample;

public interface TestCaseDriverTypeMapService {
    long countByExample(TestCaseDriverTypeMapExample example);

    int deleteByExample(TestCaseDriverTypeMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseDriverTypeMap record);

    List<Long> insertRecords(List<TestCaseDriverTypeMap> records);

    List<Long> insertRecordsSelective(List<TestCaseDriverTypeMap> records);

    int insertSelective(TestCaseDriverTypeMap record);

    List<TestCaseDriverTypeMap> selectByExample(TestCaseDriverTypeMapExample example);

    List<TestCaseDriverTypeMap> selectByExampleWithRowbounds(TestCaseDriverTypeMapExample example, RowBounds rowBounds);

    TestCaseDriverTypeMap selectByPrimaryKey(Long id);

    TestCaseDriverTypeMap selectOneByExample(TestCaseDriverTypeMapExample example);

    int updateByExample(TestCaseDriverTypeMap record, TestCaseDriverTypeMapExample example);

    int updateByExampleSelective(TestCaseDriverTypeMap record, TestCaseDriverTypeMapExample example);

    int updateByPrimaryKey(TestCaseDriverTypeMap record);

    int updateByPrimaryKeySelective(TestCaseDriverTypeMap record);
}