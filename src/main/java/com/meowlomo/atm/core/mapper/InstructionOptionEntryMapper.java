package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.InstructionOptionEntry;
import com.meowlomo.atm.core.model.InstructionOptionEntryExample;

public interface InstructionOptionEntryMapper {
    long countByExample(InstructionOptionEntryExample example);

    int deleteByExample(InstructionOptionEntryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InstructionOptionEntry record);

    int insertSelective(@Param("record") InstructionOptionEntry record, @Param("selective") InstructionOptionEntry.Column... selective);

    List<InstructionOptionEntry> selectByExample(InstructionOptionEntryExample example);

    List<InstructionOptionEntry> selectByExampleSelective(@Param("example") InstructionOptionEntryExample example, @Param("selective") InstructionOptionEntry.Column... selective);

    List<InstructionOptionEntry> selectByExampleWithRowbounds(InstructionOptionEntryExample example, RowBounds rowBounds);

    InstructionOptionEntry selectByPrimaryKey(Long id);

    InstructionOptionEntry selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") InstructionOptionEntry.Column... selective);

    InstructionOptionEntry selectOneByExample(InstructionOptionEntryExample example);

    InstructionOptionEntry selectOneByExampleSelective(@Param("example") InstructionOptionEntryExample example, @Param("selective") InstructionOptionEntry.Column... selective);

    int updateByExample(@Param("record") InstructionOptionEntry record, @Param("example") InstructionOptionEntryExample example);

    int updateByExampleSelective(@Param("record") InstructionOptionEntry record, @Param("example") InstructionOptionEntryExample example,
            @Param("selective") InstructionOptionEntry.Column... selective);

    int updateByPrimaryKey(InstructionOptionEntry record);

    int updateByPrimaryKeySelective(@Param("record") InstructionOptionEntry record, @Param("selective") InstructionOptionEntry.Column... selective);
}