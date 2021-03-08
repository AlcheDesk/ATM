package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.constraints.NotNull;

public class InstructionType implements Serializable {

    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private Boolean active;

    private Boolean predefined;

    private Boolean isDriver;

    private String driverType;

    private Boolean isElementRequired;

    private Long virtualElementId;

    private String overridableFields;

    private static final long serialVersionUID = 1L;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getPredefined() {
        return predefined;
    }

    public void setPredefined(Boolean predefined) {
        this.predefined = predefined;
    }

    public Boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(Boolean isDriver) {
        this.isDriver = isDriver;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public Boolean getIsElementRequired() {
        return isElementRequired;
    }

    public void setIsElementRequired(Boolean isElementRequired) {
        this.isElementRequired = isElementRequired;
    }

    public Long getVirtualElementId() {
        return virtualElementId;
    }

    public void setVirtualElementId(Long virtualElementId) {
        this.virtualElementId = virtualElementId;
    }

    public String getOverridableFields() {
        return overridableFields;
    }

    public void setOverridableFields(String overridableFields) {
        this.overridableFields = overridableFields;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        InstructionType other = (InstructionType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()))
                && (this.getPredefined() == null ? other.getPredefined() == null : this.getPredefined().equals(other.getPredefined()))
                && (this.getIsDriver() == null ? other.getIsDriver() == null : this.getIsDriver().equals(other.getIsDriver()))
                && (this.getDriverType() == null ? other.getDriverType() == null : this.getDriverType().equals(other.getDriverType()))
                && (this.getIsElementRequired() == null ? other.getIsElementRequired() == null : this.getIsElementRequired().equals(other.getIsElementRequired()))
                && (this.getVirtualElementId() == null ? other.getVirtualElementId() == null : this.getVirtualElementId().equals(other.getVirtualElementId()))
                && (this.getOverridableFields() == null ? other.getOverridableFields() == null : this.getOverridableFields().equals(other.getOverridableFields()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        result = prime * result + ((getPredefined() == null) ? 0 : getPredefined().hashCode());
        result = prime * result + ((getIsDriver() == null) ? 0 : getIsDriver().hashCode());
        result = prime * result + ((getDriverType() == null) ? 0 : getDriverType().hashCode());
        result = prime * result + ((getIsElementRequired() == null) ? 0 : getIsElementRequired().hashCode());
        result = prime * result + ((getVirtualElementId() == null) ? 0 : getVirtualElementId().hashCode());
        result = prime * result + ((getOverridableFields() == null) ? 0 : getOverridableFields().hashCode());
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
        sb.append(", active=").append(active);
        sb.append(", predefined=").append(predefined);
        sb.append(", isDriver=").append(isDriver);
        sb.append(", driverType=").append(driverType);
        sb.append(", isElementRequired=").append(isElementRequired);
        sb.append(", virtualElementId=").append(virtualElementId);
        sb.append(", overridableFields=").append(overridableFields);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), active("is_active", "active", "BIT", false), predefined("is_predefined", "predefined", "BIT",
                false), isDriver("is_driver", "isDriver", "BIT", false), driverType("driver_type_id", "driverType", "OTHER", false), isElementRequired("is_element_required",
                        "isElementRequired", "BIT", false), virtualElementId("virtual_element_id", "virtualElementId", "BIGINT",
                                false), overridableFields("overridable_fields", "overridableFields", "VARCHAR", false);

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