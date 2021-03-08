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
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.service.referrence.RunSetResultFullReferenceService;
import com.meowlomo.atm.report.freemaker.FreeMarkerUtilService;
import com.meowlomo.atm.report.itextpdf.HTMLToPDF;
import com.meowlomo.atm.report.result.service.RunSetResultReportWithFreeMarkerService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultReportWithFreeMarkerServiceImpl implements RunSetResultReportWithFreeMarkerService {

    private final Logger logger = LoggerFactory.getLogger(RunSetResultReportWithFreeMarkerServiceImpl.class);

    @Autowired
    private RunSetResultFullReferenceService runSetResultFullReferenceService;
    @Autowired
    private FreeMarkerUtilService freeMarkerUtilService;
    @Autowired
    private HTMLToPDF HTMLToPDF;

    @Override
    public String getHtmlRunSetResultReport(Long runSetResultId, String templateName) {
        // check the template by name first
        boolean tryDefault = templateName == null ? true : false;
        String tagetTemplateName = tryDefault
                ? RuntimeVariables.getProperty().get("meowlomo.atm.report.runSetResult.template.freemarker")
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
                    .get("meowlomo.atm.report.runSetResult.template.freemarker");
            tagetTemplateName = defaultTemplateName;

            if (tagetTemplateName.isEmpty()) {
                logger.error("Couldn't find the default report template for the run set result.");
                return null;
            }
        }

        RunSetResult selectRecord = runSetResultFullReferenceService.selectByPrimaryKey(runSetResultId);
//        if (selectRecord.getRuns() != null) {
//            List<Run> runs = runContentFilterService.generateComputedFields(selectRecord.getRuns());
//            for (int i = 0; i < runs.size(); i++) {
//                String lastImg = runs.get(i).getLastImg();
//                if (lastImg != null) {
//                    lastImg = lastImg.replace("\\", "/");
//                    lastImg = lastImg.replace("www", RuntimeVariables.property.get("meowlomo.atm.protocol")
//                            + RuntimeVariables.property.get("meowlomo.atm.hostname"));
//                    runs.get(i).setLastImg(lastImg);
//                }
//            }
//        }
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("runSetResult", selectRecord);

        String outFile = freeMarkerUtilService.fprintWithoutFile(root, tagetTemplateName);

        org.jsoup.nodes.Document doc = Jsoup.parse(outFile);
        // File fileStream = new File("src/main/resources/templates/page/test.html");
        // // Retrieve the file
        // Response response = null;
        // ResponseBuilder builder = Response.ok(fileStream,
        // MediaType.APPLICATION_OCTET_STREAM);
        // String encodedFileName = URLEncoder.encode("测试用例.html", "UTF-8");
        // builder.header("Content-Disposition", "attachment; filename*=UTF-8''" +
        // encodedFileName);
        // response = builder.build();

        // return response;
        String htmlContent = doc.outerHtml();

        if (htmlContent != null) { return htmlContent; }
        return null;

    }

    @Override
    public Response getPDFRunSetResultReport(Long runSetResultId, String templateName)
            throws UnsupportedEncodingException {
        // check the template by name first
        boolean tryDefault = templateName == null ? true : false;
        String tagetTemplateName = tryDefault
                ? RuntimeVariables.getProperty().get("meowlomo.atm.report.runSetResult.template.freemarker")
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
                    .get("meowlomo.atm.report.runSetResult.template.freemarker");
            tagetTemplateName = defaultTemplateName;

            if (tagetTemplateName.isEmpty()) {
                logger.error("Couldn't find the default report template for the run set result.");
                return null;
            }
        }

        RunSetResult selectRecord = runSetResultFullReferenceService.selectByPrimaryKey(runSetResultId);
//        List<Run> runs = runContentFilterService.generateComputedFields(selectRecord.getRuns());
//        for (int i = 0; i < runs.size(); i++) {
//            String lastImg = runs.get(i).getLastImg();
//            if (lastImg != null) {
//                lastImg = lastImg.replace("\\", "/");
//                lastImg = lastImg.replace("www", RuntimeVariables.property.get("meowlomo.atm.protocol")
//                        + RuntimeVariables.property.get("meowlomo.atm.hostname"));
//                runs.get(i).setLastImg(lastImg);
//            }
//        }
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("runSetResult", selectRecord);
        String outFile = freeMarkerUtilService.fprintWithoutFile(root, templateName);

        InputStream in = new ByteArrayInputStream(outFile.getBytes());
        InputStream inputStream = HTMLToPDF.converteHTMLToPDF(in);

        // String ftl = "src/main/resources/templates/ftl/test2.ftl";
        // InputStream in = new FileInputStream(new File(ftl));
        //
        // InputStream htmlInputStream = new FileInputStream(new File(ftl));
        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // int i;
        // while ((i = htmlInputStream.read()) != -1) {
        // baos.write(i);
        // }
        // String str = baos.toString();
        // System.out.println(str);
        // Template template = new Template();
        // template.setContent(str);

        // Retrieve the file
        Response response = null;
        ResponseBuilder builder = Response.ok(inputStream, MediaType.APPLICATION_OCTET_STREAM);
        String encodedFileName = URLEncoder.encode("runSetReport.pdf", "UTF-8");
        builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        response = builder.build();

        return response;
    }
}
