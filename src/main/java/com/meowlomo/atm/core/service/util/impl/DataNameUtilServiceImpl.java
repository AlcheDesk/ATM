package com.meowlomo.atm.core.service.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.mapper.ApiSchemaMapper;
import com.meowlomo.atm.core.mapper.ApplicationMapper;
import com.meowlomo.atm.core.mapper.DriverMapper;
import com.meowlomo.atm.core.mapper.DriverPackMapper;
import com.meowlomo.atm.core.mapper.DriverPropertyMapper;
import com.meowlomo.atm.core.mapper.ElementLocatorTypeMapper;
import com.meowlomo.atm.core.mapper.ElementMapper;
import com.meowlomo.atm.core.mapper.ElementTypeMapper;
import com.meowlomo.atm.core.mapper.EmailNotificationTargetMapper;
import com.meowlomo.atm.core.mapper.FileTypeMapper;
import com.meowlomo.atm.core.mapper.GroupMapper;
import com.meowlomo.atm.core.mapper.InstructionActionMapper;
import com.meowlomo.atm.core.mapper.InstructionBundleMapper;
import com.meowlomo.atm.core.mapper.InstructionOptionMapper;
import com.meowlomo.atm.core.mapper.InstructionTypeMapper;
import com.meowlomo.atm.core.mapper.ProjectMapper;
import com.meowlomo.atm.core.mapper.ProjectTypeMapper;
import com.meowlomo.atm.core.mapper.PropertyMapper;
import com.meowlomo.atm.core.mapper.RunSetMapper;
import com.meowlomo.atm.core.mapper.RunSetTestCaseLinkMapper;
import com.meowlomo.atm.core.mapper.SectionMapper;
import com.meowlomo.atm.core.mapper.StepLogTypeMapper;
import com.meowlomo.atm.core.mapper.SystemRequirementMapper;
import com.meowlomo.atm.core.mapper.SystemRequirementPackMapper;
import com.meowlomo.atm.core.mapper.TestCaseMapper;
import com.meowlomo.atm.core.mapper.TestCaseOptionMapper;
import com.meowlomo.atm.core.mapper.TestCaseOverwriteMapper;
import com.meowlomo.atm.core.mapper.TestCaseShareFolderMapper;
import com.meowlomo.atm.core.mapper.TestCaseShareFolderTestCaseLinkMapper;
import com.meowlomo.atm.core.mapper.util.TestCaseUtilMapper;
import com.meowlomo.atm.core.model.ApiSchemaExample;
import com.meowlomo.atm.core.model.ApplicationExample;
import com.meowlomo.atm.core.model.DriverExample;
import com.meowlomo.atm.core.model.DriverPackExample;
import com.meowlomo.atm.core.model.DriverPropertyExample;
import com.meowlomo.atm.core.model.ElementExample;
import com.meowlomo.atm.core.model.ElementLocatorTypeExample;
import com.meowlomo.atm.core.model.ElementTypeExample;
import com.meowlomo.atm.core.model.EmailNotificationTargetExample;
import com.meowlomo.atm.core.model.FileTypeExample;
import com.meowlomo.atm.core.model.GroupExample;
import com.meowlomo.atm.core.model.InstructionActionExample;
import com.meowlomo.atm.core.model.InstructionBundleExample;
import com.meowlomo.atm.core.model.InstructionOptionExample;
import com.meowlomo.atm.core.model.InstructionTypeExample;
import com.meowlomo.atm.core.model.ProjectExample;
import com.meowlomo.atm.core.model.ProjectTypeExample;
import com.meowlomo.atm.core.model.PropertyExample;
import com.meowlomo.atm.core.model.RunSetExample;
import com.meowlomo.atm.core.model.RunSetTestCaseLink;
import com.meowlomo.atm.core.model.RunSetTestCaseLinkExample;
import com.meowlomo.atm.core.model.SectionExample;
import com.meowlomo.atm.core.model.StepLogTypeExample;
import com.meowlomo.atm.core.model.SystemRequirementExample;
import com.meowlomo.atm.core.model.SystemRequirementPackExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseExample;
import com.meowlomo.atm.core.model.TestCaseOptionExample;
import com.meowlomo.atm.core.model.TestCaseOverwriteExample;
import com.meowlomo.atm.core.model.TestCaseShareFolderExample;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLink;
import com.meowlomo.atm.core.model.TestCaseShareFolderTestCaseLinkExample;
import com.meowlomo.atm.core.service.util.DataNameUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DataNameUtilServiceImpl implements DataNameUtilService {

    @Autowired
    private ApiSchemaMapper apiSchemaMapper;
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private DriverPackMapper driverPackMapper;
    @Autowired
    private DriverPropertyMapper driverPropertyMapper;
    @Autowired
    private ElementLocatorTypeMapper elementLocatorTypeMapper;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private ElementTypeMapper elementTypeMapper;
    @Autowired
    private EmailNotificationTargetMapper emailNotificationTargetMapper;
    @Autowired
    private FileTypeMapper fileTypeMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private InstructionActionMapper instructionActionMapper;
    @Autowired
    private InstructionBundleMapper instructionBundleMapper;
    @Autowired
    private InstructionOptionMapper instructionOptionMapper;
    @Autowired
    private InstructionTypeMapper instructionTypeMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectTypeMapper projectTypeMapper;
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private RunSetMapper runSetMapper;
    @Autowired
    private RunSetTestCaseLinkMapper runSetTestCaseLinkMapper;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private StepLogTypeMapper stepLogTypeMapper;
    @Autowired
    private SystemRequirementMapper systemRequirementMapper;
    @Autowired
    private SystemRequirementPackMapper systemRequirementPackMapper;
    @Autowired
    private TestCaseMapper testCaseMapper;
    @Autowired
    private TestCaseOptionMapper testCaseOptionMapper;
    @Autowired
    private TestCaseOverwriteMapper testCaseOverwriteMapper;
    @Autowired
    private TestCaseShareFolderMapper testCaseShareFolderMapper;
    @Autowired
    private TestCaseShareFolderTestCaseLinkMapper testCaseShareFolderTestCaseLinkMapper;
    @Autowired
    private TestCaseUtilMapper testCaseUtilMapper;

    @Override
    public long checkApiSchemaNameBeforeInsert(String apiSchemaName) {
        ApiSchemaExample example = new ApiSchemaExample();
        example.createCriteria().andNameEqualTo(apiSchemaName);
        return apiSchemaMapper.countByExample(example);
    }

    @Override
    public long checkApplicationNameForProjectBeforeInsert(Long projectId, String applicationName) {
        ApplicationExample example = new ApplicationExample();
        example.createCriteria().andDeletedEqualTo(false).andProjectIdEqualTo(projectId)
                .andNameEqualTo(applicationName);
        return applicationMapper.countByExample(example);
    }

    @Override
    public long checkDriverNameBeforeInsert(String driverName) {
        DriverExample example = new DriverExample();
        example.createCriteria().andNameEqualTo(driverName);
        return driverMapper.countByExample(example);
    }

    @Override
    public long checkDriverPackNameBeforeInsert(String driverPackName) {
        DriverPackExample example = new DriverPackExample();
        example.createCriteria().andDeletedEqualTo(false).andNameEqualTo(driverPackName);
        return driverPackMapper.countByExample(example);
    }

    @Override
    public long checkDriverPropertyNameForDriverVendorBeforeInsert(Long driverVenderId, String driverPropertyName) {
        DriverPropertyExample example = new DriverPropertyExample();
        example.createCriteria().andDriverVendorIdEqualTo(driverVenderId).andNameEqualTo(driverPropertyName)
                .andActiveEqualTo(true);
        return driverPropertyMapper.countByExample(example);
    }

    @Override
    public long checkElementLocatorTypeNameBeforeInsert(String elementLocatorTypeName) {
        ElementLocatorTypeExample example = new ElementLocatorTypeExample();
        example.createCriteria().andNameEqualTo(elementLocatorTypeName).andActiveEqualTo(true);
        return elementLocatorTypeMapper.countByExample(example);
    }

    @Override
    public long checkElementNameForProjectBeforeInsert(Long projectId, String elementName) {
        ElementExample example = new ElementExample();
        example.createCriteria().andDeletedEqualTo(false).andProjectIdEqualTo(projectId).andNameEqualTo(elementName);
        return elementMapper.countByExample(example);
    }

    @Override
    public long checkElementNameForSectionBeforeInsert(Long sectionId, String elementName) {
        ElementExample example = new ElementExample();
        example.createCriteria().andDeletedEqualTo(false).andSectionIdEqualTo(sectionId).andNameEqualTo(elementName);
        return elementMapper.countByExample(example);
    }

    @Override
    public long checkElementTypeNameBeforeInsert(String elementTypeName) {
        ElementTypeExample example = new ElementTypeExample();
        example.createCriteria().andNameEqualTo(elementTypeName).andActiveEqualTo(true);
        return elementTypeMapper.countByExample(example);
    }

    @Override
    public long checkEmailAddressBeforeInsert(String emailAddress) {
        EmailNotificationTargetExample example = new EmailNotificationTargetExample();
        example.createCriteria().andEmailAddressEqualTo(emailAddress);
        return emailNotificationTargetMapper.countByExample(example);
    }

    @Override
    public long checkFileTypeNameBeforeInsert(String fileTypeName) {
        FileTypeExample example = new FileTypeExample();
        example.createCriteria().andNameEqualTo(fileTypeName).andActiveEqualTo(true);
        return fileTypeMapper.countByExample(example);
    }

    @Override
    public long checkGroupNameBeforeInsert(String groupName) {
        GroupExample example = new GroupExample();
        example.createCriteria().andNameEqualTo(groupName).andActiveEqualTo(true);
        return groupMapper.countByExample(example);
    }

    @Override
    public long checkInstructionActionNameBeforeInsert(String instructionActionName) {
        InstructionActionExample example = new InstructionActionExample();
        example.createCriteria().andNameEqualTo(instructionActionName).andActiveEqualTo(true);
        return instructionActionMapper.countByExample(example);
    }

    @Override
    public long checkInstructionBundleNameBeforeInsert(String instructionBundleName) {
        InstructionBundleExample example = new InstructionBundleExample();
        example.createCriteria().andNameEqualTo(instructionBundleName).andDeletedEqualTo(false);
        return instructionBundleMapper.countByExample(example);
    }

    @Override
    public long checkInstructionOptionNameBeforeInsert(String instructionOptionName) {
        InstructionOptionExample example = new InstructionOptionExample();
        example.createCriteria().andNameEqualTo(instructionOptionName).andActiveEqualTo(true);
        return instructionOptionMapper.countByExample(example);
    }

    @Override
    public long checkInstructionTypeNameBeforeInsert(String instructionTypeName) {
        InstructionTypeExample example = new InstructionTypeExample();
        example.createCriteria().andNameEqualTo(instructionTypeName).andActiveEqualTo(true);
        return instructionTypeMapper.countByExample(example);
    }

    @Override
    public long checkProjectNameBeforeInsert(String projectName) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andDeletedEqualTo(false).andNameEqualTo(projectName);
        return projectMapper.countByExample(example);
    }

    @Override
    public long checkProjectTypeNameBeforeInsert(String projectTypeName) {
        ProjectTypeExample example = new ProjectTypeExample();
        example.createCriteria().andNameEqualTo(projectTypeName).andActiveEqualTo(true);
        return projectTypeMapper.countByExample(example);
    }

    @Override
    public long checkPropertyNameBeforeInsert(String propertyName) {
        PropertyExample example = new PropertyExample();
        example.createCriteria().andKeyEqualTo(propertyName).andActiveEqualTo(true);
        return propertyMapper.countByExample(example);
    }

    @Override
    public long checkRunSetNameBeforeInsert(String runSetName) {
        RunSetExample example = new RunSetExample();
        example.createCriteria().andDeletedEqualTo(false).andNameEqualTo(runSetName);
        return runSetMapper.countByExample(example);
    }

    @Override
    public long checkRunSetTestCaseLinkBeforeInsertOrUpdate(Long runSetId, Long testCaseId, Long testCaseOverwriteId,
            Long driverPackId) {
        RunSetTestCaseLinkExample example = new RunSetTestCaseLinkExample();
        RunSetTestCaseLinkExample.Criteria criteria = null;
        if (runSetId != null && testCaseId != null) {
            criteria = new RunSetTestCaseLinkExample().createCriteria();
            criteria.andRunSetIdEqualTo(runSetId).andTestCaseIdEqualTo(testCaseId);
        }
        if (testCaseOverwriteId != null) {
            criteria.andTestCaseOverwriteIdEqualTo(testCaseOverwriteId);
        }
        if (driverPackId != null) {
            criteria.andDriverPackIdEqualTo(driverPackId);
        }
        example.or(criteria);
        return runSetTestCaseLinkMapper.countByExample(example);
    }

    @Override
    public long checkSectionNameBeforeInsert(Long applicationId, String sectionName) {
        SectionExample example = new SectionExample();
        example.createCriteria().andDeletedEqualTo(false).andApplicationIdEqualTo(applicationId)
                .andNameEqualTo(sectionName);
        return sectionMapper.countByExample(example);
    }

    @Override
    public long checkSectionNameForApplicationBeforeInsert(Long applicationId, String sectionName) {
        SectionExample example = new SectionExample();
        example.createCriteria().andDeletedEqualTo(false).andApplicationIdEqualTo(applicationId)
                .andNameEqualTo(sectionName);
        return sectionMapper.countByExample(example);
    }

    @Override
    public long checkStepLogTypeNameBeforeInsert(String stepLogTypeName) {
        StepLogTypeExample example = new StepLogTypeExample();
        example.createCriteria().andNameEqualTo(stepLogTypeName).andActiveEqualTo(true);
        return stepLogTypeMapper.countByExample(example);
    }

    @Override
    public long checkSystemRequirementNameBeforeInsert(String systemRequirementName) {
        SystemRequirementExample example = new SystemRequirementExample();
        example.createCriteria().andNameEqualTo(systemRequirementName);
        return systemRequirementMapper.countByExample(example);
    }

    @Override
    public long checkSystemRequirementPackBeforeInsert(String systemRequirementPackName) {
        SystemRequirementPackExample systemRequirementPackExample = new SystemRequirementPackExample();
        systemRequirementPackExample.createCriteria().andNameEqualTo(systemRequirementPackName).andDeletedEqualTo(false);
        return systemRequirementPackMapper.countByExample(systemRequirementPackExample);
    }

    @Override
    public long checkTestCaseNameForProjectBeforeInsert(Long projectId, String tesCaseName) {
        TestCaseExample example = new TestCaseExample();
        example.createCriteria().andDeletedEqualTo(false).andProjectIdEqualTo(projectId).andNameEqualTo(tesCaseName);
        return testCaseMapper.countByExample(example);
    }

    @Override
    public long checkTestCaseNameForRunSetBeforeInsert(Long runSetId, String tesCaseName) {
        RunSetTestCaseLinkExample linkExample = new RunSetTestCaseLinkExample();
        linkExample.createCriteria().andRunSetIdEqualTo(runSetId);
        List<RunSetTestCaseLink> links = runSetTestCaseLinkMapper.selectByExample(linkExample);
        if (links.isEmpty()) { return 0; }
        List<Long> targetIds = new ArrayList<Long>();
        for (RunSetTestCaseLink link : links) {
            targetIds.add(link.getTestCaseId());
        }
        TestCaseExample example = new TestCaseExample();
        example.createCriteria().andDeletedEqualTo(false).andIdIn(targetIds);
        return testCaseMapper.countByExample(example);
    }

    @Override
    public long checkTestCaseNameForTestCaseShareFolderBeforeInsert(Long testCaseShareFolderId, String tesCaseName) {
        TestCaseShareFolderTestCaseLinkExample linkExample = new TestCaseShareFolderTestCaseLinkExample();
        linkExample.createCriteria().andTestCaseShareFolderIdEqualTo(testCaseShareFolderId);
        List<TestCaseShareFolderTestCaseLink> links = testCaseShareFolderTestCaseLinkMapper
                .selectByExample(linkExample);
        if (links.isEmpty()) { return 0; }
        List<Long> targetIds = new ArrayList<Long>();
        for (TestCaseShareFolderTestCaseLink link : links) {
            targetIds.add(link.getTestCaseId());
        }
        TestCaseExample example = new TestCaseExample();
        example.createCriteria().andDeletedEqualTo(false).andIdIn(targetIds);
        return testCaseMapper.countByExample(example);
    }

    @Override
    public long checkTestCaseOptionNameBeforeInsert(String testCaseOptionName) {
        TestCaseOptionExample example = new TestCaseOptionExample();
        example.createCriteria().andNameEqualTo(testCaseOptionName).andActiveEqualTo(true);
        return testCaseOptionMapper.countByExample(example);
    }

    @Override
    public long checkTestCaseOverwriteNameForTestCaseBeforeInsert(Long testCaseId, String testCaseOverwriteName) {
        TestCaseOverwriteExample example = new TestCaseOverwriteExample();
        example.createCriteria().andTestCaseIdEqualTo(testCaseId).andNameEqualTo(testCaseOverwriteName)
                .andDeletedEqualTo(false);
        return testCaseOverwriteMapper.countByExample(example);
    }

    public List<TestCase> checkTestCaseReferenceBeforeDeleteTestCase(Long testCaseId) {
        if (testCaseId == null) {
            return new ArrayList<TestCase>();
        }
        else {
            return testCaseUtilMapper.getReferencedTestCasesFromTestCaseId(testCaseId);
        }
    }

    @Override
    public long checkTestCaseShareFolderNameBeforeInsert(String testCaseShareFolderName) {
        TestCaseShareFolderExample example = new TestCaseShareFolderExample();
        example.createCriteria().andDeletedEqualTo(false).andNameEqualTo(testCaseShareFolderName);
        return testCaseShareFolderMapper.countByExample(example);
    }

    public long countTestCaseReferenceBeforeDeleteTestCase(Long testCaseId) {
        if (testCaseId == null) {
            return 0;
        }
        else {
            return testCaseUtilMapper.countReferencedTestCasesFromTestCaseId(testCaseId);
        }
    }

    @Override
    public String getNewApiSchemaNameForCopy(String originalName) {
        if (originalName == null) { return null; }

        if (this.checkApiSchemaNameBeforeInsert(originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkApiSchemaNameBeforeInsert(finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewApplicationNameForCopyForProject(Long projectId, String originalName) {
        if (originalName == null) { return null; }

        if (this.checkApplicationNameForProjectBeforeInsert(projectId, originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkApplicationNameForProjectBeforeInsert(projectId, finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewDriverPackNameForCopy(String originalName) {
        if (originalName == null) { return null; }

        if (this.checkDriverPackNameBeforeInsert(originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkDriverPackNameBeforeInsert(finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewDriverPropertyNameForCopyForDriverVender(Long driverVenderId, String originalName) {
        if (originalName == null || driverVenderId == null) { return null; }

        if (this.checkDriverPropertyNameForDriverVendorBeforeInsert(driverVenderId, originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkDriverPropertyNameForDriverVendorBeforeInsert(driverVenderId, finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewDrvierNameForCopy(String originalName) {
        if (originalName == null) { return null; }

        if (this.checkDriverNameBeforeInsert(originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkDriverNameBeforeInsert(finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewElementForProjectNameForCopy(Long projectId, String originalName) {
        if (originalName == null || projectId == null) { return null; }

        if (this.checkElementNameForProjectBeforeInsert(projectId, originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkElementNameForProjectBeforeInsert(projectId, finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewElementForSectionNameForCopy(Long sectionId, String originalName) {
        if (originalName == null || sectionId == null) { return null; }

        if (this.checkElementNameForSectionBeforeInsert(sectionId, originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkElementNameForSectionBeforeInsert(sectionId, finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    private String getNewIncrementName(String name) {
        Integer incrementNumber = this.getNumberFromEndOfString(name);
        if (incrementNumber == null) {
            return name + "0";
        }
        else {
            StringBuilder b = new StringBuilder(name);
            Integer newNumber = incrementNumber + 1;
            b.replace(name.lastIndexOf(incrementNumber.toString()),
                    name.lastIndexOf(incrementNumber.toString()) + incrementNumber.toString().length(),
                    newNumber.toString());
            name = b.toString();
            return name;
        }
    }

    @Override
    public String getNewProjectNameForCopy(String originalName) {
        if (originalName == null) { return null; }

        if (this.checkProjectNameBeforeInsert(originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkProjectNameBeforeInsert(finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewRunSetNameForCopy(String originalName) {
        if (originalName == null) { return null; }

        if (this.checkRunSetNameBeforeInsert(originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkRunSetNameBeforeInsert(finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewSectionNameForCopyForApplication(Long applicationId, String originalSectionName) {
        if (originalSectionName == null || applicationId == null) { return null; }

        if (this.checkSectionNameBeforeInsert(applicationId, originalSectionName) > 0) {
            String finalName = originalSectionName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkApiSchemaNameBeforeInsert(finalName) > 0);
            return finalName;
        }
        else {
            return originalSectionName;
        }
    }

    @Override
    public String getNewTestCaseForProjectNameForCopy(Long projectId, String originalName) {
        if (originalName == null || projectId == null) { return null; }
        if (this.checkTestCaseNameForProjectBeforeInsert(projectId, originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkTestCaseNameForProjectBeforeInsert(projectId, finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }

    @Override
    public String getNewTestCaseOverwriteForTestCaseNameForCopy(Long testCaseId, String originalName) {
        if (originalName == null) { return null; }

        if (this.checkTestCaseOverwriteNameForTestCaseBeforeInsert(testCaseId, originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkTestCaseOverwriteNameForTestCaseBeforeInsert(testCaseId, finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }
    
    @Override
    public String getNewTestCaseShareFolderNameForCopy(String originalName) {
        if (originalName == null) { return null; }

        if (this.checkTestCaseShareFolderNameBeforeInsert(originalName) > 0) {
            String finalName = originalName;
            do {
                finalName = this.getNewIncrementName(finalName);
            }
            while (this.checkTestCaseShareFolderNameBeforeInsert(finalName) > 0);
            return finalName;
        }
        else {
            return originalName;
        }
    }
    
    private Integer getNumberFromEndOfString(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        else {
            int returnValue = -1;
            for (int a = 0; a < input.length(); a++) {
                try {
                    returnValue = Integer.parseInt(input.substring(a));
                    break;
                }
                catch (Exception e) {
                }
            }
            if (returnValue == -1) {
                return null;
            }
            else {
                return returnValue;
            }
        }
    }
}
