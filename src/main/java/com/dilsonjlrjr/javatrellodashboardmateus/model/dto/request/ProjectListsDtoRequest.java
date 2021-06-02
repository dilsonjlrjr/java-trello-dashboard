package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectListsDtoRequest {
    @JsonProperty("lists_type")
    private Integer idListType;

    @JsonProperty("id_trello")
    private String idTrello;
}
