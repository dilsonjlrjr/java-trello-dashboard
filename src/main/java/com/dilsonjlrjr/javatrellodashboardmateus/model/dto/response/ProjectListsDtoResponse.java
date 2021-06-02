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
public class ProjectListsDtoResponse {
    @JsonProperty("id_project")
    private Long idProject;

    @JsonProperty("lists_type")
    private ListsTypeDtoResponse listsType;

    @JsonProperty("id_trello")
    private String idTrello;
}
