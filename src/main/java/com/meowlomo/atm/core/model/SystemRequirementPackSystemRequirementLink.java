package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.constraints.NotNull;

public class SystemRequirementPackSystemRequirementLink implements Serializable {

    private Long id;

    @NotNull(message = "systemRequirementPackId不能为空")
    private Long systemRequirementPackId;

    @NotNull(message = "systemRequirementId不能为空")
    private Long systemRequirementId;

    private String systemRequirementType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemRequirementPackId() {
        return systemRequirementPackId;
    }

    public void setSystemRequirementPackId(Long systemRequirementPackId) {
        this.systemRequirementPackId = systemRequirementPackId;
    }

    public Long getSystemRequirementId() {
        return systemRequirementId;
    }

    public void setSystemRequirementId(Long systemRequirementId) {
        this.systemRequirementId = systemRequirementId;
    }

    public String getSystemRequirementType() {
        return systemRequirementType;
    }

    public void setSystemRequirementType(String systemRequirementType) {
        this.systemRequirementType = systemRequirementType;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        SystemRequirementPackSystemRequirementLink other = (SystemRequirementPackSystemRequirementLink) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSystemRequirementPackId() == null ? other.getSystemRequirementPackId() == null
                        : this.getSystemRequirementPackId().equals(other.getSystemRequirementPackId()))
                && (this.getSystemRequirementId() == null ? other.getSystemRequirementId() == null : this.getSystemRequirementId().equals(other.getSystemRequirementId()))
                && (this.getSystemRequirementType() == null ? other.getSystemRequirementType() == null : this.getSystemRequirementType().equals(other.getSystemRequirementType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSystemRequirementPackId() == null) ? 0 : getSystemRequirementPackId().hashCode());
        result = prime * result + ((getSystemRequirementId() == null) ? 0 : getSystemRequirementId().hashCode());
        result = prime * result + ((getSystemRequirementType() == null) ? 0 : getSystemRequirementType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", systemRequirementPackId=").append(systemRequirementPackId);
        sb.append(", systemRequirementId=").append(systemRequirementId);
        sb.append(", systemRequirementType=").append(systemRequirementType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), systemRequirementPackId("system_requirement_pack_id", "systemRequirementPackId", "BIGINT", false), systemRequirementId(
                "system_requirement_id", "systemRequirementId", "BIGINT", false), systemRequirementType("system_requirement_type_id", "systemRequirementType", "OTHER", false);

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