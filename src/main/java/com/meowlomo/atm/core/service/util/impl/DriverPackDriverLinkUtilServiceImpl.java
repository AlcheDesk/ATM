package com.meowlomo.atm.core.service.util.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.DriverPackDriverLinkMapper;
import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackDriverLink;
import com.meowlomo.atm.core.model.DriverPackDriverLinkExample;
import com.meowlomo.atm.core.service.base.DriverPackService;
import com.meowlomo.atm.core.service.base.DriverService;
import com.meowlomo.atm.core.service.referrence.DriverPackReferenceService;
import com.meowlomo.atm.core.service.util.DriverPackDriverLinkUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DriverPackDriverLinkUtilServiceImpl implements DriverPackDriverLinkUtilService{

    @Autowired
    DriverPackDriverLinkMapper driverPackDriverLinkMapper;
    
    @Autowired
    DriverPackReferenceService driverPackReferenceService;
    
    @Autowired
    DriverPackService driverPackService;
    
    @Autowired
    DriverService driverService;
    
    @Override
    public boolean isIgnoredWebBrowserLink(DriverPackDriverLink record) {
        //get the id of the driver
        Long driverId = record.getDriverId();
        Long driverpackId = record.getDriverPackId();
        if (driverId == null || driverpackId == null) {
            return false;
        }
        else {
            //check the driver type
            Driver driver = driverService.selectByPrimaryKey(driverId);
            if (driver.getType().equals("WebBrowser")) {
                //check if the pack has a web browser already
                DriverPack driverPack = driverPackReferenceService.selectByPrimaryKey(driverpackId);
                //loop the drivers
                List<Driver> drivers = driverPack.getDrivers();
                for (int i = 0; i < drivers.size(); i++) {
                    Driver temp = drivers.get(i);
                    if (temp.getType().equals("WebBrowser")) {
                        return false;
                    }
                }               
            }
            return true;
        }
    }

    @Override
    public void removeInvalidLinks() {
        //get all driver packs
        DriverPackDriverLinkExample example = new DriverPackDriverLinkExample();
        example.createCriteria().andIdIsNotNull();
        List<DriverPackDriverLink> driverPackDriverLinks = driverPackDriverLinkMapper.selectByExample(example);
        //loop through the links 
        for (int linkCount = 0; linkCount < driverPackDriverLinks.size(); linkCount++) {
            Long driverPackId = driverPackDriverLinks.get(linkCount).getDriverPackId();
            DriverPack driverPackRef = driverPackReferenceService.selectByPrimaryKey(driverPackId);
            List<Driver> drivers = driverPackRef.getDrivers();
            boolean foundTheFirst = false;
            for (int i = 0; i < drivers.size(); i++) {
                Driver driver = drivers.get(i);
                if (driver.getType().equals("WebBrowser") && !foundTheFirst) { //set the flag to true
                    if (!foundTheFirst) { //the first one
                        foundTheFirst = true;
                    }
                    else {
                        //first found, remove the link
                        Long driverId = driver.getId();
                        DriverPackDriverLinkExample linkExample = new DriverPackDriverLinkExample();
                        linkExample.createCriteria().andDriverIdEqualTo(driverId).andDriverPackIdEqualTo(driverPackId);
                        driverPackDriverLinkMapper.deleteByExample(linkExample);
                    }
                }
            }
        }
        
    }

}
