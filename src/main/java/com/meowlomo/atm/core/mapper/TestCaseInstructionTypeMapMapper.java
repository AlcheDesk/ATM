package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseInstructionTypeMap;
import com.meowlomo.atm.core.model.TestCaseInstructionTypeMapExample;

public interface TestCaseInstructionTypeMapMapper {
    long countByExample(TestCaseInstructionTypeMapExample example);

    int deleteByExample(TestCaseInstructionTypeMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseInstructionTypeMap record);

    int insertSelective(@Param("record") TestCaseInstructionTypeMap record, @Param("selective") TestCaseInstructionTypeMap.Column... selective);

    List<TestCaseInstructionTypeMap> selectByExample(TestCaseInstructionTypeMapExample example);

    List<TestCaseInstructionTypeMap> selectByExampleSelective(@Param("example") TestCaseInstructionTypeMapExample example,
            @Param("selective") TestCaseInstructionTypeMap.Column... selective);

    List<TestCaseInstructionTypeMap> selectByExampleWithRowbounds(TestCaseInstructionTypeMapExample example, RowBounds rowBounds);

    TestCaseInstructionTypeMap selectByPrimaryKey(Long id);

    TestCaseInstructionTypeMap selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseInstructionTypeMap.Column... selective);

    TestCaseInstructionTypeMap selectOneByExample(TestCaseInstructionTypeMapExample example);

    TestCaseInstructionTypeMap selectOneByExampleSelective(@Param("example") TestCaseInstructionTypeMapExample example,
            @Param("selective") TestCaseInstructionTypeMap.Column... selective);

    int updateByExample(@Param("record") TestCaseInstructionTypeMap record, @Param("example") TestCaseInstructionTypeMapExample example);

    int updateByExampleSelective(@Param("record") TestCaseInstructionTypeMap record, @Param("example") TestCaseInstructionTypeMapExample example,
            @Param("selective") TestCaseInstructionTypeMap.Column... selective);

    int updateByPrimaryKey(TestCaseInstructionTypeMap record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseInstructionTypeMap record, @Param("selective") TestCaseInstructionTypeMap.Column... selective);
}