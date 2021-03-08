package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.RunSetResultExample;

public interface RunSetResultReferenceService {

    long countByExample(RunSetResultExample example);

    List<RunSetResult> selectByExample(RunSetResultExample example);

    List<RunSetResult> selectByExampleWithRowbounds(RunSetResultExample example, RowBounds rowBounds);

    RunSetResult selectByPrimaryKey(Long id);

}
