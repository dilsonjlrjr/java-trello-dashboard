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

    @Mapping(source = "sprintWillUpdate.id", target = "id")
    @Mapping(source = "sprintWillUpdate.project", target = "project")
    @Mapping(source = "sprintDtoRequest.name", target = "name")
    @Mapping(source = "sprintDtoRequest.goal", target = "goal")
    @Mapping(source = "sprintDtoRequest.start", target = "start")
    @Mapping(source = "sprintDtoRequest.end", target = "end")
    @Mapping(source = "sprintDtoRequest.delivery", target = "delivery")
    @Mapping(source = "sprintDtoRequest.numberWork", target = "numberWork")
    @Mapping(source = "sprintDtoRequest.hoursPerDay", target = "hoursPerDay")
    Sprint sprintDtoRequestToSprintMerge(Sprint sprintWillUpdate, SprintDtoRequest sprintDtoRequest);
}
