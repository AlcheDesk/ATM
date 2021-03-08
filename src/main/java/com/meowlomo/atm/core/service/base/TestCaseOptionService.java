package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOption;
import com.meowlomo.atm.core.model.TestCaseOptionExample;

public interface TestCaseOptionService {
    long countByExample(TestCaseOptionExample example);

    int deleteByExample(TestCaseOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseOption record);

    List<Long> insertRecords(List<TestCaseOption> records);

    List<Long> insertRecordsSelective(List<TestCaseOption> records);

    int insertSelective(TestCaseOption record);

    List<TestCaseOption> selectByExample(TestCaseOptionExample example);

    List<TestCaseOption> selectByExampleWithRowbounds(TestCaseOptionExample example, RowBounds rowBounds);

    TestCaseOption selectByPrimaryKey(Long id);

    TestCaseOption selectOneByExample(TestCaseOptionExample example);

    int updateByExample(TestCaseOption record, TestCaseOptionExample example);

    int updateByExampleSelective(TestCaseOption record, TestCaseOptionExample example);

    int updateByPrimaryKey(TestCaseOption record);

    int updateByPrimaryKeySelective(TestCaseOption record);
}