package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.annotations.ApiModelProperty;

public class Driver implements Serializable {
    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private String comment;

    private Boolean active;

    private Date updatedAt;

    private Date createdAt;

    private Boolean isDefault;

    @NotNull(message = "名称不能为空")
    private String type;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode parameter;

    private String vendorName;

    private String version;

    private JsonNode property;

    private Boolean predefined;

    private Long executionCount;

    private Long copyFromId;

    private String log;

    private String resourceMd5;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonNode getParameter() {
        return parameter;
    }

    public void setParameter(JsonNode parameter) {
        this.parameter = parameter;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public JsonNode getProperty() {
        return property;
    }

    public void setProperty(JsonNode property) {
        this.property = property;
    }

    public Boolean getPredefined() {
        return predefined;
    }

    public void setPredefined(Boolean predefined) {
        this.predefined = predefined;
    }

    public Long getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(Long executionCount) {
        this.executionCount = executionCount;
    }

    public Long getCopyFromId() {
        return copyFromId;
    }

    public void setCopyFromId(Long copyFromId) {
        this.copyFromId = copyFromId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getResourceMd5() {
        return resourceMd5;
    }

    public void setResourceMd5(String resourceMd5) {
        this.resourceMd5 = resourceMd5;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        Driver other = (Driver) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getIsDefault() == null ? other.getIsDefault() == null : this.getIsDefault().equals(other.getIsDefault()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getParameter() == null ? other.getParameter() == null : this.getParameter().equals(other.getParameter()))
                && (this.getVendorName() == null ? other.getVendorName() == null : this.getVendorName().equals(other.getVendorName()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
                && (this.getProperty() == null ? other.getProperty() == null : this.getProperty().equals(other.getProperty()))
                && (this.getPredefined() == null ? other.getPredefined() == null : this.getPredefined().equals(other.getPredefined()))
                && (this.getExecutionCount() == null ? other.getExecutionCount() == null : this.getExecutionCount().equals(other.getExecutionCount()))
                && (this.getCopyFromId() == null ? other.getCopyFromId() == null : this.getCopyFromId().equals(other.getCopyFromId()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getResourceMd5() == null ? other.getResourceMd5() == null : this.getResourceMd5().equals(other.getResourceMd5()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getIsDefault() == null) ? 0 : getIsDefault().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getParameter() == null) ? 0 : getParameter().hashCode());
        result = prime * result + ((getVendorName() == null) ? 0 : getVendorName().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getProperty() == null) ? 0 : getProperty().hashCode());
        result = prime * result + ((getPredefined() == null) ? 0 : getPredefined().hashCode());
        result = prime * result + ((getExecutionCount() == null) ? 0 : getExecutionCount().hashCode());
        result = prime * result + ((getCopyFromId() == null) ? 0 : getCopyFromId().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getResourceMd5() == null) ? 0 : getResourceMd5().hashCode());
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
        sb.append(", comment=").append(comment);
        sb.append(", active=").append(active);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", type=").append(type);
        sb.append(", parameter=").append(parameter);
        sb.append(", vendorName=").append(vendorName);
        sb.append(", version=").append(version);
        sb.append(", property=").append(property);
        sb.append(", predefined=").append(predefined);
        sb.append(", executionCount=").append(executionCount);
        sb.append(", copyFromId=").append(copyFromId);
        sb.append(", log=").append(log);
        sb.append(", resourceMd5=").append(resourceMd5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), comment("comment", "comment", "VARCHAR", false), active("is_active", "active", "BIT",
                false), updatedAt("updated_at", "updatedAt", "TIMESTAMP", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), isDefault("is_default", "isDefault",
                        "BIT", false), type("driver_type_id", "type", "OTHER", false), parameter("parameter", "parameter", "OTHER", false), vendorName("vendor_name", "vendorName",
                                "VARCHAR", false), version("version", "version", "VARCHAR", false), property("property", "property", "OTHER", false), predefined("is_predefined",
                                        "predefined", "BIT", false), executionCount("execution_count", "executionCount", "BIGINT", false), copyFromId("copy_from_id", "copyFromId",
                                                "BIGINT", false), log("log", "log", "VARCHAR", false), resourceMd5("resource_md5", "resourceMd5", "CHAR", false);

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