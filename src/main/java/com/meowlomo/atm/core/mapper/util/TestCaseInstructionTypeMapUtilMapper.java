package com.meowlomo.atm.core.mapper.util;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meowlomo.atm.core.model.TestCaseInstructionTypeMap;

public interface TestCaseInstructionTypeMapUtilMapper {
    List<TestCaseInstructionTypeMap> getTestCaseInstructionTypeMapByRunSetId(@Param("runSetId") Long runSetId);
}
