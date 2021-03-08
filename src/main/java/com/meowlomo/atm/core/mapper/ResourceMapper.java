package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Resource;
import com.meowlomo.atm.core.model.ResourceExample;

public interface ResourceMapper {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(@Param("record") Resource record, @Param("selective") Resource.Column... selective);

    List<Resource> selectByExample(ResourceExample example);

    List<Resource> selectByExampleSelective(@Param("example") ResourceExample example, @Param("selective") Resource.Column... selective);

    List<Resource> selectByExampleWithRowbounds(ResourceExample example, RowBounds rowBounds);

    Resource selectByPrimaryKey(Long id);

    Resource selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Resource.Column... selective);

    Resource selectOneByExample(ResourceExample example);

    Resource selectOneByExampleSelective(@Param("example") ResourceExample example, @Param("selective") Resource.Column... selective);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example, @Param("selective") Resource.Column... selective);

    int updateByPrimaryKey(Resource record);

    int updateByPrimaryKeySelective(@Param("record") Resource record, @Param("selective") Resource.Column... selective);
}