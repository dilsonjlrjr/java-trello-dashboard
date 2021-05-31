package com.dilsonjlrjr.javatrellodashboardmateus.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumSecurityResourceCode {

    UNAUTHORIZED_ACCESS_RESOURCE("UNAUTHORIZED_ACCESS_RESOURCE");

    @Getter
    private String code;
}
