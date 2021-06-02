package com.dilsonjlrjr.javatrellodashboardmateus.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumProjectServiceCode {

    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND");

    @Getter
    private final String code;
}
