package com.dilsonjlrjr.javatrellodashboardmateus.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumAuthServiceCode {

    TOKEN_TYPE_INVALID("TOKEN_TYPE_INVALID");

    @Getter
    private String code;
}
