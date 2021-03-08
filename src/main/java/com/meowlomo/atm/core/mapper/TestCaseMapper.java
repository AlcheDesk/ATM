package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;

public interface TestCaseMapper {
    long countByExample(TestCaseExample example);

    int deleteByExample(TestCaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCase record);

    int insertSelective(@Param("record") TestCase record, @Param("selective") TestCase.Column... selective);

    int logicalDeleteByExample(@Param("example") TestCaseExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<TestCase> selectByExample(TestCaseExample example);

    List<TestCase> selectByExampleSelective(@Param("example") TestCaseExample example, @Param("selective") TestCase.Column... selective);

    List<TestCase> selectByExampleWithRowbounds(TestCaseExample example, RowBounds rowBounds);

    TestCase selectByPrimaryKey(Long id);

    TestCase selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCase.Column... selective);

    TestCase selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    TestCase selectOneByExample(TestCaseExample example);

    TestCase selectOneByExampleSelective(@Param("example") TestCaseExample example, @Param("selective") TestCase.Column... selective);

    int updateByExample(@Param("record") TestCase record, @Param("example") TestCaseExample example);

    int updateByExampleSelective(@Param("record") TestCase record, @Param("example") TestCaseExample example, @Param("selective") TestCase.Column... selective);

    int updateByPrimaryKey(TestCase record);

    int updateByPrimaryKeySelective(@Param("record") TestCase record, @Param("selective") TestCase.Column... selective);
}