package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDtoRequest {
    @NotEmpty
    @Size(min = 5, max = 255)
    @JsonProperty("name")
    private String name;
    @NotEmpty
    @Size(min = 5, max = 500)
    @JsonProperty("trello_token")
    private String trelloToken;
    @NotEmpty
    @Size(min = 5, max = 500)
    @JsonProperty("trello_key")
    private String trelloKey;
}
