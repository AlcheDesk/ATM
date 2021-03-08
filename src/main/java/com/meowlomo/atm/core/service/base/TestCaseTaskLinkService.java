package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseTaskLink;
import com.meowlomo.atm.core.model.TestCaseTaskLinkExample;

public interface TestCaseTaskLinkService {
    long countByExample(TestCaseTaskLinkExample example);

    int deleteByExample(TestCaseTaskLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseTaskLink record);

    List<Long> insertRecords(List<TestCaseTaskLink> records);

    List<Long> insertRecordsSelective(List<TestCaseTaskLink> records);

    int insertSelective(TestCaseTaskLink record);

    List<TestCaseTaskLink> selectByExample(TestCaseTaskLinkExample example);

    List<TestCaseTaskLink> selectByExampleWithRowbounds(TestCaseTaskLinkExample example, RowBounds rowBounds);

    TestCaseTaskLink selectByPrimaryKey(Long id);

    TestCaseTaskLink selectOneByExample(TestCaseTaskLinkExample example);

    int updateByExample(TestCaseTaskLink record, TestCaseTaskLinkExample example);

    int updateByExampleSelective(TestCaseTaskLink record, TestCaseTaskLinkExample example);

    int updateByPrimaryKey(TestCaseTaskLink record);

    int updateByPrimaryKeySelective(TestCaseTaskLink record);
}