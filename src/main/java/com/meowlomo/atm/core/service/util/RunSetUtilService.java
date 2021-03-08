package com.meowlomo.atm.core.service.util;

import java.util.List;
import java.util.Set;

import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.external.ems.model.Job;

public interface RunSetUtilService {

    RunSet copyByPrimaryId(Long id, boolean nameIncrement);
    List<RunSetTestCaseLink> expandReferenceRunSetEntry(List<RunSetTestCaseLink> runSetTestCaseLinks);
    Set<Long> getRunSetIdsByAliasNameSet(Set<String> aliasNames);    
    Set<Long> getRunSetIdsByAliasNameSets(Set<Set<String>> aliasNames);
    Job processExecutableJobByRunSet(RunSet runSet, Job job);
}
