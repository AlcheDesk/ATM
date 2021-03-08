package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.TestCaseModuleLink;
import com.meowlomo.atm.core.model.TestCaseModuleLinkExample;

public interface TestCaseModuleLinkMapper {
    long countByExample(TestCaseModuleLinkExample example);

    int deleteByExample(TestCaseModuleLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseModuleLink record);

    int insertSelective(@Param("record") TestCaseModuleLink record, @Param("selective") TestCaseModuleLink.Column... selective);

    List<TestCaseModuleLink> selectByExample(TestCaseModuleLinkExample example);

    List<TestCaseModuleLink> selectByExampleSelective(@Param("example") TestCaseModuleLinkExample example, @Param("selective") TestCaseModuleLink.Column... selective);

    List<TestCaseModuleLink> selectByExampleWithRowbounds(TestCaseModuleLinkExample example, RowBounds rowBounds);

    TestCaseModuleLink selectByPrimaryKey(Long id);

    TestCaseModuleLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TestCaseModuleLink.Column... selective);

    TestCaseModuleLink selectOneByExample(TestCaseModuleLinkExample example);

    TestCaseModuleLink selectOneByExampleSelective(@Param("example") TestCaseModuleLinkExample example, @Param("selective") TestCaseModuleLink.Column... selective);

    int updateByExample(@Param("record") TestCaseModuleLink record, @Param("example") TestCaseModuleLinkExample example);

    int updateByExampleSelective(@Param("record") TestCaseModuleLink record, @Param("example") TestCaseModuleLinkExample example,
            @Param("selective") TestCaseModuleLink.Column... selective);

    int updateByPrimaryKey(TestCaseModuleLink record);

    int updateByPrimaryKeySelective(@Param("record") TestCaseModuleLink record, @Param("selective") TestCaseModuleLink.Column... selective);
}