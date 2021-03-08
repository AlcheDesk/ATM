package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Module;
import com.meowlomo.atm.core.model.ModuleExample;

public interface ModuleReferenceService {

    long countByExample(ModuleExample example);

    List<Module> selectByExample(ModuleExample example);

    List<Module> selectByExampleWithRowbounds(ModuleExample example, RowBounds rowBounds);

    Module selectByPrimaryKey(Long id);
}
