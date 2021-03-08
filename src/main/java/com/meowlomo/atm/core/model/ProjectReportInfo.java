package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.meowlomo.atm.core.model.custom.RunByDateInfo;

public class ProjectReportInfo implements Serializable {
    private Long projectId;

    private String projectName;

    private Date projectCreatedAt;

    private Integer activeTestCaseNumber;

    private Long totalRunNumber;

    private Long totalExecutionTime;

    private Integer executedTestCaseNumber;

    private Integer failedTestCaseNumber;

    private Integer passedTestCaseNumber;

    private BigDecimal passRate;

    private BigDecimal failRate;

    private Integer totalTestCaseNumber;

    private static final long serialVersionUID = 1L;
    
    @SuppressWarnings("unused")
    private double totalExecutionTimeInMins;

    @SuppressWarnings("unused")
    private Long duration;

    private List<RunByDateInfo> runByDateChartData;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectCreatedAt() {
        return projectCreatedAt;
    }

    public void setProjectCreatedAt(Date projectCreatedAt) {
        this.projectCreatedAt = projectCreatedAt;
    }

    public Integer getActiveTestCaseNumber() {
        return activeTestCaseNumber;
    }

    public void setActiveTestCaseNumber(Integer activeTestCaseNumber) {
        this.activeTestCaseNumber = activeTestCaseNumber;
    }

    public Long getTotalRunNumber() {
        return totalRunNumber;
    }

    public void setTotalRunNumber(Long totalRunNumber) {
        this.totalRunNumber = totalRunNumber;
    }

    public Long getTotalExecutionTime() {
        return totalExecutionTime;
    }

    public void setTotalExecutionTime(Long totalExecutionTime) {
        this.totalExecutionTime = totalExecutionTime;
    }

    public Integer getExecutedTestCaseNumber() {
        return executedTestCaseNumber;
    }

    public void setExecutedTestCaseNumber(Integer executedTestCaseNumber) {
        this.executedTestCaseNumber = executedTestCaseNumber;
    }

    public Integer getFailedTestCaseNumber() {
        return failedTestCaseNumber;
    }

    public void setFailedTestCaseNumber(Integer failedTestCaseNumber) {
        this.failedTestCaseNumber = failedTestCaseNumber;
    }

    public Integer getPassedTestCaseNumber() {
        return passedTestCaseNumber;
    }

    public void setPassedTestCaseNumber(Integer passedTestCaseNumber) {
        this.passedTestCaseNumber = passedTestCaseNumber;
    }

    public BigDecimal getPassRate() {
        return passRate;
    }

    public void setPassRate(BigDecimal passRate) {
        this.passRate = passRate;
    }

    public BigDecimal getFailRate() {
        return failRate;
    }

    public void setFailRate(BigDecimal failRate) {
        this.failRate = failRate;
    }

    public Integer getTotalTestCaseNumber() {
        return totalTestCaseNumber;
    }

    public void setTotalTestCaseNumber(Integer totalTestCaseNumber) {
        this.totalTestCaseNumber = totalTestCaseNumber;
    }

    public List<RunByDateInfo> getRunByDateChartData() {
        return runByDateChartData;
    }

    public void setRunByDateChartData(List<RunByDateInfo> runByDateChartData) {
        this.runByDateChartData = runByDateChartData;
    }

    public Long getDuration() {
        if (this.projectCreatedAt == null) {
            return null;
        }
        else {
            return ChronoUnit.DAYS.between(this.projectCreatedAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        }
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public double getTotalExecutionTimeInMins() {
        return Math.round(this.totalExecutionTime * 100d) / 6000d;
    }

    public void setTotalExecutionTimeInMins(double totalExecutionTimeInMins) {
        this.totalExecutionTimeInMins = totalExecutionTimeInMins;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        ProjectReportInfo other = (ProjectReportInfo) that;
        return (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
                && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
                && (this.getProjectCreatedAt() == null ? other.getProjectCreatedAt() == null : this.getProjectCreatedAt().equals(other.getProjectCreatedAt()))
                && (this.getActiveTestCaseNumber() == null ? other.getActiveTestCaseNumber() == null : this.getActiveTestCaseNumber().equals(other.getActiveTestCaseNumber()))
                && (this.getTotalRunNumber() == null ? other.getTotalRunNumber() == null : this.getTotalRunNumber().equals(other.getTotalRunNumber()))
                && (this.getTotalExecutionTime() == null ? other.getTotalExecutionTime() == null : this.getTotalExecutionTime().equals(other.getTotalExecutionTime()))
                && (this.getExecutedTestCaseNumber() == null ? other.getExecutedTestCaseNumber() == null
                        : this.getExecutedTestCaseNumber().equals(other.getExecutedTestCaseNumber()))
                && (this.getFailedTestCaseNumber() == null ? other.getFailedTestCaseNumber() == null : this.getFailedTestCaseNumber().equals(other.getFailedTestCaseNumber()))
                && (this.getPassedTestCaseNumber() == null ? other.getPassedTestCaseNumber() == null : this.getPassedTestCaseNumber().equals(other.getPassedTestCaseNumber()))
                && (this.getPassRate() == null ? other.getPassRate() == null : this.getPassRate().equals(other.getPassRate()))
                && (this.getFailRate() == null ? other.getFailRate() == null : this.getFailRate().equals(other.getFailRate()))
                && (this.getTotalTestCaseNumber() == null ? other.getTotalTestCaseNumber() == null : this.getTotalTestCaseNumber().equals(other.getTotalTestCaseNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getProjectCreatedAt() == null) ? 0 : getProjectCreatedAt().hashCode());
        result = prime * result + ((getActiveTestCaseNumber() == null) ? 0 : getActiveTestCaseNumber().hashCode());
        result = prime * result + ((getTotalRunNumber() == null) ? 0 : getTotalRunNumber().hashCode());
        result = prime * result + ((getTotalExecutionTime() == null) ? 0 : getTotalExecutionTime().hashCode());
        result = prime * result + ((getExecutedTestCaseNumber() == null) ? 0 : getExecutedTestCaseNumber().hashCode());
        result = prime * result + ((getFailedTestCaseNumber() == null) ? 0 : getFailedTestCaseNumber().hashCode());
        result = prime * result + ((getPassedTestCaseNumber() == null) ? 0 : getPassedTestCaseNumber().hashCode());
        result = prime * result + ((getPassRate() == null) ? 0 : getPassRate().hashCode());
        result = prime * result + ((getFailRate() == null) ? 0 : getFailRate().hashCode());
        result = prime * result + ((getTotalTestCaseNumber() == null) ? 0 : getTotalTestCaseNumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectId=").append(projectId);
        sb.append(", projectName=").append(projectName);
        sb.append(", projectCreatedAt=").append(projectCreatedAt);
        sb.append(", activeTestCaseNumber=").append(activeTestCaseNumber);
        sb.append(", totalRunNumber=").append(totalRunNumber);
        sb.append(", totalExecutionTime=").append(totalExecutionTime);
        sb.append(", executedTestCaseNumber=").append(executedTestCaseNumber);
        sb.append(", failedTestCaseNumber=").append(failedTestCaseNumber);
        sb.append(", passedTestCaseNumber=").append(passedTestCaseNumber);
        sb.append(", passRate=").append(passRate);
        sb.append(", failRate=").append(failRate);
        sb.append(", totalTestCaseNumber=").append(totalTestCaseNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        projectId("project_id", "projectId", "BIGINT", false), projectName("project_name", "projectName", "VARCHAR", false), projectCreatedAt("project_created_at",
                "projectCreatedAt", "TIMESTAMP", false), activeTestCaseNumber("active_test_case_number", "activeTestCaseNumber", "INTEGER",
                        false), totalRunNumber("total_run_number", "totalRunNumber", "BIGINT", false), totalExecutionTime("total_execution_time", "totalExecutionTime", "BIGINT",
                                false), executedTestCaseNumber("executed_test_case_number", "executedTestCaseNumber", "INTEGER", false), failedTestCaseNumber(
                                        "failed_test_case_number", "failedTestCaseNumber", "INTEGER", false), passedTestCaseNumber("passed_test_case_number",
                                                "passedTestCaseNumber", "INTEGER", false), passRate("pass_rate", "passRate", "NUMERIC", false), failRate("fail_rate", "failRate",
                                                        "NUMERIC", false), totalTestCaseNumber("total_test_case_number", "totalTestCaseNumber", "INTEGER", false);

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