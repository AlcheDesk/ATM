package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Status;
import com.meowlomo.atm.core.model.StatusExample;

public interface StatusMapper {
    long countByExample(StatusExample example);

    int deleteByExample(StatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Status record);

    int insertSelective(@Param("record") Status record, @Param("selective") Status.Column... selective);

    List<Status> selectByExample(StatusExample example);

    List<Status> selectByExampleSelective(@Param("example") StatusExample example, @Param("selective") Status.Column... selective);

    List<Status> selectByExampleWithRowbounds(StatusExample example, RowBounds rowBounds);

    Status selectByPrimaryKey(Long id);

    Status selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Status.Column... selective);

    Status selectOneByExample(StatusExample example);

    Status selectOneByExampleSelective(@Param("example") StatusExample example, @Param("selective") Status.Column... selective);

    int updateByExample(@Param("record") Status record, @Param("example") StatusExample example);

    int updateByExampleSelective(@Param("record") Status record, @Param("example") StatusExample example, @Param("selective") Status.Column... selective);

    int updateByPrimaryKey(Status record);

    int updateByPrimaryKeySelective(@Param("record") Status record, @Param("selective") Status.Column... selective);
}