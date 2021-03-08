package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class InstructionResultExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructionResultExample() {
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

    public InstructionResultExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public InstructionResultExample orderBy(String... orderByClauses) {
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
        InstructionResultExample example = new InstructionResultExample();
        return example.createCriteria();
    }

    public InstructionResultExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public InstructionResultExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> statusCriteria;

        protected List<Criterion> instructionCriteria;

        protected List<Criterion> dataCriteria;

        protected List<Criterion> runTypeCriteria;

        protected List<Criterion> inputParameterCriteria;

        protected List<Criterion> outputParameterCriteria;

        protected List<Criterion> instructionOptionsCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
            instructionCriteria = new ArrayList<Criterion>();
            dataCriteria = new ArrayList<Criterion>();
            runTypeCriteria = new ArrayList<Criterion>();
            inputParameterCriteria = new ArrayList<Criterion>();
            outputParameterCriteria = new ArrayList<Criterion>();
            instructionOptionsCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getStatusCriteria() {
            return statusCriteria;
        }

        protected void addStatusCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            statusCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        protected void addStatusCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            statusCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getInstructionCriteria() {
            return instructionCriteria;
        }

        protected void addInstructionCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            instructionCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addInstructionCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            instructionCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
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

        public List<Criterion> getRunTypeCriteria() {
            return runTypeCriteria;
        }

        protected void addRunTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            runTypeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.RunTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addRunTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            runTypeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.RunTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getInputParameterCriteria() {
            return inputParameterCriteria;
        }

        protected void addInputParameterCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            inputParameterCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addInputParameterCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            inputParameterCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getOutputParameterCriteria() {
            return outputParameterCriteria;
        }

        protected void addOutputParameterCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            outputParameterCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addOutputParameterCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            outputParameterCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || statusCriteria.size() > 0 || instructionCriteria.size() > 0 || dataCriteria.size() > 0 || runTypeCriteria.size() > 0 || inputParameterCriteria.size() > 0 || outputParameterCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(statusCriteria);
                allCriteria.addAll(instructionCriteria);
                allCriteria.addAll(dataCriteria);
                allCriteria.addAll(runTypeCriteria);
                allCriteria.addAll(inputParameterCriteria);
                allCriteria.addAll(outputParameterCriteria);
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

        public Criteria andIdEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andActionIsNull() {
            addCriterion("action is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("action is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("action =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("action = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("action <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("action <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("action >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("action > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("action >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("action >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("action <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("action < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("action <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("action <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("action like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("action not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("action in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("action not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("action between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("action not between", value1, value2, "action");
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

        public Criteria andCreatedAtEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andStatusIsNull() {
            addCriterion("status_id is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addStatusCriterion("status_id =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addStatusCriterion("status_id <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addStatusCriterion("status_id >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addStatusCriterion("status_id >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addStatusCriterion("status_id <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addStatusCriterion("status_id <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("status_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addStatusCriterion("status_id in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addStatusCriterion("status_id not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addStatusCriterion("status_id between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addStatusCriterion("status_id not between", value1, value2, "status");
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

        public Criteria andLogEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("log = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogNotEqualTo(String value) {
            addCriterion("log <>", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("log <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThan(String value) {
            addCriterion("log >", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("log > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualTo(String value) {
            addCriterion("log >=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("log >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThan(String value) {
            addCriterion("log <", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("log < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualTo(String value) {
            addCriterion("log <=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andFinishedIsNull() {
            addCriterion("is_finished is null");
            return (Criteria) this;
        }

        public Criteria andFinishedIsNotNull() {
            addCriterion("is_finished is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedEqualTo(Boolean value) {
            addCriterion("is_finished =", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_finished = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("is_finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_finished <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("is_finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_finished > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_finished >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("is_finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_finished < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_finished <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedIn(List<Boolean> values) {
            addCriterion("is_finished in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotIn(List<Boolean> values) {
            addCriterion("is_finished not in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_finished between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_finished not between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNull() {
            addCriterion("instruction is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNotNull() {
            addCriterion("instruction is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionEqualTo(JsonNode value) {
            addInstructionCriterion("instruction =", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionNotEqualTo(JsonNode value) {
            addInstructionCriterion("instruction <>", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThan(JsonNode value) {
            addInstructionCriterion("instruction >", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanOrEqualTo(JsonNode value) {
            addInstructionCriterion("instruction >=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionLessThan(JsonNode value) {
            addInstructionCriterion("instruction <", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanOrEqualTo(JsonNode value) {
            addInstructionCriterion("instruction <=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIn(List<JsonNode> values) {
            addInstructionCriterion("instruction in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotIn(List<JsonNode> values) {
            addInstructionCriterion("instruction not in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionBetween(JsonNode value1, JsonNode value2) {
            addInstructionCriterion("instruction between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotBetween(JsonNode value1, JsonNode value2) {
            addInstructionCriterion("instruction not between", value1, value2, "instruction");
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

        public Criteria andDataEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("data = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(JsonNode value) {
            addDataCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("data <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(JsonNode value) {
            addDataCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("data > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(JsonNode value) {
            addDataCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("data >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThan(JsonNode value) {
            addDataCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("data < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(JsonNode value) {
            addDataCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andRunIdIsNull() {
            addCriterion("run_id is null");
            return (Criteria) this;
        }

        public Criteria andRunIdIsNotNull() {
            addCriterion("run_id is not null");
            return (Criteria) this;
        }

        public Criteria andRunIdEqualTo(Long value) {
            addCriterion("run_id =", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualTo(Long value) {
            addCriterion("run_id <>", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThan(Long value) {
            addCriterion("run_id >", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_id >=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThan(Long value) {
            addCriterion("run_id <", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualTo(Long value) {
            addCriterion("run_id <=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdIn(List<Long> values) {
            addCriterion("run_id in", values, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdNotIn(List<Long> values) {
            addCriterion("run_id not in", values, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdBetween(Long value1, Long value2) {
            addCriterion("run_id between", value1, value2, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdNotBetween(Long value1, Long value2) {
            addCriterion("run_id not between", value1, value2, "runId");
            return (Criteria) this;
        }

        public Criteria andRunTypeIsNull() {
            addCriterion("run_type_id is null");
            return (Criteria) this;
        }

        public Criteria andRunTypeIsNotNull() {
            addCriterion("run_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andRunTypeEqualTo(String value) {
            addRunTypeCriterion("run_type_id =", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualTo(String value) {
            addRunTypeCriterion("run_type_id <>", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThan(String value) {
            addRunTypeCriterion("run_type_id >", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id >=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThan(String value) {
            addRunTypeCriterion("run_type_id <", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id <=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("run_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeIn(List<String> values) {
            addRunTypeCriterion("run_type_id in", values, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeNotIn(List<String> values) {
            addRunTypeCriterion("run_type_id not in", values, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeBetween(String value1, String value2) {
            addRunTypeCriterion("run_type_id between", value1, value2, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeNotBetween(String value1, String value2) {
            addRunTypeCriterion("run_type_id not between", value1, value2, "runType");
            return (Criteria) this;
        }

        public Criteria andStartAtIsNull() {
            addCriterion("start_at is null");
            return (Criteria) this;
        }

        public Criteria andStartAtIsNotNull() {
            addCriterion("start_at is not null");
            return (Criteria) this;
        }

        public Criteria andStartAtEqualTo(Date value) {
            addCriterion("start_at =", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("start_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtNotEqualTo(Date value) {
            addCriterion("start_at <>", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("start_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThan(Date value) {
            addCriterion("start_at >", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("start_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanOrEqualTo(Date value) {
            addCriterion("start_at >=", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("start_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtLessThan(Date value) {
            addCriterion("start_at <", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("start_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanOrEqualTo(Date value) {
            addCriterion("start_at <=", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("start_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtIn(List<Date> values) {
            addCriterion("start_at in", values, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtNotIn(List<Date> values) {
            addCriterion("start_at not in", values, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtBetween(Date value1, Date value2) {
            addCriterion("start_at between", value1, value2, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtNotBetween(Date value1, Date value2) {
            addCriterion("start_at not between", value1, value2, "startAt");
            return (Criteria) this;
        }

        public Criteria andEndAtIsNull() {
            addCriterion("end_at is null");
            return (Criteria) this;
        }

        public Criteria andEndAtIsNotNull() {
            addCriterion("end_at is not null");
            return (Criteria) this;
        }

        public Criteria andEndAtEqualTo(Date value) {
            addCriterion("end_at =", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("end_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtNotEqualTo(Date value) {
            addCriterion("end_at <>", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("end_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThan(Date value) {
            addCriterion("end_at >", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("end_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("end_at >=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("end_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtLessThan(Date value) {
            addCriterion("end_at <", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("end_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanOrEqualTo(Date value) {
            addCriterion("end_at <=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("end_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtIn(List<Date> values) {
            addCriterion("end_at in", values, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotIn(List<Date> values) {
            addCriterion("end_at not in", values, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtBetween(Date value1, Date value2) {
            addCriterion("end_at between", value1, value2, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotBetween(Date value1, Date value2) {
            addCriterion("end_at not between", value1, value2, "endAt");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexIsNull() {
            addCriterion("logical_order_index is null");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexIsNotNull() {
            addCriterion("logical_order_index is not null");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexEqualTo(String value) {
            addCriterion("logical_order_index =", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("logical_order_index = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexNotEqualTo(String value) {
            addCriterion("logical_order_index <>", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("logical_order_index <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexGreaterThan(String value) {
            addCriterion("logical_order_index >", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("logical_order_index > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexGreaterThanOrEqualTo(String value) {
            addCriterion("logical_order_index >=", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("logical_order_index >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexLessThan(String value) {
            addCriterion("logical_order_index <", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("logical_order_index < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexLessThanOrEqualTo(String value) {
            addCriterion("logical_order_index <=", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("logical_order_index <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexLike(String value) {
            addCriterion("logical_order_index like", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexNotLike(String value) {
            addCriterion("logical_order_index not like", value, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexIn(List<String> values) {
            addCriterion("logical_order_index in", values, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexNotIn(List<String> values) {
            addCriterion("logical_order_index not in", values, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexBetween(String value1, String value2) {
            addCriterion("logical_order_index between", value1, value2, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexNotBetween(String value1, String value2) {
            addCriterion("logical_order_index not between", value1, value2, "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andInputDataIsNull() {
            addCriterion("input_data is null");
            return (Criteria) this;
        }

        public Criteria andInputDataIsNotNull() {
            addCriterion("input_data is not null");
            return (Criteria) this;
        }

        public Criteria andInputDataEqualTo(String value) {
            addCriterion("input_data =", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_data = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputDataNotEqualTo(String value) {
            addCriterion("input_data <>", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_data <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputDataGreaterThan(String value) {
            addCriterion("input_data >", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_data > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputDataGreaterThanOrEqualTo(String value) {
            addCriterion("input_data >=", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_data >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputDataLessThan(String value) {
            addCriterion("input_data <", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_data < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputDataLessThanOrEqualTo(String value) {
            addCriterion("input_data <=", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_data <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputDataLike(String value) {
            addCriterion("input_data like", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataNotLike(String value) {
            addCriterion("input_data not like", value, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataIn(List<String> values) {
            addCriterion("input_data in", values, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataNotIn(List<String> values) {
            addCriterion("input_data not in", values, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataBetween(String value1, String value2) {
            addCriterion("input_data between", value1, value2, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputDataNotBetween(String value1, String value2) {
            addCriterion("input_data not between", value1, value2, "inputData");
            return (Criteria) this;
        }

        public Criteria andInputTypeIsNull() {
            addCriterion("input_type is null");
            return (Criteria) this;
        }

        public Criteria andInputTypeIsNotNull() {
            addCriterion("input_type is not null");
            return (Criteria) this;
        }

        public Criteria andInputTypeEqualTo(String value) {
            addCriterion("input_type =", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_type = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputTypeNotEqualTo(String value) {
            addCriterion("input_type <>", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_type <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThan(String value) {
            addCriterion("input_type >", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_type > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThanOrEqualTo(String value) {
            addCriterion("input_type >=", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_type >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThan(String value) {
            addCriterion("input_type <", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_type < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThanOrEqualTo(String value) {
            addCriterion("input_type <=", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_type <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputTypeLike(String value) {
            addCriterion("input_type like", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotLike(String value) {
            addCriterion("input_type not like", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeIn(List<String> values) {
            addCriterion("input_type in", values, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotIn(List<String> values) {
            addCriterion("input_type not in", values, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeBetween(String value1, String value2) {
            addCriterion("input_type between", value1, value2, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotBetween(String value1, String value2) {
            addCriterion("input_type not between", value1, value2, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputParameterIsNull() {
            addCriterion("input_parameter is null");
            return (Criteria) this;
        }

        public Criteria andInputParameterIsNotNull() {
            addCriterion("input_parameter is not null");
            return (Criteria) this;
        }

        public Criteria andInputParameterEqualTo(JsonNode value) {
            addInputParameterCriterion("input_parameter =", value, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_parameter = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputParameterNotEqualTo(JsonNode value) {
            addInputParameterCriterion("input_parameter <>", value, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_parameter <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputParameterGreaterThan(JsonNode value) {
            addInputParameterCriterion("input_parameter >", value, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_parameter > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputParameterGreaterThanOrEqualTo(JsonNode value) {
            addInputParameterCriterion("input_parameter >=", value, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_parameter >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputParameterLessThan(JsonNode value) {
            addInputParameterCriterion("input_parameter <", value, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_parameter < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputParameterLessThanOrEqualTo(JsonNode value) {
            addInputParameterCriterion("input_parameter <=", value, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("input_parameter <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputParameterIn(List<JsonNode> values) {
            addInputParameterCriterion("input_parameter in", values, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterNotIn(List<JsonNode> values) {
            addInputParameterCriterion("input_parameter not in", values, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterBetween(JsonNode value1, JsonNode value2) {
            addInputParameterCriterion("input_parameter between", value1, value2, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andInputParameterNotBetween(JsonNode value1, JsonNode value2) {
            addInputParameterCriterion("input_parameter not between", value1, value2, "inputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputDataIsNull() {
            addCriterion("output_data is null");
            return (Criteria) this;
        }

        public Criteria andOutputDataIsNotNull() {
            addCriterion("output_data is not null");
            return (Criteria) this;
        }

        public Criteria andOutputDataEqualTo(String value) {
            addCriterion("output_data =", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_data = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputDataNotEqualTo(String value) {
            addCriterion("output_data <>", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_data <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputDataGreaterThan(String value) {
            addCriterion("output_data >", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_data > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputDataGreaterThanOrEqualTo(String value) {
            addCriterion("output_data >=", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_data >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputDataLessThan(String value) {
            addCriterion("output_data <", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_data < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputDataLessThanOrEqualTo(String value) {
            addCriterion("output_data <=", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_data <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputDataLike(String value) {
            addCriterion("output_data like", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataNotLike(String value) {
            addCriterion("output_data not like", value, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataIn(List<String> values) {
            addCriterion("output_data in", values, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataNotIn(List<String> values) {
            addCriterion("output_data not in", values, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataBetween(String value1, String value2) {
            addCriterion("output_data between", value1, value2, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputDataNotBetween(String value1, String value2) {
            addCriterion("output_data not between", value1, value2, "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputTypeIsNull() {
            addCriterion("output_type is null");
            return (Criteria) this;
        }

        public Criteria andOutputTypeIsNotNull() {
            addCriterion("output_type is not null");
            return (Criteria) this;
        }

        public Criteria andOutputTypeEqualTo(String value) {
            addCriterion("output_type =", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_type = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotEqualTo(String value) {
            addCriterion("output_type <>", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_type <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputTypeGreaterThan(String value) {
            addCriterion("output_type >", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_type > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputTypeGreaterThanOrEqualTo(String value) {
            addCriterion("output_type >=", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_type >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputTypeLessThan(String value) {
            addCriterion("output_type <", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_type < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputTypeLessThanOrEqualTo(String value) {
            addCriterion("output_type <=", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_type <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputTypeLike(String value) {
            addCriterion("output_type like", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotLike(String value) {
            addCriterion("output_type not like", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeIn(List<String> values) {
            addCriterion("output_type in", values, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotIn(List<String> values) {
            addCriterion("output_type not in", values, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeBetween(String value1, String value2) {
            addCriterion("output_type between", value1, value2, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotBetween(String value1, String value2) {
            addCriterion("output_type not between", value1, value2, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputParameterIsNull() {
            addCriterion("output_parameter is null");
            return (Criteria) this;
        }

        public Criteria andOutputParameterIsNotNull() {
            addCriterion("output_parameter is not null");
            return (Criteria) this;
        }

        public Criteria andOutputParameterEqualTo(JsonNode value) {
            addOutputParameterCriterion("output_parameter =", value, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_parameter = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputParameterNotEqualTo(JsonNode value) {
            addOutputParameterCriterion("output_parameter <>", value, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_parameter <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputParameterGreaterThan(JsonNode value) {
            addOutputParameterCriterion("output_parameter >", value, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_parameter > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputParameterGreaterThanOrEqualTo(JsonNode value) {
            addOutputParameterCriterion("output_parameter >=", value, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_parameter >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputParameterLessThan(JsonNode value) {
            addOutputParameterCriterion("output_parameter <", value, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_parameter < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputParameterLessThanOrEqualTo(JsonNode value) {
            addOutputParameterCriterion("output_parameter <=", value, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("output_parameter <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOutputParameterIn(List<JsonNode> values) {
            addOutputParameterCriterion("output_parameter in", values, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterNotIn(List<JsonNode> values) {
            addOutputParameterCriterion("output_parameter not in", values, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterBetween(JsonNode value1, JsonNode value2) {
            addOutputParameterCriterion("output_parameter between", value1, value2, "outputParameter");
            return (Criteria) this;
        }

        public Criteria andOutputParameterNotBetween(JsonNode value1, JsonNode value2) {
            addOutputParameterCriterion("output_parameter not between", value1, value2, "outputParameter");
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

        public Criteria andExpectedValueEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("expected_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueNotEqualTo(String value) {
            addCriterion("expected_value <>", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("expected_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThan(String value) {
            addCriterion("expected_value >", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("expected_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThanOrEqualTo(String value) {
            addCriterion("expected_value >=", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("expected_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThan(String value) {
            addCriterion("expected_value <", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("expected_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThanOrEqualTo(String value) {
            addCriterion("expected_value <=", value, "expectedValue");
            return (Criteria) this;
        }

        public Criteria andExpectedValueLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andReturnValueIsNull() {
            addCriterion("return_value is null");
            return (Criteria) this;
        }

        public Criteria andReturnValueIsNotNull() {
            addCriterion("return_value is not null");
            return (Criteria) this;
        }

        public Criteria andReturnValueEqualTo(String value) {
            addCriterion("return_value =", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("return_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andReturnValueNotEqualTo(String value) {
            addCriterion("return_value <>", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("return_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andReturnValueGreaterThan(String value) {
            addCriterion("return_value >", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("return_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andReturnValueGreaterThanOrEqualTo(String value) {
            addCriterion("return_value >=", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("return_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andReturnValueLessThan(String value) {
            addCriterion("return_value <", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("return_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andReturnValueLessThanOrEqualTo(String value) {
            addCriterion("return_value <=", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("return_value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andReturnValueLike(String value) {
            addCriterion("return_value like", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueNotLike(String value) {
            addCriterion("return_value not like", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueIn(List<String> values) {
            addCriterion("return_value in", values, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueNotIn(List<String> values) {
            addCriterion("return_value not in", values, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueBetween(String value1, String value2) {
            addCriterion("return_value between", value1, value2, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueNotBetween(String value1, String value2) {
            addCriterion("return_value not between", value1, value2, "returnValue");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteIsNull() {
            addCriterion("is_overwrite is null");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteIsNotNull() {
            addCriterion("is_overwrite is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteEqualTo(Boolean value) {
            addCriterion("is_overwrite =", value, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_overwrite = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsOverwriteNotEqualTo(Boolean value) {
            addCriterion("is_overwrite <>", value, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_overwrite <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsOverwriteGreaterThan(Boolean value) {
            addCriterion("is_overwrite >", value, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_overwrite > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsOverwriteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_overwrite >=", value, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_overwrite >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsOverwriteLessThan(Boolean value) {
            addCriterion("is_overwrite <", value, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_overwrite < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsOverwriteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_overwrite <=", value, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("is_overwrite <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsOverwriteIn(List<Boolean> values) {
            addCriterion("is_overwrite in", values, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteNotIn(List<Boolean> values) {
            addCriterion("is_overwrite not in", values, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_overwrite between", value1, value2, "isOverwrite");
            return (Criteria) this;
        }

        public Criteria andIsOverwriteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_overwrite not between", value1, value2, "isOverwrite");
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

        public Criteria andTargetEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("target = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetNotEqualTo(String value) {
            addCriterion("target <>", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("target <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThan(String value) {
            addCriterion("target >", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("target > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanOrEqualTo(String value) {
            addCriterion("target >=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("target >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetLessThan(String value) {
            addCriterion("target <", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("target < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetLessThanOrEqualTo(String value) {
            addCriterion("target <=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andInstructionOptionLogIsNull() {
            addCriterion("instruction_option_log is null");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogIsNotNull() {
            addCriterion("instruction_option_log is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogEqualTo(String value) {
            addCriterion("instruction_option_log =", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_option_log = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogNotEqualTo(String value) {
            addCriterion("instruction_option_log <>", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_option_log <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogGreaterThan(String value) {
            addCriterion("instruction_option_log >", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_option_log > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogGreaterThanOrEqualTo(String value) {
            addCriterion("instruction_option_log >=", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_option_log >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogLessThan(String value) {
            addCriterion("instruction_option_log <", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_option_log < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogLessThanOrEqualTo(String value) {
            addCriterion("instruction_option_log <=", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_option_log <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogLike(String value) {
            addCriterion("instruction_option_log like", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogNotLike(String value) {
            addCriterion("instruction_option_log not like", value, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogIn(List<String> values) {
            addCriterion("instruction_option_log in", values, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogNotIn(List<String> values) {
            addCriterion("instruction_option_log not in", values, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogBetween(String value1, String value2) {
            addCriterion("instruction_option_log between", value1, value2, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogNotBetween(String value1, String value2) {
            addCriterion("instruction_option_log not between", value1, value2, "instructionOptionLog");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenIsNull() {
            addCriterion("result_overwritten is null");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenIsNotNull() {
            addCriterion("result_overwritten is not null");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenEqualTo(Integer value) {
            addCriterion("result_overwritten =", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("result_overwritten = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenNotEqualTo(Integer value) {
            addCriterion("result_overwritten <>", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("result_overwritten <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThan(Integer value) {
            addCriterion("result_overwritten >", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("result_overwritten > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThanOrEqualTo(Integer value) {
            addCriterion("result_overwritten >=", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("result_overwritten >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThan(Integer value) {
            addCriterion("result_overwritten <", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("result_overwritten < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThanOrEqualTo(Integer value) {
            addCriterion("result_overwritten <=", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("result_overwritten <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenIn(List<Integer> values) {
            addCriterion("result_overwritten in", values, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenNotIn(List<Integer> values) {
            addCriterion("result_overwritten not in", values, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenBetween(Integer value1, Integer value2) {
            addCriterion("result_overwritten between", value1, value2, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenNotBetween(Integer value1, Integer value2) {
            addCriterion("result_overwritten not between", value1, value2, "resultOverwritten");
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

        public Criteria andInstructionIdEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotEqualTo(Long value) {
            addCriterion("instruction_id <>", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThan(Long value) {
            addCriterion("instruction_id >", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("instruction_id >=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThan(Long value) {
            addCriterion("instruction_id <", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("instruction_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanOrEqualTo(Long value) {
            addCriterion("instruction_id <=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andCommentEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("comment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("comment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("comment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualToColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("comment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanColumn(InstructionResult.Column column) {
            addCriterion(new StringBuilder("comment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualToColumn(InstructionResult.Column column) {
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

        public Criteria andActionLikeInsensitive(String value) {
            addCriterion("upper(action) like", value.toUpperCase(), "action");
            return (Criteria) this;
        }

        public Criteria andLogLikeInsensitive(String value) {
            addCriterion("upper(log) like", value.toUpperCase(), "log");
            return (Criteria) this;
        }

        public Criteria andLogicalOrderIndexLikeInsensitive(String value) {
            addCriterion("upper(logical_order_index) like", value.toUpperCase(), "logicalOrderIndex");
            return (Criteria) this;
        }

        public Criteria andInputDataLikeInsensitive(String value) {
            addCriterion("upper(input_data) like", value.toUpperCase(), "inputData");
            return (Criteria) this;
        }

        public Criteria andInputTypeLikeInsensitive(String value) {
            addCriterion("upper(input_type) like", value.toUpperCase(), "inputType");
            return (Criteria) this;
        }

        public Criteria andOutputDataLikeInsensitive(String value) {
            addCriterion("upper(output_data) like", value.toUpperCase(), "outputData");
            return (Criteria) this;
        }

        public Criteria andOutputTypeLikeInsensitive(String value) {
            addCriterion("upper(output_type) like", value.toUpperCase(), "outputType");
            return (Criteria) this;
        }

        public Criteria andExpectedValueLikeInsensitive(String value) {
            addCriterion("upper(expected_value) like", value.toUpperCase(), "expectedValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueLikeInsensitive(String value) {
            addCriterion("upper(return_value) like", value.toUpperCase(), "returnValue");
            return (Criteria) this;
        }

        public Criteria andTargetLikeInsensitive(String value) {
            addCriterion("upper(target) like", value.toUpperCase(), "target");
            return (Criteria) this;
        }

        public Criteria andInstructionOptionLogLikeInsensitive(String value) {
            addCriterion("upper(instruction_option_log) like", value.toUpperCase(), "instructionOptionLog");
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
        private InstructionResultExample example;

        protected Criteria(InstructionResultExample example) {
            super();
            this.example = example;
        }

        public InstructionResultExample example() {
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
        void example(com.meowlomo.atm.core.model.InstructionResultExample example);
    }
}