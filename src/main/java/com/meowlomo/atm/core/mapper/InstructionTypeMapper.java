package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.InstructionTypeExample;

public interface InstructionTypeMapper {
    long countByExample(InstructionTypeExample example);

    int deleteByExample(InstructionTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionType record);

    int insertSelective(@Param("record") InstructionType record, @Param("selective") InstructionType.Column... selective);

    List<InstructionType> selectByExample(InstructionTypeExample example);

    List<InstructionType> selectByExampleSelective(@Param("example") InstructionTypeExample example, @Param("selective") InstructionType.Column... selective);

    List<InstructionType> selectByExampleWithRowbounds(InstructionTypeExample example, RowBounds rowBounds);

    InstructionType selectByPrimaryKey(Long id);

    InstructionType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionType.Column... selective);

    InstructionType selectOneByExample(InstructionTypeExample example);

    InstructionType selectOneByExampleSelective(@Param("example") InstructionTypeExample example, @Param("selective") InstructionType.Column... selective);

    int updateByExample(@Param("record") InstructionType record, @Param("example") InstructionTypeExample example);

    int updateByExampleSelective(@Param("record") InstructionType record, @Param("example") InstructionTypeExample example,
            @Param("selective") InstructionType.Column... selective);

    int updateByPrimaryKey(InstructionType record);

    int updateByPrimaryKeySelective(@Param("record") InstructionType record, @Param("selective") InstructionType.Column... selective);
}