package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackExample;

public interface DriverPackReferenceService {

    long countByExample(DriverPackExample example);

    List<DriverPack> selectByExample(DriverPackExample example);

    List<DriverPack> selectByExampleWithRowbounds(DriverPackExample example, RowBounds rowBounds);

    DriverPack selectByPrimaryKey(Long id);

}
