package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.meowlomo.atm.config.RuntimeVariables;

import io.swagger.annotations.ApiModelProperty;

public class Instruction implements Serializable {
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    private Long id;

    private String comment;

    private Date updatedAt;

    private Date createdAt;

    private String input;

    private Long elementId;

    private Boolean deleted;

    private Long projectId;

    private Long applicationId;

    private Long sectionId;

    private Long orderIndex;

    private String log;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode data;

    private String color;

    @NotNull(message = "类型不能为空")
    private String type;

    private String stepDescription;

    private String expectedDescription;

    private Long testCaseId;

    private Long refTestCaseId;

    private String action;

    private Boolean isDriver;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode parameter;

    private Long copyFromId;

    private String expectedValue;

    private String resourceMd5;

    private String elementType;

    private String driverType;

    private Long refTestCaseOverwriteId;

    private Long testCaseShareFolderId;

    private String driverAlias;

    private String target;

    private String refTestCaseOverwriteName;

    private static final long serialVersionUID = 1L;

    private String logicalOrderIndex;

    private List<InstructionOptionEntry> instructionOptions;

    private Element element;

    private Boolean regenerateOrderIndex;

    @SuppressWarnings("unused")
    private Long actionId;

    @SuppressWarnings("unused")
    private Long typeId;

    private Long fromReferenceInstructionId;

    private Boolean isOverwrite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public String getExpectedDescription() {
        return expectedDescription;
    }

    public void setExpectedDescription(String expectedDescription) {
        this.expectedDescription = expectedDescription;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getRefTestCaseId() {
        return refTestCaseId;
    }

    public void setRefTestCaseId(Long refTestCaseId) {
        this.refTestCaseId = refTestCaseId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(Boolean isDriver) {
        this.isDriver = isDriver;
    }

    public JsonNode getParameter() {
        return parameter;
    }

    public void setParameter(JsonNode parameter) {
        this.parameter = parameter;
    }

    public Long getCopyFromId() {
        return copyFromId;
    }

    public void setCopyFromId(Long copyFromId) {
        this.copyFromId = copyFromId;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getResourceMd5() {
        return resourceMd5;
    }

    public void setResourceMd5(String resourceMd5) {
        this.resourceMd5 = resourceMd5;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public Long getRefTestCaseOverwriteId() {
        return refTestCaseOverwriteId;
    }

    public void setRefTestCaseOverwriteId(Long refTestCaseOverwriteId) {
        this.refTestCaseOverwriteId = refTestCaseOverwriteId;
    }

    public Long getTestCaseShareFolderId() {
        return testCaseShareFolderId;
    }

    public void setTestCaseShareFolderId(Long testCaseShareFolderId) {
        this.testCaseShareFolderId = testCaseShareFolderId;
    }

    public String getDriverAlias() {
        return driverAlias;
    }

    public void setDriverAlias(String driverAlias) {
        this.driverAlias = driverAlias;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getRefTestCaseOverwriteName() {
        return refTestCaseOverwriteName;
    }

    public void setRefTestCaseOverwriteName(String refTestCaseOverwriteName) {
        this.refTestCaseOverwriteName = refTestCaseOverwriteName;
    }

    public String getLogicalOrderIndex() {
        return logicalOrderIndex;
    }

    public void setLogicalOrderIndex(String logicalOrderIndex) {
        this.logicalOrderIndex = logicalOrderIndex;
    }

    public List<InstructionOptionEntry> getInstructionOptions() {
        return instructionOptions;
    }

    public void setInstructionOptions(List<InstructionOptionEntry> instructionOptions) {
        this.instructionOptions = instructionOptions;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Boolean getRegenerateOrderIndex() {
        return regenerateOrderIndex;
    }

    public void setRegenerateOrderIndex(Boolean regenerateOrderIndex) {
        this.regenerateOrderIndex = regenerateOrderIndex;
    }

    public Long getActionId() {
        if (this.action != null && RuntimeVariables.getInstructionActionToIdMap().containsKey(this.action)) {
            return RuntimeVariables.getInstructionActionToIdMap().get(this.action);
        }
        else {
            return null;
        }
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Long getTypeId() {
        if (this.type != null && RuntimeVariables.getInstructionTypeToIdMap().containsKey(this.type)) {
            return RuntimeVariables.getInstructionTypeToIdMap().get(this.type);
        }
        else {
            return null;
        }
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getFromReferenceInstructionId() {
        return fromReferenceInstructionId;
    }

    public void setFromReferenceInstructionId(Long fromReferenceInstructionId) {
        this.fromReferenceInstructionId = fromReferenceInstructionId;
    }

    public Boolean getIsOverwrite() {
        return isOverwrite;
    }

    public void setIsOverwrite(Boolean isOverwrite) {
        this.isOverwrite = isOverwrite;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        Instruction other = (Instruction) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getInput() == null ? other.getInput() == null : this.getInput().equals(other.getInput()))
                && (this.getElementId() == null ? other.getElementId() == null : this.getElementId().equals(other.getElementId()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
                && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
                && (this.getApplicationId() == null ? other.getApplicationId() == null : this.getApplicationId().equals(other.getApplicationId()))
                && (this.getSectionId() == null ? other.getSectionId() == null : this.getSectionId().equals(other.getSectionId()))
                && (this.getOrderIndex() == null ? other.getOrderIndex() == null : this.getOrderIndex().equals(other.getOrderIndex()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
                && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getStepDescription() == null ? other.getStepDescription() == null : this.getStepDescription().equals(other.getStepDescription()))
                && (this.getExpectedDescription() == null ? other.getExpectedDescription() == null : this.getExpectedDescription().equals(other.getExpectedDescription()))
                && (this.getTestCaseId() == null ? other.getTestCaseId() == null : this.getTestCaseId().equals(other.getTestCaseId()))
                && (this.getRefTestCaseId() == null ? other.getRefTestCaseId() == null : this.getRefTestCaseId().equals(other.getRefTestCaseId()))
                && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
                && (this.getIsDriver() == null ? other.getIsDriver() == null : this.getIsDriver().equals(other.getIsDriver()))
                && (this.getParameter() == null ? other.getParameter() == null : this.getParameter().equals(other.getParameter()))
                && (this.getCopyFromId() == null ? other.getCopyFromId() == null : this.getCopyFromId().equals(other.getCopyFromId()))
                && (this.getExpectedValue() == null ? other.getExpectedValue() == null : this.getExpectedValue().equals(other.getExpectedValue()))
                && (this.getResourceMd5() == null ? other.getResourceMd5() == null : this.getResourceMd5().equals(other.getResourceMd5()))
                && (this.getElementType() == null ? other.getElementType() == null : this.getElementType().equals(other.getElementType()))
                && (this.getDriverType() == null ? other.getDriverType() == null : this.getDriverType().equals(other.getDriverType()))
                && (this.getRefTestCaseOverwriteId() == null ? other.getRefTestCaseOverwriteId() == null
                        : this.getRefTestCaseOverwriteId().equals(other.getRefTestCaseOverwriteId()))
                && (this.getTestCaseShareFolderId() == null ? other.getTestCaseShareFolderId() == null : this.getTestCaseShareFolderId().equals(other.getTestCaseShareFolderId()))
                && (this.getDriverAlias() == null ? other.getDriverAlias() == null : this.getDriverAlias().equals(other.getDriverAlias()))
                && (this.getTarget() == null ? other.getTarget() == null : this.getTarget().equals(other.getTarget()))
                && (this.getRefTestCaseOverwriteName() == null ? other.getRefTestCaseOverwriteName() == null
                        : this.getRefTestCaseOverwriteName().equals(other.getRefTestCaseOverwriteName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getInput() == null) ? 0 : getInput().hashCode());
        result = prime * result + ((getElementId() == null) ? 0 : getElementId().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getApplicationId() == null) ? 0 : getApplicationId().hashCode());
        result = prime * result + ((getSectionId() == null) ? 0 : getSectionId().hashCode());
        result = prime * result + ((getOrderIndex() == null) ? 0 : getOrderIndex().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStepDescription() == null) ? 0 : getStepDescription().hashCode());
        result = prime * result + ((getExpectedDescription() == null) ? 0 : getExpectedDescription().hashCode());
        result = prime * result + ((getTestCaseId() == null) ? 0 : getTestCaseId().hashCode());
        result = prime * result + ((getRefTestCaseId() == null) ? 0 : getRefTestCaseId().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getIsDriver() == null) ? 0 : getIsDriver().hashCode());
        result = prime * result + ((getParameter() == null) ? 0 : getParameter().hashCode());
        result = prime * result + ((getCopyFromId() == null) ? 0 : getCopyFromId().hashCode());
        result = prime * result + ((getExpectedValue() == null) ? 0 : getExpectedValue().hashCode());
        result = prime * result + ((getResourceMd5() == null) ? 0 : getResourceMd5().hashCode());
        result = prime * result + ((getElementType() == null) ? 0 : getElementType().hashCode());
        result = prime * result + ((getDriverType() == null) ? 0 : getDriverType().hashCode());
        result = prime * result + ((getRefTestCaseOverwriteId() == null) ? 0 : getRefTestCaseOverwriteId().hashCode());
        result = prime * result + ((getTestCaseShareFolderId() == null) ? 0 : getTestCaseShareFolderId().hashCode());
        result = prime * result + ((getDriverAlias() == null) ? 0 : getDriverAlias().hashCode());
        result = prime * result + ((getTarget() == null) ? 0 : getTarget().hashCode());
        result = prime * result + ((getRefTestCaseOverwriteName() == null) ? 0 : getRefTestCaseOverwriteName().hashCode());
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
        sb.append(", comment=").append(comment);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", input=").append(input);
        sb.append(", elementId=").append(elementId);
        sb.append(", deleted=").append(deleted);
        sb.append(", projectId=").append(projectId);
        sb.append(", applicationId=").append(applicationId);
        sb.append(", sectionId=").append(sectionId);
        sb.append(", orderIndex=").append(orderIndex);
        sb.append(", log=").append(log);
        sb.append(", data=").append(data);
        sb.append(", color=").append(color);
        sb.append(", type=").append(type);
        sb.append(", stepDescription=").append(stepDescription);
        sb.append(", expectedDescription=").append(expectedDescription);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", refTestCaseId=").append(refTestCaseId);
        sb.append(", action=").append(action);
        sb.append(", isDriver=").append(isDriver);
        sb.append(", parameter=").append(parameter);
        sb.append(", copyFromId=").append(copyFromId);
        sb.append(", expectedValue=").append(expectedValue);
        sb.append(", resourceMd5=").append(resourceMd5);
        sb.append(", elementType=").append(elementType);
        sb.append(", driverType=").append(driverType);
        sb.append(", refTestCaseOverwriteId=").append(refTestCaseOverwriteId);
        sb.append(", testCaseShareFolderId=").append(testCaseShareFolderId);
        sb.append(", driverAlias=").append(driverAlias);
        sb.append(", target=").append(target);
        sb.append(", refTestCaseOverwriteName=").append(refTestCaseOverwriteName);
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
        id("id", "id", "BIGINT", false), comment("comment", "comment", "VARCHAR", false), updatedAt("updated_at", "updatedAt", "TIMESTAMP", false), createdAt("created_at",
                "createdAt", "TIMESTAMP",
                false), input("input", "input", "VARCHAR", false), elementId("element_id", "elementId", "BIGINT", false), deleted("is_deleted", "deleted", "BIT", false), projectId(
                        "project_id", "projectId", "BIGINT",
                        false), applicationId("application_id", "applicationId", "BIGINT", false), sectionId("section_id", "sectionId", "BIGINT", false), orderIndex("order_index",
                                "orderIndex", "BIGINT", false), log("log", "log", "VARCHAR", false), data("data", "data", "OTHER", false), color("color_id", "color", "OTHER",
                                        false), instructionType("instruction_type_id", "instructionType", "OTHER", false), stepDescription("step_description", "stepDescription",
                                                "VARCHAR", false), expectedDescription("expected_description", "expectedDescription", "VARCHAR", false), testCaseId("test_case_id",
                                                        "testCaseId", "BIGINT",
                                                        false), refTestCaseId("ref_test_case_id", "refTestCaseId", "BIGINT", false), instructionAction("instruction_action_id",
                                                                "instructionAction", "OTHER", false), isDriver("is_driver", "isDriver", "BIT", false), parameter("parameter",
                                                                        "parameter", "OTHER", false), copyFromId("copy_from_id", "copyFromId", "BIGINT", false), expectedValue(
                                                                                "expected_value", "expectedValue", "VARCHAR",
                                                                                false), resourceMd5("resource_md5", "resourceMd5", "CHAR", false), elementType("element_type_id",
                                                                                        "elementType", "OTHER",
                                                                                        false), driverType("driver_type_id", "driverType", "OTHER", false), refTestCaseOverwriteId(
                                                                                                "ref_test_case_overwrite_id", "refTestCaseOverwriteId", "BIGINT",
                                                                                                false), testCaseShareFolderId("test_case_share_folder_id", "testCaseShareFolderId",
                                                                                                        "BIGINT", false), driverAlias("driver_alias", "driverAlias", "VARCHAR",
                                                                                                                false), target("target", "target", "VARCHAR",
                                                                                                                        false), refTestCaseOverwriteName(
                                                                                                                                "ref_test_case_overwrite_name",
                                                                                                                                "refTestCaseOverwriteName", "VARCHAR", false);

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