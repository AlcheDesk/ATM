package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DriverPackDriverAliasMapExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverPackDriverAliasMapExample() {
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

    public DriverPackDriverAliasMapExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public DriverPackDriverAliasMapExample orderBy(String... orderByClauses) {
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
        DriverPackDriverAliasMapExample example = new DriverPackDriverAliasMapExample();
        return example.createCriteria();
    }

    public DriverPackDriverAliasMapExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public DriverPackDriverAliasMapExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> driverAliasesCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            driverAliasesCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getDriverAliasesCriteria() {
            return driverAliasesCriteria;
        }

        protected void addDriverAliasesCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            driverAliasesCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.StringSetTypeHandler"));
            allCriteria = null;
        }

        protected void addDriverAliasesCriterion(String condition, Set<String> value1, Set<String> value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            driverAliasesCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.StringSetTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || driverAliasesCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(driverAliasesCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) { throw new RuntimeException("Value for condition cannot be null"); }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
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

        public Criteria andIdEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(DriverPackDriverAliasMap.Column column) {
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

        public Criteria andDriverPackIdIsNull() {
            addCriterion("driver_pack_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdIsNotNull() {
            addCriterion("driver_pack_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdEqualTo(Long value) {
            addCriterion("driver_pack_id =", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_pack_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotEqualTo(Long value) {
            addCriterion("driver_pack_id <>", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_pack_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThan(Long value) {
            addCriterion("driver_pack_id >", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_pack_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_pack_id >=", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanOrEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_pack_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThan(Long value) {
            addCriterion("driver_pack_id <", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_pack_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanOrEqualTo(Long value) {
            addCriterion("driver_pack_id <=", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanOrEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_pack_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdIn(List<Long> values) {
            addCriterion("driver_pack_id in", values, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotIn(List<Long> values) {
            addCriterion("driver_pack_id not in", values, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdBetween(Long value1, Long value2) {
            addCriterion("driver_pack_id between", value1, value2, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotBetween(Long value1, Long value2) {
            addCriterion("driver_pack_id not between", value1, value2, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesIsNull() {
            addCriterion("driver_aliases is null");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesIsNotNull() {
            addCriterion("driver_aliases is not null");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesEqualTo(Set<String> value) {
            addDriverAliasesCriterion("driver_aliases =", value, "driverAliases");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_aliases = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasesNotEqualTo(Set<String> value) {
            addDriverAliasesCriterion("driver_aliases <>", value, "driverAliases");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesNotEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_aliases <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasesGreaterThan(Set<String> value) {
            addDriverAliasesCriterion("driver_aliases >", value, "driverAliases");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesGreaterThanColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_aliases > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasesGreaterThanOrEqualTo(Set<String> value) {
            addDriverAliasesCriterion("driver_aliases >=", value, "driverAliases");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesGreaterThanOrEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_aliases >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasesLessThan(Set<String> value) {
            addDriverAliasesCriterion("driver_aliases <", value, "driverAliases");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesLessThanColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_aliases < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasesLessThanOrEqualTo(Set<String> value) {
            addDriverAliasesCriterion("driver_aliases <=", value, "driverAliases");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesLessThanOrEqualToColumn(DriverPackDriverAliasMap.Column column) {
            addCriterion(new StringBuilder("driver_aliases <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasesBetween(Set<String> value1, Set<String> value2) {
            addDriverAliasesCriterion("driver_aliases between", value1, value2, "driverAliases");
            return (Criteria) this;
        }

        public Criteria andDriverAliasesNotBetween(Set<String> value1, Set<String> value2) {
            addDriverAliasesCriterion("driver_aliases not between", value1, value2, "driverAliases");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private DriverPackDriverAliasMapExample example;

        protected Criteria(DriverPackDriverAliasMapExample example) {
            super();
            this.example = example;
        }

        public DriverPackDriverAliasMapExample example() {
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
        void example(com.meowlomo.atm.core.model.DriverPackDriverAliasMapExample example);
    }
}