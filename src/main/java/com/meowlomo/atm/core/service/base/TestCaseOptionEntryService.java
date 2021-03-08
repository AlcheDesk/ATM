package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOptionEntry;
import com.meowlomo.atm.core.model.TestCaseOptionEntryExample;

public interface TestCaseOptionEntryService {
    long countByExample(TestCaseOptionEntryExample example);

    int deleteByExample(TestCaseOptionEntryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseOptionEntry record);

    List<Long> insertRecords(List<TestCaseOptionEntry> records);

    List<Long> insertRecordsSelective(List<TestCaseOptionEntry> records);

    int insertSelective(TestCaseOptionEntry record);

    List<TestCaseOptionEntry> selectByExample(TestCaseOptionEntryExample example);

    List<TestCaseOptionEntry> selectByExampleWithRowbounds(TestCaseOptionEntryExample example, RowBounds rowBounds);

    TestCaseOptionEntry selectByPrimaryKey(Long id);

    TestCaseOptionEntry selectOneByExample(TestCaseOptionEntryExample example);

    int updateByExample(TestCaseOptionEntry record, TestCaseOptionEntryExample example);

    int updateByExampleSelective(TestCaseOptionEntry record, TestCaseOptionEntryExample example);

    int updateByPrimaryKey(TestCaseOptionEntry record);

    int updateByPrimaryKeySelective(TestCaseOptionEntry record);
}