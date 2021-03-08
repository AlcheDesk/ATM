package com.meowlomo.atm.core.mapper;

import org.apache.ibatis.annotations.Param;

public interface DataCheckerMapper {

    long checkAPIElementNameBeforeInsert(@Param("projectId") Long projectId, @Param("elementName") String elementName);

    long checkApplicationNameBeforeInsert(@Param("projectId") Long projectId, @Param("applicationName") String applicationName);

    long checkDriverNameBeforeInsert(@Param("driverName") String driverName);

    long checkDriverPropertyNameBeforeInsert(@Param("driverPropertyName") String driverPropertyName);

    long checkElementLocatorTypeNameBeforeInsert(@Param("elementLocatorTypeName") String elementLocatorTypeName);

    long checkElementNameBeforeInsert(@Param("sectionId") Long sectionId, @Param("elementName") String elementName);

    long checkElementTypeNameBeforeInsert(@Param("elementTypeName") String elementTypeName);

    long checkExecutionLogNameBeforeInsert(@Param("executionLogName") String executionLogName);

    long checkFileNameBeforeInsert(@Param("fileName") String fileName);

    long checkFileTypeNameBeforeInsert(@Param("fileTypeName") String fileTypeName);

    long checkGroupNameBeforeInsert(@Param("groupName") String groupName);

    long checkInstructionActionNameBeforeInsert(@Param("instructionActionName") String instructionActionName);

    long checkInstructionNameBeforeInsert(@Param("instructionName") String instructionName);

    long checkInstructionOptionNameBeforeInsert(@Param("instructionOptionName") String instructionOptionName);

    long checkInstructionResultNameBeforeInsert(@Param("instructionResultName") String instructionResultName);

    long checkInstructionTypeNameBeforeInsert(@Param("instructionTypeName") String instructionTypeName);

    long checkProjectNameBeforeInsert(@Param("projectName") String projectName);

    long checkProjectTypeNameBeforeInsert(@Param("projectTypeName") String projectTypeName);

    long checkPropertyNameBeforeInsert(@Param("propertyName") String propertyName);

    long checkRunNameBeforeInsert(@Param("runName") String runName);

    long checkRunSetNameBeforeInsert(@Param("runSetName") String runSetName);

    long checkSectionNameBeforeInsert(@Param("applicationId") Long applicationId, @Param("sectionName") String sectionName);

    long checkStepLogNameBeforeInsert(@Param("stepLogName") String stepLogName);

    long checkStepLogTypeNameBeforeInsert(@Param("stepLogTypeName") String stepLogTypeName);

    long checkStorageNameBeforeInsert(@Param("storageName") String storageName);

    long checkSystemRequirementNameBeforeInsert(@Param("systemRequirementName") String systemRequirementName);

    long checkTestCaseFolderNameBeforeInsert(@Param("testCaseFolderName") String testCaseFolderName);

    long checkTestCaseFolderTypeNameBeforeInsert(@Param("testCaseFolderTypeName") String testCaseFolderTypeName);

    long checkTestCaseNameForProjectBeforeInsert(@Param("projectId") Long projectId, @Param("tesCaseName") String tesCaseName);

    long checkTestCaseNameForRunSetBeforeInsert(@Param("runSetId") Long runSetId, @Param("tesCaseName") String tesCaseName);

    long checkTestCaseNameForTestCaseFolderBeforeInsert(@Param("testCaseFolderId") Long testCaseFolderId, @Param("tesCaseName") String tesCaseName);

    long checkTestCaseOptionNameBeforeInsert(@Param("testCaseOptionName") String testCaseOptionName);
}
