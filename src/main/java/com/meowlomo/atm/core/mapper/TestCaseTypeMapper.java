package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseType;
import com.meowlomo.atm.core.model.TestCaseTypeExample;

public interface TestCaseTypeMapper {
    long countByExample(TestCaseTypeExample example);

    int deleteByExample(TestCaseTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseType record);

    int insertSelective(@Param("record") TestCaseType record, @Param("selective") TestCaseType.Column... selective);

    List<TestCaseType> selectByExample(TestCaseTypeExample example);

    List<TestCaseType> selectByExampleSelective(@Param("example") TestCaseTypeExample example, @Param("selective") TestCaseType.Column... selective);

    List<TestCaseType> selectByExampleWithRowbounds(TestCaseTypeExample example, RowBounds rowBounds);

    TestCaseType selectByPrimaryKey(Long id);

    TestCaseType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseType.Column... selective);

    TestCaseType selectOneByExample(TestCaseTypeExample example);

    TestCaseType selectOneByExampleSelective(@Param("example") TestCaseTypeExample example, @Param("selective") TestCaseType.Column... selective);

    int updateByExample(@Param("record") TestCaseType record, @Param("example") TestCaseTypeExample example);

    int updateByExampleSelective(@Param("record") TestCaseType record, @Param("example") TestCaseTypeExample example, @Param("selective") TestCaseType.Column... selective);

    int updateByPrimaryKey(TestCaseType record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseType record, @Param("selective") TestCaseType.Column... selective);
}