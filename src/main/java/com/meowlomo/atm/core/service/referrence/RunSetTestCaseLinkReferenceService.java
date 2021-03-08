package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;

public interface RunSetTestCaseLinkReferenceService {

    long countByExample(RunSetTestCaseLinkExample example);

    List<RunSetTestCaseLink> selectByExample(RunSetTestCaseLinkExample example);

    List<RunSetTestCaseLink> selectByExampleWithRowbounds(RunSetTestCaseLinkExample example, RowBounds rowBounds);

    RunSetTestCaseLink selectByPrimaryKey(Long id);

}
