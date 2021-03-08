package com.meowlomo.atm.core.mapper.util;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meowlomo.atm.core.model.Driver;

public interface DriverUtilMapper {

    List<Driver> selectByTestCaseIds(@Param("testCaseIds") List<Long> testCaseIds);

    List<Driver> selectByRunSetId(Long runSetId);

}