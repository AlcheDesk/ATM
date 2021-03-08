package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;

public class ApiSchemaExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApiSchemaExample() {
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

    public ApiSchemaExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public ApiSchemaExample orderBy(String... orderByClauses) {
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
        ApiSchemaExample example = new ApiSchemaExample();
        return example.createCriteria();
    }

    public ApiSchemaExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public ApiSchemaExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> jsonSchemaCriteria;

        protected List<Criterion> xmlSchemaCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            jsonSchemaCriteria = new ArrayList<Criterion>();
            xmlSchemaCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getJsonSchemaCriteria() {
            return jsonSchemaCriteria;
        }

        protected void addJsonSchemaCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            jsonSchemaCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addJsonSchemaCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            jsonSchemaCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getXmlSchemaCriteria() {
            return xmlSchemaCriteria;
        }

        protected void addXmlSchemaCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            xmlSchemaCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.XMLTypeHandler"));
            allCriteria = null;
        }

        protected void addXmlSchemaCriterion(String condition, Document value1, Document value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            xmlSchemaCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.XMLTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || jsonSchemaCriteria.size() > 0 || xmlSchemaCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(jsonSchemaCriteria);
                allCriteria.addAll(xmlSchemaCriteria);
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

        public Criteria andIdEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(ApiSchema.Column column) {
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

        public Criteria andNameEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(ApiSchema.Column column) {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("type = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("type <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("type > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("type >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("type < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("type <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaIsNull() {
            addCriterion("json_schema is null");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaIsNotNull() {
            addCriterion("json_schema is not null");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaEqualTo(JsonNode value) {
            addJsonSchemaCriterion("json_schema =", value, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("json_schema = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJsonSchemaNotEqualTo(JsonNode value) {
            addJsonSchemaCriterion("json_schema <>", value, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaNotEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("json_schema <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJsonSchemaGreaterThan(JsonNode value) {
            addJsonSchemaCriterion("json_schema >", value, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaGreaterThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("json_schema > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJsonSchemaGreaterThanOrEqualTo(JsonNode value) {
            addJsonSchemaCriterion("json_schema >=", value, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaGreaterThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("json_schema >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJsonSchemaLessThan(JsonNode value) {
            addJsonSchemaCriterion("json_schema <", value, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaLessThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("json_schema < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJsonSchemaLessThanOrEqualTo(JsonNode value) {
            addJsonSchemaCriterion("json_schema <=", value, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaLessThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("json_schema <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJsonSchemaIn(List<JsonNode> values) {
            addJsonSchemaCriterion("json_schema in", values, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaNotIn(List<JsonNode> values) {
            addJsonSchemaCriterion("json_schema not in", values, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaBetween(JsonNode value1, JsonNode value2) {
            addJsonSchemaCriterion("json_schema between", value1, value2, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andJsonSchemaNotBetween(JsonNode value1, JsonNode value2) {
            addJsonSchemaCriterion("json_schema not between", value1, value2, "jsonSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaIsNull() {
            addCriterion("xml_schema is null");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaIsNotNull() {
            addCriterion("xml_schema is not null");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaEqualTo(Document value) {
            addXmlSchemaCriterion("xml_schema =", value, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("xml_schema = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXmlSchemaNotEqualTo(Document value) {
            addXmlSchemaCriterion("xml_schema <>", value, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaNotEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("xml_schema <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXmlSchemaGreaterThan(Document value) {
            addXmlSchemaCriterion("xml_schema >", value, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaGreaterThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("xml_schema > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXmlSchemaGreaterThanOrEqualTo(Document value) {
            addXmlSchemaCriterion("xml_schema >=", value, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaGreaterThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("xml_schema >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXmlSchemaLessThan(Document value) {
            addXmlSchemaCriterion("xml_schema <", value, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaLessThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("xml_schema < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXmlSchemaLessThanOrEqualTo(Document value) {
            addXmlSchemaCriterion("xml_schema <=", value, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaLessThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("xml_schema <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXmlSchemaIn(List<Document> values) {
            addXmlSchemaCriterion("xml_schema in", values, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaNotIn(List<Document> values) {
            addXmlSchemaCriterion("xml_schema not in", values, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaBetween(Document value1, Document value2) {
            addXmlSchemaCriterion("xml_schema between", value1, value2, "xmlSchema");
            return (Criteria) this;
        }

        public Criteria andXmlSchemaNotBetween(Document value1, Document value2) {
            addXmlSchemaCriterion("xml_schema not between", value1, value2, "xmlSchema");
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

        public Criteria andCopyFromIdEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("copy_from_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualTo(Long value) {
            addCriterion("copy_from_id <>", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("copy_from_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThan(Long value) {
            addCriterion("copy_from_id >", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("copy_from_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("copy_from_id >=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualToColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("copy_from_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThan(Long value) {
            addCriterion("copy_from_id <", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanColumn(ApiSchema.Column column) {
            addCriterion(new StringBuilder("copy_from_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualTo(Long value) {
            addCriterion("copy_from_id <=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualToColumn(ApiSchema.Column column) {
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

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(type) like", value.toUpperCase(), "type");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private ApiSchemaExample example;

        protected Criteria(ApiSchemaExample example) {
            super();
            this.example = example;
        }

        public ApiSchemaExample example() {
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
        void example(com.meowlomo.atm.core.model.ApiSchemaExample example);
    }
}