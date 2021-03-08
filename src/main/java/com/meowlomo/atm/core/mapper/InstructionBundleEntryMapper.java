package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionBundleEntry;
import com.meowlomo.atm.core.model.InstructionBundleEntryExample;

public interface InstructionBundleEntryMapper {
    long countByExample(InstructionBundleEntryExample example);

    int deleteByExample(InstructionBundleEntryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionBundleEntry record);

    int insertSelective(@Param("record") InstructionBundleEntry record, @Param("selective") InstructionBundleEntry.Column... selective);

    int logicalDeleteByExample(@Param("example") InstructionBundleEntryExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<InstructionBundleEntry> selectByExample(InstructionBundleEntryExample example);

    List<InstructionBundleEntry> selectByExampleSelective(@Param("example") InstructionBundleEntryExample example, @Param("selective") InstructionBundleEntry.Column... selective);

    List<InstructionBundleEntry> selectByExampleWithRowbounds(InstructionBundleEntryExample example, RowBounds rowBounds);

    InstructionBundleEntry selectByPrimaryKey(Long id);

    InstructionBundleEntry selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionBundleEntry.Column... selective);

    InstructionBundleEntry selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    InstructionBundleEntry selectOneByExample(InstructionBundleEntryExample example);

    InstructionBundleEntry selectOneByExampleSelective(@Param("example") InstructionBundleEntryExample example, @Param("selective") InstructionBundleEntry.Column... selective);

    int updateByExample(@Param("record") InstructionBundleEntry record, @Param("example") InstructionBundleEntryExample example);

    int updateByExampleSelective(@Param("record") InstructionBundleEntry record, @Param("example") InstructionBundleEntryExample example,
            @Param("selective") InstructionBundleEntry.Column... selective);

    int updateByPrimaryKey(InstructionBundleEntry record);

    int updateByPrimaryKeySelective(@Param("record") InstructionBundleEntry record, @Param("selective") InstructionBundleEntry.Column... selective);
}