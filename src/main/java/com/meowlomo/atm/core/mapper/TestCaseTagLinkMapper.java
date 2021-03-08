package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseTagLink;
import com.meowlomo.atm.core.model.TestCaseTagLinkExample;

public interface TestCaseTagLinkMapper {
    long countByExample(TestCaseTagLinkExample example);

    int deleteByExample(TestCaseTagLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseTagLink record);

    int insertSelective(@Param("record") TestCaseTagLink record, @Param("selective") TestCaseTagLink.Column... selective);

    List<TestCaseTagLink> selectByExample(TestCaseTagLinkExample example);

    List<TestCaseTagLink> selectByExampleSelective(@Param("example") TestCaseTagLinkExample example, @Param("selective") TestCaseTagLink.Column... selective);

    List<TestCaseTagLink> selectByExampleWithRowbounds(TestCaseTagLinkExample example, RowBounds rowBounds);

    TestCaseTagLink selectByPrimaryKey(Long id);

    TestCaseTagLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseTagLink.Column... selective);

    TestCaseTagLink selectOneByExample(TestCaseTagLinkExample example);

    TestCaseTagLink selectOneByExampleSelective(@Param("example") TestCaseTagLinkExample example, @Param("selective") TestCaseTagLink.Column... selective);

    int updateByExample(@Param("record") TestCaseTagLink record, @Param("example") TestCaseTagLinkExample example);

    int updateByExampleSelective(@Param("record") TestCaseTagLink record, @Param("example") TestCaseTagLinkExample example,
            @Param("selective") TestCaseTagLink.Column... selective);

    int updateByPrimaryKey(TestCaseTagLink record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseTagLink record, @Param("selective") TestCaseTagLink.Column... selective);
}