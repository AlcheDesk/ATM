package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Alias;
import com.meowlomo.atm.core.model.AliasExample;

public interface AliasReferenceService {

    long countByExample(AliasExample example);

    List<Alias> selectByExample(AliasExample example);

    List<Alias> selectByExampleWithRowbounds(AliasExample example, RowBounds rowBounds);

    Alias selectByPrimaryKey(Long id);
}
