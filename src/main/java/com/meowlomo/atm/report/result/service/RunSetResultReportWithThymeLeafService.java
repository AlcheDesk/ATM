package com.meowlomo.atm.report.result.service;

public interface RunSetResultReportWithThymeLeafService {

    String getHtmlRunSetResultReport(Long runSetResultId, String templateName);
    // RunSetResultReport generateReportFromRunSetResult(Long runSetResultId);
}
