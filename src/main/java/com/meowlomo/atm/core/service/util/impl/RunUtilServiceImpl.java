package com.meowlomo.atm.core.service.util.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.mapper.DriverPackReferenceMapper;
import com.meowlomo.atm.core.mapper.RunMapper;
import com.meowlomo.atm.core.mapper.RunTaskLinkMapper;
import com.meowlomo.atm.core.mapper.TestCaseOverwriteReferenceMapper;
import com.meowlomo.atm.core.mapper.util.RunUtilMapper;
import com.meowlomo.atm.core.model.Driver;
import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunTaskLink;
import com.meowlomo.atm.core.model.RunTaskLinkExample;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;
import com.meowlomo.atm.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.service.util.DriverPackUtilService;
import com.meowlomo.atm.core.service.util.RunUtilService;
import com.meowlomo.atm.external.ems.model.Task;
import com.meowlomo.atm.external.ems.service.EMSTaskService;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunUtilServiceImpl implements RunUtilService {

    @Autowired
    private DriverPackReferenceMapper driverPackReferenceMapper;

    @Autowired
    private DriverPackUtilService driverPackUtilService;
    @Autowired
    private EMSTaskService emsTaskService;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    private final Logger logger = LoggerFactory.getLogger(RunUtilServiceImpl.class);
    @Autowired
    private RunMapper runMapper;
    @Autowired
    private RunTaskLinkMapper runTaskLinkMapper;
    @Autowired
    private RunUtilMapper runUtilMapper;
    @Autowired
    private TestCaseOverwriteReferenceMapper testCaseOverwriteReferenceMapper;

    @Override
    public Run buildRunWithDriverPackAndTestCaseOverwriteForExecution(Run executionInputRun, Long testCaseId,
            Long driverPackId, Long testCaseOverwriteId) {
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        JsonNode executionRunJson = objectMapper.convertValue(executionInputRun, JsonNode.class);
        Run returnRun = objectMapper.convertValue(executionRunJson, Run.class);
        if (driverPackId != null) {
            List<DriverPack> fittedDriverPacks = driverPackUtilService.getAllDriverPackByTestCaseId(testCaseId);
            boolean driverPackFitFound = false;
            for (DriverPack driverPack : fittedDriverPacks) {
                if (driverPack.getId().equals(driverPackId)) {
                    driverPackFitFound = true;
                }
            }

            if (!driverPackFitFound) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " driver pack not fit";
                String message = "驱动包不符合";
                String code = "02POS";
                logger.error(
                        "Driver Pack settings is not valid. test cas id {} , driver pack id {} , test case overwrite id {}",
                        testCaseId, driverPackId, testCaseOverwriteId);
                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
            }
            DriverPack driverPack = driverPackReferenceMapper.selectByPrimaryKey(driverPackId);
            if (driverPack != null) {
                try {
                    String driverpackJsonString = objectMapper.writeValueAsString(driverPack);
                    DriverPack newDriverpack = objectMapper.readValue(driverpackJsonString, DriverPack.class);
                    logger.debug("drivers to set {}", objectMapper.writeValueAsString(newDriverpack.getDrivers()));
                    //filter the web browser driver first
                    List<Driver> driversForRun = driverPackUtilService.removeDuplicatedWebBrowerDriverForExecution(newDriverpack);
                    returnRun.setDrivers(objectMapper.convertValue(driversForRun, JsonNode.class));
                    returnRun.setDriverPackId(newDriverpack.getId());
                    newDriverpack.setDrivers(null);
                    returnRun.setDriverPack(objectMapper.convertValue(newDriverpack, JsonNode.class));
                }
                catch (JsonProcessingException e) {
                    logger.error("error on converting driver / driver pack object to json ", e);
                }
            }
        }
        else {
            UUID exuuid = UUID.randomUUID();
            String trace = "exception UUID=" + exuuid + " driver pack not fit";
            String message = String.format("用例%d的驱动包%d不符合使用条件", testCaseId, driverPackId);
            String code = "K738HTQG3A";
            logger.error("Driver Pack is nost set. test cas id {} , driver pack id {} , test case overwrite id {}",
                    testCaseId, driverPackId, testCaseOverwriteId);
            throw new CustomBadRequestException(null, message, trace, code, exuuid);
        }

        // set the test case overwrite
        if (testCaseOverwriteId != null) {
            TestCaseOverwrite testCaseOverwrite = testCaseOverwriteReferenceMapper
                    .selectByPrimaryKey(testCaseOverwriteId);
            if (!testCaseOverwrite.getTestCaseId().equals(testCaseId)) {
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " dead lock";
                String message = "存在死循环";
                String code = "02POS";
                logger.error(
                        "Deadlock for test case and test case overwrite refernece. test cas id {} , driver pack id {} , test case overwrite id {}",
                        testCaseId, driverPackId, testCaseOverwriteId);
                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
            }
            returnRun.setTestCaseOverwrite(
                    jacksonConverter.getObjectMapper().convertValue(testCaseOverwrite, JsonNode.class));
        }
        return returnRun;
    }

//    @Override
//    public Run buildRunWithDriverPackAndTestCaseOverwriteForExecution(Run executionInputRun, Long testCaseId,
//            Long driverPackId, Long testCaseOverwriteId, Long runSetTestCaseLinkId) {
//        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
//        JsonNode executionRunJson = objectMapper.convertValue(executionInputRun, JsonNode.class);
//        Run returnRun = objectMapper.convertValue(executionRunJson, Run.class);
//        if (driverPackId != null) {
//            List<DriverPack> fittedDriverPacks = driverPackUtilService.getAllDriverPackByTestCaseId(testCaseId);
//            boolean driverPackFitFound = false;
//            for (DriverPack driverPack : fittedDriverPacks) {
//                if (driverPack.getId().equals(driverPackId)) {
//                    driverPackFitFound = true;
//                }
//            }
//
//            if (!driverPackFitFound) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " driver pack not fit";
//                String message = "驱动包不符合";
//                String code = "02POS";
//                logger.error(
//                        "Driver Pack settings is not valid. test cas id {} , driver pack id {} , test case overwrite id {}",
//                        testCaseId, driverPackId, testCaseOverwriteId);
//                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//            }
//            DriverPack driverPack = driverPackReferenceMapper.selectByPrimaryKey(driverPackId);
//            if (driverPack != null) {
//                try {
//                    String driverpackJsonString = objectMapper.writeValueAsString(driverPack);
//                    DriverPack newDriverpack = objectMapper.readValue(driverpackJsonString, DriverPack.class);
//                    logger.debug("drivers to set {}", objectMapper.writeValueAsString(newDriverpack.getDrivers()));
//                    returnRun.setDrivers(objectMapper.convertValue(newDriverpack.getDrivers(), JsonNode.class));
//                    returnRun.setDriverPackId(newDriverpack.getId());
//                    newDriverpack.setDrivers(null);
//                    returnRun.setDriverPack(objectMapper.convertValue(newDriverpack, JsonNode.class));
//                }
//                catch (JsonProcessingException e) {
//                    logger.error("error on converting driver / driver pack object to json ", e);
//                }
//            }
//        }
//        else {
//            UUID exuuid = UUID.randomUUID();
//            String trace = "exception UUID=" + exuuid + " driver pack not fit";
//            String message = String.format("用例%d的驱动包%d不符合使用条件", testCaseId, driverPackId);
//            String code = "K738HTQG3A";
//            logger.error("Driver Pack is nost set. test cas id {} , driver pack id {} , test case overwrite id {}",
//                    testCaseId, driverPackId, testCaseOverwriteId);
//            throw new CustomBadRequestException(null, message, trace, code, exuuid);
//        }
//
//        // set the test case overwrite
//        if (testCaseOverwriteId != null) {
//            TestCaseOverwrite testCaseOverwrite = testCaseOverwriteReferenceMapper
//                    .selectByPrimaryKey(testCaseOverwriteId);
//            if (!testCaseOverwrite.getTestCaseId().equals(testCaseId)) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " dead lock";
//                String message = "存在死循环";
//                String code = "02POS";
//                logger.error(
//                        "Deadlock for test case and test case overwrite refernece. test cas id {} , driver pack id {} , test case overwrite id {}",
//                        testCaseId, driverPackId, testCaseOverwriteId);
//                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//            }
//            returnRun.setTestCaseOverwrite(
//                    jacksonConverter.getObjectMapper().convertValue(testCaseOverwrite, JsonNode.class));
//        }
//
//        // set the test case overwrite
//        if (runSetTestCaseLinkId != null) {
//            returnRun.setRunSetTestCaseLinkId(runSetTestCaseLinkId);
//        }
//        return returnRun;
//    }

    @Override
    public int cleanEndAtEarlierStartAtRun() {
        return runUtilMapper.cleanEndAtEarlierStartAtRun();
    }

    @Override
    public int cleanEndAtNotNullStartAtNullRun() {
        return runUtilMapper.cleanEndAtNotNullStartAtNullRun();
    }

    @Override
    public int cleanFinishedNotInEndStatus() {
        return runUtilMapper.cleanFinishedNotInEndStatus();
    }

    @Override
    public int cleanStartAtIsNullFinishedIsTrueRun() {
        return runUtilMapper.cleanStartAtIsNullFinishedIsTrueRun();
    }

    void generateDriverPermutations(List<List<Driver>> driverSetList, List<List<Driver>> result, int depth,
            List<Driver> current) {
        List<Driver> currentDepthEntryList = driverSetList.get(depth);
        // this is the last level
        if (depth == driverSetList.size() - 1) {
            // add the entry one by one to the current
            for (int i = 0; i < currentDepthEntryList.size(); i++) {
                Driver entry = currentDepthEntryList.get(i);
                List<Driver> newList = new ArrayList<Driver>(current);
                newList.add(entry);
                result.add(newList);
            }
            return;
        }
        else {
            for (int i = 0; i < currentDepthEntryList.size(); i++) {
                Driver entry = currentDepthEntryList.get(i);
                ArrayList<Driver> newCurrent = new ArrayList<Driver>(current);
                newCurrent.add(entry);
                generateDriverPermutations(driverSetList, result, depth + 1, newCurrent);
            }
        }
    }

//    public List<Run> generateRunsWithUniqueDriverType(Run fullRun) {
//        // get the drivers from the run
//        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
//        try {
//            logger.debug("expanding run " + objectMapper.writeValueAsString(fullRun));
//        }
//        catch (JsonProcessingException e1) {
//            e1.printStackTrace();
//        }
//        List<Driver> driverEntrysList = objectMapper.convertValue(fullRun.getDrivers(),
//                new TypeReference<List<Driver>>() {
//                });
//        List<Driver> drivers = driverEntrysList == null ? new ArrayList<Driver>() : driverEntrysList;
//        // sort the drivers by type
//        Map<String, List<Driver>> driverTypeEntryMap = new HashMap<String, List<Driver>>();
//
//        // loop through the drivers list and separate the driver by type
//        for (Driver driver : drivers) {
//            String type = driver.getType();
//            // add to existing type
//            if (driverTypeEntryMap.containsKey(type)) {
//                List<Driver> list = driverTypeEntryMap.get(type);
//                list.add(driver);
//                driverTypeEntryMap.put(type, list);
//            }
//            // add to new type
//            else {
//                List<Driver> list = new ArrayList<Driver>();
//                list.add(driver);
//                driverTypeEntryMap.put(type, list);
//            }
//        }
//        // we need to filter the driver, to check if the type can be select multiple
//        Map<String, DriverType> driverTypeObjectMap = RuntimeVariables.getDriverTypeObjectMap();
//        Map<String, List<Driver>> filteredDriverTypeEntryMap = new HashMap<String, List<Driver>>();
//        for (Map.Entry<String, List<Driver>> typeMapEntry : driverTypeEntryMap.entrySet()) {
//            String typeName = typeMapEntry.getKey();
//            if (driverTypeObjectMap.containsKey(typeName) && driverTypeObjectMap.get(typeName).getIsMultiselectable()) {
//                // we do nothing, because the type can be selected multiple
//                filteredDriverTypeEntryMap.put(typeName, typeMapEntry.getValue());
//            }
//            else if (driverTypeObjectMap.containsKey(typeName)
//                    && !driverTypeObjectMap.get(typeName).getIsMultiselectable()
//                    && typeMapEntry.getValue().size() >= 1) {
//                // contain the key by the type is not multi selectable, we get the first entry
//                // from the type only if the size is larger than 1
//                List<Driver> listWithOnlyOne = Arrays.asList(driverTypeEntryMap.get(typeName).get(0));
//                filteredDriverTypeEntryMap.put(typeName, listWithOnlyOne);
//            }
//            else {
//                // can not find the key, we dont add to the new map
//            }
//        }
//
//        // get different type of drivers
//        List<Run> permutationRuns = new ArrayList<Run>();
//        Set<String> typeNames = filteredDriverTypeEntryMap.keySet();
//        List<List<Driver>> driverTypeList = new ArrayList<List<Driver>>();
//        for (String type : typeNames) {
//            // get the type list
//            List<Driver> driverList = filteredDriverTypeEntryMap.get(type);
//            driverTypeList.add(driverList);
//        }
//        logger.debug("driver map from expanding run " + filteredDriverTypeEntryMap.toString());
//        List<List<Driver>> finalProcessedDriverResult = new ArrayList<List<Driver>>();
//        if (driverTypeList.isEmpty()) {
//            UUID exuuid = UUID.randomUUID();
//            String trace = "exception UUID=" + exuuid + " record with id not exist ";
//            String message = "生成的Driver列表为空，请检查DriverPack设置。问题唯一码[" + exuuid + "]";
//            String code = "REMEDVFT56";
//            try {
//                logger.error("driver map by type " + objectMapper.writeValueAsString(filteredDriverTypeEntryMap));
//            }
//            catch (JsonProcessingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            throw new CustomBadRequestException(null, message, trace, code, exuuid);
//        }
//        else {
//            // this step is called only is the input is not empty
//            logger.debug("generating drivers combination by permutation");
//            this.generateDriverPermutations(driverTypeList, finalProcessedDriverResult, 0, new ArrayList<Driver>());
//        }
//        logger.debug("driver entries from expanding run " + finalProcessedDriverResult.toString());
//
//        // copy the run and put different driver list into it.
//        // ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
//        for (int i = 0; i < finalProcessedDriverResult.size(); i++) {
//            try {
//                String jsonString = objectMapper.writeValueAsString(fullRun);
//                Run copiedRun = objectMapper.readValue(jsonString, Run.class);
//                List<Driver> driversFromList = finalProcessedDriverResult.get(i);
//                copiedRun.setDrivers(jacksonConverter.getObjectMapper().convertValue(driversFromList, JsonNode.class));
//                permutationRuns.add(copiedRun);
//            }
//            catch (JsonProcessingException e) {
//                logger.error("JSON processing error from createing new run, message : {}", e.getLocalizedMessage(), e);
//            }
//        }
//        return permutationRuns;
//    }

    @Override
    public Task processExecutableTaskByRun(Run run, Task task) {
        // setting up the task group by the test case group
        if (run.getGroup() != null) {
            task.setGroup(run.getGroup());
        }

        if (run.getPriority() != null) {
            task.setPriority(run.getPriority());
        }

        return task;
    }

    @Override
    public Object terminateRun(Long runId) throws ObjectNotFoundException {
        if (runId == null) { throw new NullPointerException("Null value for run id in termination call"); }
        // get the uuid of the run set
        RunTaskLinkExample runTaskLinkExample = new RunTaskLinkExample();
        runTaskLinkExample.createCriteria().andRunIdEqualTo(runId);
        List<RunTaskLink> runTaskLinks = runTaskLinkMapper.selectByExample(runTaskLinkExample);
        if (runTaskLinks.isEmpty()) {
            throw new ObjectNotFoundException("Chould not find the linked task for run [" + runId + "]");
        }
        else {
            Run run = runMapper.selectByPrimaryKey(runId);
            UUID taskUuid = runTaskLinks.get(0).getTaskUuid();
            if (run.getStatus().equalsIgnoreCase("NEW")) {
                // we dont need to send the signal to ems
                // update the run set result to terminated status
                Run updateRun = new Run();
                updateRun.setId(runId);
                updateRun.setLog(
                        "Status has been set to TERMINATED due to TERMINATION signal and due to NEW status, not need to send sub-system.");
                updateRun.setStatus("TERMINATED");
                int updateResult = runMapper.updateByPrimaryKeySelective(updateRun);
                if (updateResult == 1) {
                    emsTaskService.terminationTask(taskUuid);
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", 1);
                    MeowlomoResponse response = new MeowlomoResponse();
                    return response;
                }
                else {
                    throw new RuntimeException("Could not update the run status to TERMINATED.");
                }
            }
            MeowlomoResponse emsResponse = emsTaskService.terminationTask(taskUuid);
            if (emsResponse == null) {
                return null;
            }
            else {
                // update the run set result to terminated status
                Run updateRun = new Run();
                updateRun.setId(runId);
                updateRun.setLog("Status has been set to TERMINATED due to TERMINATION signal.");
                updateRun.setStatus("TERMINATED");
                int updateResult = runMapper.updateByPrimaryKeySelective(updateRun);
                if (updateResult == 1) {
                    return emsResponse.getData();
                }
                else {
                    throw new RuntimeException("Could not update the run status to TERMINATED.");
                }
            }
        }
    }
}
