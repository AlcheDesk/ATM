package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;

public interface TestCaseOverwriteService {
    long countByExample(TestCaseOverwriteExample example);

    int deleteByExample(TestCaseOverwriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseOverwrite record);

    List<Long> insertRecords(List<TestCaseOverwrite> records);

    List<Long> insertRecordsSelective(List<TestCaseOverwrite> records);

    int insertSelective(TestCaseOverwrite record);

    int logicalDeleteByExample(TestCaseOverwriteExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<TestCaseOverwrite> selectByExample(TestCaseOverwriteExample example);

    List<TestCaseOverwrite> selectByExampleWithRowbounds(TestCaseOverwriteExample example, RowBounds rowBounds);

    TestCaseOverwrite selectByPrimaryKey(Long id);

    TestCaseOverwrite selectOneByExample(TestCaseOverwriteExample example);

    int updateByExample(TestCaseOverwrite record, TestCaseOverwriteExample example);

    int updateByExampleSelective(TestCaseOverwrite record, TestCaseOverwriteExample example);

    int updateByPrimaryKey(TestCaseOverwrite record);

    int updateByPrimaryKeySelective(TestCaseOverwrite record);
}