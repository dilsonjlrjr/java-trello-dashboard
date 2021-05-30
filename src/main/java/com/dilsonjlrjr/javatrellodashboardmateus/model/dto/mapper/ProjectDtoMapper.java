package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProjectDtoMapper {
    @Mappings({
            @Mapping(source = "project.trelloToken", target = "token"),
            @Mapping(source = "project.trelloKey", target = "key")
    })
    ProjectDtoResponse projectsToProjectDtoResponse(Project project);
}
