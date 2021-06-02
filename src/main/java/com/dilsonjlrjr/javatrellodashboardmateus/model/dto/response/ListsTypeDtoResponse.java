package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListsTypeDtoResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}
