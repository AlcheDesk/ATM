package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestCaseShareFolder implements Serializable {
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    private Long id;

    private String name;

    private String description;

    private Date updatedAt;

    private Date createdAt;

    private Boolean deleted;

    private String log;

    private Long copyFromId;

    private String comment;

    private static final long serialVersionUID = 1L;

    private List<TestCase> testCases;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Long getCopyFromId() {
        return copyFromId;
    }

    public void setCopyFromId(Long copyFromId) {
        this.copyFromId = copyFromId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        TestCaseShareFolder other = (TestCaseShareFolder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getCopyFromId() == null ? other.getCopyFromId() == null : this.getCopyFromId().equals(other.getCopyFromId()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getCopyFromId() == null) ? 0 : getCopyFromId().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
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
        sb.append(", description=").append(description);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", deleted=").append(deleted);
        sb.append(", log=").append(log);
        sb.append(", copyFromId=").append(copyFromId);
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Deleted {
        NOT_DELETED(new Boolean("false"), "δɾ��"), IS_DELETED(new Boolean("true"), "��ɾ��");

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
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), description("description", "description", "VARCHAR", false), updatedAt("updated_at", "updatedAt",
                "TIMESTAMP", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), deleted("is_deleted", "deleted", "BIT",
                        false), log("log", "log", "VARCHAR", false), copyFromId("copy_from_id", "copyFromId", "BIGINT", false), comment("comment", "comment", "VARCHAR", false);

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