package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseExecutionInfo;
import com.meowlomo.atm.core.model.TestCaseExecutionInfoExample;

public interface TestCaseExecutionInfoMapper {
    long countByExample(TestCaseExecutionInfoExample example);

    List<TestCaseExecutionInfo> selectByExample(TestCaseExecutionInfoExample example);

    List<TestCaseExecutionInfo> selectByExampleSelective(@Param("example") TestCaseExecutionInfoExample example, @Param("selective") TestCaseExecutionInfo.Column... selective);

    List<TestCaseExecutionInfo> selectByExampleWithRowbounds(TestCaseExecutionInfoExample example, RowBounds rowBounds);

    TestCaseExecutionInfo selectByPrimaryKey(Long testCaseId);

    TestCaseExecutionInfo selectByPrimaryKeySelective(@Param("testCaseId") Long testCaseId, @Param("selective") TestCaseExecutionInfo.Column... selective);

    TestCaseExecutionInfo selectOneByExample(TestCaseExecutionInfoExample example);

    TestCaseExecutionInfo selectOneByExampleSelective(@Param("example") TestCaseExecutionInfoExample example, @Param("selective") TestCaseExecutionInfo.Column... selective);

}