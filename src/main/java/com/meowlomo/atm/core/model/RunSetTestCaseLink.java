package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.constraints.NotNull;

public class RunSetTestCaseLink implements Serializable {
    private Long id;

    @NotNull(message = "runSetId不能为空")
    private Long runSetId;

    private Long testCaseId;

    private Long testCaseOverwriteId;

    private Long driverPackId;

    private Long refRunSetId;

    private Long systemRequirementPackId;

    private Boolean synchronize;

    private static final long serialVersionUID = 1L;

    private TestCase testCase;

    private TestCaseOverwrite testCaseOverwrite;

    private DriverPack driverPack;

    private RunSet runSet;

    private RunSet refRunSet;

    private SystemRequirementPack systemRequirementPack;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRunSetId() {
        return runSetId;
    }

    public void setRunSetId(Long runSetId) {
        this.runSetId = runSetId;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getTestCaseOverwriteId() {
        return testCaseOverwriteId;
    }

    public void setTestCaseOverwriteId(Long testCaseOverwriteId) {
        this.testCaseOverwriteId = testCaseOverwriteId;
    }

    public Long getDriverPackId() {
        return driverPackId;
    }

    public void setDriverPackId(Long driverPackId) {
        this.driverPackId = driverPackId;
    }

    public Long getRefRunSetId() {
        return refRunSetId;
    }

    public void setRefRunSetId(Long refRunSetId) {
        this.refRunSetId = refRunSetId;
    }

    public Long getSystemRequirementPackId() {
        return systemRequirementPackId;
    }

    public void setSystemRequirementPackId(Long systemRequirementPackId) {
        this.systemRequirementPackId = systemRequirementPackId;
    }

    public Boolean getSynchronize() {
        return synchronize;
    }

    public void setSynchronize(Boolean synchronize) {
        this.synchronize = synchronize;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestCaseOverwrite getTestCaseOverwrite() {
        return testCaseOverwrite;
    }

    public void setTestCaseOverwrite(TestCaseOverwrite testCaseOverwrite) {
        this.testCaseOverwrite = testCaseOverwrite;
    }

    public DriverPack getDriverPack() {
        return driverPack;
    }

    public void setDriverPack(DriverPack driverPack) {
        this.driverPack = driverPack;
    }

    public RunSet getRunSet() {
        return runSet;
    }

    public void setRunSet(RunSet runSet) {
        this.runSet = runSet;
    }

    public SystemRequirementPack getSystemRequirementPack() {
        return systemRequirementPack;
    }

    public void setSystemRequirementPack(SystemRequirementPack systemRequirementPack) {
        this.systemRequirementPack = systemRequirementPack;
    }

    public RunSet getRefRunSet() {
        return refRunSet;
    }

    public void setRefRunSet(RunSet refRunSet) {
        this.refRunSet = refRunSet;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        RunSetTestCaseLink other = (RunSetTestCaseLink) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRunSetId() == null ? other.getRunSetId() == null : this.getRunSetId().equals(other.getRunSetId()))
                && (this.getTestCaseId() == null ? other.getTestCaseId() == null : this.getTestCaseId().equals(other.getTestCaseId()))
                && (this.getTestCaseOverwriteId() == null ? other.getTestCaseOverwriteId() == null : this.getTestCaseOverwriteId().equals(other.getTestCaseOverwriteId()))
                && (this.getDriverPackId() == null ? other.getDriverPackId() == null : this.getDriverPackId().equals(other.getDriverPackId()))
                && (this.getRefRunSetId() == null ? other.getRefRunSetId() == null : this.getRefRunSetId().equals(other.getRefRunSetId()))
                && (this.getSystemRequirementPackId() == null ? other.getSystemRequirementPackId() == null
                        : this.getSystemRequirementPackId().equals(other.getSystemRequirementPackId()))
                && (this.getSynchronize() == null ? other.getSynchronize() == null : this.getSynchronize().equals(other.getSynchronize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRunSetId() == null) ? 0 : getRunSetId().hashCode());
        result = prime * result + ((getTestCaseId() == null) ? 0 : getTestCaseId().hashCode());
        result = prime * result + ((getTestCaseOverwriteId() == null) ? 0 : getTestCaseOverwriteId().hashCode());
        result = prime * result + ((getDriverPackId() == null) ? 0 : getDriverPackId().hashCode());
        result = prime * result + ((getRefRunSetId() == null) ? 0 : getRefRunSetId().hashCode());
        result = prime * result + ((getSystemRequirementPackId() == null) ? 0 : getSystemRequirementPackId().hashCode());
        result = prime * result + ((getSynchronize() == null) ? 0 : getSynchronize().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", runSetId=").append(runSetId);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", testCaseOverwriteId=").append(testCaseOverwriteId);
        sb.append(", driverPackId=").append(driverPackId);
        sb.append(", refRunSetId=").append(refRunSetId);
        sb.append(", systemRequirementPackId=").append(systemRequirementPackId);
        sb.append(", synchronize=").append(synchronize);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), runSetId("run_set_id", "runSetId", "BIGINT", false), testCaseId("test_case_id", "testCaseId", "BIGINT", false), testCaseOverwriteId(
                "test_case_overwrite_id", "testCaseOverwriteId", "BIGINT",
                false), driverPackId("driver_pack_id", "driverPackId", "BIGINT", false), refRunSetId("ref_run_set_id", "refRunSetId", "BIGINT", false), systemRequirementPackId(
                        "system_requirement_pack_id", "systemRequirementPackId", "BIGINT", false), synchronize("synchronize", "synchronize", "BIT", false);

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