package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCaseShareFolderTestCaseLink implements Serializable {
    private Long id;

    private Long testCaseId;

    private Long testCaseShareFolderId;

    private static final long serialVersionUID = 1L;

    private TestCase testCase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getTestCaseShareFolderId() {
        return testCaseShareFolderId;
    }

    public void setTestCaseShareFolderId(Long testCaseShareFolderId) {
        this.testCaseShareFolderId = testCaseShareFolderId;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        TestCaseShareFolderTestCaseLink other = (TestCaseShareFolderTestCaseLink) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTestCaseId() == null ? other.getTestCaseId() == null : this.getTestCaseId().equals(other.getTestCaseId()))
                && (this.getTestCaseShareFolderId() == null ? other.getTestCaseShareFolderId() == null : this.getTestCaseShareFolderId().equals(other.getTestCaseShareFolderId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTestCaseId() == null) ? 0 : getTestCaseId().hashCode());
        result = prime * result + ((getTestCaseShareFolderId() == null) ? 0 : getTestCaseShareFolderId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", testCaseShareFolderId=").append(testCaseShareFolderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), testCaseId("test_case_id", "testCaseId", "BIGINT", false), testCaseShareFolderId("test_case_share_folder_id", "testCaseShareFolderId",
                "BIGINT", false);

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