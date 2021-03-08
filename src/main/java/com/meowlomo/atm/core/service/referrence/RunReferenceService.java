package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunExample;

public interface RunReferenceService {

    long countByExample(RunExample example);

    List<Run> selectByExample(RunExample example);

    List<Run> selectByExampleWithRowbounds(RunExample example, RowBounds rowBounds);

    Run selectByPrimaryKey(Long id);

}
