package com.meowlomo.atm.core.mapper.util;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface RunSetAliasNameMapUtilMapper {
    Set<Long> getRunSetIdsFromAliasNames(@Param("aliasNames") Set<String> aliasNames);
}
