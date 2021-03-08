package com.meowlomo.atm.core.service.exportor.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meowlomo.atm.core.model.Project;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.service.exportor.ProjectExportor;
import com.meowlomo.atm.core.service.exportor.TestCaseExportor;
import com.meowlomo.atm.core.service.referrence.TestCaseReferenceService;

@Service
public class ProjectExportorImpl implements ProjectExportor {

    private final Logger logger = LoggerFactory.getLogger(ProjectExportorImpl.class);

    @Autowired
    private TestCaseExportor testCaseExportor;
    @Autowired
    private TestCaseReferenceService testCaseReferenceService;

    @Override
    public ByteArrayOutputStream exportProjectToExcel(Project fullProject) {
        ClassLoader classLoader = getClass().getClassLoader();
        File projectExcelTemplate = new File(
                classLoader.getResource("templates/meowlomo-atm-project-template.xlsx").getFile());
        InputStream projectInputSteam = getClass().getResourceAsStream("/templates/meowlomo-atm-project-template.xlsx");
        Workbook projectWorkbook = null;
        try {
            // if we have a template
            if (projectExcelTemplate.exists()) {
                projectWorkbook = new XSSFWorkbook(new FileInputStream(projectExcelTemplate));
            }
            if (projectWorkbook == null) {
                projectWorkbook = new XSSFWorkbook(projectInputSteam);
            }
            CreationHelper helper = projectWorkbook.getCreationHelper();
            // create the test case sheet
            Sheet projectSheet = projectWorkbook.getSheet("TestCases");
            List<String[]> data = this.generateTestCaseData(fullProject);
            for (int rowCount = 0; rowCount < data.size(); rowCount++) {
                Row row = projectSheet.createRow(rowCount + 1);
                String[] lineContent = data.get(rowCount);
                for (int i = 0; i < lineContent.length; i++) {
                    row.createCell(i).setCellValue(helper.createRichTextString(lineContent[i]));
                }
            }
            projectWorkbook = this.generateTestCaseSheet(projectWorkbook, fullProject);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            projectWorkbook.write(baos);
            projectWorkbook.close();
            logger.info("exporting execel file instream for project " + fullProject.getId());
            return baos;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> generateTestCaseData(Project fullProject) {
        List<String[]> returnList = new ArrayList<String[]>();
        List<TestCase> testCases = fullProject.getTestCases();
        // order testCase
        orderTestCase(testCases);
        for (int i = 0; i < testCases.size(); i++) {
            ArrayList<String> fields = new ArrayList<String>();
            TestCase testCase = testCases.get(i);
            fields.add(testCase.getId().toString());
            fields.add(testCase.getName());
            fields.add(testCase.getType());
            fields.add(testCase.getComment());
            String[] strings = fields.stream().toArray(String[]::new);
            returnList.add(strings);
        }
        return returnList;
    }

    private Workbook generateTestCaseSheet(Workbook projectWorkbook, Project fullProject) {

        // generate the sheets for test cases
        List<TestCase> testCases = fullProject.getTestCases();
        // order testCase
        orderTestCase(testCases);
        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            TestCase fullTestCase = testCaseReferenceService.selectByPrimaryKey(testCase.getId());
            Sheet newTestCaseSheet = projectWorkbook.cloneSheet(projectWorkbook.getSheetIndex("TestCaseTemp"));
            String testCaseSheetName = testCase.getName();
            Random random = new Random();
            // if sheet name length over 31,sheet function will substring 31 and then lead
            // to duplication exception,so filter the sheet name
            if (testCase.getName().length() > 29) {
                testCaseSheetName = testCase.getName().substring(0, 29) + '_' + random.nextInt(9);
            }
            projectWorkbook.setSheetName(projectWorkbook.getSheetIndex(newTestCaseSheet), testCaseSheetName);
            testCaseExportor.exportTestCaseToExcelSheet(newTestCaseSheet, fullTestCase);
        }
        projectWorkbook.removeSheetAt(projectWorkbook.getSheetIndex("TestCaseTemp"));
        return projectWorkbook;
    }

    private List<TestCase> orderTestCase(List<TestCase> testCases) {
        // default id desc
        Comparator<TestCase> netTypeComparator = new Comparator<TestCase>() {
            @Override
            public int compare(TestCase o1, TestCase o2) {
                return (int) (o2.getId() - o1.getId());
            }
        };

        Collections.sort(testCases, netTypeComparator);
        return testCases;
    }
}
