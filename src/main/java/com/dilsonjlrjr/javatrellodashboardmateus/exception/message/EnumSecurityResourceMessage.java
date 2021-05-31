package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumSecurityResourceMessage {

    UNAUTHORIZED_ACCESS_RESOURCE("You are not allowed to access this type of resource.");

    @Getter
    private String message;
}
