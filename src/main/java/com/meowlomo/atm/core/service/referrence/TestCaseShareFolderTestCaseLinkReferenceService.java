package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLinkExample;

public interface TestCaseShareFolderTestCaseLinkReferenceService {

    long countByExample(TestCaseShareFolderTestCaseLinkExample example);

    List<TestCaseShareFolderTestCaseLink> selectByExample(TestCaseShareFolderTestCaseLinkExample example);

    List<TestCaseShareFolderTestCaseLink> selectByExampleWithRowbounds(TestCaseShareFolderTestCaseLinkExample example, RowBounds rowBounds);

    TestCaseShareFolderTestCaseLink selectByPrimaryKey(Long id);

}