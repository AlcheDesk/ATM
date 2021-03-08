package com.meowlomo.atm.report.result.service;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.Response;

public interface ProjectReportWithFreeMarkerService {
    String getHtmlProjectReport(Long projectId, String templateName);

    Response getPDFProjectReport(Long projectId, String templateName) throws UnsupportedEncodingException;
}
