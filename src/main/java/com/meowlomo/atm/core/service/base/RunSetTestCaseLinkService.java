package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.model.TestCase;


public interface RunSetTestCaseLinkService {
    long countByExample(RunSetTestCaseLinkExample example);

    int deleteByExample(RunSetTestCaseLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetTestCaseLink record);

    List<Long> insertRecords(List<RunSetTestCaseLink> records);

    List<Long> insertRecordsSelective(List<RunSetTestCaseLink> records);

    int insertSelective(RunSetTestCaseLink record);

    List<RunSet> listRunSetByTestCasePrimaryKey(Long testCaseId);

    List<TestCase> listTestCaseByRunSetPrimaryKey(Long runSetId);

    List<RunSetTestCaseLink> selectByExample(RunSetTestCaseLinkExample example);

    List<RunSetTestCaseLink> selectByExampleWithRowbounds(RunSetTestCaseLinkExample example, RowBounds rowBounds);

    RunSetTestCaseLink selectByPrimaryKey(Long id);

    RunSetTestCaseLink selectOneByExample(RunSetTestCaseLinkExample example);

    int updateByExample(RunSetTestCaseLink record, RunSetTestCaseLinkExample example);

    int updateByExampleSelective(RunSetTestCaseLink record, RunSetTestCaseLinkExample example);

    int updateByPrimaryKey(RunSetTestCaseLink record);

    int updateByPrimaryKeySelective(RunSetTestCaseLink record);
}