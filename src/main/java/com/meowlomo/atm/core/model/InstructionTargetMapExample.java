package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InstructionTargetMapExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructionTargetMapExample() {
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

    public InstructionTargetMapExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public InstructionTargetMapExample orderBy(String... orderByClauses) {
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
        InstructionTargetMapExample example = new InstructionTargetMapExample();
        return example.createCriteria();
    }

    public InstructionTargetMapExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public InstructionTargetMapExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
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

        public Criteria andApplicationIdIsNull() {
            addCriterion("application_id is null");
            return (Criteria) this;
        }

        public Criteria andApplicationIdIsNotNull() {
            addCriterion("application_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationIdEqualTo(Long value) {
            addCriterion("application_id =", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotEqualTo(Long value) {
            addCriterion("application_id <>", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThan(Long value) {
            addCriterion("application_id >", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("application_id >=", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThan(Long value) {
            addCriterion("application_id <", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanOrEqualTo(Long value) {
            addCriterion("application_id <=", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdIn(List<Long> values) {
            addCriterion("application_id in", values, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotIn(List<Long> values) {
            addCriterion("application_id not in", values, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdBetween(Long value1, Long value2) {
            addCriterion("application_id between", value1, value2, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotBetween(Long value1, Long value2) {
            addCriterion("application_id not between", value1, value2, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNull() {
            addCriterion("application_name is null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNotNull() {
            addCriterion("application_name is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameEqualTo(String value) {
            addCriterion("application_name =", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotEqualTo(String value) {
            addCriterion("application_name <>", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThan(String value) {
            addCriterion("application_name >", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThanOrEqualTo(String value) {
            addCriterion("application_name >=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThan(String value) {
            addCriterion("application_name <", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThanOrEqualTo(String value) {
            addCriterion("application_name <=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("application_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationNameLike(String value) {
            addCriterion("application_name like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotLike(String value) {
            addCriterion("application_name not like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIn(List<String> values) {
            addCriterion("application_name in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotIn(List<String> values) {
            addCriterion("application_name not in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameBetween(String value1, String value2) {
            addCriterion("application_name between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotBetween(String value1, String value2) {
            addCriterion("application_name not between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andSectionIdIsNull() {
            addCriterion("section_id is null");
            return (Criteria) this;
        }

        public Criteria andSectionIdIsNotNull() {
            addCriterion("section_id is not null");
            return (Criteria) this;
        }

        public Criteria andSectionIdEqualTo(Long value) {
            addCriterion("section_id =", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualTo(Long value) {
            addCriterion("section_id <>", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThan(Long value) {
            addCriterion("section_id >", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("section_id >=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThan(Long value) {
            addCriterion("section_id <", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualTo(Long value) {
            addCriterion("section_id <=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdIn(List<Long> values) {
            addCriterion("section_id in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotIn(List<Long> values) {
            addCriterion("section_id not in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdBetween(Long value1, Long value2) {
            addCriterion("section_id between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotBetween(Long value1, Long value2) {
            addCriterion("section_id not between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionNameIsNull() {
            addCriterion("section_name is null");
            return (Criteria) this;
        }

        public Criteria andSectionNameIsNotNull() {
            addCriterion("section_name is not null");
            return (Criteria) this;
        }

        public Criteria andSectionNameEqualTo(String value) {
            addCriterion("section_name =", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionNameNotEqualTo(String value) {
            addCriterion("section_name <>", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionNameGreaterThan(String value) {
            addCriterion("section_name >", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("section_name >=", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionNameLessThan(String value) {
            addCriterion("section_name <", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionNameLessThanOrEqualTo(String value) {
            addCriterion("section_name <=", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("section_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionNameLike(String value) {
            addCriterion("section_name like", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotLike(String value) {
            addCriterion("section_name not like", value, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameIn(List<String> values) {
            addCriterion("section_name in", values, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotIn(List<String> values) {
            addCriterion("section_name not in", values, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameBetween(String value1, String value2) {
            addCriterion("section_name between", value1, value2, "sectionName");
            return (Criteria) this;
        }

        public Criteria andSectionNameNotBetween(String value1, String value2) {
            addCriterion("section_name not between", value1, value2, "sectionName");
            return (Criteria) this;
        }

        public Criteria andElementIdIsNull() {
            addCriterion("element_id is null");
            return (Criteria) this;
        }

        public Criteria andElementIdIsNotNull() {
            addCriterion("element_id is not null");
            return (Criteria) this;
        }

        public Criteria andElementIdEqualTo(Long value) {
            addCriterion("element_id =", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualTo(Long value) {
            addCriterion("element_id <>", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThan(Long value) {
            addCriterion("element_id >", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualTo(Long value) {
            addCriterion("element_id >=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdLessThan(Long value) {
            addCriterion("element_id <", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualTo(Long value) {
            addCriterion("element_id <=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdIn(List<Long> values) {
            addCriterion("element_id in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotIn(List<Long> values) {
            addCriterion("element_id not in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdBetween(Long value1, Long value2) {
            addCriterion("element_id between", value1, value2, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotBetween(Long value1, Long value2) {
            addCriterion("element_id not between", value1, value2, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNull() {
            addCriterion("element_name is null");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNotNull() {
            addCriterion("element_name is not null");
            return (Criteria) this;
        }

        public Criteria andElementNameEqualTo(String value) {
            addCriterion("element_name =", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementNameNotEqualTo(String value) {
            addCriterion("element_name <>", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThan(String value) {
            addCriterion("element_name >", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThanOrEqualTo(String value) {
            addCriterion("element_name >=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementNameLessThan(String value) {
            addCriterion("element_name <", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementNameLessThanOrEqualTo(String value) {
            addCriterion("element_name <=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("element_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementNameLike(String value) {
            addCriterion("element_name like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotLike(String value) {
            addCriterion("element_name not like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameIn(List<String> values) {
            addCriterion("element_name in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotIn(List<String> values) {
            addCriterion("element_name not in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameBetween(String value1, String value2) {
            addCriterion("element_name between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotBetween(String value1, String value2) {
            addCriterion("element_name not between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdIsNull() {
            addCriterion("test_case_share_folder_id is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdIsNotNull() {
            addCriterion("test_case_share_folder_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdEqualTo(Long value) {
            addCriterion("test_case_share_folder_id =", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdNotEqualTo(Long value) {
            addCriterion("test_case_share_folder_id <>", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThan(Long value) {
            addCriterion("test_case_share_folder_id >", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_share_folder_id >=", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThan(Long value) {
            addCriterion("test_case_share_folder_id <", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_share_folder_id <=", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdIn(List<Long> values) {
            addCriterion("test_case_share_folder_id in", values, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdNotIn(List<Long> values) {
            addCriterion("test_case_share_folder_id not in", values, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdBetween(Long value1, Long value2) {
            addCriterion("test_case_share_folder_id between", value1, value2, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdNotBetween(Long value1, Long value2) {
            addCriterion("test_case_share_folder_id not between", value1, value2, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameIsNull() {
            addCriterion("test_case_share_folder_name is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameIsNotNull() {
            addCriterion("test_case_share_folder_name is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameEqualTo(String value) {
            addCriterion("test_case_share_folder_name =", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameNotEqualTo(String value) {
            addCriterion("test_case_share_folder_name <>", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameGreaterThan(String value) {
            addCriterion("test_case_share_folder_name >", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameGreaterThanOrEqualTo(String value) {
            addCriterion("test_case_share_folder_name >=", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameLessThan(String value) {
            addCriterion("test_case_share_folder_name <", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameLessThanOrEqualTo(String value) {
            addCriterion("test_case_share_folder_name <=", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameLike(String value) {
            addCriterion("test_case_share_folder_name like", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameNotLike(String value) {
            addCriterion("test_case_share_folder_name not like", value, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameIn(List<String> values) {
            addCriterion("test_case_share_folder_name in", values, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameNotIn(List<String> values) {
            addCriterion("test_case_share_folder_name not in", values, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameBetween(String value1, String value2) {
            addCriterion("test_case_share_folder_name between", value1, value2, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameNotBetween(String value1, String value2) {
            addCriterion("test_case_share_folder_name not between", value1, value2, "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdIsNull() {
            addCriterion("ref_test_case_id is null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdIsNotNull() {
            addCriterion("ref_test_case_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdEqualTo(Long value) {
            addCriterion("ref_test_case_id =", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdNotEqualTo(Long value) {
            addCriterion("ref_test_case_id <>", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThan(Long value) {
            addCriterion("ref_test_case_id >", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_id >=", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThan(Long value) {
            addCriterion("ref_test_case_id <", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_id <=", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdIn(List<Long> values) {
            addCriterion("ref_test_case_id in", values, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdNotIn(List<Long> values) {
            addCriterion("ref_test_case_id not in", values, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdBetween(Long value1, Long value2) {
            addCriterion("ref_test_case_id between", value1, value2, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdNotBetween(Long value1, Long value2) {
            addCriterion("ref_test_case_id not between", value1, value2, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameIsNull() {
            addCriterion("ref_test_case_name is null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameIsNotNull() {
            addCriterion("ref_test_case_name is not null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameEqualTo(String value) {
            addCriterion("ref_test_case_name =", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameNotEqualTo(String value) {
            addCriterion("ref_test_case_name <>", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameGreaterThan(String value) {
            addCriterion("ref_test_case_name >", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("ref_test_case_name >=", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameLessThan(String value) {
            addCriterion("ref_test_case_name <", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameLessThanOrEqualTo(String value) {
            addCriterion("ref_test_case_name <=", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameLike(String value) {
            addCriterion("ref_test_case_name like", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameNotLike(String value) {
            addCriterion("ref_test_case_name not like", value, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameIn(List<String> values) {
            addCriterion("ref_test_case_name in", values, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameNotIn(List<String> values) {
            addCriterion("ref_test_case_name not in", values, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameBetween(String value1, String value2) {
            addCriterion("ref_test_case_name between", value1, value2, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameNotBetween(String value1, String value2) {
            addCriterion("ref_test_case_name not between", value1, value2, "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andInstructionIdIsNull() {
            addCriterion("instruction_id is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIdIsNotNull() {
            addCriterion("instruction_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionIdEqualTo(Long value) {
            addCriterion("instruction_id =", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("instruction_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotEqualTo(Long value) {
            addCriterion("instruction_id <>", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("instruction_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThan(Long value) {
            addCriterion("instruction_id >", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("instruction_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("instruction_id >=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("instruction_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThan(Long value) {
            addCriterion("instruction_id <", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("instruction_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanOrEqualTo(Long value) {
            addCriterion("instruction_id <=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("instruction_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdIn(List<Long> values) {
            addCriterion("instruction_id in", values, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotIn(List<Long> values) {
            addCriterion("instruction_id not in", values, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdBetween(Long value1, Long value2) {
            addCriterion("instruction_id between", value1, value2, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotBetween(Long value1, Long value2) {
            addCriterion("instruction_id not between", value1, value2, "instructionId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdIsNull() {
            addCriterion("ref_test_case_overwrite_id is null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdIsNotNull() {
            addCriterion("ref_test_case_overwrite_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id =", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id <>", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThan(Long value) {
            addCriterion("ref_test_case_overwrite_id >", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id >=", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThan(Long value) {
            addCriterion("ref_test_case_overwrite_id <", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id <=", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdIn(List<Long> values) {
            addCriterion("ref_test_case_overwrite_id in", values, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotIn(List<Long> values) {
            addCriterion("ref_test_case_overwrite_id not in", values, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdBetween(Long value1, Long value2) {
            addCriterion("ref_test_case_overwrite_id between", value1, value2, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotBetween(Long value1, Long value2) {
            addCriterion("ref_test_case_overwrite_id not between", value1, value2, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameIsNull() {
            addCriterion("ref_test_case_overwrite_name is null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameIsNotNull() {
            addCriterion("ref_test_case_overwrite_name is not null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameEqualTo(String value) {
            addCriterion("ref_test_case_overwrite_name =", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameNotEqualTo(String value) {
            addCriterion("ref_test_case_overwrite_name <>", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameNotEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThan(String value) {
            addCriterion("ref_test_case_overwrite_name >", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThanOrEqualTo(String value) {
            addCriterion("ref_test_case_overwrite_name >=", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThan(String value) {
            addCriterion("ref_test_case_overwrite_name <", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThanColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThanOrEqualTo(String value) {
            addCriterion("ref_test_case_overwrite_name <=", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThanOrEqualToColumn(InstructionTargetMap.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLike(String value) {
            addCriterion("ref_test_case_overwrite_name like", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameNotLike(String value) {
            addCriterion("ref_test_case_overwrite_name not like", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameIn(List<String> values) {
            addCriterion("ref_test_case_overwrite_name in", values, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameNotIn(List<String> values) {
            addCriterion("ref_test_case_overwrite_name not in", values, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameBetween(String value1, String value2) {
            addCriterion("ref_test_case_overwrite_name between", value1, value2, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameNotBetween(String value1, String value2) {
            addCriterion("ref_test_case_overwrite_name not between", value1, value2, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLikeInsensitive(String value) {
            addCriterion("upper(application_name) like", value.toUpperCase(), "applicationName");
            return (Criteria) this;
        }

        public Criteria andSectionNameLikeInsensitive(String value) {
            addCriterion("upper(section_name) like", value.toUpperCase(), "sectionName");
            return (Criteria) this;
        }

        public Criteria andElementNameLikeInsensitive(String value) {
            addCriterion("upper(element_name) like", value.toUpperCase(), "elementName");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderNameLikeInsensitive(String value) {
            addCriterion("upper(test_case_share_folder_name) like", value.toUpperCase(), "testCaseShareFolderName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseNameLikeInsensitive(String value) {
            addCriterion("upper(ref_test_case_name) like", value.toUpperCase(), "refTestCaseName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLikeInsensitive(String value) {
            addCriterion("upper(ref_test_case_overwrite_name) like", value.toUpperCase(), "refTestCaseOverwriteName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private InstructionTargetMapExample example;

        protected Criteria(InstructionTargetMapExample example) {
            super();
            this.example = example;
        }

        public InstructionTargetMapExample example() {
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
        void example(com.meowlomo.atm.core.model.InstructionTargetMapExample example);
    }
}