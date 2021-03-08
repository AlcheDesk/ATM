package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseDriverAliasMap;
import com.meowlomo.atm.core.model.TestCaseDriverAliasMapExample;

public interface TestCaseDriverAliasMapMapper {
    long countByExample(TestCaseDriverAliasMapExample example);

    List<TestCaseDriverAliasMap> selectByExample(TestCaseDriverAliasMapExample example);

    List<TestCaseDriverAliasMap> selectByExampleSelective(@Param("example") TestCaseDriverAliasMapExample example, @Param("selective") TestCaseDriverAliasMap.Column... selective);

    List<TestCaseDriverAliasMap> selectByExampleWithRowbounds(TestCaseDriverAliasMapExample example, RowBounds rowBounds);

    TestCaseDriverAliasMap selectByPrimaryKey(Long id);

    TestCaseDriverAliasMap selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseDriverAliasMap.Column... selective);

    TestCaseDriverAliasMap selectOneByExample(TestCaseDriverAliasMapExample example);

    TestCaseDriverAliasMap selectOneByExampleSelective(@Param("example") TestCaseDriverAliasMapExample example, @Param("selective") TestCaseDriverAliasMap.Column... selective);

}