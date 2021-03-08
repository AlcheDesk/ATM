package com.meowlomo.atm.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.SystemRequirementType;
import com.meowlomo.atm.core.model.Tenant;
import com.meowlomo.atm.notification.model.QueuedEmail;

public class RuntimeVariables {
    
    public static int DEFAULT_LIMIT = 20;
    
    public static List<String> FINAL_STATUSES = Arrays.asList("PASS", "FAIL", "ERROR", "TERMINATED", "TIMEOUT");
    public static List<String> RERUN_STATUSES = Arrays.asList("FAIL", "ERROR");

//    public static Queue<Job> JOB_QUEUE = new ConcurrentLinkedQueue<Job>();
//    public static Queue<Task> TASK_QUEUE = new ConcurrentLinkedQueue<Task>();
    public static Queue<QueuedEmail> EMAIL_QUEUE = new ConcurrentLinkedQueue<QueuedEmail>();
//    public static Queue<Project> COPY_PROJECT_QUEUE = new ConcurrentLinkedQueue<Project>();

    // DEFAULT VALUES
    public static Map<String, String> property = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

    private static Set<String> statuses = new HashSet<String>();
    private static Map<String, Long> statusToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToStatusMap = new HashMap<Long, String>();
    
    private static Map<UUID, Tenant> tenantUuidToTenantMap = new HashMap<UUID, Tenant>();
    private static Map<UUID, Long> tenantUuidToIdMap = new HashMap<UUID, Long>();
    
    private static Set<String> systemRequirements = new HashSet<String>();
    private static Map<String, Long> SystemRequirementToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToSystemRequirementMap = new HashMap<Long, String>();

    private static Set<String> elementTypes = new HashSet<String>();
    private static Map<String, Long> elementTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToElementTypeMap = new HashMap<Long, String>();

    private static Set<String> runTypes = new HashSet<String>();
    private static Map<String, Long> runTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToRunTypeMap = new HashMap<Long, String>();
    
    private static Set<String> contentTypes = new HashSet<String>();
    private static Map<String, Long> contentTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToContentTypeMap = new HashMap<Long, String>();

    private static Set<String> instructionActions = new HashSet<String>();
    private static Map<String, Long> instructionActionToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToInstructionActionMap = new HashMap<Long, String>();

    private static Set<String> elementLocatorTypes = new HashSet<String>();
    private static Map<String, Long> elementLocatorTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToElementLocatorTypeMap = new HashMap<Long, String>();

    private static Set<String> groups = new HashSet<String>();
    private static Map<String, Long> groupToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToGroupMap = new HashMap<Long, String>();

    private static Set<String> logLevels = new HashSet<String>();
    private static Map<String, Long> logLevelToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToLogLevelMap = new HashMap<Long, String>();

    private static Set<String> runSetTypes = new HashSet<String>();
    private static Map<String, Long> runSetTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToRunSetTypeMap = new HashMap<Long, String>();

    private static Set<String> testCaseTypes = new HashSet<String>();
    private static Map<String, Long> testCaseTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToTestCaseTypeMap = new HashMap<Long, String>();

    private static Set<String> fileTypes = new HashSet<String>();
    private static Map<String, Long> fileTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToFileTypeMap = new HashMap<Long, String>();

    private static Set<String> projectTypes = new HashSet<String>();
    private static Map<String, Long> projectTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToProjectTypeMap = new HashMap<Long, String>();

    private static Set<String> emailTypes = new HashSet<String>();
    private static Map<String, Long> emailTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToEmailTypeMap = new HashMap<Long, String>();

    private static Set<String> stepLogTypes = new HashSet<String>();
    private static Map<String, Long> stepLogTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToStepLogTypeMap = new HashMap<Long, String>();

    private static Set<String> instructionTypes = new HashSet<String>();
    private static Map<String, Long> instructionTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToInstructionTypeMap = new HashMap<Long, String>();
    private static Map<String, InstructionType> instructionTypeObjectMap = new HashMap<String, InstructionType>();

    private static Set<String> driverTypes = new HashSet<String>();
    private static Map<String, Long> driverTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToDriverTypeMap = new HashMap<Long, String>();
    private static Map<String, DriverType> driverTypeObjectMap = new HashMap<String, DriverType>();
    
    private static Set<String> systemRequirementTypes = new HashSet<String>();
    private static Map<String, Long> systemRequirementTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToSystemRequirementTypeMap = new HashMap<Long, String>();
    private static Map<String, SystemRequirementType> systemRequirementTypeObjectMap = new HashMap<String, SystemRequirementType>();

    private static Set<String> colors = new HashSet<String>();
    private static Map<String, Long> colorToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToColorMap = new HashMap<Long, String>();

    private static Set<String> instructionOptions = new HashSet<String>();
    private static Map<String, Long> instructionOptionToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToInstructionOptionMap = new HashMap<Long, String>();

    private static Set<String> testCaseOptions = new HashSet<String>();
    private static Map<String, Long> testCaseOptionToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToTestCaseOptionMap = new HashMap<Long, String>();

    private static Set<String> sourceTypes = new HashSet<String>();
    private static Map<String, Long> sourceTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private static Map<Long, String> idToSourceTypeMap = new HashMap<Long, String>();

    public static Map<String, String> getProperty() {
        return property;
    }

    public static void setProperty(Map<String, String> property) {
        RuntimeVariables.property = property;
    }

    public static Set<String> getStatuses() {
        return statuses;
    }

    public static void setStatuses(Set<String> statuses) {
        RuntimeVariables.statuses = statuses;
    }

    public static Map<String, Long> getStatusToIdMap() {
        return statusToIdMap;
    }

    public static void setStatusToIdMap(Map<String, Long> statusToIdMap) {
        RuntimeVariables.statusToIdMap = statusToIdMap;
    }

    public static Map<Long, String> getIdToStatusMap() {
        return idToStatusMap;
    }

    public static void setIdToStatusMap(Map<Long, String> idToStatusMap) {
        RuntimeVariables.idToStatusMap = idToStatusMap;
    }

    public static Set<String> getSystemRequirements() {
        return systemRequirements;
    }

    public static void setSystemRequirements(Set<String> systemRequirements) {
        RuntimeVariables.systemRequirements = systemRequirements;
    }

    public static Map<String, Long> getSystemRequirementToIdMap() {
        return SystemRequirementToIdMap;
    }

    public static void setSystemRequirementToIdMap(Map<String, Long> systemRequirementToIdMap) {
        SystemRequirementToIdMap = systemRequirementToIdMap;
    }

    public static Map<Long, String> getIdToSystemRequirementMap() {
        return idToSystemRequirementMap;
    }

    public static void setIdToSystemRequirementMap(Map<Long, String> idToSystemRequirementMap) {
        RuntimeVariables.idToSystemRequirementMap = idToSystemRequirementMap;
    }

    public static Set<String> getElementTypes() {
        return elementTypes;
    }

    public static void setElementTypes(Set<String> elementTypes) {
        RuntimeVariables.elementTypes = elementTypes;
    }

    public static Map<String, Long> getElementTypeToIdMap() {
        return elementTypeToIdMap;
    }

    public static void setElementTypeToIdMap(Map<String, Long> elementTypeToIdMap) {
        RuntimeVariables.elementTypeToIdMap = elementTypeToIdMap;
    }

    public static Map<Long, String> getIdToElementTypeMap() {
        return idToElementTypeMap;
    }

    public static void setIdToElementTypeMap(Map<Long, String> idToElementTypeMap) {
        RuntimeVariables.idToElementTypeMap = idToElementTypeMap;
    }

    public static Set<String> getInstructionActions() {
        return instructionActions;
    }

    public static void setInstructionActions(Set<String> instructionActions) {
        RuntimeVariables.instructionActions = instructionActions;
    }

    public static Map<String, Long> getInstructionActionToIdMap() {
        return instructionActionToIdMap;
    }

    public static void setInstructionActionToIdMap(Map<String, Long> instructionActionToIdMap) {
        RuntimeVariables.instructionActionToIdMap = instructionActionToIdMap;
    }

    public static Map<Long, String> getIdToInstructionActionMap() {
        return idToInstructionActionMap;
    }

    public static void setIdToInstructionActionMap(Map<Long, String> idToInstructionActionMap) {
        RuntimeVariables.idToInstructionActionMap = idToInstructionActionMap;
    }

    public static Set<String> getElementLocatorTypes() {
        return elementLocatorTypes;
    }

    public static void setElementLocatorTypes(Set<String> elementLocatorTypes) {
        RuntimeVariables.elementLocatorTypes = elementLocatorTypes;
    }

    public static Map<String, Long> getElementLocatorTypeToIdMap() {
        return elementLocatorTypeToIdMap;
    }

    public static void setElementLocatorTypeToIdMap(Map<String, Long> elementLocatorTypeToIdMap) {
        RuntimeVariables.elementLocatorTypeToIdMap = elementLocatorTypeToIdMap;
    }

    public static Map<Long, String> getIdToElementLocatorTypeMap() {
        return idToElementLocatorTypeMap;
    }

    public static void setIdToElementLocatorTypeMap(Map<Long, String> idToElementLocatorTypeMap) {
        RuntimeVariables.idToElementLocatorTypeMap = idToElementLocatorTypeMap;
    }

    public static Set<String> getGroups() {
        return groups;
    }

    public static void setGroups(Set<String> groups) {
        RuntimeVariables.groups = groups;
    }

    public static Map<String, Long> getGroupToIdMap() {
        return groupToIdMap;
    }

    public static void setGroupToIdMap(Map<String, Long> groupToIdMap) {
        RuntimeVariables.groupToIdMap = groupToIdMap;
    }

    public static Map<Long, String> getIdToGroupMap() {
        return idToGroupMap;
    }

    public static void setIdToGroupMap(Map<Long, String> idToGroupMap) {
        RuntimeVariables.idToGroupMap = idToGroupMap;
    }

    public static Set<String> getLogLevels() {
        return logLevels;
    }

    public static void setLogLevels(Set<String> logLevels) {
        RuntimeVariables.logLevels = logLevels;
    }

    public static Map<String, Long> getLogLevelToIdMap() {
        return logLevelToIdMap;
    }

    public static void setLogLevelToIdMap(Map<String, Long> logLevelToIdMap) {
        RuntimeVariables.logLevelToIdMap = logLevelToIdMap;
    }

    public static Map<Long, String> getIdToLogLevelMap() {
        return idToLogLevelMap;
    }

    public static void setIdToLogLevelMap(Map<Long, String> idToLogLevelMap) {
        RuntimeVariables.idToLogLevelMap = idToLogLevelMap;
    }

    public static Set<String> getRunSetTypes() {
        return runSetTypes;
    }

    public static void setRunSetTypes(Set<String> runSetTypes) {
        RuntimeVariables.runSetTypes = runSetTypes;
    }

    public static Map<String, Long> getRunSetTypeToIdMap() {
        return runSetTypeToIdMap;
    }

    public static void setRunSetTypeToIdMap(Map<String, Long> runSetTypeToIdMap) {
        RuntimeVariables.runSetTypeToIdMap = runSetTypeToIdMap;
    }

    public static Map<Long, String> getIdToRunSetTypeMap() {
        return idToRunSetTypeMap;
    }

    public static void setIdToRunSetTypeMap(Map<Long, String> idToRunSetTypeMap) {
        RuntimeVariables.idToRunSetTypeMap = idToRunSetTypeMap;
    }

    public static Set<String> getTestCaseTypes() {
        return testCaseTypes;
    }

    public static void setTestCaseTypes(Set<String> testCaseTypes) {
        RuntimeVariables.testCaseTypes = testCaseTypes;
    }

    public static Map<String, Long> getTestCaseTypeToIdMap() {
        return testCaseTypeToIdMap;
    }

    public static void setTestCaseTypeToIdMap(Map<String, Long> testCaseTypeToIdMap) {
        RuntimeVariables.testCaseTypeToIdMap = testCaseTypeToIdMap;
    }

    public static Map<Long, String> getIdToTestCaseTypeMap() {
        return idToTestCaseTypeMap;
    }

    public static void setIdToTestCaseTypeMap(Map<Long, String> idToTestCaseTypeMap) {
        RuntimeVariables.idToTestCaseTypeMap = idToTestCaseTypeMap;
    }

    public static Set<String> getFileTypes() {
        return fileTypes;
    }

    public static void setFileTypes(Set<String> fileTypes) {
        RuntimeVariables.fileTypes = fileTypes;
    }

    public static Map<String, Long> getFileTypeToIdMap() {
        return fileTypeToIdMap;
    }

    public static void setFileTypeToIdMap(Map<String, Long> fileTypeToIdMap) {
        RuntimeVariables.fileTypeToIdMap = fileTypeToIdMap;
    }

    public static Map<Long, String> getIdToFileTypeMap() {
        return idToFileTypeMap;
    }

    public static void setIdToFileTypeMap(Map<Long, String> idToFileTypeMap) {
        RuntimeVariables.idToFileTypeMap = idToFileTypeMap;
    }

    public static Set<String> getProjectTypes() {
        return projectTypes;
    }

    public static void setProjectTypes(Set<String> projectTypes) {
        RuntimeVariables.projectTypes = projectTypes;
    }

    public static Map<String, Long> getProjectTypeToIdMap() {
        return projectTypeToIdMap;
    }

    public static void setProjectTypeToIdMap(Map<String, Long> projectTypeToIdMap) {
        RuntimeVariables.projectTypeToIdMap = projectTypeToIdMap;
    }

    public static Map<Long, String> getIdToProjectTypeMap() {
        return idToProjectTypeMap;
    }

    public static void setIdToProjectTypeMap(Map<Long, String> idToProjectTypeMap) {
        RuntimeVariables.idToProjectTypeMap = idToProjectTypeMap;
    }

    public static Set<String> getEmailTypes() {
        return emailTypes;
    }

    public static void setEmailTypes(Set<String> emailTypes) {
        RuntimeVariables.emailTypes = emailTypes;
    }

    public static Map<String, Long> getEmailTypeToIdMap() {
        return emailTypeToIdMap;
    }

    public static void setEmailTypeToIdMap(Map<String, Long> emailTypeToIdMap) {
        RuntimeVariables.emailTypeToIdMap = emailTypeToIdMap;
    }

    public static Map<Long, String> getIdToEmailTypeMap() {
        return idToEmailTypeMap;
    }

    public static void setIdToEmailTypeMap(Map<Long, String> idToEmailTypeMap) {
        RuntimeVariables.idToEmailTypeMap = idToEmailTypeMap;
    }

    public static Set<String> getStepLogTypes() {
        return stepLogTypes;
    }

    public static void setStepLogTypes(Set<String> stepLogTypes) {
        RuntimeVariables.stepLogTypes = stepLogTypes;
    }

    public static Map<String, Long> getStepLogTypeToIdMap() {
        return stepLogTypeToIdMap;
    }

    public static void setStepLogTypeToIdMap(Map<String, Long> stepLogTypeToIdMap) {
        RuntimeVariables.stepLogTypeToIdMap = stepLogTypeToIdMap;
    }

    public static Map<Long, String> getIdToStepLogTypeMap() {
        return idToStepLogTypeMap;
    }

    public static void setIdToStepLogTypeMap(Map<Long, String> idToStepLogTypeMap) {
        RuntimeVariables.idToStepLogTypeMap = idToStepLogTypeMap;
    }

    public static Set<String> getInstructionTypes() {
        return instructionTypes;
    }

    public static void setInstructionTypes(Set<String> instructionTypes) {
        RuntimeVariables.instructionTypes = instructionTypes;
    }

    public static Map<String, Long> getInstructionTypeToIdMap() {
        return instructionTypeToIdMap;
    }

    public static void setInstructionTypeToIdMap(Map<String, Long> instructionTypeToIdMap) {
        RuntimeVariables.instructionTypeToIdMap = instructionTypeToIdMap;
    }

    public static Map<Long, String> getIdToInstructionTypeMap() {
        return idToInstructionTypeMap;
    }

    public static void setIdToInstructionTypeMap(Map<Long, String> idToInstructionTypeMap) {
        RuntimeVariables.idToInstructionTypeMap = idToInstructionTypeMap;
    }

    public static Map<String, InstructionType> getInstructionTypeObjectMap() {
        return instructionTypeObjectMap;
    }

    public static void setInstructionTypeObjectMap(Map<String, InstructionType> instructionTypeObjectMap) {
        RuntimeVariables.instructionTypeObjectMap = instructionTypeObjectMap;
    }

    public static Set<String> getColors() {
        return colors;
    }

    public static void setColors(Set<String> colors) {
        RuntimeVariables.colors = colors;
    }

    public static Map<String, Long> getColorToIdMap() {
        return colorToIdMap;
    }

    public static void setColorToIdMap(Map<String, Long> colorToIdMap) {
        RuntimeVariables.colorToIdMap = colorToIdMap;
    }

    public static Map<Long, String> getIdToColorMap() {
        return idToColorMap;
    }

    public static void setIdToColorMap(Map<Long, String> idToColorMap) {
        RuntimeVariables.idToColorMap = idToColorMap;
    }

    public static Set<String> getInstructionOptions() {
        return instructionOptions;
    }

    public static void setInstructionOptions(Set<String> instructionOptions) {
        RuntimeVariables.instructionOptions = instructionOptions;
    }

    public static Map<String, Long> getInstructionOptionToIdMap() {
        return instructionOptionToIdMap;
    }

    public static void setInstructionOptionToIdMap(Map<String, Long> instructionOptionToIdMap) {
        RuntimeVariables.instructionOptionToIdMap = instructionOptionToIdMap;
    }

    public static Map<Long, String> getIdToInstructionOptionMap() {
        return idToInstructionOptionMap;
    }

    public static void setIdToInstructionOptionMap(Map<Long, String> idToInstructionOptionMap) {
        RuntimeVariables.idToInstructionOptionMap = idToInstructionOptionMap;
    }

    public static Set<String> getTestCaseOptions() {
        return testCaseOptions;
    }

    public static void setTestCaseOptions(Set<String> testCaseOptions) {
        RuntimeVariables.testCaseOptions = testCaseOptions;
    }

    public static Map<String, Long> getTestCaseOptionToIdMap() {
        return testCaseOptionToIdMap;
    }

    public static void setTestCaseOptionToIdMap(Map<String, Long> testCaseOptionToIdMap) {
        RuntimeVariables.testCaseOptionToIdMap = testCaseOptionToIdMap;
    }

    public static Map<Long, String> getIdToTestCaseOptionMap() {
        return idToTestCaseOptionMap;
    }

    public static void setIdToTestCaseOptionMap(Map<Long, String> idToTestCaseOptionMap) {
        RuntimeVariables.idToTestCaseOptionMap = idToTestCaseOptionMap;
    }

    public static Set<String> getRunTypes() {
        return runTypes;
    }

    public static void setRunTypes(Set<String> runTypes) {
        RuntimeVariables.runTypes = runTypes;
    }

    public static Map<String, Long> getRunTypeToIdMap() {
        return runTypeToIdMap;
    }

    public static void setRunTypeToIdMap(Map<String, Long> runTypeToIdMap) {
        RuntimeVariables.runTypeToIdMap = runTypeToIdMap;
    }

    public static Map<Long, String> getIdToRunTypeMap() {
        return idToRunTypeMap;
    }

    public static void setIdToRunTypeMap(Map<Long, String> idToRunTypeMap) {
        RuntimeVariables.idToRunTypeMap = idToRunTypeMap;
    }

    public static Set<String> getDriverTypes() {
        return driverTypes;
    }

    public static void setDriverTypes(Set<String> driverTypes) {
        RuntimeVariables.driverTypes = driverTypes;
    }

    public static Map<String, Long> getDriverTypeToIdMap() {
        return driverTypeToIdMap;
    }

    public static void setDriverTypeToIdMap(Map<String, Long> driverTypeToIdMap) {
        RuntimeVariables.driverTypeToIdMap = driverTypeToIdMap;
    }

    public static Map<Long, String> getIdToDriverTypeMap() {
        return idToDriverTypeMap;
    }

    public static void setIdToDriverTypeMap(Map<Long, String> idToDriverTypeMap) {
        RuntimeVariables.idToDriverTypeMap = idToDriverTypeMap;
    }

    public static Map<String, DriverType> getDriverTypeObjectMap() {
        return driverTypeObjectMap;
    }

    public static void setDriverTypeObjectMap(Map<String, DriverType> driverTypeObjectMap) {
        RuntimeVariables.driverTypeObjectMap = driverTypeObjectMap;
    }

    public static Set<String> getSourceTypes() {
        return sourceTypes;
    }

    public static void setSourceTypes(Set<String> sourceTypes) {
        RuntimeVariables.sourceTypes = sourceTypes;
    }

    public static Map<String, Long> getSourceTypeToIdMap() {
        return sourceTypeToIdMap;
    }

    public static void setSourceTypeToIdMap(Map<String, Long> sourceTypeToIdMap) {
        RuntimeVariables.sourceTypeToIdMap = sourceTypeToIdMap;
    }

    public static Map<Long, String> getIdToSourceTypeMap() {
        return idToSourceTypeMap;
    }

    public static void setIdToSourceTypeMap(Map<Long, String> idToSourceTypeMap) {
        RuntimeVariables.idToSourceTypeMap = idToSourceTypeMap;
    }

    public static Map<UUID, Tenant> getTenantUuidToTenantMap() {
        return tenantUuidToTenantMap;
    }

    public static void setTenantUuidToTenantMap(Map<UUID, Tenant> tenantUuidToTenantMap) {
        RuntimeVariables.tenantUuidToTenantMap = tenantUuidToTenantMap;
    }

    public static Map<UUID, Long> getTenantUuidToIdMap() {
        return tenantUuidToIdMap;
    }

    public static void setTenantUuidToIdMap(Map<UUID, Long> tenantUuidToIdMap) {
        RuntimeVariables.tenantUuidToIdMap = tenantUuidToIdMap;
    }

    public static Set<String> getContentTypes() {
        return contentTypes;
    }

    public static void setContentTypes(Set<String> contentTypes) {
        RuntimeVariables.contentTypes = contentTypes;
    }

    public static Map<String, Long> getContentTypeToIdMap() {
        return contentTypeToIdMap;
    }

    public static void setContentTypeToIdMap(Map<String, Long> contentTypeToIdMap) {
        RuntimeVariables.contentTypeToIdMap = contentTypeToIdMap;
    }

    public static Map<Long, String> getIdToContentTypeMap() {
        return idToContentTypeMap;
    }

    public static void setIdToContentTypeMap(Map<Long, String> idToContentTypeMap) {
        RuntimeVariables.idToContentTypeMap = idToContentTypeMap;
    }

    public static Set<String> getSystemRequirementTypes() {
        return systemRequirementTypes;
    }

    public static void setSystemRequirementTypes(Set<String> systemRequirementTypes) {
        RuntimeVariables.systemRequirementTypes = systemRequirementTypes;
    }

    public static Map<String, Long> getSystemRequirementTypeToIdMap() {
        return systemRequirementTypeToIdMap;
    }

    public static void setSystemRequirementTypeToIdMap(Map<String, Long> systemRequirementTypeToIdMap) {
        RuntimeVariables.systemRequirementTypeToIdMap = systemRequirementTypeToIdMap;
    }

    public static Map<Long, String> getIdToSystemRequirementTypeMap() {
        return idToSystemRequirementTypeMap;
    }

    public static void setIdToSystemRequirementTypeMap(Map<Long, String> idToSystemRequirementTypeMap) {
        RuntimeVariables.idToSystemRequirementTypeMap = idToSystemRequirementTypeMap;
    }

    public static Map<String, SystemRequirementType> getSystemRequirementTypeObjectMap() {
        return systemRequirementTypeObjectMap;
    }

    public static void setSystemRequirementTypeObjectMap(Map<String, SystemRequirementType> systemRequirementTypeObjectMap) {
        RuntimeVariables.systemRequirementTypeObjectMap = systemRequirementTypeObjectMap;
    }
    
}
