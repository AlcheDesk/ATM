package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DriverPropertyExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverPropertyExample() {
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

    public DriverPropertyExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public DriverPropertyExample orderBy(String... orderByClauses) {
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
        DriverPropertyExample example = new DriverPropertyExample();
        return example.createCriteria();
    }

    public DriverPropertyExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public DriverPropertyExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(DriverProperty.Column column) {
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

        public Criteria andNameEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(DriverProperty.Column column) {
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

        public Criteria andDriverVendorIdIsNull() {
            addCriterion("driver_vendor_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdIsNotNull() {
            addCriterion("driver_vendor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdEqualTo(Long value) {
            addCriterion("driver_vendor_id =", value, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("driver_vendor_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdNotEqualTo(Long value) {
            addCriterion("driver_vendor_id <>", value, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("driver_vendor_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdGreaterThan(Long value) {
            addCriterion("driver_vendor_id >", value, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("driver_vendor_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_vendor_id >=", value, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("driver_vendor_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdLessThan(Long value) {
            addCriterion("driver_vendor_id <", value, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("driver_vendor_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdLessThanOrEqualTo(Long value) {
            addCriterion("driver_vendor_id <=", value, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("driver_vendor_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdIn(List<Long> values) {
            addCriterion("driver_vendor_id in", values, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdNotIn(List<Long> values) {
            addCriterion("driver_vendor_id not in", values, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdBetween(Long value1, Long value2) {
            addCriterion("driver_vendor_id between", value1, value2, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDriverVendorIdNotBetween(Long value1, Long value2) {
            addCriterion("driver_vendor_id not between", value1, value2, "driverVendorId");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("default_value is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("default_value is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("default_value =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("default_value <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("default_value >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("default_value >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("default_value <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("default_value <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("default_value not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("default_value in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("default_value not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("default_value between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("default_value not between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultActionIsNull() {
            addCriterion("default_action is null");
            return (Criteria) this;
        }

        public Criteria andDefaultActionIsNotNull() {
            addCriterion("default_action is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultActionEqualTo(String value) {
            addCriterion("default_action =", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_action = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotEqualTo(String value) {
            addCriterion("default_action <>", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_action <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultActionGreaterThan(String value) {
            addCriterion("default_action >", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_action > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultActionGreaterThanOrEqualTo(String value) {
            addCriterion("default_action >=", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_action >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultActionLessThan(String value) {
            addCriterion("default_action <", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_action < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultActionLessThanOrEqualTo(String value) {
            addCriterion("default_action <=", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("default_action <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultActionLike(String value) {
            addCriterion("default_action like", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotLike(String value) {
            addCriterion("default_action not like", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionIn(List<String> values) {
            addCriterion("default_action in", values, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotIn(List<String> values) {
            addCriterion("default_action not in", values, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionBetween(String value1, String value2) {
            addCriterion("default_action between", value1, value2, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotBetween(String value1, String value2) {
            addCriterion("default_action not between", value1, value2, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("description = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("description <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("description > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("description >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("description < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("description <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNull() {
            addCriterion("value_type is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("value_type is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(String value) {
            addCriterion("value_type =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("value_type = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(String value) {
            addCriterion("value_type <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("value_type <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(String value) {
            addCriterion("value_type >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("value_type > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("value_type >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("value_type >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(String value) {
            addCriterion("value_type <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("value_type < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(String value) {
            addCriterion("value_type <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("value_type <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueTypeLike(String value) {
            addCriterion("value_type like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotLike(String value) {
            addCriterion("value_type not like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<String> values) {
            addCriterion("value_type in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<String> values) {
            addCriterion("value_type not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(String value1, String value2) {
            addCriterion("value_type between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(String value1, String value2) {
            addCriterion("value_type not between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredIsNull() {
            addCriterion("is_predefined_value_required is null");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredIsNotNull() {
            addCriterion("is_predefined_value_required is not null");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredEqualTo(Boolean value) {
            addCriterion("is_predefined_value_required =", value, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined_value_required = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredNotEqualTo(Boolean value) {
            addCriterion("is_predefined_value_required <>", value, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined_value_required <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredGreaterThan(Boolean value) {
            addCriterion("is_predefined_value_required >", value, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined_value_required > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined_value_required >=", value, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined_value_required >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredLessThan(Boolean value) {
            addCriterion("is_predefined_value_required <", value, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined_value_required < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredLessThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined_value_required <=", value, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined_value_required <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredIn(List<Boolean> values) {
            addCriterion("is_predefined_value_required in", values, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredNotIn(List<Boolean> values) {
            addCriterion("is_predefined_value_required not in", values, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredBetween(Boolean value1, Boolean value2) {
            addCriterion("is_predefined_value_required between", value1, value2, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andPredefinedValueRequiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_predefined_value_required not between", value1, value2, "predefinedValueRequired");
            return (Criteria) this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("is_active =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_active = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_active <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_active > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_active >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_active < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_active <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("is_active in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("is_active not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andPredefinedIsNull() {
            addCriterion("is_predefined is null");
            return (Criteria) this;
        }

        public Criteria andPredefinedIsNotNull() {
            addCriterion("is_predefined is not null");
            return (Criteria) this;
        }

        public Criteria andPredefinedEqualTo(Boolean value) {
            addCriterion("is_predefined =", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedNotEqualTo(Boolean value) {
            addCriterion("is_predefined <>", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedNotEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThan(Boolean value) {
            addCriterion("is_predefined >", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined >=", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThan(Boolean value) {
            addCriterion("is_predefined <", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined <=", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanOrEqualToColumn(DriverProperty.Column column) {
            addCriterion(new StringBuilder("is_predefined <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedIn(List<Boolean> values) {
            addCriterion("is_predefined in", values, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedNotIn(List<Boolean> values) {
            addCriterion("is_predefined not in", values, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_predefined between", value1, value2, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_predefined not between", value1, value2, "predefined");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLikeInsensitive(String value) {
            addCriterion("upper(default_value) like", value.toUpperCase(), "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultActionLikeInsensitive(String value) {
            addCriterion("upper(default_action) like", value.toUpperCase(), "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andValueTypeLikeInsensitive(String value) {
            addCriterion("upper(value_type) like", value.toUpperCase(), "valueType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private DriverPropertyExample example;

        protected Criteria(DriverPropertyExample example) {
            super();
            this.example = example;
        }

        public DriverPropertyExample example() {
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
        void example(com.meowlomo.atm.core.model.DriverPropertyExample example);
    }
}