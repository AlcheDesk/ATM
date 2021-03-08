package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;

public interface TestCaseShareFolderService {
    long countByExample(TestCaseShareFolderExample example);

    int deleteByExample(TestCaseShareFolderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseShareFolder record);

    List<Long> insertRecords(List<TestCaseShareFolder> records);

    List<Long> insertRecordsSelective(List<TestCaseShareFolder> records);

    int insertSelective(TestCaseShareFolder record);

    int logicalDeleteByExample(TestCaseShareFolderExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<TestCaseShareFolder> selectByExample(TestCaseShareFolderExample example);

    List<TestCaseShareFolder> selectByExampleWithRowbounds(TestCaseShareFolderExample example, RowBounds rowBounds);

    TestCaseShareFolder selectByPrimaryKey(Long id);

    TestCaseShareFolder selectOneByExample(TestCaseShareFolderExample example);

    int updateByExample(TestCaseShareFolder record, TestCaseShareFolderExample example);

    int updateByExampleSelective(TestCaseShareFolder record, TestCaseShareFolderExample example);

    int updateByPrimaryKey(TestCaseShareFolder record);

    int updateByPrimaryKeySelective(TestCaseShareFolder record);
}