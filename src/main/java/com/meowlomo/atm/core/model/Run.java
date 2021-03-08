package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.annotations.ApiModelProperty;

public class Run implements Serializable {
    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private Date createdAt;

    private Date updatedAt;

    private String log;

    private String status;

    private Boolean finished;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode testCase;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode parameter;

    private Long testCaseId;

    private String type;

    private Date startAt;

    private Date endAt;

    private String group;

    private Integer priority;

    private Integer timeout;

    private Long runSetResultId;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode driverPack;

    private Long driverPackId;

    private Long isRecorded;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode testCaseOverwrite;

    private Long testCaseOverwriteId;

    private String triggerSource;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode drivers;

    private UUID uuid;

    private UUID testCaseUuid;

    private Boolean singleton;

    private Long executableInstructionNumber;

    private Long systemRequirementPackId;

    private JsonNode systemRequirements;

    private JsonNode systemRequirementPack;

    private Long runSetTestCaseLinkId;

    private Integer resultOverwritten;

    private Long projectId;

    private static final long serialVersionUID = 1L;

    private List<InstructionResult> instructionResults;

    private List<ExecutionLog> executionLogs;

    private Set<Set<String>> aliases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public JsonNode getTestCase() {
        return testCase;
    }

    public void setTestCase(JsonNode testCase) {
        this.testCase = testCase;
    }

    public JsonNode getParameter() {
        return parameter;
    }

    public void setParameter(JsonNode parameter) {
        this.parameter = parameter;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Long getRunSetResultId() {
        return runSetResultId;
    }

    public void setRunSetResultId(Long runSetResultId) {
        this.runSetResultId = runSetResultId;
    }

    public JsonNode getDriverPack() {
        return driverPack;
    }

    public void setDriverPack(JsonNode driverPack) {
        this.driverPack = driverPack;
    }

    public Long getDriverPackId() {
        return driverPackId;
    }

    public void setDriverPackId(Long driverPackId) {
        this.driverPackId = driverPackId;
    }

    public Long getIsRecorded() {
        return isRecorded;
    }

    public void setIsRecorded(Long isRecorded) {
        this.isRecorded = isRecorded;
    }

    public JsonNode getTestCaseOverwrite() {
        return testCaseOverwrite;
    }

    public void setTestCaseOverwrite(JsonNode testCaseOverwrite) {
        this.testCaseOverwrite = testCaseOverwrite;
    }

    public Long getTestCaseOverwriteId() {
        return testCaseOverwriteId;
    }

    public void setTestCaseOverwriteId(Long testCaseOverwriteId) {
        this.testCaseOverwriteId = testCaseOverwriteId;
    }

    public String getTriggerSource() {
        return triggerSource;
    }

    public void setTriggerSource(String triggerSource) {
        this.triggerSource = triggerSource;
    }

    public JsonNode getDrivers() {
        return drivers;
    }

    public void setDrivers(JsonNode drivers) {
        this.drivers = drivers;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getTestCaseUuid() {
        return testCaseUuid;
    }

    public void setTestCaseUuid(UUID testCaseUuid) {
        this.testCaseUuid = testCaseUuid;
    }

    public Boolean getSingleton() {
        return singleton;
    }

    public void setSingleton(Boolean singleton) {
        this.singleton = singleton;
    }

    public Long getExecutableInstructionNumber() {
        return executableInstructionNumber;
    }

    public void setExecutableInstructionNumber(Long executableInstructionNumber) {
        this.executableInstructionNumber = executableInstructionNumber;
    }

    public Long getSystemRequirementPackId() {
        return systemRequirementPackId;
    }

    public void setSystemRequirementPackId(Long systemRequirementPackId) {
        this.systemRequirementPackId = systemRequirementPackId;
    }

    public JsonNode getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(JsonNode systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    public JsonNode getSystemRequirementPack() {
        return systemRequirementPack;
    }

    public void setSystemRequirementPack(JsonNode systemRequirementPack) {
        this.systemRequirementPack = systemRequirementPack;
    }

    public Long getRunSetTestCaseLinkId() {
        return runSetTestCaseLinkId;
    }

    public void setRunSetTestCaseLinkId(Long runSetTestCaseLinkId) {
        this.runSetTestCaseLinkId = runSetTestCaseLinkId;
    }

    public Integer getResultOverwritten() {
        return resultOverwritten;
    }

    public void setResultOverwritten(Integer resultOverwritten) {
        this.resultOverwritten = resultOverwritten;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<InstructionResult> getInstructionResults() {
        return instructionResults;
    }

    public void setInstructionResults(List<InstructionResult> instructionResults) {
        this.instructionResults = instructionResults;
    }

    public List<ExecutionLog> getExecutionLogs() {
        return executionLogs;
    }

    public void setExecutionLogs(List<ExecutionLog> executionLogs) {
        this.executionLogs = executionLogs;
    }

    public Set<Set<String>> getAliases() {
        return aliases;
    }

    public void setAliases(Set<Set<String>> aliases) {
        this.aliases = aliases;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        Run other = (Run) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getFinished() == null ? other.getFinished() == null : this.getFinished().equals(other.getFinished()))
                && (this.getTestCase() == null ? other.getTestCase() == null : this.getTestCase().equals(other.getTestCase()))
                && (this.getParameter() == null ? other.getParameter() == null : this.getParameter().equals(other.getParameter()))
                && (this.getTestCaseId() == null ? other.getTestCaseId() == null : this.getTestCaseId().equals(other.getTestCaseId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getStartAt() == null ? other.getStartAt() == null : this.getStartAt().equals(other.getStartAt()))
                && (this.getEndAt() == null ? other.getEndAt() == null : this.getEndAt().equals(other.getEndAt()))
                && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
                && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
                && (this.getTimeout() == null ? other.getTimeout() == null : this.getTimeout().equals(other.getTimeout()))
                && (this.getRunSetResultId() == null ? other.getRunSetResultId() == null : this.getRunSetResultId().equals(other.getRunSetResultId()))
                && (this.getDriverPack() == null ? other.getDriverPack() == null : this.getDriverPack().equals(other.getDriverPack()))
                && (this.getDriverPackId() == null ? other.getDriverPackId() == null : this.getDriverPackId().equals(other.getDriverPackId()))
                && (this.getIsRecorded() == null ? other.getIsRecorded() == null : this.getIsRecorded().equals(other.getIsRecorded()))
                && (this.getTestCaseOverwrite() == null ? other.getTestCaseOverwrite() == null : this.getTestCaseOverwrite().equals(other.getTestCaseOverwrite()))
                && (this.getTestCaseOverwriteId() == null ? other.getTestCaseOverwriteId() == null : this.getTestCaseOverwriteId().equals(other.getTestCaseOverwriteId()))
                && (this.getTriggerSource() == null ? other.getTriggerSource() == null : this.getTriggerSource().equals(other.getTriggerSource()))
                && (this.getDrivers() == null ? other.getDrivers() == null : this.getDrivers().equals(other.getDrivers()))
                && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
                && (this.getTestCaseUuid() == null ? other.getTestCaseUuid() == null : this.getTestCaseUuid().equals(other.getTestCaseUuid()))
                && (this.getSingleton() == null ? other.getSingleton() == null : this.getSingleton().equals(other.getSingleton()))
                && (this.getExecutableInstructionNumber() == null ? other.getExecutableInstructionNumber() == null
                        : this.getExecutableInstructionNumber().equals(other.getExecutableInstructionNumber()))
                && (this.getSystemRequirementPackId() == null ? other.getSystemRequirementPackId() == null
                        : this.getSystemRequirementPackId().equals(other.getSystemRequirementPackId()))
                && (this.getSystemRequirements() == null ? other.getSystemRequirements() == null : this.getSystemRequirements().equals(other.getSystemRequirements()))
                && (this.getSystemRequirementPack() == null ? other.getSystemRequirementPack() == null : this.getSystemRequirementPack().equals(other.getSystemRequirementPack()))
                && (this.getRunSetTestCaseLinkId() == null ? other.getRunSetTestCaseLinkId() == null : this.getRunSetTestCaseLinkId().equals(other.getRunSetTestCaseLinkId()))
                && (this.getResultOverwritten() == null ? other.getResultOverwritten() == null : this.getResultOverwritten().equals(other.getResultOverwritten()))
                && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getTestCase() == null) ? 0 : getTestCase().hashCode());
        result = prime * result + ((getParameter() == null) ? 0 : getParameter().hashCode());
        result = prime * result + ((getTestCaseId() == null) ? 0 : getTestCaseId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStartAt() == null) ? 0 : getStartAt().hashCode());
        result = prime * result + ((getEndAt() == null) ? 0 : getEndAt().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getTimeout() == null) ? 0 : getTimeout().hashCode());
        result = prime * result + ((getRunSetResultId() == null) ? 0 : getRunSetResultId().hashCode());
        result = prime * result + ((getDriverPack() == null) ? 0 : getDriverPack().hashCode());
        result = prime * result + ((getDriverPackId() == null) ? 0 : getDriverPackId().hashCode());
        result = prime * result + ((getIsRecorded() == null) ? 0 : getIsRecorded().hashCode());
        result = prime * result + ((getTestCaseOverwrite() == null) ? 0 : getTestCaseOverwrite().hashCode());
        result = prime * result + ((getTestCaseOverwriteId() == null) ? 0 : getTestCaseOverwriteId().hashCode());
        result = prime * result + ((getTriggerSource() == null) ? 0 : getTriggerSource().hashCode());
        result = prime * result + ((getDrivers() == null) ? 0 : getDrivers().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getTestCaseUuid() == null) ? 0 : getTestCaseUuid().hashCode());
        result = prime * result + ((getSingleton() == null) ? 0 : getSingleton().hashCode());
        result = prime * result + ((getExecutableInstructionNumber() == null) ? 0 : getExecutableInstructionNumber().hashCode());
        result = prime * result + ((getSystemRequirementPackId() == null) ? 0 : getSystemRequirementPackId().hashCode());
        result = prime * result + ((getSystemRequirements() == null) ? 0 : getSystemRequirements().hashCode());
        result = prime * result + ((getSystemRequirementPack() == null) ? 0 : getSystemRequirementPack().hashCode());
        result = prime * result + ((getRunSetTestCaseLinkId() == null) ? 0 : getRunSetTestCaseLinkId().hashCode());
        result = prime * result + ((getResultOverwritten() == null) ? 0 : getResultOverwritten().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", log=").append(log);
        sb.append(", status=").append(status);
        sb.append(", finished=").append(finished);
        sb.append(", testCase=").append(testCase);
        sb.append(", parameter=").append(parameter);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", runType=").append(type);
        sb.append(", startAt=").append(startAt);
        sb.append(", endAt=").append(endAt);
        sb.append(", group=").append(group);
        sb.append(", priority=").append(priority);
        sb.append(", timeout=").append(timeout);
        sb.append(", runSetResultId=").append(runSetResultId);
        sb.append(", driverPack=").append(driverPack);
        sb.append(", driverPackId=").append(driverPackId);
        sb.append(", isRecorded=").append(isRecorded);
        sb.append(", testCaseOverwrite=").append(testCaseOverwrite);
        sb.append(", testCaseOverwriteId=").append(testCaseOverwriteId);
        sb.append(", triggerSource=").append(triggerSource);
        sb.append(", drivers=").append(drivers);
        sb.append(", uuid=").append(uuid);
        sb.append(", testCaseUuid=").append(testCaseUuid);
        sb.append(", singleton=").append(singleton);
        sb.append(", executableInstructionNumber=").append(executableInstructionNumber);
        sb.append(", systemRequirementPackId=").append(systemRequirementPackId);
        sb.append(", systemRequirements=").append(systemRequirements);
        sb.append(", systemRequirementPack=").append(systemRequirementPack);
        sb.append(", runSetTestCaseLinkId=").append(runSetTestCaseLinkId);
        sb.append(", resultOverwritten=").append(resultOverwritten);
        sb.append(", projectId=").append(projectId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), updatedAt("updated_at", "updatedAt",
                "TIMESTAMP",
                false), log("log", "log", "VARCHAR", false), status("status_id", "status", "OTHER", false), finished("is_finished", "finished", "BIT", false), testCase("test_case",
                        "testCase", "OTHER",
                        false), parameter("parameter", "parameter", "OTHER", false), testCaseId("test_case_id", "testCaseId", "BIGINT", false), runType("run_type_id", "runType",
                                "OTHER", false), startAt("start_at", "startAt", "TIMESTAMP", false), endAt("end_at", "endAt", "TIMESTAMP", false), group("group_id", "group",
                                        "OTHER", false), priority("priority", "priority", "INTEGER", false), timeout("timeout", "timeout", "INTEGER", false), runSetResultId(
                                                "run_set_result_id", "runSetResultId", "BIGINT",
                                                false), driverPack("driver_pack", "driverPack", "OTHER", false), driverPackId("driver_pack_id", "driverPackId", "BIGINT",
                                                        false), isRecorded("is_recorded", "isRecorded", "BIGINT", false), testCaseOverwrite("test_case_overwrite",
                                                                "testCaseOverwrite", "OTHER",
                                                                false), testCaseOverwriteId("test_case_overwrite_id", "testCaseOverwriteId", "BIGINT", false), triggerSource(
                                                                        "trigger_source", "triggerSource", "VARCHAR",
                                                                        false), drivers("drivers", "drivers", "OTHER", false), uuid("uuid", "uuid", "OTHER", false), testCaseUuid(
                                                                                "test_case_uuid", "testCaseUuid", "OTHER",
                                                                                false), singleton("singleton", "singleton", "BIT", false), executableInstructionNumber(
                                                                                        "executable_instruction_number", "executableInstructionNumber", "BIGINT",
                                                                                        false), systemRequirementPackId("system_requirement_pack_id", "systemRequirementPackId",
                                                                                                "BIGINT", false), systemRequirements("system_requirements", "systemRequirements",
                                                                                                        "OTHER", false), systemRequirementPack("system_requirement_pack",
                                                                                                                "systemRequirementPack", "OTHER", false), runSetTestCaseLinkId(
                                                                                                                        "run_set_test_case_link_id", "runSetTestCaseLinkId",
                                                                                                                        "BIGINT", false), resultOverwritten("result_overwritten",
                                                                                                                                "resultOverwritten", "INTEGER", false), projectId(
                                                                                                                                        "project_id", "projectId", "BIGINT", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[] {});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            }
            else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}