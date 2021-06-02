package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectListsDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectListsDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ProjectLists;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProjectListsDtoMapper {

    @Mappings({
            @Mapping(source = "projectLists.project.id", target = "idProject"),
            @Mapping(source = "projectLists.listsType", target = "listsType"),
            @Mapping(source = "projectLists.idTrello", target = "idTrello")
    })
    ProjectListsDtoResponse projectListsToProjectListsDtoResponse(ProjectLists projectLists);

    @Mappings({
            @Mapping(source = "project", target = "project"),
            @Mapping(source = "projectListsDtoRequest.idListType", target = "listsType.id"),
            @Mapping(source = "projectListsDtoRequest.idTrello", target = "idTrello")
    })
    ProjectLists projectListsDtoRequestToProjectLists(Project project, ProjectListsDtoRequest projectListsDtoRequest);
}
