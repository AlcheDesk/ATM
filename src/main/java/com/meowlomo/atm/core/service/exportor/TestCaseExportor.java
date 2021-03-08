package com.meowlomo.atm.core.service.exportor;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.meowlomo.atm.core.model.TestCase;

public interface TestCaseExportor {

    ByteArrayOutputStream exportTestCaseToExcel(TestCase testCase);

    ByteArrayOutputStream exportTestCasesToExcel(List<TestCase> testCases);

    Sheet exportTestCaseToExcelSheet(Sheet testCaseSheet, TestCase testCase);
}
