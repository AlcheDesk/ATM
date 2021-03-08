package com.meowlomo.atm.core.mapper;

import com.meowlomo.atm.core.model.InstructionTargetMap;
import com.meowlomo.atm.core.model.InstructionTargetMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface InstructionTargetMapMapper {
    long countByExample(InstructionTargetMapExample example);

    int deleteByExample(InstructionTargetMapExample example);

    int insert(InstructionTargetMap record);

    int insertSelective(@Param("record") InstructionTargetMap record, @Param("selective") InstructionTargetMap.Column... selective);

    List<InstructionTargetMap> selectByExample(InstructionTargetMapExample example);

    List<InstructionTargetMap> selectByExampleSelective(@Param("example") InstructionTargetMapExample example, @Param("selective") InstructionTargetMap.Column... selective);

    List<InstructionTargetMap> selectByExampleWithRowbounds(InstructionTargetMapExample example, RowBounds rowBounds);

    InstructionTargetMap selectOneByExample(InstructionTargetMapExample example);

    InstructionTargetMap selectOneByExampleSelective(@Param("example") InstructionTargetMapExample example, @Param("selective") InstructionTargetMap.Column... selective);

    int updateByExample(@Param("record") InstructionTargetMap record, @Param("example") InstructionTargetMapExample example);

    int updateByExampleSelective(@Param("record") InstructionTargetMap record, @Param("example") InstructionTargetMapExample example,
            @Param("selective") InstructionTargetMap.Column... selective);
}