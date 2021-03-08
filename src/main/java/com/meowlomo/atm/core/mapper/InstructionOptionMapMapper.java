package com.meowlomo.atm.core.mapper;

import com.meowlomo.atm.core.model.InstructionOptionMap;
import com.meowlomo.atm.core.model.InstructionOptionMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface InstructionOptionMapMapper {
    long countByExample(InstructionOptionMapExample example);

    int deleteByExample(InstructionOptionMapExample example);

    int deleteByPrimaryKey(String instructionOption);

    int insert(InstructionOptionMap record);

    int insertSelective(@Param("record") InstructionOptionMap record, @Param("selective") InstructionOptionMap.Column... selective);

    List<InstructionOptionMap> selectByExample(InstructionOptionMapExample example);

    List<InstructionOptionMap> selectByExampleSelective(@Param("example") InstructionOptionMapExample example, @Param("selective") InstructionOptionMap.Column... selective);

    List<InstructionOptionMap> selectByExampleWithRowbounds(InstructionOptionMapExample example, RowBounds rowBounds);

    InstructionOptionMap selectByPrimaryKey(String instructionOption);

    InstructionOptionMap selectByPrimaryKeySelective(@Param("instructionOption") String instructionOption, @Param("selective") InstructionOptionMap.Column... selective);

    InstructionOptionMap selectOneByExample(InstructionOptionMapExample example);

    InstructionOptionMap selectOneByExampleSelective(@Param("example") InstructionOptionMapExample example, @Param("selective") InstructionOptionMap.Column... selective);

    int updateByExample(@Param("record") InstructionOptionMap record, @Param("example") InstructionOptionMapExample example);

    int updateByExampleSelective(@Param("record") InstructionOptionMap record, @Param("example") InstructionOptionMapExample example,
            @Param("selective") InstructionOptionMap.Column... selective);
}