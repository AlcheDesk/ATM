package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;

public interface TestCaseService {
    long countByExample(TestCaseExample example);

    int deleteByExample(TestCaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCase record);

    List<Long> insertRecords(List<TestCase> records);

    List<Long> insertRecordsSelective(List<TestCase> records);

    int insertSelective(TestCase record);

    int logicalDeleteByExample(TestCaseExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<TestCase> selectByExample(TestCaseExample example);

    List<TestCase> selectByExampleWithRowbounds(TestCaseExample example, RowBounds rowBounds);

    TestCase selectByPrimaryKey(Long id);

    TestCase selectOneByExample(TestCaseExample example);

    int updateByExample(TestCase record, TestCaseExample example);

    int updateByExampleSelective(TestCase record, TestCaseExample example);

    int updateByPrimaryKey(TestCase record);

    int updateByPrimaryKeySelective(TestCase record);
}