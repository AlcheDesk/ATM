package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;

public interface TestCaseShareFolderReferenceMapper {

    long countByExample(TestCaseShareFolderExample example);

    List<TestCaseShareFolder> selectByExample(TestCaseShareFolderExample example);

    List<TestCaseShareFolder> selectByExampleSelective(@Param("example") TestCaseShareFolderExample example, @Param("selective") TestCaseShareFolder.Column... selective);

    List<TestCaseShareFolder> selectByExampleWithRowbounds(TestCaseShareFolderExample example, RowBounds rowBounds);

    TestCaseShareFolder selectByPrimaryKey(Long id);

    TestCaseShareFolder selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseShareFolder.Column... selective);

    TestCaseShareFolder selectOneByExample(TestCaseShareFolderExample example);

    TestCaseShareFolder selectOneByExampleSelective(@Param("example") TestCaseShareFolderExample example, @Param("selective") TestCaseShareFolder.Column... selective);

}