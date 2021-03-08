package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;

public interface TestCaseOverwriteReferenceService {

    long countByExample(TestCaseOverwriteExample example);

    List<TestCaseOverwrite> selectByExample(TestCaseOverwriteExample example);

    List<TestCaseOverwrite> selectByExampleWithRowbounds(TestCaseOverwriteExample example, RowBounds rowBounds);

    TestCaseOverwrite selectByPrimaryKey(Long id);
}
