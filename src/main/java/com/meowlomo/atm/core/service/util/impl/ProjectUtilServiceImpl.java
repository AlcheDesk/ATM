package com.meowlomo.atm.core.service.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.ElementMapper;
import com.meowlomo.atm.core.mapper.InstructionMapper;
import com.meowlomo.atm.core.mapper.ProjectMapper;
import com.meowlomo.atm.core.mapper.ProjectReferenceMapper;
import com.meowlomo.atm.core.mapper.TestCaseReferenceMapper;
import com.meowlomo.atm.core.model.Application;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.service.util.ApplicationUtilService;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.ProjectUtilService;
import com.meowlomo.atm.core.service.util.TestCaseUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectUtilServiceImpl implements ProjectUtilService {

    @Autowired
    private ApplicationUtilService applicationUtilService;
    @Autowired
    private DataNameUtilService dataNameUtilService;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private InstructionMapper instructionMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectReferenceMapper projectReferenceMapper;
    @Autowired
    private TestCaseReferenceMapper testCaseReferenceMapper;
    @Autowired
    private TestCaseUtilService testCaseUtilService;

    @Override
    public Project copyByPrimaryId(Long id) {
        /*
         * fetch the project from the project queue
         */

        // check if we need to copy the project
        if (id == null) { 
            return null; 
        }

        // set default nameIncrement is true
        boolean nameIncrement = true;
        // Project project = RuntimeVariables.COPY_PROJECT_QUEUE.poll();
        Project record = projectReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewProjectNameForCopy(finalName);
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = projectMapper.insert(record);
            if (insertResult == 1) {
                boolean finalResult = true;
                // check next level
                Map<String, Map<Long, Long>> refElementIdPackageMap = new HashMap<String, Map<Long, Long>>();
                refElementIdPackageMap.put("refApplicationIdMap", new HashMap<Long, Long>());
                refElementIdPackageMap.put("refSectionIdMap", new HashMap<Long, Long>());
                refElementIdPackageMap.put("refElementIdMap", new HashMap<Long, Long>());
                // applications
                List<Application> applications = record.getApplications();
                for (Application nextLevelRecord : applications) {
                    if (applicationUtilService.copyByPrimaryIdForPorject(refElementIdPackageMap, nextLevelRecord.getId(), false, record.getId()) == null) {
                        finalResult = false;
                        break;
                    }
                }
                if (!finalResult) { return null; }
                // elements
                List<Element> elements = record.getElements();
                for (Element nextLevelRecord : elements) {

                    // get old element id
                    Long oldElementId = null;
                    if (nextLevelRecord.getId() != null) {
                        oldElementId = nextLevelRecord.getId();
                    }

                    nextLevelRecord.setProjectId(record.getId());
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
                // testCases
                // get the map of refTestCaseId
                Map<Long, Long> reftestCaseIdMap = new HashMap<Long, Long>();
                List<TestCase> testCases = record.getTestCases();
                for (TestCase nextLevelRecord : testCases) {
                    if (testCaseUtilService.copyByPrimaryIdForProject(refElementIdPackageMap, reftestCaseIdMap, nextLevelRecord.getId(), false, record.getId()) == null) {
                        finalResult = false;
                        break;
                    }
                }
                // change ref test case id from instruction
                Project newRecord = projectReferenceMapper.selectByPrimaryKey(record.getId());
                List<TestCase> newTestCases = newRecord.getTestCases();
                for (TestCase newTestCase : newTestCases) {
                    TestCase newTestCaseRef = testCaseReferenceMapper.selectByPrimaryKey(newTestCase.getId());
                    List<Instruction> newInstructions = newTestCaseRef.getInstructions();
                    for (Instruction newInstruction : newInstructions) {
                        if (newInstruction.getRefTestCaseId() != null) {
                            if (reftestCaseIdMap != null) {
                                if (reftestCaseIdMap.get(newInstruction.getRefTestCaseId()) != null) {
                                    Long value = (Long) reftestCaseIdMap.get(newInstruction.getRefTestCaseId());
                                    newInstruction.setRefTestCaseId(value);
                                    instructionMapper.updateByPrimaryKey(newInstruction);
                                }
                            }
                        }
                    }
                }
                if (!finalResult) { return null; }
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
