package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDtoRequest {

    @JsonProperty("username")
    @NotEmpty
    private String username;

    @JsonProperty("password")
    @NotEmpty
    private String password;
}
