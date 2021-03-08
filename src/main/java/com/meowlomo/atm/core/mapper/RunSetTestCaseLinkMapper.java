package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;

public interface RunSetTestCaseLinkMapper {
    long countByExample(RunSetTestCaseLinkExample example);

    int deleteByExample(RunSetTestCaseLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RunSetTestCaseLink record);

    int insertSelective(@Param("record") RunSetTestCaseLink record, @Param("selective") RunSetTestCaseLink.Column... selective);

    List<RunSetTestCaseLink> selectByExample(RunSetTestCaseLinkExample example);

    List<RunSetTestCaseLink> selectByExampleSelective(@Param("example") RunSetTestCaseLinkExample example, @Param("selective") RunSetTestCaseLink.Column... selective);

    List<RunSetTestCaseLink> selectByExampleWithRowbounds(RunSetTestCaseLinkExample example, RowBounds rowBounds);

    RunSetTestCaseLink selectByPrimaryKey(Long id);

    RunSetTestCaseLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RunSetTestCaseLink.Column... selective);

    RunSetTestCaseLink selectOneByExample(RunSetTestCaseLinkExample example);

    RunSetTestCaseLink selectOneByExampleSelective(@Param("example") RunSetTestCaseLinkExample example, @Param("selective") RunSetTestCaseLink.Column... selective);

    int updateByExample(@Param("record") RunSetTestCaseLink record, @Param("example") RunSetTestCaseLinkExample example);

    int updateByExampleSelective(@Param("record") RunSetTestCaseLink record, @Param("example") RunSetTestCaseLinkExample example,
            @Param("selective") RunSetTestCaseLink.Column... selective);

    int updateByPrimaryKey(RunSetTestCaseLink record);

    int updateByPrimaryKeySelective(@Param("record") RunSetTestCaseLink record, @Param("selective") RunSetTestCaseLink.Column... selective);
}