package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProjectDtoMapper {

    ProjectDtoResponse projectToProjectDtoResponse(Project project);

    @Mappings({
            @Mapping(source = "idUsername", target = "owner.id")
    })
    Project projectDtoRequestToProject(ProjectDtoRequest projectDtoRequest, Long idUsername);

    @Mappings({
            @Mapping(source = "projectWillUpdate.id", target = "id"),
            @Mapping(source = "projectWillUpdate.owner", target = "owner"),
            @Mapping(source = "projectDtoRequest.name", target = "name"),
            @Mapping(source = "projectDtoRequest.trelloToken", target = "trelloToken"),
            @Mapping(source = "projectDtoRequest.trelloKey", target = "trelloKey")
    })
    Project projectUpdateToProject(Project projectWillUpdate, ProjectDtoRequest projectDtoRequest);
}
