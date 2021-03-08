package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DriverPackDriverLinkExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverPackDriverLinkExample() {
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

    public DriverPackDriverLinkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public DriverPackDriverLinkExample orderBy(String... orderByClauses) {
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
        DriverPackDriverLinkExample example = new DriverPackDriverLinkExample();
        return example.createCriteria();
    }

    public DriverPackDriverLinkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public DriverPackDriverLinkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(DriverPackDriverLink.Column column) {
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

        public Criteria andDriverPackIdEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_pack_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotEqualTo(Long value) {
            addCriterion("driver_pack_id <>", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_pack_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThan(Long value) {
            addCriterion("driver_pack_id >", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_pack_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_pack_id >=", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanOrEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_pack_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThan(Long value) {
            addCriterion("driver_pack_id <", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_pack_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanOrEqualTo(Long value) {
            addCriterion("driver_pack_id <=", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanOrEqualToColumn(DriverPackDriverLink.Column column) {
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

        public Criteria andDriverIdIsNull() {
            addCriterion("driver_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNotNull() {
            addCriterion("driver_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverIdEqualTo(Long value) {
            addCriterion("driver_id =", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverIdNotEqualTo(Long value) {
            addCriterion("driver_id <>", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThan(Long value) {
            addCriterion("driver_id >", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_id >=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThanOrEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThan(Long value) {
            addCriterion("driver_id <", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThanOrEqualTo(Long value) {
            addCriterion("driver_id <=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThanOrEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverIdIn(List<Long> values) {
            addCriterion("driver_id in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotIn(List<Long> values) {
            addCriterion("driver_id not in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdBetween(Long value1, Long value2) {
            addCriterion("driver_id between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotBetween(Long value1, Long value2) {
            addCriterion("driver_id not between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverAliasIsNull() {
            addCriterion("driver_alias is null");
            return (Criteria) this;
        }

        public Criteria andDriverAliasIsNotNull() {
            addCriterion("driver_alias is not null");
            return (Criteria) this;
        }

        public Criteria andDriverAliasEqualTo(String value) {
            addCriterion("driver_alias =", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_alias = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasNotEqualTo(String value) {
            addCriterion("driver_alias <>", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasNotEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_alias <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThan(String value) {
            addCriterion("driver_alias >", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_alias > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThanOrEqualTo(String value) {
            addCriterion("driver_alias >=", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThanOrEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_alias >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThan(String value) {
            addCriterion("driver_alias <", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThanColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_alias < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThanOrEqualTo(String value) {
            addCriterion("driver_alias <=", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThanOrEqualToColumn(DriverPackDriverLink.Column column) {
            addCriterion(new StringBuilder("driver_alias <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasLike(String value) {
            addCriterion("driver_alias like", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasNotLike(String value) {
            addCriterion("driver_alias not like", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasIn(List<String> values) {
            addCriterion("driver_alias in", values, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasNotIn(List<String> values) {
            addCriterion("driver_alias not in", values, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasBetween(String value1, String value2) {
            addCriterion("driver_alias between", value1, value2, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasNotBetween(String value1, String value2) {
            addCriterion("driver_alias not between", value1, value2, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasLikeInsensitive(String value) {
            addCriterion("upper(driver_alias) like", value.toUpperCase(), "driverAlias");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private DriverPackDriverLinkExample example;

        protected Criteria(DriverPackDriverLinkExample example) {
            super();
            this.example = example;
        }

        public DriverPackDriverLinkExample example() {
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
        void example(com.meowlomo.atm.core.model.DriverPackDriverLinkExample example);
    }
}