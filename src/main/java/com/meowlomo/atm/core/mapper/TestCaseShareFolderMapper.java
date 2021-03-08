package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;

public interface TestCaseShareFolderMapper {
    long countByExample(TestCaseShareFolderExample example);

    int deleteByExample(TestCaseShareFolderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseShareFolder record);

    int insertSelective(@Param("record") TestCaseShareFolder record, @Param("selective") TestCaseShareFolder.Column... selective);

    int logicalDeleteByExample(@Param("example") TestCaseShareFolderExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<TestCaseShareFolder> selectByExample(TestCaseShareFolderExample example);

    List<TestCaseShareFolder> selectByExampleSelective(@Param("example") TestCaseShareFolderExample example, @Param("selective") TestCaseShareFolder.Column... selective);

    List<TestCaseShareFolder> selectByExampleWithRowbounds(TestCaseShareFolderExample example, RowBounds rowBounds);

    TestCaseShareFolder selectByPrimaryKey(Long id);

    TestCaseShareFolder selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseShareFolder.Column... selective);

    TestCaseShareFolder selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    TestCaseShareFolder selectOneByExample(TestCaseShareFolderExample example);

    TestCaseShareFolder selectOneByExampleSelective(@Param("example") TestCaseShareFolderExample example, @Param("selective") TestCaseShareFolder.Column... selective);

    int updateByExample(@Param("record") TestCaseShareFolder record, @Param("example") TestCaseShareFolderExample example);

    int updateByExampleSelective(@Param("record") TestCaseShareFolder record, @Param("example") TestCaseShareFolderExample example,
            @Param("selective") TestCaseShareFolder.Column... selective);

    int updateByPrimaryKey(TestCaseShareFolder record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseShareFolder record, @Param("selective") TestCaseShareFolder.Column... selective);
}