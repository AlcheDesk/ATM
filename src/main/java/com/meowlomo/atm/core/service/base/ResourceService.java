package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Resource;
import com.meowlomo.atm.core.model.ResourceExample;

public interface ResourceService {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    List<Long> insertRecords(List<Resource> records);

    List<Long> insertRecordsSelective(List<Resource> records);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    List<Resource> selectByExampleWithRowbounds(ResourceExample example, RowBounds rowBounds);

    Resource selectByPrimaryKey(Long id);

    Resource selectOneByExample(ResourceExample example);

    int updateByExample(Resource record, ResourceExample example);

    int updateByExampleSelective(Resource record, ResourceExample example);

    int updateByPrimaryKey(Resource record);

    int updateByPrimaryKeySelective(Resource record);
}