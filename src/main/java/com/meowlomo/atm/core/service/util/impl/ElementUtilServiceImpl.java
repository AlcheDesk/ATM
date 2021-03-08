package com.meowlomo.atm.core.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.ElementMapper;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.ElementUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementUtilServiceImpl implements ElementUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private ElementMapper elementMapper;

    @Override
    public Element copyByPrimaryId(Long id, boolean nameIncrement) {
        Element record = elementMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                if (record.getProjectId() != null) {
                    finalName = dataNameUtilService.getNewElementForProjectNameForCopy(record.getProjectId(),
                            finalName);
                }
                else if (record.getSectionId() != null) {
                    finalName = dataNameUtilService.getNewElementForSectionNameForCopy(record.getSectionId(),
                            finalName);
                }
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = elementMapper.insert(record);
            if (insertResult == 1) {
                return record;
            }
            else {
                return null;
            }
        }
    }

}
