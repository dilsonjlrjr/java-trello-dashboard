package com.dilsonjlrjr.javatrellodashboardmateus.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumProjectListsServiceMessage {

    ENTITY_NOT_FOUND("List not found!");

    @Getter
    private String message;
}
