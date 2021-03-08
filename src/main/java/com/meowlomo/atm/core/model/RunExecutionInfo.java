package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.annotations.ApiModelProperty;

public class RunExecutionInfo implements Serializable {
    private Long runId;

    private String runName;

    private String runType;

    private String runStatus;

    private Date runCreatedAt;

    private Date runUpdatedAt;

    private Long testCaseId;

    private Long runSetResultId;

    private Integer executableInstructionNumber;

    private Long instructionExecutedCount;

    private Long instructionPassCount;

    private String triggerSource;

    private UUID driverPackMd5;

    private UUID testCaseOverwriteMd5;

    private UUID testCaseMd5;

    private String group;

    private String driverPackName;

    private String testCaseOverwriteName;

    private String testCaseName;

    private Long runPriority;

    private Integer runResultOverwritten;

    private Long runProjectId;

    private Integer instructionFailCount;

    private Boolean finished;

    private Date runStartAt;

    private Date runEndAt;

    private Long duration;

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode environment;
    
    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode devInfo;

    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public Date getRunCreatedAt() {
        return runCreatedAt;
    }

    public void setRunCreatedAt(Date runCreatedAt) {
        this.runCreatedAt = runCreatedAt;
    }

    public Date getRunUpdatedAt() {
        return runUpdatedAt;
    }

    public void setRunUpdatedAt(Date runUpdatedAt) {
        this.runUpdatedAt = runUpdatedAt;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getRunSetResultId() {
        return runSetResultId;
    }

    public void setRunSetResultId(Long runSetResultId) {
        this.runSetResultId = runSetResultId;
    }

    public Integer getExecutableInstructionNumber() {
        return executableInstructionNumber;
    }

    public void setExecutableInstructionNumber(Integer executableInstructionNumber) {
        this.executableInstructionNumber = executableInstructionNumber;
    }

    public Long getInstructionExecutedCount() {
        return instructionExecutedCount;
    }

    public void setInstructionExecutedCount(Long instructionExecutedCount) {
        this.instructionExecutedCount = instructionExecutedCount;
    }

    public Long getInstructionPassCount() {
        return instructionPassCount;
    }

    public void setInstructionPassCount(Long instructionPassCount) {
        this.instructionPassCount = instructionPassCount;
    }

    public String getTriggerSource() {
        return triggerSource;
    }

    public void setTriggerSource(String triggerSource) {
        this.triggerSource = triggerSource;
    }

    public UUID getDriverPackMd5() {
        return driverPackMd5;
    }

    public void setDriverPackMd5(UUID driverPackMd5) {
        this.driverPackMd5 = driverPackMd5;
    }

    public UUID getTestCaseOverwriteMd5() {
        return testCaseOverwriteMd5;
    }

    public void setTestCaseOverwriteMd5(UUID testCaseOverwriteMd5) {
        this.testCaseOverwriteMd5 = testCaseOverwriteMd5;
    }

    public UUID getTestCaseMd5() {
        return testCaseMd5;
    }

    public void setTestCaseMd5(UUID testCaseMd5) {
        this.testCaseMd5 = testCaseMd5;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDriverPackName() {
        return driverPackName;
    }

    public void setDriverPackName(String driverPackName) {
        this.driverPackName = driverPackName;
    }

    public String getTestCaseOverwriteName() {
        return testCaseOverwriteName;
    }

    public void setTestCaseOverwriteName(String testCaseOverwriteName) {
        this.testCaseOverwriteName = testCaseOverwriteName;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public Long getRunPriority() {
        return runPriority;
    }

    public void setRunPriority(Long runPriority) {
        this.runPriority = runPriority;
    }

    public Integer getRunResultOverwritten() {
        return runResultOverwritten;
    }

    public void setRunResultOverwritten(Integer runResultOverwritten) {
        this.runResultOverwritten = runResultOverwritten;
    }

    public Long getRunProjectId() {
        return runProjectId;
    }

    public void setRunProjectId(Long runProjectId) {
        this.runProjectId = runProjectId;
    }

    public Integer getInstructionFailCount() {
        return instructionFailCount;
    }

    public void setInstructionFailCount(Integer instructionFailCount) {
        this.instructionFailCount = instructionFailCount;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getRunStartAt() {
        return runStartAt;
    }

    public void setRunStartAt(Date runStartAt) {
        this.runStartAt = runStartAt;
    }

    public Date getRunEndAt() {
        return runEndAt;
    }

    public void setRunEndAt(Date runEndAt) {
        this.runEndAt = runEndAt;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public JsonNode getEnvironment() {
        return environment;
    }

    public void setEnvironment(JsonNode environment) {
        this.environment = environment;
    }

    public JsonNode getDevInfo() {
        return devInfo;
    }

    public void setDevInfo(JsonNode devInfo) {
        this.devInfo = devInfo;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        RunExecutionInfo other = (RunExecutionInfo) that;
        return (this.getRunId() == null ? other.getRunId() == null : this.getRunId().equals(other.getRunId()))
                && (this.getRunName() == null ? other.getRunName() == null : this.getRunName().equals(other.getRunName()))
                && (this.getRunType() == null ? other.getRunType() == null : this.getRunType().equals(other.getRunType()))
                && (this.getRunStatus() == null ? other.getRunStatus() == null : this.getRunStatus().equals(other.getRunStatus()))
                && (this.getRunCreatedAt() == null ? other.getRunCreatedAt() == null : this.getRunCreatedAt().equals(other.getRunCreatedAt()))
                && (this.getRunUpdatedAt() == null ? other.getRunUpdatedAt() == null : this.getRunUpdatedAt().equals(other.getRunUpdatedAt()))
                && (this.getTestCaseId() == null ? other.getTestCaseId() == null : this.getTestCaseId().equals(other.getTestCaseId()))
                && (this.getRunSetResultId() == null ? other.getRunSetResultId() == null : this.getRunSetResultId().equals(other.getRunSetResultId()))
                && (this.getExecutableInstructionNumber() == null ? other.getExecutableInstructionNumber() == null
                        : this.getExecutableInstructionNumber().equals(other.getExecutableInstructionNumber()))
                && (this.getInstructionExecutedCount() == null ? other.getInstructionExecutedCount() == null
                        : this.getInstructionExecutedCount().equals(other.getInstructionExecutedCount()))
                && (this.getInstructionPassCount() == null ? other.getInstructionPassCount() == null : this.getInstructionPassCount().equals(other.getInstructionPassCount()))
                && (this.getTriggerSource() == null ? other.getTriggerSource() == null : this.getTriggerSource().equals(other.getTriggerSource()))
                && (this.getDriverPackMd5() == null ? other.getDriverPackMd5() == null : this.getDriverPackMd5().equals(other.getDriverPackMd5()))
                && (this.getTestCaseOverwriteMd5() == null ? other.getTestCaseOverwriteMd5() == null : this.getTestCaseOverwriteMd5().equals(other.getTestCaseOverwriteMd5()))
                && (this.getTestCaseMd5() == null ? other.getTestCaseMd5() == null : this.getTestCaseMd5().equals(other.getTestCaseMd5()))
                && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
                && (this.getDriverPackName() == null ? other.getDriverPackName() == null : this.getDriverPackName().equals(other.getDriverPackName()))
                && (this.getTestCaseOverwriteName() == null ? other.getTestCaseOverwriteName() == null : this.getTestCaseOverwriteName().equals(other.getTestCaseOverwriteName()))
                && (this.getTestCaseName() == null ? other.getTestCaseName() == null : this.getTestCaseName().equals(other.getTestCaseName()))
                && (this.getRunPriority() == null ? other.getRunPriority() == null : this.getRunPriority().equals(other.getRunPriority()))
                && (this.getRunResultOverwritten() == null ? other.getRunResultOverwritten() == null : this.getRunResultOverwritten().equals(other.getRunResultOverwritten()))
                && (this.getRunProjectId() == null ? other.getRunProjectId() == null : this.getRunProjectId().equals(other.getRunProjectId()))
                && (this.getInstructionFailCount() == null ? other.getInstructionFailCount() == null : this.getInstructionFailCount().equals(other.getInstructionFailCount()))
                && (this.getFinished() == null ? other.getFinished() == null : this.getFinished().equals(other.getFinished()))
                && (this.getRunStartAt() == null ? other.getRunStartAt() == null : this.getRunStartAt().equals(other.getRunStartAt()))
                && (this.getRunEndAt() == null ? other.getRunEndAt() == null : this.getRunEndAt().equals(other.getRunEndAt()))
                && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRunId() == null) ? 0 : getRunId().hashCode());
        result = prime * result + ((getRunName() == null) ? 0 : getRunName().hashCode());
        result = prime * result + ((getRunType() == null) ? 0 : getRunType().hashCode());
        result = prime * result + ((getRunStatus() == null) ? 0 : getRunStatus().hashCode());
        result = prime * result + ((getRunCreatedAt() == null) ? 0 : getRunCreatedAt().hashCode());
        result = prime * result + ((getRunUpdatedAt() == null) ? 0 : getRunUpdatedAt().hashCode());
        result = prime * result + ((getTestCaseId() == null) ? 0 : getTestCaseId().hashCode());
        result = prime * result + ((getRunSetResultId() == null) ? 0 : getRunSetResultId().hashCode());
        result = prime * result + ((getExecutableInstructionNumber() == null) ? 0 : getExecutableInstructionNumber().hashCode());
        result = prime * result + ((getInstructionExecutedCount() == null) ? 0 : getInstructionExecutedCount().hashCode());
        result = prime * result + ((getInstructionPassCount() == null) ? 0 : getInstructionPassCount().hashCode());
        result = prime * result + ((getTriggerSource() == null) ? 0 : getTriggerSource().hashCode());
        result = prime * result + ((getDriverPackMd5() == null) ? 0 : getDriverPackMd5().hashCode());
        result = prime * result + ((getTestCaseOverwriteMd5() == null) ? 0 : getTestCaseOverwriteMd5().hashCode());
        result = prime * result + ((getTestCaseMd5() == null) ? 0 : getTestCaseMd5().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getDriverPackName() == null) ? 0 : getDriverPackName().hashCode());
        result = prime * result + ((getTestCaseOverwriteName() == null) ? 0 : getTestCaseOverwriteName().hashCode());
        result = prime * result + ((getTestCaseName() == null) ? 0 : getTestCaseName().hashCode());
        result = prime * result + ((getRunPriority() == null) ? 0 : getRunPriority().hashCode());
        result = prime * result + ((getRunResultOverwritten() == null) ? 0 : getRunResultOverwritten().hashCode());
        result = prime * result + ((getRunProjectId() == null) ? 0 : getRunProjectId().hashCode());
        result = prime * result + ((getInstructionFailCount() == null) ? 0 : getInstructionFailCount().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getRunStartAt() == null) ? 0 : getRunStartAt().hashCode());
        result = prime * result + ((getRunEndAt() == null) ? 0 : getRunEndAt().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", runId=").append(runId);
        sb.append(", runName=").append(runName);
        sb.append(", runType=").append(runType);
        sb.append(", runStatus=").append(runStatus);
        sb.append(", runCreatedAt=").append(runCreatedAt);
        sb.append(", runUpdatedAt=").append(runUpdatedAt);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", runSetResultId=").append(runSetResultId);
        sb.append(", executableInstructionNumber=").append(executableInstructionNumber);
        sb.append(", instructionExecutedCount=").append(instructionExecutedCount);
        sb.append(", instructionPassCount=").append(instructionPassCount);
        sb.append(", triggerSource=").append(triggerSource);
        sb.append(", driverPackMd5=").append(driverPackMd5);
        sb.append(", testCaseOverwriteMd5=").append(testCaseOverwriteMd5);
        sb.append(", testCaseMd5=").append(testCaseMd5);
        sb.append(", group=").append(group);
        sb.append(", driverPackName=").append(driverPackName);
        sb.append(", testCaseOverwriteName=").append(testCaseOverwriteName);
        sb.append(", testCaseName=").append(testCaseName);
        sb.append(", runPriority=").append(runPriority);
        sb.append(", runResultOverwritten=").append(runResultOverwritten);
        sb.append(", runProjectId=").append(runProjectId);
        sb.append(", instructionFailCount=").append(instructionFailCount);
        sb.append(", finished=").append(finished);
        sb.append(", runStartAt=").append(runStartAt);
        sb.append(", runEndAt=").append(runEndAt);
        sb.append(", duration=").append(duration);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        runId("run_id", "runId", "BIGINT", false), runName("run_name", "runName", "VARCHAR", false), runType("run_type_id", "runType", "OTHER", false), runStatus("run_status_id",
                "runStatus", "OTHER",
                false), runCreatedAt("run_created_at", "runCreatedAt", "TIMESTAMP", false), runUpdatedAt("run_updated_at", "runUpdatedAt", "TIMESTAMP", false), testCaseId(
                        "test_case_id", "testCaseId", "BIGINT", false), runSetResultId("run_set_result_id", "runSetResultId", "BIGINT", false), executableInstructionNumber(
                                "executable_instruction_number", "executableInstructionNumber", "INTEGER",
                                false), instructionExecutedCount("instruction_executed_count", "instructionExecutedCount", "BIGINT", false), instructionPassCount(
                                        "instruction_pass_count", "instructionPassCount", "BIGINT",
                                        false), triggerSource("trigger_source", "triggerSource", "VARCHAR", false), driverPackMd5("driver_pack_md5", "driverPackMd5", "OTHER",
                                                false), testCaseOverwriteMd5("test_case_overwrite_md5", "testCaseOverwriteMd5", "OTHER", false), testCaseMd5("test_case_md5",
                                                        "testCaseMd5", "OTHER",
                                                        false), group("run_group_id", "group", "OTHER", false), driverPackName("driver_pack_name", "driverPackName", "VARCHAR",
                                                                false), testCaseOverwriteName("test_case_overwrite_name", "testCaseOverwriteName", "VARCHAR", false), testCaseName(
                                                                        "test_case_name", "testCaseName", "VARCHAR",
                                                                        false), runPriority("run_priority", "runPriority", "BIGINT", false), runResultOverwritten(
                                                                                "run_result_overwritten", "runResultOverwritten", "INTEGER",
                                                                                false), runProjectId("run_project_id", "runProjectId", "BIGINT", false), instructionFailCount(
                                                                                        "instruction_fail_count", "instructionFailCount", "INTEGER",
                                                                                        false), finished("is_finished", "finished", "BIT", false), runStartAt("run_start_at",
                                                                                                "runStartAt", "TIMESTAMP", false), runEndAt("run_end_at", "runEndAt", "TIMESTAMP",
                                                                                                        false), duration("duration", "duration", "BIGINT", false);

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