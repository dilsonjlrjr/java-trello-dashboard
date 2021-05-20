package com.dilsonjlrjr.javatrellodashboardmateus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDtoRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
