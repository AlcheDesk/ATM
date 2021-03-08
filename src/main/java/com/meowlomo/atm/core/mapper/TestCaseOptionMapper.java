package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseOption;
import com.meowlomo.atm.core.model.TestCaseOptionExample;

public interface TestCaseOptionMapper {
    long countByExample(TestCaseOptionExample example);

    int deleteByExample(TestCaseOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseOption record);

    int insertSelective(@Param("record") TestCaseOption record, @Param("selective") TestCaseOption.Column... selective);

    List<TestCaseOption> selectByExample(TestCaseOptionExample example);

    List<TestCaseOption> selectByExampleSelective(@Param("example") TestCaseOptionExample example, @Param("selective") TestCaseOption.Column... selective);

    List<TestCaseOption> selectByExampleWithRowbounds(TestCaseOptionExample example, RowBounds rowBounds);

    TestCaseOption selectByPrimaryKey(Long id);

    TestCaseOption selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseOption.Column... selective);

    TestCaseOption selectOneByExample(TestCaseOptionExample example);

    TestCaseOption selectOneByExampleSelective(@Param("example") TestCaseOptionExample example, @Param("selective") TestCaseOption.Column... selective);

    int updateByExample(@Param("record") TestCaseOption record, @Param("example") TestCaseOptionExample example);

    int updateByExampleSelective(@Param("record") TestCaseOption record, @Param("example") TestCaseOptionExample example, @Param("selective") TestCaseOption.Column... selective);

    int updateByPrimaryKey(TestCaseOption record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseOption record, @Param("selective") TestCaseOption.Column... selective);
}