package com.meowlomo.atm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.scheduler.ReferenceDataFetcher;

@Component
public class DataMappingInitListener implements ApplicationListener<ApplicationReadyEvent> {

    static final Logger logger = LoggerFactory.getLogger(DataMappingInitListener.class);

    @Autowired
    private ReferenceDataFetcher referenceDataFetcher = new ReferenceDataFetcher();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        logger.info("Initializing Database Data Mapping");

        this.referenceDataFetcher.fetchStatus();
        this.referenceDataFetcher.fetchElementType();
        this.referenceDataFetcher.fetchSystemRequirement();
        this.referenceDataFetcher.fetchInstructionAction();
        this.referenceDataFetcher.fetchElementLocatorType();
        this.referenceDataFetcher.fetchFileType();
        this.referenceDataFetcher.fetchProjectType();
        this.referenceDataFetcher.fetchProperty();
        this.referenceDataFetcher.fetchInstrcutionTypes();
        this.referenceDataFetcher.fetchTestCaseType();
        this.referenceDataFetcher.fetchColor();
        this.referenceDataFetcher.fetchInstructionOption();
        this.referenceDataFetcher.fetchTestCaseOption();
        this.referenceDataFetcher.fetchLogLevel();
        this.referenceDataFetcher.fetchStepLogTypes();
        this.referenceDataFetcher.fetchRunType();
        this.referenceDataFetcher.fetchGroup();
        this.referenceDataFetcher.fetchDriverTypes();
        this.referenceDataFetcher.fetchRunSetTypes();
        this.referenceDataFetcher.fetchSourceType();
        this.referenceDataFetcher.fetchContentType();
        this.referenceDataFetcher.fetchSystemRequirementTypes();

        logger.info("Status Map =>" + RuntimeVariables.getIdToStatusMap().toString());
        logger.info("Element Type Map =>" + RuntimeVariables.getIdToElementTypeMap().toString());
        logger.info("System Requirement Map =>" + RuntimeVariables.getIdToSystemRequirementMap().toString());
        logger.info("element Action Map =>" + RuntimeVariables.getIdToInstructionActionMap().toString());
        logger.info("Element Locator type Map =>" + RuntimeVariables.getIdToElementLocatorTypeMap().toString());
        logger.info("File Type Map =>" + RuntimeVariables.getIdToFileTypeMap().toString());
        logger.info("Project Type Map =>" + RuntimeVariables.getIdToProjectTypeMap().toString());
        logger.info("Property Map =>" + RuntimeVariables.property.toString());
        logger.info("Instruction Type Map =>" + RuntimeVariables.getInstructionTypes().toString());
        logger.info("Test Case Type Map =>" + RuntimeVariables.getTestCaseTypes().toString());
        logger.info("Color Map =>" + RuntimeVariables.getIdToColorMap().toString());
        logger.info("Instruction Option Map =>" + RuntimeVariables.getIdToInstructionOptionMap().toString());
        logger.info("Test Case Option Map =>" + RuntimeVariables.getIdToTestCaseOptionMap().toString());
        logger.info("Step Log Type Map =>" + RuntimeVariables.getIdToStepLogTypeMap().toString());
        logger.info("Log Level Map =>" + RuntimeVariables.getIdToLogLevelMap().toString());
        logger.info("Run Type Map =>" + RuntimeVariables.getIdToRunTypeMap().toString());
        logger.info("Group Map =>" + RuntimeVariables.getIdToGroupMap().toString());
        logger.info("Driver Type Map =>" + RuntimeVariables.getIdToDriverTypeMap().toString());
        logger.info("Instruction Type Object Map =>" + RuntimeVariables.getInstructionTypeObjectMap().toString());
        logger.info("Driver Type Object Map =>" + RuntimeVariables.getDriverTypeObjectMap().toString());
        logger.info("Run Set Type Map =>" + RuntimeVariables.getIdToRunSetTypeMap().toString());
        logger.info("Source Type Map =>" + RuntimeVariables.getIdToSourceTypeMap().toString());
        logger.info("Content Type Map =>" + RuntimeVariables.getIdToContentTypeMap().toString());
        logger.info("System Requirement Type Map =>" + RuntimeVariables.getIdToSystemRequirementTypeMap().toString());
    }

}
