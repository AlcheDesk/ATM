package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLinkExample;

public interface TestCaseShareFolderTestCaseLinkReferenceMapper {

    long countByExample(TestCaseShareFolderTestCaseLinkExample example);

    List<TestCaseShareFolderTestCaseLink> selectByExample(TestCaseShareFolderTestCaseLinkExample example);

    List<TestCaseShareFolderTestCaseLink> selectByExampleSelective(@Param("example") TestCaseShareFolderTestCaseLinkExample example,
            @Param("selective") TestCaseShareFolderTestCaseLink.Column... selective);

    List<TestCaseShareFolderTestCaseLink> selectByExampleWithRowbounds(TestCaseShareFolderTestCaseLinkExample example, RowBounds rowBounds);

    TestCaseShareFolderTestCaseLink selectByPrimaryKey(Long id);

    TestCaseShareFolderTestCaseLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseShareFolderTestCaseLink.Column... selective);

    TestCaseShareFolderTestCaseLink selectOneByExample(TestCaseShareFolderTestCaseLinkExample example);

    TestCaseShareFolderTestCaseLink selectOneByExampleSelective(@Param("example") TestCaseShareFolderTestCaseLinkExample example,
            @Param("selective") TestCaseShareFolderTestCaseLink.Column... selective);
}