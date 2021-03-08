package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectReportInfoExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectReportInfoExample() {
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

    public ProjectReportInfoExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public ProjectReportInfoExample orderBy(String... orderByClauses) {
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
        ProjectReportInfoExample example = new ProjectReportInfoExample();
        return example.createCriteria();
    }

    public ProjectReportInfoExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public ProjectReportInfoExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andProjectIdEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtIsNull() {
            addCriterion("project_created_at is null");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtIsNotNull() {
            addCriterion("project_created_at is not null");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtEqualTo(Date value) {
            addCriterion("project_created_at =", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtNotEqualTo(Date value) {
            addCriterion("project_created_at <>", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThan(Date value) {
            addCriterion("project_created_at >", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("project_created_at >=", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThan(Date value) {
            addCriterion("project_created_at <", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("project_created_at <=", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtIn(List<Date> values) {
            addCriterion("project_created_at in", values, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtNotIn(List<Date> values) {
            addCriterion("project_created_at not in", values, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtBetween(Date value1, Date value2) {
            addCriterion("project_created_at between", value1, value2, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("project_created_at not between", value1, value2, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberIsNull() {
            addCriterion("active_test_case_number is null");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberIsNotNull() {
            addCriterion("active_test_case_number is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberEqualTo(Integer value) {
            addCriterion("active_test_case_number =", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("active_test_case_number <>", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThan(Integer value) {
            addCriterion("active_test_case_number >", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_test_case_number >=", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThan(Integer value) {
            addCriterion("active_test_case_number <", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("active_test_case_number <=", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberIn(List<Integer> values) {
            addCriterion("active_test_case_number in", values, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberNotIn(List<Integer> values) {
            addCriterion("active_test_case_number not in", values, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberBetween(Integer value1, Integer value2) {
            addCriterion("active_test_case_number between", value1, value2, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("active_test_case_number not between", value1, value2, "activeTestCaseNumber");
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

        public Criteria andTotalRunNumberEqualTo(Long value) {
            addCriterion("total_run_number =", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_run_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotEqualTo(Long value) {
            addCriterion("total_run_number <>", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_run_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThan(Long value) {
            addCriterion("total_run_number >", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_run_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("total_run_number >=", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_run_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThan(Long value) {
            addCriterion("total_run_number <", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_run_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThanOrEqualTo(Long value) {
            addCriterion("total_run_number <=", value, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_run_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberIn(List<Long> values) {
            addCriterion("total_run_number in", values, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotIn(List<Long> values) {
            addCriterion("total_run_number not in", values, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberBetween(Long value1, Long value2) {
            addCriterion("total_run_number between", value1, value2, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalRunNumberNotBetween(Long value1, Long value2) {
            addCriterion("total_run_number not between", value1, value2, "totalRunNumber");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeIsNull() {
            addCriterion("total_execution_time is null");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeIsNotNull() {
            addCriterion("total_execution_time is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeEqualTo(Long value) {
            addCriterion("total_execution_time =", value, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_execution_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeNotEqualTo(Long value) {
            addCriterion("total_execution_time <>", value, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_execution_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeGreaterThan(Long value) {
            addCriterion("total_execution_time >", value, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_execution_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("total_execution_time >=", value, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_execution_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeLessThan(Long value) {
            addCriterion("total_execution_time <", value, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_execution_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeLessThanOrEqualTo(Long value) {
            addCriterion("total_execution_time <=", value, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_execution_time <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeIn(List<Long> values) {
            addCriterion("total_execution_time in", values, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeNotIn(List<Long> values) {
            addCriterion("total_execution_time not in", values, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeBetween(Long value1, Long value2) {
            addCriterion("total_execution_time between", value1, value2, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andTotalExecutionTimeNotBetween(Long value1, Long value2) {
            addCriterion("total_execution_time not between", value1, value2, "totalExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberIsNull() {
            addCriterion("executed_test_case_number is null");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberIsNotNull() {
            addCriterion("executed_test_case_number is not null");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberEqualTo(Integer value) {
            addCriterion("executed_test_case_number =", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("executed_test_case_number <>", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThan(Integer value) {
            addCriterion("executed_test_case_number >", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("executed_test_case_number >=", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThan(Integer value) {
            addCriterion("executed_test_case_number <", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("executed_test_case_number <=", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberIn(List<Integer> values) {
            addCriterion("executed_test_case_number in", values, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberNotIn(List<Integer> values) {
            addCriterion("executed_test_case_number not in", values, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberBetween(Integer value1, Integer value2) {
            addCriterion("executed_test_case_number between", value1, value2, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("executed_test_case_number not between", value1, value2, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberIsNull() {
            addCriterion("failed_test_case_number is null");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberIsNotNull() {
            addCriterion("failed_test_case_number is not null");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberEqualTo(Integer value) {
            addCriterion("failed_test_case_number =", value, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("failed_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("failed_test_case_number <>", value, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("failed_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberGreaterThan(Integer value) {
            addCriterion("failed_test_case_number >", value, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("failed_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("failed_test_case_number >=", value, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("failed_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberLessThan(Integer value) {
            addCriterion("failed_test_case_number <", value, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("failed_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("failed_test_case_number <=", value, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("failed_test_case_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberIn(List<Integer> values) {
            addCriterion("failed_test_case_number in", values, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberNotIn(List<Integer> values) {
            addCriterion("failed_test_case_number not in", values, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberBetween(Integer value1, Integer value2) {
            addCriterion("failed_test_case_number between", value1, value2, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andFailedTestCaseNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("failed_test_case_number not between", value1, value2, "failedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberIsNull() {
            addCriterion("passed_test_case_number is null");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberIsNotNull() {
            addCriterion("passed_test_case_number is not null");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberEqualTo(Integer value) {
            addCriterion("passed_test_case_number =", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("passed_test_case_number <>", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThan(Integer value) {
            addCriterion("passed_test_case_number >", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("passed_test_case_number >=", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThan(Integer value) {
            addCriterion("passed_test_case_number <", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("passed_test_case_number <=", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberIn(List<Integer> values) {
            addCriterion("passed_test_case_number in", values, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberNotIn(List<Integer> values) {
            addCriterion("passed_test_case_number not in", values, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberBetween(Integer value1, Integer value2) {
            addCriterion("passed_test_case_number between", value1, value2, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("passed_test_case_number not between", value1, value2, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassRateIsNull() {
            addCriterion("pass_rate is null");
            return (Criteria) this;
        }

        public Criteria andPassRateIsNotNull() {
            addCriterion("pass_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPassRateEqualTo(BigDecimal value) {
            addCriterion("pass_rate =", value, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("pass_rate = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassRateNotEqualTo(BigDecimal value) {
            addCriterion("pass_rate <>", value, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("pass_rate <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassRateGreaterThan(BigDecimal value) {
            addCriterion("pass_rate >", value, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("pass_rate > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pass_rate >=", value, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("pass_rate >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassRateLessThan(BigDecimal value) {
            addCriterion("pass_rate <", value, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("pass_rate < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pass_rate <=", value, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("pass_rate <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassRateIn(List<BigDecimal> values) {
            addCriterion("pass_rate in", values, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateNotIn(List<BigDecimal> values) {
            addCriterion("pass_rate not in", values, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pass_rate between", value1, value2, "passRate");
            return (Criteria) this;
        }

        public Criteria andPassRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pass_rate not between", value1, value2, "passRate");
            return (Criteria) this;
        }

        public Criteria andFailRateIsNull() {
            addCriterion("fail_rate is null");
            return (Criteria) this;
        }

        public Criteria andFailRateIsNotNull() {
            addCriterion("fail_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFailRateEqualTo(BigDecimal value) {
            addCriterion("fail_rate =", value, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("fail_rate = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailRateNotEqualTo(BigDecimal value) {
            addCriterion("fail_rate <>", value, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("fail_rate <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailRateGreaterThan(BigDecimal value) {
            addCriterion("fail_rate >", value, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("fail_rate > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fail_rate >=", value, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("fail_rate >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailRateLessThan(BigDecimal value) {
            addCriterion("fail_rate <", value, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("fail_rate < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fail_rate <=", value, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("fail_rate <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFailRateIn(List<BigDecimal> values) {
            addCriterion("fail_rate in", values, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateNotIn(List<BigDecimal> values) {
            addCriterion("fail_rate not in", values, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fail_rate between", value1, value2, "failRate");
            return (Criteria) this;
        }

        public Criteria andFailRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fail_rate not between", value1, value2, "failRate");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberIsNull() {
            addCriterion("total_test_case_number is null");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberIsNotNull() {
            addCriterion("total_test_case_number is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberEqualTo(Integer value) {
            addCriterion("total_test_case_number =", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("total_test_case_number <>", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberNotEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThan(Integer value) {
            addCriterion("total_test_case_number >", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_test_case_number >=", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThan(Integer value) {
            addCriterion("total_test_case_number <", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThanColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("total_test_case_number <=", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThanOrEqualToColumn(ProjectReportInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberIn(List<Integer> values) {
            addCriterion("total_test_case_number in", values, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberNotIn(List<Integer> values) {
            addCriterion("total_test_case_number not in", values, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberBetween(Integer value1, Integer value2) {
            addCriterion("total_test_case_number between", value1, value2, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("total_test_case_number not between", value1, value2, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andProjectNameLikeInsensitive(String value) {
            addCriterion("upper(project_name) like", value.toUpperCase(), "projectName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private ProjectReportInfoExample example;

        protected Criteria(ProjectReportInfoExample example) {
            super();
            this.example = example;
        }

        public ProjectReportInfoExample example() {
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
        void example(com.meowlomo.atm.core.model.ProjectReportInfoExample example);
    }
}