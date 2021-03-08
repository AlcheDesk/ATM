package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RunSetAliasNameMapExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunSetAliasNameMapExample() {
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

    public RunSetAliasNameMapExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunSetAliasNameMapExample orderBy(String... orderByClauses) {
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
        RunSetAliasNameMapExample example = new RunSetAliasNameMapExample();
        return example.createCriteria();
    }

    public RunSetAliasNameMapExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunSetAliasNameMapExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> aliasNamesCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            aliasNamesCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getAliasNamesCriteria() {
            return aliasNamesCriteria;
        }

        protected void addAliasNamesCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            aliasNamesCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.StringSetTypeHandler"));
            allCriteria = null;
        }

        protected void addAliasNamesCriterion(String condition, Set<String> value1, Set<String> value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            aliasNamesCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.StringSetTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || aliasNamesCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(aliasNamesCriteria);
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

        public Criteria andIdEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(RunSetAliasNameMap.Column column) {
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

        public Criteria andRunSetIdIsNull() {
            addCriterion("run_set_id is null");
            return (Criteria) this;
        }

        public Criteria andRunSetIdIsNotNull() {
            addCriterion("run_set_id is not null");
            return (Criteria) this;
        }

        public Criteria andRunSetIdEqualTo(Long value) {
            addCriterion("run_set_id =", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("run_set_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualTo(Long value) {
            addCriterion("run_set_id <>", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("run_set_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThan(Long value) {
            addCriterion("run_set_id >", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("run_set_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_id >=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("run_set_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThan(Long value) {
            addCriterion("run_set_id <", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("run_set_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_id <=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("run_set_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdIn(List<Long> values) {
            addCriterion("run_set_id in", values, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotIn(List<Long> values) {
            addCriterion("run_set_id not in", values, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdBetween(Long value1, Long value2) {
            addCriterion("run_set_id between", value1, value2, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotBetween(Long value1, Long value2) {
            addCriterion("run_set_id not between", value1, value2, "runSetId");
            return (Criteria) this;
        }

        public Criteria andAliasNamesIsNull() {
            addCriterion("alias_names is null");
            return (Criteria) this;
        }

        public Criteria andAliasNamesIsNotNull() {
            addCriterion("alias_names is not null");
            return (Criteria) this;
        }

        public Criteria andAliasNamesEqualTo(Set<String> value) {
            addAliasNamesCriterion("alias_names =", value, "aliasNames");
            return (Criteria) this;
        }

        public Criteria andAliasNamesEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("alias_names = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasNamesNotEqualTo(Set<String> value) {
            addAliasNamesCriterion("alias_names <>", value, "aliasNames");
            return (Criteria) this;
        }

        public Criteria andAliasNamesNotEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("alias_names <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasNamesGreaterThan(Set<String> value) {
            addAliasNamesCriterion("alias_names >", value, "aliasNames");
            return (Criteria) this;
        }

        public Criteria andAliasNamesGreaterThanColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("alias_names > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasNamesGreaterThanOrEqualTo(Set<String> value) {
            addAliasNamesCriterion("alias_names >=", value, "aliasNames");
            return (Criteria) this;
        }

        public Criteria andAliasNamesGreaterThanOrEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("alias_names >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasNamesLessThan(Set<String> value) {
            addAliasNamesCriterion("alias_names <", value, "aliasNames");
            return (Criteria) this;
        }

        public Criteria andAliasNamesLessThanColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("alias_names < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasNamesLessThanOrEqualTo(Set<String> value) {
            addAliasNamesCriterion("alias_names <=", value, "aliasNames");
            return (Criteria) this;
        }

        public Criteria andAliasNamesLessThanOrEqualToColumn(RunSetAliasNameMap.Column column) {
            addCriterion(new StringBuilder("alias_names <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasNamesBetween(Set<String> value1, Set<String> value2) {
            addAliasNamesCriterion("alias_names between", value1, value2, "aliasNames");
            return (Criteria) this;
        }

        public Criteria andAliasNamesNotBetween(Set<String> value1, Set<String> value2) {
            addAliasNamesCriterion("alias_names not between", value1, value2, "aliasNames");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private RunSetAliasNameMapExample example;

        protected Criteria(RunSetAliasNameMapExample example) {
            super();
            this.example = example;
        }

        public RunSetAliasNameMapExample example() {
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
        void example(com.meowlomo.atm.core.model.RunSetAliasNameMapExample example);
    }
}