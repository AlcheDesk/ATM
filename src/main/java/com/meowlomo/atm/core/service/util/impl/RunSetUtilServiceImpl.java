package com.meowlomo.atm.core.service.util.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.RunSetMapper;
import com.meowlomo.atm.core.mapper.RunSetReferenceMapper;
import com.meowlomo.atm.core.mapper.RunSetTestCaseLinkMapper;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.service.util.DataNameUtilService;
import com.meowlomo.atm.core.service.util.RunSetAliasNameMapUtilService;
import com.meowlomo.atm.core.service.util.RunSetUtilService;
import com.meowlomo.atm.external.ems.model.Job;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetUtilServiceImpl implements RunSetUtilService {

    @Autowired
    private DataNameUtilService dataNameUtilService;

    private final Logger logger = LoggerFactory.getLogger(RunSetUtilServiceImpl.class);
    @Autowired
    private RunSetAliasNameMapUtilService runSetAliasNameMapUtilService;
    @Autowired
    private RunSetMapper runSetMapper;
    @Autowired
    private RunSetReferenceMapper runSetReferenceMapper;
    @Autowired
    private RunSetTestCaseLinkMapper runSetTestCaseLinkMapper;

    @Override
    public RunSet copyByPrimaryId(Long id, boolean nameIncrement) {
        RunSet record = runSetReferenceMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        else {
            String finalName = record.getName();
            if (nameIncrement) {
                finalName = finalName + "_COPY";
                finalName = dataNameUtilService.getNewRunSetNameForCopy(finalName);
                record.setName(finalName);
            }
            record.setCopyFromId(id);
            long insertResult = runSetMapper.insert(record);
            if (insertResult == 1) {
                boolean finalResult = true;
                // check next level
                // testCases
                List<TestCase> testCases = record.getTestCases();
                for (TestCase nextLevelRecord : testCases) {
                    RunSetTestCaseLink linkRecord = new RunSetTestCaseLink();
                    linkRecord.setRunSetId(record.getId());
                    linkRecord.setTestCaseId(nextLevelRecord.getId());
                    if (runSetTestCaseLinkMapper.insertSelective(linkRecord) == 0) {
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
    public List<RunSetTestCaseLink> expandReferenceRunSetEntry(List<RunSetTestCaseLink> runSetTestCaseLinks) {
        List<Long> runSetIds = new ArrayList<Long>();
        for (RunSetTestCaseLink runSet : runSetTestCaseLinks) {
            runSetIds.add(runSet.getRunSetId());
        }
        // loop through the reference links
        List<Long> referencedRunSetIds = new ArrayList<Long>();
        for (RunSetTestCaseLink link : runSetTestCaseLinks) {
            if (link.getRefRunSetId() != null) {
                referencedRunSetIds.add(link.getRefRunSetId());
            }
        }

        Set<Long> runSetIdSet = new HashSet<Long>();
        runSetIdSet.addAll(runSetIds);
        runSetIdSet.addAll(referencedRunSetIds);
        RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
        linkExample.createCriteria().andRunSetIdIn(new ArrayList<Long>(runSetIdSet));
        List<RunSetTestCaseLink> links = runSetTestCaseLinkMapper.selectByExample(linkExample);
        // remove reference links
        List<RunSetTestCaseLink> returnLinks = new ArrayList<RunSetTestCaseLink>();
        for (RunSetTestCaseLink link : links) {
            if (link.getRefRunSetId() == null) {
                returnLinks.add(link);
            }
        }
        return returnLinks;
    }

    @Override
    public Set<Long> getRunSetIdsByAliasNameSet(Set<String> aliasNames) {
        return runSetAliasNameMapUtilService.getRunSetIdsFromAliasNames(aliasNames);
    }

    @Override
    public Set<Long> getRunSetIdsByAliasNameSets(Set<Set<String>> aliasNames) {
        if (aliasNames.isEmpty()) {
            return new HashSet<Long>();
        }
        Set<Long> targetRunSetIds = new HashSet<Long>();
        for (Set<String> nameSet : aliasNames) {
            targetRunSetIds.addAll(runSetAliasNameMapUtilService.getRunSetIdsFromAliasNames(nameSet));
        }
        targetRunSetIds.remove(null);
        return targetRunSetIds;
    }

    public Job processExecutableJobByRunSet(RunSet runSet, Job job) {
        if (runSet.getGroup() != null) {
            logger.info("Set group " + runSet.getGroup() + " to job to execute.");
            job.setGroup(runSet.getGroup());
        }

        if (runSet.getPriority() != null) {
            logger.info("Set priority " + runSet.getPriority() + " to job to execute.");
            job.setPriority(runSet.getPriority());
        }

        return job;
    }
}
