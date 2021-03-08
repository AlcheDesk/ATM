package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;

public interface ModuleReferenceMapper {

    long countByExample(ModuleExample example);

    List<Module> selectByExample(ModuleExample example);

    List<Module> selectByExampleSelective(@Param("example") ModuleExample example, @Param("selective") Module.Column... selective);

    List<Module> selectByExampleWithRowbounds(ModuleExample example, RowBounds rowBounds);

    Module selectByPrimaryKey(Long id);

    Module selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Module.Column... selective);

    Module selectOneByExample(ModuleExample example);

    Module selectOneByExampleSelective(@Param("example") ModuleExample example, @Param("selective") Module.Column... selective);

}