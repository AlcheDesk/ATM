package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.FileType;
import com.meowlomo.atm.core.model.FileTypeExample;

public interface FileTypeService {
    long countByExample(FileTypeExample example);

    int deleteByExample(FileTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileType record);

    List<Long> insertRecords(List<FileType> records);

    List<Long> insertRecordsSelective(List<FileType> records);

    int insertSelective(FileType record);

    List<FileType> selectByExample(FileTypeExample example);

    List<FileType> selectByExampleWithRowbounds(FileTypeExample example, RowBounds rowBounds);

    FileType selectByPrimaryKey(Long id);

    FileType selectOneByExample(FileTypeExample example);

    int updateByExample(FileType record, FileTypeExample example);

    int updateByExampleSelective(FileType record, FileTypeExample example);

    int updateByPrimaryKey(FileType record);

    int updateByPrimaryKeySelective(FileType record);
}