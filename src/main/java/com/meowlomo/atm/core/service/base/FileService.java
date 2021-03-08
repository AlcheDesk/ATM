package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.File;
import com.meowlomo.atm.core.model.FileExample;

public interface FileService {
    long countByExample(FileExample example, String mode);

    int deleteByExample(FileExample example, String mode);

    int deleteByPrimaryKey(Long id, String mode);

    int insert(File record, String mode);

    List<Long> insertRecords(List<File> records, String mode);

    List<Long> insertRecordsSelective(List<File> records, String mode);

    int insertSelective(File record, String mode);

    List<File> selectByExample(FileExample example, String mode);

    List<File> selectByExampleWithRowbounds(FileExample example, RowBounds rowBounds, String mode);

    File selectByPrimaryKey(Long id, String mode);

    File selectOneByExample(FileExample example, String mode);

    int updateByExample(File record, FileExample example, String mode);

    int updateByExampleSelective(File record, FileExample example, String mode);

    int updateByPrimaryKey(File record, String mode);

    int updateByPrimaryKeySelective(File record, String mode);
}