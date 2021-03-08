package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.ApplicationExample;

public interface ApplicationMapper {
    long countByExample(ApplicationExample example);

    int deleteByExample(ApplicationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Application record);

    int insertSelective(@Param("record") Application record, @Param("selective") Application.Column... selective);

    int logicalDeleteByExample(@Param("example") ApplicationExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Application> selectByExample(ApplicationExample example);

    List<Application> selectByExampleSelective(@Param("example") ApplicationExample example, @Param("selective") Application.Column... selective);

    List<Application> selectByExampleWithRowbounds(ApplicationExample example, RowBounds rowBounds);

    Application selectByPrimaryKey(Long id);

    Application selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Application.Column... selective);

    Application selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Application selectOneByExample(ApplicationExample example);

    Application selectOneByExampleSelective(@Param("example") ApplicationExample example, @Param("selective") Application.Column... selective);

    int updateByExample(@Param("record") Application record, @Param("example") ApplicationExample example);

    int updateByExampleSelective(@Param("record") Application record, @Param("example") ApplicationExample example, @Param("selective") Application.Column... selective);

    int updateByPrimaryKey(Application record);

    int updateByPrimaryKeySelective(@Param("record") Application record, @Param("selective") Application.Column... selective);
}