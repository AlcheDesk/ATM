package com.meowlomo.atm.core.mapper.util;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meowlomo.atm.core.model.TestCaseDriverTypeMap;

public interface TestCaseDriverTypeMapUtilMapper {
    List<TestCaseDriverTypeMap> getTestCaseDriverTypeMapByRunSetId(@Param("runSetId") Long runSetId);
}
