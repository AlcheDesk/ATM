package com.meowlomo.atm.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.annotations.ApiModelProperty;

public class InstructionResult implements Serializable {
    private Long id;

    private String action;

    private Date createdAt;

    private Date updatedAt;

    private String status;

    private String log;

    private Boolean finished;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode instruction;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode data;

    private Long runId;

    private String runType;

    private Date startAt;

    private Date endAt;

    private String logicalOrderIndex;

    private String inputData;

    private String inputType;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode inputParameter;

    private String outputData;

    private String outputType;

    @ApiModelProperty(value = "JSON data", dataType = "String")
    private JsonNode outputParameter;

    private String expectedValue;

    private String returnValue;

    private Boolean isOverwrite;

    private String target;

    private Set<String> instructionOptions;

    private String instructionOptionLog;

    private Integer resultOverwritten;

    private Long instructionId;

    private String comment;

    private static final long serialVersionUID = 1L;

    private List<StepLog> stepLogs;

    private List<ExecutionLog> executionLogs;

    private List<File> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public JsonNode getInstruction() {
        return instruction;
    }

    public void setInstruction(JsonNode instruction) {
        this.instruction = instruction;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getLogicalOrderIndex() {
        return logicalOrderIndex;
    }

    public void setLogicalOrderIndex(String logicalOrderIndex) {
        this.logicalOrderIndex = logicalOrderIndex;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public JsonNode getInputParameter() {
        return inputParameter;
    }

    public void setInputParameter(JsonNode inputParameter) {
        this.inputParameter = inputParameter;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public JsonNode getOutputParameter() {
        return outputParameter;
    }

    public void setOutputParameter(JsonNode outputParameter) {
        this.outputParameter = outputParameter;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public Boolean getIsOverwrite() {
        return isOverwrite;
    }

    public void setIsOverwrite(Boolean isOverwrite) {
        this.isOverwrite = isOverwrite;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Set<String> getInstructionOptions() {
        return instructionOptions;
    }

    public void setInstructionOptions(Set<String> instructionOptions) {
        this.instructionOptions = instructionOptions;
    }

    public String getInstructionOptionLog() {
        return instructionOptionLog;
    }

    public void setInstructionOptionLog(String instructionOptionLog) {
        this.instructionOptionLog = instructionOptionLog;
    }

    public Integer getResultOverwritten() {
        return resultOverwritten;
    }

    public void setResultOverwritten(Integer resultOverwritten) {
        this.resultOverwritten = resultOverwritten;
    }

    public Long getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Long instructionId) {
        this.instructionId = instructionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<StepLog> getStepLogs() {
        return stepLogs;
    }

    public void setStepLogs(List<StepLog> stepLogs) {
        this.stepLogs = stepLogs;
    }

    public List<ExecutionLog> getExecutionLogs() {
        return executionLogs;
    }

    public void setExecutionLogs(List<ExecutionLog> executionLogs) {
        this.executionLogs = executionLogs;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        InstructionResult other = (InstructionResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getLog() == null ? other.getLog() == null : this.getLog().equals(other.getLog()))
                && (this.getFinished() == null ? other.getFinished() == null : this.getFinished().equals(other.getFinished()))
                && (this.getInstruction() == null ? other.getInstruction() == null : this.getInstruction().equals(other.getInstruction()))
                && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
                && (this.getRunId() == null ? other.getRunId() == null : this.getRunId().equals(other.getRunId()))
                && (this.getRunType() == null ? other.getRunType() == null : this.getRunType().equals(other.getRunType()))
                && (this.getStartAt() == null ? other.getStartAt() == null : this.getStartAt().equals(other.getStartAt()))
                && (this.getEndAt() == null ? other.getEndAt() == null : this.getEndAt().equals(other.getEndAt()))
                && (this.getLogicalOrderIndex() == null ? other.getLogicalOrderIndex() == null : this.getLogicalOrderIndex().equals(other.getLogicalOrderIndex()))
                && (this.getInputData() == null ? other.getInputData() == null : this.getInputData().equals(other.getInputData()))
                && (this.getInputType() == null ? other.getInputType() == null : this.getInputType().equals(other.getInputType()))
                && (this.getInputParameter() == null ? other.getInputParameter() == null : this.getInputParameter().equals(other.getInputParameter()))
                && (this.getOutputData() == null ? other.getOutputData() == null : this.getOutputData().equals(other.getOutputData()))
                && (this.getOutputType() == null ? other.getOutputType() == null : this.getOutputType().equals(other.getOutputType()))
                && (this.getOutputParameter() == null ? other.getOutputParameter() == null : this.getOutputParameter().equals(other.getOutputParameter()))
                && (this.getExpectedValue() == null ? other.getExpectedValue() == null : this.getExpectedValue().equals(other.getExpectedValue()))
                && (this.getReturnValue() == null ? other.getReturnValue() == null : this.getReturnValue().equals(other.getReturnValue()))
                && (this.getIsOverwrite() == null ? other.getIsOverwrite() == null : this.getIsOverwrite().equals(other.getIsOverwrite()))
                && (this.getTarget() == null ? other.getTarget() == null : this.getTarget().equals(other.getTarget()))
                && (this.getInstructionOptions() == null ? other.getInstructionOptions() == null : this.getInstructionOptions().equals(other.getInstructionOptions()))
                && (this.getInstructionOptionLog() == null ? other.getInstructionOptionLog() == null : this.getInstructionOptionLog().equals(other.getInstructionOptionLog()))
                && (this.getResultOverwritten() == null ? other.getResultOverwritten() == null : this.getResultOverwritten().equals(other.getResultOverwritten()))
                && (this.getInstructionId() == null ? other.getInstructionId() == null : this.getInstructionId().equals(other.getInstructionId()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLog() == null) ? 0 : getLog().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getInstruction() == null) ? 0 : getInstruction().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getRunId() == null) ? 0 : getRunId().hashCode());
        result = prime * result + ((getRunType() == null) ? 0 : getRunType().hashCode());
        result = prime * result + ((getStartAt() == null) ? 0 : getStartAt().hashCode());
        result = prime * result + ((getEndAt() == null) ? 0 : getEndAt().hashCode());
        result = prime * result + ((getLogicalOrderIndex() == null) ? 0 : getLogicalOrderIndex().hashCode());
        result = prime * result + ((getInputData() == null) ? 0 : getInputData().hashCode());
        result = prime * result + ((getInputType() == null) ? 0 : getInputType().hashCode());
        result = prime * result + ((getInputParameter() == null) ? 0 : getInputParameter().hashCode());
        result = prime * result + ((getOutputData() == null) ? 0 : getOutputData().hashCode());
        result = prime * result + ((getOutputType() == null) ? 0 : getOutputType().hashCode());
        result = prime * result + ((getOutputParameter() == null) ? 0 : getOutputParameter().hashCode());
        result = prime * result + ((getExpectedValue() == null) ? 0 : getExpectedValue().hashCode());
        result = prime * result + ((getReturnValue() == null) ? 0 : getReturnValue().hashCode());
        result = prime * result + ((getIsOverwrite() == null) ? 0 : getIsOverwrite().hashCode());
        result = prime * result + ((getTarget() == null) ? 0 : getTarget().hashCode());
        result = prime * result + ((getInstructionOptions() == null) ? 0 : getInstructionOptions().hashCode());
        result = prime * result + ((getInstructionOptionLog() == null) ? 0 : getInstructionOptionLog().hashCode());
        result = prime * result + ((getResultOverwritten() == null) ? 0 : getResultOverwritten().hashCode());
        result = prime * result + ((getInstructionId() == null) ? 0 : getInstructionId().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", action=").append(action);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", status=").append(status);
        sb.append(", log=").append(log);
        sb.append(", finished=").append(finished);
        sb.append(", instruction=").append(instruction);
        sb.append(", data=").append(data);
        sb.append(", runId=").append(runId);
        sb.append(", runType=").append(runType);
        sb.append(", startAt=").append(startAt);
        sb.append(", endAt=").append(endAt);
        sb.append(", logicalOrderIndex=").append(logicalOrderIndex);
        sb.append(", inputData=").append(inputData);
        sb.append(", inputType=").append(inputType);
        sb.append(", inputParameter=").append(inputParameter);
        sb.append(", outputData=").append(outputData);
        sb.append(", outputType=").append(outputType);
        sb.append(", outputParameter=").append(outputParameter);
        sb.append(", expectedValue=").append(expectedValue);
        sb.append(", returnValue=").append(returnValue);
        sb.append(", isOverwrite=").append(isOverwrite);
        sb.append(", target=").append(target);
        sb.append(", instructionOptions=").append(instructionOptions);
        sb.append(", instructionOptionLog=").append(instructionOptionLog);
        sb.append(", resultOverwritten=").append(resultOverwritten);
        sb.append(", instructionId=").append(instructionId);
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), action("action", "action", "VARCHAR", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), updatedAt("updated_at", "updatedAt", "TIMESTAMP", false), status("status_id", "status",
                "OTHER", false), log("log", "log", "VARCHAR", false), finished("is_finished", "finished", "BIT", false), instruction("instruction", "instruction", "OTHER", false), data("data", "data", "OTHER", false), runId("run_id",
                        "runId", "BIGINT", false), runType("run_type_id", "runType", "OTHER", false), startAt("start_at", "startAt", "TIMESTAMP", false), endAt("end_at", "endAt", "TIMESTAMP", false), logicalOrderIndex("logical_order_index",
                                "logicalOrderIndex", "VARCHAR", false), inputData("input_data", "inputData", "VARCHAR", false), inputType("input_type", "inputType", "VARCHAR", false), inputParameter("input_parameter", "inputParameter",
                                        "OTHER", false), outputData("output_data", "outputData", "VARCHAR", false), outputType("output_type", "outputType", "VARCHAR", false), outputParameter("output_parameter", "outputParameter", "OTHER",
                                                false), expectedValue("expected_value", "expectedValue", "VARCHAR", false), returnValue("return_value", "returnValue", "VARCHAR", false), isOverwrite("is_overwrite", "isOverwrite", "BIT",
                                                        false), target("target", "target", "VARCHAR", false), instructionOptions("instruction_options", "instructionOptions", "ARRAY", false), instructionOptionLog("instruction_option_log",
                                                                "instructionOptionLog", "VARCHAR", false), resultOverwritten("result_overwritten", "resultOverwritten", "INTEGER",
                                                                        false), instructionId("instruction_id", "instructionId", "BIGINT", false), comment("comment", "comment", "VARCHAR", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[] {});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            }
            else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}