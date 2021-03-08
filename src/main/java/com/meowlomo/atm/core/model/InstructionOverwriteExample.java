package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class InstructionOverwriteExample implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructionOverwriteExample() {
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

    public InstructionOverwriteExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public InstructionOverwriteExample orderBy(String... orderByClauses) {
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
        InstructionOverwriteExample example = new InstructionOverwriteExample();
        return example.createCriteria();
    }

    public InstructionOverwriteExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public InstructionOverwriteExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> dataCriteria;

        protected List<Criterion> typeCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            dataCriteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getDataCriteria() {
            return dataCriteria;
        }

        protected void addDataCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            dataCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addDataCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            dataCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.atm.typehandler.InstructionTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.atm.typehandler.InstructionTypeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || dataCriteria.size() > 0 || typeCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(dataCriteria);
                allCriteria.addAll(typeCriteria);
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

        public Criteria andIdEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
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

        public Criteria andTestCaseOverwriteIdIsNull() {
            addCriterion("test_case_overwrite_id is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdIsNotNull() {
            addCriterion("test_case_overwrite_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdEqualTo(Long value) {
            addCriterion("test_case_overwrite_id =", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotEqualTo(Long value) {
            addCriterion("test_case_overwrite_id <>", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThan(Long value) {
            addCriterion("test_case_overwrite_id >", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_overwrite_id >=", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThan(Long value) {
            addCriterion("test_case_overwrite_id <", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_overwrite_id <=", value, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_overwrite_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdIn(List<Long> values) {
            addCriterion("test_case_overwrite_id in", values, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotIn(List<Long> values) {
            addCriterion("test_case_overwrite_id not in", values, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdBetween(Long value1, Long value2) {
            addCriterion("test_case_overwrite_id between", value1, value2, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseOverwriteIdNotBetween(Long value1, Long value2) {
            addCriterion("test_case_overwrite_id not between", value1, value2, "testCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdIsNull() {
            addCriterion("test_case_id is null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdIsNotNull() {
            addCriterion("test_case_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdEqualTo(Long value) {
            addCriterion("test_case_id =", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualTo(Long value) {
            addCriterion("test_case_id <>", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThan(Long value) {
            addCriterion("test_case_id >", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_case_id >=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThan(Long value) {
            addCriterion("test_case_id <", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("test_case_id <=", value, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("test_case_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTestCaseIdIn(List<Long> values) {
            addCriterion("test_case_id in", values, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotIn(List<Long> values) {
            addCriterion("test_case_id not in", values, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdBetween(Long value1, Long value2) {
            addCriterion("test_case_id between", value1, value2, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andTestCaseIdNotBetween(Long value1, Long value2) {
            addCriterion("test_case_id not between", value1, value2, "testCaseId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdIsNull() {
            addCriterion("instruction_id is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIdIsNotNull() {
            addCriterion("instruction_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionIdEqualTo(Long value) {
            addCriterion("instruction_id =", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotEqualTo(Long value) {
            addCriterion("instruction_id <>", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThan(Long value) {
            addCriterion("instruction_id >", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("instruction_id >=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThan(Long value) {
            addCriterion("instruction_id <", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanOrEqualTo(Long value) {
            addCriterion("instruction_id <=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstructionIdIn(List<Long> values) {
            addCriterion("instruction_id in", values, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotIn(List<Long> values) {
            addCriterion("instruction_id not in", values, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdBetween(Long value1, Long value2) {
            addCriterion("instruction_id between", value1, value2, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotBetween(Long value1, Long value2) {
            addCriterion("instruction_id not between", value1, value2, "instructionId");
            return (Criteria) this;
        }

        public Criteria andElementIdIsNull() {
            addCriterion("element_id is null");
            return (Criteria) this;
        }

        public Criteria andElementIdIsNotNull() {
            addCriterion("element_id is not null");
            return (Criteria) this;
        }

        public Criteria andElementIdEqualTo(Long value) {
            addCriterion("element_id =", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("element_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualTo(Long value) {
            addCriterion("element_id <>", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("element_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThan(Long value) {
            addCriterion("element_id >", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("element_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualTo(Long value) {
            addCriterion("element_id >=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("element_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdLessThan(Long value) {
            addCriterion("element_id <", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("element_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualTo(Long value) {
            addCriterion("element_id <=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("element_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andElementIdIn(List<Long> values) {
            addCriterion("element_id in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotIn(List<Long> values) {
            addCriterion("element_id not in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdBetween(Long value1, Long value2) {
            addCriterion("element_id between", value1, value2, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotBetween(Long value1, Long value2) {
            addCriterion("element_id not between", value1, value2, "elementId");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsIsNull() {
            addCriterion("overwrite_fields is null");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsIsNotNull() {
            addCriterion("overwrite_fields is not null");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsEqualTo(String value) {
            addCriterion("overwrite_fields =", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("overwrite_fields = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsNotEqualTo(String value) {
            addCriterion("overwrite_fields <>", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("overwrite_fields <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsGreaterThan(String value) {
            addCriterion("overwrite_fields >", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("overwrite_fields > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsGreaterThanOrEqualTo(String value) {
            addCriterion("overwrite_fields >=", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("overwrite_fields >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsLessThan(String value) {
            addCriterion("overwrite_fields <", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("overwrite_fields < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsLessThanOrEqualTo(String value) {
            addCriterion("overwrite_fields <=", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("overwrite_fields <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsLike(String value) {
            addCriterion("overwrite_fields like", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsNotLike(String value) {
            addCriterion("overwrite_fields not like", value, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsIn(List<String> values) {
            addCriterion("overwrite_fields in", values, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsNotIn(List<String> values) {
            addCriterion("overwrite_fields not in", values, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsBetween(String value1, String value2) {
            addCriterion("overwrite_fields between", value1, value2, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andOverwriteFieldsNotBetween(String value1, String value2) {
            addCriterion("overwrite_fields not between", value1, value2, "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(JsonNode value) {
            addDataCriterion("data =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("data = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(JsonNode value) {
            addDataCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("data <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(JsonNode value) {
            addDataCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("data > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(JsonNode value) {
            addDataCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("data >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThan(JsonNode value) {
            addDataCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("data < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(JsonNode value) {
            addDataCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("data <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataIn(List<JsonNode> values) {
            addDataCriterion("data in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<JsonNode> values) {
            addDataCriterion("data not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(JsonNode value1, JsonNode value2) {
            addDataCriterion("data between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(JsonNode value1, JsonNode value2) {
            addDataCriterion("data not between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("instruction_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("instruction_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("instruction_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("instruction_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("instruction_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("instruction_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("instruction_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("instruction_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("instruction_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("instruction_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("instruction_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("instruction_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("instruction_type_id not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdIsNull() {
            addCriterion("ref_test_case_overwrite_id is null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdIsNotNull() {
            addCriterion("ref_test_case_overwrite_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id =", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id <>", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThan(Long value) {
            addCriterion("ref_test_case_overwrite_id >", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id >=", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThan(Long value) {
            addCriterion("ref_test_case_overwrite_id <", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanOrEqualTo(Long value) {
            addCriterion("ref_test_case_overwrite_id <=", value, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("ref_test_case_overwrite_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdIn(List<Long> values) {
            addCriterion("ref_test_case_overwrite_id in", values, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotIn(List<Long> values) {
            addCriterion("ref_test_case_overwrite_id not in", values, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdBetween(Long value1, Long value2) {
            addCriterion("ref_test_case_overwrite_id between", value1, value2, "refTestCaseOverwriteId");
            return (Criteria) this;
        }

        public Criteria andRefTestCaseOverwriteIdNotBetween(Long value1, Long value2) {
            addCriterion("ref_test_case_overwrite_id not between", value1, value2, "refTestCaseOverwriteId");
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

        public Criteria andCopyFromIdEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("copy_from_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualTo(Long value) {
            addCriterion("copy_from_id <>", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("copy_from_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThan(Long value) {
            addCriterion("copy_from_id >", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("copy_from_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("copy_from_id >=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("copy_from_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThan(Long value) {
            addCriterion("copy_from_id <", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("copy_from_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualTo(Long value) {
            addCriterion("copy_from_id <=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
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

        public Criteria andLogEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("log = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogNotEqualTo(String value) {
            addCriterion("log <>", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("log <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThan(String value) {
            addCriterion("log >", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("log > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualTo(String value) {
            addCriterion("log >=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualToColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("log >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThan(String value) {
            addCriterion("log <", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanColumn(InstructionOverwrite.Column column) {
            addCriterion(new StringBuilder("log < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualTo(String value) {
            addCriterion("log <=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualToColumn(InstructionOverwrite.Column column) {
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

        public Criteria andOverwriteFieldsLikeInsensitive(String value) {
            addCriterion("upper(overwrite_fields) like", value.toUpperCase(), "overwriteFields");
            return (Criteria) this;
        }

        public Criteria andLogLikeInsensitive(String value) {
            addCriterion("upper(log) like", value.toUpperCase(), "log");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private InstructionOverwriteExample example;

        protected Criteria(InstructionOverwriteExample example) {
            super();
            this.example = example;
        }

        public InstructionOverwriteExample example() {
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
        void example(com.meowlomo.atm.core.model.InstructionOverwriteExample example);
    }
}