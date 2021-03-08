package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class RunSetResultJobLink implements Serializable {
    private Long id;

    @NotNull(message = "runSetResultId不能为空")
    private Long runSetResultId;

    @NotNull(message = "jobUuid不能为空")
    private UUID jobUuid;

    private Date createdAt;

    private Date updatedAt;

    private UUID runSetResultUuid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRunSetResultId() {
        return runSetResultId;
    }

    public void setRunSetResultId(Long runSetResultId) {
        this.runSetResultId = runSetResultId;
    }

    public UUID getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(UUID jobUuid) {
        this.jobUuid = jobUuid;
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

    public UUID getRunSetResultUuid() {
        return runSetResultUuid;
    }

    public void setRunSetResultUuid(UUID runSetResultUuid) {
        this.runSetResultUuid = runSetResultUuid;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        RunSetResultJobLink other = (RunSetResultJobLink) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRunSetResultId() == null ? other.getRunSetResultId() == null : this.getRunSetResultId().equals(other.getRunSetResultId()))
                && (this.getJobUuid() == null ? other.getJobUuid() == null : this.getJobUuid().equals(other.getJobUuid()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getRunSetResultUuid() == null ? other.getRunSetResultUuid() == null : this.getRunSetResultUuid().equals(other.getRunSetResultUuid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRunSetResultId() == null) ? 0 : getRunSetResultId().hashCode());
        result = prime * result + ((getJobUuid() == null) ? 0 : getJobUuid().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getRunSetResultUuid() == null) ? 0 : getRunSetResultUuid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", runSetResultId=").append(runSetResultId);
        sb.append(", jobUuid=").append(jobUuid);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", runSetResultUuid=").append(runSetResultUuid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), runSetResultId("run_set_result_id", "runSetResultId", "BIGINT", false), jobUuid("job_uuid", "jobUuid", "OTHER", false), createdAt(
                "created_at", "createdAt", "TIMESTAMP",
                false), updatedAt("updated_at", "updatedAt", "TIMESTAMP", false), runSetResultUuid("run_set_result_uuid", "runSetResultUuid", "OTHER", false);

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