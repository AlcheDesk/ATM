package com.meowlomo.atm.core.service.filter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.service.filter.RunSetContentFilterService;
import com.meowlomo.atm.core.service.filter.RunSetTestCaseLinkContentFilterService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetContentFilterServiceImpl implements RunSetContentFilterService{
    
    @Autowired
    private RunSetTestCaseLinkContentFilterService runSetTestCaseLinkContentFilterService;

    @Override
    public RunSet expandReferenecingRunSetTestCaseLinkForRunSet(RunSet runSetRef) {
        // get all test case from the run set
        List<RunSetTestCaseLink> runSetTestCaseLinks = runSetRef.getRunSetTestCaseLinks();
        if (runSetTestCaseLinks == null || runSetTestCaseLinks.isEmpty()) {
            return runSetRef;
        }
        //expand the links with the referencing links
        runSetTestCaseLinks = runSetTestCaseLinkContentFilterService.expandRunSetTestCaseLink(runSetTestCaseLinks);
        // loop the instruction
        runSetRef.setRunSetTestCaseLinks(null);
        runSetRef.setRunSetTestCaseLinks(runSetTestCaseLinks);
        return runSetRef;
    }

}
