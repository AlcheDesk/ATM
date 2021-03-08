package com.meowlomo.atm.core.service.filter;

import java.util.List;

import com.meowlomo.atm.core.model.RunSetTestCaseLink;

public interface RunSetTestCaseLinkContentFilterService {
    List<RunSetTestCaseLink> expandRunSetTestCaseLink(List<RunSetTestCaseLink> runSetTestCaseLinks);
}
