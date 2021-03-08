package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RunSetJobLinkExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunSetJobLinkExample() {
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

    public RunSetJobLinkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunSetJobLinkExample orderBy(String... orderByClauses) {
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
        RunSetJobLinkExample example = new RunSetJobLinkExample();
        return example.createCriteria();
    }

    public RunSetJobLinkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunSetJobLinkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> jobUuidCriteria;

        protected List<Criterion> runSetUuidCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            jobUuidCriteria = new ArrayList<Criterion>();
            runSetUuidCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getJobUuidCriteria() {
            return jobUuidCriteria;
        }

        protected void addJobUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            jobUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addJobUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            jobUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getRunSetUuidCriteria() {
            return runSetUuidCriteria;
        }

        protected void addRunSetUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            runSetUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addRunSetUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            runSetUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || jobUuidCriteria.size() > 0 || runSetUuidCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(jobUuidCriteria);
                allCriteria.addAll(runSetUuidCriteria);
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

        public Criteria andIdEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(RunSetJobLink.Column column) {
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

        public Criteria andRunSetIdEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualTo(Long value) {
            addCriterion("run_set_id <>", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdNotEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThan(Long value) {
            addCriterion("run_set_id >", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_id >=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdGreaterThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThan(Long value) {
            addCriterion("run_set_id <", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_id <=", value, "runSetId");
            return (Criteria) this;
        }

        public Criteria andRunSetIdLessThanOrEqualToColumn(RunSetJobLink.Column column) {
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

        public Criteria andJobUuidIsNull() {
            addCriterion("job_uuid is null");
            return (Criteria) this;
        }

        public Criteria andJobUuidIsNotNull() {
            addCriterion("job_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andJobUuidEqualTo(UUID value) {
            addJobUuidCriterion("job_uuid =", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidNotEqualTo(UUID value) {
            addJobUuidCriterion("job_uuid <>", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidNotEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThan(UUID value) {
            addJobUuidCriterion("job_uuid >", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThanOrEqualTo(UUID value) {
            addJobUuidCriterion("job_uuid >=", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThan(UUID value) {
            addJobUuidCriterion("job_uuid <", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThanOrEqualTo(UUID value) {
            addJobUuidCriterion("job_uuid <=", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidIn(List<UUID> values) {
            addJobUuidCriterion("job_uuid in", values, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidNotIn(List<UUID> values) {
            addJobUuidCriterion("job_uuid not in", values, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidBetween(UUID value1, UUID value2) {
            addJobUuidCriterion("job_uuid between", value1, value2, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidNotBetween(UUID value1, UUID value2) {
            addJobUuidCriterion("job_uuid not between", value1, value2, "jobUuid");
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

        public Criteria andCreatedAtEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(RunSetJobLink.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(RunSetJobLink.Column column) {
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

        public Criteria andRunSetUuidIsNull() {
            addCriterion("run_set_uuid is null");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidIsNotNull() {
            addCriterion("run_set_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidEqualTo(UUID value) {
            addRunSetUuidCriterion("run_set_uuid =", value, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetUuidNotEqualTo(UUID value) {
            addRunSetUuidCriterion("run_set_uuid <>", value, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidNotEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetUuidGreaterThan(UUID value) {
            addRunSetUuidCriterion("run_set_uuid >", value, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidGreaterThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetUuidGreaterThanOrEqualTo(UUID value) {
            addRunSetUuidCriterion("run_set_uuid >=", value, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidGreaterThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetUuidLessThan(UUID value) {
            addRunSetUuidCriterion("run_set_uuid <", value, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidLessThanColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetUuidLessThanOrEqualTo(UUID value) {
            addRunSetUuidCriterion("run_set_uuid <=", value, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidLessThanOrEqualToColumn(RunSetJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetUuidIn(List<UUID> values) {
            addRunSetUuidCriterion("run_set_uuid in", values, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidNotIn(List<UUID> values) {
            addRunSetUuidCriterion("run_set_uuid not in", values, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidBetween(UUID value1, UUID value2) {
            addRunSetUuidCriterion("run_set_uuid between", value1, value2, "runSetUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetUuidNotBetween(UUID value1, UUID value2) {
            addRunSetUuidCriterion("run_set_uuid not between", value1, value2, "runSetUuid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private RunSetJobLinkExample example;

        protected Criteria(RunSetJobLinkExample example) {
            super();
            this.example = example;
        }

        public RunSetJobLinkExample example() {
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
        void example(com.meowlomo.atm.core.model.RunSetJobLinkExample example);
    }
}