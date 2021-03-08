package com.meowlomo.atm.core.mapper.util;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;

public interface RunUtilMapper {

    List<Run> selectTimoutedRun();
    int updateTimeoutedByExample(@Param("record") Run record, @Param("example") RunExample example);
    int cleanEndAtEarlierStartAtRun();
    int cleanStartAtIsNullFinishedIsTrueRun();
    int cleanFinishedNotInEndStatus();
    int cleanEndAtNotNullStartAtNullRun();
}