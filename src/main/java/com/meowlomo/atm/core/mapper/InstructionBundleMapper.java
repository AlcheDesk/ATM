package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionBundle;
import com.meowlomo.atm.core.model.InstructionBundleExample;

public interface InstructionBundleMapper {
    long countByExample(InstructionBundleExample example);

    int deleteByExample(InstructionBundleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionBundle record);

    int insertSelective(@Param("record") InstructionBundle record, @Param("selective") InstructionBundle.Column... selective);

    int logicalDeleteByExample(@Param("example") InstructionBundleExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<InstructionBundle> selectByExample(InstructionBundleExample example);

    List<InstructionBundle> selectByExampleSelective(@Param("example") InstructionBundleExample example, @Param("selective") InstructionBundle.Column... selective);

    List<InstructionBundle> selectByExampleWithRowbounds(InstructionBundleExample example, RowBounds rowBounds);

    InstructionBundle selectByPrimaryKey(Long id);

    InstructionBundle selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionBundle.Column... selective);

    InstructionBundle selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    InstructionBundle selectOneByExample(InstructionBundleExample example);

    InstructionBundle selectOneByExampleSelective(@Param("example") InstructionBundleExample example, @Param("selective") InstructionBundle.Column... selective);

    int updateByExample(@Param("record") InstructionBundle record, @Param("example") InstructionBundleExample example);

    int updateByExampleSelective(@Param("record") InstructionBundle record, @Param("example") InstructionBundleExample example,
            @Param("selective") InstructionBundle.Column... selective);

    int updateByPrimaryKey(InstructionBundle record);

    int updateByPrimaryKeySelective(@Param("record") InstructionBundle record, @Param("selective") InstructionBundle.Column... selective);
}