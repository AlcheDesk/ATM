package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.constraints.NotNull;

public class DriverProperty implements Serializable {

    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private Long driverVendorId;

    private String defaultValue;

    private String defaultAction;

    private String description;

    private String valueType;

    private Boolean predefinedValueRequired;

    private Boolean active;

    private Boolean predefined;

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

    public Long getDriverVendorId() {
        return driverVendorId;
    }

    public void setDriverVendorId(Long driverVendorId) {
        this.driverVendorId = driverVendorId;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Boolean getPredefinedValueRequired() {
        return predefinedValueRequired;
    }

    public void setPredefinedValueRequired(Boolean predefinedValueRequired) {
        this.predefinedValueRequired = predefinedValueRequired;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        DriverProperty other = (DriverProperty) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getDriverVendorId() == null ? other.getDriverVendorId() == null : this.getDriverVendorId().equals(other.getDriverVendorId()))
                && (this.getDefaultValue() == null ? other.getDefaultValue() == null : this.getDefaultValue().equals(other.getDefaultValue()))
                && (this.getDefaultAction() == null ? other.getDefaultAction() == null : this.getDefaultAction().equals(other.getDefaultAction()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getValueType() == null ? other.getValueType() == null : this.getValueType().equals(other.getValueType()))
                && (this.getPredefinedValueRequired() == null ? other.getPredefinedValueRequired() == null
                        : this.getPredefinedValueRequired().equals(other.getPredefinedValueRequired()))
                && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()))
                && (this.getPredefined() == null ? other.getPredefined() == null : this.getPredefined().equals(other.getPredefined()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDriverVendorId() == null) ? 0 : getDriverVendorId().hashCode());
        result = prime * result + ((getDefaultValue() == null) ? 0 : getDefaultValue().hashCode());
        result = prime * result + ((getDefaultAction() == null) ? 0 : getDefaultAction().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getValueType() == null) ? 0 : getValueType().hashCode());
        result = prime * result + ((getPredefinedValueRequired() == null) ? 0 : getPredefinedValueRequired().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        result = prime * result + ((getPredefined() == null) ? 0 : getPredefined().hashCode());
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
        sb.append(", driverVendorId=").append(driverVendorId);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", defaultAction=").append(defaultAction);
        sb.append(", description=").append(description);
        sb.append(", valueType=").append(valueType);
        sb.append(", predefinedValueRequired=").append(predefinedValueRequired);
        sb.append(", active=").append(active);
        sb.append(", predefined=").append(predefined);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), driverVendorId("driver_vendor_id", "driverVendorId", "BIGINT", false), defaultValue(
                "default_value", "defaultValue", "VARCHAR", false), defaultAction("default_action", "defaultAction", "VARCHAR", false), description("description", "description",
                        "VARCHAR", false), valueType("value_type", "valueType", "VARCHAR", false), predefinedValueRequired("is_predefined_value_required",
                                "predefinedValueRequired", "BIT", false), active("is_active", "active", "BIT", false), predefined("is_predefined", "predefined", "BIT", false);

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