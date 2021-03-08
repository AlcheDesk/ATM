package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ExecutionLog implements Serializable {

    private Long id;

    @NotNull(message = "名称不能为空")
    private String message;

    private Date updatedAt;

    private Date createdAt;

    private String logLevel;

    @NotNull(message = "instructionResultId不能为空")
    private Long instructionResultId;

    @NotNull(message = "runId不能为空")
    private Long runId;

    private String runType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Long getInstructionResultId() {
        return instructionResultId;
    }

    public void setInstructionResultId(Long instructionResultId) {
        this.instructionResultId = instructionResultId;
    }

    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        ExecutionLog other = (ExecutionLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getLogLevel() == null ? other.getLogLevel() == null : this.getLogLevel().equals(other.getLogLevel()))
                && (this.getInstructionResultId() == null ? other.getInstructionResultId() == null : this.getInstructionResultId().equals(other.getInstructionResultId()))
                && (this.getRunId() == null ? other.getRunId() == null : this.getRunId().equals(other.getRunId()))
                && (this.getRunType() == null ? other.getRunType() == null : this.getRunType().equals(other.getRunType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLogLevel() == null) ? 0 : getLogLevel().hashCode());
        result = prime * result + ((getInstructionResultId() == null) ? 0 : getInstructionResultId().hashCode());
        result = prime * result + ((getRunId() == null) ? 0 : getRunId().hashCode());
        result = prime * result + ((getRunType() == null) ? 0 : getRunType().hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", message=").append(message);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", logLevel=").append(logLevel);
        sb.append(", instructionResultId=").append(instructionResultId);
        sb.append(", runId=").append(runId);
        sb.append(", runType=").append(runType);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), message("message", "message", "VARCHAR", false), updatedAt("updated_at", "updatedAt", "TIMESTAMP", false), createdAt("created_at",
                "createdAt", "TIMESTAMP", false), logLevel("log_level_id", "logLevel", "OTHER", false), instructionResultId("instruction_result_id", "instructionResultId",
                        "BIGINT", false), runId("run_id", "runId", "BIGINT", false), runType("run_type_id", "runType", "OTHER", false);

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