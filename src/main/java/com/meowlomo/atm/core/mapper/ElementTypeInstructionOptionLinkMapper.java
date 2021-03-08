package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLink;
import com.meowlomo.atm.core.model.ElementTypeInstructionOptionLinkExample;

public interface ElementTypeInstructionOptionLinkMapper {
    long countByExample(ElementTypeInstructionOptionLinkExample example);

    int deleteByExample(ElementTypeInstructionOptionLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ElementTypeInstructionOptionLink record);

    int insertSelective(@Param("record") ElementTypeInstructionOptionLink record, @Param("selective") ElementTypeInstructionOptionLink.Column... selective);

    List<ElementTypeInstructionOptionLink> selectByExample(ElementTypeInstructionOptionLinkExample example);

    List<ElementTypeInstructionOptionLink> selectByExampleSelective(@Param("example") ElementTypeInstructionOptionLinkExample example,
            @Param("selective") ElementTypeInstructionOptionLink.Column... selective);

    List<ElementTypeInstructionOptionLink> selectByExampleWithRowbounds(ElementTypeInstructionOptionLinkExample example, RowBounds rowBounds);

    ElementTypeInstructionOptionLink selectByPrimaryKey(Long id);

    ElementTypeInstructionOptionLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ElementTypeInstructionOptionLink.Column... selective);

    ElementTypeInstructionOptionLink selectOneByExample(ElementTypeInstructionOptionLinkExample example);

    ElementTypeInstructionOptionLink selectOneByExampleSelective(@Param("example") ElementTypeInstructionOptionLinkExample example,
            @Param("selective") ElementTypeInstructionOptionLink.Column... selective);

    int updateByExample(@Param("record") ElementTypeInstructionOptionLink record, @Param("example") ElementTypeInstructionOptionLinkExample example);

    int updateByExampleSelective(@Param("record") ElementTypeInstructionOptionLink record, @Param("example") ElementTypeInstructionOptionLinkExample example,
            @Param("selective") ElementTypeInstructionOptionLink.Column... selective);

    int updateByPrimaryKey(ElementTypeInstructionOptionLink record);

    int updateByPrimaryKeySelective(@Param("record") ElementTypeInstructionOptionLink record, @Param("selective") ElementTypeInstructionOptionLink.Column... selective);
}