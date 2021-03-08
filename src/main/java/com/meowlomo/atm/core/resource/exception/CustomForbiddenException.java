package com.meowlomo.atm.core.resource.exception;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;

import com.meowlomo.atm.config.Constants;

public class CustomForbiddenException extends ForbiddenException {

    private static final long serialVersionUID = 1L;

    private String message;
    private String trace;
    private int status;
    private String code;
    private String type;
    private UUID uuid;

    public CustomForbiddenException(Exception ex, String message, String trace, String code, UUID uuid) {
        this.message = message;
        this.trace = trace;
        this.type = ex == null ? ForbiddenException.class.getName() : ex.getClass().getName();
        this.uuid = uuid;
        this.code = code == null ? Constants.APP_NAME + HttpServletResponse.SC_FORBIDDEN
                : Constants.APP_NAME + HttpServletResponse.SC_FORBIDDEN + code;
        this.status = HttpServletResponse.SC_FORBIDDEN;
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
