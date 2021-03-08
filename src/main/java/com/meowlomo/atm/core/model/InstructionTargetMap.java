package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class InstructionTargetMap implements Serializable {
    private Long id;

    private Long applicationId;

    private String applicationName;

    private Long sectionId;

    private String sectionName;

    private Long elementId;

    private String elementName;

    private Long testCaseShareFolderId;

    private String testCaseShareFolderName;

    private Long refTestCaseId;

    private String refTestCaseName;

    private Long instructionId;

    private Long refTestCaseOverwriteId;

    private String refTestCaseOverwriteName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Long getTestCaseShareFolderId() {
        return testCaseShareFolderId;
    }

    public void setTestCaseShareFolderId(Long testCaseShareFolderId) {
        this.testCaseShareFolderId = testCaseShareFolderId;
    }

    public String getTestCaseShareFolderName() {
        return testCaseShareFolderName;
    }

    public void setTestCaseShareFolderName(String testCaseShareFolderName) {
        this.testCaseShareFolderName = testCaseShareFolderName;
    }

    public Long getRefTestCaseId() {
        return refTestCaseId;
    }

    public void setRefTestCaseId(Long refTestCaseId) {
        this.refTestCaseId = refTestCaseId;
    }

    public String getRefTestCaseName() {
        return refTestCaseName;
    }

    public void setRefTestCaseName(String refTestCaseName) {
        this.refTestCaseName = refTestCaseName;
    }

    public Long getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Long instructionId) {
        this.instructionId = instructionId;
    }

    public Long getRefTestCaseOverwriteId() {
        return refTestCaseOverwriteId;
    }

    public void setRefTestCaseOverwriteId(Long refTestCaseOverwriteId) {
        this.refTestCaseOverwriteId = refTestCaseOverwriteId;
    }

    public String getRefTestCaseOverwriteName() {
        return refTestCaseOverwriteName;
    }

    public void setRefTestCaseOverwriteName(String refTestCaseOverwriteName) {
        this.refTestCaseOverwriteName = refTestCaseOverwriteName;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        InstructionTargetMap other = (InstructionTargetMap) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getApplicationId() == null ? other.getApplicationId() == null : this.getApplicationId().equals(other.getApplicationId()))
                && (this.getApplicationName() == null ? other.getApplicationName() == null : this.getApplicationName().equals(other.getApplicationName()))
                && (this.getSectionId() == null ? other.getSectionId() == null : this.getSectionId().equals(other.getSectionId()))
                && (this.getSectionName() == null ? other.getSectionName() == null : this.getSectionName().equals(other.getSectionName()))
                && (this.getElementId() == null ? other.getElementId() == null : this.getElementId().equals(other.getElementId()))
                && (this.getElementName() == null ? other.getElementName() == null : this.getElementName().equals(other.getElementName()))
                && (this.getTestCaseShareFolderId() == null ? other.getTestCaseShareFolderId() == null : this.getTestCaseShareFolderId().equals(other.getTestCaseShareFolderId()))
                && (this.getTestCaseShareFolderName() == null ? other.getTestCaseShareFolderName() == null
                        : this.getTestCaseShareFolderName().equals(other.getTestCaseShareFolderName()))
                && (this.getRefTestCaseId() == null ? other.getRefTestCaseId() == null : this.getRefTestCaseId().equals(other.getRefTestCaseId()))
                && (this.getRefTestCaseName() == null ? other.getRefTestCaseName() == null : this.getRefTestCaseName().equals(other.getRefTestCaseName()))
                && (this.getInstructionId() == null ? other.getInstructionId() == null : this.getInstructionId().equals(other.getInstructionId()))
                && (this.getRefTestCaseOverwriteId() == null ? other.getRefTestCaseOverwriteId() == null
                        : this.getRefTestCaseOverwriteId().equals(other.getRefTestCaseOverwriteId()))
                && (this.getRefTestCaseOverwriteName() == null ? other.getRefTestCaseOverwriteName() == null
                        : this.getRefTestCaseOverwriteName().equals(other.getRefTestCaseOverwriteName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getApplicationId() == null) ? 0 : getApplicationId().hashCode());
        result = prime * result + ((getApplicationName() == null) ? 0 : getApplicationName().hashCode());
        result = prime * result + ((getSectionId() == null) ? 0 : getSectionId().hashCode());
        result = prime * result + ((getSectionName() == null) ? 0 : getSectionName().hashCode());
        result = prime * result + ((getElementId() == null) ? 0 : getElementId().hashCode());
        result = prime * result + ((getElementName() == null) ? 0 : getElementName().hashCode());
        result = prime * result + ((getTestCaseShareFolderId() == null) ? 0 : getTestCaseShareFolderId().hashCode());
        result = prime * result + ((getTestCaseShareFolderName() == null) ? 0 : getTestCaseShareFolderName().hashCode());
        result = prime * result + ((getRefTestCaseId() == null) ? 0 : getRefTestCaseId().hashCode());
        result = prime * result + ((getRefTestCaseName() == null) ? 0 : getRefTestCaseName().hashCode());
        result = prime * result + ((getInstructionId() == null) ? 0 : getInstructionId().hashCode());
        result = prime * result + ((getRefTestCaseOverwriteId() == null) ? 0 : getRefTestCaseOverwriteId().hashCode());
        result = prime * result + ((getRefTestCaseOverwriteName() == null) ? 0 : getRefTestCaseOverwriteName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", applicationId=").append(applicationId);
        sb.append(", applicationName=").append(applicationName);
        sb.append(", sectionId=").append(sectionId);
        sb.append(", sectionName=").append(sectionName);
        sb.append(", elementId=").append(elementId);
        sb.append(", elementName=").append(elementName);
        sb.append(", testCaseShareFolderId=").append(testCaseShareFolderId);
        sb.append(", testCaseShareFolderName=").append(testCaseShareFolderName);
        sb.append(", refTestCaseId=").append(refTestCaseId);
        sb.append(", refTestCaseName=").append(refTestCaseName);
        sb.append(", instructionId=").append(instructionId);
        sb.append(", refTestCaseOverwriteId=").append(refTestCaseOverwriteId);
        sb.append(", refTestCaseOverwriteName=").append(refTestCaseOverwriteName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), applicationId("application_id", "applicationId", "BIGINT", false), applicationName("application_name", "applicationName", "VARCHAR",
                false), sectionId("section_id", "sectionId", "BIGINT", false), sectionName("section_name", "sectionName", "VARCHAR", false), elementId("element_id", "elementId",
                        "BIGINT", false), elementName("element_name", "elementName", "VARCHAR", false), testCaseShareFolderId("test_case_share_folder_id", "testCaseShareFolderId",
                                "BIGINT", false), testCaseShareFolderName("test_case_share_folder_name", "testCaseShareFolderName", "VARCHAR", false), refTestCaseId(
                                        "ref_test_case_id", "refTestCaseId", "BIGINT", false), refTestCaseName("ref_test_case_name", "refTestCaseName", "VARCHAR",
                                                false), instructionId("instruction_id", "instructionId", "BIGINT", false), refTestCaseOverwriteId("ref_test_case_overwrite_id",
                                                        "refTestCaseOverwriteId", "BIGINT",
                                                        false), refTestCaseOverwriteName("ref_test_case_overwrite_name", "refTestCaseOverwriteName", "VARCHAR", false);

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