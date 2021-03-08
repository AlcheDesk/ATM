package com.meowlomo.atm.core.mapper.util;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.RunSet;

public interface RunSetUtilMapper {
    List<RunSet> selectByAliases(@Param("aliases") List<Alias> aliases);

    List<Long> getRunSetIdsByAliasNames(@Param("aliasNames") Set<String> aliasNames);
}
