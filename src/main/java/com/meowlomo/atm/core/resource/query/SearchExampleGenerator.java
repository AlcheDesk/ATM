package com.meowlomo.atm.core.resource.query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageRowBounds;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.model.ExecutionLogExample;
import com.meowlomo.atm.core.model.FileExample;
import com.meowlomo.atm.core.model.InstructionResultExample;
import com.meowlomo.atm.core.model.RunExample;
import com.meowlomo.atm.core.model.RunExecutionInfoExample;
import com.meowlomo.atm.core.model.RunSetResultExample;
import com.meowlomo.atm.core.model.StepLogExample;

@Component
public class SearchExampleGenerator {

    private final Logger logger = LoggerFactory.getLogger(SearchExampleGenerator.class);

    private String regex = "[0-9, /,]+";

    private String processIdsString(String idsString) {
        if (idsString == null || idsString.isEmpty()) {
            return null;
        }
        else {
            if (idsString != null && idsString.matches(regex)) {
                return idsString;
            }
            else {
                logger.error("Getting a ids serach string with non number input, will be ignore");
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T, C> T generateExample(UriInfo uriInfo, Object criteria, Class<T> example) {
        // check the type is a class of Example.Criteria
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();

            // generate the example object and then create the criteria instance
            Object exampleObject = example.newInstance();
            Object criteriaObject = null;
            if (criteria == null) {
                // get the create Criteria method
                Method exampleCriteriaMethod = exampleObject.getClass().getDeclaredMethod("createCriteria");
                criteriaObject = exampleCriteriaMethod.invoke(exampleObject);
            }
            else {
                // initialize the criteria
                criteriaObject = criteria;
            }
            // get the methods
            Map<String, Method> methods = this.getMathods(criteriaObject.getClass().getDeclaredMethods());
            Set<String> methodNames = methods.keySet();

            // check the field from the query string map
            String ids = this.processIdsString(queryParams.getFirst("ids"));
            String orderIndexs = queryParams.getFirst("orderIndex");
            String name = queryParams.getFirst("name");
            String names = queryParams.getFirst("names");
            String username = queryParams.getFirst("username");
            String comment = queryParams.getFirst("comment");
            String startDate = queryParams.getFirst("startDate");
            String endDate = queryParams.getFirst("endDate");
            String orderBy = queryParams.getFirst("orderBy");
            String status = queryParams.getFirst("status");
            String type = queryParams.getFirst("type");
            String runType = queryParams.getFirst("runType");
            String mode = queryParams.getFirst("mode");
            String target = queryParams.getFirst("target");
            String message = queryParams.getFirst("message");
            String values = queryParams.getFirst("values");
            String log = queryParams.getFirst("log");
            String isDriver = queryParams.getFirst("isDriver");
            String isDefault = queryParams.getFirst("isDefault");
            String isActive = queryParams.getFirst("active");
            String isDeleted = queryParams.getFirst("deleted");
            String sha256 = queryParams.getFirst("sha256");
            String originalName = queryParams.getFirst("originalName");

            String latestRunUpdatedAt = queryParams.getFirst("latestRunUpdatedAt");
            String latestRunCreatedAt = queryParams.getFirst("latestRunCreatedAt");
            String latestDevRunUpdatedAt = queryParams.getFirst("latestDevRunUpdatedAt");
            String latestDevRunCreatedAt = queryParams.getFirst("latestDevRunCreatedAt");
            String latestRunId = queryParams.getFirst("latestRunId");
            String latestDevRunId = queryParams.getFirst("latestDevRunId");
            String statusNotIn = queryParams.getFirst("statusNotIn");
            String statusIn = queryParams.getFirst("statusIn");
            String devStatusNotIn = queryParams.getFirst("devStatusNotIn");
            String devStatusIn = queryParams.getFirst("devStatusIn");

            String projectName = queryParams.getFirst("projectName");
            String projectIds = this.processIdsString(queryParams.getFirst("projectIds"));
            String projectStartDate = queryParams.getFirst("projectStartDate");
            String projectEndDate = queryParams.getFirst("projectEndDate");

            String testCaseName = queryParams.getFirst("testCaseName");
            String testCaseIds = this.processIdsString(queryParams.getFirst("testCaseIds"));
            String testCaseStartDate = queryParams.getFirst("projectStartDate");
            String testCaseEndDate = queryParams.getFirst("projectEndDate");
            String testCaseOverwriteName = queryParams.getFirst("testCaseOverwriteName");
            String driverPackName = queryParams.getFirst("driverPackName");

            String runName = queryParams.getFirst("runName");
            String runIds = this.processIdsString(queryParams.getFirst("runIds"));
            String runStartDate = queryParams.getFirst("runStartDate");
            String runEndDate = queryParams.getFirst("runEndDate");

            String testCaseIsDeleted = queryParams.getFirst("testCaseIsDeleted");
            String projectIsDeleted = queryParams.getFirst("projectIsDeleted");

            if (devStatusNotIn != null) {
                if (criteriaObject.getClass().getName().toLowerCase().contains("testcaseexecutioninfo")
                        && methodNames.contains("andLatestDevRunStatusNotIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = devStatusNotIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andLatestDevRunStatusNotIn");
                    method.invoke(criteriaObject, idsList);
                }
            }

            if (devStatusIn != null) {
                if (criteriaObject.getClass().getName().toLowerCase().contains("testcaseexecutioninfo")
                        && methodNames.contains("andLatestDevRunStatusIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = devStatusIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andLatestDevRunStatusIn");
                    method.invoke(criteriaObject, idsList);
                }
            }

            if (statusNotIn != null) {
                if (criteriaObject.getClass().getName().toLowerCase().contains("testcaseexecutioninfo")
                        && methodNames.contains("andLatestRunStatusNotIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = statusNotIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andLatestRunStatusNotIn");
                    method.invoke(criteriaObject, idsList);
                }
                else if (criteriaObject.getClass().getName().toLowerCase().contains("runexecutioninfo")
                        && methodNames.contains("andRunStatusNotIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = statusNotIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andRunStatusNotIn");
                    method.invoke(criteriaObject, idsList);
                }
                else if (methodNames.contains("andStatusNotIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = statusNotIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andStatusNotIn");
                    method.invoke(criteriaObject, idsList);
                }
            }

            if (statusIn != null) {
                if (criteriaObject.getClass().getName().toLowerCase().contains("testcaseexecutioninfo")
                        && methodNames.contains("andLatestRunStatusIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = statusIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andLatestRunStatusIn");
                    method.invoke(criteriaObject, idsList);
                }
                else if (criteriaObject.getClass().getName().toLowerCase().contains("runexecutioninfo")
                        && methodNames.contains("andRunStatusIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = statusNotIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andRunStatusIn");
                    method.invoke(criteriaObject, idsList);
                }
                else if (methodNames.contains("andStatusIn")) {
                    Set<String> statusArray = RuntimeVariables.getStatuses();
                    String[] statusStringArray = statusIn.trim().split(",");
                    statusArray.retainAll(Sets.newHashSet(statusStringArray));
                    List<String> idsList = new ArrayList<String>(statusArray);
                    Method method = methods.get("andStatusIn");
                    method.invoke(criteriaObject, idsList);
                }
            }

            if (latestRunId != null && latestRunId.replaceAll("\\s+", "").toLowerCase().equals("notnull")
                    && methodNames.contains("andLatestRunIdIsNotNull")) {
                Method method = methods.get("andLatestRunIdIsNotNull");
                method.invoke(criteriaObject);
            }

            if (latestDevRunId != null && latestDevRunId.replaceAll("\\s+", "").toLowerCase().equals("notnull")
                    && methodNames.contains("andLatestDevRunIdIsNotNull")) {
                Method method = methods.get("andLatestDevRunIdIsNotNull");
                method.invoke(criteriaObject);
            }

            if (ids != null && methodNames.contains("andIdIn")) {
                String[] idsStringArray = ids.trim().split(",");
                List<Long> idsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                        .collect(Collectors.toList());
                Method method = methods.get("andIdIn");
                method.invoke(criteriaObject, idsList);
            }

            if (testCaseIds != null && methodNames.contains("andTestCaseIdIn")) {
                String[] idsStringArray = testCaseIds.trim().split(",");
                List<Long> testCaseIdsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                        .collect(Collectors.toList());
                Method method = methods.get("andTestCaseIdIn");
                method.invoke(criteriaObject, testCaseIdsList);
            }

            if (projectIds != null && methodNames.contains("andProjectIdIn")) {
                String[] idsStringArray = testCaseIds.trim().split(",");
                List<Long> projectIdsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                        .collect(Collectors.toList());
                Method method = methods.get("andProjectIdIn");
                method.invoke(criteriaObject, projectIdsList);
            }

            if (runIds != null && methodNames.contains("andRunIdIn")) {
                String[] idsStringArray = testCaseIds.trim().split(",");
                List<Long> runIdsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                        .collect(Collectors.toList());
                Method method = methods.get("andRunIdIn");
                method.invoke(criteriaObject, runIdsList);
            }

            if (orderIndexs != null && methodNames.contains("andOrderIndexIn")) {
                String[] orderIndexsStringArray = orderIndexs.trim().split(",");
                List<Long> orderIndexsList = Arrays.asList(orderIndexsStringArray).stream().mapToLong(Long::parseLong)
                        .boxed().collect(Collectors.toList());
                Method method = methods.get("andOrderIndexIn");
                method.invoke(criteriaObject, orderIndexsList);
            }

            if (names != null && methodNames.contains("andNameIn")) {
                String[] idsStringArray = names.trim().split(",");
                List<String> namesList = Arrays.asList(idsStringArray);
                Method method = methods.get("andNameIn");
                method.invoke(criteriaObject, namesList);
            }

            if (values != null && methodNames.contains("andValueIn")) {
                String[] idsStringArray = values.trim().split(",");
                List<String> valuesList = Arrays.asList(idsStringArray);
                Method method = methods.get("andValueIn");
                method.invoke(criteriaObject, valuesList);
            }

            if (name != null && methodNames.contains("andNameLikeInsensitive")) {
                Method method = methods.get("andNameLikeInsensitive");
                method.invoke(criteriaObject, name);
            }
            else if (name != null && criteriaObject.getClass().getName().toLowerCase().contains("runexecutioninfo")
                    && methodNames.contains("andRunNameLikeInsensitive")) {
                Method method = methods.get("andRunNameLikeInsensitive");
                method.invoke(criteriaObject, name);
            }
            else if (name != null && criteriaObject.getClass().getName().toLowerCase().contains("projectexecutioninfo")
                    && methodNames.contains("andProjectNameLikeInsensitive")) {
                Method method = methods.get("andProjectNameLikeInsensitive");
                method.invoke(criteriaObject, name);
            }
            else if (name != null && criteriaObject.getClass().getName().toLowerCase().contains("testcaseexecutioninfo")
                    && methodNames.contains("andTestCaseNameLikeInsensitive")) {
                Method method = methods.get("andTestCaseNameLikeInsensitive");
                method.invoke(criteriaObject, name);
            }

            if (testCaseName != null && methodNames.contains("andTestCaseNameLikeInsensitive")) {
                Method method = methods.get("andTestCaseNameLikeInsensitive");
                method.invoke(criteriaObject, testCaseName);
            }

            if (runName != null && methodNames.contains("andRunNameLikeInsensitive")) {
                Method method = methods.get("andRunNameLikeInsensitive");
                method.invoke(criteriaObject, runName);
            }

            if (projectName != null && methodNames.contains("andProjectNameLikeInsensitive")) {
                Method method = methods.get("andProjectNameLikeInsensitive");
                method.invoke(criteriaObject, projectName);
            }

            if (testCaseOverwriteName != null && methodNames.contains("andTestCaseOverwriteNameLikeInsensitive")) {
                Method method = methods.get("andTestCaseOverwriteNameLikeInsensitive");
                method.invoke(criteriaObject, testCaseOverwriteName);
            }

            if (driverPackName != null && methodNames.contains("andDriverPackNameLikeInsensitive")) {
                Method method = methods.get("andDriverPackNameLikeInsensitive");
                method.invoke(criteriaObject, driverPackName);
            }

            if (username != null && methodNames.contains("andUsernameLikeInsensitive")) {
                Method method = methods.get("andUsernameLikeInsensitive");
                method.invoke(criteriaObject, username);
            }

            if (log != null && methodNames.contains("andLogLikeInsensitive")) {
                Method method = methods.get("andLogLikeInsensitive");
                method.invoke(criteriaObject, log);
            }

            if (status != null && methodNames.contains("andStatusEqualTo")) {
                Method method = methods.get("andStatusEqualTo");
                method.invoke(criteriaObject, status);
            }

            if (type != null && methodNames.contains("andTypeEqualTo")) {
                Method method = methods.get("andTypeEqualTo");
                method.invoke(criteriaObject, type);
            }

            if (target != null && methodNames.contains("andTargetLikeInsensitive")) {
                Method method = methods.get("andTargetLikeInsensitive");
                method.invoke(criteriaObject, target);
            }

            if (message != null && methodNames.contains("andMessageLikeInsensitive")) {
                Method method = methods.get("andMessageLikeInsensitive");
                method.invoke(criteriaObject, message);
            }

            if (comment != null && methodNames.contains("andCommentLikeInsensitive")) {
                Method method = methods.get("andCommentLikeInsensitive");
                method.invoke(criteriaObject, comment);
            }

            if (sha256 != null && methodNames.contains("andSha256LikeInsensitive")) {
                Method method = methods.get("andSha256LikeInsensitive");
                method.invoke(criteriaObject, sha256);
            }

            if (originalName != null && methodNames.contains("andOriginalNameLikeInsensitive")) {
                Method method = methods.get("andOriginalNameLikeInsensitive");
                method.invoke(criteriaObject, originalName);
            }

            if (isActive != null && methodNames.contains("andActiveEqualTo")) {
                Method method = methods.get("andActiveEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isActive));
            }

            // default action for active filter
            if (isActive == null && methodNames.contains("andActiveEqualTo")) {
                Method method = methods.get("andActiveEqualTo");
                method.invoke(criteriaObject, Boolean.TRUE);
            }

            // default delete is set to false
            if (isDeleted == null && methodNames.contains("andDeletedEqualTo")) {
                Method method = methods.get("andDeletedEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject("false"));
            }

            if (isDeleted != null && methodNames.contains("andDeletedEqualTo") && !isDeleted.equalsIgnoreCase("all")) {
                Method method = methods.get("andDeletedEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDeleted));
            }

            if (isDeleted != null && isDeleted.equalsIgnoreCase("all") && methodNames.contains("andDeletedIsNotNull")) {
                Method method = methods.get("andDeletedIsNotNull");
                method.invoke(criteriaObject);
            }

            // check if this is run example
            if (criteriaObject instanceof RunExample.Criteria && methodNames.contains("andTypeEqualTo")) {
                Method method = methods.get("andTypeEqualTo");
                if (type != null) { // has type for run
                    method.invoke(criteriaObject, type);
                }
                else if (mode != null) {// has mode for run
                    method.invoke(criteriaObject, mode);
                }
                else {// default for run
                    method.invoke(criteriaObject, "PRODUCTION");
                }
            }
            else if (criteriaObject instanceof FileExample.Criteria || criteriaObject instanceof StepLogExample.Criteria
                    || criteriaObject instanceof InstructionResultExample.Criteria
                    || criteriaObject instanceof RunSetResultExample.Criteria
                    || criteriaObject instanceof ExecutionLogExample.Criteria
                    || criteriaObject instanceof RunExecutionInfoExample.Criteria) {

                Method method = methods.get("andRunTypeEqualTo");
                if (runType != null) { // has run type
                    method.invoke(criteriaObject, runType);
                }
                else if (mode != null) {// has mode
                    method.invoke(criteriaObject, mode);
                }
                else {// default for run
                    method.invoke(criteriaObject, "PRODUCTION");
                }
            }

            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            if (startDate != null && org.apache.commons.lang3.StringUtils.isNumeric(startDate)
                    && methodNames.contains("andCreatedAtGreaterThanOrEqualTo")) {
                startCal.setTimeInMillis(Long.valueOf(startDate));
                Method method = methods.get("andCreatedAtGreaterThanOrEqualTo");
                method.invoke(criteriaObject, startCal.getTime());
            }

            if (startDate != null && org.apache.commons.lang3.StringUtils.isNumeric(startDate) && endDate != null
                    && org.apache.commons.lang3.StringUtils.isNumeric(endDate)
                    && methods.keySet().contains("andCreatedAtBetween")) {
                startCal.setTimeInMillis(Long.valueOf(startDate));
                endCal.setTimeInMillis(Long.valueOf(endDate));
                Method method = methods.get("andCreatedAtBetween");
                method.invoke(criteriaObject, startCal.getTime(), endCal.getTime());
            }

            if (endDate != null && org.apache.commons.lang3.StringUtils.isNumeric(endDate)
                    && methods.keySet().contains("andCreatedAtLessThanOrEqualTo")) {
                endCal.setTimeInMillis(Long.valueOf(endDate));
                Method method = methods.get("andCreatedAtLessThanOrEqualTo");
                method.invoke(criteriaObject, endCal.getTime());
            }

            if (latestRunUpdatedAt != null && org.apache.commons.lang3.StringUtils.isNumeric(latestRunUpdatedAt)
                    && methods.keySet().contains("andCreatedAtLessThanOrEqualTo")) {
                endCal.setTimeInMillis(Long.valueOf(latestRunUpdatedAt));
                Method method = methods.get("andCreatedAtLessThanOrEqualTo");
                method.invoke(criteriaObject, endCal.getTime());
            }

            if (latestRunCreatedAt != null && org.apache.commons.lang3.StringUtils.isNumeric(latestRunCreatedAt)
                    && methods.keySet().contains("andCreatedAtLessThanOrEqualTo")) {
                endCal.setTimeInMillis(Long.valueOf(latestRunCreatedAt));
                Method method = methods.get("andCreatedAtLessThanOrEqualTo");
                method.invoke(criteriaObject, endCal.getTime());
            }

            if (latestDevRunUpdatedAt != null && org.apache.commons.lang3.StringUtils.isNumeric(latestDevRunUpdatedAt)
                    && methods.keySet().contains("andCreatedAtLessThanOrEqualTo")) {
                endCal.setTimeInMillis(Long.valueOf(latestDevRunUpdatedAt));
                Method method = methods.get("andCreatedAtLessThanOrEqualTo");
                method.invoke(criteriaObject, endCal.getTime());
            }

            if (latestDevRunCreatedAt != null && org.apache.commons.lang3.StringUtils.isNumeric(latestDevRunCreatedAt)
                    && methods.keySet().contains("andCreatedAtLessThanOrEqualTo")) {
                endCal.setTimeInMillis(Long.valueOf(latestDevRunCreatedAt));
                Method method = methods.get("andCreatedAtLessThanOrEqualTo");
                method.invoke(criteriaObject, endCal.getTime());
            }

            // ========== execution info date search
            if (projectStartDate != null && org.apache.commons.lang3.StringUtils.isNumeric(projectStartDate)
                    && methodNames.contains("andProjectCreatedAtGreaterThanOrEqualTo")) {
                startCal.setTimeInMillis(Long.valueOf(projectStartDate));
                Method method = methods.get("andProjectCreatedAtGreaterThanOrEqualTo");
                method.invoke(criteriaObject, startCal.getTime());
            }

            if (projectStartDate != null && org.apache.commons.lang3.StringUtils.isNumeric(projectStartDate)
                    && projectEndDate != null && org.apache.commons.lang3.StringUtils.isNumeric(projectEndDate)
                    && methods.keySet().contains("andProjectCreatedAtBetween")) {
                startCal.setTimeInMillis(Long.valueOf(projectStartDate));
                endCal.setTimeInMillis(Long.valueOf(projectEndDate));
                Method method = methods.get("andProjectCreatedAtBetween");
                method.invoke(criteriaObject, startCal.getTime(), endCal.getTime());
            }

            if (testCaseStartDate != null && org.apache.commons.lang3.StringUtils.isNumeric(testCaseStartDate)
                    && methodNames.contains("andTestCaseCreatedAtGreaterThanOrEqualTo")) {
                startCal.setTimeInMillis(Long.valueOf(testCaseStartDate));
                Method method = methods.get("andTestCaseCreatedAtGreaterThanOrEqualTo");
                method.invoke(criteriaObject, startCal.getTime());
            }

            if (testCaseStartDate != null && org.apache.commons.lang3.StringUtils.isNumeric(testCaseStartDate)
                    && testCaseEndDate != null && org.apache.commons.lang3.StringUtils.isNumeric(testCaseEndDate)
                    && methods.keySet().contains("andTestCaseCreatedAtBetween")) {
                startCal.setTimeInMillis(Long.valueOf(testCaseStartDate));
                endCal.setTimeInMillis(Long.valueOf(testCaseEndDate));
                Method method = methods.get("andTestCaseCreatedAtBetween");
                method.invoke(criteriaObject, startCal.getTime(), endCal.getTime());
            }

            if (runStartDate != null && org.apache.commons.lang3.StringUtils.isNumeric(runStartDate)
                    && methodNames.contains("andRunCreatedAtGreaterThanOrEqualTo")) {
                startCal.setTimeInMillis(Long.valueOf(runStartDate));
                Method method = methods.get("andRunCreatedAtGreaterThanOrEqualTo");
                method.invoke(criteriaObject, startCal.getTime());
            }

            if (runStartDate != null && org.apache.commons.lang3.StringUtils.isNumeric(runStartDate)
                    && runEndDate != null && org.apache.commons.lang3.StringUtils.isNumeric(runEndDate)
                    && methods.keySet().contains("andRunCreatedAtBetween")) {
                startCal.setTimeInMillis(Long.valueOf(runStartDate));
                endCal.setTimeInMillis(Long.valueOf(runEndDate));
                Method method = methods.get("andRunCreatedAtBetween");
                method.invoke(criteriaObject, startCal.getTime(), endCal.getTime());
            }

            if (orderBy != null && !orderBy.equalsIgnoreCase("null")) {
                if (criteriaObject.getClass().getName().toLowerCase().contains("testcaseexecutioninfo")) {
                    orderBy = orderBy + " , test_case_id asc";
                }
                else if (criteriaObject.getClass().getName().toLowerCase().contains("runexecutioninfo")) {
                    orderBy = orderBy + " , run_id asc";
                }
                else if (criteriaObject.getClass().getName().toLowerCase().contains("projectexecutioninfo")) {
                    orderBy = orderBy + " , project_id asc";
                }
                else {
                    orderBy = orderBy + " , id asc";
                }
                Method exampleOrderMethod = exampleObject.getClass().getDeclaredMethod("setOrderByClause",
                        String.class);
                exampleOrderMethod.invoke(exampleObject,
                        CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy));
            }
            // default testCaseIsDeleted is set to false
            if (testCaseIsDeleted == null && methodNames.contains("andTestCaseIsDeletedEqualTo")) {
                Method method = methods.get("andTestCaseIsDeletedEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject("false"));
            }

            if (testCaseIsDeleted != null && methodNames.contains("andTestCaseIsDeletedEqualTo")
                    && !testCaseIsDeleted.equalsIgnoreCase("all")) {
                Method method = methods.get("andTestCaseIsDeletedEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(testCaseIsDeleted));
            }

            if (testCaseIsDeleted != null && testCaseIsDeleted.equalsIgnoreCase("all")
                    && methodNames.contains("andTestCaseIsDeletedIsNotNull")) {
                Method method = methods.get("andTestCaseIsDeletedIsNotNull");
                method.invoke(criteriaObject);
            }
            // default projectIsDeleted is set to false
            if (projectIsDeleted == null && methodNames.contains("andProjectIsDeletedEqualTo")) {
                Method method = methods.get("andProjectIsDeletedEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject("false"));
            }

            if (projectIsDeleted != null && methodNames.contains("andProjectIsDeletedEqualTo")
                    && !projectIsDeleted.equalsIgnoreCase("all")) {
                Method method = methods.get("andProjectIsDeletedEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(projectIsDeleted));
            }

            if (projectIsDeleted != null && projectIsDeleted.equalsIgnoreCase("all")
                    && methodNames.contains("andProjectIsDeletedIsNotNull")) {
                Method method = methods.get("andProjectIsDeletedIsNotNull");
                method.invoke(criteriaObject);
            }

            // is driver
            if (isDriver != null && methods.keySet().contains("andIsDriverEqualTo")
                    && BooleanUtils.toBooleanObject(isDriver) != null) {
                Method method = methods.get("andIsDriverEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDriver));
            }

            // is default
            if (isDefault != null && methods.keySet().contains("andIsDefaultEqualTo")
                    && BooleanUtils.toBooleanObject(isDefault) != null) {
                Method method = methods.get("andIsDefaultEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDefault));
            }

            // set the criteria
            Method exampleOrMethod = exampleObject.getClass().getDeclaredMethod("or", criteriaObject.getClass());
            exampleOrMethod.invoke(exampleObject, criteriaObject);
            return (T) exampleObject;
        }
        catch (InstantiationException e) {
            logger.error("could not create example object instance", e);
        }
        catch (IllegalAccessException e) {
            logger.error("could not create example object instance", e);
        }
        catch (IllegalArgumentException e) {
            logger.error("Error on invoking method", e);
        }
        catch (InvocationTargetException e) {
            logger.error("Error on invoking method", e);
        }
        catch (NoSuchMethodException e) {
            logger.error("Error on creating method object", e);
        }
        catch (SecurityException e) {
            logger.error("Error on creating method object", e);
        }
        return null;
    }

    // private String processOrderBy(String orderByString) {
    // String[] orderByClauses = orderByString.split(",");
    // StringBuffer sb = new StringBuffer();
    // for (int i = 0; i < orderByClauses.length; i++) {
    // sb.append(orderByClauses[i].trim());
    // if (i < orderByClauses.length - 1) {
    // sb.append(" , ");
    // }
    // }
    // return sb.toString();
    // }

    private Map<String, Method> getMathods(Method[] methodList) {
        // loop through the method
        Map<String, Method> methods = new HashMap<String, Method>();
        for (int i = 0; i < methodList.length; i++) {
            Method method = methodList[i];
            methods.put(method.getName(), method);
        }
        return methods;
    }

    public RowBounds generateSearchRowBounds(UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        // check id default, no page size
        if (queryParams.getFirst("pageSize") == null) {
            return new RowBounds(0, 50);
        }
        // check not default , and all
        else if (queryParams.getFirst("pageSize") != null && (queryParams.getFirst("pageSize").equalsIgnoreCase("all")
                || queryParams.getFirst("pageSize").equalsIgnoreCase("ALL")
                || queryParams.getFirst("pageSize").equalsIgnoreCase("All"))) {
            return new RowBounds();
        }
        // check not default , and all
        else {
            String pageSize = queryParams.getFirst("pageSize");
            String pageNumber = queryParams.getFirst("pageNumber");
            if (pageSize != null && pageNumber != null && org.apache.commons.lang3.StringUtils.isNumeric(pageSize)
                    && org.apache.commons.lang3.StringUtils.isNumeric(pageNumber)) {
                int limit = Integer.parseInt(pageSize);
                int offect = limit * (Integer.parseInt(pageNumber) - 1);
                return new PageRowBounds(offect, limit);
            }
            else {
                return new RowBounds(0, 50);
            }
        }
    }
}
