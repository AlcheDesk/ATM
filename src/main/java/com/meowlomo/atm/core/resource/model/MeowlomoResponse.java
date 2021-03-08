package com.meowlomo.atm.core.resource.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.ApiModelProperty;

@JsonRootName(value = "Response")

public class MeowlomoResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "JSON data", dataType = "String")
    private ObjectNode metadata;
    @ApiModelProperty(value = "JSON data", dataType = "String")
    private Object data;
    @ApiModelProperty(value = "JSON data", dataType = "String")
    private MeowlomoErrorResponse error;
    
    private long dateCount = 0;

    public MeowlomoResponse() {
    }

    public MeowlomoResponse(ObjectNode metadata, Object data, MeowlomoErrorResponse restErrorResponse) {
        //set the data first, because it will be used by the metadata
        setData(data);
        setError(restErrorResponse);
        setMetadata(metadata);
    }

    public ObjectNode getMetadata() {
        return metadata;
    }

    public void setMetadata(ObjectNode metadata) {
        if (metadata == null) {
            this.metadata = JsonNodeFactory.instance.objectNode();
            this.metadata.put("count", dateCount);
        }
        else {
            this.metadata = metadata;
            if (this.metadata.isEmpty(null)) {
                this.metadata.put("count", dateCount);
            }
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        if (data == null) {
            this.data = JsonNodeFactory.instance.arrayNode();
        }
        else {
            if (data instanceof List<?>) {
                this.data = data;
                this.dateCount = ((List<?>) data).size();
            }
            else {
                List<Object> jsonArray = new ArrayList<>();
                jsonArray.add(data);
                this.data = jsonArray;
                this.dateCount = 0;
            }
        }
    }

    public Object getError() {
        return error;
    }

    public void setError(MeowlomoErrorResponse restErrorResponse) {
        if (restErrorResponse == null) {
            this.error = new MeowlomoErrorResponse();
            this.error.setStatusCode(200);
        }
        else {
            this.error = restErrorResponse;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", metadata=").append(metadata);
        sb.append(", data=").append(data);
        sb.append(", error=").append(error);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
