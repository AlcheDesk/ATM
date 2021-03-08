package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseDriverTypeMap;
import com.meowlomo.atm.core.model.TestCaseDriverTypeMapExample;

public interface TestCaseDriverTypeMapMapper {
    long countByExample(TestCaseDriverTypeMapExample example);

    int deleteByExample(TestCaseDriverTypeMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseDriverTypeMap record);

    int insertSelective(@Param("record") TestCaseDriverTypeMap record, @Param("selective") TestCaseDriverTypeMap.Column... selective);

    List<TestCaseDriverTypeMap> selectByExample(TestCaseDriverTypeMapExample example);

    List<TestCaseDriverTypeMap> selectByExampleSelective(@Param("example") TestCaseDriverTypeMapExample example, @Param("selective") TestCaseDriverTypeMap.Column... selective);

    List<TestCaseDriverTypeMap> selectByExampleWithRowbounds(TestCaseDriverTypeMapExample example, RowBounds rowBounds);

    TestCaseDriverTypeMap selectByPrimaryKey(Long id);

    TestCaseDriverTypeMap selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseDriverTypeMap.Column... selective);

    TestCaseDriverTypeMap selectOneByExample(TestCaseDriverTypeMapExample example);

    TestCaseDriverTypeMap selectOneByExampleSelective(@Param("example") TestCaseDriverTypeMapExample example, @Param("selective") TestCaseDriverTypeMap.Column... selective);

    int updateByExample(@Param("record") TestCaseDriverTypeMap record, @Param("example") TestCaseDriverTypeMapExample example);

    int updateByExampleSelective(@Param("record") TestCaseDriverTypeMap record, @Param("example") TestCaseDriverTypeMapExample example,
            @Param("selective") TestCaseDriverTypeMap.Column... selective);

    int updateByPrimaryKey(TestCaseDriverTypeMap record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseDriverTypeMap record, @Param("selective") TestCaseDriverTypeMap.Column... selective);
}