package com.meowlomo.atm.core.mapper.util;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meowlomo.atm.core.model.TestCase;

public interface TestCaseUtilMapper {
    List<TestCase> getReferencedTestCasesFromTestCaseId(@Param("testCaseId") Long testCaseId);
    long countReferencedTestCasesFromTestCaseId(@Param("testCaseId") Long testCaseId);
}
