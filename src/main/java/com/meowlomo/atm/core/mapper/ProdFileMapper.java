package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.File;
import com.meowlomo.atm.core.model.FileExample;

public interface ProdFileMapper {
    long countByExample(FileExample example);

    int deleteByExample(FileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(File record);

    int insertSelective(@Param("record") File record, @Param("selective") File.Column... selective);

    List<File> selectByExample(FileExample example);

    List<File> selectByExampleSelective(@Param("example") FileExample example, @Param("selective") File.Column... selective);

    List<File> selectByExampleWithRowbounds(FileExample example, RowBounds rowBounds);

    File selectByPrimaryKey(Long id);

    File selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") File.Column... selective);

    File selectOneByExample(FileExample example);

    File selectOneByExampleSelective(@Param("example") FileExample example, @Param("selective") File.Column... selective);

    int updateByExample(@Param("record") File record, @Param("example") FileExample example);

    int updateByExampleSelective(@Param("record") File record, @Param("example") FileExample example, @Param("selective") File.Column... selective);

    int updateByPrimaryKey(File record);

    int updateByPrimaryKeySelective(@Param("record") File record, @Param("selective") File.Column... selective);
}