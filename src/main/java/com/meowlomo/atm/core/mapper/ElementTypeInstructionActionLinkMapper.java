package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementTypeInstructionActionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionActionLinkExample;

public interface ElementTypeInstructionActionLinkMapper {
    long countByExample(ElementTypeInstructionActionLinkExample example);

    int deleteByExample(ElementTypeInstructionActionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementTypeInstructionActionLink record);

    int insertSelective(@Param("record") ElementTypeInstructionActionLink record, @Param("selective") ElementTypeInstructionActionLink.Column... selective);

    List<ElementTypeInstructionActionLink> selectByExample(ElementTypeInstructionActionLinkExample example);

    List<ElementTypeInstructionActionLink> selectByExampleSelective(@Param("example") ElementTypeInstructionActionLinkExample example,
            @Param("selective") ElementTypeInstructionActionLink.Column... selective);

    List<ElementTypeInstructionActionLink> selectByExampleWithRowbounds(ElementTypeInstructionActionLinkExample example, RowBounds rowBounds);

    ElementTypeInstructionActionLink selectByPrimaryKey(Long id);

    ElementTypeInstructionActionLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ElementTypeInstructionActionLink.Column... selective);

    ElementTypeInstructionActionLink selectOneByExample(ElementTypeInstructionActionLinkExample example);

    ElementTypeInstructionActionLink selectOneByExampleSelective(@Param("example") ElementTypeInstructionActionLinkExample example,
            @Param("selective") ElementTypeInstructionActionLink.Column... selective);

    int updateByExample(@Param("record") ElementTypeInstructionActionLink record, @Param("example") ElementTypeInstructionActionLinkExample example);

    int updateByExampleSelective(@Param("record") ElementTypeInstructionActionLink record, @Param("example") ElementTypeInstructionActionLinkExample example,
            @Param("selective") ElementTypeInstructionActionLink.Column... selective);

    int updateByPrimaryKey(ElementTypeInstructionActionLink record);

    int updateByPrimaryKeySelective(@Param("record") ElementTypeInstructionActionLink record, @Param("selective") ElementTypeInstructionActionLink.Column... selective);
}