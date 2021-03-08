package com.meowlomo.atm.report.result.service;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.Response;

public interface RunSetResultReportWithFreeMarkerService {
    String getHtmlRunSetResultReport(Long runSetResultId, String templateName);

    Response getPDFRunSetResultReport(Long runSetResultId, String templateName) throws UnsupportedEncodingException;
}
