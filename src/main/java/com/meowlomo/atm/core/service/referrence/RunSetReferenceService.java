package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetExample;

public interface RunSetReferenceService {

    long countByExample(RunSetExample example);

    List<RunSet> selectByExample(RunSetExample example);

    List<RunSet> selectByExampleWithRowbounds(RunSetExample example, RowBounds rowBounds);

    RunSet selectByPrimaryKey(Long id);

}
