package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DriverPropertyPredefinedValue implements Serializable {
    private Long id;

    private String value;

    private Long driverPropertyId;

    private String comment;

    private Boolean prefered;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getDriverPropertyId() {
        return driverPropertyId;
    }

    public void setDriverPropertyId(Long driverPropertyId) {
        this.driverPropertyId = driverPropertyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getPrefered() {
        return prefered;
    }

    public void setPrefered(Boolean prefered) {
        this.prefered = prefered;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        DriverPropertyPredefinedValue other = (DriverPropertyPredefinedValue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
                && (this.getDriverPropertyId() == null ? other.getDriverPropertyId() == null : this.getDriverPropertyId().equals(other.getDriverPropertyId()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getPrefered() == null ? other.getPrefered() == null : this.getPrefered().equals(other.getPrefered()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getDriverPropertyId() == null) ? 0 : getDriverPropertyId().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getPrefered() == null) ? 0 : getPrefered().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", value=").append(value);
        sb.append(", driverPropertyId=").append(driverPropertyId);
        sb.append(", comment=").append(comment);
        sb.append(", prefered=").append(prefered);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), value("value", "value", "VARCHAR", false), driverPropertyId("driver_property_id", "driverPropertyId", "BIGINT", false), comment("comment",
                "comment", "VARCHAR", false), prefered("is_prefered", "prefered", "BIT", false);

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