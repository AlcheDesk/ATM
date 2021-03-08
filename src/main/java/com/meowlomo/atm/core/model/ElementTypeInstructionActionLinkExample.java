package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ElementTypeInstructionActionLinkExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ElementTypeInstructionActionLinkExample() {
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

    public ElementTypeInstructionActionLinkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public ElementTypeInstructionActionLinkExample orderBy(String... orderByClauses) {
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
        ElementTypeInstructionActionLinkExample example = new ElementTypeInstructionActionLinkExample();
        return example.createCriteria();
    }

    public ElementTypeInstructionActionLinkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public ElementTypeInstructionActionLinkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> elementTypeCriteria;

        protected List<Criterion> instructionActionCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            elementTypeCriteria = new ArrayList<Criterion>();
            instructionActionCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getElementTypeCriteria() {
            return elementTypeCriteria;
        }

        protected void addElementTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            elementTypeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.ElementTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addElementTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            elementTypeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.ElementTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getInstructionActionCriteria() {
            return instructionActionCriteria;
        }

        protected void addInstructionActionCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            instructionActionCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.InstructionActionTypeHandler"));
            allCriteria = null;
        }

        protected void addInstructionActionCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            instructionActionCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.InstructionActionTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || elementTypeCriteria.size() > 0 || instructionActionCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(elementTypeCriteria);
                allCriteria.addAll(instructionActionCriteria);
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

        public Criteria andIdEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(ElementTypeInstructionActionLink.Column column) {
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

        public Criteria andElementTypeIsNull() {
            addCriterion("element_type_id is null");
            return (Criteria) this;
        }

        public Criteria andElementTypeIsNotNull() {
            addCriterion("element_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andElementTypeEqualTo(String value) {
            addElementTypeCriterion("element_type_id =", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("element_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeNotEqualTo(String value) {
            addElementTypeCriterion("element_type_id <>", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeNotEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("element_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThan(String value) {
            addElementTypeCriterion("element_type_id >", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("element_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanOrEqualTo(String value) {
            addElementTypeCriterion("element_type_id >=", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanOrEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("element_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThan(String value) {
            addElementTypeCriterion("element_type_id <", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("element_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanOrEqualTo(String value) {
            addElementTypeCriterion("element_type_id <=", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanOrEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("element_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeIn(List<String> values) {
            addElementTypeCriterion("element_type_id in", values, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeNotIn(List<String> values) {
            addElementTypeCriterion("element_type_id not in", values, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeBetween(String value1, String value2) {
            addElementTypeCriterion("element_type_id between", value1, value2, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeNotBetween(String value1, String value2) {
            addElementTypeCriterion("element_type_id not between", value1, value2, "elementType");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIsNull() {
            addCriterion("instruction_action_id is null");
            return (Criteria) this;
        }

        public Criteria andInstructionActionIsNotNull() {
            addCriterion("instruction_action_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionActionEqualTo(String value) {
            addInstructionActionCriterion("instruction_action_id =", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("instruction_action_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionNotEqualTo(String value) {
            addInstructionActionCriterion("instruction_action_id <>", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionNotEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("instruction_action_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThan(String value) {
            addInstructionActionCriterion("instruction_action_id >", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThanColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("instruction_action_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThanOrEqualTo(String value) {
            addInstructionActionCriterion("instruction_action_id >=", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThanOrEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("instruction_action_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThan(String value) {
            addInstructionActionCriterion("instruction_action_id <", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThanColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("instruction_action_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThanOrEqualTo(String value) {
            addInstructionActionCriterion("instruction_action_id <=", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThanOrEqualToColumn(ElementTypeInstructionActionLink.Column column) {
            addCriterion(new StringBuilder("instruction_action_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionIn(List<String> values) {
            addInstructionActionCriterion("instruction_action_id in", values, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionNotIn(List<String> values) {
            addInstructionActionCriterion("instruction_action_id not in", values, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionBetween(String value1, String value2) {
            addInstructionActionCriterion("instruction_action_id between", value1, value2, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionNotBetween(String value1, String value2) {
            addInstructionActionCriterion("instruction_action_id not between", value1, value2, "instructionAction");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private ElementTypeInstructionActionLinkExample example;

        protected Criteria(ElementTypeInstructionActionLinkExample example) {
            super();
            this.example = example;
        }

        public ElementTypeInstructionActionLinkExample example() {
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
        void example(com.meowlomo.atm.core.model.ElementTypeInstructionActionLinkExample example);
    }
}