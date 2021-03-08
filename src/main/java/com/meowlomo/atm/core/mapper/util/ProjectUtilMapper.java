package com.meowlomo.atm.core.mapper.util;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProjectUtilMapper {
    List<Long> getTestCaseIdsFromProjectId(@Param("projectId") Long projectId);
}
