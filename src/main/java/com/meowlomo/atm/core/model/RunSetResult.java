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

public class RunSetResult implements Serializable {
    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private Date createdAt;

    private Date updatedAt;

    private Date startAt;

    private Date endAt;

    private String status;

    private String log;

    private String group;

    private String type;

    private Integer priority;

    private Long runSetId;

    private Boolean finished;

    private String runType;

    private String sourceType;

    private String comment;

    private JsonNode run;

    private UUID uuid;

    private Integer totalRunNumber;

    private Integer passedRunNumber;

    private Integer failedRunNumber;

    private Set<Long> runIds;

    private Set<Long> passedRunIds;

    private Set<Long> failedRunIds;

    private static final long serialVersionUID = 1L;

    private List<Run> runs;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getRunSetId() {
        return runSetId;
    }

    public void setRunSetId(Long runSetId) {
        this.runSetId = runSetId;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public JsonNode getRun() {
        return run;
    }

    public void setRun(JsonNode run) {
        this.run = run;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getTotalRunNumber() {
        return totalRunNumber;
    }

    public void setTotalRunNumber(Integer totalRunNumber) {
        this.totalRunNumber = totalRunNumber;
    }

    public Integer getPassedRunNumber() {
        return passedRunNumber;
    }

    public void setPassedRunNumber(Integer passedRunNumber) {
        this.passedRunNumber = passedRunNumber;
    }

    public Integer getFailedRunNumber() {
        return failedRunNumber;
    }

    public void setFailedRunNumber(Integer failedRunNumber) {
        this.failedRunNumber = failedRunNumber;
    }

    public Set<Long> getRunIds() {
        return runIds;
    }

    public void setRunIds(Set<Long> runIds) {
        this.runIds = runIds;
    }

    public Set<Long> getPassedRunIds() {
        return passedRunIds;
    }

    public void setPassedRunIds(Set<Long> passedRunIds) {
        this.passedRunIds = passedRunIds;
    }

    public Set<Long> getFailedRunIds() {
        return failedRunIds;
    }

    public void setFailedRunIds(Set<Long> failedRunIds) {
        this.failedRunIds = failedRunIds;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        RunSetResult other = (RunSetResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getStartAt() == null ? other.getStartAt() == null : this.getStartAt().equals(other.getStartAt()))
                && (this.getEndAt() == null ? other.getEndAt() == null : this.getEndAt().equals(other.getEndAt()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
                && (this.getRunSetId() == null ? other.getRunSetId() == null : this.getRunSetId().equals(other.getRunSetId()))
                && (this.getFinished() == null ? other.getFinished() == null : this.getFinished().equals(other.getFinished()))
                && (this.getRunType() == null ? other.getRunType() == null : this.getRunType().equals(other.getRunType()))
                && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getRun() == null ? other.getRun() == null : this.getRun().equals(other.getRun()))
                && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
                && (this.getTotalRunNumber() == null ? other.getTotalRunNumber() == null : this.getTotalRunNumber().equals(other.getTotalRunNumber()))
                && (this.getPassedRunNumber() == null ? other.getPassedRunNumber() == null : this.getPassedRunNumber().equals(other.getPassedRunNumber()))
                && (this.getFailedRunNumber() == null ? other.getFailedRunNumber() == null : this.getFailedRunNumber().equals(other.getFailedRunNumber()))
                && (this.getRunIds() == null ? other.getRunIds() == null : this.getRunIds().equals(other.getRunIds()))
                && (this.getPassedRunIds() == null ? other.getPassedRunIds() == null : this.getPassedRunIds().equals(other.getPassedRunIds()))
                && (this.getFailedRunIds() == null ? other.getFailedRunIds() == null : this.getFailedRunIds().equals(other.getFailedRunIds()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getStartAt() == null) ? 0 : getStartAt().hashCode());
        result = prime * result + ((getEndAt() == null) ? 0 : getEndAt().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getRunSetId() == null) ? 0 : getRunSetId().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getRunType() == null) ? 0 : getRunType().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getRun() == null) ? 0 : getRun().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getTotalRunNumber() == null) ? 0 : getTotalRunNumber().hashCode());
        result = prime * result + ((getPassedRunNumber() == null) ? 0 : getPassedRunNumber().hashCode());
        result = prime * result + ((getFailedRunNumber() == null) ? 0 : getFailedRunNumber().hashCode());
        result = prime * result + ((getRunIds() == null) ? 0 : getRunIds().hashCode());
        result = prime * result + ((getPassedRunIds() == null) ? 0 : getPassedRunIds().hashCode());
        result = prime * result + ((getFailedRunIds() == null) ? 0 : getFailedRunIds().hashCode());
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
        sb.append(", startAt=").append(startAt);
        sb.append(", endAt=").append(endAt);
        sb.append(", status=").append(status);
        sb.append(", log=").append(log);
        sb.append(", group=").append(group);
        sb.append(", runSetType=").append(type);
        sb.append(", priority=").append(priority);
        sb.append(", runSetId=").append(runSetId);
        sb.append(", finished=").append(finished);
        sb.append(", runType=").append(runType);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", comment=").append(comment);
        sb.append(", run=").append(run);
        sb.append(", uuid=").append(uuid);
        sb.append(", totalRunNumber=").append(totalRunNumber);
        sb.append(", passedRunNumber=").append(passedRunNumber);
        sb.append(", failedRunNumber=").append(failedRunNumber);
        sb.append(", runIds=").append(runIds);
        sb.append(", passedRunIds=").append(passedRunIds);
        sb.append(", failedRunIds=").append(failedRunIds);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), updatedAt("updated_at", "updatedAt",
                "TIMESTAMP", false), startAt("start_at", "startAt", "TIMESTAMP", false), endAt("end_at", "endAt", "TIMESTAMP", false), status("status_id", "status", "OTHER",
                        false), log("log", "log", "VARCHAR", false), group("group_id", "group", "OTHER", false), runSetType("run_set_type_id", "runSetType", "OTHER",
                                false), priority("priority", "priority", "INTEGER", false), runSetId("run_set_id", "runSetId", "BIGINT", false), finished("is_finished", "finished",
                                        "BIT", false), runType("run_type_id", "runType", "OTHER", false), sourceType("source_type_id", "sourceType", "OTHER",
                                                false), comment("comment", "comment", "VARCHAR", false), run("run", "run", "OTHER", false), uuid("uuid", "uuid", "OTHER",
                                                        false), totalRunNumber("total_run_number", "totalRunNumber", "INTEGER", false), passedRunNumber("passed_run_number",
                                                                "passedRunNumber", "INTEGER", false), failedRunNumber("failed_run_number", "failedRunNumber", "INTEGER",
                                                                        false), runIds("run_ids", "runIds", "ARRAY", false), passedRunIds("passed_run_ids", "passedRunIds", "ARRAY",
                                                                                false), failedRunIds("failed_run_ids", "failedRunIds", "ARRAY", false);

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