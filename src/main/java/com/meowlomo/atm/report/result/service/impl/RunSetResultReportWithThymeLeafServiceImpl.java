package com.meowlomo.atm.report.result.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.Template;
import com.meowlomo.atm.core.model.TemplateExample;
import com.meowlomo.atm.core.service.base.TemplateService;
import com.meowlomo.atm.core.service.referrence.RunSetResultReferenceService;
import com.meowlomo.atm.report.result.service.RunSetResultReportWithThymeLeafService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultReportWithThymeLeafServiceImpl
        implements RunSetResultReportWithThymeLeafService {

    private final Logger logger = LoggerFactory.getLogger(RunSetResultReportWithThymeLeafServiceImpl.class);

    @Autowired
    private RunSetResultReferenceService runSetResultReferenceService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public String getHtmlRunSetResultReport(Long runSetResultId, String templateName) {
        // check the template by name first
        boolean tryDefault = templateName == null ? true : false;
        TemplateExample contentTemplateExample = new TemplateExample();
        contentTemplateExample.createCriteria()
                .andNameEqualTo(tryDefault
                        ? RuntimeVariables.getProperty().get("meowlomo.atm.report.runSetResult.template.thymeleaf")
                        : templateName);
        List<Template> templates = templateService.selectByExample(contentTemplateExample);
        if (templates.isEmpty() && tryDefault) { // empty but check default already
            logger.error(
                    "trying to get a NULL template : " + templateName + " and default template chould't be found.");
            return null;
        }
        else if (templates.isEmpty() && !tryDefault) { // empty but not try default
            logger.error("trying to get a nonexsiting template : " + templateName
                    + " and default template will be used instead.");
            // try to get the default template
            String defaultTemplateName = RuntimeVariables.getProperty()
                    .get("meowlomo.atm.report.runSetResult.template.thymeleaf");
            contentTemplateExample = new TemplateExample();
            contentTemplateExample.createCriteria().andNameEqualTo(defaultTemplateName);
            templates = templateService.selectByExample(contentTemplateExample);
            if (templates.isEmpty()) {
                logger.error("Couldn't find the default report template for the run set result.");
                return null;
            }
        }
        // get the reference run set result first
        RunSetResult fullRunSetresult = runSetResultReferenceService.selectByPrimaryKey(runSetResultId);
        List<Run> runs = fullRunSetresult.getRuns();
        Context context = new Context();
        context.setVariable("runs", runs);
        context.setVariable("runSetResult", fullRunSetresult);
        String htmlContent = templateEngine.process("db:" + templates.get(0).getName(), context);
        if (htmlContent != null) { return htmlContent; }
        return null;
    }

    // @Override
    // public RunSetResultReport generateReportFromRunSetResult(Long runSetResultId)
    // {
    // return null;
    // }

}
