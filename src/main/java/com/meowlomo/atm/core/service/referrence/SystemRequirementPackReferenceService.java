package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.SystemRequirementPack;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;

public interface SystemRequirementPackReferenceService {

    long countByExample(SystemRequirementPackExample example);

    List<SystemRequirementPack> selectByExample(SystemRequirementPackExample example);

    List<SystemRequirementPack> selectByExampleWithRowbounds(SystemRequirementPackExample example, RowBounds rowBounds);

    SystemRequirementPack selectByPrimaryKey(Long id);

}
