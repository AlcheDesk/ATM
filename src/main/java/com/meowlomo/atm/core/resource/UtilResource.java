package com.meowlomo.atm.core.resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.annotation.LogUserActivity;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.custom.XmlEnvolop;
import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;
import com.meowlomo.atm.core.resource.exception.CustomForbiddenException;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.atm.core.resource.exception.CustomNotAllowedException;
import com.meowlomo.atm.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.atm.core.resource.exception.CustomNotFoundException;
import com.meowlomo.atm.core.resource.exception.CustomNotSupportedException;
import com.meowlomo.atm.core.resource.exception.CustomServiceUnavailableException;
import com.meowlomo.atm.core.resource.model.MeowlomoErrorResponse;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.service.base.RunService;
import com.meowlomo.atm.core.service.base.RunSetResultService;
import com.meowlomo.atm.core.service.util.RunSetResultUtilService;
import com.meowlomo.atm.core.service.util.RunUtilService;
import com.meowlomo.atm.util.XmlUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/utils")
@Api(value = "util resources", produces = "application/json")
public class UtilResource {

    private final Logger logger = LoggerFactory.getLogger(UtilResource.class);

    @Autowired
    private XmlUtil xmlUtil;
    
    @Autowired
    private RunUtilService runUtilService;
    
    @Autowired
    private RunSetResultUtilService runSetResultUtilService;

    @Autowired
    private RunService runService;

    @Autowired
    private RunSetResultService runSetResultService;

    private static final String ERROR_TYPE = "UTIL";

    @POST
    @LogUserActivity
    @Path("/xml/validate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "validate xml with xsd or dtd schema. Version 1 - (version in URL)", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "missing xml content body for validation or missing xml schema for validation。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse ValidateXmlWithXSD(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, XmlEnvolop record) throws Exception {
        logger.info("received xml validate call");
        try {
            String xmlUrlString = record.getXml();
            String xsdUrlString = record.getXsd();
            String dtdUrlString = record.getDtd();

            String xmlString = java.net.URLDecoder.decode(xmlUrlString, "UTF-8");
            String xsdString = java.net.URLDecoder.decode(xsdUrlString, "UTF-8");
            String dtdString = java.net.URLDecoder.decode(dtdUrlString, "UTF-8");

            logger.info("reviced xml to validate =>" + xmlString);
            logger.info("reviced xsd to validate =>" + xsdString);
            logger.info("reviced dtd to validate =>" + dtdString);

            // get the data from the request
            if (xmlString == null) {
                UUID exuuid = UUID.randomUUID();
                String message = "missing xml content body for validation.";
                String code = ERROR_TYPE + "01";
                logger.error(message);
                throw new CustomBadRequestException(null, message, null, code, exuuid);
            }

            if (dtdString == null && xsdString == null) {
                UUID exuuid = UUID.randomUUID();
                String message = "missing xml schema for validation.";
                String code = ERROR_TYPE + "01";
                logger.error(message);
                throw new CustomBadRequestException(null, message, null, code, exuuid);
            }

            String schemaString = xsdString != null ? xsdString : dtdString;
            InputStream xmlInputStream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8.name()));
            InputStream schemaInputStream = new ByteArrayInputStream(
                    schemaString.getBytes(StandardCharsets.UTF_8.name()));
            MeowlomoErrorResponse errorResponse = new MeowlomoErrorResponse();
            boolean validateResult = true;
            try {
                validateResult = xmlUtil.validateWithXSD(schemaInputStream, xmlInputStream);
            }
            catch (Exception e) {
                validateResult = false;
                errorResponse.setMessage(e.getMessage());
            }

            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("validateResult", validateResult);
            return new MeowlomoResponse(metadata, record, errorResponse);

        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // =====Termination Method Start=====

    /**
     * Delete by ID.
     *
     * @param id
     *            the id
     * @return the meowlomo response
     */
    @DELETE
    @LogUserActivity
    @Path("/termination/runs/{runId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "终止Job", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "job uuid", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse TerminationRun(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("runId") Long runId) {
        logger.info("received runs termination by id call " + uriInfo.getPath());
        try {
            // select the record first
            Run record = runService.selectByPrimaryKey(runId);
            if (record == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + runId + " does not exists.";
                String message = "不存在ID为" + runId + "的对象，无法终止。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            Object deleteResult = runUtilService.terminateRun(runId);
            if (deleteResult == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with ID = " + runId + " does not terminated successfully.";
                String message = "终止失败。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomServiceUnavailableException(null, message, trace, code, exuuid);
            }
            else{
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, deleteResult, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "CORE03SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Delete by ID.
     *
     * @param id
     *            the id
     * @return the meowlomo response
     */
    @DELETE
    @LogUserActivity
    @Path("/termination/runSetResults/{runSetResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "终止Job", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "job uuid", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse TerminationRunSetResult(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("runSetResultId") Long runSetResultId) {
        logger.info("received runSetResults termination by id call " + uriInfo.getPath());
        try {
            // select the record first
            RunSetResult record = runSetResultService.selectByPrimaryKey(runSetResultId);
            if (record == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with UUID = " + runSetResultId + " does not exists.";
                String message = "不存在ID为" + runSetResultId + "的对象，无法终止。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomNotFoundException(null, message, trace, code, exuuid);
            }
            Object deleteResult = runSetResultUtilService.terminateRunSetResult(runSetResultId);
            if (deleteResult == null) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " resource with ID = " + runSetResultId + " does not terminated successfully.";
                String message = "终止失败。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "CORE01DEL";
                logger.error(trace, httpServletRequest.getContextPath());
                throw new CustomServiceUnavailableException(null, message, trace, code, exuuid);
            }
            else{
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, deleteResult, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "CORE03SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // =====Termination Method End=====
}
