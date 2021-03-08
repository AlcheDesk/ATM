package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Tenant;
import com.meowlomo.atm.core.model.TenantExample;

public interface TenantMapper {
    long countByExample(TenantExample example);

    int deleteByExample(TenantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tenant record);

    int insertSelective(@Param("record") Tenant record, @Param("selective") Tenant.Column... selective);

    List<Tenant> selectByExample(TenantExample example);

    List<Tenant> selectByExampleSelective(@Param("example") TenantExample example, @Param("selective") Tenant.Column... selective);

    List<Tenant> selectByExampleWithRowbounds(TenantExample example, RowBounds rowBounds);

    Tenant selectByPrimaryKey(Long id);

    Tenant selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Tenant.Column... selective);

    Tenant selectOneByExample(TenantExample example);

    Tenant selectOneByExampleSelective(@Param("example") TenantExample example, @Param("selective") Tenant.Column... selective);

    int updateByExample(@Param("record") Tenant record, @Param("example") TenantExample example);

    int updateByExampleSelective(@Param("record") Tenant record, @Param("example") TenantExample example, @Param("selective") Tenant.Column... selective);

    int updateByPrimaryKey(Tenant record);

    int updateByPrimaryKeySelective(@Param("record") Tenant record, @Param("selective") Tenant.Column... selective);
}