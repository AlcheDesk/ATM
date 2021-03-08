package com.meowlomo.atm.core.service.exportor.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meowlomo.atm.core.model.Instruction;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.service.exportor.TestCaseExportor;

@Service
public class TestCaseExportorImpl implements TestCaseExportor {

    private final Logger logger = LoggerFactory.getLogger(TestCaseExportorImpl.class);

    @Override
    public ByteArrayOutputStream exportTestCaseToExcel(TestCase fullTestCase) {
        List<String[]> data = this.generateInstructionData(fullTestCase);
        ClassLoader classLoader = getClass().getClassLoader();
        File testExcelTemplate = new File(
                classLoader.getResource("templates/meowlomo-atm-test-case-template.xlsx").getFile());
        InputStream in = getClass().getResourceAsStream("/templates/meowlomo-atm-test-case-template.xlsx");
        Workbook testWorkbook = null;
        try {
            if (testExcelTemplate.exists()) {
                logger.info("testExcelTemplate exists");
                testWorkbook = new XSSFWorkbook(new FileInputStream(testExcelTemplate));
            }
            if (testWorkbook == null) {
                logger.info("testExcelTemplate not exists");
                testWorkbook = new XSSFWorkbook(in);
            }
            CreationHelper helper = testWorkbook.getCreationHelper();
            Sheet sheet = testWorkbook.getSheet("Instrcutions");
            for (int rowCount = 0; rowCount < data.size(); rowCount++) {
                Row row = sheet.createRow(rowCount + 1);
                String[] lineContent = data.get(rowCount);
                for (int i = 0; i < lineContent.length; i++) {
                    row.createCell(i).setCellValue(helper.createRichTextString(lineContent[i]));
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            testWorkbook.write(baos);
            testWorkbook.close();
            logger.info("exporting execel file instream for test case " + fullTestCase.getId());
            return baos;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayOutputStream exportTestCasesToExcel(List<TestCase> testCases) {
        ClassLoader classLoader = getClass().getClassLoader();
        File testCasesExcelTemplate = new File(
                classLoader.getResource("templates/meowlomo-atm-test-cases-template.xlsx").getFile());
        InputStream testCasesInputSteam = getClass()
                .getResourceAsStream("/templates/meowlomo-atm-test-cases-template.xlsx");
        Workbook testCasesWorkbook = null;
        try {
            // if we have a template
            if (testCasesExcelTemplate.exists()) {
                testCasesWorkbook = new XSSFWorkbook(new FileInputStream(testCasesExcelTemplate));
            }
            if (testCasesWorkbook == null) {
                testCasesWorkbook = new XSSFWorkbook(testCasesInputSteam);
            }
            CreationHelper helper = testCasesWorkbook.getCreationHelper();
            // create the test case sheet
            Sheet totalSheet = testCasesWorkbook.getSheet("TestCases");
            List<String[]> data = this.generateTestCasesDataWithId(testCases);
            for (int rowCount = 0; rowCount < data.size(); rowCount++) {
                Row row = totalSheet.createRow(rowCount + 1);
                String[] lineContent = data.get(rowCount);
                for (int i = 0; i < lineContent.length; i++) {
                    row.createCell(i).setCellValue(helper.createRichTextString(lineContent[i]));
                }
            }
            testCasesWorkbook = this.generateTestCasesSheetWithId(testCasesWorkbook, testCases);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            testCasesWorkbook.write(baos);
            testCasesWorkbook.close();
            logger.info("exporting execel file instream for testCases size " + testCases.size());
            return baos;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> generateInstructionData(TestCase fullTestCase) {
        List<String[]> returnList = new ArrayList<String[]>();
        List<Instruction> instructions = fullTestCase.getInstructions();
        for (int i = 0; i < instructions.size(); i++) {
            ArrayList<String> fields = new ArrayList<String>();
            Instruction instruction = instructions.get(i);
            fields.add(Long.toString(instruction.getOrderIndex()));
            fields.add(instruction.getTarget());
            fields.add(instruction.getType());
            fields.add(instruction.getAction());
            fields.add(instruction.getInput());
            fields.add(instruction.getComment());
            if (instruction.getRefTestCaseId() != null) {
                fields.add(Long.toString(instruction.getRefTestCaseId()));
            }
            else {
                fields.add("N/A");
            }
            fields.add(instruction.getStepDescription());
            fields.add(instruction.getExpectedDescription());
            String[] strings = fields.stream().toArray(String[]::new);
            returnList.add(strings);
        }
        return returnList;
    }

    @Override
    public Sheet exportTestCaseToExcelSheet(Sheet testCaseSheet, TestCase fullTestCase) {
        List<String[]> testCaseData = this.generateInstructionData(fullTestCase);
        for (int rowCount = 0; rowCount < testCaseData.size(); rowCount++) {
            Row row = testCaseSheet.createRow(rowCount + 1);
            String[] lineContent = testCaseData.get(rowCount);
            for (int i = 0; i < lineContent.length; i++) {
                row.createCell(i).setCellValue(lineContent[i]);
            }
        }
        return testCaseSheet;
    }

    private List<String[]> generateTestCasesDataWithId(List<TestCase> testCases) {
        List<String[]> returnList = new ArrayList<String[]>();
        // List<TestCase> testCases = fullProject.getTestCases();
        for (int i = 0; i < testCases.size(); i++) {
            ArrayList<String> fields = new ArrayList<String>();
            TestCase testCase = testCases.get(i);
            fields.add(testCase.getId().toString());
            fields.add(testCase.getName());
            fields.add(testCase.getType());
            fields.add(testCase.getComment());
            fields.add(testCase.getProjectName());
            String[] strings = fields.stream().toArray(String[]::new);
            returnList.add(strings);
        }
        return returnList;
    }

    private Workbook generateTestCasesSheetWithId(Workbook testCasesWorkbook, List<TestCase> fullTestCases) {

        // generate the sheets for test cases
        for (int i = 0; i < fullTestCases.size(); i++) {
            TestCase fullTestCase = fullTestCases.get(i);
            Sheet newTestCaseSheet = testCasesWorkbook.cloneSheet(testCasesWorkbook.getSheetIndex("TestCaseTemp"));
            testCasesWorkbook.setSheetName(testCasesWorkbook.getSheetIndex(newTestCaseSheet),
                    fullTestCase.getId() + "_" + fullTestCase.getName());
            this.exportTestCaseToExcelSheet(newTestCaseSheet, fullTestCase);
        }
        testCasesWorkbook.removeSheetAt(testCasesWorkbook.getSheetIndex("TestCaseTemp"));
        return testCasesWorkbook;
    }
}