package com.meowlomo.atm.core.resource.exception;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;

import com.meowlomo.atm.config.Constants;

public class CustomBadRequestException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    private String message;
    private String trace;
    private int status;
    private String code;
    private String type;
    private UUID uuid;

    public CustomBadRequestException(Exception ex, String message, String trace, String code, UUID uuid) {
        this.message = message;
        this.trace = trace;
        this.type = ex == null ? BadRequestException.class.getName() : ex.getClass().getName();
        this.uuid = uuid;
        this.code = code == null ? Constants.APP_NAME + HttpServletResponse.SC_BAD_REQUEST
                : Constants.APP_NAME + HttpServletResponse.SC_BAD_REQUEST + code;
        this.status = HttpServletResponse.SC_BAD_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return trace;
    }

    public void setDeveloperMessage(String trace) {
        this.trace = trace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
