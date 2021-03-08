package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;

public interface TestCaseOverwriteReferenceMapper {

    long countByExample(TestCaseOverwriteExample example);

    List<TestCaseOverwrite> selectByExample(TestCaseOverwriteExample example);

    List<TestCaseOverwrite> selectByExampleSelective(@Param("example") TestCaseOverwriteExample example, @Param("selective") TestCaseOverwrite.Column... selective);

    List<TestCaseOverwrite> selectByExampleWithRowbounds(TestCaseOverwriteExample example, RowBounds rowBounds);

    TestCaseOverwrite selectByPrimaryKey(Long id);

    TestCaseOverwrite selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseOverwrite.Column... selective);

    TestCaseOverwrite selectOneByExample(TestCaseOverwriteExample example);

    TestCaseOverwrite selectOneByExampleSelective(@Param("example") TestCaseOverwriteExample example, @Param("selective") TestCaseOverwrite.Column... selective);

}