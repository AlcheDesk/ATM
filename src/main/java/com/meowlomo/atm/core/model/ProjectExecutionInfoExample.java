package com.meowlomo.atm.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ProjectExecutionInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExecutionInfoExample() {
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

    public ProjectExecutionInfoExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public ProjectExecutionInfoExample orderBy(String ... orderByClauses) {
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
        ProjectExecutionInfoExample example = new ProjectExecutionInfoExample();
        return example.createCriteria();
    }

    public ProjectExecutionInfoExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public ProjectExecutionInfoExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> activeTestCaseIdsCriteria;

        protected List<Criterion> executedTestCaseIdsCriteria;

        protected List<Criterion> passedTestCaseIdsCriteria;

        protected List<Criterion> testCaseIdsCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            activeTestCaseIdsCriteria = new ArrayList<Criterion>();
            executedTestCaseIdsCriteria = new ArrayList<Criterion>();
            passedTestCaseIdsCriteria = new ArrayList<Criterion>();
            testCaseIdsCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getActiveTestCaseIdsCriteria() {
            return activeTestCaseIdsCriteria;
        }

        protected void addActiveTestCaseIdsCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            activeTestCaseIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addActiveTestCaseIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            activeTestCaseIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getExecutedTestCaseIdsCriteria() {
            return executedTestCaseIdsCriteria;
        }

        protected void addExecutedTestCaseIdsCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            executedTestCaseIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addExecutedTestCaseIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            executedTestCaseIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getPassedTestCaseIdsCriteria() {
            return passedTestCaseIdsCriteria;
        }

        protected void addPassedTestCaseIdsCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            passedTestCaseIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addPassedTestCaseIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            passedTestCaseIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTestCaseIdsCriteria() {
            return testCaseIdsCriteria;
        }

        protected void addTestCaseIdsCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            testCaseIdsCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        protected void addTestCaseIdsCriterion(String condition, Set<Long> value1, Set<Long> value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            testCaseIdsCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LongSetTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0
                || activeTestCaseIdsCriteria.size() > 0
                || executedTestCaseIdsCriteria.size() > 0
                || passedTestCaseIdsCriteria.size() > 0
                || testCaseIdsCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(activeTestCaseIdsCriteria);
                allCriteria.addAll(executedTestCaseIdsCriteria);
                allCriteria.addAll(passedTestCaseIdsCriteria);
                allCriteria.addAll(testCaseIdsCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
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

        public Criteria andProjectIdEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
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

        public Criteria andProjectNameEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
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

        public Criteria andProjectCreatedAtEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtNotEqualTo(Date value) {
            addCriterion("project_created_at <>", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThan(Date value) {
            addCriterion("project_created_at >", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("project_created_at >=", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThan(Date value) {
            addCriterion("project_created_at <", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("project_created_at <=", value, "projectCreatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectCreatedAtLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
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

        public Criteria andProjectUpdatedAtIsNull() {
            addCriterion("project_updated_at is null");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtIsNotNull() {
            addCriterion("project_updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtEqualTo(Date value) {
            addCriterion("project_updated_at =", value, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtNotEqualTo(Date value) {
            addCriterion("project_updated_at <>", value, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtGreaterThan(Date value) {
            addCriterion("project_updated_at >", value, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("project_updated_at >=", value, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtLessThan(Date value) {
            addCriterion("project_updated_at <", value, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("project_updated_at <=", value, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_updated_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtIn(List<Date> values) {
            addCriterion("project_updated_at in", values, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtNotIn(List<Date> values) {
            addCriterion("project_updated_at not in", values, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("project_updated_at between", value1, value2, "projectUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andProjectUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("project_updated_at not between", value1, value2, "projectUpdatedAt");
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

        public Criteria andActiveTestCaseNumberEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("active_test_case_number <>", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThan(Integer value) {
            addCriterion("active_test_case_number >", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_test_case_number >=", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThan(Integer value) {
            addCriterion("active_test_case_number <", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("active_test_case_number <=", value, "activeTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseNumberLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
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

        public Criteria andExecutedTestCaseNumberEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("executed_test_case_number <>", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThan(Integer value) {
            addCriterion("executed_test_case_number >", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("executed_test_case_number >=", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThan(Integer value) {
            addCriterion("executed_test_case_number <", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("executed_test_case_number <=", value, "executedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseNumberLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
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

        public Criteria andActiveTestCaseIdsIsNull() {
            addCriterion("active_test_case_ids is null");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsIsNotNull() {
            addCriterion("active_test_case_ids is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsEqualTo(Set<Long> value) {
            addActiveTestCaseIdsCriterion("active_test_case_ids =", value, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsNotEqualTo(Set<Long> value) {
            addActiveTestCaseIdsCriterion("active_test_case_ids <>", value, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsGreaterThan(Set<Long> value) {
            addActiveTestCaseIdsCriterion("active_test_case_ids >", value, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsGreaterThanOrEqualTo(Set<Long> value) {
            addActiveTestCaseIdsCriterion("active_test_case_ids >=", value, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsLessThan(Set<Long> value) {
            addActiveTestCaseIdsCriterion("active_test_case_ids <", value, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsLessThanOrEqualTo(Set<Long> value) {
            addActiveTestCaseIdsCriterion("active_test_case_ids <=", value, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("active_test_case_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsBetween(Set<Long> value1, Set<Long> value2) {
            addActiveTestCaseIdsCriterion("active_test_case_ids between", value1, value2, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andActiveTestCaseIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addActiveTestCaseIdsCriterion("active_test_case_ids not between", value1, value2, "activeTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsIsNull() {
            addCriterion("executed_test_case_ids is null");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsIsNotNull() {
            addCriterion("executed_test_case_ids is not null");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsEqualTo(Set<Long> value) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids =", value, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsNotEqualTo(Set<Long> value) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids <>", value, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsGreaterThan(Set<Long> value) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids >", value, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsGreaterThanOrEqualTo(Set<Long> value) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids >=", value, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsLessThan(Set<Long> value) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids <", value, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsLessThanOrEqualTo(Set<Long> value) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids <=", value, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("executed_test_case_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsBetween(Set<Long> value1, Set<Long> value2) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids between", value1, value2, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andExecutedTestCaseIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addExecutedTestCaseIdsCriterion("executed_test_case_ids not between", value1, value2, "executedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsIsNull() {
            addCriterion("passed_test_case_ids is null");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsIsNotNull() {
            addCriterion("passed_test_case_ids is not null");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsEqualTo(Set<Long> value) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids =", value, "passedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsNotEqualTo(Set<Long> value) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids <>", value, "passedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsGreaterThan(Set<Long> value) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids >", value, "passedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsGreaterThanOrEqualTo(Set<Long> value) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids >=", value, "passedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsLessThan(Set<Long> value) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids <", value, "passedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsLessThanOrEqualTo(Set<Long> value) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids <=", value, "passedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsBetween(Set<Long> value1, Set<Long> value2) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids between", value1, value2, "passedTestCaseIds");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addPassedTestCaseIdsCriterion("passed_test_case_ids not between", value1, value2, "passedTestCaseIds");
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

        public Criteria andPassedTestCaseNumberEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("passed_test_case_number <>", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThan(Integer value) {
            addCriterion("passed_test_case_number >", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("passed_test_case_number >=", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThan(Integer value) {
            addCriterion("passed_test_case_number <", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("passed_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("passed_test_case_number <=", value, "passedTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andPassedTestCaseNumberLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
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

        public Criteria andTotalTestCaseNumberEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberNotEqualTo(Integer value) {
            addCriterion("total_test_case_number <>", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThan(Integer value) {
            addCriterion("total_test_case_number >", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_test_case_number >=", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThan(Integer value) {
            addCriterion("total_test_case_number <", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("total_test_case_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("total_test_case_number <=", value, "totalTestCaseNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTestCaseNumberLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
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

        public Criteria andTestCaseIdsIsNull() {
            addCriterion("test_case_ids is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsIsNotNull() {
            addCriterion("test_case_ids is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsEqualTo(Set<Long> value) {
            addTestCaseIdsCriterion("test_case_ids =", value, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_ids = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsNotEqualTo(Set<Long> value) {
            addTestCaseIdsCriterion("test_case_ids <>", value, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_ids <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsGreaterThan(Set<Long> value) {
            addTestCaseIdsCriterion("test_case_ids >", value, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_ids > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsGreaterThanOrEqualTo(Set<Long> value) {
            addTestCaseIdsCriterion("test_case_ids >=", value, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_ids >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsLessThan(Set<Long> value) {
            addTestCaseIdsCriterion("test_case_ids <", value, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_ids < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsLessThanOrEqualTo(Set<Long> value) {
            addTestCaseIdsCriterion("test_case_ids <=", value, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("test_case_ids <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsBetween(Set<Long> value1, Set<Long> value2) {
            addTestCaseIdsCriterion("test_case_ids between", value1, value2, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdsNotBetween(Set<Long> value1, Set<Long> value2) {
            addTestCaseIdsCriterion("test_case_ids not between", value1, value2, "testCaseIds");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedIsNull() {
            addCriterion("project_is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedIsNotNull() {
            addCriterion("project_is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedEqualTo(Boolean value) {
            addCriterion("project_is_deleted =", value, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_is_deleted = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedNotEqualTo(Boolean value) {
            addCriterion("project_is_deleted <>", value, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedNotEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_is_deleted <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedGreaterThan(Boolean value) {
            addCriterion("project_is_deleted >", value, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedGreaterThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_is_deleted > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("project_is_deleted >=", value, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedGreaterThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_is_deleted >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedLessThan(Boolean value) {
            addCriterion("project_is_deleted <", value, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedLessThanColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_is_deleted < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("project_is_deleted <=", value, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedLessThanOrEqualToColumn(ProjectExecutionInfo.Column column) {
            addCriterion(new StringBuilder("project_is_deleted <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedIn(List<Boolean> values) {
            addCriterion("project_is_deleted in", values, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedNotIn(List<Boolean> values) {
            addCriterion("project_is_deleted not in", values, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("project_is_deleted between", value1, value2, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("project_is_deleted not between", value1, value2, "projectIsDeleted");
            return (Criteria) this;
        }

        public Criteria andProjectNameLikeInsensitive(String value) {
            addCriterion("upper(project_name) like", value.toUpperCase(), "projectName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private ProjectExecutionInfoExample example;

        protected Criteria(ProjectExecutionInfoExample example) {
            super();
            this.example = example;
        }

        public ProjectExecutionInfoExample example() {
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
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            Criteria add(Criteria add);
        }
    }

    public static class Criterion {
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
            } else {
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
        void example(com.meowlomo.atm.core.model.ProjectExecutionInfoExample example);
    }
}