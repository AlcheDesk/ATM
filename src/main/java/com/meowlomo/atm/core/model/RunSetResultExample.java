package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

public class RunSetResultExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunSetResultExample() {
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

    public RunSetResultExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunSetResultExample orderBy(String... orderByClauses) {
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
        RunSetResultExample example = new RunSetResultExample();
        return example.createCriteria();
    }

    public RunSetResultExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunSetResultExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> groupCriteria;

        protected List<Criterion> typeCriteria;

        protected List<Criterion> runTypeCriteria;

        protected List<Criterion> sourceTypeCriteria;

        protected List<Criterion> runCriteria;

        protected List<Criterion> uuidCriteria;

        protected List<Criterion> runIdsCriteria;

        protected List<Criterion> passedRunIdsCriteria;

        protected List<Criterion> failedRunIdsCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
            groupCriteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            runTypeCriteria = new ArrayList<Criterion>();
            sourceTypeCriteria = new ArrayList<Criterion>();
            runCriteria = new ArrayList<Criterion>();
            uuidCriteria = new ArrayList<Criterion>();
            runIdsCriteria = new ArrayList<Criterion>();
            passedRunIdsCriteria = new ArrayList<Criterion>();
            failedRunIdsCriteria = new ArrayList<Criterion>();
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

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.RunSetTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.RunSetTypeTypeHandler"));
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

        public List<Criterion> getSourceTypeCriteria() {
            return sourceTypeCriteria;
        }

        protected void addSourceTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            sourceTypeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.SourceTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addSourceTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            sourceTypeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.SourceTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getRunCriteria() {
            return runCriteria;
        }

        protected void addRunCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            runCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addRunCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            runCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
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

        public List<Criterion> getRunIdsCriteria() {
            return runIdsCriteria;
        }

        protected void addRunIdsCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            runIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addRunIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            runIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getPassedRunIdsCriteria() {
            return passedRunIdsCriteria;
        }

        protected void addPassedRunIdsCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            passedRunIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addPassedRunIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            passedRunIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getFailedRunIdsCriteria() {
            return failedRunIdsCriteria;
        }

        protected void addFailedRunIdsCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            failedRunIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addFailedRunIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            failedRunIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || statusCriteria.size() > 0 || groupCriteria.size() > 0 || typeCriteria.size() > 0 || runTypeCriteria.size() > 0
                    || sourceTypeCriteria.size() > 0 || runCriteria.size() > 0 || uuidCriteria.size() > 0 || runIdsCriteria.size() > 0 || passedRunIdsCriteria.size() > 0
                    || failedRunIdsCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(statusCriteria);
                allCriteria.addAll(groupCriteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(runTypeCriteria);
                allCriteria.addAll(sourceTypeCriteria);
                allCriteria.addAll(runCriteria);
                allCriteria.addAll(uuidCriteria);
                allCriteria.addAll(runIdsCriteria);
                allCriteria.addAll(passedRunIdsCriteria);
                allCriteria.addAll(failedRunIdsCriteria);
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

        public Criteria andIdEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andNameEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andCreatedAtEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andStartAtEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("start_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtNotEqualTo(Date value) {
            addCriterion("start_at <>", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("start_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThan(Date value) {
            addCriterion("start_at >", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("start_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanOrEqualTo(Date value) {
            addCriterion("start_at >=", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("start_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtLessThan(Date value) {
            addCriterion("start_at <", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("start_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanOrEqualTo(Date value) {
            addCriterion("start_at <=", value, "startAt");
            return (Criteria) this;
        }

        public Criteria andStartAtLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andEndAtEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("end_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtNotEqualTo(Date value) {
            addCriterion("end_at <>", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("end_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThan(Date value) {
            addCriterion("end_at >", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("end_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("end_at >=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("end_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtLessThan(Date value) {
            addCriterion("end_at <", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("end_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanOrEqualTo(Date value) {
            addCriterion("end_at <=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andStatusEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addStatusCriterion("status_id <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addStatusCriterion("status_id >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addStatusCriterion("status_id >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addStatusCriterion("status_id <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addStatusCriterion("status_id <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andLogEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("log = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogNotEqualTo(String value) {
            addCriterion("log <>", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("log <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThan(String value) {
            addCriterion("log >", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("log > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualTo(String value) {
            addCriterion("log >=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("log >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThan(String value) {
            addCriterion("log <", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("log < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualTo(String value) {
            addCriterion("log <=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andGroupEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("group_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addGroupCriterion("group_id <>", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("group_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addGroupCriterion("group_id >", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("group_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addGroupCriterion("group_id >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("group_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addGroupCriterion("group_id <", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("group_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addGroupCriterion("group_id <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andTypeIsNull() {
            addCriterion("run_set_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("run_set_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("run_set_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("run_set_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("run_set_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("run_set_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("run_set_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("run_set_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("run_set_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("run_set_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("run_set_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("run_set_type_id not between", value1, value2, "type");
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

        public Criteria andPriorityEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("priority = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("priority <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("priority > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("priority >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("priority < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andRunSetIdEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualTo(Long value) {
            addCriterion("run_set_id <>", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThan(Long value) {
            addCriterion("run_set_id >", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_id >=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThan(Long value) {
            addCriterion("run_set_id <", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_set_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_id <=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andFinishedEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("is_finished = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("is_finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("is_finished <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("is_finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("is_finished > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("is_finished >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("is_finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("is_finished < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andRunTypeEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualTo(String value) {
            addRunTypeCriterion("run_type_id <>", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThan(String value) {
            addRunTypeCriterion("run_type_id >", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id >=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThan(String value) {
            addRunTypeCriterion("run_type_id <", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id <=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andSourceTypeIsNull() {
            addCriterion("source_type_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("source_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(String value) {
            addSourceTypeCriterion("source_type_id =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("source_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(String value) {
            addSourceTypeCriterion("source_type_id <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("source_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(String value) {
            addSourceTypeCriterion("source_type_id >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("source_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(String value) {
            addSourceTypeCriterion("source_type_id >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("source_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(String value) {
            addSourceTypeCriterion("source_type_id <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("source_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(String value) {
            addSourceTypeCriterion("source_type_id <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("source_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<String> values) {
            addSourceTypeCriterion("source_type_id in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<String> values) {
            addSourceTypeCriterion("source_type_id not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(String value1, String value2) {
            addSourceTypeCriterion("source_type_id between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(String value1, String value2) {
            addSourceTypeCriterion("source_type_id not between", value1, value2, "sourceType");
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

        public Criteria andCommentEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("comment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("comment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("comment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("comment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("comment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andRunIsNull() {
            addCriterion("run is null");
            return (Criteria) this;
        }

        public Criteria andRunIsNotNull() {
            addCriterion("run is not null");
            return (Criteria) this;
        }

        public Criteria andRunEqualTo(JsonNode value) {
            addRunCriterion("run =", value, "run");
            return (Criteria) this;
        }

        public Criteria andRunEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunNotEqualTo(JsonNode value) {
            addRunCriterion("run <>", value, "run");
            return (Criteria) this;
        }

        public Criteria andRunNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunGreaterThan(JsonNode value) {
            addRunCriterion("run >", value, "run");
            return (Criteria) this;
        }

        public Criteria andRunGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunGreaterThanOrEqualTo(JsonNode value) {
            addRunCriterion("run >=", value, "run");
            return (Criteria) this;
        }

        public Criteria andRunGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunLessThan(JsonNode value) {
            addRunCriterion("run <", value, "run");
            return (Criteria) this;
        }

        public Criteria andRunLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunLessThanOrEqualTo(JsonNode value) {
            addRunCriterion("run <=", value, "run");
            return (Criteria) this;
        }

        public Criteria andRunLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIn(List<JsonNode> values) {
            addRunCriterion("run in", values, "run");
            return (Criteria) this;
        }

        public Criteria andRunNotIn(List<JsonNode> values) {
            addRunCriterion("run not in", values, "run");
            return (Criteria) this;
        }

        public Criteria andRunBetween(JsonNode value1, JsonNode value2) {
            addRunCriterion("run between", value1, value2, "run");
            return (Criteria) this;
        }

        public Criteria andRunNotBetween(JsonNode value1, JsonNode value2) {
            addRunCriterion("run not between", value1, value2, "run");
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

        public Criteria andUuidEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(UUID value) {
            addUuidCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(UUID value) {
            addUuidCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(UUID value) {
            addUuidCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualToColumn(RunSetResult.Column column) {
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

        public Criteria andTotalRunNumberIsNull() {
            addCriterion("total_run_number is null");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberIsNotNull() {
            addCriterion("total_run_number is not null");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberEqualTo(Integer value) {
            addCriterion("total_run_number =", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("total_run_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotEqualTo(Integer value) {
            addCriterion("total_run_number <>", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("total_run_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThan(Integer value) {
            addCriterion("total_run_number >", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("total_run_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_run_number >=", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("total_run_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThan(Integer value) {
            addCriterion("total_run_number <", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("total_run_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThanOrEqualTo(Integer value) {
            addCriterion("total_run_number <=", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("total_run_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberIn(List<Integer> values) {
            addCriterion("total_run_number in", values, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotIn(List<Integer> values) {
            addCriterion("total_run_number not in", values, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberBetween(Integer value1, Integer value2) {
            addCriterion("total_run_number between", value1, value2, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("total_run_number not between", value1, value2, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberIsNull() {
            addCriterion("passed_run_number is null");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberIsNotNull() {
            addCriterion("passed_run_number is not null");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberEqualTo(Integer value) {
            addCriterion("passed_run_number =", value, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberNotEqualTo(Integer value) {
            addCriterion("passed_run_number <>", value, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberGreaterThan(Integer value) {
            addCriterion("passed_run_number >", value, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("passed_run_number >=", value, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberLessThan(Integer value) {
            addCriterion("passed_run_number <", value, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberLessThanOrEqualTo(Integer value) {
            addCriterion("passed_run_number <=", value, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberIn(List<Integer> values) {
            addCriterion("passed_run_number in", values, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberNotIn(List<Integer> values) {
            addCriterion("passed_run_number not in", values, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberBetween(Integer value1, Integer value2) {
            addCriterion("passed_run_number between", value1, value2, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andPassedRunNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("passed_run_number not between", value1, value2, "passedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberIsNull() {
            addCriterion("failed_run_number is null");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberIsNotNull() {
            addCriterion("failed_run_number is not null");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberEqualTo(Integer value) {
            addCriterion("failed_run_number =", value, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberNotEqualTo(Integer value) {
            addCriterion("failed_run_number <>", value, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberGreaterThan(Integer value) {
            addCriterion("failed_run_number >", value, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("failed_run_number >=", value, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberLessThan(Integer value) {
            addCriterion("failed_run_number <", value, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberLessThanOrEqualTo(Integer value) {
            addCriterion("failed_run_number <=", value, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberIn(List<Integer> values) {
            addCriterion("failed_run_number in", values, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberNotIn(List<Integer> values) {
            addCriterion("failed_run_number not in", values, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberBetween(Integer value1, Integer value2) {
            addCriterion("failed_run_number between", value1, value2, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andFailedRunNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("failed_run_number not between", value1, value2, "failedRunNumber");
            return (Criteria) this;
        }

        public Criteria andRunIdsIsNull() {
            addCriterion("run_ids is null");
            return (Criteria) this;
        }

        public Criteria andRunIdsIsNotNull() {
            addCriterion("run_ids is not null");
            return (Criteria) this;
        }

        public Criteria andRunIdsEqualTo(Set<Long> value) {
            addRunIdsCriterion("run_ids =", value, "runIds");
            return (Criteria) this;
        }

        public Criteria andRunIdsEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdsNotEqualTo(Set<Long> value) {
            addRunIdsCriterion("run_ids <>", value, "runIds");
            return (Criteria) this;
        }

        public Criteria andRunIdsNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdsGreaterThan(Set<Long> value) {
            addRunIdsCriterion("run_ids >", value, "runIds");
            return (Criteria) this;
        }

        public Criteria andRunIdsGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdsGreaterThanOrEqualTo(Set<Long> value) {
            addRunIdsCriterion("run_ids >=", value, "runIds");
            return (Criteria) this;
        }

        public Criteria andRunIdsGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdsLessThan(Set<Long> value) {
            addRunIdsCriterion("run_ids <", value, "runIds");
            return (Criteria) this;
        }

        public Criteria andRunIdsLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdsLessThanOrEqualTo(Set<Long> value) {
            addRunIdsCriterion("run_ids <=", value, "runIds");
            return (Criteria) this;
        }

        public Criteria andRunIdsLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("run_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdsBetween(Set<Long> value1, Set<Long> value2) {
            addRunIdsCriterion("run_ids between", value1, value2, "runIds");
            return (Criteria) this;
        }

        public Criteria andRunIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addRunIdsCriterion("run_ids not between", value1, value2, "runIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsIsNull() {
            addCriterion("passed_run_ids is null");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsIsNotNull() {
            addCriterion("passed_run_ids is not null");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsEqualTo(Set<Long> value) {
            addPassedRunIdsCriterion("passed_run_ids =", value, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsNotEqualTo(Set<Long> value) {
            addPassedRunIdsCriterion("passed_run_ids <>", value, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsGreaterThan(Set<Long> value) {
            addPassedRunIdsCriterion("passed_run_ids >", value, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsGreaterThanOrEqualTo(Set<Long> value) {
            addPassedRunIdsCriterion("passed_run_ids >=", value, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsLessThan(Set<Long> value) {
            addPassedRunIdsCriterion("passed_run_ids <", value, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsLessThanOrEqualTo(Set<Long> value) {
            addPassedRunIdsCriterion("passed_run_ids <=", value, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("passed_run_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsBetween(Set<Long> value1, Set<Long> value2) {
            addPassedRunIdsCriterion("passed_run_ids between", value1, value2, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andPassedRunIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addPassedRunIdsCriterion("passed_run_ids not between", value1, value2, "passedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsIsNull() {
            addCriterion("failed_run_ids is null");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsIsNotNull() {
            addCriterion("failed_run_ids is not null");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsEqualTo(Set<Long> value) {
            addFailedRunIdsCriterion("failed_run_ids =", value, "failedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsNotEqualTo(Set<Long> value) {
            addFailedRunIdsCriterion("failed_run_ids <>", value, "failedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsNotEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsGreaterThan(Set<Long> value) {
            addFailedRunIdsCriterion("failed_run_ids >", value, "failedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsGreaterThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsGreaterThanOrEqualTo(Set<Long> value) {
            addFailedRunIdsCriterion("failed_run_ids >=", value, "failedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsGreaterThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsLessThan(Set<Long> value) {
            addFailedRunIdsCriterion("failed_run_ids <", value, "failedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsLessThanColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsLessThanOrEqualTo(Set<Long> value) {
            addFailedRunIdsCriterion("failed_run_ids <=", value, "failedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsLessThanOrEqualToColumn(RunSetResult.Column column) {
            addCriterion(new StringBuilder("failed_run_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsBetween(Set<Long> value1, Set<Long> value2) {
            addFailedRunIdsCriterion("failed_run_ids between", value1, value2, "failedRunIds");
            return (Criteria) this;
        }

        public Criteria andFailedRunIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addFailedRunIdsCriterion("failed_run_ids not between", value1, value2, "failedRunIds");
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
        private RunSetResultExample example;

        protected Criteria(RunSetResultExample example) {
            super();
            this.example = example;
        }

        public RunSetResultExample example() {
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
        void example(com.meowlomo.atm.core.model.RunSetResultExample example);
    }
}