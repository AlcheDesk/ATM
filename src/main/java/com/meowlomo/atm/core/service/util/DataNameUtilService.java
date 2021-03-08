package com.meowlomo.atm.core.service.util;

public interface DataNameUtilService {

    long checkApiSchemaNameBeforeInsert(String apiSchemaName);

    long checkApplicationNameForProjectBeforeInsert(Long projectId, String applicationName);
    
    long checkDriverNameBeforeInsert(String driverName);

    long checkDriverPackNameBeforeInsert(String driverPackName);

    long checkDriverPropertyNameForDriverVendorBeforeInsert(Long driverVenderId, String driverPropertyName);

    long checkElementLocatorTypeNameBeforeInsert(String projectName);

    long checkElementNameForProjectBeforeInsert(Long projectId, String elementName);

    long checkElementNameForSectionBeforeInsert(Long sectionId, String elementName);

    long checkElementTypeNameBeforeInsert(String elementTypeName);

    long checkEmailAddressBeforeInsert(String emailAddress);

    long checkFileTypeNameBeforeInsert(String fileTypeName);

    long checkGroupNameBeforeInsert(String groupName);

    long checkInstructionActionNameBeforeInsert(String instructionActionName);

    long checkInstructionBundleNameBeforeInsert(String instructionBundleName);

    long checkInstructionOptionNameBeforeInsert(String instructionOptionName);

    long checkInstructionTypeNameBeforeInsert(String instructionTypeName);

    long checkProjectNameBeforeInsert(String projectName);

    long checkProjectTypeNameBeforeInsert(String projectTypeName);

    long checkPropertyNameBeforeInsert(String propertyName);

    long checkRunSetNameBeforeInsert(String runSetName);

    long checkRunSetTestCaseLinkBeforeInsertOrUpdate(Long runSetId, Long testCaseId, Long testCaseOverwriteId,
            Long driverPackId);

    long checkSectionNameBeforeInsert(Long applicationId, String sectionName);

    long checkSectionNameForApplicationBeforeInsert(Long applicationId, String sectionName);

    long checkStepLogTypeNameBeforeInsert(String stepLogTypeName);

    long checkSystemRequirementNameBeforeInsert(String systemRequirementName);

    long checkSystemRequirementPackBeforeInsert(String systemRequirementPackName);

    long checkTestCaseNameForProjectBeforeInsert(Long projectId, String tesCaseName);

    long checkTestCaseNameForRunSetBeforeInsert(Long runSetId, String tesCaseName);

    long checkTestCaseNameForTestCaseShareFolderBeforeInsert(Long testCaseShareFolderId, String tesCaseName);

    long checkTestCaseOptionNameBeforeInsert(String testCaseOptionName);

    long checkTestCaseOverwriteNameForTestCaseBeforeInsert(Long testCaseId, String testCaseOverwriteName);

    long checkTestCaseShareFolderNameBeforeInsert(String testCaseShareFolderName);

    String getNewApiSchemaNameForCopy(String originalApiSchemaName);

    String getNewApplicationNameForCopyForProject(Long projectId, String originalApplicationName);

    String getNewDriverPackNameForCopy(String originalDriverpackName);

    String getNewDriverPropertyNameForCopyForDriverVender(Long driverVenderId, String originalDriverPropertyName);

    String getNewDrvierNameForCopy(String originalDrvierName);

    String getNewElementForProjectNameForCopy(Long projectId, String originalElementName);

    String getNewElementForSectionNameForCopy(Long sectionId, String originalElementName);

    String getNewProjectNameForCopy(String originalProjectName);

    String getNewRunSetNameForCopy(String originalRunSetName);

    String getNewSectionNameForCopyForApplication(Long applicationId, String originalSectionName);

    String getNewTestCaseForProjectNameForCopy(Long projectId, String originalTestCaseName);

    String getNewTestCaseOverwriteForTestCaseNameForCopy(Long testCaseId, String originalTestCaseOverwriteName);

    String getNewTestCaseShareFolderNameForCopy(String originalTestCaseShareFolderName);

}
