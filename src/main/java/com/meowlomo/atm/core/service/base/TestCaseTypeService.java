package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseType;
import com.meowlomo.atm.core.model.TestCaseTypeExample;

public interface TestCaseTypeService {
    long countByExample(TestCaseTypeExample example);

    int deleteByExample(TestCaseTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseType record);

    List<Long> insertRecords(List<TestCaseType> records);

    List<Long> insertRecordsSelective(List<TestCaseType> records);

    int insertSelective(TestCaseType record);

    List<TestCaseType> selectByExample(TestCaseTypeExample example);

    List<TestCaseType> selectByExampleWithRowbounds(TestCaseTypeExample example, RowBounds rowBounds);

    TestCaseType selectByPrimaryKey(Long id);

    TestCaseType selectOneByExample(TestCaseTypeExample example);

    int updateByExample(TestCaseType record, TestCaseTypeExample example);

    int updateByExampleSelective(TestCaseType record, TestCaseTypeExample example);

    int updateByPrimaryKey(TestCaseType record);

    int updateByPrimaryKeySelective(TestCaseType record);
}