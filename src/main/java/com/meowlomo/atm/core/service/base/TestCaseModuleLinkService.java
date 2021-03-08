package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseModuleLink;
import com.meowlomo.atm.core.model.TestCaseModuleLinkExample;

public interface TestCaseModuleLinkService {
    long countByExample(TestCaseModuleLinkExample example);

    int deleteByExample(TestCaseModuleLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseModuleLink record);

    List<Long> insertRecords(List<TestCaseModuleLink> records);

    List<Long> insertRecordsSelective(List<TestCaseModuleLink> records);

    int insertSelective(TestCaseModuleLink record);

    List<Module> listModuleByTestCasePrimaryKey(Long testCaseId);

    List<TestCase> listTestCaseByModulePrimaryKey(Long moduleId);

    List<TestCaseModuleLink> selectByExample(TestCaseModuleLinkExample example);

    List<TestCaseModuleLink> selectByExampleWithRowbounds(TestCaseModuleLinkExample example, RowBounds rowBounds);

    TestCaseModuleLink selectByPrimaryKey(Long id);

    TestCaseModuleLink selectOneByExample(TestCaseModuleLinkExample example);

    int updateByExample(TestCaseModuleLink record, TestCaseModuleLinkExample example);

    int updateByExampleSelective(TestCaseModuleLink record, TestCaseModuleLinkExample example);

    int updateByPrimaryKey(TestCaseModuleLink record);

    int updateByPrimaryKeySelective(TestCaseModuleLink record);
}