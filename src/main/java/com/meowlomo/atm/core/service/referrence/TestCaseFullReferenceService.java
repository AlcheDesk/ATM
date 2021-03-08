package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;

public interface TestCaseFullReferenceService {

    long countByExample(TestCaseExample example);

    List<TestCase> selectByExample(TestCaseExample example);

    List<TestCase> selectByExampleWithRowbounds(TestCaseExample example, RowBounds rowBounds);

    TestCase selectByPrimaryKey(Long id);

}
