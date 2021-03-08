package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionTypeElementTypeLink;
import com.meowlomo.atm.core.model.InstructionTypeElementTypeLinkExample;

public interface InstructionTypeElementTypeLinkMapper {
    long countByExample(InstructionTypeElementTypeLinkExample example);

    int deleteByExample(InstructionTypeElementTypeLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionTypeElementTypeLink record);

    int insertSelective(@Param("record") InstructionTypeElementTypeLink record, @Param("selective") InstructionTypeElementTypeLink.Column... selective);

    List<InstructionTypeElementTypeLink> selectByExample(InstructionTypeElementTypeLinkExample example);

    List<InstructionTypeElementTypeLink> selectByExampleSelective(@Param("example") InstructionTypeElementTypeLinkExample example,
            @Param("selective") InstructionTypeElementTypeLink.Column... selective);

    List<InstructionTypeElementTypeLink> selectByExampleWithRowbounds(InstructionTypeElementTypeLinkExample example, RowBounds rowBounds);

    InstructionTypeElementTypeLink selectByPrimaryKey(Long id);

    InstructionTypeElementTypeLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionTypeElementTypeLink.Column... selective);

    InstructionTypeElementTypeLink selectOneByExample(InstructionTypeElementTypeLinkExample example);

    InstructionTypeElementTypeLink selectOneByExampleSelective(@Param("example") InstructionTypeElementTypeLinkExample example,
            @Param("selective") InstructionTypeElementTypeLink.Column... selective);

    int updateByExample(@Param("record") InstructionTypeElementTypeLink record, @Param("example") InstructionTypeElementTypeLinkExample example);

    int updateByExampleSelective(@Param("record") InstructionTypeElementTypeLink record, @Param("example") InstructionTypeElementTypeLinkExample example,
            @Param("selective") InstructionTypeElementTypeLink.Column... selective);

    int updateByPrimaryKey(InstructionTypeElementTypeLink record);

    int updateByPrimaryKeySelective(@Param("record") InstructionTypeElementTypeLink record, @Param("selective") InstructionTypeElementTypeLink.Column... selective);
}