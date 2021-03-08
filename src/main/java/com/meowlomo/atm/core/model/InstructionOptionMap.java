package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class InstructionOptionMap implements Serializable {
    private String instructionOption;

    private Set<Long> instructionActionIds;

    private Set<Long> elementTypeIds;

    private static final long serialVersionUID = 1L;

    public String getInstructionOption() {
        return instructionOption;
    }

    public void setInstructionOption(String instructionOption) {
        this.instructionOption = instructionOption;
    }

    public Set<Long> getInstructionActionIds() {
        return instructionActionIds;
    }

    public void setInstructionActionIds(Set<Long> instructionActionIds) {
        this.instructionActionIds = instructionActionIds;
    }

    public Set<Long> getElementTypeIds() {
        return elementTypeIds;
    }

    public void setElementTypeIds(Set<Long> elementTypeIds) {
        this.elementTypeIds = elementTypeIds;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        InstructionOptionMap other = (InstructionOptionMap) that;
        return (this.getInstructionOption() == null ? other.getInstructionOption() == null : this.getInstructionOption().equals(other.getInstructionOption()))
                && (this.getInstructionActionIds() == null ? other.getInstructionActionIds() == null : this.getInstructionActionIds().equals(other.getInstructionActionIds()))
                && (this.getElementTypeIds() == null ? other.getElementTypeIds() == null : this.getElementTypeIds().equals(other.getElementTypeIds()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInstructionOption() == null) ? 0 : getInstructionOption().hashCode());
        result = prime * result + ((getInstructionActionIds() == null) ? 0 : getInstructionActionIds().hashCode());
        result = prime * result + ((getElementTypeIds() == null) ? 0 : getElementTypeIds().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", instructionOption=").append(instructionOption);
        sb.append(", instructionActionIds=").append(instructionActionIds);
        sb.append(", elementTypeIds=").append(elementTypeIds);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        instructionOption("instruction_option_id", "instructionOption", "OTHER", false), instructionActionIds("instruction_action_ids", "instructionActionIds", "ARRAY",
                false), elementTypeIds("element_type_ids", "elementTypeIds", "ARRAY", false);

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