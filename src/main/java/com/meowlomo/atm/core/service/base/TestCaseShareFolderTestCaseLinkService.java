package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLinkExample;

public interface TestCaseShareFolderTestCaseLinkService {
    long countByExample(TestCaseShareFolderTestCaseLinkExample example);

    int deleteByExample(TestCaseShareFolderTestCaseLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseShareFolderTestCaseLink record);

    List<Long> insertRecords(List<TestCaseShareFolderTestCaseLink> records);

    List<Long> insertRecordsSelective(List<TestCaseShareFolderTestCaseLink> records);

    int insertSelective(TestCaseShareFolderTestCaseLink record);

    List<TestCase> listTestCaseByTestCaseShareFolderPrimaryKey(Long testCaseShareFolderId);

    List<TestCaseShareFolder> listTestCaseShareFolderByTestCasePrimaryKey(Long testCaseId);

    List<TestCaseShareFolderTestCaseLink> selectByExample(TestCaseShareFolderTestCaseLinkExample example);

    List<TestCaseShareFolderTestCaseLink> selectByExampleWithRowbounds(TestCaseShareFolderTestCaseLinkExample example,
            RowBounds rowBounds);

    TestCaseShareFolderTestCaseLink selectByPrimaryKey(Long id);

    TestCaseShareFolderTestCaseLink selectOneByExample(TestCaseShareFolderTestCaseLinkExample example);

    int updateByExample(TestCaseShareFolderTestCaseLink record, TestCaseShareFolderTestCaseLinkExample example);

    int updateByExampleSelective(TestCaseShareFolderTestCaseLink record,
            TestCaseShareFolderTestCaseLinkExample example);

    int updateByPrimaryKey(TestCaseShareFolderTestCaseLink record);

    int updateByPrimaryKeySelective(TestCaseShareFolderTestCaseLink record);
}