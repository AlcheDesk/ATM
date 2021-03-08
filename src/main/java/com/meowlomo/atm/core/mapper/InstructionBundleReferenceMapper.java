package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionBundle;
import com.meowlomo.atm.core.model.InstructionBundleExample;

public interface InstructionBundleReferenceMapper {

    long countByExample(InstructionBundleExample example);

    List<InstructionBundle> selectByExample(InstructionBundleExample example);

    List<InstructionBundle> selectByExampleSelective(@Param("example") InstructionBundleExample example, @Param("selective") InstructionBundle.Column... selective);

    List<InstructionBundle> selectByExampleWithRowbounds(InstructionBundleExample example, RowBounds rowBounds);

    InstructionBundle selectByPrimaryKey(Long id);

    InstructionBundle selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionBundle.Column... selective);

    InstructionBundle selectOneByExample(InstructionBundleExample example);

    InstructionBundle selectOneByExampleSelective(@Param("example") InstructionBundleExample example, @Param("selective") InstructionBundle.Column... selective);

}