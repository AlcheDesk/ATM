package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ApiSchema;
import com.meowlomo.atm.core.model.ApiSchemaExample;

public interface ApiSchemaService {
    long countByExample(ApiSchemaExample example);

    int deleteByExample(ApiSchemaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApiSchema record);

    List<Long> insertRecords(List<ApiSchema> records);

    List<Long> insertRecordsSelective(List<ApiSchema> records);

    int insertSelective(ApiSchema record);

    List<ApiSchema> selectByExample(ApiSchemaExample example);

    List<ApiSchema> selectByExampleWithRowbounds(ApiSchemaExample example, RowBounds rowBounds);

    ApiSchema selectByPrimaryKey(Long id);

    ApiSchema selectOneByExample(ApiSchemaExample example);

    int updateByExample(ApiSchema record, ApiSchemaExample example);

    int updateByExampleSelective(ApiSchema record, ApiSchemaExample example);

    int updateByPrimaryKey(ApiSchema record);

    int updateByPrimaryKeySelective(ApiSchema record);
}