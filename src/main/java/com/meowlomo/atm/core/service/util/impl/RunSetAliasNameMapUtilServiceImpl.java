package com.meowlomo.atm.core.service.util.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.util.RunSetAliasNameMapUtilMapper;
import com.meowlomo.atm.core.service.util.RunSetAliasNameMapUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetAliasNameMapUtilServiceImpl implements RunSetAliasNameMapUtilService{

    @Autowired
    private RunSetAliasNameMapUtilMapper runSetAliasNameMapUtilMapper;
    
    @Override
    public Set<Long> getRunSetIdsFromAliasNames(Set<String> aliasNames) {
        aliasNames.remove(null);
        return runSetAliasNameMapUtilMapper.getRunSetIdsFromAliasNames(aliasNames);
    }

}
