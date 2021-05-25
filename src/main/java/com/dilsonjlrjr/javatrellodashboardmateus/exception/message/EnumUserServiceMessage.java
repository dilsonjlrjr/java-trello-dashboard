package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumUserServiceMessage {

    ENTITY_NOT_FOUND("User not found!");

    @Getter
    private String message;
}
