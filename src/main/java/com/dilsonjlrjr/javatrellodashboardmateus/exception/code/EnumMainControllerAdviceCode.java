package com.dilsonjlrjr.javatrellodashboardmateus.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumMainControllerAdviceCode {

    HTTP_CLIENT_ERROR_EXCEPTION("HTTP_CLIENT_ERROR_EXCEPTION"),
    IO_EXCEPTION("IO_EXCEPTION"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("METHOD_ARGUMENT_NOT_VALID_EXCEPTION"),
    RUNTIME_EXCEPTION("RUNTIME_EXCEPTION"),
    BAD_CREDENTIALS("BAD_CREDENTIALS"),
    EXPIRED_TOKEN("EXPIRED_TOKEN");

    @Getter
    private String code;
}
