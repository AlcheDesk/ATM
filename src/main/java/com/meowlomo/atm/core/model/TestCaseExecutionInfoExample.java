package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCaseExecutionInfoExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestCaseExecutionInfoExample() {
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

    public TestCaseExecutionInfoExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public TestCaseExecutionInfoExample orderBy(String... orderByClauses) {
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
        TestCaseExecutionInfoExample example = new TestCaseExecutionInfoExample();
        return example.createCriteria();
    }

    public TestCaseExecutionInfoExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public TestCaseExecutionInfoExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> latestRunStatusCriteria;

        protected List<Criterion> latestDevRunStatusCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            latestRunStatusCriteria = new ArrayList<Criterion>();
            latestDevRunStatusCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getLatestRunStatusCriteria() {
            return latestRunStatusCriteria;
        }

        protected void addLatestRunStatusCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            latestRunStatusCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        protected void addLatestRunStatusCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            latestRunStatusCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getLatestDevRunStatusCriteria() {
            return latestDevRunStatusCriteria;
        }

        protected void addLatestDevRunStatusCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            latestDevRunStatusCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        protected void addLatestDevRunStatusCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            latestDevRunStatusCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || latestRunStatusCriteria.size() > 0 || latestDevRunStatusCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(latestRunStatusCriteria);
                allCriteria.addAll(latestDevRunStatusCriteria);
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

        public Criteria andTestCaseIdEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualTo(Long value) {
            addCriterion("test_case_id <>", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThan(Long value) {
            addCriterion("test_case_id >", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_id >=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThan(Long value) {
            addCriterion("test_case_id <", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_id <=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
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

        public Criteria andTestCaseNameEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameNotEqualTo(String value) {
            addCriterion("test_case_name <>", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThan(String value) {
            addCriterion("test_case_name >", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("test_case_name >=", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThan(String value) {
            addCriterion("test_case_name <", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThanOrEqualTo(String value) {
            addCriterion("test_case_name <=", value, "testCaseName");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
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

        public Criteria andTestCaseCreatedAtIsNull() {
            addCriterion("test_case_created_at is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtIsNotNull() {
            addCriterion("test_case_created_at is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtEqualTo(Date value) {
            addCriterion("test_case_created_at =", value, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtNotEqualTo(Date value) {
            addCriterion("test_case_created_at <>", value, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtGreaterThan(Date value) {
            addCriterion("test_case_created_at >", value, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("test_case_created_at >=", value, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtLessThan(Date value) {
            addCriterion("test_case_created_at <", value, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("test_case_created_at <=", value, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_created_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtIn(List<Date> values) {
            addCriterion("test_case_created_at in", values, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtNotIn(List<Date> values) {
            addCriterion("test_case_created_at not in", values, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtBetween(Date value1, Date value2) {
            addCriterion("test_case_created_at between", value1, value2, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTestCaseCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("test_case_created_at not between", value1, value2, "testCaseCreatedAt");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountIsNull() {
            addCriterion("total_run_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountIsNotNull() {
            addCriterion("total_run_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountEqualTo(Long value) {
            addCriterion("total_run_count =", value, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_run_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunCountNotEqualTo(Long value) {
            addCriterion("total_run_count <>", value, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_run_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunCountGreaterThan(Long value) {
            addCriterion("total_run_count >", value, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_run_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunCountGreaterThanOrEqualTo(Long value) {
            addCriterion("total_run_count >=", value, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_run_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunCountLessThan(Long value) {
            addCriterion("total_run_count <", value, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_run_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunCountLessThanOrEqualTo(Long value) {
            addCriterion("total_run_count <=", value, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_run_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunCountIn(List<Long> values) {
            addCriterion("total_run_count in", values, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountNotIn(List<Long> values) {
            addCriterion("total_run_count not in", values, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountBetween(Long value1, Long value2) {
            addCriterion("total_run_count between", value1, value2, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalRunCountNotBetween(Long value1, Long value2) {
            addCriterion("total_run_count not between", value1, value2, "totalRunCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusIsNull() {
            addCriterion("latest_run_status_id is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusIsNotNull() {
            addCriterion("latest_run_status_id is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusEqualTo(String value) {
            addLatestRunStatusCriterion("latest_run_status_id =", value, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusNotEqualTo(String value) {
            addLatestRunStatusCriterion("latest_run_status_id <>", value, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusGreaterThan(String value) {
            addLatestRunStatusCriterion("latest_run_status_id >", value, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusGreaterThanOrEqualTo(String value) {
            addLatestRunStatusCriterion("latest_run_status_id >=", value, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusLessThan(String value) {
            addLatestRunStatusCriterion("latest_run_status_id <", value, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusLessThanOrEqualTo(String value) {
            addLatestRunStatusCriterion("latest_run_status_id <=", value, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_status_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusIn(List<String> values) {
            addLatestRunStatusCriterion("latest_run_status_id in", values, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusNotIn(List<String> values) {
            addLatestRunStatusCriterion("latest_run_status_id not in", values, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusBetween(String value1, String value2) {
            addLatestRunStatusCriterion("latest_run_status_id between", value1, value2, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunStatusNotBetween(String value1, String value2) {
            addLatestRunStatusCriterion("latest_run_status_id not between", value1, value2, "latestRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtIsNull() {
            addCriterion("latest_run_updated_at is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtIsNotNull() {
            addCriterion("latest_run_updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtEqualTo(Date value) {
            addCriterion("latest_run_updated_at =", value, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtNotEqualTo(Date value) {
            addCriterion("latest_run_updated_at <>", value, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtGreaterThan(Date value) {
            addCriterion("latest_run_updated_at >", value, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("latest_run_updated_at >=", value, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtLessThan(Date value) {
            addCriterion("latest_run_updated_at <", value, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("latest_run_updated_at <=", value, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_updated_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtIn(List<Date> values) {
            addCriterion("latest_run_updated_at in", values, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtNotIn(List<Date> values) {
            addCriterion("latest_run_updated_at not in", values, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("latest_run_updated_at between", value1, value2, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("latest_run_updated_at not between", value1, value2, "latestRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountIsNull() {
            addCriterion("latest_run_instruction_executed_count is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountIsNotNull() {
            addCriterion("latest_run_instruction_executed_count is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountEqualTo(Long value) {
            addCriterion("latest_run_instruction_executed_count =", value, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_executed_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountNotEqualTo(Long value) {
            addCriterion("latest_run_instruction_executed_count <>", value, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_executed_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountGreaterThan(Long value) {
            addCriterion("latest_run_instruction_executed_count >", value, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_executed_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_run_instruction_executed_count >=", value, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_executed_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountLessThan(Long value) {
            addCriterion("latest_run_instruction_executed_count <", value, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_executed_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountLessThanOrEqualTo(Long value) {
            addCriterion("latest_run_instruction_executed_count <=", value, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_executed_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountIn(List<Long> values) {
            addCriterion("latest_run_instruction_executed_count in", values, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountNotIn(List<Long> values) {
            addCriterion("latest_run_instruction_executed_count not in", values, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountBetween(Long value1, Long value2) {
            addCriterion("latest_run_instruction_executed_count between", value1, value2, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionExecutedCountNotBetween(Long value1, Long value2) {
            addCriterion("latest_run_instruction_executed_count not between", value1, value2, "latestRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountIsNull() {
            addCriterion("latest_run_instruction_pass_count is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountIsNotNull() {
            addCriterion("latest_run_instruction_pass_count is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountEqualTo(Long value) {
            addCriterion("latest_run_instruction_pass_count =", value, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_pass_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountNotEqualTo(Long value) {
            addCriterion("latest_run_instruction_pass_count <>", value, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_pass_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountGreaterThan(Long value) {
            addCriterion("latest_run_instruction_pass_count >", value, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_pass_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_run_instruction_pass_count >=", value, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_pass_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountLessThan(Long value) {
            addCriterion("latest_run_instruction_pass_count <", value, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_pass_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountLessThanOrEqualTo(Long value) {
            addCriterion("latest_run_instruction_pass_count <=", value, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_pass_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountIn(List<Long> values) {
            addCriterion("latest_run_instruction_pass_count in", values, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountNotIn(List<Long> values) {
            addCriterion("latest_run_instruction_pass_count not in", values, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountBetween(Long value1, Long value2) {
            addCriterion("latest_run_instruction_pass_count between", value1, value2, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionPassCountNotBetween(Long value1, Long value2) {
            addCriterion("latest_run_instruction_pass_count not between", value1, value2, "latestRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceIsNull() {
            addCriterion("latest_run_trigger_source is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceIsNotNull() {
            addCriterion("latest_run_trigger_source is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceEqualTo(String value) {
            addCriterion("latest_run_trigger_source =", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_trigger_source = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceNotEqualTo(String value) {
            addCriterion("latest_run_trigger_source <>", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_trigger_source <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceGreaterThan(String value) {
            addCriterion("latest_run_trigger_source >", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_trigger_source > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceGreaterThanOrEqualTo(String value) {
            addCriterion("latest_run_trigger_source >=", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_trigger_source >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceLessThan(String value) {
            addCriterion("latest_run_trigger_source <", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_trigger_source < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceLessThanOrEqualTo(String value) {
            addCriterion("latest_run_trigger_source <=", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_trigger_source <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceLike(String value) {
            addCriterion("latest_run_trigger_source like", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceNotLike(String value) {
            addCriterion("latest_run_trigger_source not like", value, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceIn(List<String> values) {
            addCriterion("latest_run_trigger_source in", values, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceNotIn(List<String> values) {
            addCriterion("latest_run_trigger_source not in", values, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceBetween(String value1, String value2) {
            addCriterion("latest_run_trigger_source between", value1, value2, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceNotBetween(String value1, String value2) {
            addCriterion("latest_run_trigger_source not between", value1, value2, "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtIsNull() {
            addCriterion("latest_run_created_at is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtIsNotNull() {
            addCriterion("latest_run_created_at is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtEqualTo(Date value) {
            addCriterion("latest_run_created_at =", value, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtNotEqualTo(Date value) {
            addCriterion("latest_run_created_at <>", value, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtGreaterThan(Date value) {
            addCriterion("latest_run_created_at >", value, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("latest_run_created_at >=", value, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtLessThan(Date value) {
            addCriterion("latest_run_created_at <", value, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("latest_run_created_at <=", value, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_created_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtIn(List<Date> values) {
            addCriterion("latest_run_created_at in", values, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtNotIn(List<Date> values) {
            addCriterion("latest_run_created_at not in", values, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtBetween(Date value1, Date value2) {
            addCriterion("latest_run_created_at between", value1, value2, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("latest_run_created_at not between", value1, value2, "latestRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdIsNull() {
            addCriterion("latest_run_id is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdIsNotNull() {
            addCriterion("latest_run_id is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdEqualTo(Long value) {
            addCriterion("latest_run_id =", value, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunIdNotEqualTo(Long value) {
            addCriterion("latest_run_id <>", value, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunIdGreaterThan(Long value) {
            addCriterion("latest_run_id >", value, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunIdGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_run_id >=", value, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunIdLessThan(Long value) {
            addCriterion("latest_run_id <", value, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunIdLessThanOrEqualTo(Long value) {
            addCriterion("latest_run_id <=", value, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunIdIn(List<Long> values) {
            addCriterion("latest_run_id in", values, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdNotIn(List<Long> values) {
            addCriterion("latest_run_id not in", values, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdBetween(Long value1, Long value2) {
            addCriterion("latest_run_id between", value1, value2, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunIdNotBetween(Long value1, Long value2) {
            addCriterion("latest_run_id not between", value1, value2, "latestRunId");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberIsNull() {
            addCriterion("latest_run_executable_instruction_number is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberIsNotNull() {
            addCriterion("latest_run_executable_instruction_number is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberEqualTo(Long value) {
            addCriterion("latest_run_executable_instruction_number =", value, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_executable_instruction_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberNotEqualTo(Long value) {
            addCriterion("latest_run_executable_instruction_number <>", value, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_executable_instruction_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberGreaterThan(Long value) {
            addCriterion("latest_run_executable_instruction_number >", value, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_executable_instruction_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_run_executable_instruction_number >=", value, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_executable_instruction_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberLessThan(Long value) {
            addCriterion("latest_run_executable_instruction_number <", value, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_executable_instruction_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberLessThanOrEqualTo(Long value) {
            addCriterion("latest_run_executable_instruction_number <=", value, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_executable_instruction_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberIn(List<Long> values) {
            addCriterion("latest_run_executable_instruction_number in", values, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberNotIn(List<Long> values) {
            addCriterion("latest_run_executable_instruction_number not in", values, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberBetween(Long value1, Long value2) {
            addCriterion("latest_run_executable_instruction_number between", value1, value2, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunExecutableInstructionNumberNotBetween(Long value1, Long value2) {
            addCriterion("latest_run_executable_instruction_number not between", value1, value2, "latestRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountIsNull() {
            addCriterion("total_dev_run_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountIsNotNull() {
            addCriterion("total_dev_run_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountEqualTo(Long value) {
            addCriterion("total_dev_run_count =", value, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_dev_run_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountNotEqualTo(Long value) {
            addCriterion("total_dev_run_count <>", value, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_dev_run_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountGreaterThan(Long value) {
            addCriterion("total_dev_run_count >", value, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_dev_run_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountGreaterThanOrEqualTo(Long value) {
            addCriterion("total_dev_run_count >=", value, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_dev_run_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountLessThan(Long value) {
            addCriterion("total_dev_run_count <", value, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_dev_run_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountLessThanOrEqualTo(Long value) {
            addCriterion("total_dev_run_count <=", value, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_dev_run_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountIn(List<Long> values) {
            addCriterion("total_dev_run_count in", values, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountNotIn(List<Long> values) {
            addCriterion("total_dev_run_count not in", values, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountBetween(Long value1, Long value2) {
            addCriterion("total_dev_run_count between", value1, value2, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andTotalDevRunCountNotBetween(Long value1, Long value2) {
            addCriterion("total_dev_run_count not between", value1, value2, "totalDevRunCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusIsNull() {
            addCriterion("latest_dev_run_status_id is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusIsNotNull() {
            addCriterion("latest_dev_run_status_id is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusEqualTo(String value) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id =", value, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusNotEqualTo(String value) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id <>", value, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusGreaterThan(String value) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id >", value, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusGreaterThanOrEqualTo(String value) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id >=", value, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusLessThan(String value) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id <", value, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusLessThanOrEqualTo(String value) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id <=", value, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_status_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusIn(List<String> values) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id in", values, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusNotIn(List<String> values) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id not in", values, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusBetween(String value1, String value2) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id between", value1, value2, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunStatusNotBetween(String value1, String value2) {
            addLatestDevRunStatusCriterion("latest_dev_run_status_id not between", value1, value2, "latestDevRunStatus");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtIsNull() {
            addCriterion("latest_dev_run_updated_at is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtIsNotNull() {
            addCriterion("latest_dev_run_updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtEqualTo(Date value) {
            addCriterion("latest_dev_run_updated_at =", value, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtNotEqualTo(Date value) {
            addCriterion("latest_dev_run_updated_at <>", value, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtGreaterThan(Date value) {
            addCriterion("latest_dev_run_updated_at >", value, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("latest_dev_run_updated_at >=", value, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtLessThan(Date value) {
            addCriterion("latest_dev_run_updated_at <", value, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("latest_dev_run_updated_at <=", value, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_updated_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtIn(List<Date> values) {
            addCriterion("latest_dev_run_updated_at in", values, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtNotIn(List<Date> values) {
            addCriterion("latest_dev_run_updated_at not in", values, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("latest_dev_run_updated_at between", value1, value2, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("latest_dev_run_updated_at not between", value1, value2, "latestDevRunUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountIsNull() {
            addCriterion("latest_dev_run_instruction_executed_count is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountIsNotNull() {
            addCriterion("latest_dev_run_instruction_executed_count is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_executed_count =", value, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_executed_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountNotEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_executed_count <>", value, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_executed_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountGreaterThan(Long value) {
            addCriterion("latest_dev_run_instruction_executed_count >", value, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_executed_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_executed_count >=", value, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_executed_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountLessThan(Long value) {
            addCriterion("latest_dev_run_instruction_executed_count <", value, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_executed_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountLessThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_executed_count <=", value, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_executed_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountIn(List<Long> values) {
            addCriterion("latest_dev_run_instruction_executed_count in", values, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountNotIn(List<Long> values) {
            addCriterion("latest_dev_run_instruction_executed_count not in", values, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_instruction_executed_count between", value1, value2, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionExecutedCountNotBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_instruction_executed_count not between", value1, value2, "latestDevRunInstructionExecutedCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountIsNull() {
            addCriterion("latest_dev_run_instruction_pass_count is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountIsNotNull() {
            addCriterion("latest_dev_run_instruction_pass_count is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_pass_count =", value, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_pass_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountNotEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_pass_count <>", value, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_pass_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountGreaterThan(Long value) {
            addCriterion("latest_dev_run_instruction_pass_count >", value, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_pass_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_pass_count >=", value, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_pass_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountLessThan(Long value) {
            addCriterion("latest_dev_run_instruction_pass_count <", value, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_pass_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountLessThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_instruction_pass_count <=", value, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_pass_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountIn(List<Long> values) {
            addCriterion("latest_dev_run_instruction_pass_count in", values, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountNotIn(List<Long> values) {
            addCriterion("latest_dev_run_instruction_pass_count not in", values, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_instruction_pass_count between", value1, value2, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionPassCountNotBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_instruction_pass_count not between", value1, value2, "latestDevRunInstructionPassCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceIsNull() {
            addCriterion("latest_dev_run_trigger_source is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceIsNotNull() {
            addCriterion("latest_dev_run_trigger_source is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceEqualTo(String value) {
            addCriterion("latest_dev_run_trigger_source =", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_trigger_source = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceNotEqualTo(String value) {
            addCriterion("latest_dev_run_trigger_source <>", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_trigger_source <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceGreaterThan(String value) {
            addCriterion("latest_dev_run_trigger_source >", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_trigger_source > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceGreaterThanOrEqualTo(String value) {
            addCriterion("latest_dev_run_trigger_source >=", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_trigger_source >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceLessThan(String value) {
            addCriterion("latest_dev_run_trigger_source <", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_trigger_source < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceLessThanOrEqualTo(String value) {
            addCriterion("latest_dev_run_trigger_source <=", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_trigger_source <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceLike(String value) {
            addCriterion("latest_dev_run_trigger_source like", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceNotLike(String value) {
            addCriterion("latest_dev_run_trigger_source not like", value, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceIn(List<String> values) {
            addCriterion("latest_dev_run_trigger_source in", values, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceNotIn(List<String> values) {
            addCriterion("latest_dev_run_trigger_source not in", values, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceBetween(String value1, String value2) {
            addCriterion("latest_dev_run_trigger_source between", value1, value2, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceNotBetween(String value1, String value2) {
            addCriterion("latest_dev_run_trigger_source not between", value1, value2, "latestDevRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtIsNull() {
            addCriterion("latest_dev_run_created_at is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtIsNotNull() {
            addCriterion("latest_dev_run_created_at is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtEqualTo(Date value) {
            addCriterion("latest_dev_run_created_at =", value, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtNotEqualTo(Date value) {
            addCriterion("latest_dev_run_created_at <>", value, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtGreaterThan(Date value) {
            addCriterion("latest_dev_run_created_at >", value, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("latest_dev_run_created_at >=", value, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtLessThan(Date value) {
            addCriterion("latest_dev_run_created_at <", value, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("latest_dev_run_created_at <=", value, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_created_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtIn(List<Date> values) {
            addCriterion("latest_dev_run_created_at in", values, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtNotIn(List<Date> values) {
            addCriterion("latest_dev_run_created_at not in", values, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtBetween(Date value1, Date value2) {
            addCriterion("latest_dev_run_created_at between", value1, value2, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("latest_dev_run_created_at not between", value1, value2, "latestDevRunCreatedAt");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdIsNull() {
            addCriterion("latest_dev_run_id is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdIsNotNull() {
            addCriterion("latest_dev_run_id is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdEqualTo(Long value) {
            addCriterion("latest_dev_run_id =", value, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdNotEqualTo(Long value) {
            addCriterion("latest_dev_run_id <>", value, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdGreaterThan(Long value) {
            addCriterion("latest_dev_run_id >", value, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_id >=", value, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdLessThan(Long value) {
            addCriterion("latest_dev_run_id <", value, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdLessThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_id <=", value, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdIn(List<Long> values) {
            addCriterion("latest_dev_run_id in", values, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdNotIn(List<Long> values) {
            addCriterion("latest_dev_run_id not in", values, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_id between", value1, value2, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunIdNotBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_id not between", value1, value2, "latestDevRunId");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberIsNull() {
            addCriterion("latest_dev_run_executable_instruction_number is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberIsNotNull() {
            addCriterion("latest_dev_run_executable_instruction_number is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberEqualTo(Long value) {
            addCriterion("latest_dev_run_executable_instruction_number =", value, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_executable_instruction_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberNotEqualTo(Long value) {
            addCriterion("latest_dev_run_executable_instruction_number <>", value, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_executable_instruction_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberGreaterThan(Long value) {
            addCriterion("latest_dev_run_executable_instruction_number >", value, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_executable_instruction_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_executable_instruction_number >=", value, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_executable_instruction_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberLessThan(Long value) {
            addCriterion("latest_dev_run_executable_instruction_number <", value, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_executable_instruction_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberLessThanOrEqualTo(Long value) {
            addCriterion("latest_dev_run_executable_instruction_number <=", value, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_executable_instruction_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberIn(List<Long> values) {
            addCriterion("latest_dev_run_executable_instruction_number in", values, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberNotIn(List<Long> values) {
            addCriterion("latest_dev_run_executable_instruction_number not in", values, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_executable_instruction_number between", value1, value2, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunExecutableInstructionNumberNotBetween(Long value1, Long value2) {
            addCriterion("latest_dev_run_executable_instruction_number not between", value1, value2, "latestDevRunExecutableInstructionNumber");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountIsNull() {
            addCriterion("latest_run_instruction_fail_count is null");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountIsNotNull() {
            addCriterion("latest_run_instruction_fail_count is not null");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountEqualTo(Integer value) {
            addCriterion("latest_run_instruction_fail_count =", value, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_fail_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountNotEqualTo(Integer value) {
            addCriterion("latest_run_instruction_fail_count <>", value, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_fail_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountGreaterThan(Integer value) {
            addCriterion("latest_run_instruction_fail_count >", value, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_fail_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("latest_run_instruction_fail_count >=", value, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_fail_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountLessThan(Integer value) {
            addCriterion("latest_run_instruction_fail_count <", value, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_fail_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountLessThanOrEqualTo(Integer value) {
            addCriterion("latest_run_instruction_fail_count <=", value, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_run_instruction_fail_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountIn(List<Integer> values) {
            addCriterion("latest_run_instruction_fail_count in", values, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountNotIn(List<Integer> values) {
            addCriterion("latest_run_instruction_fail_count not in", values, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountBetween(Integer value1, Integer value2) {
            addCriterion("latest_run_instruction_fail_count between", value1, value2, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestRunInstructionFailCountNotBetween(Integer value1, Integer value2) {
            addCriterion("latest_run_instruction_fail_count not between", value1, value2, "latestRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountIsNull() {
            addCriterion("latest_dev_run_instruction_fail_count is null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountIsNotNull() {
            addCriterion("latest_dev_run_instruction_fail_count is not null");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountEqualTo(Integer value) {
            addCriterion("latest_dev_run_instruction_fail_count =", value, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_fail_count = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountNotEqualTo(Integer value) {
            addCriterion("latest_dev_run_instruction_fail_count <>", value, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_fail_count <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountGreaterThan(Integer value) {
            addCriterion("latest_dev_run_instruction_fail_count >", value, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_fail_count > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("latest_dev_run_instruction_fail_count >=", value, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_fail_count >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountLessThan(Integer value) {
            addCriterion("latest_dev_run_instruction_fail_count <", value, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_fail_count < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountLessThanOrEqualTo(Integer value) {
            addCriterion("latest_dev_run_instruction_fail_count <=", value, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("latest_dev_run_instruction_fail_count <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountIn(List<Integer> values) {
            addCriterion("latest_dev_run_instruction_fail_count in", values, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountNotIn(List<Integer> values) {
            addCriterion("latest_dev_run_instruction_fail_count not in", values, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountBetween(Integer value1, Integer value2) {
            addCriterion("latest_dev_run_instruction_fail_count between", value1, value2, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunInstructionFailCountNotBetween(Integer value1, Integer value2) {
            addCriterion("latest_dev_run_instruction_fail_count not between", value1, value2, "latestDevRunInstructionFailCount");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedIsNull() {
            addCriterion("test_case_is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedIsNotNull() {
            addCriterion("test_case_is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedEqualTo(Boolean value) {
            addCriterion("test_case_is_deleted =", value, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_is_deleted = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedNotEqualTo(Boolean value) {
            addCriterion("test_case_is_deleted <>", value, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_is_deleted <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedGreaterThan(Boolean value) {
            addCriterion("test_case_is_deleted >", value, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_is_deleted > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("test_case_is_deleted >=", value, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_is_deleted >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedLessThan(Boolean value) {
            addCriterion("test_case_is_deleted <", value, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_is_deleted < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("test_case_is_deleted <=", value, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_is_deleted <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedIn(List<Boolean> values) {
            addCriterion("test_case_is_deleted in", values, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedNotIn(List<Boolean> values) {
            addCriterion("test_case_is_deleted not in", values, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("test_case_is_deleted between", value1, value2, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("test_case_is_deleted not between", value1, value2, "testCaseIsDeleted");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdIsNull() {
            addCriterion("test_case_project_id is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdIsNotNull() {
            addCriterion("test_case_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdEqualTo(Long value) {
            addCriterion("test_case_project_id =", value, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_project_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdNotEqualTo(Long value) {
            addCriterion("test_case_project_id <>", value, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdNotEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_project_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdGreaterThan(Long value) {
            addCriterion("test_case_project_id >", value, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdGreaterThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_project_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_project_id >=", value, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdGreaterThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_project_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdLessThan(Long value) {
            addCriterion("test_case_project_id <", value, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdLessThanColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_project_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_project_id <=", value, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdLessThanOrEqualToColumn(TestCaseExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_project_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdIn(List<Long> values) {
            addCriterion("test_case_project_id in", values, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdNotIn(List<Long> values) {
            addCriterion("test_case_project_id not in", values, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdBetween(Long value1, Long value2) {
            addCriterion("test_case_project_id between", value1, value2, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseProjectIdNotBetween(Long value1, Long value2) {
            addCriterion("test_case_project_id not between", value1, value2, "testCaseProjectId");
            return (Criteria) this;
        }

        public Criteria andTestCaseNameLikeInsensitive(String value) {
            addCriterion("upper(test_case_name) like", value.toUpperCase(), "testCaseName");
            return (Criteria) this;
        }

        public Criteria andLatestRunTriggerSourceLikeInsensitive(String value) {
            addCriterion("upper(latest_run_trigger_source) like", value.toUpperCase(), "latestRunTriggerSource");
            return (Criteria) this;
        }

        public Criteria andLatestDevRunTriggerSourceLikeInsensitive(String value) {
            addCriterion("upper(latest_dev_run_trigger_source) like", value.toUpperCase(), "latestDevRunTriggerSource");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private TestCaseExecutionInfoExample example;

        protected Criteria(TestCaseExecutionInfoExample example) {
            super();
            this.example = example;
        }

        public TestCaseExecutionInfoExample example() {
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
        void example(com.meowlomo.atm.core.model.TestCaseExecutionInfoExample example);
    }
}