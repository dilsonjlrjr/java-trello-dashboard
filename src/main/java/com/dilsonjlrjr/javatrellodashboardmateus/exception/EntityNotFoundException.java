package com.dilsonjlrjr.javatrellodashboardmateus.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {

    @Getter
    private final String code;
    public EntityNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
