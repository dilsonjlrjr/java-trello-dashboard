package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDtoResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("token_time_expiration")
    private Integer tokenTimeExpiration;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_token_time_expiration")
    private Integer refreshTokenTimeExpiration;
}
