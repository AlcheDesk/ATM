package com.meowlomo.atm.core.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.DriverMapper;
import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.DriverUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverUtilServiceImpl implements DriverUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private DriverMapper driverMapper;

    @Override
    public Driver copyByPrimaryId(Long id, boolean nameIncrement) {
        Driver record = driverMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewDrvierNameForCopy(finalName);
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = driverMapper.insert(record);
            if (insertResult == 1) {
                return record;
            }
            else {
                return null;
            }
        }
    }

}
