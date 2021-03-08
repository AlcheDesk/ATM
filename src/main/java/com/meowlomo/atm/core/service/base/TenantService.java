package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Tenant;
import com.meowlomo.atm.core.model.TenantExample;

public interface TenantService {
    long countByExample(TenantExample example);

    int deleteByExample(TenantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tenant record);

    List<Long> insertRecords(List<Tenant> records);

    List<Long> insertRecordsSelective(List<Tenant> records);

    int insertSelective(Tenant record);

    List<Tenant> selectByExample(TenantExample example);

    List<Tenant> selectByExampleWithRowbounds(TenantExample example, RowBounds rowBounds);

    Tenant selectByPrimaryKey(Long id);

    Tenant selectOneByExample(TenantExample example);

    int updateByExample(Tenant record, TenantExample example);

    int updateByExampleSelective(Tenant record, TenantExample example);

    int updateByPrimaryKey(Tenant record);

    int updateByPrimaryKeySelective(Tenant record);
}