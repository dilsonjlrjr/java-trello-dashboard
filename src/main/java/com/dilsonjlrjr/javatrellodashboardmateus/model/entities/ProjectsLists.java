package com.dilsonjlrjr.javatrellodashboardmateus.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsLists {

    private Projects project;
    private ListsType type;
    private String trelloId;
}
