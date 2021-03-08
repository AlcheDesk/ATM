package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RunExecutionInfoExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunExecutionInfoExample() {
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

    public RunExecutionInfoExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunExecutionInfoExample orderBy(String... orderByClauses) {
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
        RunExecutionInfoExample example = new RunExecutionInfoExample();
        return example.createCriteria();
    }

    public RunExecutionInfoExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunExecutionInfoExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> runTypeCriteria;

        protected List<Criterion> runStatusCriteria;

        protected List<Criterion> driverPackMd5Criteria;

        protected List<Criterion> testCaseOverwriteMd5Criteria;

        protected List<Criterion> testCaseMd5Criteria;

        protected List<Criterion> groupCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            runTypeCriteria = new ArrayList<Criterion>();
            runStatusCriteria = new ArrayList<Criterion>();
            driverPackMd5Criteria = new ArrayList<Criterion>();
            testCaseOverwriteMd5Criteria = new ArrayList<Criterion>();
            testCaseMd5Criteria = new ArrayList<Criterion>();
            groupCriteria = new ArrayList<Criterion>();
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

        public List<Criterion> getRunStatusCriteria() {
            return runStatusCriteria;
        }

        protected void addRunStatusCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            runStatusCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        protected void addRunStatusCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            runStatusCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getDriverPackMd5Criteria() {
            return driverPackMd5Criteria;
        }

        protected void addDriverPackMd5Criterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            driverPackMd5Criteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addDriverPackMd5Criterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            driverPackMd5Criteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTestCaseOverwriteMd5Criteria() {
            return testCaseOverwriteMd5Criteria;
        }

        protected void addTestCaseOverwriteMd5Criterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            testCaseOverwriteMd5Criteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addTestCaseOverwriteMd5Criterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            testCaseOverwriteMd5Criteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTestCaseMd5Criteria() {
            return testCaseMd5Criteria;
        }

        protected void addTestCaseMd5Criterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            testCaseMd5Criteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addTestCaseMd5Criterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            testCaseMd5Criteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
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

        public boolean isValid() {
            return criteria.size() > 0 || runTypeCriteria.size() > 0 || runStatusCriteria.size() > 0 || driverPackMd5Criteria.size() > 0 || testCaseOverwriteMd5Criteria.size() > 0
                    || testCaseMd5Criteria.size() > 0 || groupCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(runTypeCriteria);
                allCriteria.addAll(runStatusCriteria);
                allCriteria.addAll(driverPackMd5Criteria);
                allCriteria.addAll(testCaseOverwriteMd5Criteria);
                allCriteria.addAll(testCaseMd5Criteria);
                allCriteria.addAll(groupCriteria);
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

        public Criteria andRunIdEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualTo(Long value) {
            addCriterion("run_id <>", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThan(Long value) {
            addCriterion("run_id >", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_id >=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThan(Long value) {
            addCriterion("run_id <", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualTo(Long value) {
            addCriterion("run_id <=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
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

        public Criteria andRunNameIsNull() {
            addCriterion("run_name is null");
            return (Criteria) this;
        }

        public Criteria andRunNameIsNotNull() {
            addCriterion("run_name is not null");
            return (Criteria) this;
        }

        public Criteria andRunNameEqualTo(String value) {
            addCriterion("run_name =", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunNameNotEqualTo(String value) {
            addCriterion("run_name <>", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunNameGreaterThan(String value) {
            addCriterion("run_name >", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunNameGreaterThanOrEqualTo(String value) {
            addCriterion("run_name >=", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunNameLessThan(String value) {
            addCriterion("run_name <", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunNameLessThanOrEqualTo(String value) {
            addCriterion("run_name <=", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunNameLike(String value) {
            addCriterion("run_name like", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameNotLike(String value) {
            addCriterion("run_name not like", value, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameIn(List<String> values) {
            addCriterion("run_name in", values, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameNotIn(List<String> values) {
            addCriterion("run_name not in", values, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameBetween(String value1, String value2) {
            addCriterion("run_name between", value1, value2, "runName");
            return (Criteria) this;
        }

        public Criteria andRunNameNotBetween(String value1, String value2) {
            addCriterion("run_name not between", value1, value2, "runName");
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

        public Criteria andRunTypeEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualTo(String value) {
            addRunTypeCriterion("run_type_id <>", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThan(String value) {
            addRunTypeCriterion("run_type_id >", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id >=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThan(String value) {
            addRunTypeCriterion("run_type_id <", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id <=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
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

        public Criteria andRunStatusIsNull() {
            addCriterion("run_status_id is null");
            return (Criteria) this;
        }

        public Criteria andRunStatusIsNotNull() {
            addCriterion("run_status_id is not null");
            return (Criteria) this;
        }

        public Criteria andRunStatusEqualTo(String value) {
            addRunStatusCriterion("run_status_id =", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStatusNotEqualTo(String value) {
            addRunStatusCriterion("run_status_id <>", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThan(String value) {
            addRunStatusCriterion("run_status_id >", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThanOrEqualTo(String value) {
            addRunStatusCriterion("run_status_id >=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThan(String value) {
            addRunStatusCriterion("run_status_id <", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThanOrEqualTo(String value) {
            addRunStatusCriterion("run_status_id <=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_status_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStatusIn(List<String> values) {
            addRunStatusCriterion("run_status_id in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotIn(List<String> values) {
            addRunStatusCriterion("run_status_id not in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusBetween(String value1, String value2) {
            addRunStatusCriterion("run_status_id between", value1, value2, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotBetween(String value1, String value2) {
            addRunStatusCriterion("run_status_id not between", value1, value2, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtIsNull() {
            addCriterion("run_created_at is null");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtIsNotNull() {
            addCriterion("run_created_at is not null");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtEqualTo(Date value) {
            addCriterion("run_created_at =", value, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtNotEqualTo(Date value) {
            addCriterion("run_created_at <>", value, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtGreaterThan(Date value) {
            addCriterion("run_created_at >", value, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("run_created_at >=", value, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtLessThan(Date value) {
            addCriterion("run_created_at <", value, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("run_created_at <=", value, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_created_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtIn(List<Date> values) {
            addCriterion("run_created_at in", values, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtNotIn(List<Date> values) {
            addCriterion("run_created_at not in", values, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtBetween(Date value1, Date value2) {
            addCriterion("run_created_at between", value1, value2, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("run_created_at not between", value1, value2, "runCreatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtIsNull() {
            addCriterion("run_updated_at is null");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtIsNotNull() {
            addCriterion("run_updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtEqualTo(Date value) {
            addCriterion("run_updated_at =", value, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtNotEqualTo(Date value) {
            addCriterion("run_updated_at <>", value, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtGreaterThan(Date value) {
            addCriterion("run_updated_at >", value, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("run_updated_at >=", value, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtLessThan(Date value) {
            addCriterion("run_updated_at <", value, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("run_updated_at <=", value, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_updated_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtIn(List<Date> values) {
            addCriterion("run_updated_at in", values, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtNotIn(List<Date> values) {
            addCriterion("run_updated_at not in", values, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("run_updated_at between", value1, value2, "runUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andRunUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("run_updated_at not between", value1, value2, "runUpdatedAt");
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

        public Criteria andTestCaseIdEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualTo(Long value) {
            addCriterion("test_case_id <>", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThan(Long value) {
            addCriterion("test_case_id >", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_id >=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThan(Long value) {
            addCriterion("test_case_id <", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_id <=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
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

        public Criteria andRunSetResultIdEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_set_result_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotEqualTo(Long value) {
            addCriterion("run_set_result_id <>", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_set_result_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThan(Long value) {
            addCriterion("run_set_result_id >", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_set_result_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_result_id >=", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_set_result_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThan(Long value) {
            addCriterion("run_set_result_id <", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_set_result_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_result_id <=", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
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

        public Criteria andExecutableInstructionNumberIsNull() {
            addCriterion("executable_instruction_number is null");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberIsNotNull() {
            addCriterion("executable_instruction_number is not null");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberEqualTo(Integer value) {
            addCriterion("executable_instruction_number =", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotEqualTo(Integer value) {
            addCriterion("executable_instruction_number <>", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThan(Integer value) {
            addCriterion("executable_instruction_number >", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("executable_instruction_number >=", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThan(Integer value) {
            addCriterion("executable_instruction_number <", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThanOrEqualTo(Integer value) {
            addCriterion("executable_instruction_number <=", value, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executable_instruction_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberIn(List<Integer> values) {
            addCriterion("executable_instruction_number in", values, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotIn(List<Integer> values) {
            addCriterion("executable_instruction_number not in", values, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberBetween(Integer value1, Integer value2) {
            addCriterion("executable_instruction_number between", value1, value2, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andExecutableInstructionNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("executable_instruction_number not between", value1, value2, "executableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountIsNull() {
            addCriterion("instruction_executed_count is null");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountIsNotNull() {
            addCriterion("instruction_executed_count is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountEqualTo(Long value) {
            addCriterion("instruction_executed_count =", value, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_executed_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountNotEqualTo(Long value) {
            addCriterion("instruction_executed_count <>", value, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_executed_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountGreaterThan(Long value) {
            addCriterion("instruction_executed_count >", value, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_executed_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountGreaterThanOrEqualTo(Long value) {
            addCriterion("instruction_executed_count >=", value, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_executed_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountLessThan(Long value) {
            addCriterion("instruction_executed_count <", value, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_executed_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountLessThanOrEqualTo(Long value) {
            addCriterion("instruction_executed_count <=", value, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_executed_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountIn(List<Long> values) {
            addCriterion("instruction_executed_count in", values, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountNotIn(List<Long> values) {
            addCriterion("instruction_executed_count not in", values, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountBetween(Long value1, Long value2) {
            addCriterion("instruction_executed_count between", value1, value2, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionExecutedCountNotBetween(Long value1, Long value2) {
            addCriterion("instruction_executed_count not between", value1, value2, "instructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountIsNull() {
            addCriterion("instruction_pass_count is null");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountIsNotNull() {
            addCriterion("instruction_pass_count is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountEqualTo(Long value) {
            addCriterion("instruction_pass_count =", value, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_pass_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountNotEqualTo(Long value) {
            addCriterion("instruction_pass_count <>", value, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_pass_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountGreaterThan(Long value) {
            addCriterion("instruction_pass_count >", value, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_pass_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountGreaterThanOrEqualTo(Long value) {
            addCriterion("instruction_pass_count >=", value, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_pass_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountLessThan(Long value) {
            addCriterion("instruction_pass_count <", value, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_pass_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountLessThanOrEqualTo(Long value) {
            addCriterion("instruction_pass_count <=", value, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_pass_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountIn(List<Long> values) {
            addCriterion("instruction_pass_count in", values, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountNotIn(List<Long> values) {
            addCriterion("instruction_pass_count not in", values, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountBetween(Long value1, Long value2) {
            addCriterion("instruction_pass_count between", value1, value2, "instructionPassCount");
            return (Criteria) this;
        }

        public Criteria andInstructionPassCountNotBetween(Long value1, Long value2) {
            addCriterion("instruction_pass_count not between", value1, value2, "instructionPassCount");
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

        public Criteria andTriggerSourceEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("trigger_source = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceNotEqualTo(String value) {
            addCriterion("trigger_source <>", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("trigger_source <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThan(String value) {
            addCriterion("trigger_source >", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("trigger_source > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThanOrEqualTo(String value) {
            addCriterion("trigger_source >=", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("trigger_source >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThan(String value) {
            addCriterion("trigger_source <", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("trigger_source < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThanOrEqualTo(String value) {
            addCriterion("trigger_source <=", value, "triggerSource");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
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

        public Criteria andDriverPackMd5IsNull() {
            addCriterion("driver_pack_md5 is null");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5IsNotNull() {
            addCriterion("driver_pack_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5EqualTo(UUID value) {
            addDriverPackMd5Criterion("driver_pack_md5 =", value, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5EqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_md5 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5NotEqualTo(UUID value) {
            addDriverPackMd5Criterion("driver_pack_md5 <>", value, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5NotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_md5 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5GreaterThan(UUID value) {
            addDriverPackMd5Criterion("driver_pack_md5 >", value, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5GreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_md5 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5GreaterThanOrEqualTo(UUID value) {
            addDriverPackMd5Criterion("driver_pack_md5 >=", value, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5GreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_md5 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5LessThan(UUID value) {
            addDriverPackMd5Criterion("driver_pack_md5 <", value, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5LessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_md5 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5LessThanOrEqualTo(UUID value) {
            addDriverPackMd5Criterion("driver_pack_md5 <=", value, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5LessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_md5 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5In(List<UUID> values) {
            addDriverPackMd5Criterion("driver_pack_md5 in", values, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5NotIn(List<UUID> values) {
            addDriverPackMd5Criterion("driver_pack_md5 not in", values, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5Between(UUID value1, UUID value2) {
            addDriverPackMd5Criterion("driver_pack_md5 between", value1, value2, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andDriverPackMd5NotBetween(UUID value1, UUID value2) {
            addDriverPackMd5Criterion("driver_pack_md5 not between", value1, value2, "driverPackMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5IsNull() {
            addCriterion("test_case_overwrite_md5 is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5IsNotNull() {
            addCriterion("test_case_overwrite_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5EqualTo(UUID value) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 =", value, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5EqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_md5 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5NotEqualTo(UUID value) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 <>", value, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5NotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_md5 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5GreaterThan(UUID value) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 >", value, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5GreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_md5 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5GreaterThanOrEqualTo(UUID value) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 >=", value, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5GreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_md5 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5LessThan(UUID value) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 <", value, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5LessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_md5 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5LessThanOrEqualTo(UUID value) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 <=", value, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5LessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_md5 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5In(List<UUID> values) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 in", values, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5NotIn(List<UUID> values) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 not in", values, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5Between(UUID value1, UUID value2) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 between", value1, value2, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteMd5NotBetween(UUID value1, UUID value2) {
            addTestCaseOverwriteMd5Criterion("test_case_overwrite_md5 not between", value1, value2, "testCaseOverwriteMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5IsNull() {
            addCriterion("test_case_md5 is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5IsNotNull() {
            addCriterion("test_case_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5EqualTo(UUID value) {
            addTestCaseMd5Criterion("test_case_md5 =", value, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5EqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_md5 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5NotEqualTo(UUID value) {
            addTestCaseMd5Criterion("test_case_md5 <>", value, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5NotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_md5 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5GreaterThan(UUID value) {
            addTestCaseMd5Criterion("test_case_md5 >", value, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5GreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_md5 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5GreaterThanOrEqualTo(UUID value) {
            addTestCaseMd5Criterion("test_case_md5 >=", value, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5GreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_md5 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5LessThan(UUID value) {
            addTestCaseMd5Criterion("test_case_md5 <", value, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5LessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_md5 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5LessThanOrEqualTo(UUID value) {
            addTestCaseMd5Criterion("test_case_md5 <=", value, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5LessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_md5 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5In(List<UUID> values) {
            addTestCaseMd5Criterion("test_case_md5 in", values, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5NotIn(List<UUID> values) {
            addTestCaseMd5Criterion("test_case_md5 not in", values, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5Between(UUID value1, UUID value2) {
            addTestCaseMd5Criterion("test_case_md5 between", value1, value2, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andTestCaseMd5NotBetween(UUID value1, UUID value2) {
            addTestCaseMd5Criterion("test_case_md5 not between", value1, value2, "testCaseMd5");
            return (Criteria) this;
        }

        public Criteria andGroupIsNull() {
            addCriterion("run_group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIsNotNull() {
            addCriterion("run_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupEqualTo(String value) {
            addGroupCriterion("run_group_id =", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_group_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addGroupCriterion("run_group_id <>", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_group_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addGroupCriterion("run_group_id >", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_group_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addGroupCriterion("run_group_id >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_group_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addGroupCriterion("run_group_id <", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_group_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addGroupCriterion("run_group_id <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_group_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupIn(List<String> values) {
            addGroupCriterion("run_group_id in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotIn(List<String> values) {
            addGroupCriterion("run_group_id not in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupBetween(String value1, String value2) {
            addGroupCriterion("run_group_id between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotBetween(String value1, String value2) {
            addGroupCriterion("run_group_id not between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameIsNull() {
            addCriterion("driver_pack_name is null");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameIsNotNull() {
            addCriterion("driver_pack_name is not null");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameEqualTo(String value) {
            addCriterion("driver_pack_name =", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackNameNotEqualTo(String value) {
            addCriterion("driver_pack_name <>", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackNameGreaterThan(String value) {
            addCriterion("driver_pack_name >", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackNameGreaterThanOrEqualTo(String value) {
            addCriterion("driver_pack_name >=", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackNameLessThan(String value) {
            addCriterion("driver_pack_name <", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackNameLessThanOrEqualTo(String value) {
            addCriterion("driver_pack_name <=", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("driver_pack_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverPackNameLike(String value) {
            addCriterion("driver_pack_name like", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameNotLike(String value) {
            addCriterion("driver_pack_name not like", value, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameIn(List<String> values) {
            addCriterion("driver_pack_name in", values, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameNotIn(List<String> values) {
            addCriterion("driver_pack_name not in", values, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameBetween(String value1, String value2) {
            addCriterion("driver_pack_name between", value1, value2, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameNotBetween(String value1, String value2) {
            addCriterion("driver_pack_name not between", value1, value2, "driverPackName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameIsNull() {
            addCriterion("test_case_overwrite_name is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameIsNotNull() {
            addCriterion("test_case_overwrite_name is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameEqualTo(String value) {
            addCriterion("test_case_overwrite_name =", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameNotEqualTo(String value) {
            addCriterion("test_case_overwrite_name <>", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameGreaterThan(String value) {
            addCriterion("test_case_overwrite_name >", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameGreaterThanOrEqualTo(String value) {
            addCriterion("test_case_overwrite_name >=", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameLessThan(String value) {
            addCriterion("test_case_overwrite_name <", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameLessThanOrEqualTo(String value) {
            addCriterion("test_case_overwrite_name <=", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameLike(String value) {
            addCriterion("test_case_overwrite_name like", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameNotLike(String value) {
            addCriterion("test_case_overwrite_name not like", value, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameIn(List<String> values) {
            addCriterion("test_case_overwrite_name in", values, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameNotIn(List<String> values) {
            addCriterion("test_case_overwrite_name not in", values, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameBetween(String value1, String value2) {
            addCriterion("test_case_overwrite_name between", value1, value2, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameNotBetween(String value1, String value2) {
            addCriterion("test_case_overwrite_name not between", value1, value2, "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameIsNull() {
            addCriterion("test_case_name is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameIsNotNull() {
            addCriterion("test_case_name is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameEqualTo(String value) {
            addCriterion("test_case_name =", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameNotEqualTo(String value) {
            addCriterion("test_case_name <>", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThan(String value) {
            addCriterion("test_case_name >", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("test_case_name >=", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThan(String value) {
            addCriterion("test_case_name <", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThanOrEqualTo(String value) {
            addCriterion("test_case_name <=", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLike(String value) {
            addCriterion("test_case_name like", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameNotLike(String value) {
            addCriterion("test_case_name not like", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameIn(List<String> values) {
            addCriterion("test_case_name in", values, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameNotIn(List<String> values) {
            addCriterion("test_case_name not in", values, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameBetween(String value1, String value2) {
            addCriterion("test_case_name between", value1, value2, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameNotBetween(String value1, String value2) {
            addCriterion("test_case_name not between", value1, value2, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andRunPriorityIsNull() {
            addCriterion("run_priority is null");
            return (Criteria) this;
        }

        public Criteria andRunPriorityIsNotNull() {
            addCriterion("run_priority is not null");
            return (Criteria) this;
        }

        public Criteria andRunPriorityEqualTo(Long value) {
            addCriterion("run_priority =", value, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_priority = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunPriorityNotEqualTo(Long value) {
            addCriterion("run_priority <>", value, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_priority <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunPriorityGreaterThan(Long value) {
            addCriterion("run_priority >", value, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_priority > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunPriorityGreaterThanOrEqualTo(Long value) {
            addCriterion("run_priority >=", value, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_priority >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunPriorityLessThan(Long value) {
            addCriterion("run_priority <", value, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_priority < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunPriorityLessThanOrEqualTo(Long value) {
            addCriterion("run_priority <=", value, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_priority <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunPriorityIn(List<Long> values) {
            addCriterion("run_priority in", values, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityNotIn(List<Long> values) {
            addCriterion("run_priority not in", values, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityBetween(Long value1, Long value2) {
            addCriterion("run_priority between", value1, value2, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunPriorityNotBetween(Long value1, Long value2) {
            addCriterion("run_priority not between", value1, value2, "runPriority");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenIsNull() {
            addCriterion("run_result_overwritten is null");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenIsNotNull() {
            addCriterion("run_result_overwritten is not null");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenEqualTo(Integer value) {
            addCriterion("run_result_overwritten =", value, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_result_overwritten = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenNotEqualTo(Integer value) {
            addCriterion("run_result_overwritten <>", value, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_result_overwritten <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenGreaterThan(Integer value) {
            addCriterion("run_result_overwritten >", value, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_result_overwritten > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenGreaterThanOrEqualTo(Integer value) {
            addCriterion("run_result_overwritten >=", value, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_result_overwritten >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenLessThan(Integer value) {
            addCriterion("run_result_overwritten <", value, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_result_overwritten < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenLessThanOrEqualTo(Integer value) {
            addCriterion("run_result_overwritten <=", value, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_result_overwritten <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenIn(List<Integer> values) {
            addCriterion("run_result_overwritten in", values, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenNotIn(List<Integer> values) {
            addCriterion("run_result_overwritten not in", values, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenBetween(Integer value1, Integer value2) {
            addCriterion("run_result_overwritten between", value1, value2, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunResultOverwrittenNotBetween(Integer value1, Integer value2) {
            addCriterion("run_result_overwritten not between", value1, value2, "runResultOverwritten");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdIsNull() {
            addCriterion("run_project_id is null");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdIsNotNull() {
            addCriterion("run_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdEqualTo(Long value) {
            addCriterion("run_project_id =", value, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_project_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunProjectIdNotEqualTo(Long value) {
            addCriterion("run_project_id <>", value, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_project_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunProjectIdGreaterThan(Long value) {
            addCriterion("run_project_id >", value, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_project_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_project_id >=", value, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_project_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunProjectIdLessThan(Long value) {
            addCriterion("run_project_id <", value, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_project_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("run_project_id <=", value, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_project_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunProjectIdIn(List<Long> values) {
            addCriterion("run_project_id in", values, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdNotIn(List<Long> values) {
            addCriterion("run_project_id not in", values, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdBetween(Long value1, Long value2) {
            addCriterion("run_project_id between", value1, value2, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andRunProjectIdNotBetween(Long value1, Long value2) {
            addCriterion("run_project_id not between", value1, value2, "runProjectId");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountIsNull() {
            addCriterion("instruction_fail_count is null");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountIsNotNull() {
            addCriterion("instruction_fail_count is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountEqualTo(Integer value) {
            addCriterion("instruction_fail_count =", value, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_fail_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountNotEqualTo(Integer value) {
            addCriterion("instruction_fail_count <>", value, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_fail_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountGreaterThan(Integer value) {
            addCriterion("instruction_fail_count >", value, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_fail_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("instruction_fail_count >=", value, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_fail_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountLessThan(Integer value) {
            addCriterion("instruction_fail_count <", value, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_fail_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountLessThanOrEqualTo(Integer value) {
            addCriterion("instruction_fail_count <=", value, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("instruction_fail_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountIn(List<Integer> values) {
            addCriterion("instruction_fail_count in", values, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountNotIn(List<Integer> values) {
            addCriterion("instruction_fail_count not in", values, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountBetween(Integer value1, Integer value2) {
            addCriterion("instruction_fail_count between", value1, value2, "instructionFailCount");
            return (Criteria) this;
        }

        public Criteria andInstructionFailCountNotBetween(Integer value1, Integer value2) {
            addCriterion("instruction_fail_count not between", value1, value2, "instructionFailCount");
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

        public Criteria andFinishedEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("is_finished = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("is_finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("is_finished <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("is_finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("is_finished > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("is_finished >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("is_finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("is_finished < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
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

        public Criteria andRunStartAtIsNull() {
            addCriterion("run_start_at is null");
            return (Criteria) this;
        }

        public Criteria andRunStartAtIsNotNull() {
            addCriterion("run_start_at is not null");
            return (Criteria) this;
        }

        public Criteria andRunStartAtEqualTo(Date value) {
            addCriterion("run_start_at =", value, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_start_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStartAtNotEqualTo(Date value) {
            addCriterion("run_start_at <>", value, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_start_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStartAtGreaterThan(Date value) {
            addCriterion("run_start_at >", value, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_start_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStartAtGreaterThanOrEqualTo(Date value) {
            addCriterion("run_start_at >=", value, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_start_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStartAtLessThan(Date value) {
            addCriterion("run_start_at <", value, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_start_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStartAtLessThanOrEqualTo(Date value) {
            addCriterion("run_start_at <=", value, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_start_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunStartAtIn(List<Date> values) {
            addCriterion("run_start_at in", values, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtNotIn(List<Date> values) {
            addCriterion("run_start_at not in", values, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtBetween(Date value1, Date value2) {
            addCriterion("run_start_at between", value1, value2, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunStartAtNotBetween(Date value1, Date value2) {
            addCriterion("run_start_at not between", value1, value2, "runStartAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtIsNull() {
            addCriterion("run_end_at is null");
            return (Criteria) this;
        }

        public Criteria andRunEndAtIsNotNull() {
            addCriterion("run_end_at is not null");
            return (Criteria) this;
        }

        public Criteria andRunEndAtEqualTo(Date value) {
            addCriterion("run_end_at =", value, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_end_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunEndAtNotEqualTo(Date value) {
            addCriterion("run_end_at <>", value, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_end_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunEndAtGreaterThan(Date value) {
            addCriterion("run_end_at >", value, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_end_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("run_end_at >=", value, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_end_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunEndAtLessThan(Date value) {
            addCriterion("run_end_at <", value, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_end_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunEndAtLessThanOrEqualTo(Date value) {
            addCriterion("run_end_at <=", value, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("run_end_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunEndAtIn(List<Date> values) {
            addCriterion("run_end_at in", values, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtNotIn(List<Date> values) {
            addCriterion("run_end_at not in", values, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtBetween(Date value1, Date value2) {
            addCriterion("run_end_at between", value1, value2, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andRunEndAtNotBetween(Date value1, Date value2) {
            addCriterion("run_end_at not between", value1, value2, "runEndAt");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Long value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("duration = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Long value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("duration <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Long value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("duration > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Long value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("duration >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Long value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("duration < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Long value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualToColumn(RunExecutionInfo.Column column) {
            addCriterion(new StringBuilder("duration <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Long> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Long> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Long value1, Long value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Long value1, Long value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andRunNameLikeInsensitive(String value) {
            addCriterion("upper(run_name) like", value.toUpperCase(), "runName");
            return (Criteria) this;
        }

        public Criteria andTriggerSourceLikeInsensitive(String value) {
            addCriterion("upper(trigger_source) like", value.toUpperCase(), "triggerSource");
            return (Criteria) this;
        }

        public Criteria andDriverPackNameLikeInsensitive(String value) {
            addCriterion("upper(driver_pack_name) like", value.toUpperCase(), "driverPackName");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteNameLikeInsensitive(String value) {
            addCriterion("upper(test_case_overwrite_name) like", value.toUpperCase(), "testCaseOverwriteName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLikeInsensitive(String value) {
            addCriterion("upper(test_case_name) like", value.toUpperCase(), "testCaseName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private RunExecutionInfoExample example;

        protected Criteria(RunExecutionInfoExample example) {
            super();
            this.example = example;
        }

        public RunExecutionInfoExample example() {
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
        void example(com.meowlomo.atm.core.model.RunExecutionInfoExample example);
    }
}