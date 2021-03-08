package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;

public class InstructionOverwrite implements Serializable {

    private Long id;

    private Long testCaseOverwriteId;

    private Long testCaseId;

    private Long instructionId;

    private Long elementId;

    private String overwriteFields;

    private JsonNode data;

    private String type;

    private Long refTestCaseOverwriteId;

    private Long copyFromId;

    private String log;

    private static final long serialVersionUID = 1L;

    private Instruction instruction;

    private Element element;

    private Long fromReferenceInstructionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestCaseOverwriteId() {
        return testCaseOverwriteId;
    }

    public void setTestCaseOverwriteId(Long testCaseOverwriteId) {
        this.testCaseOverwriteId = testCaseOverwriteId;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Long instructionId) {
        this.instructionId = instructionId;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public String getOverwriteFields() {
        return overwriteFields;
    }

    public void setOverwriteFields(String overwriteFields) {
        this.overwriteFields = overwriteFields;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRefTestCaseOverwriteId() {
        return refTestCaseOverwriteId;
    }

    public void setRefTestCaseOverwriteId(Long refTestCaseOverwriteId) {
        this.refTestCaseOverwriteId = refTestCaseOverwriteId;
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

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Long getFromReferenceInstructionId() {
        return fromReferenceInstructionId;
    }

    public void setFromReferenceInstructionId(Long fromReferenceInstructionId) {
        this.fromReferenceInstructionId = fromReferenceInstructionId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        InstructionOverwrite other = (InstructionOverwrite) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTestCaseOverwriteId() == null ? other.getTestCaseOverwriteId() == null : this.getTestCaseOverwriteId().equals(other.getTestCaseOverwriteId()))
                && (this.getTestCaseId() == null ? other.getTestCaseId() == null : this.getTestCaseId().equals(other.getTestCaseId()))
                && (this.getInstructionId() == null ? other.getInstructionId() == null : this.getInstructionId().equals(other.getInstructionId()))
                && (this.getElementId() == null ? other.getElementId() == null : this.getElementId().equals(other.getElementId()))
                && (this.getOverwriteFields() == null ? other.getOverwriteFields() == null : this.getOverwriteFields().equals(other.getOverwriteFields()))
                && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getRefTestCaseOverwriteId() == null ? other.getRefTestCaseOverwriteId() == null
                        : this.getRefTestCaseOverwriteId().equals(other.getRefTestCaseOverwriteId()))
                && (this.getCopyFromId() == null ? other.getCopyFromId() == null : this.getCopyFromId().equals(other.getCopyFromId()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTestCaseOverwriteId() == null) ? 0 : getTestCaseOverwriteId().hashCode());
        result = prime * result + ((getTestCaseId() == null) ? 0 : getTestCaseId().hashCode());
        result = prime * result + ((getInstructionId() == null) ? 0 : getInstructionId().hashCode());
        result = prime * result + ((getElementId() == null) ? 0 : getElementId().hashCode());
        result = prime * result + ((getOverwriteFields() == null) ? 0 : getOverwriteFields().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getRefTestCaseOverwriteId() == null) ? 0 : getRefTestCaseOverwriteId().hashCode());
        result = prime * result + ((getCopyFromId() == null) ? 0 : getCopyFromId().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", testCaseOverwriteId=").append(testCaseOverwriteId);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", instructionId=").append(instructionId);
        sb.append(", elementId=").append(elementId);
        sb.append(", overwriteFields=").append(overwriteFields);
        sb.append(", data=").append(data);
        sb.append(", type=").append(type);
        sb.append(", refTestCaseOverwriteId=").append(refTestCaseOverwriteId);
        sb.append(", copyFromId=").append(copyFromId);
        sb.append(", log=").append(log);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), testCaseOverwriteId("test_case_overwrite_id", "testCaseOverwriteId", "BIGINT", false), testCaseId("test_case_id", "testCaseId", "BIGINT",
                false), instructionId("instruction_id", "instructionId", "BIGINT", false), elementId("element_id", "elementId", "BIGINT",
                        false), overwriteFields("overwrite_fields", "overwriteFields", "VARCHAR", false), data("data", "data", "OTHER",
                                false), instructionType("instruction_type_id", "instructionType", "OTHER", false), refTestCaseOverwriteId("ref_test_case_overwrite_id",
                                        "refTestCaseOverwriteId", "BIGINT", false), copyFromId("copy_from_id", "copyFromId", "BIGINT", false), log("log", "log", "VARCHAR", false);

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