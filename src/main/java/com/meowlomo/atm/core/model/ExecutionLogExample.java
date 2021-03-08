package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExecutionLogExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExecutionLogExample() {
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

    public ExecutionLogExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public ExecutionLogExample orderBy(String... orderByClauses) {
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
        ExecutionLogExample example = new ExecutionLogExample();
        return example.createCriteria();
    }

    public ExecutionLogExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public ExecutionLogExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> logLevelCriteria;

        protected List<Criterion> runTypeCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            logLevelCriteria = new ArrayList<Criterion>();
            runTypeCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getLogLevelCriteria() {
            return logLevelCriteria;
        }

        protected void addLogLevelCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            logLevelCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.LogLevelTypeHandler"));
            allCriteria = null;
        }

        protected void addLogLevelCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            logLevelCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.LogLevelTypeHandler"));
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

        public boolean isValid() {
            return criteria.size() > 0 || logLevelCriteria.size() > 0 || runTypeCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(logLevelCriteria);
                allCriteria.addAll(runTypeCriteria);
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

        public Criteria andIdEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(ExecutionLog.Column column) {
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

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("message = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("message <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("message > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("message >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("message < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("message <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
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

        public Criteria andUpdatedAtEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(ExecutionLog.Column column) {
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

        public Criteria andCreatedAtEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(ExecutionLog.Column column) {
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

        public Criteria andLogLevelIsNull() {
            addCriterion("log_level_id is null");
            return (Criteria) this;
        }

        public Criteria andLogLevelIsNotNull() {
            addCriterion("log_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogLevelEqualTo(String value) {
            addLogLevelCriterion("log_level_id =", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("log_level_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLevelNotEqualTo(String value) {
            addLogLevelCriterion("log_level_id <>", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("log_level_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThan(String value) {
            addLogLevelCriterion("log_level_id >", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("log_level_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThanOrEqualTo(String value) {
            addLogLevelCriterion("log_level_id >=", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("log_level_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThan(String value) {
            addLogLevelCriterion("log_level_id <", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("log_level_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThanOrEqualTo(String value) {
            addLogLevelCriterion("log_level_id <=", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("log_level_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLevelIn(List<String> values) {
            addLogLevelCriterion("log_level_id in", values, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotIn(List<String> values) {
            addLogLevelCriterion("log_level_id not in", values, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelBetween(String value1, String value2) {
            addLogLevelCriterion("log_level_id between", value1, value2, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotBetween(String value1, String value2) {
            addLogLevelCriterion("log_level_id not between", value1, value2, "logLevel");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdIsNull() {
            addCriterion("instruction_result_id is null");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdIsNotNull() {
            addCriterion("instruction_result_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdEqualTo(Long value) {
            addCriterion("instruction_result_id =", value, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("instruction_result_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdNotEqualTo(Long value) {
            addCriterion("instruction_result_id <>", value, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("instruction_result_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdGreaterThan(Long value) {
            addCriterion("instruction_result_id >", value, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("instruction_result_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdGreaterThanOrEqualTo(Long value) {
            addCriterion("instruction_result_id >=", value, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("instruction_result_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdLessThan(Long value) {
            addCriterion("instruction_result_id <", value, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("instruction_result_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdLessThanOrEqualTo(Long value) {
            addCriterion("instruction_result_id <=", value, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdLessThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("instruction_result_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdIn(List<Long> values) {
            addCriterion("instruction_result_id in", values, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdNotIn(List<Long> values) {
            addCriterion("instruction_result_id not in", values, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdBetween(Long value1, Long value2) {
            addCriterion("instruction_result_id between", value1, value2, "instructionResultId");
            return (Criteria) this;
        }

        public Criteria andInstructionResultIdNotBetween(Long value1, Long value2) {
            addCriterion("instruction_result_id not between", value1, value2, "instructionResultId");
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

        public Criteria andRunIdEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualTo(Long value) {
            addCriterion("run_id <>", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThan(Long value) {
            addCriterion("run_id >", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_id >=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThan(Long value) {
            addCriterion("run_id <", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualTo(Long value) {
            addCriterion("run_id <=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualToColumn(ExecutionLog.Column column) {
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

        public Criteria andRunTypeEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualTo(String value) {
            addRunTypeCriterion("run_type_id <>", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeNotEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThan(String value) {
            addRunTypeCriterion("run_type_id >", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id >=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeGreaterThanOrEqualToColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThan(String value) {
            addRunTypeCriterion("run_type_id <", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanColumn(ExecutionLog.Column column) {
            addCriterion(new StringBuilder("run_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualTo(String value) {
            addRunTypeCriterion("run_type_id <=", value, "runType");
            return (Criteria) this;
        }

        public Criteria andRunTypeLessThanOrEqualToColumn(ExecutionLog.Column column) {
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

        public Criteria andMessageLikeInsensitive(String value) {
            addCriterion("upper(message) like", value.toUpperCase(), "message");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private ExecutionLogExample example;

        protected Criteria(ExecutionLogExample example) {
            super();
            this.example = example;
        }

        public ExecutionLogExample example() {
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
        void example(com.meowlomo.atm.core.model.ExecutionLogExample example);
    }
}