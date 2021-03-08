package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ApiSchema;
import com.meowlomo.atm.core.model.ApiSchemaExample;

public interface ApiSchemaMapper {
    long countByExample(ApiSchemaExample example);

    int deleteByExample(ApiSchemaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApiSchema record);

    int insertSelective(@Param("record") ApiSchema record, @Param("selective") ApiSchema.Column... selective);

    List<ApiSchema> selectByExample(ApiSchemaExample example);

    List<ApiSchema> selectByExampleSelective(@Param("example") ApiSchemaExample example, @Param("selective") ApiSchema.Column... selective);

    List<ApiSchema> selectByExampleWithRowbounds(ApiSchemaExample example, RowBounds rowBounds);

    ApiSchema selectByPrimaryKey(Long id);

    ApiSchema selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ApiSchema.Column... selective);

    ApiSchema selectOneByExample(ApiSchemaExample example);

    ApiSchema selectOneByExampleSelective(@Param("example") ApiSchemaExample example, @Param("selective") ApiSchema.Column... selective);

    int updateByExample(@Param("record") ApiSchema record, @Param("example") ApiSchemaExample example);

    int updateByExampleSelective(@Param("record") ApiSchema record, @Param("example") ApiSchemaExample example, @Param("selective") ApiSchema.Column... selective);

    int updateByPrimaryKey(ApiSchema record);

    int updateByPrimaryKeySelective(@Param("record") ApiSchema record, @Param("selective") ApiSchema.Column... selective);
}