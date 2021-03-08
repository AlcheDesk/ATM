package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserActivityLogExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserActivityLogExample() {
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

    public UserActivityLogExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public UserActivityLogExample orderBy(String... orderByClauses) {
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
        UserActivityLogExample example = new UserActivityLogExample();
        return example.createCriteria();
    }

    public UserActivityLogExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public UserActivityLogExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> userUuidCriteria;

        protected List<Criterion> activityUuidCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            userUuidCriteria = new ArrayList<Criterion>();
            activityUuidCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getUserUuidCriteria() {
            return userUuidCriteria;
        }

        protected void addUserUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            userUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addUserUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            userUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getActivityUuidCriteria() {
            return activityUuidCriteria;
        }

        protected void addActivityUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            activityUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addActivityUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            activityUuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || userUuidCriteria.size() > 0 || activityUuidCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(userUuidCriteria);
                allCriteria.addAll(activityUuidCriteria);
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

        public Criteria andIdEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(UserActivityLog.Column column) {
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

        public Criteria andUserUuidIsNull() {
            addCriterion("user_uuid is null");
            return (Criteria) this;
        }

        public Criteria andUserUuidIsNotNull() {
            addCriterion("user_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUserUuidEqualTo(UUID value) {
            addUserUuidCriterion("user_uuid =", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserUuidNotEqualTo(UUID value) {
            addUserUuidCriterion("user_uuid <>", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThan(UUID value) {
            addUserUuidCriterion("user_uuid >", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThanOrEqualTo(UUID value) {
            addUserUuidCriterion("user_uuid >=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThan(UUID value) {
            addUserUuidCriterion("user_uuid <", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThanOrEqualTo(UUID value) {
            addUserUuidCriterion("user_uuid <=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserUuidIn(List<UUID> values) {
            addUserUuidCriterion("user_uuid in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotIn(List<UUID> values) {
            addUserUuidCriterion("user_uuid not in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidBetween(UUID value1, UUID value2) {
            addUserUuidCriterion("user_uuid between", value1, value2, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotBetween(UUID value1, UUID value2) {
            addUserUuidCriterion("user_uuid not between", value1, value2, "userUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidIsNull() {
            addCriterion("activity_uuid is null");
            return (Criteria) this;
        }

        public Criteria andActivityUuidIsNotNull() {
            addCriterion("activity_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andActivityUuidEqualTo(UUID value) {
            addActivityUuidCriterion("activity_uuid =", value, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("activity_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActivityUuidNotEqualTo(UUID value) {
            addActivityUuidCriterion("activity_uuid <>", value, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("activity_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActivityUuidGreaterThan(UUID value) {
            addActivityUuidCriterion("activity_uuid >", value, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("activity_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActivityUuidGreaterThanOrEqualTo(UUID value) {
            addActivityUuidCriterion("activity_uuid >=", value, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("activity_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActivityUuidLessThan(UUID value) {
            addActivityUuidCriterion("activity_uuid <", value, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("activity_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActivityUuidLessThanOrEqualTo(UUID value) {
            addActivityUuidCriterion("activity_uuid <=", value, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidLessThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("activity_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActivityUuidIn(List<UUID> values) {
            addActivityUuidCriterion("activity_uuid in", values, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidNotIn(List<UUID> values) {
            addActivityUuidCriterion("activity_uuid not in", values, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidBetween(UUID value1, UUID value2) {
            addActivityUuidCriterion("activity_uuid between", value1, value2, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andActivityUuidNotBetween(UUID value1, UUID value2) {
            addActivityUuidCriterion("activity_uuid not between", value1, value2, "activityUuid");
            return (Criteria) this;
        }

        public Criteria andTargetModelIsNull() {
            addCriterion("target_model is null");
            return (Criteria) this;
        }

        public Criteria andTargetModelIsNotNull() {
            addCriterion("target_model is not null");
            return (Criteria) this;
        }

        public Criteria andTargetModelEqualTo(String value) {
            addCriterion("target_model =", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("target_model = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetModelNotEqualTo(String value) {
            addCriterion("target_model <>", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("target_model <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetModelGreaterThan(String value) {
            addCriterion("target_model >", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("target_model > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetModelGreaterThanOrEqualTo(String value) {
            addCriterion("target_model >=", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("target_model >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetModelLessThan(String value) {
            addCriterion("target_model <", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("target_model < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetModelLessThanOrEqualTo(String value) {
            addCriterion("target_model <=", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelLessThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("target_model <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTargetModelLike(String value) {
            addCriterion("target_model like", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelNotLike(String value) {
            addCriterion("target_model not like", value, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelIn(List<String> values) {
            addCriterion("target_model in", values, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelNotIn(List<String> values) {
            addCriterion("target_model not in", values, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelBetween(String value1, String value2) {
            addCriterion("target_model between", value1, value2, "targetModel");
            return (Criteria) this;
        }

        public Criteria andTargetModelNotBetween(String value1, String value2) {
            addCriterion("target_model not between", value1, value2, "targetModel");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNull() {
            addCriterion("action_name is null");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNotNull() {
            addCriterion("action_name is not null");
            return (Criteria) this;
        }

        public Criteria andActionNameEqualTo(String value) {
            addCriterion("action_name =", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("action_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNameNotEqualTo(String value) {
            addCriterion("action_name <>", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("action_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThan(String value) {
            addCriterion("action_name >", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("action_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanOrEqualTo(String value) {
            addCriterion("action_name >=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("action_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNameLessThan(String value) {
            addCriterion("action_name <", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("action_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanOrEqualTo(String value) {
            addCriterion("action_name <=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("action_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActionNameLike(String value) {
            addCriterion("action_name like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotLike(String value) {
            addCriterion("action_name not like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameIn(List<String> values) {
            addCriterion("action_name in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotIn(List<String> values) {
            addCriterion("action_name not in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameBetween(String value1, String value2) {
            addCriterion("action_name between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotBetween(String value1, String value2) {
            addCriterion("action_name not between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andInputIsNull() {
            addCriterion("input is null");
            return (Criteria) this;
        }

        public Criteria andInputIsNotNull() {
            addCriterion("input is not null");
            return (Criteria) this;
        }

        public Criteria andInputEqualTo(String value) {
            addCriterion("input =", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("input = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputNotEqualTo(String value) {
            addCriterion("input <>", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("input <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputGreaterThan(String value) {
            addCriterion("input >", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("input > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputGreaterThanOrEqualTo(String value) {
            addCriterion("input >=", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("input >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputLessThan(String value) {
            addCriterion("input <", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("input < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputLessThanOrEqualTo(String value) {
            addCriterion("input <=", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputLessThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("input <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInputLike(String value) {
            addCriterion("input like", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotLike(String value) {
            addCriterion("input not like", value, "input");
            return (Criteria) this;
        }

        public Criteria andInputIn(List<String> values) {
            addCriterion("input in", values, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotIn(List<String> values) {
            addCriterion("input not in", values, "input");
            return (Criteria) this;
        }

        public Criteria andInputBetween(String value1, String value2) {
            addCriterion("input between", value1, value2, "input");
            return (Criteria) this;
        }

        public Criteria andInputNotBetween(String value1, String value2) {
            addCriterion("input not between", value1, value2, "input");
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

        public Criteria andCreatedAtEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(UserActivityLog.Column column) {
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualToColumn(UserActivityLog.Column column) {
            addCriterion(new StringBuilder("user_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andTargetModelLikeInsensitive(String value) {
            addCriterion("upper(target_model) like", value.toUpperCase(), "targetModel");
            return (Criteria) this;
        }

        public Criteria andActionNameLikeInsensitive(String value) {
            addCriterion("upper(action_name) like", value.toUpperCase(), "actionName");
            return (Criteria) this;
        }

        public Criteria andInputLikeInsensitive(String value) {
            addCriterion("upper(input) like", value.toUpperCase(), "input");
            return (Criteria) this;
        }

        public Criteria andUserNameLikeInsensitive(String value) {
            addCriterion("upper(user_name) like", value.toUpperCase(), "userName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private UserActivityLogExample example;

        protected Criteria(UserActivityLogExample example) {
            super();
            this.example = example;
        }

        public UserActivityLogExample example() {
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
        void example(com.meowlomo.atm.core.model.UserActivityLogExample example);
    }
}