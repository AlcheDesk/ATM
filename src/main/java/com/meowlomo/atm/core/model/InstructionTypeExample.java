package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InstructionTypeExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructionTypeExample() {
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

    public InstructionTypeExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public InstructionTypeExample orderBy(String... orderByClauses) {
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
        InstructionTypeExample example = new InstructionTypeExample();
        return example.createCriteria();
    }

    public InstructionTypeExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public InstructionTypeExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> driverTypeCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            driverTypeCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getDriverTypeCriteria() {
            return driverTypeCriteria;
        }

        protected void addDriverTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            driverTypeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.DriverTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addDriverTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            driverTypeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.DriverTypeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || driverTypeCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(driverTypeCriteria);
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

        public Criteria andIdEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(InstructionType.Column column) {
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

        public Criteria andNameEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(InstructionType.Column column) {
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

        public Criteria andActiveEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_active = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_active <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_active > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_active >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_active < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualToColumn(InstructionType.Column column) {
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

        public Criteria andPredefinedEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_predefined = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedNotEqualTo(Boolean value) {
            addCriterion("is_predefined <>", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_predefined <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThan(Boolean value) {
            addCriterion("is_predefined >", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_predefined > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined >=", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_predefined >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThan(Boolean value) {
            addCriterion("is_predefined <", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_predefined < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_predefined <=", value, "predefined");
            return (Criteria) this;
        }

        public Criteria andPredefinedLessThanOrEqualToColumn(InstructionType.Column column) {
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

        public Criteria andIsDriverIsNull() {
            addCriterion("is_driver is null");
            return (Criteria) this;
        }

        public Criteria andIsDriverIsNotNull() {
            addCriterion("is_driver is not null");
            return (Criteria) this;
        }

        public Criteria andIsDriverEqualTo(Boolean value) {
            addCriterion("is_driver =", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_driver = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverNotEqualTo(Boolean value) {
            addCriterion("is_driver <>", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_driver <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThan(Boolean value) {
            addCriterion("is_driver >", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_driver > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_driver >=", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_driver >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThan(Boolean value) {
            addCriterion("is_driver <", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_driver < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanOrEqualTo(Boolean value) {
            addCriterion("is_driver <=", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_driver <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverIn(List<Boolean> values) {
            addCriterion("is_driver in", values, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverNotIn(List<Boolean> values) {
            addCriterion("is_driver not in", values, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverBetween(Boolean value1, Boolean value2) {
            addCriterion("is_driver between", value1, value2, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_driver not between", value1, value2, "isDriver");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIsNull() {
            addCriterion("driver_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIsNotNull() {
            addCriterion("driver_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id =", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("driver_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id <>", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("driver_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThan(String value) {
            addDriverTypeCriterion("driver_type_id >", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("driver_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanOrEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id >=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("driver_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThan(String value) {
            addDriverTypeCriterion("driver_type_id <", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("driver_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanOrEqualTo(String value) {
            addDriverTypeCriterion("driver_type_id <=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("driver_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDriverTypeIn(List<String> values) {
            addDriverTypeCriterion("driver_type_id in", values, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotIn(List<String> values) {
            addDriverTypeCriterion("driver_type_id not in", values, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeBetween(String value1, String value2) {
            addDriverTypeCriterion("driver_type_id between", value1, value2, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotBetween(String value1, String value2) {
            addDriverTypeCriterion("driver_type_id not between", value1, value2, "driverType");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredIsNull() {
            addCriterion("is_element_required is null");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredIsNotNull() {
            addCriterion("is_element_required is not null");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredEqualTo(Boolean value) {
            addCriterion("is_element_required =", value, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_element_required = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredNotEqualTo(Boolean value) {
            addCriterion("is_element_required <>", value, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_element_required <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredGreaterThan(Boolean value) {
            addCriterion("is_element_required >", value, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_element_required > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_element_required >=", value, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_element_required >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredLessThan(Boolean value) {
            addCriterion("is_element_required <", value, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_element_required < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredLessThanOrEqualTo(Boolean value) {
            addCriterion("is_element_required <=", value, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredLessThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("is_element_required <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredIn(List<Boolean> values) {
            addCriterion("is_element_required in", values, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredNotIn(List<Boolean> values) {
            addCriterion("is_element_required not in", values, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredBetween(Boolean value1, Boolean value2) {
            addCriterion("is_element_required between", value1, value2, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andIsElementRequiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_element_required not between", value1, value2, "isElementRequired");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdIsNull() {
            addCriterion("virtual_element_id is null");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdIsNotNull() {
            addCriterion("virtual_element_id is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdEqualTo(Long value) {
            addCriterion("virtual_element_id =", value, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("virtual_element_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdNotEqualTo(Long value) {
            addCriterion("virtual_element_id <>", value, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("virtual_element_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdGreaterThan(Long value) {
            addCriterion("virtual_element_id >", value, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("virtual_element_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdGreaterThanOrEqualTo(Long value) {
            addCriterion("virtual_element_id >=", value, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("virtual_element_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdLessThan(Long value) {
            addCriterion("virtual_element_id <", value, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("virtual_element_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdLessThanOrEqualTo(Long value) {
            addCriterion("virtual_element_id <=", value, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdLessThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("virtual_element_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdIn(List<Long> values) {
            addCriterion("virtual_element_id in", values, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdNotIn(List<Long> values) {
            addCriterion("virtual_element_id not in", values, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdBetween(Long value1, Long value2) {
            addCriterion("virtual_element_id between", value1, value2, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andVirtualElementIdNotBetween(Long value1, Long value2) {
            addCriterion("virtual_element_id not between", value1, value2, "virtualElementId");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsIsNull() {
            addCriterion("overridable_fields is null");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsIsNotNull() {
            addCriterion("overridable_fields is not null");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsEqualTo(String value) {
            addCriterion("overridable_fields =", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("overridable_fields = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsNotEqualTo(String value) {
            addCriterion("overridable_fields <>", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsNotEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("overridable_fields <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsGreaterThan(String value) {
            addCriterion("overridable_fields >", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsGreaterThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("overridable_fields > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsGreaterThanOrEqualTo(String value) {
            addCriterion("overridable_fields >=", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsGreaterThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("overridable_fields >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsLessThan(String value) {
            addCriterion("overridable_fields <", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsLessThanColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("overridable_fields < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsLessThanOrEqualTo(String value) {
            addCriterion("overridable_fields <=", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsLessThanOrEqualToColumn(InstructionType.Column column) {
            addCriterion(new StringBuilder("overridable_fields <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsLike(String value) {
            addCriterion("overridable_fields like", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsNotLike(String value) {
            addCriterion("overridable_fields not like", value, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsIn(List<String> values) {
            addCriterion("overridable_fields in", values, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsNotIn(List<String> values) {
            addCriterion("overridable_fields not in", values, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsBetween(String value1, String value2) {
            addCriterion("overridable_fields between", value1, value2, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsNotBetween(String value1, String value2) {
            addCriterion("overridable_fields not between", value1, value2, "overridableFields");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andOverridableFieldsLikeInsensitive(String value) {
            addCriterion("upper(overridable_fields) like", value.toUpperCase(), "overridableFields");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private InstructionTypeExample example;

        protected Criteria(InstructionTypeExample example) {
            super();
            this.example = example;
        }

        public InstructionTypeExample example() {
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
        void example(com.meowlomo.atm.core.model.InstructionTypeExample example);
    }
}