package com.dilsonjlrjr.javatrellodashboardmateus.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumSprintServiceCode {

    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND");

    @Getter
    private final String code;
}
