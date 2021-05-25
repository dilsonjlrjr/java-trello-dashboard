package com.dilsonjlrjr.javatrellodashboardmateus.exception;

import lombok.Getter;

public class ServiceException extends RuntimeException {

    @Getter
    private String code;
    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }
}
