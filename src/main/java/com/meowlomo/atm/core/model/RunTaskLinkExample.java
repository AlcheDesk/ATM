package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RunTaskLinkExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunTaskLinkExample() {
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

    public RunTaskLinkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunTaskLinkExample orderBy(String... orderByClauses) {
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
        RunTaskLinkExample example = new RunTaskLinkExample();
        return example.createCriteria();
    }

    public RunTaskLinkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunTaskLinkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> taskUuidCriteria;

        protected List<Criterion> runUuidCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            taskUuidCriteria = new ArrayList<Criterion>();
            runUuidCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getTaskUuidCriteria() {
            return taskUuidCriteria;
        }

        protected void addTaskUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            taskUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addTaskUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            taskUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getRunUuidCriteria() {
            return runUuidCriteria;
        }

        protected void addRunUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            runUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addRunUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            runUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || taskUuidCriteria.size() > 0 || runUuidCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(taskUuidCriteria);
                allCriteria.addAll(runUuidCriteria);
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

        public Criteria andIdEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(RunTaskLink.Column column) {
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

        public Criteria andRunIdEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualTo(Long value) {
            addCriterion("run_id <>", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdNotEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThan(Long value) {
            addCriterion("run_id >", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_id >=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdGreaterThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThan(Long value) {
            addCriterion("run_id <", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualTo(Long value) {
            addCriterion("run_id <=", value, "runId");
            return (Criteria) this;
        }

        public Criteria andRunIdLessThanOrEqualToColumn(RunTaskLink.Column column) {
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

        public Criteria andTaskUuidIsNull() {
            addCriterion("task_uuid is null");
            return (Criteria) this;
        }

        public Criteria andTaskUuidIsNotNull() {
            addCriterion("task_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andTaskUuidEqualTo(UUID value) {
            addTaskUuidCriterion("task_uuid =", value, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("task_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskUuidNotEqualTo(UUID value) {
            addTaskUuidCriterion("task_uuid <>", value, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidNotEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("task_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskUuidGreaterThan(UUID value) {
            addTaskUuidCriterion("task_uuid >", value, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidGreaterThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("task_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskUuidGreaterThanOrEqualTo(UUID value) {
            addTaskUuidCriterion("task_uuid >=", value, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidGreaterThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("task_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskUuidLessThan(UUID value) {
            addTaskUuidCriterion("task_uuid <", value, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidLessThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("task_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskUuidLessThanOrEqualTo(UUID value) {
            addTaskUuidCriterion("task_uuid <=", value, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidLessThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("task_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskUuidIn(List<UUID> values) {
            addTaskUuidCriterion("task_uuid in", values, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidNotIn(List<UUID> values) {
            addTaskUuidCriterion("task_uuid not in", values, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidBetween(UUID value1, UUID value2) {
            addTaskUuidCriterion("task_uuid between", value1, value2, "taskUuid");
            return (Criteria) this;
        }

        public Criteria andTaskUuidNotBetween(UUID value1, UUID value2) {
            addTaskUuidCriterion("task_uuid not between", value1, value2, "taskUuid");
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

        public Criteria andCreatedAtEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(RunTaskLink.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(RunTaskLink.Column column) {
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

        public Criteria andRunUuidIsNull() {
            addCriterion("run_uuid is null");
            return (Criteria) this;
        }

        public Criteria andRunUuidIsNotNull() {
            addCriterion("run_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andRunUuidEqualTo(UUID value) {
            addRunUuidCriterion("run_uuid =", value, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUuidNotEqualTo(UUID value) {
            addRunUuidCriterion("run_uuid <>", value, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidNotEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUuidGreaterThan(UUID value) {
            addRunUuidCriterion("run_uuid >", value, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidGreaterThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUuidGreaterThanOrEqualTo(UUID value) {
            addRunUuidCriterion("run_uuid >=", value, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidGreaterThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUuidLessThan(UUID value) {
            addRunUuidCriterion("run_uuid <", value, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidLessThanColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUuidLessThanOrEqualTo(UUID value) {
            addRunUuidCriterion("run_uuid <=", value, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidLessThanOrEqualToColumn(RunTaskLink.Column column) {
            addCriterion(new StringBuilder("run_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunUuidIn(List<UUID> values) {
            addRunUuidCriterion("run_uuid in", values, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidNotIn(List<UUID> values) {
            addRunUuidCriterion("run_uuid not in", values, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidBetween(UUID value1, UUID value2) {
            addRunUuidCriterion("run_uuid between", value1, value2, "runUuid");
            return (Criteria) this;
        }

        public Criteria andRunUuidNotBetween(UUID value1, UUID value2) {
            addRunUuidCriterion("run_uuid not between", value1, value2, "runUuid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private RunTaskLinkExample example;

        protected Criteria(RunTaskLinkExample example) {
            super();
            this.example = example;
        }

        public RunTaskLinkExample example() {
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
        void example(com.meowlomo.atm.core.model.RunTaskLinkExample example);
    }
}