package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;

public interface ProdInstructionResultMapper {
    long countByExample(InstructionResultExample example);

    int deleteByExample(InstructionResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionResult record);

    int insertSelective(@Param("record") InstructionResult record, @Param("selective") InstructionResult.Column... selective);

    List<InstructionResult> selectByExample(InstructionResultExample example);

    List<InstructionResult> selectByExampleSelective(@Param("example") InstructionResultExample example, @Param("selective") InstructionResult.Column... selective);

    List<InstructionResult> selectByExampleWithRowbounds(InstructionResultExample example, RowBounds rowBounds);

    InstructionResult selectByPrimaryKey(Long id);

    InstructionResult selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionResult.Column... selective);

    InstructionResult selectOneByExample(InstructionResultExample example);

    InstructionResult selectOneByExampleSelective(@Param("example") InstructionResultExample example, @Param("selective") InstructionResult.Column... selective);

    int updateByExample(@Param("record") InstructionResult record, @Param("example") InstructionResultExample example);

    int updateByExampleSelective(@Param("record") InstructionResult record, @Param("example") InstructionResultExample example,
            @Param("selective") InstructionResult.Column... selective);

    int updateByPrimaryKey(InstructionResult record);

    int updateByPrimaryKeySelective(@Param("record") InstructionResult record, @Param("selective") InstructionResult.Column... selective);
}