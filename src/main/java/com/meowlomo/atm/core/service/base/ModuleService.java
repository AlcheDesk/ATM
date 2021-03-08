package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;

public interface ModuleService {
    long countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Module record);

    List<Long> insertRecords(List<Module> records);

    List<Long> insertRecordsSelective(List<Module> records);

    int insertSelective(Module record);

    int logicalDeleteByExample(ModuleExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Module> selectByExample(ModuleExample example);

    List<Module> selectByExampleWithRowbounds(ModuleExample example, RowBounds rowBounds);

    Module selectByPrimaryKey(Long id);

    Module selectOneByExample(ModuleExample example);

    int updateByExample(Module record, ModuleExample example);

    int updateByExampleSelective(Module record, ModuleExample example);

    int updateByPrimaryKey(Module record);

    int updateByPrimaryKeySelective(Module record);
}