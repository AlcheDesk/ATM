package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;

public interface ModuleMapper {
    long countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Module record);

    int insertSelective(@Param("record") Module record, @Param("selective") Module.Column... selective);

    int logicalDeleteByExample(@Param("example") ModuleExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Module> selectByExample(ModuleExample example);

    List<Module> selectByExampleSelective(@Param("example") ModuleExample example, @Param("selective") Module.Column... selective);

    List<Module> selectByExampleWithRowbounds(ModuleExample example, RowBounds rowBounds);

    Module selectByPrimaryKey(Long id);

    Module selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Module.Column... selective);

    Module selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Module selectOneByExample(ModuleExample example);

    Module selectOneByExampleSelective(@Param("example") ModuleExample example, @Param("selective") Module.Column... selective);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example, @Param("selective") Module.Column... selective);

    int updateByPrimaryKey(Module record);

    int updateByPrimaryKeySelective(@Param("record") Module record, @Param("selective") Module.Column... selective);
}