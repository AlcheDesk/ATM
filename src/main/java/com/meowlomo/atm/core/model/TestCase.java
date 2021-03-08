package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;

public class TestCase implements Serializable {
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private Date createdAt;

    private Date updatedAt;

    private Boolean flagged;

    private String comment;

    private String log;

    private Boolean deleted;

    private String group;

    private Integer priority;

    private String type;

    private Long projectId;

    private Long copyFromId;

    private Integer timeout;

    private Long refRunSetId;

    private String resourceMd5;

    private JsonNode parameter;

    private UUID uuid;

    private Boolean singleton;

    private String projectName;

    private static final long serialVersionUID = 1L;

    private List<Instruction> instructions;

    private List<Tag> tags;

    private List<TestCaseOptionEntry> testCaseOptions;

    private List<UUID> tasks;

    private List<Module> modules;

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

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getCopyFromId() {
        return copyFromId;
    }

    public void setCopyFromId(Long copyFromId) {
        this.copyFromId = copyFromId;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Long getRefRunSetId() {
        return refRunSetId;
    }

    public void setRefRunSetId(Long refRunSetId) {
        this.refRunSetId = refRunSetId;
    }

    public String getResourceMd5() {
        return resourceMd5;
    }

    public void setResourceMd5(String resourceMd5) {
        this.resourceMd5 = resourceMd5;
    }

    public JsonNode getParameter() {
        return parameter;
    }

    public void setParameter(JsonNode parameter) {
        this.parameter = parameter;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean getSingleton() {
        return singleton;
    }

    public void setSingleton(Boolean singleton) {
        this.singleton = singleton;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<TestCaseOptionEntry> getTestCaseOptions() {
        return testCaseOptions;
    }

    public void setTestCaseOptions(List<TestCaseOptionEntry> testCaseOptions) {
        this.testCaseOptions = testCaseOptions;
    }

    public List<UUID> getTasks() {
        return tasks;
    }

    public void setTasks(List<UUID> tasks) {
        this.tasks = tasks;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        TestCase other = (TestCase) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getFlagged() == null ? other.getFlagged() == null : this.getFlagged().equals(other.getFlagged()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
                && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
                && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
                && (this.getCopyFromId() == null ? other.getCopyFromId() == null : this.getCopyFromId().equals(other.getCopyFromId()))
                && (this.getTimeout() == null ? other.getTimeout() == null : this.getTimeout().equals(other.getTimeout()))
                && (this.getRefRunSetId() == null ? other.getRefRunSetId() == null : this.getRefRunSetId().equals(other.getRefRunSetId()))
                && (this.getResourceMd5() == null ? other.getResourceMd5() == null : this.getResourceMd5().equals(other.getResourceMd5()))
                && (this.getParameter() == null ? other.getParameter() == null : this.getParameter().equals(other.getParameter()))
                && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
                && (this.getSingleton() == null ? other.getSingleton() == null : this.getSingleton().equals(other.getSingleton()))
                && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getFlagged() == null) ? 0 : getFlagged().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getCopyFromId() == null) ? 0 : getCopyFromId().hashCode());
        result = prime * result + ((getTimeout() == null) ? 0 : getTimeout().hashCode());
        result = prime * result + ((getRefRunSetId() == null) ? 0 : getRefRunSetId().hashCode());
        result = prime * result + ((getResourceMd5() == null) ? 0 : getResourceMd5().hashCode());
        result = prime * result + ((getParameter() == null) ? 0 : getParameter().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getSingleton() == null) ? 0 : getSingleton().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", flagged=").append(flagged);
        sb.append(", comment=").append(comment);
        sb.append(", log=").append(log);
        sb.append(", deleted=").append(deleted);
        sb.append(", group=").append(group);
        sb.append(", priority=").append(priority);
        sb.append(", type=").append(type);
        sb.append(", projectId=").append(projectId);
        sb.append(", copyFromId=").append(copyFromId);
        sb.append(", timeout=").append(timeout);
        sb.append(", refRunSetId=").append(refRunSetId);
        sb.append(", resourceMd5=").append(resourceMd5);
        sb.append(", parameter=").append(parameter);
        sb.append(", uuid=").append(uuid);
        sb.append(", singleton=").append(singleton);
        sb.append(", projectName=").append(projectName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Deleted {
        NOT_DELETED(new Boolean("false"), "NOT_DELETED"), IS_DELETED(new Boolean("true"), "IS_DELETED");

        private final Boolean value;

        private final String name;

        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        public Boolean getValue() {
            return this.value;
        }

        public Boolean value() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum Column {
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), updatedAt("updated_at", "updatedAt",
                "TIMESTAMP",
                false), flagged("is_flagged", "flagged", "BIT", false), comment("comment", "comment", "VARCHAR", false), log("log", "log", "VARCHAR", false), deleted("is_deleted",
                        "deleted", "BIT", false), group("group_id", "group", "OTHER", false), priority("priority", "priority", "INTEGER", false), testCaseType("test_case_type_id",
                                "testCaseType", "OTHER", false), projectId("project_id", "projectId", "BIGINT", false), copyFromId("copy_from_id", "copyFromId", "BIGINT",
                                        false), timeout("timeout", "timeout", "INTEGER", false), refRunSetId("ref_run_set_id", "refRunSetId", "BIGINT", false), resourceMd5(
                                                "resource_md5", "resourceMd5", "CHAR", false), parameter("parameter", "parameter", "OTHER", false), uuid("uuid", "uuid", "OTHER",
                                                        false), singleton("singleton", "singleton", "BIT", false), projectName("project_name", "projectName", "VARCHAR", false);

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