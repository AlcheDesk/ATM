package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class ElementExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ElementExample() {
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

    public ElementExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public ElementExample orderBy(String... orderByClauses) {
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
        ElementExample example = new ElementExample();
        return example.createCriteria();
    }

    public ElementExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public ElementExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> typeCriteria;

        protected List<Criterion> locatorTypeCriteria;

        protected List<Criterion> colorCriteria;

        protected List<Criterion> parameterCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            locatorTypeCriteria = new ArrayList<Criterion>();
            colorCriteria = new ArrayList<Criterion>();
            parameterCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.ElementTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.ElementTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getLocatorTypeCriteria() {
            return locatorTypeCriteria;
        }

        protected void addLocatorTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            locatorTypeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.ElementLocatorTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addLocatorTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            locatorTypeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.ElementLocatorTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getColorCriteria() {
            return colorCriteria;
        }

        protected void addColorCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            colorCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.ColorTypeHandler"));
            allCriteria = null;
        }

        protected void addColorCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            colorCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.ColorTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getParameterCriteria() {
            return parameterCriteria;
        }

        protected void addParameterCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            parameterCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addParameterCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            parameterCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || typeCriteria.size() > 0 || locatorTypeCriteria.size() > 0 || colorCriteria.size() > 0 || parameterCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(locatorTypeCriteria);
                allCriteria.addAll(colorCriteria);
                allCriteria.addAll(parameterCriteria);
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

        public Criteria andIdEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(Element.Column column) {
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

        public Criteria andNameEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(Element.Column column) {
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

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("comment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("comment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("comment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("comment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("comment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("comment <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andLocatorValueIsNull() {
            addCriterion("locator_value is null");
            return (Criteria) this;
        }

        public Criteria andLocatorValueIsNotNull() {
            addCriterion("locator_value is not null");
            return (Criteria) this;
        }

        public Criteria andLocatorValueEqualTo(String value) {
            addCriterion("locator_value =", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("locator_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorValueNotEqualTo(String value) {
            addCriterion("locator_value <>", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("locator_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorValueGreaterThan(String value) {
            addCriterion("locator_value >", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("locator_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorValueGreaterThanOrEqualTo(String value) {
            addCriterion("locator_value >=", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("locator_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorValueLessThan(String value) {
            addCriterion("locator_value <", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("locator_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorValueLessThanOrEqualTo(String value) {
            addCriterion("locator_value <=", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("locator_value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorValueLike(String value) {
            addCriterion("locator_value like", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueNotLike(String value) {
            addCriterion("locator_value not like", value, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueIn(List<String> values) {
            addCriterion("locator_value in", values, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueNotIn(List<String> values) {
            addCriterion("locator_value not in", values, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueBetween(String value1, String value2) {
            addCriterion("locator_value between", value1, value2, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andLocatorValueNotBetween(String value1, String value2) {
            addCriterion("locator_value not between", value1, value2, "locatorValue");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXIsNull() {
            addCriterion("html_position_x is null");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXIsNotNull() {
            addCriterion("html_position_x is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXEqualTo(String value) {
            addCriterion("html_position_x =", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_x = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXNotEqualTo(String value) {
            addCriterion("html_position_x <>", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_x <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXGreaterThan(String value) {
            addCriterion("html_position_x >", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_x > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXGreaterThanOrEqualTo(String value) {
            addCriterion("html_position_x >=", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_x >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXLessThan(String value) {
            addCriterion("html_position_x <", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_x < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXLessThanOrEqualTo(String value) {
            addCriterion("html_position_x <=", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_x <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXLike(String value) {
            addCriterion("html_position_x like", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXNotLike(String value) {
            addCriterion("html_position_x not like", value, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXIn(List<String> values) {
            addCriterion("html_position_x in", values, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXNotIn(List<String> values) {
            addCriterion("html_position_x not in", values, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXBetween(String value1, String value2) {
            addCriterion("html_position_x between", value1, value2, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXNotBetween(String value1, String value2) {
            addCriterion("html_position_x not between", value1, value2, "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYIsNull() {
            addCriterion("html_position_y is null");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYIsNotNull() {
            addCriterion("html_position_y is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYEqualTo(String value) {
            addCriterion("html_position_y =", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_y = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYNotEqualTo(String value) {
            addCriterion("html_position_y <>", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_y <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYGreaterThan(String value) {
            addCriterion("html_position_y >", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_y > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYGreaterThanOrEqualTo(String value) {
            addCriterion("html_position_y >=", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_y >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYLessThan(String value) {
            addCriterion("html_position_y <", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_y < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYLessThanOrEqualTo(String value) {
            addCriterion("html_position_y <=", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("html_position_y <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYLike(String value) {
            addCriterion("html_position_y like", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYNotLike(String value) {
            addCriterion("html_position_y not like", value, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYIn(List<String> values) {
            addCriterion("html_position_y in", values, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYNotIn(List<String> values) {
            addCriterion("html_position_y not in", values, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYBetween(String value1, String value2) {
            addCriterion("html_position_y between", value1, value2, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYNotBetween(String value1, String value2) {
            addCriterion("html_position_y not between", value1, value2, "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_deleted = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_deleted <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_deleted > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_deleted >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_deleted < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_deleted <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "deleted");
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

        public Criteria andCreatedAtEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(Element.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(Element.Column column) {
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

        public Criteria andLogIsNull() {
            addCriterion("log is null");
            return (Criteria) this;
        }

        public Criteria andLogIsNotNull() {
            addCriterion("log is not null");
            return (Criteria) this;
        }

        public Criteria andLogEqualTo(String value) {
            addCriterion("log =", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("log = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogNotEqualTo(String value) {
            addCriterion("log <>", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("log <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThan(String value) {
            addCriterion("log >", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("log > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualTo(String value) {
            addCriterion("log >=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("log >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThan(String value) {
            addCriterion("log <", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("log < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualTo(String value) {
            addCriterion("log <=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("log <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLike(String value) {
            addCriterion("log like", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotLike(String value) {
            addCriterion("log not like", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogIn(List<String> values) {
            addCriterion("log in", values, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotIn(List<String> values) {
            addCriterion("log not in", values, "log");
            return (Criteria) this;
        }

        public Criteria andLogBetween(String value1, String value2) {
            addCriterion("log between", value1, value2, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotBetween(String value1, String value2) {
            addCriterion("log not between", value1, value2, "log");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("element_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("element_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("element_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("element_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("element_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("element_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("element_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("element_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("element_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("element_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("element_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("element_type_id not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeIsNull() {
            addCriterion("element_locator_type_id is null");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeIsNotNull() {
            addCriterion("element_locator_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeEqualTo(String value) {
            addLocatorTypeCriterion("element_locator_type_id =", value, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_locator_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorTypeNotEqualTo(String value) {
            addLocatorTypeCriterion("element_locator_type_id <>", value, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_locator_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorTypeGreaterThan(String value) {
            addLocatorTypeCriterion("element_locator_type_id >", value, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_locator_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorTypeGreaterThanOrEqualTo(String value) {
            addLocatorTypeCriterion("element_locator_type_id >=", value, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_locator_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorTypeLessThan(String value) {
            addLocatorTypeCriterion("element_locator_type_id <", value, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_locator_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorTypeLessThanOrEqualTo(String value) {
            addLocatorTypeCriterion("element_locator_type_id <=", value, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("element_locator_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLocatorTypeIn(List<String> values) {
            addLocatorTypeCriterion("element_locator_type_id in", values, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeNotIn(List<String> values) {
            addLocatorTypeCriterion("element_locator_type_id not in", values, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeBetween(String value1, String value2) {
            addLocatorTypeCriterion("element_locator_type_id between", value1, value2, "locatorType");
            return (Criteria) this;
        }

        public Criteria andLocatorTypeNotBetween(String value1, String value2) {
            addLocatorTypeCriterion("element_locator_type_id not between", value1, value2, "locatorType");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color_id is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color_id is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addColorCriterion("color_id =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("color_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addColorCriterion("color_id <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("color_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addColorCriterion("color_id >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("color_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addColorCriterion("color_id >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("color_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addColorCriterion("color_id <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("color_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addColorCriterion("color_id <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("color_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addColorCriterion("color_id in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addColorCriterion("color_id not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addColorCriterion("color_id between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addColorCriterion("color_id not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andParameterIsNull() {
            addCriterion("parameter is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("parameter is not null");
            return (Criteria) this;
        }

        public Criteria andParameterEqualTo(JsonNode value) {
            addParameterCriterion("parameter =", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("parameter = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(JsonNode value) {
            addParameterCriterion("parameter <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("parameter <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(JsonNode value) {
            addParameterCriterion("parameter >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("parameter > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("parameter >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(JsonNode value) {
            addParameterCriterion("parameter <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("parameter < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("parameter <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterIn(List<JsonNode> values) {
            addParameterCriterion("parameter in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<JsonNode> values) {
            addParameterCriterion("parameter not in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(JsonNode value1, JsonNode value2) {
            addParameterCriterion("parameter between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(JsonNode value1, JsonNode value2) {
            addParameterCriterion("parameter not between", value1, value2, "parameter");
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

        public Criteria andIsDriverEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_driver = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverNotEqualTo(Boolean value) {
            addCriterion("is_driver <>", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_driver <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThan(Boolean value) {
            addCriterion("is_driver >", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_driver > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_driver >=", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_driver >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThan(Boolean value) {
            addCriterion("is_driver <", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("is_driver < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanOrEqualTo(Boolean value) {
            addCriterion("is_driver <=", value, "isDriver");
            return (Criteria) this;
        }

        public Criteria andIsDriverLessThanOrEqualToColumn(Element.Column column) {
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

        public Criteria andProjectIdEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("project_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("project_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("project_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("project_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("project_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualToColumn(Element.Column column) {
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

        public Criteria andSectionIdIsNull() {
            addCriterion("section_id is null");
            return (Criteria) this;
        }

        public Criteria andSectionIdIsNotNull() {
            addCriterion("section_id is not null");
            return (Criteria) this;
        }

        public Criteria andSectionIdEqualTo(Long value) {
            addCriterion("section_id =", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("section_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualTo(Long value) {
            addCriterion("section_id <>", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("section_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThan(Long value) {
            addCriterion("section_id >", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("section_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("section_id >=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("section_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThan(Long value) {
            addCriterion("section_id <", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("section_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualTo(Long value) {
            addCriterion("section_id <=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("section_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSectionIdIn(List<Long> values) {
            addCriterion("section_id in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotIn(List<Long> values) {
            addCriterion("section_id not in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdBetween(Long value1, Long value2) {
            addCriterion("section_id between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotBetween(Long value1, Long value2) {
            addCriterion("section_id not between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIsNull() {
            addCriterion("copy_from_id is null");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIsNotNull() {
            addCriterion("copy_from_id is not null");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdEqualTo(Long value) {
            addCriterion("copy_from_id =", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("copy_from_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualTo(Long value) {
            addCriterion("copy_from_id <>", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("copy_from_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThan(Long value) {
            addCriterion("copy_from_id >", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("copy_from_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("copy_from_id >=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("copy_from_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThan(Long value) {
            addCriterion("copy_from_id <", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("copy_from_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualTo(Long value) {
            addCriterion("copy_from_id <=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("copy_from_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIn(List<Long> values) {
            addCriterion("copy_from_id in", values, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotIn(List<Long> values) {
            addCriterion("copy_from_id not in", values, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdBetween(Long value1, Long value2) {
            addCriterion("copy_from_id between", value1, value2, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotBetween(Long value1, Long value2) {
            addCriterion("copy_from_id not between", value1, value2, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdIsNull() {
            addCriterion("application_id is null");
            return (Criteria) this;
        }

        public Criteria andApplicationIdIsNotNull() {
            addCriterion("application_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationIdEqualTo(Long value) {
            addCriterion("application_id =", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("application_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotEqualTo(Long value) {
            addCriterion("application_id <>", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("application_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThan(Long value) {
            addCriterion("application_id >", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("application_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("application_id >=", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdGreaterThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("application_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThan(Long value) {
            addCriterion("application_id <", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanColumn(Element.Column column) {
            addCriterion(new StringBuilder("application_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanOrEqualTo(Long value) {
            addCriterion("application_id <=", value, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdLessThanOrEqualToColumn(Element.Column column) {
            addCriterion(new StringBuilder("application_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andApplicationIdIn(List<Long> values) {
            addCriterion("application_id in", values, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotIn(List<Long> values) {
            addCriterion("application_id not in", values, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdBetween(Long value1, Long value2) {
            addCriterion("application_id between", value1, value2, "applicationId");
            return (Criteria) this;
        }

        public Criteria andApplicationIdNotBetween(Long value1, Long value2) {
            addCriterion("application_id not between", value1, value2, "applicationId");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andCommentLikeInsensitive(String value) {
            addCriterion("upper(comment) like", value.toUpperCase(), "comment");
            return (Criteria) this;
        }

        public Criteria andLocatorValueLikeInsensitive(String value) {
            addCriterion("upper(locator_value) like", value.toUpperCase(), "locatorValue");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionXLikeInsensitive(String value) {
            addCriterion("upper(html_position_x) like", value.toUpperCase(), "htmlPositionX");
            return (Criteria) this;
        }

        public Criteria andHtmlPositionYLikeInsensitive(String value) {
            addCriterion("upper(html_position_y) like", value.toUpperCase(), "htmlPositionY");
            return (Criteria) this;
        }

        public Criteria andLogLikeInsensitive(String value) {
            addCriterion("upper(log) like", value.toUpperCase(), "log");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("(parameter->>'method')::text =", value, "method");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private ElementExample example;

        protected Criteria(ElementExample example) {
            super();
            this.example = example;
        }

        public ElementExample example() {
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

        public Criteria andLogicalDeleted(boolean deleted) {
            return deleted ? andDeletedEqualTo(Element.Deleted.IS_DELETED.value()) : andDeletedNotEqualTo(Element.Deleted.IS_DELETED.value());
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
        void example(com.meowlomo.atm.core.model.ElementExample example);
    }
}