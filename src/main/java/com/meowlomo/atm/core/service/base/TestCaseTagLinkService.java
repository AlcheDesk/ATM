package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseTagLink;
import com.meowlomo.atm.core.model.TestCaseTagLinkExample;

public interface TestCaseTagLinkService {
    long countByExample(TestCaseTagLinkExample example);

    int deleteByExample(TestCaseTagLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseTagLink record);

    List<Long> insertRecords(List<TestCaseTagLink> records);

    List<Long> insertRecordsSelective(List<TestCaseTagLink> records);

    int insertSelective(TestCaseTagLink record);

    List<TestCaseTagLink> selectByExample(TestCaseTagLinkExample example);

    List<TestCaseTagLink> selectByExampleWithRowbounds(TestCaseTagLinkExample example, RowBounds rowBounds);

    TestCaseTagLink selectByPrimaryKey(Long id);

    TestCaseTagLink selectOneByExample(TestCaseTagLinkExample example);

    int updateByExample(TestCaseTagLink record, TestCaseTagLinkExample example);

    int updateByExampleSelective(TestCaseTagLink record, TestCaseTagLinkExample example);

    int updateByPrimaryKey(TestCaseTagLink record);

    int updateByPrimaryKeySelective(TestCaseTagLink record);
}