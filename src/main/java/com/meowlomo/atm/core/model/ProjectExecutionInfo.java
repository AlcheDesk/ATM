package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

public class ProjectExecutionInfo implements Serializable {
    private Long projectId;

    private String projectName;

    private Date projectCreatedAt;

    private Date projectUpdatedAt;

    private Integer activeTestCaseNumber;

    private Integer executedTestCaseNumber;

    private Set<Long> activeTestCaseIds;

    private Set<Long> executedTestCaseIds;

    private Set<Long> passedTestCaseIds;

    private Integer passedTestCaseNumber;

    private Integer totalTestCaseNumber;

    private Set<Long> testCaseIds;

    private Boolean projectIsDeleted;

    private static final long serialVersionUID = 1L;

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

    public Date getProjectUpdatedAt() {
        return projectUpdatedAt;
    }

    public void setProjectUpdatedAt(Date projectUpdatedAt) {
        this.projectUpdatedAt = projectUpdatedAt;
    }

    public Integer getActiveTestCaseNumber() {
        return activeTestCaseNumber;
    }

    public void setActiveTestCaseNumber(Integer activeTestCaseNumber) {
        this.activeTestCaseNumber = activeTestCaseNumber;
    }

    public Integer getExecutedTestCaseNumber() {
        return executedTestCaseNumber;
    }

    public void setExecutedTestCaseNumber(Integer executedTestCaseNumber) {
        this.executedTestCaseNumber = executedTestCaseNumber;
    }

    public Set<Long> getActiveTestCaseIds() {
        return activeTestCaseIds;
    }

    public void setActiveTestCaseIds(Set<Long> activeTestCaseIds) {
        this.activeTestCaseIds = activeTestCaseIds;
    }

    public Set<Long> getExecutedTestCaseIds() {
        return executedTestCaseIds;
    }

    public void setExecutedTestCaseIds(Set<Long> executedTestCaseIds) {
        this.executedTestCaseIds = executedTestCaseIds;
    }

    public Set<Long> getPassedTestCaseIds() {
        return passedTestCaseIds;
    }

    public void setPassedTestCaseIds(Set<Long> passedTestCaseIds) {
        this.passedTestCaseIds = passedTestCaseIds;
    }

    public Integer getPassedTestCaseNumber() {
        return passedTestCaseNumber;
    }

    public void setPassedTestCaseNumber(Integer passedTestCaseNumber) {
        this.passedTestCaseNumber = passedTestCaseNumber;
    }

    public Integer getTotalTestCaseNumber() {
        return totalTestCaseNumber;
    }

    public void setTotalTestCaseNumber(Integer totalTestCaseNumber) {
        this.totalTestCaseNumber = totalTestCaseNumber;
    }

    public Set<Long> getTestCaseIds() {
        return testCaseIds;
    }

    public void setTestCaseIds(Set<Long> testCaseIds) {
        this.testCaseIds = testCaseIds;
    }

    public Boolean getProjectIsDeleted() {
        return projectIsDeleted;
    }

    public void setProjectIsDeleted(Boolean projectIsDeleted) {
        this.projectIsDeleted = projectIsDeleted;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProjectExecutionInfo other = (ProjectExecutionInfo) that;
        return (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getProjectCreatedAt() == null ? other.getProjectCreatedAt() == null : this.getProjectCreatedAt().equals(other.getProjectCreatedAt()))
            && (this.getProjectUpdatedAt() == null ? other.getProjectUpdatedAt() == null : this.getProjectUpdatedAt().equals(other.getProjectUpdatedAt()))
            && (this.getActiveTestCaseNumber() == null ? other.getActiveTestCaseNumber() == null : this.getActiveTestCaseNumber().equals(other.getActiveTestCaseNumber()))
            && (this.getExecutedTestCaseNumber() == null ? other.getExecutedTestCaseNumber() == null : this.getExecutedTestCaseNumber().equals(other.getExecutedTestCaseNumber()))
            && (this.getActiveTestCaseIds() == null ? other.getActiveTestCaseIds() == null : this.getActiveTestCaseIds().equals(other.getActiveTestCaseIds()))
            && (this.getExecutedTestCaseIds() == null ? other.getExecutedTestCaseIds() == null : this.getExecutedTestCaseIds().equals(other.getExecutedTestCaseIds()))
            && (this.getPassedTestCaseIds() == null ? other.getPassedTestCaseIds() == null : this.getPassedTestCaseIds().equals(other.getPassedTestCaseIds()))
            && (this.getPassedTestCaseNumber() == null ? other.getPassedTestCaseNumber() == null : this.getPassedTestCaseNumber().equals(other.getPassedTestCaseNumber()))
            && (this.getTotalTestCaseNumber() == null ? other.getTotalTestCaseNumber() == null : this.getTotalTestCaseNumber().equals(other.getTotalTestCaseNumber()))
            && (this.getTestCaseIds() == null ? other.getTestCaseIds() == null : this.getTestCaseIds().equals(other.getTestCaseIds()))
            && (this.getProjectIsDeleted() == null ? other.getProjectIsDeleted() == null : this.getProjectIsDeleted().equals(other.getProjectIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getProjectCreatedAt() == null) ? 0 : getProjectCreatedAt().hashCode());
        result = prime * result + ((getProjectUpdatedAt() == null) ? 0 : getProjectUpdatedAt().hashCode());
        result = prime * result + ((getActiveTestCaseNumber() == null) ? 0 : getActiveTestCaseNumber().hashCode());
        result = prime * result + ((getExecutedTestCaseNumber() == null) ? 0 : getExecutedTestCaseNumber().hashCode());
        result = prime * result + ((getActiveTestCaseIds() == null) ? 0 : getActiveTestCaseIds().hashCode());
        result = prime * result + ((getExecutedTestCaseIds() == null) ? 0 : getExecutedTestCaseIds().hashCode());
        result = prime * result + ((getPassedTestCaseIds() == null) ? 0 : getPassedTestCaseIds().hashCode());
        result = prime * result + ((getPassedTestCaseNumber() == null) ? 0 : getPassedTestCaseNumber().hashCode());
        result = prime * result + ((getTotalTestCaseNumber() == null) ? 0 : getTotalTestCaseNumber().hashCode());
        result = prime * result + ((getTestCaseIds() == null) ? 0 : getTestCaseIds().hashCode());
        result = prime * result + ((getProjectIsDeleted() == null) ? 0 : getProjectIsDeleted().hashCode());
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
        sb.append(", projectUpdatedAt=").append(projectUpdatedAt);
        sb.append(", activeTestCaseNumber=").append(activeTestCaseNumber);
        sb.append(", executedTestCaseNumber=").append(executedTestCaseNumber);
        sb.append(", activeTestCaseIds=").append(activeTestCaseIds);
        sb.append(", executedTestCaseIds=").append(executedTestCaseIds);
        sb.append(", passedTestCaseIds=").append(passedTestCaseIds);
        sb.append(", passedTestCaseNumber=").append(passedTestCaseNumber);
        sb.append(", totalTestCaseNumber=").append(totalTestCaseNumber);
        sb.append(", testCaseIds=").append(testCaseIds);
        sb.append(", projectIsDeleted=").append(projectIsDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        projectId("project_id", "projectId", "BIGINT", false),
        projectName("project_name", "projectName", "VARCHAR", false),
        projectCreatedAt("project_created_at", "projectCreatedAt", "TIMESTAMP", false),
        projectUpdatedAt("project_updated_at", "projectUpdatedAt", "TIMESTAMP", false),
        activeTestCaseNumber("active_test_case_number", "activeTestCaseNumber", "INTEGER", false),
        executedTestCaseNumber("executed_test_case_number", "executedTestCaseNumber", "INTEGER", false),
        activeTestCaseIds("active_test_case_ids", "activeTestCaseIds", "ARRAY", false),
        executedTestCaseIds("executed_test_case_ids", "executedTestCaseIds", "ARRAY", false),
        passedTestCaseIds("passed_test_case_ids", "passedTestCaseIds", "ARRAY", false),
        passedTestCaseNumber("passed_test_case_number", "passedTestCaseNumber", "INTEGER", false),
        totalTestCaseNumber("total_test_case_number", "totalTestCaseNumber", "INTEGER", false),
        testCaseIds("test_case_ids", "testCaseIds", "ARRAY", false),
        projectIsDeleted("project_is_deleted", "projectIsDeleted", "BIT", false);

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

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}