package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumProjectServiceMessage {

    ENTITY_NOT_FOUND("Project not found!");

    @Getter
    private String message;
}
