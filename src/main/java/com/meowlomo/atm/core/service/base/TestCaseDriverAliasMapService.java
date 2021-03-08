package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseDriverAliasMap;
import com.meowlomo.atm.core.model.TestCaseDriverAliasMapExample;

public interface TestCaseDriverAliasMapService {
    long countByExample(TestCaseDriverAliasMapExample example);

    List<TestCaseDriverAliasMap> selectByExample(TestCaseDriverAliasMapExample example);

    List<TestCaseDriverAliasMap> selectByExampleWithRowbounds(TestCaseDriverAliasMapExample example,
            RowBounds rowBounds);

    TestCaseDriverAliasMap selectByPrimaryKey(Long id);

    TestCaseDriverAliasMap selectOneByExample(TestCaseDriverAliasMapExample example);

}