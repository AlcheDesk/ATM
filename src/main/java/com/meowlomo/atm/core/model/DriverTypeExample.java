package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DriverTypeExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverTypeExample() {
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

    public DriverTypeExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public DriverTypeExample orderBy(String... orderByClauses) {
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
        DriverTypeExample example = new DriverTypeExample();
        return example.createCriteria();
    }

    public DriverTypeExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public DriverTypeExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(DriverType.Column column) {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableIsNull() {
            addCriterion("is_multiselectable is null");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableIsNotNull() {
            addCriterion("is_multiselectable is not null");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableEqualTo(Boolean value) {
            addCriterion("is_multiselectable =", value, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_multiselectable = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableNotEqualTo(Boolean value) {
            addCriterion("is_multiselectable <>", value, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableNotEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_multiselectable <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableGreaterThan(Boolean value) {
            addCriterion("is_multiselectable >", value, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableGreaterThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_multiselectable > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_multiselectable >=", value, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableGreaterThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_multiselectable >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableLessThan(Boolean value) {
            addCriterion("is_multiselectable <", value, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableLessThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_multiselectable < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableLessThanOrEqualTo(Boolean value) {
            addCriterion("is_multiselectable <=", value, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableLessThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_multiselectable <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableIn(List<Boolean> values) {
            addCriterion("is_multiselectable in", values, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableNotIn(List<Boolean> values) {
            addCriterion("is_multiselectable not in", values, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableBetween(Boolean value1, Boolean value2) {
            addCriterion("is_multiselectable between", value1, value2, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andIsMultiselectableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_multiselectable not between", value1, value2, "isMultiselectable");
            return (Criteria) this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("is_active =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_active = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_active <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_active > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_active >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_active < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_active <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("is_active in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("is_active not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andPredefinedIsNull() {
            addCriterion("is_predefined is null");
            return (Criteria) this;
        }

        public Criteria andPredefinedIsNotNull() {
            addCriterion("is_predefined is not null");
            return (Criteria) this;
        }

        public Criteria andPredefinedEqualTo(Boolean value) {
            addCriterion("is_predefined =", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_predefined = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedNotEqualTo(Boolean value) {
            addCriterion("is_predefined <>", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedNotEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_predefined <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThan(Boolean value) {
            addCriterion("is_predefined >", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_predefined > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined >=", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_predefined >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThan(Boolean value) {
            addCriterion("is_predefined <", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_predefined < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined <=", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanOrEqualToColumn(DriverType.Column column) {
            addCriterion(new StringBuilder("is_predefined <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedIn(List<Boolean> values) {
            addCriterion("is_predefined in", values, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedNotIn(List<Boolean> values) {
            addCriterion("is_predefined not in", values, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_predefined between", value1, value2, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_predefined not between", value1, value2, "predefined");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private DriverTypeExample example;

        protected Criteria(DriverTypeExample example) {
            super();
            this.example = example;
        }

        public DriverTypeExample example() {
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
        void example(com.meowlomo.atm.core.model.DriverTypeExample example);
    }
}