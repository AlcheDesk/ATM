package com.meowlomo.atm.core.mapper.util;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.meowlomo.atm.core.model.DriverPack;

public interface DriverPackUtilMapper {
    List<DriverPack> getDriverPack(@Param("driverTypes") Set<String> driverTypes);

    List<DriverPack> getAllDriverPack(@Param("driverTypes") Set<String> driverTypes);
}