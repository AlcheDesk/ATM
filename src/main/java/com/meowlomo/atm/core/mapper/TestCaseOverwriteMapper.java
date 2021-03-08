package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;

public interface TestCaseOverwriteMapper {
    long countByExample(TestCaseOverwriteExample example);

    int deleteByExample(TestCaseOverwriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseOverwrite record);

    int insertSelective(@Param("record") TestCaseOverwrite record, @Param("selective") TestCaseOverwrite.Column... selective);

    int logicalDeleteByExample(@Param("example") TestCaseOverwriteExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<TestCaseOverwrite> selectByExample(TestCaseOverwriteExample example);

    List<TestCaseOverwrite> selectByExampleSelective(@Param("example") TestCaseOverwriteExample example, @Param("selective") TestCaseOverwrite.Column... selective);

    List<TestCaseOverwrite> selectByExampleWithRowbounds(TestCaseOverwriteExample example, RowBounds rowBounds);

    TestCaseOverwrite selectByPrimaryKey(Long id);

    TestCaseOverwrite selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseOverwrite.Column... selective);

    TestCaseOverwrite selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    TestCaseOverwrite selectOneByExample(TestCaseOverwriteExample example);

    TestCaseOverwrite selectOneByExampleSelective(@Param("example") TestCaseOverwriteExample example, @Param("selective") TestCaseOverwrite.Column... selective);

    int updateByExample(@Param("record") TestCaseOverwrite record, @Param("example") TestCaseOverwriteExample example);

    int updateByExampleSelective(@Param("record") TestCaseOverwrite record, @Param("example") TestCaseOverwriteExample example,
            @Param("selective") TestCaseOverwrite.Column... selective);

    int updateByPrimaryKey(TestCaseOverwrite record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseOverwrite record, @Param("selective") TestCaseOverwrite.Column... selective);
}