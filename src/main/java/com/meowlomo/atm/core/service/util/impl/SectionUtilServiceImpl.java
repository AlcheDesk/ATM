package com.meowlomo.atm.core.service.util.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.ElementMapper;
import com.meowlomo.atm.core.mapper.SectionMapper;
import com.meowlomo.atm.core.mapper.SectionReferenceMapper;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.SectionUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SectionUtilServiceImpl implements SectionUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private SectionReferenceMapper sectionReferenceMapper;

    @Override
    public Section copyByPrimaryId(Long id, boolean nameIncrement) {
        Section record = sectionReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            Long applicationId = record.getApplicationId();
            Long projectId = record.getProjectId();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewSectionNameForCopyForApplication(applicationId, finalName);
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = sectionMapper.insert(record);
            if (insertResult == 1) {
                boolean finalResult = true;
                // check next level
                List<Element> nextLevelRecords = record.getElements();
                // copy the next level one by one
                for (Element nextLevelRecord : nextLevelRecords) {
                    nextLevelRecord.setSectionId(record.getId());
                    nextLevelRecord.setProjectId(projectId);
                    if (elementMapper.insertSelective(nextLevelRecord) == 0) {
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
    public Section copyByPrimaryId(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id, boolean nameIncrement) {
        return this.copyByPrimaryIdForApplication(refElementIdPackageMap, id, nameIncrement, null);
    }

    @Override
    public Section copyByPrimaryIdForApplication(Map<String, Map<Long, Long>> refElementIdPackageMap, Long id,
            boolean nameIncrement, Long applicationId) {
        Section record = sectionReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewSectionNameForCopyForApplication(record.getApplicationId(),
                        finalName);
                record.setName(finalName);
            }
            if (applicationId != null) {
                record.setApplicationId(applicationId);
            }
            record.setCopyFromId(id);

            // get old section id
            Long oldSectionId = null;
            if (record.getId() != null) {
                oldSectionId = record.getId();
            }

            long insertResult = sectionMapper.insert(record);

            // get new section id
            Long newSectionId = null;
            if (record.getId() != null) {
                newSectionId = record.getId();
            }
            // add refSectionIdMap
            if (refElementIdPackageMap != null) {
                if (refElementIdPackageMap.get("refSectionIdMap") != null) {
                    if ((oldSectionId != null) && (newSectionId != null)) {
                        refElementIdPackageMap.get("refSectionIdMap").put(oldSectionId, newSectionId);
                    }
                }
            }

            if (insertResult == 1) {
                boolean finalResult = true;
                // insert elements
                List<Element> elements = record.getElements();
                for (Element nextLevelRecord : elements) {
                    // get old element id
                    Long oldElementId = null;
                    if (nextLevelRecord.getId() != null) {
                        oldElementId = nextLevelRecord.getId();
                    }

                    nextLevelRecord.setSectionId(record.getId());
                    if (elementMapper.insert(nextLevelRecord) == 0) {
                        finalResult = false;
                        break;
                    }

                    // get new element id
                    Long newElementId = null;
                    if (nextLevelRecord.getId() != null) {
                        newElementId = nextLevelRecord.getId();
                    }

                    // add refelementIdMap
                    if (refElementIdPackageMap.get("refElementIdMap") != null) {
                        if ((oldElementId != null) && (newElementId != null)) {
                            refElementIdPackageMap.get("refElementIdMap").put(oldElementId, newElementId);
                        }
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
