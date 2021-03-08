package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;

public interface InstructionOverwriteReferenceMapper {

    long countByExample(InstructionOverwriteExample example);

    List<InstructionOverwrite> selectByExample(InstructionOverwriteExample example);

    List<InstructionOverwrite> selectByExampleSelective(@Param("example") InstructionOverwriteExample example, @Param("selective") InstructionOverwrite.Column... selective);

    List<InstructionOverwrite> selectByExampleWithRowbounds(InstructionOverwriteExample example, RowBounds rowBounds);

    InstructionOverwrite selectByPrimaryKey(Long id);

    InstructionOverwrite selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionOverwrite.Column... selective);

    InstructionOverwrite selectOneByExample(InstructionOverwriteExample example);

    InstructionOverwrite selectOneByExampleSelective(@Param("example") InstructionOverwriteExample example, @Param("selective") InstructionOverwrite.Column... selective);
}