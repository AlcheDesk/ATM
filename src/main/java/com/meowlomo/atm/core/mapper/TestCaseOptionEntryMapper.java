package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOptionEntry;
import com.meowlomo.atm.core.model.TestCaseOptionEntryExample;

public interface TestCaseOptionEntryMapper {
    long countByExample(TestCaseOptionEntryExample example);

    int deleteByExample(TestCaseOptionEntryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseOptionEntry record);

    int insertSelective(@Param("record") TestCaseOptionEntry record, @Param("selective") TestCaseOptionEntry.Column... selective);

    List<TestCaseOptionEntry> selectByExample(TestCaseOptionEntryExample example);

    List<TestCaseOptionEntry> selectByExampleSelective(@Param("example") TestCaseOptionEntryExample example, @Param("selective") TestCaseOptionEntry.Column... selective);

    List<TestCaseOptionEntry> selectByExampleWithRowbounds(TestCaseOptionEntryExample example, RowBounds rowBounds);

    TestCaseOptionEntry selectByPrimaryKey(Long id);

    TestCaseOptionEntry selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseOptionEntry.Column... selective);

    TestCaseOptionEntry selectOneByExample(TestCaseOptionEntryExample example);

    TestCaseOptionEntry selectOneByExampleSelective(@Param("example") TestCaseOptionEntryExample example, @Param("selective") TestCaseOptionEntry.Column... selective);

    int updateByExample(@Param("record") TestCaseOptionEntry record, @Param("example") TestCaseOptionEntryExample example);

    int updateByExampleSelective(@Param("record") TestCaseOptionEntry record, @Param("example") TestCaseOptionEntryExample example,
            @Param("selective") TestCaseOptionEntry.Column... selective);

    int updateByPrimaryKey(TestCaseOptionEntry record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseOptionEntry record, @Param("selective") TestCaseOptionEntry.Column... selective);
}