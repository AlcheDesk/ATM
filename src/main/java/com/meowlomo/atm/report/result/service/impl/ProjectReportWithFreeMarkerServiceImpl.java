package com.meowlomo.atm.report.result.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.service.referrence.ProjectReferenceService;
import com.meowlomo.atm.report.freemaker.FreeMarkerUtilService;
import com.meowlomo.atm.report.itextpdf.HTMLToPDF;
import com.meowlomo.atm.report.result.service.ProjectReportWithFreeMarkerService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectReportWithFreeMarkerServiceImpl implements ProjectReportWithFreeMarkerService {

    private final Logger logger = LoggerFactory.getLogger(ProjectReportWithFreeMarkerServiceImpl.class);

    @Autowired
    private ProjectReferenceService projectReferenceService;
    @Autowired
    private FreeMarkerUtilService freeMarkerUtilService;
    @Autowired
    private HTMLToPDF HTMLToPDF;

    @Override
    public String getHtmlProjectReport(Long projectId, String templateName) {
        // check the template by name first
        boolean tryDefault = templateName == null ? true : false;
        String tagetTemplateName = tryDefault
                ? RuntimeVariables.getProperty().get("meowlomo.atm.report.project.template.freemarker")
                : templateName;
        if (tagetTemplateName.isEmpty() && tryDefault) { // empty but check default already
            logger.error(
                    "trying to get a NULL template : " + templateName + " and default template chould't be found.");
            return null;
        }
        else if (tagetTemplateName.isEmpty() && !tryDefault) { // empty but not try default
            logger.error("trying to get a nonexsiting template : " + templateName
                    + " and default template will be used instead.");
            // try to get the default template
            String defaultTemplateName = RuntimeVariables.getProperty()
                    .get("meowlomo.atm.report.project.template.freemarker");
            tagetTemplateName = defaultTemplateName;

            if (tagetTemplateName.isEmpty()) {
                logger.error("Couldn't find the default report template for the run set result.");
                return null;
            }
        }

        Project selectRecord = projectReferenceService.selectByPrimaryKey(projectId);
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("project", selectRecord);

        String outFile = freeMarkerUtilService.fprintWithoutFile(root, tagetTemplateName);

        org.jsoup.nodes.Document doc = Jsoup.parse(outFile);
        String htmlContent = doc.outerHtml();

        if (htmlContent != null) { return htmlContent; }
        return null;

    }

    @Override
    public Response getPDFProjectReport(Long projectId, String templateName) throws UnsupportedEncodingException {
        // check the template by name first
        boolean tryDefault = templateName == null ? true : false;
        String tagetTemplateName = tryDefault
                ? RuntimeVariables.getProperty().get("meowlomo.atm.report.project.template.freemarker")
                : templateName;
        if (tagetTemplateName.isEmpty() && tryDefault) { // empty but check default already
            logger.error(
                    "trying to get a NULL template : " + templateName + " and default template chould't be found.");
            return null;
        }
        else if (tagetTemplateName.isEmpty() && !tryDefault) { // empty but not try default
            logger.error("trying to get a nonexsiting template : " + templateName
                    + " and default template will be used instead.");
            // try to get the default template
            String defaultTemplateName = RuntimeVariables.getProperty()
                    .get("meowlomo.atm.report.project.template.freemarker");
            tagetTemplateName = defaultTemplateName;

            if (tagetTemplateName.isEmpty()) {
                logger.error("Couldn't find the default report template for the run set result.");
                return null;
            }
        }

        Project selectRecord = projectReferenceService.selectByPrimaryKey(projectId);
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("project", selectRecord);
        String outFile = freeMarkerUtilService.fprintWithoutFile(root, templateName);

        InputStream in = new ByteArrayInputStream(outFile.getBytes());
        InputStream inputStream = HTMLToPDF.converteHTMLToPDF(in);

        // Retrieve the file
        Response response = null;
        ResponseBuilder builder = Response.ok(inputStream, MediaType.APPLICATION_OCTET_STREAM);
        String encodedFileName = URLEncoder.encode("projectReport.pdf", "UTF-8");
        builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        response = builder.build();

        return response;
    }
}