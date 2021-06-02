package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumAuthServiceMessage {

    TOKEN_TYPE_INVALID("Type token is invalid!");

    @Getter
    private final String message;
}
