package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DriverTypeInstructionTypeLinkExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverTypeInstructionTypeLinkExample() {
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

    public DriverTypeInstructionTypeLinkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public DriverTypeInstructionTypeLinkExample orderBy(String... orderByClauses) {
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
        DriverTypeInstructionTypeLinkExample example = new DriverTypeInstructionTypeLinkExample();
        return example.createCriteria();
    }

    public DriverTypeInstructionTypeLinkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public DriverTypeInstructionTypeLinkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> driverTypeCriteria;

        protected List<Criterion> instructionTypeCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            driverTypeCriteria = new ArrayList<Criterion>();
            instructionTypeCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getDriverTypeCriteria() {
            return driverTypeCriteria;
        }

        protected void addDriverTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            driverTypeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.DriverTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addDriverTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            driverTypeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.DriverTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getInstructionTypeCriteria() {
            return instructionTypeCriteria;
        }

        protected void addInstructionTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            instructionTypeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.InstructionTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addInstructionTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            instructionTypeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.InstructionTypeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || driverTypeCriteria.size() > 0 || instructionTypeCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(driverTypeCriteria);
                allCriteria.addAll(instructionTypeCriteria);
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

        public Criteria andIdEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
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

        public Criteria andDriverTypeIsNull() {
            addCriterion("driver_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIsNotNull() {
            addCriterion("driver_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id =", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("driver_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id <>", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("driver_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThan(String value) {
            addDriverTypeCriterion("driver_type_id >", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("driver_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanOrEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id >=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanOrEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("driver_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThan(String value) {
            addDriverTypeCriterion("driver_type_id <", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("driver_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanOrEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id <=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanOrEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("driver_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeIn(List<String> values) {
            addDriverTypeCriterion("driver_type_id in", values, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotIn(List<String> values) {
            addDriverTypeCriterion("driver_type_id not in", values, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeBetween(String value1, String value2) {
            addDriverTypeCriterion("driver_type_id between", value1, value2, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotBetween(String value1, String value2) {
            addDriverTypeCriterion("driver_type_id not between", value1, value2, "driverType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeIsNull() {
            addCriterion("instruction_type_id is null");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeIsNotNull() {
            addCriterion("instruction_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeEqualTo(String value) {
            addInstructionTypeCriterion("instruction_type_id =", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("instruction_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeNotEqualTo(String value) {
            addInstructionTypeCriterion("instruction_type_id <>", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeNotEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("instruction_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThan(String value) {
            addInstructionTypeCriterion("instruction_type_id >", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThanColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("instruction_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThanOrEqualTo(String value) {
            addInstructionTypeCriterion("instruction_type_id >=", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThanOrEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("instruction_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThan(String value) {
            addInstructionTypeCriterion("instruction_type_id <", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThanColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("instruction_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThanOrEqualTo(String value) {
            addInstructionTypeCriterion("instruction_type_id <=", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThanOrEqualToColumn(DriverTypeInstructionTypeLink.Column column) {
            addCriterion(new StringBuilder("instruction_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeIn(List<String> values) {
            addInstructionTypeCriterion("instruction_type_id in", values, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeNotIn(List<String> values) {
            addInstructionTypeCriterion("instruction_type_id not in", values, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeBetween(String value1, String value2) {
            addInstructionTypeCriterion("instruction_type_id between", value1, value2, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeNotBetween(String value1, String value2) {
            addInstructionTypeCriterion("instruction_type_id not between", value1, value2, "instructionType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private DriverTypeInstructionTypeLinkExample example;

        protected Criteria(DriverTypeInstructionTypeLinkExample example) {
            super();
            this.example = example;
        }

        public DriverTypeInstructionTypeLinkExample example() {
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
        void example(com.meowlomo.atm.core.model.DriverTypeInstructionTypeLinkExample example);
    }
}