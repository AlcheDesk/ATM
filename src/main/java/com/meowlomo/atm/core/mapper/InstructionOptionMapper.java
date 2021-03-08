package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOption;
import com.meowlomo.atm.core.model.InstructionOptionExample;

public interface InstructionOptionMapper {
    long countByExample(InstructionOptionExample example);

    int deleteByExample(InstructionOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionOption record);

    int insertSelective(@Param("record") InstructionOption record, @Param("selective") InstructionOption.Column... selective);

    List<InstructionOption> selectByExample(InstructionOptionExample example);

    List<InstructionOption> selectByExampleSelective(@Param("example") InstructionOptionExample example, @Param("selective") InstructionOption.Column... selective);

    List<InstructionOption> selectByExampleWithRowbounds(InstructionOptionExample example, RowBounds rowBounds);

    InstructionOption selectByPrimaryKey(Long id);

    InstructionOption selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionOption.Column... selective);

    InstructionOption selectOneByExample(InstructionOptionExample example);

    InstructionOption selectOneByExampleSelective(@Param("example") InstructionOptionExample example, @Param("selective") InstructionOption.Column... selective);

    int updateByExample(@Param("record") InstructionOption record, @Param("example") InstructionOptionExample example);

    int updateByExampleSelective(@Param("record") InstructionOption record, @Param("example") InstructionOptionExample example,
            @Param("selective") InstructionOption.Column... selective);

    int updateByPrimaryKey(InstructionOption record);

    int updateByPrimaryKeySelective(@Param("record") InstructionOption record, @Param("selective") InstructionOption.Column... selective);
}