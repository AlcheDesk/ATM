package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseExecutionInfo;
import com.meowlomo.atm.core.model.TestCaseExecutionInfoExample;

public interface TestCaseExecutionInfoService {
    long countByExample(TestCaseExecutionInfoExample example);

    List<TestCaseExecutionInfo> selectByExample(TestCaseExecutionInfoExample example);

    List<TestCaseExecutionInfo> selectByExampleWithRowbounds(TestCaseExecutionInfoExample example, RowBounds rowBounds);

    TestCaseExecutionInfo selectByPrimaryKey(Long testCaseId);

    TestCaseExecutionInfo selectOneByExample(TestCaseExecutionInfoExample example);

}