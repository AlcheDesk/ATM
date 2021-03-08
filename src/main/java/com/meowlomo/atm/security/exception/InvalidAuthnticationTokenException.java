package com.meowlomo.atm.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidAuthnticationTokenException extends AuthenticationException {
    public InvalidAuthnticationTokenException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = 1L;
}
