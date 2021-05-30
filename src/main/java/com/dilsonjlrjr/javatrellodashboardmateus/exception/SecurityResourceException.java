package com.dilsonjlrjr.javatrellodashboardmateus.exception;

import lombok.Getter;

public class SecurityResourceException extends RuntimeException {

    @Getter
    private String code;
    public SecurityResourceException(String message, String code) {
        super(message);
        this.code = code;
    }
}
