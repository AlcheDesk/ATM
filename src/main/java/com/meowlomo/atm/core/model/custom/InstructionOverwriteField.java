package com.meowlomo.atm.core.model.custom;

import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;
import com.meowlomo.atm.core.model.TestCaseOverwrite;

public class InstructionOverwriteField implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String jsonPath;

    private Long refTestCaseOverwriteId;

    private Long refTestCaseId;

    private TestCaseOverwrite refTestCaseOverwrite;

    private String valueType;

    private JsonNode value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public JsonNode getValue() {
        return value;
    }

    public void setValue(JsonNode value) {
        this.value = value;
    }

    public Long getRefTestCaseOverwriteId() {
        return refTestCaseOverwriteId;
    }

    public void setRefTestCaseOverwriteId(Long refTestCaseOverwriteId) {
        this.refTestCaseOverwriteId = refTestCaseOverwriteId;
    }

    public TestCaseOverwrite getRefTestCaseOverwrite() {
        return refTestCaseOverwrite;
    }

    public void setRefTestCaseOverwrite(TestCaseOverwrite refTestCaseOverwrite) {
        this.refTestCaseOverwrite = refTestCaseOverwrite;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Long getRefTestCaseId() {
        return refTestCaseId;
    }

    public void setRefTestCaseId(Long refTestCaseId) {
        this.refTestCaseId = refTestCaseId;
    }

}
