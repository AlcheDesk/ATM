package com.meowlomo.atm.core.service.util.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.TestCaseShareFolderMapper;
import com.meowlomo.atm.core.mapper.TestCaseShareFolderTestCaseLinkMapper;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseShareFolder;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.TestCaseShareFolderUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestCaseShareFolderUtilServiceImpl implements TestCaseShareFolderUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private TestCaseShareFolderMapper testCaseShareFolderMapper;
    @Autowired
    private TestCaseShareFolderTestCaseLinkMapper testCaseShareFolderTestCaseLinkMapper;

    @Override
    public TestCaseShareFolder copyByPrimaryId(Long id, boolean nameIncrement) {
        TestCaseShareFolder record = testCaseShareFolderMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewTestCaseShareFolderNameForCopy(finalName);
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = testCaseShareFolderMapper.insert(record);
            if (insertResult == 1) {
                boolean finalResult = true;
                // check next level
                List<TestCase> nextLevelRecords = record.getTestCases();
                // copy the next level one by one
                for (TestCase nextLevelRecord : nextLevelRecords) {
                    TestCaseShareFolderTestCaseLink linkRecord = new TestCaseShareFolderTestCaseLink();
                    linkRecord.setTestCaseShareFolderId(record.getId());
                    linkRecord.setTestCaseId(nextLevelRecord.getId());
                    if (testCaseShareFolderTestCaseLinkMapper.insertSelective(linkRecord) == 0) {
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
