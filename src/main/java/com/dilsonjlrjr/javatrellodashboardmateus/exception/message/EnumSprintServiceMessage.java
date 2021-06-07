package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumSprintServiceMessage {

    ENTITY_NOT_FOUND("Sprint not found!");

    @Getter
    private String message;
}
