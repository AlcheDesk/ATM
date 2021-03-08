package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.meowlomo.atm.config.RuntimeVariables;

import io.swagger.annotations.ApiModelProperty;

public class Element implements Serializable {
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private String comment;

    private String locatorValue;

    private String htmlPositionX;

    private String htmlPositionY;

    private Boolean deleted;

    private Date createdAt;

    private Date updatedAt;

    private String log;

    @NotNull(message = "类型不能为空")
    private String type;

    private String locatorType;

    private String color;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode parameter;

    private Boolean isDriver;

    private Long projectId;

    private Long sectionId;

    private Long copyFromId;

    private Long applicationId;

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private Long typeId;

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

    public String getLocatorValue() {
        return locatorValue;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    public String getHtmlPositionX() {
        return htmlPositionX;
    }

    public void setHtmlPositionX(String htmlPositionX) {
        this.htmlPositionX = htmlPositionX;
    }

    public String getHtmlPositionY() {
        return htmlPositionY;
    }

    public void setHtmlPositionY(String htmlPositionY) {
        this.htmlPositionY = htmlPositionY;
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

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public JsonNode getParameter() {
        return parameter;
    }

    public void setParameter(JsonNode parameter) {
        this.parameter = parameter;
    }

    public Boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(Boolean isDriver) {
        this.isDriver = isDriver;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getCopyFromId() {
        return copyFromId;
    }

    public void setCopyFromId(Long copyFromId) {
        this.copyFromId = copyFromId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getTypeId() {
        if (this.type != null && RuntimeVariables.getElementLocatorTypeToIdMap().containsKey(this.type)) {
            return RuntimeVariables.getElementLocatorTypeToIdMap().get(this.type);
        }
        else {
            return null;
        }
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        Element other = (Element) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getLocatorValue() == null ? other.getLocatorValue() == null : this.getLocatorValue().equals(other.getLocatorValue()))
                && (this.getHtmlPositionX() == null ? other.getHtmlPositionX() == null : this.getHtmlPositionX().equals(other.getHtmlPositionX()))
                && (this.getHtmlPositionY() == null ? other.getHtmlPositionY() == null : this.getHtmlPositionY().equals(other.getHtmlPositionY()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getLocatorType() == null ? other.getLocatorType() == null : this.getLocatorType().equals(other.getLocatorType()))
                && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
                && (this.getParameter() == null ? other.getParameter() == null : this.getParameter().equals(other.getParameter()))
                && (this.getIsDriver() == null ? other.getIsDriver() == null : this.getIsDriver().equals(other.getIsDriver()))
                && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
                && (this.getSectionId() == null ? other.getSectionId() == null : this.getSectionId().equals(other.getSectionId()))
                && (this.getCopyFromId() == null ? other.getCopyFromId() == null : this.getCopyFromId().equals(other.getCopyFromId()))
                && (this.getApplicationId() == null ? other.getApplicationId() == null : this.getApplicationId().equals(other.getApplicationId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getLocatorValue() == null) ? 0 : getLocatorValue().hashCode());
        result = prime * result + ((getHtmlPositionX() == null) ? 0 : getHtmlPositionX().hashCode());
        result = prime * result + ((getHtmlPositionY() == null) ? 0 : getHtmlPositionY().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLocatorType() == null) ? 0 : getLocatorType().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getParameter() == null) ? 0 : getParameter().hashCode());
        result = prime * result + ((getIsDriver() == null) ? 0 : getIsDriver().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getSectionId() == null) ? 0 : getSectionId().hashCode());
        result = prime * result + ((getCopyFromId() == null) ? 0 : getCopyFromId().hashCode());

        result = prime * result + ((getApplicationId() == null) ? 0 : getApplicationId().hashCode());
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
        sb.append(", comment=").append(comment);
        sb.append(", locatorValue=").append(locatorValue);
        sb.append(", htmlPositionX=").append(htmlPositionX);
        sb.append(", htmlPositionY=").append(htmlPositionY);
        sb.append(", deleted=").append(deleted);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", log=").append(log);
        sb.append(", type=").append(type);
        sb.append(", locatorType=").append(locatorType);
        sb.append(", color=").append(color);
        sb.append(", parameter=").append(parameter);
        sb.append(", isDriver=").append(isDriver);
        sb.append(", projectId=").append(projectId);
        sb.append(", sectionId=").append(sectionId);
        sb.append(", copyFromId=").append(copyFromId);

        sb.append(", applicationId=").append(applicationId);
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
        id("id", "id", "BIGINT", false), name("name", "name", "VARCHAR", false), comment("comment", "comment", "VARCHAR", false), locatorValue("locator_value", "locatorValue",
                "VARCHAR", false), htmlPositionX("html_position_x", "htmlPositionX", "VARCHAR", false), htmlPositionY("html_position_y", "htmlPositionY", "VARCHAR",
                        false), deleted("is_deleted", "deleted", "BIT", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), updatedAt("updated_at", "updatedAt",
                                "TIMESTAMP", false), log("log", "log", "VARCHAR", false), elementType("element_type_id", "elementType", "OTHER", false), elementLocatorType(
                                        "element_locator_type_id", "elementLocatorType", "OTHER", false), color("color_id", "color", "OTHER", false), parameter("parameter",
                                                "parameter", "OTHER", false), isDriver("is_driver", "isDriver", "BIT", false), projectId("project_id", "projectId", "BIGINT",
                                                        false), sectionId("section_id", "sectionId", "BIGINT", false), copyFromId("copy_from_id", "copyFromId", "BIGINT",
                                                                false), applicationId("application_id", "applicationId", "BIGINT", false);

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