package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumMainControllerAdviceMessage {

    GENERIC_ERROR("GENERIC_ERROR"),
    BAD_CREDENTIALS("Bad credentials!"),
    TOKEN_EXPIRED("Token expired!"),
    SESSION_TOKEN_INVALID("Session token invalid!"),
    INVALID_TOKEN("Invalid Token!");

    @Getter
    private String message;
}
