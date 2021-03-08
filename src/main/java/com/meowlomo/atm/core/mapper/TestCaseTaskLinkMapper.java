package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseTaskLink;
import com.meowlomo.atm.core.model.TestCaseTaskLinkExample;

public interface TestCaseTaskLinkMapper {
    long countByExample(TestCaseTaskLinkExample example);

    int deleteByExample(TestCaseTaskLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseTaskLink record);

    int insertSelective(@Param("record") TestCaseTaskLink record, @Param("selective") TestCaseTaskLink.Column... selective);

    List<TestCaseTaskLink> selectByExample(TestCaseTaskLinkExample example);

    List<TestCaseTaskLink> selectByExampleSelective(@Param("example") TestCaseTaskLinkExample example, @Param("selective") TestCaseTaskLink.Column... selective);

    List<TestCaseTaskLink> selectByExampleWithRowbounds(TestCaseTaskLinkExample example, RowBounds rowBounds);

    TestCaseTaskLink selectByPrimaryKey(Long id);

    TestCaseTaskLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseTaskLink.Column... selective);

    TestCaseTaskLink selectOneByExample(TestCaseTaskLinkExample example);

    TestCaseTaskLink selectOneByExampleSelective(@Param("example") TestCaseTaskLinkExample example, @Param("selective") TestCaseTaskLink.Column... selective);

    int updateByExample(@Param("record") TestCaseTaskLink record, @Param("example") TestCaseTaskLinkExample example);

    int updateByExampleSelective(@Param("record") TestCaseTaskLink record, @Param("example") TestCaseTaskLinkExample example,
            @Param("selective") TestCaseTaskLink.Column... selective);

    int updateByPrimaryKey(TestCaseTaskLink record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseTaskLink record, @Param("selective") TestCaseTaskLink.Column... selective);
}