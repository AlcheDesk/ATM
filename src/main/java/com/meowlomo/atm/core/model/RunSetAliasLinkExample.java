package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RunSetAliasLinkExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunSetAliasLinkExample() {
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

    public RunSetAliasLinkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunSetAliasLinkExample orderBy(String... orderByClauses) {
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
        RunSetAliasLinkExample example = new RunSetAliasLinkExample();
        return example.createCriteria();
    }

    public RunSetAliasLinkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunSetAliasLinkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(RunSetAliasLink.Column column) {
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

        public Criteria andRunSetIdEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("run_set_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualTo(Long value) {
            addCriterion("run_set_id <>", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("run_set_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThan(Long value) {
            addCriterion("run_set_id >", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("run_set_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_id >=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("run_set_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThan(Long value) {
            addCriterion("run_set_id <", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("run_set_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_id <=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualToColumn(RunSetAliasLink.Column column) {
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

        public Criteria andAliasIdIsNull() {
            addCriterion("alias_id is null");
            return (Criteria) this;
        }

        public Criteria andAliasIdIsNotNull() {
            addCriterion("alias_id is not null");
            return (Criteria) this;
        }

        public Criteria andAliasIdEqualTo(Long value) {
            addCriterion("alias_id =", value, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("alias_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasIdNotEqualTo(Long value) {
            addCriterion("alias_id <>", value, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdNotEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("alias_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasIdGreaterThan(Long value) {
            addCriterion("alias_id >", value, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdGreaterThanColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("alias_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasIdGreaterThanOrEqualTo(Long value) {
            addCriterion("alias_id >=", value, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdGreaterThanOrEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("alias_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasIdLessThan(Long value) {
            addCriterion("alias_id <", value, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdLessThanColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("alias_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasIdLessThanOrEqualTo(Long value) {
            addCriterion("alias_id <=", value, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdLessThanOrEqualToColumn(RunSetAliasLink.Column column) {
            addCriterion(new StringBuilder("alias_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAliasIdIn(List<Long> values) {
            addCriterion("alias_id in", values, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdNotIn(List<Long> values) {
            addCriterion("alias_id not in", values, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdBetween(Long value1, Long value2) {
            addCriterion("alias_id between", value1, value2, "aliasId");
            return (Criteria) this;
        }

        public Criteria andAliasIdNotBetween(Long value1, Long value2) {
            addCriterion("alias_id not between", value1, value2, "aliasId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private RunSetAliasLinkExample example;

        protected Criteria(RunSetAliasLinkExample example) {
            super();
            this.example = example;
        }

        public RunSetAliasLinkExample example() {
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
        void example(com.meowlomo.atm.core.model.RunSetAliasLinkExample example);
    }
}