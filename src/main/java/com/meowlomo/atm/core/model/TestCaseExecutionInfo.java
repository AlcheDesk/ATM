package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TestCaseExecutionInfo implements Serializable {
    private Long testCaseId;

    private String testCaseName;

    private Date testCaseCreatedAt;

    private Long totalRunCount;

    private String latestRunStatus;

    private Date latestRunUpdatedAt;

    private Long latestRunInstructionExecutedCount;

    private Long latestRunInstructionPassCount;

    private String latestRunTriggerSource;

    private Date latestRunCreatedAt;

    private Long latestRunId;

    private Long latestRunExecutableInstructionNumber;

    private Long totalDevRunCount;

    private String latestDevRunStatus;

    private Date latestDevRunUpdatedAt;

    private Long latestDevRunInstructionExecutedCount;

    private Long latestDevRunInstructionPassCount;

    private String latestDevRunTriggerSource;

    private Date latestDevRunCreatedAt;

    private Long latestDevRunId;

    private Long latestDevRunExecutableInstructionNumber;

    private Integer latestRunInstructionFailCount;

    private Integer latestDevRunInstructionFailCount;

    private Boolean testCaseIsDeleted;

    private Long testCaseProjectId;

    private static final long serialVersionUID = 1L;

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public Date getTestCaseCreatedAt() {
        return testCaseCreatedAt;
    }

    public void setTestCaseCreatedAt(Date testCaseCreatedAt) {
        this.testCaseCreatedAt = testCaseCreatedAt;
    }

    public Long getTotalRunCount() {
        return totalRunCount;
    }

    public void setTotalRunCount(Long totalRunCount) {
        this.totalRunCount = totalRunCount;
    }

    public String getLatestRunStatus() {
        return latestRunStatus;
    }

    public void setLatestRunStatus(String latestRunStatus) {
        this.latestRunStatus = latestRunStatus;
    }

    public Date getLatestRunUpdatedAt() {
        return latestRunUpdatedAt;
    }

    public void setLatestRunUpdatedAt(Date latestRunUpdatedAt) {
        this.latestRunUpdatedAt = latestRunUpdatedAt;
    }

    public Long getLatestRunInstructionExecutedCount() {
        return latestRunInstructionExecutedCount;
    }

    public void setLatestRunInstructionExecutedCount(Long latestRunInstructionExecutedCount) {
        this.latestRunInstructionExecutedCount = latestRunInstructionExecutedCount;
    }

    public Long getLatestRunInstructionPassCount() {
        return latestRunInstructionPassCount;
    }

    public void setLatestRunInstructionPassCount(Long latestRunInstructionPassCount) {
        this.latestRunInstructionPassCount = latestRunInstructionPassCount;
    }

    public String getLatestRunTriggerSource() {
        return latestRunTriggerSource;
    }

    public void setLatestRunTriggerSource(String latestRunTriggerSource) {
        this.latestRunTriggerSource = latestRunTriggerSource;
    }

    public Date getLatestRunCreatedAt() {
        return latestRunCreatedAt;
    }

    public void setLatestRunCreatedAt(Date latestRunCreatedAt) {
        this.latestRunCreatedAt = latestRunCreatedAt;
    }

    public Long getLatestRunId() {
        return latestRunId;
    }

    public void setLatestRunId(Long latestRunId) {
        this.latestRunId = latestRunId;
    }

    public Long getLatestRunExecutableInstructionNumber() {
        return latestRunExecutableInstructionNumber;
    }

    public void setLatestRunExecutableInstructionNumber(Long latestRunExecutableInstructionNumber) {
        this.latestRunExecutableInstructionNumber = latestRunExecutableInstructionNumber;
    }

    public Long getTotalDevRunCount() {
        return totalDevRunCount;
    }

    public void setTotalDevRunCount(Long totalDevRunCount) {
        this.totalDevRunCount = totalDevRunCount;
    }

    public String getLatestDevRunStatus() {
        return latestDevRunStatus;
    }

    public void setLatestDevRunStatus(String latestDevRunStatus) {
        this.latestDevRunStatus = latestDevRunStatus;
    }

    public Date getLatestDevRunUpdatedAt() {
        return latestDevRunUpdatedAt;
    }

    public void setLatestDevRunUpdatedAt(Date latestDevRunUpdatedAt) {
        this.latestDevRunUpdatedAt = latestDevRunUpdatedAt;
    }

    public Long getLatestDevRunInstructionExecutedCount() {
        return latestDevRunInstructionExecutedCount;
    }

    public void setLatestDevRunInstructionExecutedCount(Long latestDevRunInstructionExecutedCount) {
        this.latestDevRunInstructionExecutedCount = latestDevRunInstructionExecutedCount;
    }

    public Long getLatestDevRunInstructionPassCount() {
        return latestDevRunInstructionPassCount;
    }

    public void setLatestDevRunInstructionPassCount(Long latestDevRunInstructionPassCount) {
        this.latestDevRunInstructionPassCount = latestDevRunInstructionPassCount;
    }

    public String getLatestDevRunTriggerSource() {
        return latestDevRunTriggerSource;
    }

    public void setLatestDevRunTriggerSource(String latestDevRunTriggerSource) {
        this.latestDevRunTriggerSource = latestDevRunTriggerSource;
    }

    public Date getLatestDevRunCreatedAt() {
        return latestDevRunCreatedAt;
    }

    public void setLatestDevRunCreatedAt(Date latestDevRunCreatedAt) {
        this.latestDevRunCreatedAt = latestDevRunCreatedAt;
    }

    public Long getLatestDevRunId() {
        return latestDevRunId;
    }

    public void setLatestDevRunId(Long latestDevRunId) {
        this.latestDevRunId = latestDevRunId;
    }

    public Long getLatestDevRunExecutableInstructionNumber() {
        return latestDevRunExecutableInstructionNumber;
    }

    public void setLatestDevRunExecutableInstructionNumber(Long latestDevRunExecutableInstructionNumber) {
        this.latestDevRunExecutableInstructionNumber = latestDevRunExecutableInstructionNumber;
    }

    public Integer getLatestRunInstructionFailCount() {
        return latestRunInstructionFailCount;
    }

    public void setLatestRunInstructionFailCount(Integer latestRunInstructionFailCount) {
        this.latestRunInstructionFailCount = latestRunInstructionFailCount;
    }

    public Integer getLatestDevRunInstructionFailCount() {
        return latestDevRunInstructionFailCount;
    }

    public void setLatestDevRunInstructionFailCount(Integer latestDevRunInstructionFailCount) {
        this.latestDevRunInstructionFailCount = latestDevRunInstructionFailCount;
    }

    public Boolean getTestCaseIsDeleted() {
        return testCaseIsDeleted;
    }

    public void setTestCaseIsDeleted(Boolean testCaseIsDeleted) {
        this.testCaseIsDeleted = testCaseIsDeleted;
    }

    public Long getTestCaseProjectId() {
        return testCaseProjectId;
    }

    public void setTestCaseProjectId(Long testCaseProjectId) {
        this.testCaseProjectId = testCaseProjectId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        TestCaseExecutionInfo other = (TestCaseExecutionInfo) that;
        return (this.getTestCaseId() == null ? other.getTestCaseId() == null : this.getTestCaseId().equals(other.getTestCaseId()))
                && (this.getTestCaseName() == null ? other.getTestCaseName() == null : this.getTestCaseName().equals(other.getTestCaseName()))
                && (this.getTestCaseCreatedAt() == null ? other.getTestCaseCreatedAt() == null : this.getTestCaseCreatedAt().equals(other.getTestCaseCreatedAt()))
                && (this.getTotalRunCount() == null ? other.getTotalRunCount() == null : this.getTotalRunCount().equals(other.getTotalRunCount()))
                && (this.getLatestRunStatus() == null ? other.getLatestRunStatus() == null : this.getLatestRunStatus().equals(other.getLatestRunStatus()))
                && (this.getLatestRunUpdatedAt() == null ? other.getLatestRunUpdatedAt() == null : this.getLatestRunUpdatedAt().equals(other.getLatestRunUpdatedAt()))
                && (this.getLatestRunInstructionExecutedCount() == null ? other.getLatestRunInstructionExecutedCount() == null
                        : this.getLatestRunInstructionExecutedCount().equals(other.getLatestRunInstructionExecutedCount()))
                && (this.getLatestRunInstructionPassCount() == null ? other.getLatestRunInstructionPassCount() == null
                        : this.getLatestRunInstructionPassCount().equals(other.getLatestRunInstructionPassCount()))
                && (this.getLatestRunTriggerSource() == null ? other.getLatestRunTriggerSource() == null
                        : this.getLatestRunTriggerSource().equals(other.getLatestRunTriggerSource()))
                && (this.getLatestRunCreatedAt() == null ? other.getLatestRunCreatedAt() == null : this.getLatestRunCreatedAt().equals(other.getLatestRunCreatedAt()))
                && (this.getLatestRunId() == null ? other.getLatestRunId() == null : this.getLatestRunId().equals(other.getLatestRunId()))
                && (this.getLatestRunExecutableInstructionNumber() == null ? other.getLatestRunExecutableInstructionNumber() == null
                        : this.getLatestRunExecutableInstructionNumber().equals(other.getLatestRunExecutableInstructionNumber()))
                && (this.getTotalDevRunCount() == null ? other.getTotalDevRunCount() == null : this.getTotalDevRunCount().equals(other.getTotalDevRunCount()))
                && (this.getLatestDevRunStatus() == null ? other.getLatestDevRunStatus() == null : this.getLatestDevRunStatus().equals(other.getLatestDevRunStatus()))
                && (this.getLatestDevRunUpdatedAt() == null ? other.getLatestDevRunUpdatedAt() == null : this.getLatestDevRunUpdatedAt().equals(other.getLatestDevRunUpdatedAt()))
                && (this.getLatestDevRunInstructionExecutedCount() == null ? other.getLatestDevRunInstructionExecutedCount() == null
                        : this.getLatestDevRunInstructionExecutedCount().equals(other.getLatestDevRunInstructionExecutedCount()))
                && (this.getLatestDevRunInstructionPassCount() == null ? other.getLatestDevRunInstructionPassCount() == null
                        : this.getLatestDevRunInstructionPassCount().equals(other.getLatestDevRunInstructionPassCount()))
                && (this.getLatestDevRunTriggerSource() == null ? other.getLatestDevRunTriggerSource() == null
                        : this.getLatestDevRunTriggerSource().equals(other.getLatestDevRunTriggerSource()))
                && (this.getLatestDevRunCreatedAt() == null ? other.getLatestDevRunCreatedAt() == null : this.getLatestDevRunCreatedAt().equals(other.getLatestDevRunCreatedAt()))
                && (this.getLatestDevRunId() == null ? other.getLatestDevRunId() == null : this.getLatestDevRunId().equals(other.getLatestDevRunId()))
                && (this.getLatestDevRunExecutableInstructionNumber() == null ? other.getLatestDevRunExecutableInstructionNumber() == null
                        : this.getLatestDevRunExecutableInstructionNumber().equals(other.getLatestDevRunExecutableInstructionNumber()))
                && (this.getLatestRunInstructionFailCount() == null ? other.getLatestRunInstructionFailCount() == null
                        : this.getLatestRunInstructionFailCount().equals(other.getLatestRunInstructionFailCount()))
                && (this.getLatestDevRunInstructionFailCount() == null ? other.getLatestDevRunInstructionFailCount() == null
                        : this.getLatestDevRunInstructionFailCount().equals(other.getLatestDevRunInstructionFailCount()))
                && (this.getTestCaseIsDeleted() == null ? other.getTestCaseIsDeleted() == null : this.getTestCaseIsDeleted().equals(other.getTestCaseIsDeleted()))
                && (this.getTestCaseProjectId() == null ? other.getTestCaseProjectId() == null : this.getTestCaseProjectId().equals(other.getTestCaseProjectId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTestCaseId() == null) ? 0 : getTestCaseId().hashCode());
        result = prime * result + ((getTestCaseName() == null) ? 0 : getTestCaseName().hashCode());
        result = prime * result + ((getTestCaseCreatedAt() == null) ? 0 : getTestCaseCreatedAt().hashCode());
        result = prime * result + ((getTotalRunCount() == null) ? 0 : getTotalRunCount().hashCode());
        result = prime * result + ((getLatestRunStatus() == null) ? 0 : getLatestRunStatus().hashCode());
        result = prime * result + ((getLatestRunUpdatedAt() == null) ? 0 : getLatestRunUpdatedAt().hashCode());
        result = prime * result + ((getLatestRunInstructionExecutedCount() == null) ? 0 : getLatestRunInstructionExecutedCount().hashCode());
        result = prime * result + ((getLatestRunInstructionPassCount() == null) ? 0 : getLatestRunInstructionPassCount().hashCode());
        result = prime * result + ((getLatestRunTriggerSource() == null) ? 0 : getLatestRunTriggerSource().hashCode());
        result = prime * result + ((getLatestRunCreatedAt() == null) ? 0 : getLatestRunCreatedAt().hashCode());
        result = prime * result + ((getLatestRunId() == null) ? 0 : getLatestRunId().hashCode());
        result = prime * result + ((getLatestRunExecutableInstructionNumber() == null) ? 0 : getLatestRunExecutableInstructionNumber().hashCode());
        result = prime * result + ((getTotalDevRunCount() == null) ? 0 : getTotalDevRunCount().hashCode());
        result = prime * result + ((getLatestDevRunStatus() == null) ? 0 : getLatestDevRunStatus().hashCode());
        result = prime * result + ((getLatestDevRunUpdatedAt() == null) ? 0 : getLatestDevRunUpdatedAt().hashCode());
        result = prime * result + ((getLatestDevRunInstructionExecutedCount() == null) ? 0 : getLatestDevRunInstructionExecutedCount().hashCode());
        result = prime * result + ((getLatestDevRunInstructionPassCount() == null) ? 0 : getLatestDevRunInstructionPassCount().hashCode());
        result = prime * result + ((getLatestDevRunTriggerSource() == null) ? 0 : getLatestDevRunTriggerSource().hashCode());
        result = prime * result + ((getLatestDevRunCreatedAt() == null) ? 0 : getLatestDevRunCreatedAt().hashCode());
        result = prime * result + ((getLatestDevRunId() == null) ? 0 : getLatestDevRunId().hashCode());
        result = prime * result + ((getLatestDevRunExecutableInstructionNumber() == null) ? 0 : getLatestDevRunExecutableInstructionNumber().hashCode());
        result = prime * result + ((getLatestRunInstructionFailCount() == null) ? 0 : getLatestRunInstructionFailCount().hashCode());
        result = prime * result + ((getLatestDevRunInstructionFailCount() == null) ? 0 : getLatestDevRunInstructionFailCount().hashCode());
        result = prime * result + ((getTestCaseIsDeleted() == null) ? 0 : getTestCaseIsDeleted().hashCode());
        result = prime * result + ((getTestCaseProjectId() == null) ? 0 : getTestCaseProjectId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", testCaseName=").append(testCaseName);
        sb.append(", testCaseCreatedAt=").append(testCaseCreatedAt);
        sb.append(", totalRunCount=").append(totalRunCount);
        sb.append(", latestRunStatus=").append(latestRunStatus);
        sb.append(", latestRunUpdatedAt=").append(latestRunUpdatedAt);
        sb.append(", latestRunInstructionExecutedCount=").append(latestRunInstructionExecutedCount);
        sb.append(", latestRunInstructionPassCount=").append(latestRunInstructionPassCount);
        sb.append(", latestRunTriggerSource=").append(latestRunTriggerSource);
        sb.append(", latestRunCreatedAt=").append(latestRunCreatedAt);
        sb.append(", latestRunId=").append(latestRunId);
        sb.append(", latestRunExecutableInstructionNumber=").append(latestRunExecutableInstructionNumber);
        sb.append(", totalDevRunCount=").append(totalDevRunCount);
        sb.append(", latestDevRunStatus=").append(latestDevRunStatus);
        sb.append(", latestDevRunUpdatedAt=").append(latestDevRunUpdatedAt);
        sb.append(", latestDevRunInstructionExecutedCount=").append(latestDevRunInstructionExecutedCount);
        sb.append(", latestDevRunInstructionPassCount=").append(latestDevRunInstructionPassCount);
        sb.append(", latestDevRunTriggerSource=").append(latestDevRunTriggerSource);
        sb.append(", latestDevRunCreatedAt=").append(latestDevRunCreatedAt);
        sb.append(", latestDevRunId=").append(latestDevRunId);
        sb.append(", latestDevRunExecutableInstructionNumber=").append(latestDevRunExecutableInstructionNumber);
        sb.append(", latestRunInstructionFailCount=").append(latestRunInstructionFailCount);
        sb.append(", latestDevRunInstructionFailCount=").append(latestDevRunInstructionFailCount);
        sb.append(", testCaseIsDeleted=").append(testCaseIsDeleted);
        sb.append(", testCaseProjectId=").append(testCaseProjectId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        testCaseId("test_case_id", "testCaseId", "BIGINT", false), testCaseName("test_case_name", "testCaseName", "VARCHAR", false), testCaseCreatedAt("test_case_created_at",
                "testCaseCreatedAt", "TIMESTAMP", false), totalRunCount("total_run_count", "totalRunCount", "BIGINT", false), latestRunStatus("latest_run_status_id",
                        "latestRunStatus", "OTHER",
                        false), latestRunUpdatedAt("latest_run_updated_at", "latestRunUpdatedAt", "TIMESTAMP", false), latestRunInstructionExecutedCount(
                                "latest_run_instruction_executed_count", "latestRunInstructionExecutedCount", "BIGINT",
                                false), latestRunInstructionPassCount("latest_run_instruction_pass_count", "latestRunInstructionPassCount", "BIGINT",
                                        false), latestRunTriggerSource("latest_run_trigger_source", "latestRunTriggerSource", "VARCHAR", false), latestRunCreatedAt(
                                                "latest_run_created_at", "latestRunCreatedAt", "TIMESTAMP",
                                                false), latestRunId("latest_run_id", "latestRunId", "BIGINT", false), latestRunExecutableInstructionNumber(
                                                        "latest_run_executable_instruction_number", "latestRunExecutableInstructionNumber", "BIGINT",
                                                        false), totalDevRunCount("total_dev_run_count", "totalDevRunCount", "BIGINT", false), latestDevRunStatus(
                                                                "latest_dev_run_status_id", "latestDevRunStatus", "OTHER",
                                                                false), latestDevRunUpdatedAt("latest_dev_run_updated_at", "latestDevRunUpdatedAt", "TIMESTAMP",
                                                                        false), latestDevRunInstructionExecutedCount("latest_dev_run_instruction_executed_count",
                                                                                "latestDevRunInstructionExecutedCount", "BIGINT", false), latestDevRunInstructionPassCount(
                                                                                        "latest_dev_run_instruction_pass_count", "latestDevRunInstructionPassCount", "BIGINT",
                                                                                        false), latestDevRunTriggerSource("latest_dev_run_trigger_source",
                                                                                                "latestDevRunTriggerSource", "VARCHAR",
                                                                                                false), latestDevRunCreatedAt("latest_dev_run_created_at", "latestDevRunCreatedAt",
                                                                                                        "TIMESTAMP", false), latestDevRunId("latest_dev_run_id", "latestDevRunId",
                                                                                                                "BIGINT", false), latestDevRunExecutableInstructionNumber(
                                                                                                                        "latest_dev_run_executable_instruction_number",
                                                                                                                        "latestDevRunExecutableInstructionNumber", "BIGINT",
                                                                                                                        false), latestRunInstructionFailCount(
                                                                                                                                "latest_run_instruction_fail_count",
                                                                                                                                "latestRunInstructionFailCount", "INTEGER",
                                                                                                                                false), latestDevRunInstructionFailCount(
                                                                                                                                        "latest_dev_run_instruction_fail_count",
                                                                                                                                        "latestDevRunInstructionFailCount",
                                                                                                                                        "INTEGER", false), testCaseIsDeleted(
                                                                                                                                                "test_case_is_deleted",
                                                                                                                                                "testCaseIsDeleted", "BIT",
                                                                                                                                                false), testCaseProjectId(
                                                                                                                                                        "test_case_project_id",
                                                                                                                                                        "testCaseProjectId",
                                                                                                                                                        "BIGINT", false);

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