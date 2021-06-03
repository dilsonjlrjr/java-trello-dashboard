package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.SprintDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.SprintDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SprintDtoMapper {

    SprintDtoResponse sprintToSprintDtoResponse(Sprint sprint);

    @Mapping(source = "idProject", target = "project.id")
    Sprint sprintDtoRequestToSprint(SprintDtoRequest sprintDtoRequest, Long idProject);
}
