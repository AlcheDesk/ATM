package com.meowlomo.atm.core.service.filter.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.service.base.RunSetTestCaseLinkService;
import com.meowlomo.atm.core.service.filter.RunSetTestCaseLinkContentFilterService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetTestCaseLinkContentFilterServiceImpl implements RunSetTestCaseLinkContentFilterService {

	@Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
	
    private final Logger logger = LoggerFactory.getLogger(RunSetTestCaseLinkContentFilterServiceImpl.class);
    @Autowired
    private RunSetTestCaseLinkService runSetTestCaseLinkService;

    @Override
    public List<RunSetTestCaseLink> expandRunSetTestCaseLink(List<RunSetTestCaseLink> runSetTestCaseLinks) {
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        List<RunSetTestCaseLink> expanedRunSetTestCaseLinks = new ArrayList<RunSetTestCaseLink>();
        for (int rsTsCount = 0; rsTsCount < runSetTestCaseLinks.size(); rsTsCount++) {
            RunSetTestCaseLink runSetTestCaseLink = runSetTestCaseLinks.get(rsTsCount);
            //check if the run set test case link has referencing other links
            if (runSetTestCaseLink.getRefRunSetId() != null) {
                Long refRunSetId = runSetTestCaseLink.getRefRunSetId();
                if (refRunSetId != null) {
                    RunSetTestCaseLinkExample lowerRunSetTestCaseLinkExample = new RunSetTestCaseLinkExample();
                    lowerRunSetTestCaseLinkExample.createCriteria().andRunSetIdEqualTo(refRunSetId);
                    List<RunSetTestCaseLink> tempRunSetTestCaseLinks = runSetTestCaseLinkService.selectByExample(lowerRunSetTestCaseLinkExample);
                    // expand the lower level
                    // object to string
                    String objectString = null;
                    List<RunSetTestCaseLink> runSetTestCaseLinksInLowerLevel = null;
                    try {
                        objectString = mapper.writeValueAsString(tempRunSetTestCaseLinks);
                        runSetTestCaseLinksInLowerLevel = mapper.readValue(objectString, new TypeReference<List<RunSetTestCaseLink>>() {});
                    }
                    catch (JsonParseException e) {
                    	logger.error(getClass().getName() + " ERROR : ", e);
                        e.printStackTrace();
                    }
                    catch (JsonMappingException e) {
                    	logger.error(getClass().getName() + " ERROR : ", e);
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                    	logger.error(getClass().getName() + " ERROR : ", e);
                        e.printStackTrace();
                    }
                    List<RunSetTestCaseLink> exapndedLowerRunSetTestCaseLinks = this.expandRunSetTestCaseLink(runSetTestCaseLinksInLowerLevel);
                    expanedRunSetTestCaseLinks.addAll(exapndedLowerRunSetTestCaseLinks);
                }
            }
            else {
                expanedRunSetTestCaseLinks.add(runSetTestCaseLink);
            }
        }
        return expanedRunSetTestCaseLinks;
    }
}
