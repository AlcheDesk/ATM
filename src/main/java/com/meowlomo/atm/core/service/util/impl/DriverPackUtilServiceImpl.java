package com.meowlomo.atm.core.service.util.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.DriverPackDriverLinkMapper;
import com.meowlomo.atm.core.mapper.DriverPackMapper;
import com.meowlomo.atm.core.mapper.DriverPackReferenceMapper;
import com.meowlomo.atm.core.mapper.util.DriverPackUtilMapper;
import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackDriverLink;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.DriverPackUtilService;
import com.meowlomo.atm.core.service.util.DriverTypeUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPackUtilServiceImpl implements DriverPackUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private DriverPackDriverLinkMapper driverPackDriverLinkMapper;
    @Autowired
    private DriverPackMapper driverPackMapper;
    @Autowired
    private DriverPackReferenceMapper driverPackReferenceMapper;
    @Autowired
    private DriverPackUtilMapper driverPackUtilMapper;
    @Autowired
    private DriverTypeUtilService driverTypeUtilService;

    @Override
    public DriverPack copyByPrimaryId(Long id, boolean nameIncrement) {
        DriverPack record = driverPackReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewDriverPackNameForCopy(finalName);
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = driverPackMapper.insert(record);
            if (insertResult == 1) {
                boolean finalResult = true;
                // check next level
                List<Driver> nextLevelRecords = record.getDrivers();
                // copy the next level one by one
                for (Driver nextLevelRecord : nextLevelRecords) {
                    DriverPackDriverLink linkRecord = new DriverPackDriverLink();
                    linkRecord.setDriverPackId(record.getId());
                    linkRecord.setDriverId(nextLevelRecord.getId());
                    if (driverPackDriverLinkMapper.insertSelective(linkRecord) == 0) {
                        finalResult = false;
                        break;
                    }
                }
                if (finalResult) {
                    return record;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
    }

    @Override
    public List<DriverPack> getAllDriverPackByRunSetId(Long runSetId) {
        Set<String> driverTypes = driverTypeUtilService.getDriverTypeSetByRunSetId(runSetId);
        return driverPackUtilMapper.getAllDriverPack(driverTypes);
    }

    @Override
    public List<DriverPack> getAllDriverPackByTestCaseId(Long testCaseId) {
        Set<String> driverTypes = driverTypeUtilService.getDriverTypeSetByTestCaseId(testCaseId);
        return driverPackUtilMapper.getAllDriverPack(driverTypes);
    }

    @Override
    public List<DriverPack> getDriverPackByRunSetId(Long runSetId) {
        Set<String> driverTypes = driverTypeUtilService.getDriverTypeSetByRunSetId(runSetId);
        return driverPackUtilMapper.getDriverPack(driverTypes);
    }

    @Override
    public List<DriverPack> getDriverPackByTestCaseId(Long testCaseId) {
        Set<String> driverTypes = driverTypeUtilService.getDriverTypeSetByTestCaseId(testCaseId);
        return driverPackUtilMapper.getDriverPack(driverTypes);
    }

    @Override
    public List<Driver> removeDuplicatedWebBrowerDriverForExecution(DriverPack driverPack) {
        //get the drivers from the drive pack first
        List<Driver> drivers = driverPack.getDrivers();
        if (drivers.size() == 0) {
            return drivers;
        }
        else {
            List<Driver> returnList = new ArrayList<Driver>();
            boolean foundTheFirst = false;
            for (int i = 0; i < drivers.size(); i++) {
                Driver driver = drivers.get(i);
                if (driver.getType().equals("WebBrowser")) {
                    if (!foundTheFirst) { //not found the first one
                        returnList.add(driver);
                        foundTheFirst = true;
                    }
                }
                else {
                    returnList.add(driver);
                }
            }
            return returnList;
        }
    }
}
