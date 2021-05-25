package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRefreshDtoRequest {

    @NotEmpty
    @JsonProperty("refresh_token")
    private String refreshToken;
}
