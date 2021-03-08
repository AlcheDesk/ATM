package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.ApplicationExample;

public interface ApplicationReferenceMapper {

    long countByExample(ApplicationExample example);

    List<Application> selectByExample(ApplicationExample example);

    List<Application> selectByExampleSelective(@Param("example") ApplicationExample example, @Param("selective") Application.Column... selective);

    List<Application> selectByExampleWithRowbounds(ApplicationExample example, RowBounds rowBounds);

    Application selectByPrimaryKey(Long id);

    Application selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Application.Column... selective);

    Application selectOneByExample(ApplicationExample example);

    Application selectOneByExampleSelective(@Param("example") ApplicationExample example, @Param("selective") Application.Column... selective);
}