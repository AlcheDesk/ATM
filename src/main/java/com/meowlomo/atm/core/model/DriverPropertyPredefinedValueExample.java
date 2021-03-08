package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DriverPropertyPredefinedValueExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverPropertyPredefinedValueExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public DriverPropertyPredefinedValueExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public DriverPropertyPredefinedValueExample orderBy(String... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public static Criteria newAndCreateCriteria() {
        DriverPropertyPredefinedValueExample example = new DriverPropertyPredefinedValueExample();
        return example.createCriteria();
    }

    public DriverPropertyPredefinedValueExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public DriverPropertyPredefinedValueExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        }
        else {
            otherwise.example(this);
        }
        return this;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) { throw new RuntimeException("Value for condition cannot be null"); }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(String value) {
            addCriterion("value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("value like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("value not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdIsNull() {
            addCriterion("driver_property_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdIsNotNull() {
            addCriterion("driver_property_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdEqualTo(Long value) {
            addCriterion("driver_property_id =", value, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("driver_property_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdNotEqualTo(Long value) {
            addCriterion("driver_property_id <>", value, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdNotEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("driver_property_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdGreaterThan(Long value) {
            addCriterion("driver_property_id >", value, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdGreaterThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("driver_property_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_property_id >=", value, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdGreaterThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("driver_property_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdLessThan(Long value) {
            addCriterion("driver_property_id <", value, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdLessThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("driver_property_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdLessThanOrEqualTo(Long value) {
            addCriterion("driver_property_id <=", value, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdLessThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("driver_property_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdIn(List<Long> values) {
            addCriterion("driver_property_id in", values, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdNotIn(List<Long> values) {
            addCriterion("driver_property_id not in", values, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdBetween(Long value1, Long value2) {
            addCriterion("driver_property_id between", value1, value2, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andDriverPropertyIdNotBetween(Long value1, Long value2) {
            addCriterion("driver_property_id not between", value1, value2, "driverPropertyId");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("comment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("comment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("comment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("comment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("comment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("comment <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andPreferedIsNull() {
            addCriterion("is_prefered is null");
            return (Criteria) this;
        }

        public Criteria andPreferedIsNotNull() {
            addCriterion("is_prefered is not null");
            return (Criteria) this;
        }

        public Criteria andPreferedEqualTo(Boolean value) {
            addCriterion("is_prefered =", value, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("is_prefered = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPreferedNotEqualTo(Boolean value) {
            addCriterion("is_prefered <>", value, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedNotEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("is_prefered <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPreferedGreaterThan(Boolean value) {
            addCriterion("is_prefered >", value, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedGreaterThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("is_prefered > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPreferedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_prefered >=", value, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedGreaterThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("is_prefered >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPreferedLessThan(Boolean value) {
            addCriterion("is_prefered <", value, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedLessThanColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("is_prefered < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPreferedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_prefered <=", value, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedLessThanOrEqualToColumn(DriverPropertyPredefinedValue.Column column) {
            addCriterion(new StringBuilder("is_prefered <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPreferedIn(List<Boolean> values) {
            addCriterion("is_prefered in", values, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedNotIn(List<Boolean> values) {
            addCriterion("is_prefered not in", values, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_prefered between", value1, value2, "prefered");
            return (Criteria) this;
        }

        public Criteria andPreferedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_prefered not between", value1, value2, "prefered");
            return (Criteria) this;
        }

        public Criteria andValueLikeInsensitive(String value) {
            addCriterion("upper(value) like", value.toUpperCase(), "value");
            return (Criteria) this;
        }

        public Criteria andCommentLikeInsensitive(String value) {
            addCriterion("upper(comment) like", value.toUpperCase(), "comment");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private DriverPropertyPredefinedValueExample example;

        protected Criteria(DriverPropertyPredefinedValueExample example) {
            super();
            this.example = example;
        }

        public DriverPropertyPredefinedValueExample example() {
            return this.example;
        }

        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            }
            else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            Criteria add(Criteria add);
        }
    }

    public static class Criterion implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            }
            else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public interface ICriteriaWhen {
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        void example(com.meowlomo.atm.core.model.DriverPropertyPredefinedValueExample example);
    }
}