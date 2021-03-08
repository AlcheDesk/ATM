package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.ApplicationExample;

public interface ApplicationReferenceService {

    long countByExample(ApplicationExample example);

    List<Application> selectByExample(ApplicationExample example);

    List<Application> selectByExampleWithRowbounds(ApplicationExample example, RowBounds rowBounds);

    Application selectByPrimaryKey(Long id);
}
