package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;

public interface RunSetTestCaseLinkReferenceMapper {

    long countByExample(RunSetTestCaseLinkExample example);

    List<RunSetTestCaseLink> selectByExample(RunSetTestCaseLinkExample example);

    List<RunSetTestCaseLink> selectByExampleSelective(@Param("example") RunSetTestCaseLinkExample example, @Param("selective") RunSetTestCaseLink.Column... selective);

    List<RunSetTestCaseLink> selectByExampleWithRowbounds(RunSetTestCaseLinkExample example, RowBounds rowBounds);

    RunSetTestCaseLink selectByPrimaryKey(Long id);

    RunSetTestCaseLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetTestCaseLink.Column... selective);

    RunSetTestCaseLink selectOneByExample(RunSetTestCaseLinkExample example);

    RunSetTestCaseLink selectOneByExampleSelective(@Param("example") RunSetTestCaseLinkExample example, @Param("selective") RunSetTestCaseLink.Column... selective);
}