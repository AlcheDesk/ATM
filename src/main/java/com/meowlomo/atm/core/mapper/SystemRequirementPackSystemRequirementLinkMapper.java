package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLink;
import com.meowlomo.atm.core.model.SystemRequirementPackSystemRequirementLinkExample;

public interface SystemRequirementPackSystemRequirementLinkMapper {
    long countByExample(SystemRequirementPackSystemRequirementLinkExample example);

    int deleteByExample(SystemRequirementPackSystemRequirementLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRequirementPackSystemRequirementLink record);

    int insertSelective(@Param("record") SystemRequirementPackSystemRequirementLink record, @Param("selective") SystemRequirementPackSystemRequirementLink.Column... selective);

    List<SystemRequirementPackSystemRequirementLink> selectByExample(SystemRequirementPackSystemRequirementLinkExample example);

    List<SystemRequirementPackSystemRequirementLink> selectByExampleSelective(@Param("example") SystemRequirementPackSystemRequirementLinkExample example,
            @Param("selective") SystemRequirementPackSystemRequirementLink.Column... selective);

    List<SystemRequirementPackSystemRequirementLink> selectByExampleWithRowbounds(SystemRequirementPackSystemRequirementLinkExample example, RowBounds rowBounds);

    SystemRequirementPackSystemRequirementLink selectByPrimaryKey(Long id);

    SystemRequirementPackSystemRequirementLink selectByPrimaryKeySelective(@Param("id") Long id,
            @Param("selective") SystemRequirementPackSystemRequirementLink.Column... selective);

    SystemRequirementPackSystemRequirementLink selectOneByExample(SystemRequirementPackSystemRequirementLinkExample example);

    SystemRequirementPackSystemRequirementLink selectOneByExampleSelective(@Param("example") SystemRequirementPackSystemRequirementLinkExample example,
            @Param("selective") SystemRequirementPackSystemRequirementLink.Column... selective);

    int updateByExample(@Param("record") SystemRequirementPackSystemRequirementLink record, @Param("example") SystemRequirementPackSystemRequirementLinkExample example);

    int updateByExampleSelective(@Param("record") SystemRequirementPackSystemRequirementLink record, @Param("example") SystemRequirementPackSystemRequirementLinkExample example,
            @Param("selective") SystemRequirementPackSystemRequirementLink.Column... selective);

    int updateByPrimaryKey(SystemRequirementPackSystemRequirementLink record);

    int updateByPrimaryKeySelective(@Param("record") SystemRequirementPackSystemRequirementLink record,
            @Param("selective") SystemRequirementPackSystemRequirementLink.Column... selective);
}