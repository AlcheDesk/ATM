package com.meowlomo.atm.scheduler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.model.Color;
import com.meowlomo.atm.core.model.ColorExample;
import com.meowlomo.atm.core.model.ContentType;
import com.meowlomo.atm.core.model.ContentTypeExample;
import com.meowlomo.atm.core.model.DriverType;
import com.meowlomo.atm.core.model.DriverTypeExample;
import com.meowlomo.atm.core.model.ElementLocatorType;
import com.meowlomo.atm.core.model.ElementLocatorTypeExample;
import com.meowlomo.atm.core.model.ElementType;
import com.meowlomo.atm.core.model.ElementTypeExample;
import com.meowlomo.atm.core.model.FileType;
import com.meowlomo.atm.core.model.FileTypeExample;
import com.meowlomo.atm.core.model.Group;
import com.meowlomo.atm.core.model.GroupExample;
import com.meowlomo.atm.core.model.InstructionAction;
import com.meowlomo.atm.core.model.InstructionActionExample;
import com.meowlomo.atm.core.model.InstructionOption;
import com.meowlomo.atm.core.model.InstructionOptionExample;
import com.meowlomo.atm.core.model.InstructionType;
import com.meowlomo.atm.core.model.InstructionTypeExample;
import com.meowlomo.atm.core.model.LogLevel;
import com.meowlomo.atm.core.model.LogLevelExample;
import com.meowlomo.atm.core.model.ProjectType;
import com.meowlomo.atm.core.model.ProjectTypeExample;
import com.meowlomo.atm.core.model.Property;
import com.meowlomo.atm.core.model.PropertyExample;
import com.meowlomo.atm.core.model.RunSetType;
import com.meowlomo.atm.core.model.RunSetTypeExample;
import com.meowlomo.atm.core.model.RunType;
import com.meowlomo.atm.core.model.RunTypeExample;
import com.meowlomo.atm.core.model.SourceType;
import com.meowlomo.atm.core.model.SourceTypeExample;
import com.meowlomo.atm.core.model.Status;
import com.meowlomo.atm.core.model.StatusExample;
import com.meowlomo.atm.core.model.StepLogType;
import com.meowlomo.atm.core.model.StepLogTypeExample;
import com.meowlomo.atm.core.model.SystemRequirement;
import com.meowlomo.atm.core.model.SystemRequirementExample;
import com.meowlomo.atm.core.model.SystemRequirementType;
import com.meowlomo.atm.core.model.SystemRequirementTypeExample;
import com.meowlomo.atm.core.model.Tenant;
import com.meowlomo.atm.core.model.TenantExample;
import com.meowlomo.atm.core.model.TestCaseOption;
import com.meowlomo.atm.core.model.TestCaseOptionExample;
import com.meowlomo.atm.core.model.TestCaseType;
import com.meowlomo.atm.core.model.TestCaseTypeExample;
import com.meowlomo.atm.core.mapper.ColorMapper;
import com.meowlomo.atm.core.mapper.ContentTypeMapper;
import com.meowlomo.atm.core.mapper.DriverTypeMapper;
import com.meowlomo.atm.core.mapper.ElementLocatorTypeMapper;
import com.meowlomo.atm.core.mapper.ElementTypeMapper;
import com.meowlomo.atm.core.mapper.FileTypeMapper;
import com.meowlomo.atm.core.mapper.GroupMapper;
import com.meowlomo.atm.core.mapper.InstructionActionMapper;
import com.meowlomo.atm.core.mapper.InstructionOptionMapper;
import com.meowlomo.atm.core.mapper.InstructionTypeMapper;
import com.meowlomo.atm.core.mapper.LogLevelMapper;
import com.meowlomo.atm.core.mapper.ProjectTypeMapper;
import com.meowlomo.atm.core.mapper.PropertyMapper;
import com.meowlomo.atm.core.mapper.RunSetTypeMapper;
import com.meowlomo.atm.core.mapper.RunTypeMapper;
import com.meowlomo.atm.core.mapper.SourceTypeMapper;
import com.meowlomo.atm.core.mapper.StatusMapper;
import com.meowlomo.atm.core.mapper.StepLogTypeMapper;
import com.meowlomo.atm.core.mapper.SystemRequirementMapper;
import com.meowlomo.atm.core.mapper.SystemRequirementTypeMapper;
import com.meowlomo.atm.core.mapper.TenantMapper;
import com.meowlomo.atm.core.mapper.TestCaseOptionMapper;
import com.meowlomo.atm.core.mapper.TestCaseTypeMapper;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class ReferenceDataFetcher {

    static final Logger logger = LoggerFactory.getLogger(ReferenceDataFetcher.class);

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private SystemRequirementMapper systemRequirementMapper;
    @Autowired
    private ElementTypeMapper elementTypeMapper;
    @Autowired
    private InstructionActionMapper instructionActionMapper;
    @Autowired
    private ElementLocatorTypeMapper elementLocatorTypeMapper;
    @Autowired
    private FileTypeMapper fileTypeMapper;
    @Autowired
    private ProjectTypeMapper projectTypeMapper;
    @Autowired
    private LogLevelMapper logLevelMapper;
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private InstructionTypeMapper instructionTypeMapper;
    @Autowired
    private TestCaseTypeMapper testCaseTypeMapper;
    @Autowired
    private InstructionOptionMapper instructionOptionMapper;
    @Autowired
    private TestCaseOptionMapper testCaseOptionMapper;
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private StepLogTypeMapper stepLogTypeMapper;
    @Autowired
    private RunTypeMapper runTypeMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private DriverTypeMapper driverTypeMapper;
    @Autowired
    private RunSetTypeMapper runSetTypeMapper;
    @Autowired
    private SourceTypeMapper sourceTypeMapper;
    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private ContentTypeMapper contentTypeMapper;
    @Autowired
    private SystemRequirementTypeMapper systemRequirementTypeMapper;
    
    public void fetchTenant() {
        logger.info("fetching tenant");
        HashMap<UUID, Long> uuidToIdMap = new HashMap<UUID, Long>();
        HashMap<UUID, Tenant> uuidToObjectMap = new HashMap<UUID, Tenant>();
        TenantExample example = new TenantExample();
        example.or().andIdIsNotNull();
        List<Tenant> recordList = tenantMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Tenant record = recordList.get(count);
                uuidToIdMap.put(record.getUuid(), record.getId());
                uuidToObjectMap.put(record.getUuid(), record);
            }
            // put the map to the runtime variables
            RuntimeVariables.setTenantUuidToIdMap(uuidToIdMap);
            RuntimeVariables.setTenantUuidToTenantMap(uuidToObjectMap);
        }
    }    
    
    /**
     * Fetch status.
     */
    public void fetchStatus() {
        logger.info("fetching status");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        StatusExample example = new StatusExample();
        example.or().andIdIsNotNull();
        List<Status> recordList = statusMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Status record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToStatusMap(idToNameMap);
            RuntimeVariables.setStatusToIdMap(nameToIdMap);
            RuntimeVariables.setStatuses(names);
        }
    }

    public void fetchGroup() {
        logger.info("fetching group");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        GroupExample example = new GroupExample();
        example.or().andIdIsNotNull();
        List<Group> recordList = groupMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Group record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToGroupMap(idToNameMap);
            RuntimeVariables.setGroupToIdMap(nameToIdMap);
            RuntimeVariables.setGroups(names);
        }
    }

    /**
     * Fetch test case task type.
     */
    public void fetchTestCaseType() {
        logger.info("fetching test case task type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        TestCaseTypeExample example = new TestCaseTypeExample();
        example.or().andIdIsNotNull();
        List<TestCaseType> recordList = testCaseTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                TestCaseType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setTestCaseTypes(names);
            RuntimeVariables.setTestCaseTypeToIdMap(nameToIdMap);
            RuntimeVariables.setIdToTestCaseTypeMap(idToNameMap);
        }
    }

    /**
     * Fetch element type.
     */
    // @Scheduled(cron = "1 * * * * *")
    public void fetchElementType() {
        logger.info("fetching element type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        ElementTypeExample example = new ElementTypeExample();
        example.or().andIdIsNotNull();
        List<ElementType> recordList = elementTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                ElementType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToElementTypeMap(idToNameMap);
            RuntimeVariables.setElementTypeToIdMap(nameToIdMap);
            RuntimeVariables.setElementTypes(names);
        }
    }

    /**
     * Fetch systemRequirement.
     */
    // @Scheduled(cron = "3 * * * * *")
    public void fetchSystemRequirement() {
        logger.info("fetching systemRequirement");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        SystemRequirementExample example = new SystemRequirementExample();
        example.or().andIdIsNotNull();
        List<SystemRequirement> recordList = systemRequirementMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                SystemRequirement record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToSystemRequirementMap(idToNameMap);
            RuntimeVariables.setSystemRequirementToIdMap(nameToIdMap);
            RuntimeVariables.setSystemRequirements(names);
        }
    }

    /**
     * Fetch element locator type.
     */
    // @Scheduled(cron = "5 * * * * *")
    public void fetchElementLocatorType() {
        logger.info("fetching element locator type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        ElementLocatorTypeExample example = new ElementLocatorTypeExample();
        example.or().andIdIsNotNull();
        List<ElementLocatorType> recordList = elementLocatorTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                ElementLocatorType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToElementLocatorTypeMap(idToNameMap);
            RuntimeVariables.setElementLocatorTypeToIdMap(nameToIdMap);
            RuntimeVariables.setElementLocatorTypes(names);
        }
    }

    /**
     * Fetch operating system.
     */
    // @Scheduled(cron = "7 * * * * *")
    public void fetchInstructionAction() {
        logger.info("fetching element action");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        InstructionActionExample example = new InstructionActionExample();
        example.or().andIdIsNotNull();
        List<InstructionAction> recordList = instructionActionMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                InstructionAction record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToInstructionActionMap(idToNameMap);
            RuntimeVariables.setInstructionActionToIdMap(nameToIdMap);
            RuntimeVariables.setInstructionActions(names);
        }
    }

    /**
     * Fetch file type.
     */
    // @Scheduled(cron = "11 * * * * *")
    public void fetchFileType() {
        logger.info("fetching atm object type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        FileTypeExample example = new FileTypeExample();
        example.or().andIdIsNotNull();
        List<FileType> recordList = fileTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                FileType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToFileTypeMap(idToNameMap);
            RuntimeVariables.setFileTypeToIdMap(nameToIdMap);
            RuntimeVariables.setFileTypes(names);
        }
    }

    /**
     * Fetch project type.
     */
    // @Scheduled(cron = "13 * * * * *")
    public void fetchProjectType() {
        logger.info("fetching atm object type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        ProjectTypeExample example = new ProjectTypeExample();
        example.or().andIdIsNotNull();
        List<ProjectType> recordList = projectTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                ProjectType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToProjectTypeMap(idToNameMap);
            RuntimeVariables.setProjectTypeToIdMap(nameToIdMap);
            RuntimeVariables.setProjectTypes(names);
        }
    }

    /**
     * Fetch run type.
     */
    // @Scheduled(cron = "1 * * * * *")
    public void fetchRunType() {
        logger.info("fetching run type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        RunTypeExample example = new RunTypeExample();
        example.or().andIdIsNotNull();
        List<RunType> recordList = runTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                RunType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToRunTypeMap(idToNameMap);
            RuntimeVariables.setRunTypeToIdMap(nameToIdMap);
            RuntimeVariables.setRunTypes(names);
        }
    }

    /**
     * Fetch source type.
     */
    // @Scheduled(cron = "1 * * * * *")
    public void fetchSourceType() {
        logger.info("fetching source type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        SourceTypeExample example = new SourceTypeExample();
        example.or().andIdIsNotNull();
        List<SourceType> recordList = sourceTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                SourceType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the sourcetime variables
            RuntimeVariables.setIdToSourceTypeMap(idToNameMap);
            RuntimeVariables.setSourceTypeToIdMap(nameToIdMap);
            RuntimeVariables.setSourceTypes(names);
        }
    }

    /**
     * Fetch log level.
     */
    public void fetchLogLevel() {
        logger.info("fetching log level");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        LogLevelExample example = new LogLevelExample();
        example.or().andIdIsNotNull();
        List<LogLevel> recordList = logLevelMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                LogLevel record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToLogLevelMap(idToNameMap);
            RuntimeVariables.setLogLevelToIdMap(nameToIdMap);
            RuntimeVariables.setLogLevels(names);
        }
    }

    // @Scheduled(cron = "*/30 * * * * *")
    public void fetchProperty() {
        logger.info("fetching property");
        PropertyExample example = new PropertyExample();
        example.or().andIdIsNotNull();
        List<Property> recordList = propertyMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Property record = recordList.get(count);
                RuntimeVariables.property.put(record.getKey(), record.getValue());
            }
        }
    }

    // @Scheduled(cron = "19 * * * * *")
    public void fetchInstrcutionTypes() {
        logger.info("fetching instrcution types");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        HashMap<String, InstructionType> nameObjectMap = new HashMap<String, InstructionType>();
        InstructionTypeExample example = new InstructionTypeExample();
        example.or().andIdIsNotNull();
        List<InstructionType> recordList = instructionTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                InstructionType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
                nameObjectMap.put(record.getName(), record);
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToInstructionTypeMap(idToNameMap);
            RuntimeVariables.setInstructionTypeToIdMap(nameToIdMap);
            RuntimeVariables.setInstructionTypes(names);
            RuntimeVariables.setInstructionTypeObjectMap(nameObjectMap);
        }
    }

    public void fetchColor() {
        logger.info("fetching color");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        ColorExample example = new ColorExample();
        example.or().andIdIsNotNull();
        List<Color> recordList = colorMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Color record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToColorMap(idToNameMap);
            RuntimeVariables.setColorToIdMap(nameToIdMap);
            RuntimeVariables.setColors(names);
        }
    }

    // @Scheduled(cron = "23 * * * * *")
    public void fetchInstructionOption() {
        logger.info("fetching instruction option");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        InstructionOptionExample example = new InstructionOptionExample();
        example.or().andIdIsNotNull();
        List<InstructionOption> recordList = instructionOptionMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                InstructionOption record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToInstructionOptionMap(idToNameMap);
            RuntimeVariables.setInstructionOptionToIdMap(nameToIdMap);
            RuntimeVariables.setInstructionOptions(names);
        }
    }

    // @Scheduled(cron = "29 * * * * *")
    public void fetchTestCaseOption() {
        logger.info("fetching test case option");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        TestCaseOptionExample example = new TestCaseOptionExample();
        example.or().andIdIsNotNull();
        List<TestCaseOption> recordList = testCaseOptionMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                TestCaseOption record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToTestCaseOptionMap(idToNameMap);
            RuntimeVariables.setTestCaseOptionToIdMap(nameToIdMap);
            RuntimeVariables.setTestCaseOptions(names);
        }
    }

    public void fetchStepLogTypes() {
        logger.info("fetching stepLog types");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        StepLogTypeExample example = new StepLogTypeExample();
        example.or().andIdIsNotNull();
        List<StepLogType> recordList = stepLogTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                StepLogType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToStepLogTypeMap(idToNameMap);
            RuntimeVariables.setStepLogTypeToIdMap(nameToIdMap);
            RuntimeVariables.setStepLogTypes(names);
        }
    }

    public void fetchDriverTypes() {
        logger.info("fetching driver types");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        HashMap<String, DriverType> nameObjectMap = new HashMap<String, DriverType>();
        DriverTypeExample example = new DriverTypeExample();
        example.or().andIdIsNotNull();
        List<DriverType> recordList = driverTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                DriverType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
                nameObjectMap.put(record.getName(), record);
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToDriverTypeMap(idToNameMap);
            RuntimeVariables.setDriverTypeToIdMap(nameToIdMap);
            RuntimeVariables.setDriverTypes(names);
            RuntimeVariables.setDriverTypeObjectMap(nameObjectMap);
        }
    }

    public void fetchSystemRequirementTypes() {
        logger.info("fetching systemRequirement types");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        HashMap<String, SystemRequirementType> nameObjectMap = new HashMap<String, SystemRequirementType>();
        SystemRequirementTypeExample example = new SystemRequirementTypeExample();
        example.or().andIdIsNotNull();
        List<SystemRequirementType> recordList = systemRequirementTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                SystemRequirementType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
                nameObjectMap.put(record.getName(), record);
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToSystemRequirementTypeMap(idToNameMap);
            RuntimeVariables.setSystemRequirementTypeToIdMap(nameToIdMap);
            RuntimeVariables.setSystemRequirementTypes(names);
            RuntimeVariables.setSystemRequirementTypeObjectMap(nameObjectMap);
        }
    }
    
    public void fetchRunSetTypes() {
        logger.info("fetching runSet types");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        HashMap<String, RunSetType> nameObjectMap = new HashMap<String, RunSetType>();
        RunSetTypeExample example = new RunSetTypeExample();
        example.or().andIdIsNotNull();
        List<RunSetType> recordList = runSetTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                RunSetType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
                nameObjectMap.put(record.getName(), record);
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToRunSetTypeMap(idToNameMap);
            RuntimeVariables.setRunSetTypeToIdMap(nameToIdMap);
            RuntimeVariables.setRunSetTypes(names);
        }
    }
    
    public void fetchContentType() {
        logger.info("fetching element type");
        HashSet<String> names = new HashSet<String>();
        TreeMap<String, Long> nameToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        ContentTypeExample example = new ContentTypeExample();
        example.or().andIdIsNotNull();
        List<ContentType> recordList = contentTypeMapper.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                ContentType record = recordList.get(count);
                nameToIdMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToContentTypeMap(idToNameMap);
            RuntimeVariables.setContentTypeToIdMap(nameToIdMap);
            RuntimeVariables.setContentTypes(names);
        }
    }

}
