package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

public class RunExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunExample() {
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

    public RunExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunExample orderBy(String... orderByClauses) {
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
        RunExample example = new RunExample();
        return example.createCriteria();
    }

    public RunExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> testCaseCriteria;

        protected List<Criterion> parameterCriteria;

        protected List<Criterion> typeCriteria;

        protected List<Criterion> groupCriteria;

        protected List<Criterion> driverPackCriteria;

        protected List<Criterion> testCaseOverwriteCriteria;

        protected List<Criterion> driversCriteria;

        protected List<Criterion> uuidCriteria;

        protected List<Criterion> testCaseUuidCriteria;

        protected List<Criterion> systemRequirementsCriteria;

        protected List<Criterion> systemRequirementPackCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
            testCaseCriteria = new ArrayList<Criterion>();
            parameterCriteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            groupCriteria = new ArrayList<Criterion>();
            driverPackCriteria = new ArrayList<Criterion>();
            testCaseOverwriteCriteria = new ArrayList<Criterion>();
            driversCriteria = new ArrayList<Criterion>();
            uuidCriteria = new ArrayList<Criterion>();
            testCaseUuidCriteria = new ArrayList<Criterion>();
            systemRequirementsCriteria = new ArrayList<Criterion>();
            systemRequirementPackCriteria = new ArrayList<Criterion>();
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

        public List<Criterion> getTestCaseCriteria() {
            return testCaseCriteria;
        }

        protected void addTestCaseCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            testCaseCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addTestCaseCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            testCaseCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
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

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.RunTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.RunTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getGroupCriteria() {
            return groupCriteria;
        }

        protected void addGroupCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            groupCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.GroupTypeHandler"));
            allCriteria = null;
        }

        protected void addGroupCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            groupCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.GroupTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getDriverPackCriteria() {
            return driverPackCriteria;
        }

        protected void addDriverPackCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            driverPackCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addDriverPackCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            driverPackCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTestCaseOverwriteCriteria() {
            return testCaseOverwriteCriteria;
        }

        protected void addTestCaseOverwriteCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            testCaseOverwriteCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addTestCaseOverwriteCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            testCaseOverwriteCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getDriversCriteria() {
            return driversCriteria;
        }

        protected void addDriversCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            driversCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addDriversCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            driversCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getUuidCriteria() {
            return uuidCriteria;
        }

        protected void addUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            uuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            uuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTestCaseUuidCriteria() {
            return testCaseUuidCriteria;
        }

        protected void addTestCaseUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            testCaseUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addTestCaseUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            testCaseUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getSystemRequirementsCriteria() {
            return systemRequirementsCriteria;
        }

        protected void addSystemRequirementsCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            systemRequirementsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addSystemRequirementsCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            systemRequirementsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getSystemRequirementPackCriteria() {
            return systemRequirementPackCriteria;
        }

        protected void addSystemRequirementPackCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            systemRequirementPackCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addSystemRequirementPackCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            systemRequirementPackCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || statusCriteria.size() > 0 || testCaseCriteria.size() > 0 || parameterCriteria.size() > 0 || typeCriteria.size() > 0
                    || groupCriteria.size() > 0 || driverPackCriteria.size() > 0 || testCaseOverwriteCriteria.size() > 0 || driversCriteria.size() > 0 || uuidCriteria.size() > 0
                    || testCaseUuidCriteria.size() > 0 || systemRequirementsCriteria.size() > 0 || systemRequirementPackCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(statusCriteria);
                allCriteria.addAll(testCaseCriteria);
                allCriteria.addAll(parameterCriteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(groupCriteria);
                allCriteria.addAll(driverPackCriteria);
                allCriteria.addAll(testCaseOverwriteCriteria);
                allCriteria.addAll(driversCriteria);
                allCriteria.addAll(uuidCriteria);
                allCriteria.addAll(testCaseUuidCriteria);
                allCriteria.addAll(systemRequirementsCriteria);
                allCriteria.addAll(systemRequirementPackCriteria);
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

        public Criteria andIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andCreatedAtEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andLogEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("log = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogNotEqualTo(String value) {
            addCriterion("log <>", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("log <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThan(String value) {
            addCriterion("log >", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("log > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualTo(String value) {
            addCriterion("log >=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("log >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThan(String value) {
            addCriterion("log <", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("log < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualTo(String value) {
            addCriterion("log <=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andStatusEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addStatusCriterion("status_id <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addStatusCriterion("status_id >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addStatusCriterion("status_id >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addStatusCriterion("status_id <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addStatusCriterion("status_id <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andFinishedEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_finished = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("is_finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_finished <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("is_finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_finished > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_finished >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("is_finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_finished < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andTestCaseIsNull() {
            addCriterion("test_case is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsNotNull() {
            addCriterion("test_case is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseEqualTo(JsonNode value) {
            addTestCaseCriterion("test_case =", value, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNotEqualTo(JsonNode value) {
            addTestCaseCriterion("test_case <>", value, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseGreaterThan(JsonNode value) {
            addTestCaseCriterion("test_case >", value, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseGreaterThanOrEqualTo(JsonNode value) {
            addTestCaseCriterion("test_case >=", value, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseLessThan(JsonNode value) {
            addTestCaseCriterion("test_case <", value, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseLessThanOrEqualTo(JsonNode value) {
            addTestCaseCriterion("test_case <=", value, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIn(List<JsonNode> values) {
            addTestCaseCriterion("test_case in", values, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseNotIn(List<JsonNode> values) {
            addTestCaseCriterion("test_case not in", values, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseBetween(JsonNode value1, JsonNode value2) {
            addTestCaseCriterion("test_case between", value1, value2, "testCase");
            return (Criteria) this;
        }

        public Criteria andTestCaseNotBetween(JsonNode value1, JsonNode value2) {
            addTestCaseCriterion("test_case not between", value1, value2, "testCase");
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

        public Criteria andParameterEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("parameter = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(JsonNode value) {
            addParameterCriterion("parameter <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("parameter <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(JsonNode value) {
            addParameterCriterion("parameter >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("parameter > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("parameter >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(JsonNode value) {
            addParameterCriterion("parameter <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("parameter < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andTestCaseIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualTo(Long value) {
            addCriterion("test_case_id <>", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThan(Long value) {
            addCriterion("test_case_id >", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_id >=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThan(Long value) {
            addCriterion("test_case_id <", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_id <=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andTypeIsNull() {
            addCriterion("run_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("run_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("run_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("run_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("run_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("run_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("run_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("run_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("run_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("run_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("run_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("run_type_id not between", value1, value2, "type");
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

        public Criteria andStartAtEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("start_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtNotEqualTo(Date value) {
            addCriterion("start_at <>", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("start_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThan(Date value) {
            addCriterion("start_at >", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("start_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanOrEqualTo(Date value) {
            addCriterion("start_at >=", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("start_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtLessThan(Date value) {
            addCriterion("start_at <", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("start_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanOrEqualTo(Date value) {
            addCriterion("start_at <=", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andEndAtEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("end_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtNotEqualTo(Date value) {
            addCriterion("end_at <>", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("end_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThan(Date value) {
            addCriterion("end_at >", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("end_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("end_at >=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("end_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtLessThan(Date value) {
            addCriterion("end_at <", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("end_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanOrEqualTo(Date value) {
            addCriterion("end_at <=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andGroupIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupEqualTo(String value) {
            addGroupCriterion("group_id =", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("group_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addGroupCriterion("group_id <>", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("group_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addGroupCriterion("group_id >", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("group_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addGroupCriterion("group_id >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("group_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addGroupCriterion("group_id <", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("group_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addGroupCriterion("group_id <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("group_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupIn(List<String> values) {
            addGroupCriterion("group_id in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotIn(List<String> values) {
            addGroupCriterion("group_id not in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupBetween(String value1, String value2) {
            addGroupCriterion("group_id between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotBetween(String value1, String value2) {
            addGroupCriterion("group_id not between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("priority = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("priority <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("priority > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("priority >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("priority < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("priority <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNull() {
            addCriterion("timeout is null");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNotNull() {
            addCriterion("timeout is not null");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualTo(Integer value) {
            addCriterion("timeout =", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("timeout = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualTo(Integer value) {
            addCriterion("timeout <>", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("timeout <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThan(Integer value) {
            addCriterion("timeout >", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("timeout > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("timeout >=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("timeout >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThan(Integer value) {
            addCriterion("timeout <", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("timeout < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("timeout <=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("timeout <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutIn(List<Integer> values) {
            addCriterion("timeout in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotIn(List<Integer> values) {
            addCriterion("timeout not in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("timeout between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("timeout not between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdIsNull() {
            addCriterion("run_set_result_id is null");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdIsNotNull() {
            addCriterion("run_set_result_id is not null");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdEqualTo(Long value) {
            addCriterion("run_set_result_id =", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_result_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotEqualTo(Long value) {
            addCriterion("run_set_result_id <>", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_result_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThan(Long value) {
            addCriterion("run_set_result_id >", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_result_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_result_id >=", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_result_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThan(Long value) {
            addCriterion("run_set_result_id <", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_result_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_result_id <=", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_result_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdIn(List<Long> values) {
            addCriterion("run_set_result_id in", values, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotIn(List<Long> values) {
            addCriterion("run_set_result_id not in", values, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdBetween(Long value1, Long value2) {
            addCriterion("run_set_result_id between", value1, value2, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotBetween(Long value1, Long value2) {
            addCriterion("run_set_result_id not between", value1, value2, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIsNull() {
            addCriterion("driver_pack is null");
            return (Criteria) this;
        }

        public Criteria andDriverPackIsNotNull() {
            addCriterion("driver_pack is not null");
            return (Criteria) this;
        }

        public Criteria andDriverPackEqualTo(JsonNode value) {
            addDriverPackCriterion("driver_pack =", value, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackNotEqualTo(JsonNode value) {
            addDriverPackCriterion("driver_pack <>", value, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackGreaterThan(JsonNode value) {
            addDriverPackCriterion("driver_pack >", value, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackGreaterThanOrEqualTo(JsonNode value) {
            addDriverPackCriterion("driver_pack >=", value, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackLessThan(JsonNode value) {
            addDriverPackCriterion("driver_pack <", value, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackLessThanOrEqualTo(JsonNode value) {
            addDriverPackCriterion("driver_pack <=", value, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIn(List<JsonNode> values) {
            addDriverPackCriterion("driver_pack in", values, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackNotIn(List<JsonNode> values) {
            addDriverPackCriterion("driver_pack not in", values, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackBetween(JsonNode value1, JsonNode value2) {
            addDriverPackCriterion("driver_pack between", value1, value2, "driverPack");
            return (Criteria) this;
        }

        public Criteria andDriverPackNotBetween(JsonNode value1, JsonNode value2) {
            addDriverPackCriterion("driver_pack not between", value1, value2, "driverPack");
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

        public Criteria andDriverPackIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotEqualTo(Long value) {
            addCriterion("driver_pack_id <>", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThan(Long value) {
            addCriterion("driver_pack_id >", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_pack_id >=", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThan(Long value) {
            addCriterion("driver_pack_id <", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("driver_pack_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanOrEqualTo(Long value) {
            addCriterion("driver_pack_id <=", value, "driverPackId");
            return (Criteria) this;
        }

        public Criteria andDriverPackIdLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andIsRecordedIsNull() {
            addCriterion("is_recorded is null");
            return (Criteria) this;
        }

        public Criteria andIsRecordedIsNotNull() {
            addCriterion("is_recorded is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecordedEqualTo(Long value) {
            addCriterion("is_recorded =", value, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_recorded = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsRecordedNotEqualTo(Long value) {
            addCriterion("is_recorded <>", value, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_recorded <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsRecordedGreaterThan(Long value) {
            addCriterion("is_recorded >", value, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_recorded > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsRecordedGreaterThanOrEqualTo(Long value) {
            addCriterion("is_recorded >=", value, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_recorded >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsRecordedLessThan(Long value) {
            addCriterion("is_recorded <", value, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_recorded < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsRecordedLessThanOrEqualTo(Long value) {
            addCriterion("is_recorded <=", value, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("is_recorded <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsRecordedIn(List<Long> values) {
            addCriterion("is_recorded in", values, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedNotIn(List<Long> values) {
            addCriterion("is_recorded not in", values, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedBetween(Long value1, Long value2) {
            addCriterion("is_recorded between", value1, value2, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andIsRecordedNotBetween(Long value1, Long value2) {
            addCriterion("is_recorded not between", value1, value2, "isRecorded");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIsNull() {
            addCriterion("test_case_overwrite is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIsNotNull() {
            addCriterion("test_case_overwrite is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteEqualTo(JsonNode value) {
            addTestCaseOverwriteCriterion("test_case_overwrite =", value, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNotEqualTo(JsonNode value) {
            addTestCaseOverwriteCriterion("test_case_overwrite <>", value, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteGreaterThan(JsonNode value) {
            addTestCaseOverwriteCriterion("test_case_overwrite >", value, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteGreaterThanOrEqualTo(JsonNode value) {
            addTestCaseOverwriteCriterion("test_case_overwrite >=", value, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteLessThan(JsonNode value) {
            addTestCaseOverwriteCriterion("test_case_overwrite <", value, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteLessThanOrEqualTo(JsonNode value) {
            addTestCaseOverwriteCriterion("test_case_overwrite <=", value, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIn(List<JsonNode> values) {
            addTestCaseOverwriteCriterion("test_case_overwrite in", values, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNotIn(List<JsonNode> values) {
            addTestCaseOverwriteCriterion("test_case_overwrite not in", values, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteBetween(JsonNode value1, JsonNode value2) {
            addTestCaseOverwriteCriterion("test_case_overwrite between", value1, value2, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNotBetween(JsonNode value1, JsonNode value2) {
            addTestCaseOverwriteCriterion("test_case_overwrite not between", value1, value2, "testCaseOverwrite");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdIsNull() {
            addCriterion("test_case_overwrite_id is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdIsNotNull() {
            addCriterion("test_case_overwrite_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdEqualTo(Long value) {
            addCriterion("test_case_overwrite_id =", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotEqualTo(Long value) {
            addCriterion("test_case_overwrite_id <>", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThan(Long value) {
            addCriterion("test_case_overwrite_id >", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_overwrite_id >=", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThan(Long value) {
            addCriterion("test_case_overwrite_id <", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_overwrite_id <=", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdIn(List<Long> values) {
            addCriterion("test_case_overwrite_id in", values, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotIn(List<Long> values) {
            addCriterion("test_case_overwrite_id not in", values, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdBetween(Long value1, Long value2) {
            addCriterion("test_case_overwrite_id between", value1, value2, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotBetween(Long value1, Long value2) {
            addCriterion("test_case_overwrite_id not between", value1, value2, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceIsNull() {
            addCriterion("trigger_source is null");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceIsNotNull() {
            addCriterion("trigger_source is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceEqualTo(String value) {
            addCriterion("trigger_source =", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("trigger_source = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceNotEqualTo(String value) {
            addCriterion("trigger_source <>", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("trigger_source <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThan(String value) {
            addCriterion("trigger_source >", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("trigger_source > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThanOrEqualTo(String value) {
            addCriterion("trigger_source >=", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("trigger_source >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThan(String value) {
            addCriterion("trigger_source <", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("trigger_source < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThanOrEqualTo(String value) {
            addCriterion("trigger_source <=", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("trigger_source <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLike(String value) {
            addCriterion("trigger_source like", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceNotLike(String value) {
            addCriterion("trigger_source not like", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceIn(List<String> values) {
            addCriterion("trigger_source in", values, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceNotIn(List<String> values) {
            addCriterion("trigger_source not in", values, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceBetween(String value1, String value2) {
            addCriterion("trigger_source between", value1, value2, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceNotBetween(String value1, String value2) {
            addCriterion("trigger_source not between", value1, value2, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andDriversIsNull() {
            addCriterion("drivers is null");
            return (Criteria) this;
        }

        public Criteria andDriversIsNotNull() {
            addCriterion("drivers is not null");
            return (Criteria) this;
        }

        public Criteria andDriversEqualTo(JsonNode value) {
            addDriversCriterion("drivers =", value, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("drivers = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriversNotEqualTo(JsonNode value) {
            addDriversCriterion("drivers <>", value, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("drivers <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriversGreaterThan(JsonNode value) {
            addDriversCriterion("drivers >", value, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("drivers > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriversGreaterThanOrEqualTo(JsonNode value) {
            addDriversCriterion("drivers >=", value, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("drivers >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriversLessThan(JsonNode value) {
            addDriversCriterion("drivers <", value, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("drivers < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriversLessThanOrEqualTo(JsonNode value) {
            addDriversCriterion("drivers <=", value, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("drivers <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriversIn(List<JsonNode> values) {
            addDriversCriterion("drivers in", values, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversNotIn(List<JsonNode> values) {
            addDriversCriterion("drivers not in", values, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversBetween(JsonNode value1, JsonNode value2) {
            addDriversCriterion("drivers between", value1, value2, "drivers");
            return (Criteria) this;
        }

        public Criteria andDriversNotBetween(JsonNode value1, JsonNode value2) {
            addDriversCriterion("drivers not between", value1, value2, "drivers");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(UUID value) {
            addUuidCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(UUID value) {
            addUuidCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(UUID value) {
            addUuidCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(UUID value) {
            addUuidCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<UUID> values) {
            addUuidCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<UUID> values) {
            addUuidCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(UUID value1, UUID value2) {
            addUuidCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(UUID value1, UUID value2) {
            addUuidCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidIsNull() {
            addCriterion("test_case_uuid is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidIsNotNull() {
            addCriterion("test_case_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidEqualTo(UUID value) {
            addTestCaseUuidCriterion("test_case_uuid =", value, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidNotEqualTo(UUID value) {
            addTestCaseUuidCriterion("test_case_uuid <>", value, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidGreaterThan(UUID value) {
            addTestCaseUuidCriterion("test_case_uuid >", value, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidGreaterThanOrEqualTo(UUID value) {
            addTestCaseUuidCriterion("test_case_uuid >=", value, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidLessThan(UUID value) {
            addTestCaseUuidCriterion("test_case_uuid <", value, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidLessThanOrEqualTo(UUID value) {
            addTestCaseUuidCriterion("test_case_uuid <=", value, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("test_case_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidIn(List<UUID> values) {
            addTestCaseUuidCriterion("test_case_uuid in", values, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidNotIn(List<UUID> values) {
            addTestCaseUuidCriterion("test_case_uuid not in", values, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidBetween(UUID value1, UUID value2) {
            addTestCaseUuidCriterion("test_case_uuid between", value1, value2, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andTestCaseUuidNotBetween(UUID value1, UUID value2) {
            addTestCaseUuidCriterion("test_case_uuid not between", value1, value2, "testCaseUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonIsNull() {
            addCriterion("singleton is null");
            return (Criteria) this;
        }

        public Criteria andSingletonIsNotNull() {
            addCriterion("singleton is not null");
            return (Criteria) this;
        }

        public Criteria andSingletonEqualTo(Boolean value) {
            addCriterion("singleton =", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("singleton = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonNotEqualTo(Boolean value) {
            addCriterion("singleton <>", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("singleton <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThan(Boolean value) {
            addCriterion("singleton >", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("singleton > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThanOrEqualTo(Boolean value) {
            addCriterion("singleton >=", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("singleton >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonLessThan(Boolean value) {
            addCriterion("singleton <", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("singleton < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonLessThanOrEqualTo(Boolean value) {
            addCriterion("singleton <=", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("singleton <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonIn(List<Boolean> values) {
            addCriterion("singleton in", values, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonNotIn(List<Boolean> values) {
            addCriterion("singleton not in", values, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonBetween(Boolean value1, Boolean value2) {
            addCriterion("singleton between", value1, value2, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonNotBetween(Boolean value1, Boolean value2) {
            addCriterion("singleton not between", value1, value2, "singleton");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberIsNull() {
            addCriterion("executable_instruction_number is null");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberIsNotNull() {
            addCriterion("executable_instruction_number is not null");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberEqualTo(Long value) {
            addCriterion("executable_instruction_number =", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotEqualTo(Long value) {
            addCriterion("executable_instruction_number <>", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThan(Long value) {
            addCriterion("executable_instruction_number >", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("executable_instruction_number >=", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThan(Long value) {
            addCriterion("executable_instruction_number <", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThanOrEqualTo(Long value) {
            addCriterion("executable_instruction_number <=", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberIn(List<Long> values) {
            addCriterion("executable_instruction_number in", values, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotIn(List<Long> values) {
            addCriterion("executable_instruction_number not in", values, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberBetween(Long value1, Long value2) {
            addCriterion("executable_instruction_number between", value1, value2, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotBetween(Long value1, Long value2) {
            addCriterion("executable_instruction_number not between", value1, value2, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdIsNull() {
            addCriterion("system_requirement_pack_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdIsNotNull() {
            addCriterion("system_requirement_pack_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdEqualTo(Long value) {
            addCriterion("system_requirement_pack_id =", value, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdNotEqualTo(Long value) {
            addCriterion("system_requirement_pack_id <>", value, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdGreaterThan(Long value) {
            addCriterion("system_requirement_pack_id >", value, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("system_requirement_pack_id >=", value, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdLessThan(Long value) {
            addCriterion("system_requirement_pack_id <", value, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdLessThanOrEqualTo(Long value) {
            addCriterion("system_requirement_pack_id <=", value, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdIn(List<Long> values) {
            addCriterion("system_requirement_pack_id in", values, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdNotIn(List<Long> values) {
            addCriterion("system_requirement_pack_id not in", values, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdBetween(Long value1, Long value2) {
            addCriterion("system_requirement_pack_id between", value1, value2, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIdNotBetween(Long value1, Long value2) {
            addCriterion("system_requirement_pack_id not between", value1, value2, "systemRequirementPackId");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsIsNull() {
            addCriterion("system_requirements is null");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsIsNotNull() {
            addCriterion("system_requirements is not null");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsEqualTo(JsonNode value) {
            addSystemRequirementsCriterion("system_requirements =", value, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirements = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsNotEqualTo(JsonNode value) {
            addSystemRequirementsCriterion("system_requirements <>", value, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirements <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsGreaterThan(JsonNode value) {
            addSystemRequirementsCriterion("system_requirements >", value, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirements > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsGreaterThanOrEqualTo(JsonNode value) {
            addSystemRequirementsCriterion("system_requirements >=", value, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirements >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsLessThan(JsonNode value) {
            addSystemRequirementsCriterion("system_requirements <", value, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirements < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsLessThanOrEqualTo(JsonNode value) {
            addSystemRequirementsCriterion("system_requirements <=", value, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirements <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsIn(List<JsonNode> values) {
            addSystemRequirementsCriterion("system_requirements in", values, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsNotIn(List<JsonNode> values) {
            addSystemRequirementsCriterion("system_requirements not in", values, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsBetween(JsonNode value1, JsonNode value2) {
            addSystemRequirementsCriterion("system_requirements between", value1, value2, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementsNotBetween(JsonNode value1, JsonNode value2) {
            addSystemRequirementsCriterion("system_requirements not between", value1, value2, "systemRequirements");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIsNull() {
            addCriterion("system_requirement_pack is null");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIsNotNull() {
            addCriterion("system_requirement_pack is not null");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackEqualTo(JsonNode value) {
            addSystemRequirementPackCriterion("system_requirement_pack =", value, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackNotEqualTo(JsonNode value) {
            addSystemRequirementPackCriterion("system_requirement_pack <>", value, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackGreaterThan(JsonNode value) {
            addSystemRequirementPackCriterion("system_requirement_pack >", value, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackGreaterThanOrEqualTo(JsonNode value) {
            addSystemRequirementPackCriterion("system_requirement_pack >=", value, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackLessThan(JsonNode value) {
            addSystemRequirementPackCriterion("system_requirement_pack <", value, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackLessThanOrEqualTo(JsonNode value) {
            addSystemRequirementPackCriterion("system_requirement_pack <=", value, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("system_requirement_pack <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackIn(List<JsonNode> values) {
            addSystemRequirementPackCriterion("system_requirement_pack in", values, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackNotIn(List<JsonNode> values) {
            addSystemRequirementPackCriterion("system_requirement_pack not in", values, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackBetween(JsonNode value1, JsonNode value2) {
            addSystemRequirementPackCriterion("system_requirement_pack between", value1, value2, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andSystemRequirementPackNotBetween(JsonNode value1, JsonNode value2) {
            addSystemRequirementPackCriterion("system_requirement_pack not between", value1, value2, "systemRequirementPack");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdIsNull() {
            addCriterion("run_set_test_case_link_id is null");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdIsNotNull() {
            addCriterion("run_set_test_case_link_id is not null");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdEqualTo(Long value) {
            addCriterion("run_set_test_case_link_id =", value, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_test_case_link_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdNotEqualTo(Long value) {
            addCriterion("run_set_test_case_link_id <>", value, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_test_case_link_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdGreaterThan(Long value) {
            addCriterion("run_set_test_case_link_id >", value, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_test_case_link_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_test_case_link_id >=", value, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_test_case_link_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdLessThan(Long value) {
            addCriterion("run_set_test_case_link_id <", value, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_test_case_link_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_test_case_link_id <=", value, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdLessThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("run_set_test_case_link_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdIn(List<Long> values) {
            addCriterion("run_set_test_case_link_id in", values, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdNotIn(List<Long> values) {
            addCriterion("run_set_test_case_link_id not in", values, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdBetween(Long value1, Long value2) {
            addCriterion("run_set_test_case_link_id between", value1, value2, "runSetTestCaseLinkId");
            return (Criteria) this;
        }

        public Criteria andRunSetTestCaseLinkIdNotBetween(Long value1, Long value2) {
            addCriterion("run_set_test_case_link_id not between", value1, value2, "runSetTestCaseLinkId");
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

        public Criteria andResultOverwrittenEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("result_overwritten = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenNotEqualTo(Integer value) {
            addCriterion("result_overwritten <>", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("result_overwritten <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThan(Integer value) {
            addCriterion("result_overwritten >", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("result_overwritten > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThanOrEqualTo(Integer value) {
            addCriterion("result_overwritten >=", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("result_overwritten >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThan(Integer value) {
            addCriterion("result_overwritten <", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("result_overwritten < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThanOrEqualTo(Integer value) {
            addCriterion("result_overwritten <=", value, "resultOverwritten");
            return (Criteria) this;
        }

        public Criteria andResultOverwrittenLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andProjectIdEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("project_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("project_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("project_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualToColumn(Run.Column column) {
            addCriterion(new StringBuilder("project_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanColumn(Run.Column column) {
            addCriterion(new StringBuilder("project_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualToColumn(Run.Column column) {
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

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andLogLikeInsensitive(String value) {
            addCriterion("upper(log) like", value.toUpperCase(), "log");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLikeInsensitive(String value) {
            addCriterion("upper(trigger_source) like", value.toUpperCase(), "triggerSource");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private RunExample example;

        protected Criteria(RunExample example) {
            super();
            this.example = example;
        }

        public RunExample example() {
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
        void example(com.meowlomo.atm.core.model.RunExample example);
    }
}