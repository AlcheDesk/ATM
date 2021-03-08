package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.FileType;
import com.meowlomo.atm.core.model.FileTypeExample;

public interface FileTypeMapper {
    long countByExample(FileTypeExample example);

    int deleteByExample(FileTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileType record);

    int insertSelective(@Param("record") FileType record, @Param("selective") FileType.Column... selective);

    List<FileType> selectByExample(FileTypeExample example);

    List<FileType> selectByExampleSelective(@Param("example") FileTypeExample example, @Param("selective") FileType.Column... selective);

    List<FileType> selectByExampleWithRowbounds(FileTypeExample example, RowBounds rowBounds);

    FileType selectByPrimaryKey(Long id);

    FileType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") FileType.Column... selective);

    FileType selectOneByExample(FileTypeExample example);

    FileType selectOneByExampleSelective(@Param("example") FileTypeExample example, @Param("selective") FileType.Column... selective);

    int updateByExample(@Param("record") FileType record, @Param("example") FileTypeExample example);

    int updateByExampleSelective(@Param("record") FileType record, @Param("example") FileTypeExample example, @Param("selective") FileType.Column... selective);

    int updateByPrimaryKey(FileType record);

    int updateByPrimaryKeySelective(@Param("record") FileType record, @Param("selective") FileType.Column... selective);
}