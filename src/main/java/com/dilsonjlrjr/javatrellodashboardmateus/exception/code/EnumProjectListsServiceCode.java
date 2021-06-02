package com.dilsonjlrjr.javatrellodashboardmateus.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumProjectListsServiceCode {

    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND");

    @Getter
    private String code;
}
