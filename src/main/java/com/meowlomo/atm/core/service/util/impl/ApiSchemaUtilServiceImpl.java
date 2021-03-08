package com.meowlomo.atm.core.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.ApiSchemaMapper;
import com.meowlomo.atm.core.model.ApiSchema;
import com.meowlomo.atm.core.service.util.ApiSchemaUtilService;
import com.meowlomo.atm.core.service.util.DataNameUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApiSchemaUtilServiceImpl implements ApiSchemaUtilService {

    @Autowired
    private ApiSchemaMapper apiSchemaMapper;
    @Autowired
    private DataNameUtilService dataNameUtilService;

    @Override
    public ApiSchema copyByPrimaryId(Long id, boolean nameIncrement) {
        ApiSchema record = apiSchemaMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewApiSchemaNameForCopy(finalName);
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = apiSchemaMapper.insert(record);
            if (insertResult == 1) {
                return record;
            }
            else {
                return null;
            }
        }
    }

}
