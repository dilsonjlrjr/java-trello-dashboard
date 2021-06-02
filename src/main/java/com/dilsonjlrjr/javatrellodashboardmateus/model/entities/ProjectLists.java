package com.dilsonjlrjr.javatrellodashboardmateus.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectLists {

    private Project project;
    private ListsType listsType;
    private String idTrello;
}
