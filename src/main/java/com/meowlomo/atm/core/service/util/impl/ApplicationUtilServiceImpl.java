package com.meowlomo.atm.core.service.util.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.ApplicationMapper;
import com.meowlomo.atm.core.mapper.ApplicationReferenceMapper;
import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.service.util.ApplicationUtilService;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.SectionUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApplicationUtilServiceImpl implements ApplicationUtilService {

    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private ApplicationReferenceMapper applicationReferenceMapper;
    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private SectionUtilService sectionUtilService;

    @Override
    public Application copyByPrimaryId(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id, boolean nameIncrement) {
        return this.copyByPrimaryIdForPorject(refElementIdPackageMap, id, nameIncrement, null);
    }

    @Override
    public Application copyByPrimaryIdForPorject(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id,
            boolean nameIncrement, Long projectId) {
        Application record = applicationReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewApplicationNameForCopyForProject(record.getProjectId(),
                        finalName);
                record.setName(finalName);
            }
            if (projectId != null) {
                record.setProjectId(projectId);
            }
            record.setCopyFromId(id);

            // get old application id
            Long oldApplicationId = null;
            if (record.getId() != null) {
                oldApplicationId = record.getId();
            }

            long insertResult = applicationMapper.insert(record);

            // get new application id
            Long newApplicationId = null;
            if (record.getId() != null) {
                newApplicationId = record.getId();
            }
            // add refApplicationIdMap
            if (refElementIdPackageMap != null) {
                if (refElementIdPackageMap.get("refApplicationIdMap") != null) {
                    if ((oldApplicationId != null) && (newApplicationId != null)) {
                        refElementIdPackageMap.get("refApplicationIdMap").put(oldApplicationId, newApplicationId);
                    }
                }
            }

            if (insertResult == 1) {
                boolean finalResult = true;
                // check next level
                List<Section> nextLevelRecords = record.getSections();
                // copy the next level one by one
                for (Section nextLevelRecord : nextLevelRecords) {
                    if (sectionUtilService.copyByPrimaryIdForApplication(refElementIdPackageMap,nextLevelRecord.getId(), false, record.getId()) == null) {
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

}
