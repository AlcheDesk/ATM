package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstructionBundleEntryExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructionBundleEntryExample() {
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

    public InstructionBundleEntryExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public InstructionBundleEntryExample orderBy(String... orderByClauses) {
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
        InstructionBundleEntryExample example = new InstructionBundleEntryExample();
        return example.createCriteria();
    }

    public InstructionBundleEntryExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public InstructionBundleEntryExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> instructionTypeCriteria;

        protected List<Criterion> elementTypeCriteria;

        protected List<Criterion> instructionActionCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            instructionTypeCriteria = new ArrayList<Criterion>();
            elementTypeCriteria = new ArrayList<Criterion>();
            instructionActionCriteria = new ArrayList<Criterion>();
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
            return criteria.size() > 0 || instructionTypeCriteria.size() > 0 || elementTypeCriteria.size() > 0 || instructionActionCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(instructionTypeCriteria);
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

        public Criteria andIdEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
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

        public Criteria andCommentEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("comment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("comment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("comment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("comment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("comment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
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

        public Criteria andInstructionBundleIdIsNull() {
            addCriterion("instruction_bundle_id is null");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdIsNotNull() {
            addCriterion("instruction_bundle_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdEqualTo(Long value) {
            addCriterion("instruction_bundle_id =", value, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_bundle_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdNotEqualTo(Long value) {
            addCriterion("instruction_bundle_id <>", value, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_bundle_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdGreaterThan(Long value) {
            addCriterion("instruction_bundle_id >", value, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_bundle_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("instruction_bundle_id >=", value, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_bundle_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdLessThan(Long value) {
            addCriterion("instruction_bundle_id <", value, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_bundle_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdLessThanOrEqualTo(Long value) {
            addCriterion("instruction_bundle_id <=", value, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_bundle_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdIn(List<Long> values) {
            addCriterion("instruction_bundle_id in", values, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdNotIn(List<Long> values) {
            addCriterion("instruction_bundle_id not in", values, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdBetween(Long value1, Long value2) {
            addCriterion("instruction_bundle_id between", value1, value2, "instructionBundleId");
            return (Criteria) this;
        }

        public Criteria andInstructionBundleIdNotBetween(Long value1, Long value2) {
            addCriterion("instruction_bundle_id not between", value1, value2, "instructionBundleId");
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

        public Criteria andInstructionTypeEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeNotEqualTo(String value) {
            addInstructionTypeCriterion("instruction_type_id <>", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThan(String value) {
            addInstructionTypeCriterion("instruction_type_id >", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThanOrEqualTo(String value) {
            addInstructionTypeCriterion("instruction_type_id >=", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThan(String value) {
            addInstructionTypeCriterion("instruction_type_id <", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThanOrEqualTo(String value) {
            addInstructionTypeCriterion("instruction_type_id <=", value, "instructionType");
            return (Criteria) this;
        }

        public Criteria andInstructionTypeLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
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

        public Criteria andElementTypeEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("element_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeNotEqualTo(String value) {
            addElementTypeCriterion("element_type_id <>", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("element_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThan(String value) {
            addElementTypeCriterion("element_type_id >", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("element_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanOrEqualTo(String value) {
            addElementTypeCriterion("element_type_id >=", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("element_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThan(String value) {
            addElementTypeCriterion("element_type_id <", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("element_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanOrEqualTo(String value) {
            addElementTypeCriterion("element_type_id <=", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
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

        public Criteria andInstructionActionEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_action_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionNotEqualTo(String value) {
            addInstructionActionCriterion("instruction_action_id <>", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_action_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThan(String value) {
            addInstructionActionCriterion("instruction_action_id >", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_action_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThanOrEqualTo(String value) {
            addInstructionActionCriterion("instruction_action_id >=", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_action_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThan(String value) {
            addInstructionActionCriterion("instruction_action_id <", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("instruction_action_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThanOrEqualTo(String value) {
            addInstructionActionCriterion("instruction_action_id <=", value, "instructionAction");
            return (Criteria) this;
        }

        public Criteria andInstructionActionLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
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

        public Criteria andOrderIndexIsNull() {
            addCriterion("order_index is null");
            return (Criteria) this;
        }

        public Criteria andOrderIndexIsNotNull() {
            addCriterion("order_index is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIndexEqualTo(Long value) {
            addCriterion("order_index =", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("order_index = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotEqualTo(Long value) {
            addCriterion("order_index <>", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("order_index <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThan(Long value) {
            addCriterion("order_index >", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("order_index > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThanOrEqualTo(Long value) {
            addCriterion("order_index >=", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("order_index >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThan(Long value) {
            addCriterion("order_index <", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("order_index < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThanOrEqualTo(Long value) {
            addCriterion("order_index <=", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("order_index <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexIn(List<Long> values) {
            addCriterion("order_index in", values, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotIn(List<Long> values) {
            addCriterion("order_index not in", values, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexBetween(Long value1, Long value2) {
            addCriterion("order_index between", value1, value2, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotBetween(Long value1, Long value2) {
            addCriterion("order_index not between", value1, value2, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("is_deleted = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("is_deleted <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("is_deleted > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("is_deleted >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("is_deleted < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("is_deleted <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("created_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(InstructionBundleEntry.Column column) {
            addCriterion(new StringBuilder("updated_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
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
        private InstructionBundleEntryExample example;

        protected Criteria(InstructionBundleEntryExample example) {
            super();
            this.example = example;
        }

        public InstructionBundleEntryExample example() {
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

        public Criteria andLogicalDeleted(boolean deleted) {
            return deleted ? andDeletedEqualTo(InstructionBundleEntry.Deleted.IS_DELETED.value()) : andDeletedNotEqualTo(InstructionBundleEntry.Deleted.IS_DELETED.value());
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
        void example(com.meowlomo.atm.core.model.InstructionBundleEntryExample example);
    }
}