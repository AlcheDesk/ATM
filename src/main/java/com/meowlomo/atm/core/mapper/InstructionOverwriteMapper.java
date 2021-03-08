package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOverwrite;
import com.meowlomo.atm.core.model.InstructionOverwriteExample;

public interface InstructionOverwriteMapper {
    long countByExample(InstructionOverwriteExample example);

    int deleteByExample(InstructionOverwriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionOverwrite record);

    int insertSelective(@Param("record") InstructionOverwrite record, @Param("selective") InstructionOverwrite.Column... selective);

    List<InstructionOverwrite> selectByExample(InstructionOverwriteExample example);

    List<InstructionOverwrite> selectByExampleSelective(@Param("example") InstructionOverwriteExample example, @Param("selective") InstructionOverwrite.Column... selective);

    List<InstructionOverwrite> selectByExampleWithRowbounds(InstructionOverwriteExample example, RowBounds rowBounds);

    InstructionOverwrite selectByPrimaryKey(Long id);

    InstructionOverwrite selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionOverwrite.Column... selective);

    InstructionOverwrite selectOneByExample(InstructionOverwriteExample example);

    InstructionOverwrite selectOneByExampleSelective(@Param("example") InstructionOverwriteExample example, @Param("selective") InstructionOverwrite.Column... selective);

    int updateByExample(@Param("record") InstructionOverwrite record, @Param("example") InstructionOverwriteExample example);

    int updateByExampleSelective(@Param("record") InstructionOverwrite record, @Param("example") InstructionOverwriteExample example,
            @Param("selective") InstructionOverwrite.Column... selective);

    int updateByPrimaryKey(InstructionOverwrite record);

    int updateByPrimaryKeySelective(@Param("record") InstructionOverwrite record, @Param("selective") InstructionOverwrite.Column... selective);
}