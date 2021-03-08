package com.meowlomo.atm.external.ems.model;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

public class Job implements Serializable {
    private Long id;

    private String name;

    private String type;

    private String description;

    private Integer priority;

    @ApiModelProperty(value = "UUID", dataType = "String")
    private UUID uuid;

    private String status;

    private Boolean isBuilt;

    private Boolean finished;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode parameter;

    private String group;

    private Date updatedAt;

    private Date createdAt;

    private UUID externalIdentifier;

    private Date executionStartAt;

    private Date executionEndAt;

    private static final long serialVersionUID = 1L;

    private List<Task> tasks;

    private List<JobLog> logs;

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
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getIsBuilt() {
        return isBuilt;
    }

    public void setIsBuilt(Boolean isBuilt) {
        this.isBuilt = isBuilt;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public JsonNode getParameter() {
        return parameter;
    }

    public void setParameter(JsonNode parameter) {
        this.parameter = parameter;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
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

    public UUID getExternalIdentifier() {
        return externalIdentifier;
    }

    public void setExternalIdentifier(UUID externalIdentifier) {
        this.externalIdentifier = externalIdentifier;
    }

    public Date getExecutionStartAt() {
        return executionStartAt;
    }

    public void setExecutionStartAt(Date executionStartAt) {
        this.executionStartAt = executionStartAt;
    }

    public Date getExecutionEndAt() {
        return executionEndAt;
    }

    public void setExecutionEndAt(Date executionEndAt) {
        this.executionEndAt = executionEndAt;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<JobLog> getLogs() {
        return logs;
    }

    public void setLogs(List<JobLog> logs) {
        this.logs = logs;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        Job other = (Job) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getDescription() == null ? other.getDescription() == null
                        : this.getDescription().equals(other.getDescription()))
                && (this.getPriority() == null ? other.getPriority() == null
                        : this.getPriority().equals(other.getPriority()))
                && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getIsBuilt() == null ? other.getIsBuilt() == null
                        : this.getIsBuilt().equals(other.getIsBuilt()))
                && (this.getFinished() == null ? other.getFinished() == null
                        : this.getFinished().equals(other.getFinished()))
                && (this.getParameter() == null ? other.getParameter() == null
                        : this.getParameter().equals(other.getParameter()))
                && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null
                        : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null
                        : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getExternalIdentifier() == null ? other.getExternalIdentifier() == null
                        : this.getExternalIdentifier().equals(other.getExternalIdentifier()))
                && (this.getExecutionStartAt() == null ? other.getExecutionStartAt() == null
                        : this.getExecutionStartAt().equals(other.getExecutionStartAt()))
                && (this.getExecutionEndAt() == null ? other.getExecutionEndAt() == null
                        : this.getExecutionEndAt().equals(other.getExecutionEndAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsBuilt() == null) ? 0 : getIsBuilt().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getParameter() == null) ? 0 : getParameter().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getExternalIdentifier() == null) ? 0 : getExternalIdentifier().hashCode());
        result = prime * result + ((getExecutionStartAt() == null) ? 0 : getExecutionStartAt().hashCode());
        result = prime * result + ((getExecutionEndAt() == null) ? 0 : getExecutionEndAt().hashCode());
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
        sb.append(", type=").append(type);
        sb.append(", description=").append(description);
        sb.append(", priority=").append(priority);
        sb.append(", uuid=").append(uuid);
        sb.append(", status=").append(status);
        sb.append(", isBuilt=").append(isBuilt);
        sb.append(", finished=").append(finished);
        sb.append(", parameter=").append(parameter);
        sb.append(", group=").append(group);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", externalIdentifier=").append(externalIdentifier);
        sb.append(", executionStartAt=").append(executionStartAt);
        sb.append(", executionEndAt=").append(executionEndAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), type("job_type_id", "type", "OTHER",
                false), description("description", "description", "VARCHAR", false), priority("priority", "priority",
                        "INTEGER", false), uuid("uuid", "uuid", "OTHER", false), status("status_id", "status", "OTHER",
                                false), isBuilt("is_built", "isBuilt", "BIT", false), finished("is_finished",
                                        "finished", "BIT", false), parameter("parameter", "parameter", "OTHER",
                                                false), group("group_id", "group", "OTHER", false), updatedAt(
                                                        "updated_at", "updatedAt", "TIMESTAMP",
                                                        false), createdAt("created_at", "createdAt", "TIMESTAMP",
                                                                false), externalIdentifier("external_identifier",
                                                                        "externalIdentifier", "OTHER",
                                                                        false), executionStartAt("execution_start_at",
                                                                                "executionStartAt", "TIMESTAMP",
                                                                                false), executionEndAt(
                                                                                        "execution_end_at",
                                                                                        "executionEndAt", "TIMESTAMP",
                                                                                        false);

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
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER)
                        .toString();
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