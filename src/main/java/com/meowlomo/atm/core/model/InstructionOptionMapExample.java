package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InstructionOptionMapExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructionOptionMapExample() {
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

    public InstructionOptionMapExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public InstructionOptionMapExample orderBy(String... orderByClauses) {
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
        InstructionOptionMapExample example = new InstructionOptionMapExample();
        return example.createCriteria();
    }

    public InstructionOptionMapExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public InstructionOptionMapExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> instructionOptionCriteria;

        protected List<Criterion> instructionActionIdsCriteria;

        protected List<Criterion> elementTypeIdsCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            instructionOptionCriteria = new ArrayList<Criterion>();
            instructionActionIdsCriteria = new ArrayList<Criterion>();
            elementTypeIdsCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getInstructionOptionCriteria() {
            return instructionOptionCriteria;
        }

        protected void addInstructionOptionCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            instructionOptionCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.InstructionOptionTypeHandler"));
            allCriteria = null;
        }

        protected void addInstructionOptionCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            instructionOptionCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.InstructionOptionTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getInstructionActionIdsCriteria() {
            return instructionActionIdsCriteria;
        }

        protected void addInstructionActionIdsCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            instructionActionIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addInstructionActionIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            instructionActionIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getElementTypeIdsCriteria() {
            return elementTypeIdsCriteria;
        }

        protected void addElementTypeIdsCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            elementTypeIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addElementTypeIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            elementTypeIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || instructionOptionCriteria.size() > 0 || instructionActionIdsCriteria.size() > 0 || elementTypeIdsCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(instructionOptionCriteria);
                allCriteria.addAll(instructionActionIdsCriteria);
                allCriteria.addAll(elementTypeIdsCriteria);
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

        public Criteria andInstructionOptionIsNull() {
            addCriterion("instruction_option_id is null");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionIsNotNull() {
            addCriterion("instruction_option_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionEqualTo(String value) {
            addInstructionOptionCriterion("instruction_option_id =", value, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_option_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionNotEqualTo(String value) {
            addInstructionOptionCriterion("instruction_option_id <>", value, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionNotEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_option_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionGreaterThan(String value) {
            addInstructionOptionCriterion("instruction_option_id >", value, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionGreaterThanColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_option_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionGreaterThanOrEqualTo(String value) {
            addInstructionOptionCriterion("instruction_option_id >=", value, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionGreaterThanOrEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_option_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLessThan(String value) {
            addInstructionOptionCriterion("instruction_option_id <", value, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLessThanColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_option_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLessThanOrEqualTo(String value) {
            addInstructionOptionCriterion("instruction_option_id <=", value, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLessThanOrEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_option_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionIn(List<String> values) {
            addInstructionOptionCriterion("instruction_option_id in", values, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionNotIn(List<String> values) {
            addInstructionOptionCriterion("instruction_option_id not in", values, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionBetween(String value1, String value2) {
            addInstructionOptionCriterion("instruction_option_id between", value1, value2, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionNotBetween(String value1, String value2) {
            addInstructionOptionCriterion("instruction_option_id not between", value1, value2, "instructionOption");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsIsNull() {
            addCriterion("instruction_action_ids is null");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsIsNotNull() {
            addCriterion("instruction_action_ids is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsEqualTo(Set<Long> value) {
            addInstructionActionIdsCriterion("instruction_action_ids =", value, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_action_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsNotEqualTo(Set<Long> value) {
            addInstructionActionIdsCriterion("instruction_action_ids <>", value, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsNotEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_action_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsGreaterThan(Set<Long> value) {
            addInstructionActionIdsCriterion("instruction_action_ids >", value, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsGreaterThanColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_action_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsGreaterThanOrEqualTo(Set<Long> value) {
            addInstructionActionIdsCriterion("instruction_action_ids >=", value, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsGreaterThanOrEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_action_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsLessThan(Set<Long> value) {
            addInstructionActionIdsCriterion("instruction_action_ids <", value, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsLessThanColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_action_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsLessThanOrEqualTo(Set<Long> value) {
            addInstructionActionIdsCriterion("instruction_action_ids <=", value, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsLessThanOrEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("instruction_action_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsBetween(Set<Long> value1, Set<Long> value2) {
            addInstructionActionIdsCriterion("instruction_action_ids between", value1, value2, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addInstructionActionIdsCriterion("instruction_action_ids not between", value1, value2, "instructionActionIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsIsNull() {
            addCriterion("element_type_ids is null");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsIsNotNull() {
            addCriterion("element_type_ids is not null");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsEqualTo(Set<Long> value) {
            addElementTypeIdsCriterion("element_type_ids =", value, "elementTypeIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("element_type_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsNotEqualTo(Set<Long> value) {
            addElementTypeIdsCriterion("element_type_ids <>", value, "elementTypeIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsNotEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("element_type_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsGreaterThan(Set<Long> value) {
            addElementTypeIdsCriterion("element_type_ids >", value, "elementTypeIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsGreaterThanColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("element_type_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsGreaterThanOrEqualTo(Set<Long> value) {
            addElementTypeIdsCriterion("element_type_ids >=", value, "elementTypeIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsGreaterThanOrEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("element_type_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsLessThan(Set<Long> value) {
            addElementTypeIdsCriterion("element_type_ids <", value, "elementTypeIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsLessThanColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("element_type_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsLessThanOrEqualTo(Set<Long> value) {
            addElementTypeIdsCriterion("element_type_ids <=", value, "elementTypeIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsLessThanOrEqualToColumn(InstructionOptionMap.Column column) {
            addCriterion(new StringBuilder("element_type_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsBetween(Set<Long> value1, Set<Long> value2) {
            addElementTypeIdsCriterion("element_type_ids between", value1, value2, "elementTypeIds");
            return (Criteria) this;
        }

        public Criteria andElementTypeIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addElementTypeIdsCriterion("element_type_ids not between", value1, value2, "elementTypeIds");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private InstructionOptionMapExample example;

        protected Criteria(InstructionOptionMapExample example) {
            super();
            this.example = example;
        }

        public InstructionOptionMapExample example() {
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
        void example(com.meowlomo.atm.core.model.InstructionOptionMapExample example);
    }
}