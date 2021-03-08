package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class InstructionExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructionExample() {
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

    public InstructionExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public InstructionExample orderBy(String... orderByClauses) {
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
        InstructionExample example = new InstructionExample();
        return example.createCriteria();
    }

    public InstructionExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public InstructionExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> dataCriteria;

        protected List<Criterion> colorCriteria;

        protected List<Criterion> typeCriteria;

        protected List<Criterion> actionCriteria;

        protected List<Criterion> parameterCriteria;

        protected List<Criterion> elementTypeCriteria;

        protected List<Criterion> driverTypeCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            dataCriteria = new ArrayList<Criterion>();
            colorCriteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            actionCriteria = new ArrayList<Criterion>();
            parameterCriteria = new ArrayList<Criterion>();
            elementTypeCriteria = new ArrayList<Criterion>();
            driverTypeCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getDataCriteria() {
            return dataCriteria;
        }

        protected void addDataCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            dataCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addDataCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            dataCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getColorCriteria() {
            return colorCriteria;
        }

        protected void addColorCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            colorCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.ColorTypeHandler"));
            allCriteria = null;
        }

        protected void addColorCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            colorCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.ColorTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.InstructionTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.InstructionTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getActionCriteria() {
            return actionCriteria;
        }

        protected void addActionCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            actionCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.ActionTypeHandler"));
            allCriteria = null;
        }

        protected void addActionCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            actionCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.ActionTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getParameterCriteria() {
            return parameterCriteria;
        }

        protected void addParameterCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            parameterCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addParameterCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            parameterCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
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

        public boolean isValid() {
            return criteria.size() > 0 || dataCriteria.size() > 0 || colorCriteria.size() > 0 || typeCriteria.size() > 0 || actionCriteria.size() > 0
                    || parameterCriteria.size() > 0 || elementTypeCriteria.size() > 0 || driverTypeCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(dataCriteria);
                allCriteria.addAll(colorCriteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(actionCriteria);
                allCriteria.addAll(parameterCriteria);
                allCriteria.addAll(elementTypeCriteria);
                allCriteria.addAll(driverTypeCriteria);
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

        public Criteria andIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andCommentEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("comment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("comment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("comment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("comment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("comment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andCreatedAtEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andInputIsNull() {
            addCriterion("input is null");
            return (Criteria) this;
        }

        public Criteria andInputIsNotNull() {
            addCriterion("input is not null");
            return (Criteria) this;
        }

        public Criteria andInputEqualTo(String value) {
            addCriterion("input =", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("input = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputNotEqualTo(String value) {
            addCriterion("input <>", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("input <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputGreaterThan(String value) {
            addCriterion("input >", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("input > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputGreaterThanOrEqualTo(String value) {
            addCriterion("input >=", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("input >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputLessThan(String value) {
            addCriterion("input <", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("input < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputLessThanOrEqualTo(String value) {
            addCriterion("input <=", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("input <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputLike(String value) {
            addCriterion("input like", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotLike(String value) {
            addCriterion("input not like", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputIn(List<String> values) {
            addCriterion("input in", values, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotIn(List<String> values) {
            addCriterion("input not in", values, "input");
            return (Criteria) this;
        }

        public Criteria andInputBetween(String value1, String value2) {
            addCriterion("input between", value1, value2, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotBetween(String value1, String value2) {
            addCriterion("input not between", value1, value2, "input");
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

        public Criteria andElementIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualTo(Long value) {
            addCriterion("element_id <>", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThan(Long value) {
            addCriterion("element_id >", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualTo(Long value) {
            addCriterion("element_id >=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdLessThan(Long value) {
            addCriterion("element_id <", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualTo(Long value) {
            addCriterion("element_id <=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andDeletedEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_deleted = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_deleted <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_deleted > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_deleted >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_deleted < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Long value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("project_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("project_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("project_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("project_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("project_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("project_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Long> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Long> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Long value1, Long value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Long value1, Long value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
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

        public Criteria andApplicationIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("application_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotEqualTo(Long value) {
            addCriterion("application_id <>", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("application_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThan(Long value) {
            addCriterion("application_id >", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("application_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("application_id >=", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("application_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThan(Long value) {
            addCriterion("application_id <", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("application_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanOrEqualTo(Long value) {
            addCriterion("application_id <=", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andSectionIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("section_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualTo(Long value) {
            addCriterion("section_id <>", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("section_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThan(Long value) {
            addCriterion("section_id >", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("section_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("section_id >=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("section_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThan(Long value) {
            addCriterion("section_id <", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("section_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualTo(Long value) {
            addCriterion("section_id <=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andOrderIndexEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("order_index = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotEqualTo(Long value) {
            addCriterion("order_index <>", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("order_index <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThan(Long value) {
            addCriterion("order_index >", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("order_index > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThanOrEqualTo(Long value) {
            addCriterion("order_index >=", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("order_index >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThan(Long value) {
            addCriterion("order_index <", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("order_index < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThanOrEqualTo(Long value) {
            addCriterion("order_index <=", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andLogIsNull() {
            addCriterion("log is null");
            return (Criteria) this;
        }

        public Criteria andLogIsNotNull() {
            addCriterion("log is not null");
            return (Criteria) this;
        }

        public Criteria andLogEqualTo(String value) {
            addCriterion("log =", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("log = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogNotEqualTo(String value) {
            addCriterion("log <>", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("log <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThan(String value) {
            addCriterion("log >", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("log > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualTo(String value) {
            addCriterion("log >=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("log >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThan(String value) {
            addCriterion("log <", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("log < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualTo(String value) {
            addCriterion("log <=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("log <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLike(String value) {
            addCriterion("log like", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotLike(String value) {
            addCriterion("log not like", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogIn(List<String> values) {
            addCriterion("log in", values, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotIn(List<String> values) {
            addCriterion("log not in", values, "log");
            return (Criteria) this;
        }

        public Criteria andLogBetween(String value1, String value2) {
            addCriterion("log between", value1, value2, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotBetween(String value1, String value2) {
            addCriterion("log not between", value1, value2, "log");
            return (Criteria) this;
        }

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(JsonNode value) {
            addDataCriterion("data =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("data = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(JsonNode value) {
            addDataCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("data <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(JsonNode value) {
            addDataCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("data > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(JsonNode value) {
            addDataCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("data >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThan(JsonNode value) {
            addDataCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("data < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(JsonNode value) {
            addDataCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("data <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataIn(List<JsonNode> values) {
            addDataCriterion("data in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<JsonNode> values) {
            addDataCriterion("data not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(JsonNode value1, JsonNode value2) {
            addDataCriterion("data between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(JsonNode value1, JsonNode value2) {
            addDataCriterion("data not between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color_id is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color_id is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addColorCriterion("color_id =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("color_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addColorCriterion("color_id <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("color_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addColorCriterion("color_id >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("color_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addColorCriterion("color_id >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("color_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addColorCriterion("color_id <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("color_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addColorCriterion("color_id <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("color_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addColorCriterion("color_id in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addColorCriterion("color_id not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addColorCriterion("color_id between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addColorCriterion("color_id not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("instruction_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("instruction_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("instruction_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("instruction_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("instruction_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("instruction_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("instruction_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("instruction_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("instruction_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("instruction_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("instruction_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("instruction_type_id not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionIsNull() {
            addCriterion("step_description is null");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionIsNotNull() {
            addCriterion("step_description is not null");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionEqualTo(String value) {
            addCriterion("step_description =", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("step_description = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStepDescriptionNotEqualTo(String value) {
            addCriterion("step_description <>", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("step_description <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStepDescriptionGreaterThan(String value) {
            addCriterion("step_description >", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("step_description > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStepDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("step_description >=", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("step_description >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStepDescriptionLessThan(String value) {
            addCriterion("step_description <", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("step_description < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStepDescriptionLessThanOrEqualTo(String value) {
            addCriterion("step_description <=", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("step_description <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStepDescriptionLike(String value) {
            addCriterion("step_description like", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionNotLike(String value) {
            addCriterion("step_description not like", value, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionIn(List<String> values) {
            addCriterion("step_description in", values, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionNotIn(List<String> values) {
            addCriterion("step_description not in", values, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionBetween(String value1, String value2) {
            addCriterion("step_description between", value1, value2, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionNotBetween(String value1, String value2) {
            addCriterion("step_description not between", value1, value2, "stepDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionIsNull() {
            addCriterion("expected_description is null");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionIsNotNull() {
            addCriterion("expected_description is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionEqualTo(String value) {
            addCriterion("expected_description =", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_description = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionNotEqualTo(String value) {
            addCriterion("expected_description <>", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_description <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionGreaterThan(String value) {
            addCriterion("expected_description >", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_description > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("expected_description >=", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_description >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionLessThan(String value) {
            addCriterion("expected_description <", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_description < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionLessThanOrEqualTo(String value) {
            addCriterion("expected_description <=", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_description <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionLike(String value) {
            addCriterion("expected_description like", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionNotLike(String value) {
            addCriterion("expected_description not like", value, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionIn(List<String> values) {
            addCriterion("expected_description in", values, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionNotIn(List<String> values) {
            addCriterion("expected_description not in", values, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionBetween(String value1, String value2) {
            addCriterion("expected_description between", value1, value2, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionNotBetween(String value1, String value2) {
            addCriterion("expected_description not between", value1, value2, "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdIsNull() {
            addCriterion("test_case_id is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdIsNotNull() {
            addCriterion("test_case_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdEqualTo(Long value) {
            addCriterion("test_case_id =", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualTo(Long value) {
            addCriterion("test_case_id <>", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThan(Long value) {
            addCriterion("test_case_id >", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_id >=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThan(Long value) {
            addCriterion("test_case_id <", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_id <=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdIn(List<Long> values) {
            addCriterion("test_case_id in", values, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotIn(List<Long> values) {
            addCriterion("test_case_id not in", values, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdBetween(Long value1, Long value2) {
            addCriterion("test_case_id between", value1, value2, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotBetween(Long value1, Long value2) {
            addCriterion("test_case_id not between", value1, value2, "testCaseId");
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

        public Criteria andRefTestCaseIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdNotEqualTo(Long value) {
            addCriterion("ref_test_case_id <>", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThan(Long value) {
            addCriterion("ref_test_case_id >", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_id >=", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThan(Long value) {
            addCriterion("ref_test_case_id <", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_id <=", value, "refTestCaseId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseIdLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andActionIsNull() {
            addCriterion("instruction_action_id is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("instruction_action_id is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addActionCriterion("instruction_action_id =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_action_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addActionCriterion("instruction_action_id <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_action_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addActionCriterion("instruction_action_id >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_action_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addActionCriterion("instruction_action_id >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_action_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addActionCriterion("instruction_action_id <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_action_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addActionCriterion("instruction_action_id <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("instruction_action_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addActionCriterion("instruction_action_id in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addActionCriterion("instruction_action_id not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addActionCriterion("instruction_action_id between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addActionCriterion("instruction_action_id not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andIsDriverIsNull() {
            addCriterion("is_driver is null");
            return (Criteria) this;
        }

        public Criteria andIsDriverIsNotNull() {
            addCriterion("is_driver is not null");
            return (Criteria) this;
        }

        public Criteria andIsDriverEqualTo(Boolean value) {
            addCriterion("is_driver =", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_driver = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverNotEqualTo(Boolean value) {
            addCriterion("is_driver <>", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_driver <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThan(Boolean value) {
            addCriterion("is_driver >", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_driver > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_driver >=", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_driver >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThan(Boolean value) {
            addCriterion("is_driver <", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_driver < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanOrEqualTo(Boolean value) {
            addCriterion("is_driver <=", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("is_driver <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverIn(List<Boolean> values) {
            addCriterion("is_driver in", values, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverNotIn(List<Boolean> values) {
            addCriterion("is_driver not in", values, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverBetween(Boolean value1, Boolean value2) {
            addCriterion("is_driver between", value1, value2, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_driver not between", value1, value2, "isDriver");
            return (Criteria) this;
        }

        public Criteria andParameterIsNull() {
            addCriterion("parameter is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("parameter is not null");
            return (Criteria) this;
        }

        public Criteria andParameterEqualTo(JsonNode value) {
            addParameterCriterion("parameter =", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("parameter = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(JsonNode value) {
            addParameterCriterion("parameter <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("parameter <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(JsonNode value) {
            addParameterCriterion("parameter >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("parameter > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("parameter >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(JsonNode value) {
            addParameterCriterion("parameter <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("parameter < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("parameter <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterIn(List<JsonNode> values) {
            addParameterCriterion("parameter in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<JsonNode> values) {
            addParameterCriterion("parameter not in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(JsonNode value1, JsonNode value2) {
            addParameterCriterion("parameter between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(JsonNode value1, JsonNode value2) {
            addParameterCriterion("parameter not between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIsNull() {
            addCriterion("copy_from_id is null");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIsNotNull() {
            addCriterion("copy_from_id is not null");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdEqualTo(Long value) {
            addCriterion("copy_from_id =", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("copy_from_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualTo(Long value) {
            addCriterion("copy_from_id <>", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("copy_from_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThan(Long value) {
            addCriterion("copy_from_id >", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("copy_from_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("copy_from_id >=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("copy_from_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThan(Long value) {
            addCriterion("copy_from_id <", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("copy_from_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualTo(Long value) {
            addCriterion("copy_from_id <=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("copy_from_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIn(List<Long> values) {
            addCriterion("copy_from_id in", values, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotIn(List<Long> values) {
            addCriterion("copy_from_id not in", values, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdBetween(Long value1, Long value2) {
            addCriterion("copy_from_id between", value1, value2, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotBetween(Long value1, Long value2) {
            addCriterion("copy_from_id not between", value1, value2, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andExpectedValueIsNull() {
            addCriterion("expected_value is null");
            return (Criteria) this;
        }

        public Criteria andExpectedValueIsNotNull() {
            addCriterion("expected_value is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedValueEqualTo(String value) {
            addCriterion("expected_value =", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueNotEqualTo(String value) {
            addCriterion("expected_value <>", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThan(String value) {
            addCriterion("expected_value >", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThanOrEqualTo(String value) {
            addCriterion("expected_value >=", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThan(String value) {
            addCriterion("expected_value <", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThanOrEqualTo(String value) {
            addCriterion("expected_value <=", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("expected_value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueLike(String value) {
            addCriterion("expected_value like", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueNotLike(String value) {
            addCriterion("expected_value not like", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueIn(List<String> values) {
            addCriterion("expected_value in", values, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueNotIn(List<String> values) {
            addCriterion("expected_value not in", values, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueBetween(String value1, String value2) {
            addCriterion("expected_value between", value1, value2, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueNotBetween(String value1, String value2) {
            addCriterion("expected_value not between", value1, value2, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andResourceMd5IsNull() {
            addCriterion("resource_md5 is null");
            return (Criteria) this;
        }

        public Criteria andResourceMd5IsNotNull() {
            addCriterion("resource_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andResourceMd5EqualTo(String value) {
            addCriterion("resource_md5 =", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5EqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("resource_md5 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResourceMd5NotEqualTo(String value) {
            addCriterion("resource_md5 <>", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5NotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("resource_md5 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResourceMd5GreaterThan(String value) {
            addCriterion("resource_md5 >", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5GreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("resource_md5 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResourceMd5GreaterThanOrEqualTo(String value) {
            addCriterion("resource_md5 >=", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5GreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("resource_md5 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResourceMd5LessThan(String value) {
            addCriterion("resource_md5 <", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5LessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("resource_md5 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResourceMd5LessThanOrEqualTo(String value) {
            addCriterion("resource_md5 <=", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5LessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("resource_md5 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResourceMd5Like(String value) {
            addCriterion("resource_md5 like", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5NotLike(String value) {
            addCriterion("resource_md5 not like", value, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5In(List<String> values) {
            addCriterion("resource_md5 in", values, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5NotIn(List<String> values) {
            addCriterion("resource_md5 not in", values, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5Between(String value1, String value2) {
            addCriterion("resource_md5 between", value1, value2, "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andResourceMd5NotBetween(String value1, String value2) {
            addCriterion("resource_md5 not between", value1, value2, "resourceMd5");
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

        public Criteria andElementTypeEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeNotEqualTo(String value) {
            addElementTypeCriterion("element_type_id <>", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThan(String value) {
            addElementTypeCriterion("element_type_id >", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanOrEqualTo(String value) {
            addElementTypeCriterion("element_type_id >=", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThan(String value) {
            addElementTypeCriterion("element_type_id <", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("element_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanOrEqualTo(String value) {
            addElementTypeCriterion("element_type_id <=", value, "elementType");
            return (Criteria) this;
        }

        public Criteria andElementTypeLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andDriverTypeEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id <>", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThan(String value) {
            addDriverTypeCriterion("driver_type_id >", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanOrEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id >=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThan(String value) {
            addDriverTypeCriterion("driver_type_id <", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanOrEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id <=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andRefTestCaseOverwriteIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id <>", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThan(Long value) {
            addCriterion("ref_test_case_overwrite_id >", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id >=", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThan(Long value) {
            addCriterion("ref_test_case_overwrite_id <", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id <=", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andTestCaseShareFolderIdEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdNotEqualTo(Long value) {
            addCriterion("test_case_share_folder_id <>", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThan(Long value) {
            addCriterion("test_case_share_folder_id >", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_share_folder_id >=", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThan(Long value) {
            addCriterion("test_case_share_folder_id <", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("test_case_share_folder_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_share_folder_id <=", value, "testCaseShareFolderId");
            return (Criteria) this;
        }

        public Criteria andTestCaseShareFolderIdLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andDriverAliasEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_alias = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasNotEqualTo(String value) {
            addCriterion("driver_alias <>", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_alias <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThan(String value) {
            addCriterion("driver_alias >", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_alias > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThanOrEqualTo(String value) {
            addCriterion("driver_alias >=", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_alias >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThan(String value) {
            addCriterion("driver_alias <", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("driver_alias < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThanOrEqualTo(String value) {
            addCriterion("driver_alias <=", value, "driverAlias");
            return (Criteria) this;
        }

        public Criteria andDriverAliasLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andTargetIsNull() {
            addCriterion("target is null");
            return (Criteria) this;
        }

        public Criteria andTargetIsNotNull() {
            addCriterion("target is not null");
            return (Criteria) this;
        }

        public Criteria andTargetEqualTo(String value) {
            addCriterion("target =", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("target = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetNotEqualTo(String value) {
            addCriterion("target <>", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("target <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThan(String value) {
            addCriterion("target >", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("target > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanOrEqualTo(String value) {
            addCriterion("target >=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("target >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetLessThan(String value) {
            addCriterion("target <", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("target < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetLessThanOrEqualTo(String value) {
            addCriterion("target <=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("target <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetLike(String value) {
            addCriterion("target like", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotLike(String value) {
            addCriterion("target not like", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetIn(List<String> values) {
            addCriterion("target in", values, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotIn(List<String> values) {
            addCriterion("target not in", values, "target");
            return (Criteria) this;
        }

        public Criteria andTargetBetween(String value1, String value2) {
            addCriterion("target between", value1, value2, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotBetween(String value1, String value2) {
            addCriterion("target not between", value1, value2, "target");
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

        public Criteria andRefTestCaseOverwriteNameEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameNotEqualTo(String value) {
            addCriterion("ref_test_case_overwrite_name <>", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameNotEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThan(String value) {
            addCriterion("ref_test_case_overwrite_name >", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThanOrEqualTo(String value) {
            addCriterion("ref_test_case_overwrite_name >=", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameGreaterThanOrEqualToColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThan(String value) {
            addCriterion("ref_test_case_overwrite_name <", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThanColumn(Instruction.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThanOrEqualTo(String value) {
            addCriterion("ref_test_case_overwrite_name <=", value, "refTestCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteNameLessThanOrEqualToColumn(Instruction.Column column) {
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

        public Criteria andCommentLikeInsensitive(String value) {
            addCriterion("upper(comment) like", value.toUpperCase(), "comment");
            return (Criteria) this;
        }

        public Criteria andInputLikeInsensitive(String value) {
            addCriterion("upper(input) like", value.toUpperCase(), "input");
            return (Criteria) this;
        }

        public Criteria andLogLikeInsensitive(String value) {
            addCriterion("upper(log) like", value.toUpperCase(), "log");
            return (Criteria) this;
        }

        public Criteria andStepDescriptionLikeInsensitive(String value) {
            addCriterion("upper(step_description) like", value.toUpperCase(), "stepDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedDescriptionLikeInsensitive(String value) {
            addCriterion("upper(expected_description) like", value.toUpperCase(), "expectedDescription");
            return (Criteria) this;
        }

        public Criteria andExpectedValueLikeInsensitive(String value) {
            addCriterion("upper(expected_value) like", value.toUpperCase(), "expectedValue");
            return (Criteria) this;
        }

        public Criteria andResourceMd5LikeInsensitive(String value) {
            addCriterion("upper(resource_md5) like", value.toUpperCase(), "resourceMd5");
            return (Criteria) this;
        }

        public Criteria andDriverAliasLikeInsensitive(String value) {
            addCriterion("upper(driver_alias) like", value.toUpperCase(), "driverAlias");
            return (Criteria) this;
        }

        public Criteria andTargetLikeInsensitive(String value) {
            addCriterion("upper(target) like", value.toUpperCase(), "target");
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
        private InstructionExample example;

        protected Criteria(InstructionExample example) {
            super();
            this.example = example;
        }

        public InstructionExample example() {
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
            return deleted ? andDeletedEqualTo(Instruction.Deleted.IS_DELETED.value()) : andDeletedNotEqualTo(Instruction.Deleted.IS_DELETED.value());
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
        void example(com.meowlomo.atm.core.model.InstructionExample example);
    }
}