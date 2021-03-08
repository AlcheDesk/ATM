package com.meowlomo.atm.core.resource;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;
import com.meowlomo.atm.core.resource.exception.CustomForbiddenException;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.atm.core.resource.exception.CustomNotAllowedException;
import com.meowlomo.atm.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.atm.core.resource.exception.CustomNotFoundException;
import com.meowlomo.atm.core.resource.exception.CustomNotSupportedException;
import com.meowlomo.atm.core.resource.exception.CustomServiceUnavailableException;
import com.meowlomo.atm.report.result.service.ProjectReportWithFreeMarkerService;
import com.meowlomo.atm.report.result.service.RunSetResultReportWithFreeMarkerService;

import io.swagger.annotations.Api;

@Component
@Path("freeMarker/reports")
@Api(value = "export resources", produces = "application/json")
public class ReportWithFreeMarkerResources {
    private final Logger logger = LoggerFactory.getLogger(ReportWithFreeMarkerResources.class);

    private static final String ERROR_TYPE = "RWF";

    @Autowired
    private ProjectReportWithFreeMarkerService projectReportWithFreeMarkerService;
    @Autowired
    private RunSetResultReportWithFreeMarkerService runSetResultReportWithFreeMarkerService;

    @GET
    @Path("/projects/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectProjectsHTML(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("projectId") Long projectId) throws Exception {
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            String htmlContent = projectReportWithFreeMarkerService.getHtmlProjectReport(projectId,
                    queryParams.getFirst("template"));
            if (htmlContent != null) {
                Response response = null;
                // Retrieve the file
                ResponseBuilder builder = Response.ok(htmlContent, MediaType.TEXT_HTML);
                response = builder.build();
                return response;
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with projectId = " + projectId
                        + " does not exists.";
                String message = "不存在projectId为" + projectId + "的对象，无法读取。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "05F";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "CORE01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @Path("/downloadPDF/projects/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectProjectsPDF(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("projectId") Long projectId) throws Exception {
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            Response response = projectReportWithFreeMarkerService.getPDFProjectReport(projectId,
                    queryParams.getFirst("template"));
            return response;
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "CORE01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @Path("/runSetResults/{runSetResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectRunSetResultsHTML(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("runSetResultId") Long runSetResultId) throws Exception {
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            String htmlContent = runSetResultReportWithFreeMarkerService.getHtmlRunSetResultReport(runSetResultId,
                    queryParams.getFirst("template"));
            if (htmlContent != null) {
                Response response = null;
                // Retrieve the file
                ResponseBuilder builder = Response.ok(htmlContent, MediaType.TEXT_HTML);
                response = builder.build();
                return response;
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with runSetResultId = " + runSetResultId
                        + " does not exists.";
                String message = "不存在runSetResultId为" + runSetResultId + "的对象，无法读取。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "05F";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "CORE01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    @GET
    @Path("/downloadPDF/runSetResults/{runSetResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectRunSetResultsPDF(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest,
            @PathParam("runSetResultId") Long runSetResultId) throws Exception {
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            Response response = runSetResultReportWithFreeMarkerService.getPDFRunSetResultReport(runSetResultId,
                    queryParams.getFirst("template"));
            return response;
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},", this.getClass().getName(), ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "CORE01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // @GET
    // @Path("/downloadPDFWithFile/runSetResults/{id}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response selectRunSetResultsPDFWithoutFile(@PathParam("id") Long id)
    // throws Exception {
    // try {
    // RunSetResult selectRecord =
    // runSetResultFullReferenceService.selectByPrimaryKey(id);
    // List<Run> runs = selectRecord.getRuns();
    // for (int i = 0; i < runs.size(); i++) {
    // String lastImg = runs.get(i).getLastImg();
    // if (lastImg != null) {
    // lastImg = lastImg.replace("\\", "/");
    // lastImg = lastImg.replace("www",
    // RuntimeVariables.property.get("meowlomo.atm.protocol")
    // + RuntimeVariables.property.get("meowlomo.atm.hostname"));
    // runs.get(i).setLastImg(lastImg);
    // }
    // }
    // Map<String, Object> root = new HashMap<String, Object>();
    // root.put("runSetResult", selectRecord);
    // String outFile = freeMarkerUtilService.fprint("test2.ftl", root,
    // "test.html");
    // // freeMarkerUtilService.print("test.ftl", root);
    //
    // String html = "src/main/resources/templates/page/test.html";
    // String pdf = "src/main/resources/templates/page/test.pdf";
    // File pdfFile = new File(pdf);
    //
    // InputStream inputStream = freeMarkerUtilService.htmlToPDF(new
    // FileInputStream(new File(html)));
    // try {
    // if (!pdfFile.exists()) {
    // pdfFile.createNewFile();
    // }
    // }
    // catch (IOException e) {
    // //
    // }
    // IOUtil.copyCompletely(inputStream, new FileOutputStream(pdf));
    //
    // File fileStream = new File("src/main/resources/templates/page/test.pdf");
    // // InputStream in =
    // getClass().getResourceAsStream("/templates/page/test.pdf");
    //
    // // Retrieve the file
    // Response response = null;
    // ResponseBuilder builder = Response.ok(fileStream,
    // MediaType.APPLICATION_OCTET_STREAM);
    // String encodedFileName = URLEncoder.encode("测试用例.pdf", "UTF-8");
    // builder.header("Content-Disposition", "attachment; filename*=UTF-8''" +
    // encodedFileName);
    // response = builder.build();
    //
    // if (fileStream.exists()) {
    // fileStream.delete();
    // logger.info("delete file success");
    // }
    // else {
    // logger.info("file not exist");
    // }
    //
    // return response;
    // // return doc.outerHtml();
    // }
    // catch (CustomNotAuthorizedException | CustomBadRequestException |
    // CustomForbiddenException
    // | CustomNotAcceptableException | CustomNotAllowedException |
    // CustomNotFoundException
    // | CustomNotSupportedException | CustomServiceUnavailableException ex) {
    // throw ex;
    // }
    // catch (Exception ex) {
    // UUID exuuid = UUID.randomUUID();
    // String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
    // String code = ERROR_TYPE + "CORE01SYS";
    // logger.error(message, ex);
    // throw new CustomInternalServerErrorException(ex, message, ex.getMessage(),
    // code, exuuid);
    // }
    // }
}
