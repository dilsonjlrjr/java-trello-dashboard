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
    INVALID_TOKEN("INVALID_TOKEN"),
    SESSION_TOKEN_INVALID("SESSION_TOKEN_INVALID"),
    EXPIRED_TOKEN("EXPIRED_TOKEN"),
    USER_NOT_FOUND_BAD_CREDENTIALS("USER_NOT_FOUND_BAD_CREDENTIALS");

    @Getter
    private final String code;
}
