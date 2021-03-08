package com.meowlomo.atm.core.service.notification.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.model.EmailNotificationTarget;
import com.meowlomo.atm.core.model.InstructionResult;
import com.meowlomo.atm.core.model.InstructionResultExample;
import com.meowlomo.atm.core.model.Notification;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.model.RunSetResult;
import com.meowlomo.atm.core.model.StepLog;
import com.meowlomo.atm.core.model.StepLogExample;
import com.meowlomo.atm.core.model.TestCase;
import com.meowlomo.atm.core.model.TestCaseOverwrite;
import com.meowlomo.atm.core.service.base.InstructionResultService;
import com.meowlomo.atm.core.service.base.RunService;
import com.meowlomo.atm.core.service.base.StepLogService;
import com.meowlomo.atm.core.service.base.TestCaseService;
import com.meowlomo.atm.core.service.notification.RunSetResultNotificationService;
import com.meowlomo.atm.core.service.referrence.NotificationReferenceService;
import com.meowlomo.atm.core.service.referrence.RunSetReferenceService;
import com.meowlomo.atm.core.service.referrence.RunSetResultFullReferenceService;
import com.meowlomo.atm.notification.model.QueuedEmail;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RunSetResultNotificationServiceImpl implements RunSetResultNotificationService {

    private final Logger logger = LoggerFactory.getLogger(RunSetResultNotificationServiceImpl.class);

    @Autowired
    private RunSetResultFullReferenceService runSetResultFullReferenceService;
    @Autowired
    private RunSetReferenceService runSetReferenceService;
    @Autowired
    private RunService runService;
    @Autowired
    private InstructionResultService instructionResultService;
    @Autowired
    private NotificationReferenceService notificationReferenceService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    @Autowired
    private StepLogService stepLogService;
    @Value("${spring.mail.content.environment:true}")
    private boolean SEND_ENVIRONMENT;

    @Override
    public void sendRunSetResultEmail(Long runSetResultId) {
        if (runSetResultId != null && runSetResultId > 0) {
            RunSetResult fullRunSetResult = runSetResultFullReferenceService.selectByPrimaryKey(runSetResultId);
            this.sendRunSetResultEmail(fullRunSetResult);
        }
    }

    public void sendRunSetResultEmail(RunSetResult fullRunSetResult) {

        // get the emails list from the runset
        Long runSetId = fullRunSetResult.getRunSetId();
        // replace the
        logger.info("starting email process for run set [" + runSetId + "] from runset result ["
                + fullRunSetResult.getId() + "]");
        RunSet runSet = runSetReferenceService.selectByPrimaryKey(runSetId);
        List<Notification> notifications = runSet.getNotifications();

        // ========= notification is not empty, so we send emails
        if (!notifications.isEmpty()) {
            logger.info(String.format("Detected notification for runset result [%s]", fullRunSetResult.getId()));
            for (Notification notification : notifications) {
                logger.info(String.format("Processing notification [%s] for runset result [%s]", notification.getId(),
                        fullRunSetResult.getId()));
                // let us prepare the elements for the email.
                InternetAddress[] recipients = null;
                String subject = null;
                String messages = null;
                Map<String, Entry<String, String>> attachments = null;

                // get the emails from all the notifications
                recipients = this.generateRecipients(notification);
                subject = this.generateSubject(fullRunSetResult, notification);
                messages = this.generateMessageBody(fullRunSetResult, runSet, notification);
                attachments = this.generateAttachmentForResult(fullRunSetResult);
                logger.info("sent email for run set [" + runSetId + "] notification [" + notification.getId()
                        + "] from runset result [" + fullRunSetResult.getId() + "]");

                // create the email object
                QueuedEmail queuedEmail = new QueuedEmail();
                queuedEmail.setRecipients(recipients);
                queuedEmail.setSubject(subject);
                queuedEmail.setMessages(messages);
                queuedEmail.setHtml(false);
                queuedEmail.setAttachments(attachments);
                // emailClientService.sendMail(recipients, subject, messages, false,
                // attachments);
                RuntimeVariables.EMAIL_QUEUE.add(queuedEmail);
            }
        }
    }

    private InternetAddress[] generateRecipients(Notification notification) {
        Notification fullNotification = notificationReferenceService.selectByPrimaryKey(notification.getId());
        List<EmailNotificationTarget> emailNotificationTargets = fullNotification.getEmailNotificationTargets();
        List<InternetAddress> recipientsList = new ArrayList<InternetAddress>();
        if (!emailNotificationTargets.isEmpty()) {
            // recipients
            Set<String> emailAddressSet = new HashSet<String>();
            for (EmailNotificationTarget emailNotificationTarget : emailNotificationTargets) {
                emailAddressSet.addAll(Arrays.asList(emailNotificationTarget.getEmailAddress().split(";")));
            }
            List<String> emailAddressList = new ArrayList<String>();
            emailAddressList.addAll(emailAddressSet);

            for (String emailAddress : emailAddressList) {
                try {
                    logger.info(emailAddress + " added to sending list.");
                    recipientsList.add(new InternetAddress(emailAddress));
                }
                catch (AddressException e) {
                    logger.error("The email [" + emailAddress + "] address is not valid.", e);
                }
            }

        }
        return recipientsList.toArray(new InternetAddress[recipientsList.size()]);
    }

    private String generateSubject(RunSetResult runSetResult, Notification notification) {
        String subject = "[meowlomo][" + runSetResult.getStatus() + "][" + runSetResult.getName() + "]["
                + runSetResult.getId() + "][" + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + "]["
                + notification.getSubject() + "]";
        return subject;
    }

    private String generateResultLink(RunSetResult runSetResult, RunSet runSet) {
        String link = "localhost" + "/#/RunResult/RunListResult/" + runSet.getId() + "/RunCaseResult";
        if (RuntimeVariables.getProperty().containsKey("meowlomo.atm.hostname")
                && RuntimeVariables.getProperty().containsKey("meowlomo.atm.protocol")
                && RuntimeVariables.getProperty().containsKey("meowlomo.atm.runSetResult.url")
                && RuntimeVariables.getProperty().containsKey("meowlomo.atm.run.url")) {
            link = RuntimeVariables.getProperty().get("meowlomo.atm.protocol")
                    + RuntimeVariables.getProperty().get("meowlomo.atm.hostname")
                    + RuntimeVariables.getProperty().get("meowlomo.atm.runSetResult.url") + runSetResult.getId()
                    + RuntimeVariables.getProperty().get("meowlomo.atm.run.url");
        }
        return link;
    }

    private String generateCIPartContent(JsonNode ciJsonNode) {
        Map<String, Object> CIMaps = jacksonConverter.getObjectMapper().convertValue(ciJsonNode,
                new TypeReference<Map<String, Object>>() {
                });
        Set<String> CIMapsKeys = CIMaps.keySet();
        if (CIMapsKeys != null && !CIMaps.isEmpty()) {
            String content = "*****CI信息开始***** " + System.lineSeparator();
            Iterator<String> iterator = CIMapsKeys.iterator();
            while (iterator.hasNext()) {
                Object CIMapsKey = iterator.next();
                Object CIMapsValue = CIMaps.get(CIMapsKey);
                content += CIMapsKey + "：    " + CIMapsValue + System.lineSeparator();
            }
            content += "*****CI信息结束***** " + System.lineSeparator();
            return content;
        }
        else {
            return "";
        }

    }

    private String generateEnvironmentPartContent(JsonNode environmentJsonNode) {
        Map<String, String> environmentMaps = jacksonConverter.getObjectMapper().convertValue(environmentJsonNode,
                new TypeReference<Map<String, String>>() {
                });
        Set<String> environmentMapsKeys = environmentMaps.keySet();
        if (environmentMapsKeys != null && !environmentMapsKeys.isEmpty()) {
            String content = ".....环境信息开始....." + System.lineSeparator();
            Iterator<String> iterator = environmentMapsKeys.iterator();
            while (iterator.hasNext()) {
                Object environmentMapsKey = iterator.next();
                Object environmentMapsValue = environmentMaps.get(environmentMapsKey);
                if (!environmentMapsKey.toString().startsWith("OS")) {
                    content += environmentMapsKey + "：    " + environmentMapsValue + System.lineSeparator();
                }
            }
            content += ".....环境信息结束....." + System.lineSeparator();
            return content;
        }
        else {
            return "";
        }
    }

    private String generateFailOrErrorTestCaseContent(List<Run> errorRuns) {
        if (errorRuns != null && !errorRuns.isEmpty()) {
            String content = System.lineSeparator();
            content += "=====失败用例信息开始=====" + System.lineSeparator();
            content += "失败用例数：    " + errorRuns.size() + System.lineSeparator();
            for (Run errorRun : errorRuns) {
                content += System.lineSeparator();
                content += "<<<<<失败用例编号：" + errorRun.getTestCaseId() + ">>>>>" + System.lineSeparator();
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
                TestCase testCase = null;
                try {
                    testCase = objectMapper.treeToValue(
                            objectMapper.convertValue(errorRun.getTestCase(), JsonNode.class), TestCase.class);
                }
                catch (JsonProcessingException | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                TestCaseOverwrite testCaseOverwrite = null;
                try {
                    testCaseOverwrite = objectMapper.treeToValue(
                            objectMapper.convertValue(errorRun.getTestCaseOverwrite(), JsonNode.class),
                            TestCaseOverwrite.class);
                }
                catch (JsonProcessingException | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (testCase != null) {
                    content += "用例名称：    " + testCase.getName() + System.lineSeparator();
                    if (testCase.getComment() != null && testCase.getComment().length() != 0) {
                        content += "用例备注：    " + testCase.getComment() + System.lineSeparator();
                    }
                }
                if (testCaseOverwrite != null) {
                    content += "用例参数：    " + testCaseOverwrite.getName() + System.lineSeparator();
                }
                Map<String, JsonNode> parameterMaps = jacksonConverter.getObjectMapper()
                        .convertValue(errorRun.getParameter(), new TypeReference<Map<String, JsonNode>>() {
                        });
                if (parameterMaps.containsKey("environment") && parameterMaps.get("environment") != null
                        && SEND_ENVIRONMENT) {
                    content += this.generateEnvironmentPartContent(parameterMaps.get("environment"));
                }
                List<InstructionResult> instructionResults = errorRun.getInstructionResults();
                content += "步骤总数：    " + instructionResults.size() + System.lineSeparator();
                List<InstructionResult> errorInstructionResults = new ArrayList<InstructionResult>();
                for (InstructionResult instructionResult : instructionResults) {
                    if (!instructionResult.getStatus().equalsIgnoreCase("PASS")) {
                        errorInstructionResults.add(instructionResult);
                    }
                }
                for (InstructionResult errorInstructionResult : errorInstructionResults) {
                    content += "失败步骤目标：    " + errorInstructionResult.getTarget() + System.lineSeparator();
                    // get steplog
                    StepLogExample stepLogExample = new StepLogExample();
                    stepLogExample.createCriteria().andInstructionResultIdEqualTo(errorInstructionResult.getId());
                    stepLogExample.setOrderByClause("created_at asc");
                    List<StepLog> stepLogs = stepLogService.selectByExample(stepLogExample, "PRODUCTION");
                    if (stepLogs.size() > 0) {
                        for (StepLog stepLog : stepLogs) {
                            content += this.DateToString(stepLog.getCreatedAt()) + System.lineSeparator();
                            content += this.getLimitLengthString(stepLog.getMessage()) + System.lineSeparator();
                        }
                    }
                    // get fail details or pictures links
                    Map<String, Object> instructionMaps = new HashMap<String, Object>();
                    instructionMaps = jacksonConverter.getObjectMapper().convertValue(
                            errorInstructionResult.getInstruction(), new TypeReference<Map<String, Object>>() {
                            });
                    String picturelink = "";
                    if (instructionMaps.get("type").toString().equalsIgnoreCase("REST_API")) {
                        Long runId = errorInstructionResult.getRunId();
                        Long testCaseId = runService.selectByPrimaryKey(runId).getTestCaseId();
                        Long projectId = testCaseService.selectByPrimaryKey(testCaseId).getProjectId();
                        picturelink = "失败详情路径：    " + "localhost" + "/#/RunResult/ProjectRunResult/" + projectId
                                + "/TestCasesResults/" + testCaseId + "/Runs/" + runId + "/CaseInstructionsResults/"
                                + errorInstructionResult.getId() + "/ApiInstructionInfo";
                        if (RuntimeVariables.getProperty().containsKey("meowlomo.atm.hostname")
                                && RuntimeVariables.getProperty().containsKey("meowlomo.atm.protocol")) {
                            picturelink = "失败详情路径：    " + RuntimeVariables.getProperty().get("meowlomo.atm.protocol")
                                    + RuntimeVariables.getProperty().get("meowlomo.atm.hostname")
                                    + "/#/RunResult/ProjectRunResult/" + projectId + "/TestCasesResults/" + testCaseId
                                    + "/Runs/" + runId + "/CaseInstructionsResults/" + errorInstructionResult.getId()
                                    + "/ApiInstructionInfo";
                        }
                    }
                    else {
                        picturelink = "失败截图路径：    " + "localhost" + "/result/" + errorInstructionResult.getId()
                                + errorInstructionResult.getLogicalOrderIndex();
                        if (RuntimeVariables.getProperty().containsKey("meowlomo.atm.hostname")
                                && RuntimeVariables.getProperty().containsKey("meowlomo.atm.protocol")) {
                            picturelink = "失败截图路径：    " + RuntimeVariables.getProperty().get("meowlomo.atm.protocol")
                                    + RuntimeVariables.getProperty().get("meowlomo.atm.hostname") + "/result/"
                                    + errorInstructionResult.getRunId() + "/"
                                    + errorInstructionResult.getLogicalOrderIndex() + "/";
                        }
                    }
                    content += picturelink + System.lineSeparator();
                }
            }
            content += "=====失败用例信息结束=====" + System.lineSeparator();
            return content;
        }
        else {
            logger.warn("Trying to generate ru nset result stattistic with null or empty runs as parameter.");
            return "";
        }
    }

//    private String generateBugTestCaseContent(List<Run> bugRuns) {
//        if (bugRuns != null && !bugRuns.isEmpty()) {
//            String content = System.lineSeparator();
//            content += "=====需要根据用例名称判断是否是bug的用例信息开始=====" + System.lineSeparator();
//            content += "解释说明：    这类用例可能结果是pass但是实际是bug，或者结果是pass需要再查看截图才能判断是否是bug，用例名称或者参数会有说明"
//                    + System.lineSeparator();
//            content += "用例数：    " + bugRuns.size() + System.lineSeparator();
//            for (Run bugRun : bugRuns) {
//                content += System.lineSeparator();
//                content += "<<<<<用例编号：" + bugRun.getTestCaseId() + ">>>>>" + System.lineSeparator();
//                content += "用例状态：    " + bugRun.getStatus() + System.lineSeparator();
//                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
//                TestCase testCase = null;
//                try {
//                    testCase = objectMapper.treeToValue(objectMapper.convertValue(bugRun.getTestCase(), JsonNode.class),
//                            TestCase.class);
//                }
//                catch (JsonProcessingException | IllegalArgumentException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                TestCaseOverwrite testCaseOverwrite = null;
//                try {
//                    testCaseOverwrite = objectMapper.treeToValue(
//                            objectMapper.convertValue(bugRun.getTestCaseOverwrite(), JsonNode.class),
//                            TestCaseOverwrite.class);
//                }
//                catch (JsonProcessingException | IllegalArgumentException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                if (testCase != null) {
//                    content += "用例名称：    " + testCase.getName() + System.lineSeparator();
//                    if (testCase.getComment() != null && testCase.getComment().length() != 0) {
//                        content += "用例备注：    " + testCase.getComment() + System.lineSeparator();
//                    }
//                }
//                if (testCaseOverwrite != null) {
//                    content += "用例参数：    " + testCaseOverwrite.getName() + System.lineSeparator();
//                }
//                Map<String, JsonNode> parameterMaps = jacksonConverter.getObjectMapper()
//                        .convertValue(bugRun.getParameter(), new TypeReference<Map<String, JsonNode>>() {
//                        });
//                if (parameterMaps.containsKey("environment") && parameterMaps.get("environment") != null
//                        && SEND_ENVIRONMENT) {
//                    content += this.generateEnvironmentPartContent(parameterMaps.get("environment"));
//                }
//                List<InstructionResult> instructionResults = bugRun.getInstructionResults();
//                content += "步骤总数：    " + instructionResults.size() + System.lineSeparator();
//                List<InstructionResult> errorInstructionResults = new ArrayList<InstructionResult>();
//                // 取最后一个InstructionResult
//                if (instructionResults.size() > 0) {
//                    errorInstructionResults.add(instructionResults.get(instructionResults.size() - 1));
//                }
//                for (InstructionResult errorInstructionResult : errorInstructionResults) {
//                    content += "最后一步步骤目标：    " + errorInstructionResult.getTarget() + System.lineSeparator();
//                    // get steplog
//                    StepLogExample stepLogExample = new StepLogExample();
//                    stepLogExample.createCriteria().andInstructionResultIdEqualTo(errorInstructionResult.getId());
//                    stepLogExample.setOrderByClause("created_at asc");
//                    List<StepLog> stepLogs = stepLogService.selectByExample(stepLogExample, "PRODUCTION");
//                    if (stepLogs.size() > 0) {
//                        for (StepLog stepLog : stepLogs) {
//                            content += this.DateToString(stepLog.getCreatedAt()) + System.lineSeparator();
//                            content += this.getLimitLengthString(stepLog.getMessage()) + System.lineSeparator();
//                        }
//                    }
//                    // get fail details or pictures links
//                    Map<String, Object> instructionMaps = new HashMap<String, Object>();
//                    instructionMaps = jacksonConverter.getObjectMapper().convertValue(
//                            errorInstructionResult.getInstruction(), new TypeReference<Map<String, Object>>() {
//                            });
//                    String picturelink = "";
//                    if (instructionMaps.get("type").toString().equalsIgnoreCase("REST_API")) {
//                        Long runId = errorInstructionResult.getRunId();
//                        Long testCaseId = runService.selectByPrimaryKey(runId).getTestCaseId();
//                        Long projectId = testCaseService.selectByPrimaryKey(testCaseId).getProjectId();
//                        picturelink = "详情路径：    " + "localhost" + "/#/RunResult/ProjectRunResult/" + projectId
//                                + "/TestCasesResults/" + testCaseId + "/Runs/" + runId + "/CaseInstructionsResults/"
//                                + errorInstructionResult.getId() + "/ApiInstructionInfo";
//                        if (RuntimeVariables.getProperty().containsKey("meowlomo.atm.hostname")
//                                && RuntimeVariables.getProperty().containsKey("meowlomo.atm.protocol")) {
//                            picturelink = "详情路径：    " + RuntimeVariables.getProperty().get("meowlomo.atm.protocol")
//                                    + RuntimeVariables.getProperty().get("meowlomo.atm.hostname")
//                                    + "/#/RunResult/ProjectRunResult/" + projectId + "/TestCasesResults/" + testCaseId
//                                    + "/Runs/" + runId + "/CaseInstructionsResults/" + errorInstructionResult.getId()
//                                    + "/ApiInstructionInfo";
//                        }
//                    }
//                    else {
//                        picturelink = "截图路径：    " + "localhost" + "/result/" + errorInstructionResult.getId()
//                                + errorInstructionResult.getLogicalOrderIndex();
//                        if (RuntimeVariables.getProperty().containsKey("meowlomo.atm.hostname")
//                                && RuntimeVariables.getProperty().containsKey("meowlomo.atm.protocol")) {
//                            picturelink = "截图路径：    " + RuntimeVariables.getProperty().get("meowlomo.atm.protocol")
//                                    + RuntimeVariables.getProperty().get("meowlomo.atm.hostname") + "/result/"
//                                    + errorInstructionResult.getRunId() + "/"
//                                    + errorInstructionResult.getLogicalOrderIndex() + "/";
//                        }
//                    }
//                    content += picturelink + System.lineSeparator();
//                }
//            }
//            content += "=====需要根据用例名称判断是否是bug的用例信息结束=====" + System.lineSeparator();
//            return content;
//        }
//        else {
//            logger.warn("Trying to generate ru nset result stattistic with null or empty runs as parameter.");
//            return "";
//        }
//    }

    private String generateResultStatistic(RunSetResult fullRunSetResult, List<Run> errorRuns,
            Set<Long> allTestCasesIds, Set<Long> noPassTestCasesIds) {
        if (fullRunSetResult != null && errorRuns != null && allTestCasesIds != null && noPassTestCasesIds != null) {
            long totalTestCaseNum = allTestCasesIds.size();
            long totalRunNum = fullRunSetResult.getRuns().size();
            float passRunCount = 0;
            float passTestCount = 0;
            passTestCount = totalTestCaseNum - noPassTestCasesIds.size();
            passRunCount = totalRunNum - errorRuns.size();
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            String testCasePassPercent = numberFormat.format((float) passTestCount / (float) totalTestCaseNum * 100);
            String runPassPercent = numberFormat.format((float) passRunCount / (float) totalRunNum * 100);

            // result info part
            String content = "*****统计信息开始*****" + System.lineSeparator();
            content += "用例运行计划编号：    " + fullRunSetResult.getRunSetId() + System.lineSeparator();
            content += "用例运行计划名称：    " + fullRunSetResult.getName() + System.lineSeparator();
            content += "关联用例数：    " + allTestCasesIds.size() + System.lineSeparator();
            content += "用例通过率：    " + testCasePassPercent + "%" + System.lineSeparator();
            content += "用例执行数：    " + fullRunSetResult.getRuns().size() + System.lineSeparator();
            content += "用例执行通过率：    " + runPassPercent + "%" + System.lineSeparator();
            content += "*****统计信息结束*****" + System.lineSeparator();
            return content;
        }
        else {
            logger.error("Trying to generate ru nset result stattistic with null parameter.");
            return "";
        }
    }

    private String generateMessageBody(RunSetResult fullRunSetResult, RunSet runSet, Notification notification) {
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        String messages = notification.getMessages();
        messages = messages + "\r\n运行结果见链接：" + this.generateResultLink(fullRunSetResult, runSet);
        // get the runs and test case from the run set result
        List<Run> runs = fullRunSetResult.getRuns();

        if (runs == null || runs.isEmpty()) { return messages; }

        Set<Long> allTestCasesIds = new HashSet<Long>();
        Set<Long> noPassTestCasesIds = new HashSet<Long>();
        List<Run> errorRuns = new ArrayList<Run>();
        for (Run run : runs) {
            allTestCasesIds.add(run.getTestCaseId());
            if (!run.getStatus().equalsIgnoreCase("PASS")) {
                noPassTestCasesIds.add(run.getTestCaseId());
                errorRuns.add(run);
            }
        }
        // the result content part
        String content = "##########测试结果开始##########" + System.lineSeparator();
        content += "############################" + System.lineSeparator();
        content += this.generateResultStatistic(fullRunSetResult, errorRuns, allTestCasesIds, noPassTestCasesIds);
        if (!runs.isEmpty()) {
            Map<String, JsonNode> parameterMaps = mapper.convertValue(runs.get(0).getParameter(),
                    new TypeReference<Map<String, JsonNode>>() {
                    });
            if (parameterMaps.containsKey("CI") && parameterMaps.get("CI") != null) {
                content += this.generateCIPartContent(parameterMaps.get("CI"));
            }
            content += this.generateFailOrErrorTestCaseContent(errorRuns);
        }

        content += "############################" + System.lineSeparator();
        content += "##########测试结果结束##########" + System.lineSeparator();

        messages = messages + System.lineSeparator() + content;
        return messages;
    }

    private Map<String, Entry<String, String>> generateAttachmentForResult(RunSetResult fullRunSetResult) {
        Map<String, Entry<String, String>> returnMap = new HashMap<String, Entry<String, String>>();
        List<Run> runs = fullRunSetResult.getRuns();
        Set<Long> errorTestCaseIds = new HashSet<Long>();
        List<Run> errorRuns = new ArrayList<Run>();
        for (Run run : runs) {
            if (!run.getStatus().equalsIgnoreCase("PASS")) {
                errorRuns.add(run);
                errorTestCaseIds.add(run.getTestCaseId());
            }
        }
        if (!errorTestCaseIds.isEmpty()) {
            String content = "";
            content += "失败接口类型测试步骤详细信息：" + System.lineSeparator() + System.lineSeparator();
            for (Run errorRun : errorRuns) {
                InstructionResultExample instructionResultExample = new InstructionResultExample();
                instructionResultExample.createCriteria().andRunIdEqualTo(errorRun.getId());
                instructionResultExample.setOrderByClause(" id ");
                List<InstructionResult> instructionResults = instructionResultService
                        .selectByExample(instructionResultExample, null);
                List<InstructionResult> errorInstructionResults = new ArrayList<InstructionResult>();
                for (InstructionResult instructionResult : instructionResults) {
                    if (!instructionResult.getStatus().equalsIgnoreCase("PASS")) {
                        errorInstructionResults.add(instructionResult);
                    }
                }
                boolean haveApiInstruction = false;
                for (InstructionResult errorInstructionResult : errorInstructionResults) {
                    // print api instruction result
                    Map<String, Object> instructionMaps = new HashMap<String, Object>();
                    if (errorInstructionResult.getInstruction() != null) {
                        instructionMaps = jacksonConverter.getObjectMapper().convertValue(
                                errorInstructionResult.getInstruction(), new TypeReference<Map<String, Object>>() {
                                });
                    }
                    if (instructionMaps.get("type").equals("REST_API")) {
                        // print error REST_API instruction
                        haveApiInstruction = true;
                        String jsonPathString = "【无】";
                        String expectedValueString = "【无】";
                        String requestBodyString = "【无】";
                        String requestMethodTypeString = "【无】";
                        String requestHeaderString = "【无】";
                        String requestUrlString = "【无】";
                        Map<String, JsonNode> instructionJsonMaps = jacksonConverter.getObjectMapper().convertValue(
                                errorInstructionResult.getInstruction(), new TypeReference<Map<String, JsonNode>>() {
                                });
                        Object data = null;
                        if (instructionJsonMaps.containsKey("data")) {
                            data = instructionJsonMaps.get("data");
                        }
                        if (data != null) {
                            Map<String, JsonNode> dataMaps = new HashMap<String, JsonNode>();
                            dataMaps = jacksonConverter.getObjectMapper().convertValue(data,
                                    new TypeReference<Map<String, JsonNode>>() {
                                    });
                            if (dataMaps.containsKey("body") && dataMaps.get("body") != null) {
                                requestBodyString = dataMaps.get("body").toString();
                            }
                            if (dataMaps.containsKey("method") && dataMaps.get("method") != null) {
                                requestMethodTypeString = dataMaps.get("method").toString();
                            }
                            if (dataMaps.containsKey("requestHeaders") && dataMaps.get("requestHeaders") != null) {
                                requestHeaderString = dataMaps.get("requestHeaders").toString();
                            }
                            if (dataMaps.containsKey("url") && dataMaps.get("url") != null) {
                                requestUrlString = dataMaps.get("url").toString();
                            }
                            Object jsonPathPackage = dataMaps.get("jsonPathPackage");
                            if (jsonPathPackage != null
                                    && !(dataMaps.get("jsonPathPackage").toString().equals("null"))) {
                                Map<String, Object> jsonPathPackageMaps = jacksonConverter.getObjectMapper()
                                        .convertValue(jsonPathPackage, new TypeReference<Map<String, Object>>() {
                                        });
                                if (jsonPathPackageMaps.containsKey("jsonPath")
                                        && jsonPathPackageMaps.get("jsonPath") != null) {
                                    jsonPathString = jsonPathPackageMaps.get("jsonPath").toString();
                                }
                                if (jsonPathPackageMaps.containsKey("expectedValue")
                                        && jsonPathPackageMaps.get("expectedValue") != null) {
                                    expectedValueString = jsonPathPackageMaps.get("expectedValue").toString();
                                }
                            }
                        }
                        Object outputType = errorInstructionResult.getOutputType();
                        String responseHeadersString = "【返回为空】";
                        if (outputType != null) {
                            responseHeadersString = outputType.toString();
                        }
                        Object outputData = errorInstructionResult.getOutputData();
                        String responseBodyString = "【返回为空】";
                        if (outputData != null) {
                            responseBodyString = outputData.toString();
                        }
                        // print error REST_API instruction
                        content += "####################################" + System.lineSeparator();
                        content += "####################################" + System.lineSeparator();
                        content += "失败用例编号：    " + errorRun.getTestCaseId() + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤目标：    " + errorInstructionResult.getTarget() + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤requestMethod：    " + System.lineSeparator();
                        content += requestMethodTypeString + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤requestUrl：    " + System.lineSeparator();
                        content += requestUrlString + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤requestHeaders：    " + System.lineSeparator();
                        content += requestHeaderString + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤requestBody：    " + System.lineSeparator();
                        content += requestBodyString + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤responseHeaders：    " + System.lineSeparator();
                        content += responseHeadersString + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤responseBody：    " + System.lineSeparator();
                        content += responseBodyString + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤JsonPath：    " + System.lineSeparator();
                        content += jsonPathString + System.lineSeparator();
                        content += "==============================" + System.lineSeparator();
                        content += "失败步骤JsonPath预期值：    " + System.lineSeparator();
                        content += expectedValueString + System.lineSeparator();
                    }
                }
                if (haveApiInstruction) {
                    Map.Entry<String, String> attachmentEntry = new AbstractMap.SimpleEntry<String, String>(
                            "text/plain", content);
                    returnMap.put("api result.txt", attachmentEntry);
                }
            }
        }
        return returnMap;
    }

    private String DateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    private String getLimitLengthString(String str) {
        if (str.length() > 200) {
            String str1 = str.substring(0, 200) + "......";
            return str1;
        }
        else {
            return str;
        }
    }
}
