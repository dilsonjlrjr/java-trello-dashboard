package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumMainControllerAdviceMessage {

    GENERIC_ERROR("GENERIC_ERROR"),
    BAD_CREDENTIALS("Bad credentials!"),
    EXPIRED_TOKEN("Token expired!"),
    SESSION_TOKEN_INVALID("Session token invalid!"),
    INVALID_TOKEN("Invalid Token!"),
    FIELD_NOT_VALID("Fields not validated"),
    USER_NOT_FOUND_BAD_CREDENTIALS("User not found or bad credentials!");

    @Getter
    private String message;
}
