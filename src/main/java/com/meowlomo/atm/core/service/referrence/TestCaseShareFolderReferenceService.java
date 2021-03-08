package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;

public interface TestCaseShareFolderReferenceService {

    long countByExample(TestCaseShareFolderExample example);

    List<TestCaseShareFolder> selectByExample(TestCaseShareFolderExample example);

    List<TestCaseShareFolder> selectByExampleWithRowbounds(TestCaseShareFolderExample example, RowBounds rowBounds);

    TestCaseShareFolder selectByPrimaryKey(Long id);

}
