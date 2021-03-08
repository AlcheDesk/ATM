package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RunSetResultJobLinkExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunSetResultJobLinkExample() {
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

    public RunSetResultJobLinkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RunSetResultJobLinkExample orderBy(String... orderByClauses) {
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
        RunSetResultJobLinkExample example = new RunSetResultJobLinkExample();
        return example.createCriteria();
    }

    public RunSetResultJobLinkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public RunSetResultJobLinkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> runSetResultUuidCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            jobUuidCriteria = new ArrayList<Criterion>();
            runSetResultUuidCriteria = new ArrayList<Criterion>();
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

        public List<Criterion> getRunSetResultUuidCriteria() {
            return runSetResultUuidCriteria;
        }

        protected void addRunSetResultUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            runSetResultUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addRunSetResultUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            runSetResultUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || jobUuidCriteria.size() > 0 || runSetResultUuidCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(jobUuidCriteria);
                allCriteria.addAll(runSetResultUuidCriteria);
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

        public Criteria andIdEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(RunSetResultJobLink.Column column) {
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

        public Criteria andRunSetResultIdEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotEqualTo(Long value) {
            addCriterion("run_set_result_id <>", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdNotEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThan(Long value) {
            addCriterion("run_set_result_id >", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanOrEqualTo(Long value) {
            addCriterion("run_set_result_id >=", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdGreaterThanOrEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThan(Long value) {
            addCriterion("run_set_result_id <", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanOrEqualTo(Long value) {
            addCriterion("run_set_result_id <=", value, "runSetResultId");
            return (Criteria) this;
        }

        public Criteria andRunSetResultIdLessThanOrEqualToColumn(RunSetResultJobLink.Column column) {
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

        public Criteria andJobUuidEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidNotEqualTo(UUID value) {
            addJobUuidCriterion("job_uuid <>", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidNotEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThan(UUID value) {
            addJobUuidCriterion("job_uuid >", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThanOrEqualTo(UUID value) {
            addJobUuidCriterion("job_uuid >=", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidGreaterThanOrEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThan(UUID value) {
            addJobUuidCriterion("job_uuid <", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("job_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThanOrEqualTo(UUID value) {
            addJobUuidCriterion("job_uuid <=", value, "jobUuid");
            return (Criteria) this;
        }

        public Criteria andJobUuidLessThanOrEqualToColumn(RunSetResultJobLink.Column column) {
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

        public Criteria andCreatedAtEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(RunSetResultJobLink.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(RunSetResultJobLink.Column column) {
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

        public Criteria andRunSetResultUuidIsNull() {
            addCriterion("run_set_result_uuid is null");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidIsNotNull() {
            addCriterion("run_set_result_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidEqualTo(UUID value) {
            addRunSetResultUuidCriterion("run_set_result_uuid =", value, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidNotEqualTo(UUID value) {
            addRunSetResultUuidCriterion("run_set_result_uuid <>", value, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidNotEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidGreaterThan(UUID value) {
            addRunSetResultUuidCriterion("run_set_result_uuid >", value, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidGreaterThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidGreaterThanOrEqualTo(UUID value) {
            addRunSetResultUuidCriterion("run_set_result_uuid >=", value, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidGreaterThanOrEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidLessThan(UUID value) {
            addRunSetResultUuidCriterion("run_set_result_uuid <", value, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidLessThanColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidLessThanOrEqualTo(UUID value) {
            addRunSetResultUuidCriterion("run_set_result_uuid <=", value, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidLessThanOrEqualToColumn(RunSetResultJobLink.Column column) {
            addCriterion(new StringBuilder("run_set_result_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidIn(List<UUID> values) {
            addRunSetResultUuidCriterion("run_set_result_uuid in", values, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidNotIn(List<UUID> values) {
            addRunSetResultUuidCriterion("run_set_result_uuid not in", values, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidBetween(UUID value1, UUID value2) {
            addRunSetResultUuidCriterion("run_set_result_uuid between", value1, value2, "runSetResultUuid");
            return (Criteria) this;
        }

        public Criteria andRunSetResultUuidNotBetween(UUID value1, UUID value2) {
            addRunSetResultUuidCriterion("run_set_result_uuid not between", value1, value2, "runSetResultUuid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private RunSetResultJobLinkExample example;

        protected Criteria(RunSetResultJobLinkExample example) {
            super();
            this.example = example;
        }

        public RunSetResultJobLinkExample example() {
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
        void example(com.meowlomo.atm.core.model.RunSetResultJobLinkExample example);
    }
}